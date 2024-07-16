package _untouchable_.supportC2x00;


import static _untouchable_.supportC2x00.Configuration.mainPartOfTestResultDataBaseFileName;
import static _untouchable_.supportC2x00.Configuration.mainPartOfTestResultDataBasePath;
import static _untouchable_.supportC2x00.Herald.Medium.*;

//
//
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
//
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
//
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;


/**
 * LabExam11xx_4XIB1-P1    (PTP/Eclipse)<br />
 *<br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * The Test Result DataBase Manager is expected to be created for each individual exercise.
 * Hence it is NOT in the responsibility of the Test Result DataBase Manager to handle two or more exercises at the same time!
 *<br />
 *<br />
 * VCS: see ReadMe.txt
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  210526 #01
 */
public class TestResultDataBaseManager {
    
    /**
     * ...
     */
    final TestResultTable  currentTestResults;                                  // package scope on purpose
    
    final private TE  theExercise;                                              // ...
    
    
    final private String localPathToDbGuard;
    final private String localPathToDatabase;
    //
    final private Path examRootPath;
    final private Path absoluteExamRootPath;
    final private Path normalizedAbsoluteExamRootPath;
    final private Path testResultDataBaseGuard_Path;
    final private Path testResultDataBase_Path;
    
    
    
    
    
    /**
     * This method resets the current test result table.
     *
     */
    synchronized public void reset(){
        currentTestResults.clear();
    }//method()
    
    
    
    /**
     * This method enters the current test result locally into the current test result table.
     * Later on ( @AfterClass -> runTestTearDownAfterAllUnitTestsHaveFinished() of related test frame ) it is entered into the data base.
     *
     * @param testTopic         the very current test topic
     * @param testResult        the current test result
     */
    synchronized public void enterLocally(
        final TestTopic  testTopic,                                             // the very current test topic
        final TestResult  testResult                                            // the current test result
    ){
        // check for parameter consistency/meaningfulness
        final TL testLevel = testTopic.getLevel();
        final TC testCategory = testTopic.getCategory();
        final String testName = testResult.getTestMethodName();
        final int testWeight = testResult.getWeight();
        //
        /*
        Just in case zero points are supported for TC.DBGPRINT related tests, accept them ;-)
        Currently the thinking is that student gets 1 point if he has NOT destroyed the TC.DBGPRINT related test
        --------------------------------------------------------------------------------------------------------
        if(     (( 0 >= testWeight ) && ( TC.DBGPRINT != testCategory ))
            ||  (  0 >  testWeight )
        ){
        */
        if( 0 >= testWeight ){
            throw new IllegalArgumentException( String.format( 
                "SETUP ERROR: invalid test weight -> method:%s, weight:%d, category:%s\n",
                testName,
                testWeight,
                testCategory
            ));
        }//if
        //\=> parameter are valid respectively NOT null
        //
        // check for consistency:  test name <-> test level
        switch( testLevel ){
            case I:
                {//scope
                    final Pattern pattern = Pattern.compile( "^tol_0i_[a-z].*$" );
                    final Matcher matcher = pattern.matcher( testName );
                    if( ! matcher.matches() ){
                        Herald.proclaimMessage( SYS_ERR,  String.format( "[%s]:  tol_0i_ <-> %s",  testLevel, testName ));
                        throw new IllegalStateException( "Internal coding error - did not expect to end up \"here\" - call Michael Schaefers" );
                    }//if
                }//scope
                break;
            case A:
                {//scope
                    final Pattern pattern = Pattern.compile( "^tol_1e_[a-z].*$" );
                    final Matcher matcher = pattern.matcher( testName );
                    if( ! matcher.matches() ){
                        Herald.proclaimMessage( SYS_ERR,  String.format( "[%s]:  tol_1e_ <-> %s",  testLevel, testName ));
                        throw new IllegalStateException( "Internal coding error - did not expect to end up \"here\" - call Michael Schaefers" );
                    }//if
                }//scope
                break;
            case B:
                {//scope
                    final Pattern pattern = Pattern.compile( "^tol_2b_[a-z].*$" );
                    final Matcher matcher = pattern.matcher( testName );
                    if( ! matcher.matches() ){
                        Herald.proclaimMessage( SYS_ERR,  String.format( "[%s]:  tol_2b_ <-> %s",  testLevel, testName ));
                        throw new IllegalStateException( "Internal coding error - did not expect to end up \"here\" - call Michael Schaefers" );
                    }//if
                }//scope
                break;
            case C:
                {//scope
                    final Pattern pattern = Pattern.compile( "^tol_3n_[a-z].*$" );
                    final Matcher matcher = pattern.matcher( testName );
                    if( ! matcher.matches() ){
                        Herald.proclaimMessage( SYS_ERR,  String.format( "[%s]:  tol_3n_ <-> %s",  testLevel, testName ));
                        throw new IllegalStateException( "Internal coding error - did not expect to end up \"here\" - call Michael Schaefers" );
                    }//if
                }//scope
                break;
            case D:
                {//scope
                    final Pattern pattern = Pattern.compile( "^tol_4s_[a-z].*$" );
                    final Matcher matcher = pattern.matcher( testName );
                    if( ! matcher.matches() ){
                        Herald.proclaimMessage( SYS_ERR, String.format( "[%s]:  tol_4s_ <-> %s",  testLevel, testName ));
                        throw new IllegalStateException( "Internal coding error - did not expect to end up \"here\" - call Michael Schaefers" );
                    }//if
                }//scope
                break;
            default:
                {//scope
                    Herald.proclaimMessage( SYS_ERR,  String.format( "[%s]:  Did not expect to end up \"here\": %s",  testLevel, testName ));
                    if( true )  throw new IllegalStateException( "Internal coding error - call Michael Schaefers" );
                }//scope
                break;
        }//switch
        //\=> parameter are consistent respectively meaningful
        
        // start of action
        currentTestResults.enterTestResult( testTopic, testResult );
    }//method()
    
    
    
    
    
    /**
     * This method (removes first the old/obsolete test results and afterwards)
     * inserts the current test result(s) into the test result data base.
     * The test results have to be related to the current exercise.
     * This is checked.
     */
    synchronized public void updateSynchronizedExerciseRelatedResults(){
        // initial check if "situation" is as expected resp. current test results contain only results related to exercise under test
        for( TestTopic testTopic : currentTestResults.keySet() ){
            final TE testExercise = testTopic.getExercise();
            if( testExercise != theExercise ){
                final Set<TestResult> testResults = currentTestResults.get( testTopic );
                for( final TestResult testResult : testResults ){               // first one will throw exception (but it's a set, so what? ;-)
                    final String testName = testResult.getTestMethodName();
                    throw new IllegalStateException( String.format(
                        "Uuupps : unexpected internal situation - this might indicate an internal coding error => contact schaefers -> result is NOT expected inside test result table -> method:%s  exercise under test:%s <-> %s:exercise related to found result",
                        testName,
                        theExercise,
                        testExercise
                    ));
                }//for
            }//if
        }//for
        //\=> current test result contains only results related to exercise under test
        
        
        //"get theTable from data base"
        try(
            final RandomAccessFile testResultDataBaseGuard = new RandomAccessFile( localPathToDbGuard.toString(), "rw" );
            final FileChannel channel = testResultDataBaseGuard.getChannel();
            final FileLock fileLock = channel.lock();                           // <<<<==== GUARD
        ){  //##################################################################
            //####  GUARDED - GUARDED - GUARDED - GUARDED - GUARDED - GUARDED
            //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
            
            final boolean requestedDataBaseExists = Files.exists( testResultDataBase_Path, new LinkOption[]{ LinkOption.NOFOLLOW_LINKS } );
            TestResultTable allTestResults;
            if( requestedDataBaseExists ){
                //\=> data base exists ;-)
                
                final TestResultTable oldStoredTable = iLoadTestResultTableFromDataBaseFile( fileLock );
                oldStoredTable.removeExcercise( theExercise );                  // remove exercise related entries from old stored table
                if( null != currentTestResults ){
                    currentTestResults.integrateTestResults( oldStoredTable );
                    allTestResults = currentTestResults;
                }else{
                    allTestResults = oldStoredTable;
                }//if
            }else{
                allTestResults = currentTestResults;
            }//if
            if( null != allTestResults ){
                iStoreTestResultTableIntoDataBaseFile( fileLock, allTestResults );
            }else{
                Herald.proclaimMessage( SYS_OUT,  "note: NO results at all\n" );
            }//if
            
            //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            //####  GUARDED - GUARDED - GUARDED - GUARDED - GUARDED - GUARDED
            //##################################################################
        }catch( final IOException ex ){                                         // FileNotFoundException | IOException
            ex.printStackTrace();
            throw new IllegalStateException( String.format( "this should NOT have happened -> \"%s\"\n",  ex.getMessage() ));
        }//try
    }//method()
    
    
    
    /**
     * This method inserts the current test result(s) into the test result data base.
     * The test results have to be related to the current exercise.
     * This is checked.
     */
    synchronized public TestResultTable loadSynchronizedExerciseRelatedResults(){
        TestResultTable theTable = null;
        
        try(
            final RandomAccessFile testResultDataBaseGuard = new RandomAccessFile( localPathToDbGuard.toString(), "rw" );
            final FileChannel channel = testResultDataBaseGuard.getChannel();
            final FileLock fileLock = channel.lock();                           // <<<<==== GUARD
        ){  //##################################################################
            //####  GUARDED - GUARDED - GUARDED - GUARDED - GUARDED - GUARDED
            //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
            
            final boolean requestedDataBaseExists = Files.exists( testResultDataBase_Path, new LinkOption[]{ LinkOption.NOFOLLOW_LINKS } );
            if( requestedDataBaseExists ){
                theTable = iLoadTestResultTableFromDataBaseFile( fileLock );
            }//if
            
            //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            //####  GUARDED - GUARDED - GUARDED - GUARDED - GUARDED - GUARDED
            //##################################################################
        }catch( final IOException ex ){                                         // FileNotFoundException | IOException
            ex.printStackTrace();
            throw new IllegalStateException( String.format( "this should NOT have happened -> \"%s\"\n",  ex.getMessage() ));
        }//try
        
        return theTable;
    }//method()
    
    
    
    private TestResultTable iLoadTestResultTableFromDataBaseFile( final FileLock fileLock ){
        TestResultTable readTestResultTable = null;
        try(
            final ObjectInputStream ois = new ObjectInputStream( Files.newInputStream( testResultDataBase_Path ));
        ){
            readTestResultTable = (TestResultTable)( ois.readObject() );        // the "old" table currently stored in data base
        }catch( final ClassNotFoundException|IOException ex ){                  // ClassNotFoundException | FileNotFoundException | IOException
            ex.printStackTrace();
            throw new IllegalStateException( String.format( "this should NOT have happened -> \"%s\"", ex.getMessage() ));
        }//try                                                                  // result of WorkAround for BlueJ BUG - AutoClosable is NOT supported
        return readTestResultTable;
    }//method()
    
    private void iStoreTestResultTableIntoDataBaseFile( final FileLock fileLock,  final TestResultTable theTable ){
        try(
            final ObjectOutputStream oos = new ObjectOutputStream( Files.newOutputStream( testResultDataBase_Path ));
        ){
            oos.writeObject( theTable );
        }catch( final IOException ex ){                                         // FileNotFoundException | IOException
            ex.printStackTrace();
            throw new IllegalStateException( String.format( "this should NOT have happened -> \"%s\"",  ex.getMessage() ));
        }//try 
    }//method()
    
     
    
    
    
    /**
     * Constructs test result database manager
     *
     * @param currentExercise  the current exercise
     * @param postfixOfTestResultDataBasePath  (equals) root package name of examinee code
     */
    public TestResultDataBaseManager(                                           // package scope on purpose
        final TE currentExercise,                                               // the current exercise
        final String postfixOfTestResultDataBasePath                            // (equals) root package name of examinee code
    ){
        // check if result database folder is already existent or can be generated
        if( ! guaranteeDataBaseExistence() ){
            throw new IllegalStateException( "can NOT generate test result database" );
        }//if
        //\=> result database folder is existent
        //
        localPathToDbGuard  = mainPartOfTestResultDataBasePath +File.separator+ mainPartOfTestResultDataBaseFileName+"__"+postfixOfTestResultDataBasePath+".guard";
        localPathToDatabase = mainPartOfTestResultDataBasePath +File.separator+ mainPartOfTestResultDataBaseFileName+"__"+postfixOfTestResultDataBasePath+".data";
        //
        examRootPath = Paths.get( "." );
        examRootPath.toAbsolutePath();
        absoluteExamRootPath = examRootPath.toAbsolutePath();
        normalizedAbsoluteExamRootPath = absoluteExamRootPath.normalize();
        testResultDataBaseGuard_Path = Paths.get( normalizedAbsoluteExamRootPath.toString(), localPathToDbGuard );          // TODO - recheck __???__<170911>
        testResultDataBase_Path      = Paths.get( normalizedAbsoluteExamRootPath.toString(), localPathToDatabase );         // TODO - recheck __???__<170911>
        theExercise = currentExercise;
        currentTestResults = TestResultTable.createEmptyTable();
        //
        // consistency check: is localPath* part of testResultDataBase*Path ?
        if( ! (
                testResultDataBaseGuard_Path.toString().endsWith( localPathToDbGuard )
             && testResultDataBase_Path.toString().endsWith( localPathToDatabase )
        )){
            final String errorMessage =  String.format(
                "this should NOT have happened ( @%s )-> \n"
              + "\"%s\"<->\"%s\"\n"
              + "\"%s\"<->\"%s\"\n",
                TestResultDataBaseManager.class.getSimpleName(),
                testResultDataBaseGuard_Path.toString(),
                localPathToDbGuard,
                testResultDataBase_Path.toString(),
                localPathToDatabase
            );
            Herald.proclaimMessage( SYS_ERR, errorMessage );
            throw new IllegalStateException( errorMessage );
            //TODO:
            //  Wo bleibt die Reaktion auf die Exception  / wo wird sie verschluckt ???
            //  UND macht das ueberhaupt Sinn - was sollte denn hier anbrennen?
        }//if
        //\=> consistency is given
    }//constructor()
    //
    private boolean guaranteeDataBaseExistence(){
        try{
            // check if requested directory exists - otherwise generate it
            final File resultDB = new File( mainPartOfTestResultDataBasePath );
            if( resultDB.exists() ){
                if( ! resultDB.isDirectory() ){
                    throw new TestSupportException( "name for result database folder \"occupied\" by some file" );
                }//if
            }else{
                final boolean directoryGenerationProblem = !resultDB.mkdir();
                if( directoryGenerationProblem ){
                    throw new TestSupportException( "can NOT generate test result database folder" );
                }//if
            }//if
            //=> directory is existent
            
        }catch( final TestSupportException ex ){
            ex.printStackTrace();
            return false;
        }//try
        return true;
    }//method()
    
}//class
