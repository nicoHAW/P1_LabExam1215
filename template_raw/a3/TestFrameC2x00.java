package template_raw.a3;


import static _untouchable_.supportC2x00.Configuration.dbgConfigurationVector;
import static _untouchable_.supportC2x00.Herald.Medium.SYS_ERR;
import static _untouchable_.supportC2x00.PointDefinition.countsOnePoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
//
import java.math.BigInteger;
import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;

import _untouchable_.supportC2x00.Herald;
import _untouchable_.supportC2x00.RefTypeInfoContainer;
import _untouchable_.supportC2x00.TC;
import _untouchable_.supportC2x00.TE;
import _untouchable_.supportC2x00.TL;
import _untouchable_.supportC2x00.TS;
import _untouchable_.supportC2x00.TestResult;
import _untouchable_.supportC2x00.TestResultDataBaseManager;
import _untouchable_.supportC2x00.TestSupportException;
import _untouchable_.supportC2x00.TestTopic;


/**
 * LabExam1215_4XIB1-P1    (PTP-Eclipse)<br />
 * <br />
 * Diese Sammlung von Tests soll nur die Sicherheit vermitteln, dass Sie die Aufgabe richtig verstanden haben.
 * Dass von den Tests dieser Testsammlung keine Fehler gefunden wurden, kann nicht als Beweis dienen, dass der Code fehlerfrei ist.
 * Es liegt in Ihrer Verantwortung sicherzustellen, dass Sie fehlerfreien Code geschrieben haben.
 * Bei der Bewertung werden u.U. andere - konkret : modifizierte und haertere Tests - verwendet.
 *<br />
 *<br />
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1215_4XIB1-P1_211v10_210705_v01
 */
@TestMethodOrder( MethodOrderer.MethodName.class )
public class TestFrameC2x00 {
    
    // constant(s)
    
    // limit for test time
    final static private int commonDefaultLimit = 4_000;                               // timeout resp. max. number of ms for test - attention unit is defined in @Timeout
    
    // exercise related
    final static private TE exercise = TE.A3;                                   // <<<<===  _HERE_
    final static private String packagePath = getPackagePath();
    final static private String rootPackageName = getRootPackageName();
    
    // automatic evaluation or more detailed access to debugManager (as result of BlueJ BUG workaround)
    final static private boolean enableAutomaticEvaluation  =  ( 0 > dbgConfigurationVector );
    
  //final static private boolean enableDbgOutputAfterEachTest  =  ( 0 != ( dbgConfigurationVector & 0x0000_8000 ));    
    
    
    // variable(s) - since the methods are static, the following variables have to be static
    
    // TestFrame "state"
    static private TestResultDataBaseManager  dbManager;
    
    
    
    //--------------------------------------------------------------------------
    //
    // support methods for setup computation of final static stuff
    //
    
    private static String getPackagePath(){
        return new Object(){}.getClass().getPackage().getName();
    }//method()
    
    private static String getRootPackageName(){
        final String packagePath = getPackagePath();
        final String[] packagePathPart = packagePath.split( "\\." );
        if( packagePathPart.length != 2 ){
            Herald.proclaimMessage( SYS_ERR,  String.format(
                "Uuupps : This should NOT have happened\n"
              + "    Path: \"%s\" is NOT matching expectations\n"
              + "\n"
              + "This might be an CRITICAL ERROR resulting in unpredictable behavior\n",
                packagePath
            ));
        }//if
        if( packagePathPart.length >= 2 ){
            return packagePathPart[0];
        }else{
            return packagePath;
        }//if
    }//method
    
    private static String getPathOfBinFolderViaTF(){                            // TF ::= TestFrame-class
        return TestFrameC2x00.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }//method()
    
    private static String getPathOfBinFolderViaTS(){                            // TS ::= TestSupport- resp. TS-class
        return TS.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }//method()
    
    
    
    
    
    
    
    
    
    
    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * @BeforeAll
     * ============
     */
    @BeforeAll
    public static void runSetupBeforeAnyUnitTestStarts(){
        guaranteeExerciseConsistency( exercise.toString().toLowerCase(), packagePath );
        if( enableAutomaticEvaluation ){
            dbManager = new TestResultDataBaseManager( exercise, rootPackageName );
            TS.runTestSetupBeforeAnyUnitTestStarts( dbManager, exercise, rootPackageName );
        }//if
    }//method()
    //
    private static void guaranteeExerciseConsistency(
        final String  stringAsResultOfEnum,
        final String  stringAsResultOfPath
    ){
        // Macht das wirklich Sinn? - Koennte hier wirklich jemals was anbrennen ???
        // 1.) Sind Class-Dateien fuer Examinee-Code und _untouchable_ im gleichen bin-Folder?
        final String pathOfBinFolderViaTF = getPathOfBinFolderViaTF();
        final String pathOfBinFolderViaTS = getPathOfBinFolderViaTS();
        if( ! pathOfBinFolderViaTF.equals( pathOfBinFolderViaTS )){
            Herald.proclaimMessage( SYS_ERR,  String.format(
                "Uuupps : This should NOT have happened\n"
              + "    This might indicate coming problems\n"
              + "    Have you already done \"%s\" in a proper way?\n"
              + "\n"
              + "CRITICAL ERROR :  (TS) %s <-> %s (TF)\n",
                exercise,
                pathOfBinFolderViaTS,
                pathOfBinFolderViaTF
            ));
        }//if
        //\=> _untouchable_ und Examinee-Code liegen im gleichen bin-Folder
        
        // 2.) Entspricht Folder mit Examinee-Source-Code den Erwartungen?
        if( ! stringAsResultOfPath.matches( "^[a-z]+" + "_" + "[a-z]+" + "\\." + stringAsResultOfEnum + "$" )) {
            Herald.proclaimMessage( SYS_ERR,  String.format(
                "Uuupps : This should NOT have happened\n"
              + "    Path: \"%s\" is NOT matching expectations\n"
              + "    Have you done \"%s\" in a proper way?\n"
              + "\n"
              + "CRITICAL ERROR :  %s <-> %s\n",
                stringAsResultOfPath,
                exercise,
                stringAsResultOfEnum,
                stringAsResultOfPath
            ));
            throw new IllegalStateException( String.format(
                "Uuupps : this should NOT have happened - path to your solution is NOT matching expectations => CRITICAL ERROR :  %s : ???-->> %s <<--???",
                stringAsResultOfEnum,
                stringAsResultOfPath
            ));
        }//if
        //\=> Folder mit Examinee-Source-Code hat Aufbau: xxx_xxx.a1
    }//method()
    
    
    
    /*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * @AfterAll
     * ===========
     */
    @AfterAll
    public static void runTearDownAfterAllUnitTestsHaveFinished(){
        if( enableAutomaticEvaluation ){
            TS.runTestTearDownAfterAllUnitTestsHaveFinished( dbManager, rootPackageName );          // <<<<==== the actual job is done here
            dbManager.reset();                                                                      //
        }//if
    }//method()
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //##########################################################################
    //##########################################################################
    //###
    //###
    //###   START OF THE VERY TESTS
    //###   =======================
    //###
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   local test support
    //###
    
    private void setUpArrayForPalindromeTest(
        final String[] testNumber,
        final int numberOfDigits,
        final char digitA,
        final char digitB
    ){
        if(( null==testNumber )  ||  ( numberOfDigits > testNumber.length ))  throw new IllegalArgumentException();
        if(( digitA < '0' )  ||  ( '9' < digitA ))  throw new IllegalArgumentException();
        if(( digitB < '0' )  ||  ( '9' < digitB ))  throw new IllegalArgumentException();
        if( digitA == digitB )                      throw new IllegalArgumentException();
        
        final char[] protoNumber = new char[ numberOfDigits ];                  // array containing digits
        final boolean middleExists = ( 0b1 == ( numberOfDigits & 0b1 ));        // numberOfDigits is odd
        final int middle = numberOfDigits >> 1;                                 // index/position in the middle
        //
        for( int indx=0; indx<numberOfDigits; indx++ ){                         // protoNumber array index running
            for( int pos=0; pos<numberOfDigits; pos++ ){                        // char array index/position running
                if( indx!=pos ){                                                // the n-th entry in protoNumber array has digitB at position n
                    protoNumber[pos] = digitA;
                }else{
                    protoNumber[pos] = digitB;
                }//if
            }//for
            //
            // construct testNumber in a way that does NOT help the students ;-)
            String rawNumber = "";
            int i;                                                              // char array read index helping to construct number
            for( i=0; i<numberOfDigits-1; i++ ){
                rawNumber = rawNumber + protoNumber[i];
            }//for
            if( middleExists && ( indx == middle )){
                //\=> this will result in palindromic number => destroy it
                //
                rawNumber = rawNumber + '0';
            }else{
                rawNumber = rawNumber + protoNumber[i];
            }//if
            testNumber[indx] = rawNumber;
        }//for  
    }//method()
    
    
    
    private void subTestBehavior_isPalindromicNumber(
        final String testParameterAsString,                                     // test paramter - number that shall be checked if it is a palindromic number
        final boolean expectedResult,                                           // expected result of palindrom-test
        final int points,                                                       // points in case of success
        final TL testLevel,                                                     // test level
        final String testName                                                   // name of test method that is responsible fot the test
    ){
        final String requestedRefTypeName = "NumberPalindromeChecker";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final BigInteger testParameter = new BigInteger( testParameterAsString );
                        final NumberPalindromeChecker_I numberPalindromeChecker = (NumberPalindromeChecker_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        final boolean reportedResult = numberPalindromeChecker.isNumberPalindrome( testParameter );
                        assertEquals( expectedResult, reportedResult, String.format( " unexpected result for %s", testParameterAsString ));
                    }catch( final TestSupportException ex ){
                        fail( String.format( "\"%s\" -> %s",  testParameterAsString, ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( testLevel, exercise, TC.BEHAVIOR ),  new TestResult( testName, points ) );
        }//if
    }//method()
    
    
    
    private void subTestBehaviorWithGuard_isPalindromicNumber(
        final String[] testParameterAsString,                                   // test paramter - number that shall be checked if it is a palindromic number
        final boolean expectedResultForAll,                                     // expected result of palindrom-test
        final int points,                                                       // points in case of success
        final TL testLevel,                                                     // test level
        final String testName                                                   // name of test method that is responsible fot the test
    ){
        final String requestedRefTypeName = "NumberPalindromeChecker";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    BigInteger testValue = new BigInteger( "-1" );              // to keep compiler happy - "-1" would mark internal BUG, since variable is assigned before read
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeWithPath );
                    //
                    // start of actual test
                    try{
                        final NumberPalindromeChecker_I numberPalindromeChecker = (NumberPalindromeChecker_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        for( final String currentTestValueAsString : testParameterAsString ){
                            final BigInteger currentTestValue = new BigInteger( currentTestValueAsString );
                            testValue = currentTestValue;
                            final boolean reportedResult = numberPalindromeChecker.isNumberPalindrome( currentTestValue );
                            assertEquals( expectedResultForAll, reportedResult );
                        }//for
                    }catch( final TestSupportException ex ){
                        fail( String.format( "\"%s\" -> %s",  testValue.toString(), ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( testLevel, exercise, TC.BEHAVIOR ),  new TestResult( testName, points ) );
        }//if
    }//method()
    
    
    
    private void doGuardTest_general( final String requestedRefTypeWithPath ){
        // test uses multiple Objects of NumberPalindromeChecker on purpose
        //
        BigInteger testValue = new BigInteger( "-1" );                      // "-1" marks internal BUG - variable is assigned before read
        try {
            // prepare guard test
            String[] guardTestValueAsString;
            BigInteger[] guardTestValue;
            boolean expectedResult;
            
            // do guard test #1 positive
            guardTestValueAsString = new String[]{ "111", "222", "333", "444", "555", "666", "777", "888", "999" };     // most simple palindromic number
            guardTestValue = new BigInteger[guardTestValueAsString.length];
            for( int i=0; i<guardTestValueAsString.length; i++ ){
                guardTestValue[i] = new BigInteger( guardTestValueAsString[i] );
            }//for
            expectedResult = true;
            for( final BigInteger localCurrentGuardTestValue : guardTestValue){
                final BigInteger currentGuardTestValue = localCurrentGuardTestValue;
                testValue = currentGuardTestValue;
                final NumberPalindromeChecker_I numberPalindromeChecker = (NumberPalindromeChecker_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                final boolean reportedResult = numberPalindromeChecker.isNumberPalindrome( localCurrentGuardTestValue );
                assertEquals( expectedResult, reportedResult, String.format( "Guard test failed" ));
            }//for
            
            // do guard test #2 negative
            guardTestValueAsString = new String[]{ "789", "876", "567", "654", "345", "432", "123", "219", "891" };     // most simple non palindromic number
            guardTestValue = new BigInteger[guardTestValueAsString.length];
            for( int i=0; i<guardTestValueAsString.length; i++ ){
                guardTestValue[i] = new BigInteger( guardTestValueAsString[i] );
            }//for
            expectedResult = false;
            for( final BigInteger localCurrentGuardTestValue : guardTestValue){
                final BigInteger currentGuardTestValue = localCurrentGuardTestValue;
                testValue = currentGuardTestValue;
                final NumberPalindromeChecker_I numberPalindromeChecker = (NumberPalindromeChecker_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                final boolean reportedResult = numberPalindromeChecker.isNumberPalindrome( localCurrentGuardTestValue );
                assertEquals( expectedResult, reportedResult, String.format( "Guard test failed" ));
            }//for
            
            
            // once again with single object
            final NumberPalindromeChecker_I numberPalindromeChecker = (NumberPalindromeChecker_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            //
            // do guard test #1 positive
            guardTestValueAsString = new String[]{ "111", "222", "333", "444", "555", "666", "777", "888", "999" };     // most simple palindromic number
            guardTestValue = new BigInteger[guardTestValueAsString.length];
            for( int i=0; i<guardTestValueAsString.length; i++ ){
                guardTestValue[i] = new BigInteger( guardTestValueAsString[i] );
            }//for
            expectedResult = true;
            for( final BigInteger localCurrentGuardTestValue : guardTestValue){
                final BigInteger currentGuardTestValue = localCurrentGuardTestValue;
                testValue = currentGuardTestValue;
                final boolean reportedResult = numberPalindromeChecker.isNumberPalindrome( localCurrentGuardTestValue );
                assertEquals( expectedResult, reportedResult, "Guard test failed" );
            }//for
            //
            // do guard test #2 negative
            guardTestValueAsString = new String[]{ "789", "876", "567", "654", "345", "432", "123", "219", "891" };     // most simple non palindromic number
            guardTestValue = new BigInteger[guardTestValueAsString.length];
            for( int i=0; i<guardTestValueAsString.length; i++ ){
                guardTestValue[i] = new BigInteger( guardTestValueAsString[i] );
            }//for
            expectedResult = false;
            for( final BigInteger localCurrentGuardTestValue : guardTestValue){
                final BigInteger currentGuardTestValue = localCurrentGuardTestValue;
                testValue = currentGuardTestValue;
                final boolean reportedResult = numberPalindromeChecker.isNumberPalindrome( localCurrentGuardTestValue );
                assertEquals( expectedResult, reportedResult, "Guard test failed" );
            }//for
            
        // still here / this execution point reached => guard tests have been successfully passed  ->  just leave method
        }catch( final TestSupportException ex ){
            fail( String.format( "\"%s\" -> %s",  testValue.toString(), ex.getMessage() ));
        }//try
    }//method()
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   A / "1e"
    //###
    
    /** Ausgabe auf Bildschirm zur visuellen Kontrolle (fuer Studenten idR. abgeschaltet => 0 Punkte) */
    @Test
    public void tol_1e_printSupportForManualReview(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    final boolean dbgLocalOutputEnable = ( 0 != ( dbgConfigurationVector & 0x0200 ));
                    if( dbgLocalOutputEnable ){
                        System.out.printf( "\n\n" );
                        System.out.printf( "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv\n" );
                        System.out.printf( "%s():\n",  testName );
                        System.out.printf( "\n\n" );    
                        //
                        final String requestedRefTypeName = "NumberPalindromeChecker";
                        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;        
                        try{
                            TS.printDetailedInfoAboutClass( requestedRefTypeWithPath );
                            System.out.printf( "\n" );
                            final NumberPalindromeChecker_I wp = (NumberPalindromeChecker_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                            TS.printDetailedInfoAboutObject( wp, "NumberPalindromeChecker" );
                            //
                            if( TS.isActualMethod( wp.getClass(), "toString", String.class, null )){
                                System.out.printf( "~.toString(): \"%s\"     again ;-)\n",  wp.toString() );
                            }else{
                                System.out.printf( "NO! toString() implemented by class \"%s\" itself\n",  wp.getClass().getSimpleName() );
                            }//if
                            //
                            System.out.printf( "\n\n" );
                            System.out.printf( "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" );
                            System.out.printf( "\n\n" );
                        }catch( final TestSupportException ex ){
                            ex.printStackTrace();
                            fail( ex.getMessage() );
                        }finally{
                            System.out.flush();
                        }//try
                    }//if
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        // at least the unit test was NOT destroyed by student ;-)
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.DBGPRINT ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Existenz-Test: Gibt es die Klasse ueberhaupt? - "NumberPalindromChecker" */
    @Test
    public void tol_1e_classExistence_NumberPalindromeChecker(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "NumberPalindromeChecker";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
                        // NO crash yet => success ;-)
                    }catch( final ClassNotFoundException ex ){
                        fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.EXISTENCE ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: Eigenschaften des Referenz-Typs NumberPalindromeChecker incl. Constructor */
    @Test
    public void tol_1e_properties_NumberPalindromeChecker(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "NumberPalindromeChecker";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
                        assertTrue( TS.isClass(             classUnderTest ),                                String.format( "requested class %s missing", requestedRefTypeName ));
                        assertTrue( TS.isClassPublic(       classUnderTest ),                                "false class access modifier" );
                        assertTrue( TS.isImplementing(      classUnderTest,   "NumberPalindromeChecker_I" ), "requested supertype missing" );
                        assertTrue( TS.isConstructor(       classUnderTest ),                                "requested constructor missing");
                        assertTrue( TS.isConstructorPublic( classUnderTest ),                                "false constructor access modifier");
                    }catch( final ClassNotFoundException ex ){
                        fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "NumberPalindromeChecker" - Access Modifier Methoden */
    @Test
    public void tol_1e_propertiesMethods_NumberPalindromeChecker(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "NumberPalindromeChecker";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
                        assertTrue( TS.isFunction(       classUnderTest, "isNumberPalindrome", new Class<?>[]{ BigInteger.class }, boolean.class ), "requested method missing" );
                        //
                        assertTrue( TS.isFunctionPublic( classUnderTest, "isNumberPalindrome", new Class<?>[]{ BigInteger.class }, boolean.class ), "false method access modifier" ); // -D interface ;-)
                    }catch( final ClassNotFoundException ex ){
                        fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "NumberPalindromeChecker" - Access Modifier Variablen */
    @Test
    public void tol_1e_propertiesFields_NumberPalindromeChecker(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "NumberPalindromeChecker";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
                        assertTrue( TS.allVariableFieldAccessModifiersPrivate( classUnderTest ), "false field access modifier" );
                    }catch( final ClassNotFoundException ex ){
                        fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "NumberPalindromeChecker" - Schreibweise Methoden */
    @Test
    public void tol_1e_notationMethods_NumberPalindromeChecker(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "NumberPalindromeChecker";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
                        final String faultExample = TS.hasInvalidMethodNotation( classUnderTest );
                        if( null != faultExample ){
                            fail( String.format( "method name: \"%s\" does NOT follow the VERY JAVA LAW !",  faultExample ));
                        }//if
                    }catch( final ClassNotFoundException ex ){
                        fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "NumberPalindromeChecker" - Schreibweise Variablen */
    @Test
    public void tol_1e_notationFields_NumberPalindromeChecker(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "NumberPalindromeChecker";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
                        final String faultExample = TS.hasInvalidFieldNotation( classUnderTest );
                        if( null != faultExample ){
                            fail( String.format( "field name: \"%s\" does NOT follow the VERY JAVA LAW !",  faultExample ));
                        }//if
                    }catch( final ClassNotFoundException ex ){
                        fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: Kann ueberhaupt ein Objekt erzeugt werden? - "NumberPalindromeChecker erzeugen"(ohne Parameter) */
    @Test
    public void tol_1e_behavior_process(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "NumberPalindromeChecker";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final NumberPalindromeChecker_I numberPalindromeChecker = (NumberPalindromeChecker_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        // NO crash yet => success ;-)
                    }catch( final TestSupportException ex ){
                        fail( ex.getMessage() );
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: (NUR-)Methoden-Aufruf "isNumberPalindrome()" */
    @Test
    public void tol_1e_behavior_itemsProcessed(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "NumberPalindromeChecker";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final NumberPalindromeChecker_I numberPalindromeChecker = (NumberPalindromeChecker_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        numberPalindromeChecker.isNumberPalindrome( new BigInteger( "485" ));
                        // NO crash yet => success ;-)
                    }catch( final TestSupportException ex ){
                        fail( ex.getMessage() );
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   B / "2b"
    //###
    
    /** Testing: isNumberPalindrome() */
    @Test
    public void tol_2b_behavior_isNumberPalindrome_testWith3DigitsPositive_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        subTestBehavior_isPalindromicNumber( "888", true,  1, TL.B, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() */
    @Test
    public void tol_2b_behavior_isNumberPalindrome_testWith3DigitsNegative_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        subTestBehavior_isPalindromicNumber( "345", false,  1, TL.B,  testName );
    }//method()
    
    /** Testing: isNumberPalindrome() */
    @Test
    public void tol_2b_behavior_isNumberPalindrome_testWith4DigitsPositive_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        subTestBehavior_isPalindromicNumber( "8888", true,  1, TL.B, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() */
    @Test
    public void tol_2b_behavior_isNumberPalindrome_testWith4DigitsNegative_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        subTestBehavior_isPalindromicNumber( "6789", false,  1, TL.B, testName );
    }//method()
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   C / "3n"
    //###
    //
    // tests mostly taken from LabExam1111_4XIB1-P1_162 a4
    
    
    /** Testing: isNumberPalindrome() 1 digit */
    @Test
    public void tol_3n_behavior_isNumberPalindrome_testWith1DigitPositive_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 2 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith2DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = { "11", "22", "33", "44", "55", "66", "77", "88", "99" };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 2 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith2DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = { "16", "72", "38", "94", "50", "41", "70", "36", "96" };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, false,  1, TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 3 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith3DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = { "161", "272", "383", "494", "505", "616", "727", "838", "949", "141", "474", "707", "363", "696", "929", "252", "585", "818" };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 3 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith3DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "123", "456", "789",
            "963", "630", "307", "741", "418", "185", "852", "529", "296",
            "166", "772", "388", "994", "500",
            "966", "558", "744", "336", "552", "114", "300", "992", "388", "774", "366", "552", "144", "330", "922", "118", "700"  
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, false,  1, TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 4 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith4DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "8008", "1221",
            "1661", "2772", "3883", "4994", "5005", "6116", "7227", "8338", "9449",
            "1441", "4774", "7007", "3663", "6996", "9229", "2552", "5885", "8118"
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 4 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith4DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "3888", "8880",
            "1234", "4567", "7890",
            "9630", "6307", "3074", "7418", "4185", "1852", "8529", "5296", "2963",
            "1166", "7722", "3388", "9944", "5500",
            "9966", "5588", "7744", "3366", "5522", "1144", "3300", "9922", "3388", "7744", "3366", "5522", "1144", "3300", "9922", "1188", "7700",
            "6161", "7272", "3838", "9494", "5050",
            "9696", "5858", "7474", "3636", "5252", "1414", "3030", "9292", "3838", "7474", "3636", "5252", "1414", "3030", "9292", "1818", "7070"
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, false,  1, TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 5 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith5DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "12321",
            "12321", "45654", "78987",
            "97479", "85158", "73837", "61516", "59295", "46964", "34643",
            "25252", "37373", "49494", "51515", "63636", "75757", "87878",
            "11111", "22222", "33333", "44444", "55555", "66666", "77777", "88888", "99999"
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 5 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith5DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "38888", "88880",
            "98765",
            "45566", "77665",
            "78789", "89089", "90101",
            "99996", "99969", "96999", "69999"
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, false,  1, TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 6 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith6DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "123321",
            "777777", "665566", "433334",
            "246642", "369963", "482284", "505505", "631136", "853358", "975579"
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 6 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith6DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "388888", "888880",
            "888882", "888828", "888288", "882888", "828888", "288888"
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, false,  1, TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 7 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith7DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "1234321",
            "2468642", "3692963", "6493946", "8516158",
            "4445444"
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 7 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith7DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "3888888", "8888880",
            "1234567", "8642086", "7418529",
            "5444444", "4544444", "4454444", "4444544", "4444454", "4444445",
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, false,  1, TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 8 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith8DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "12344321",
            "55555555",
            "24688642", "36922963", "64933946", "85166158"
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 8 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith8DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "38888888", "88888880",
            "12345678", "97531975", "63074185",
            "27777777", "72777777", "77277777", "77727777", "77772777", "77777277", "77777727", "77777772"
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, false,  1, TL.C, testName );
    }//method()    
    
    
    /** Testing: isNumberPalindrome() 9 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith9DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "123454321",
            "246808642", "369252963", "469383964", "851636158",
            "555525555"
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 9 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith9DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "388888898", "888988880",
            "123456789", "975319753", "630741852",
            "255555555", "525555555", "552555555", "555255555", "555552555", "555555255", "555555525", "555555552"
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, false,  1, TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 10 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith10DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "1234554321",
            "2468008642", "3692552963", "4693883964", "8516336158"
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 10 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith10DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "3888888898", "8888988880",
            "1234567890", "9753197531", "6307418529",
            "3777777777", "7377777777", "7737777777", "7773777777", "7777377777", "7777737777", "7777773777", "7777777377", "7777777737", "7777777773"
        };
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, false,  1, TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 11 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith11DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "12345654321",
            "46802420864", "69258185296", "12471617421", "85160306158"
        };
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 11 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith11DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final int numberOfDigits = 11;
        final String[] testParameter = new String[numberOfDigits+5];
        setUpArrayForPalindromeTest( testParameter, numberOfDigits, '1', '9' );
        testParameter[numberOfDigits+0] = "38888888888";
        testParameter[numberOfDigits+1] = "88888888880";
        testParameter[numberOfDigits+2] = "12345678901";                        // "+1"
        testParameter[numberOfDigits+3] = "97531975319";                        // "-2"
        testParameter[numberOfDigits+4] = "63074185296";                        // "-3" 
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, false,  1, TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 12 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith12DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "123456654321",                                                     // "+1"
            "468024420864",                                                     // "+2"
            "692581185296",                                                     // "+3"
            "124716617421",                                                     // "+1, +2, +3, ..."
            "851603306158"                                                      // "-3, -4, -5, ..."
        };
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 12 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith12DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final int numberOfDigits = 12;
        final String[] testParameter = new String[numberOfDigits+5];
        setUpArrayForPalindromeTest( testParameter, numberOfDigits, '2', '8' );
        testParameter[numberOfDigits+0] = "388888888888";
        testParameter[numberOfDigits+1] = "888888888880";
        testParameter[numberOfDigits+2] = "123456789012";                       // "+1"
        testParameter[numberOfDigits+3] = "753197531975";                       // "-2"
        testParameter[numberOfDigits+4] = "307418529630";                       // "-3"
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, false,  1, TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 13 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith13DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "1234567654321",                                                    // "+1"
            "6802468642086",                                                    // "+2"
            "9258136318529",                                                    // "+3"
            "1247162617421",                                                    // "+1, +2, +3, ..."
            "8516035306158"                                                     // "-3, -4, -5, ..."
        };
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 13 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith13DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final int numberOfDigits = 13;
        final String[] testParameter = new String[numberOfDigits+5];
        setUpArrayForPalindromeTest( testParameter, numberOfDigits, '3', '7' );
        testParameter[numberOfDigits+0] = "3888888889888";
        testParameter[numberOfDigits+1] = "8888888888980";
        testParameter[numberOfDigits+2] = "1234567890123";                      // "+1"
        testParameter[numberOfDigits+3] = "5319753197531";                      // "-2"
        testParameter[numberOfDigits+4] = "7418529630741";                      // "-3"
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, false,  1, TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 14 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith14DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "12345677654321",                                                   // "+1"
            "80246800864208",                                                   // "+2"
            "25813699631852",                                                   // "+3"
            "12471622617421",                                                   // "+1, +2, +3, ..."
            "85160355306158"                                                    // "-3, -4, -5, ..."
        };
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter, true,  1, TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 14 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith14DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final int numberOfDigits = 14;
        final String[] testParameter = new String[numberOfDigits+5];
        setUpArrayForPalindromeTest( testParameter, numberOfDigits, '3', '7' );
        testParameter[numberOfDigits+0] = "38888888898888";
        testParameter[numberOfDigits+1] = "88888888889880";
        testParameter[numberOfDigits+2] = "12345678901234";                     // "+1"
        testParameter[numberOfDigits+3] = "31975319753197";                     // "-2"
        testParameter[numberOfDigits+4] = "41852963074185";                     // "-3"
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  false,  1,  TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 15 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith15DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "123456787654321",                                                  // "+1"
            "468024686420864",                                                  // "+2 & shift"
            "581470363074185",                                                  // "+3 & shift"
            "124716292617421",                                                  // "+1, +2, +3, ..."
            "851603565306158"                                                   // "-3, -4, -5, ..."
        };
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  true,  1,  TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 15 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith15DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final int numberOfDigits = 15;
        final String[] testParameter = new String[numberOfDigits+5];
        setUpArrayForPalindromeTest( testParameter, numberOfDigits, '3', '7' );
        testParameter[numberOfDigits+0] = "388888888988888";
        testParameter[numberOfDigits+1] = "888888888898880";
        testParameter[numberOfDigits+2] = "123456789012345";                    // "+1"
        testParameter[numberOfDigits+3] = "197531975319753";                    // "-2 & shift"
        testParameter[numberOfDigits+4] = "185296307418529";                    // "-3 & shift"
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  false,  1,  TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 16 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith16DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "1234567887654321",                                                 // "+1"
            "6802468008642086",                                                 // "+2 & shift"
            "8147036996307418",                                                 // "+3 & shift"
            "1247162992617421",                                                 // "+1, +2, +3, ..."
            "8516035665306158"                                                  // "-3, -4, -5, ..."
        };
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  true,  1,  TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 16 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith16DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final int numberOfDigits = 16;
        final String[] testParameter = new String[numberOfDigits+5];
        setUpArrayForPalindromeTest( testParameter, numberOfDigits, '5', '9' );
        testParameter[numberOfDigits+0] = "3888888888988888";
        testParameter[numberOfDigits+1] = "8888888888898880";
        testParameter[numberOfDigits+2] = "1234567890123456";                   // "+1"
        testParameter[numberOfDigits+3] = "9753197531975319";                   // "-2 & shift"
        testParameter[numberOfDigits+4] = "8529630741852963";                   // "-3 & shift"
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  false,  1,  TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 17 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith17DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "12345678987654321",                                                // "+1"
            "80246802420864208",                                                // "+2 & shift"
            "14703692529630741",                                                // "+3 & shift"
            "12471629792617421",                                                // "+1, +2, +3, ..."
            "85160356565306158"                                                 // "-3, -4, -5, ..."
        };
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  true,  1,  TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 17 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith17DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final int numberOfDigits = 17;
        final String[] testParameter = new String[numberOfDigits+5];
        setUpArrayForPalindromeTest( testParameter, numberOfDigits, '3', '2' );
        testParameter[numberOfDigits+0] = "38888888889888888";
        testParameter[numberOfDigits+1] = "88888888888988880";
        testParameter[numberOfDigits+2] = "12345678901234567";                  // "+1"
        testParameter[numberOfDigits+3] = "75319753197531975";                  // "-2 & shift"
        testParameter[numberOfDigits+4] = "52963074185296307";                  // "-3 & shift"
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  false,  1,  TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 18 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith18DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "123456789987654321",                                               // "+1"
            "246802468864208642",                                               // "+2 & shift"
            "470369258852963074",                                               // "+3 & shift"
            "124716297792617421",                                               // "+1, +2, +3, ..."
            "851603565565306158"                                                // "-3, -4, -5, ..."
        };
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  true,  1,  TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 18 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith18DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final int numberOfDigits = 18;
        final String[] testParameter = new String[numberOfDigits+5];
        setUpArrayForPalindromeTest( testParameter, numberOfDigits, '4', '5' );
        testParameter[numberOfDigits+0] = "388888888888888888";
        testParameter[numberOfDigits+1] = "888888888888888880";
        testParameter[numberOfDigits+2] = "123456789012345678";                 // "+1"
        testParameter[numberOfDigits+3] = "531975319753197531";                 // "-2 & shift"
        testParameter[numberOfDigits+4] = "296307418529630741";                 // "-3 & shift"
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  false,  1,  TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 19 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith19DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = {
            "1234567890987654321",                                              // "+1"
            "7913580246420853197",                                              // "+2 & shift"
            "4703692581852963074",                                              // "+3 & shift"
            "1247162974792617421",                                              // "+1, +2, +3, ..."
            "8516035653565306158"                                               // "-3, -4, -5, ..."
        };
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  true,  1,  TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 19 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith19DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final int numberOfDigits = 19;
        final String[] testParameter = new String[numberOfDigits+5];
        setUpArrayForPalindromeTest( testParameter, numberOfDigits, '3', '7' );
        testParameter[numberOfDigits+0] = "3888888888898888888";
        testParameter[numberOfDigits+1] = "8888888888889888880";
        testParameter[numberOfDigits+2] = "1234567890123456789";                // "+1"
        testParameter[numberOfDigits+3] = "5319753197531975319";                // "-2 & shift"
        testParameter[numberOfDigits+4] = "2963074185296307418";                // "-3 & shift"
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  false,  1,  TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 100 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith100DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = new String[] {
            "1234567890123456789012345678901234567890123456789009876543210987654321098765432109876543210987654321",
            "9876543210987654321987654329876543987654987659876996789567894567893456789234567891234567890123456789"
        };
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  true,  1,  TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 100 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith100DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final int numberOfDigits = 100;
        final String[] testParameter = new String[numberOfDigits];
        setUpArrayForPalindromeTest( testParameter, numberOfDigits, '1', '8' );
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  false,  1,  TL.C, testName );
    }//method()
    
    
    /** Testing: isNumberPalindrome() 101 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith101DigitsPositive(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String[] testParameter = new String[] {
            "12345678901234567890123456789012345678901234567890809876543210987654321098765432109876543210987654321",
            "98765432109876543219876543298765439876549876598769896789567894567893456789234567891234567890123456789"
        };
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  true,  1,  TL.C, testName );
    }//method()
    
    /** Testing: isNumberPalindrome() 101 digits */
    @Test
    public void tol_3n_behavior_isPalindromicNumberWith101DigitsNegative(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final int numberOfDigits = 101;
        final String[] testParameter = new String[numberOfDigits];
        setUpArrayForPalindromeTest( testParameter, numberOfDigits, '9', '6' );
        //
        subTestBehaviorWithGuard_isPalindromicNumber( testParameter,  false,  1,  TL.C, testName );
    }//method()
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   D / "4s"
    //###
    
    /** TODOs marking code NOT ready inside Code. */
    @Test
    public void tol_4s_containsSuspiciousTodoMarks(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "NumberPalindromeChecker";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeWithPath );
                    //
                    // start of actual test
                    try{
                        final RefTypeInfoContainer refTypeInfoContainer = new RefTypeInfoContainer(
                            requestedRefTypeName,
                            getPackagePath(),
                            getPathOfBinFolderViaTF()
                        );
                        if( TS.containsSuspiciousToDoMarks( testName, refTypeInfoContainer ) ){
                            fail( "Source code contains TODO marks indicating code is NOT ready" );
                        }//if
                    }catch( final TestSupportException ex ){
                        fail( String.format( "test is NOT executable as result of errors -> %s", ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test: parameter is null */
    @Test
    public void tol_4s_behavior_parameter_null_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "NumberPalindromeChecker";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeWithPath );
                    //
                    // start of actual test
                    try{
                        final NumberPalindromeChecker_I numberPalindromeChecker = (NumberPalindromeChecker_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        boolean expectedExceptionOccured = false;
                        try{
                            final BigInteger testValue = null;
                            final boolean reportedResult = numberPalindromeChecker.isNumberPalindrome( testValue );
                        }catch( final Throwable ex ){
                            TS.examineExceptionForIllegalArgumentCauseAndReact( testName, ex );
                            expectedExceptionOccured = true;
                        }//try
                        //\=> either AssertionError|IllegalArgumentException or NO exception at all
                        assertTrue( expectedExceptionOccured,  "undetected illegal argument -> null accepted" );
                    }catch( final TestSupportException ex ){
                        fail( String.format("%s",  ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Stress-Test: parameter is negative */
    @Test
    public void tol_4s_behavior_parameter_negative_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "NumberPalindromeChecker";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeWithPath );
                    //
                    // start of actual test
                    try{
                        final NumberPalindromeChecker_I numberPalindromeChecker = (NumberPalindromeChecker_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        boolean expectedExceptionOccured = false;
                        final String[]  testValueAsString = { "-1", "-2", "-999", "-1234567890", "-2147483647", "-1267650600228229401496703205344" };
                        for( final String currentTestValueAsString : testValueAsString ){
                            try{
                                final BigInteger currentTestValue = new BigInteger( currentTestValueAsString );
                                final boolean reportedResult = numberPalindromeChecker.isNumberPalindrome( currentTestValue );
                            }catch( final Throwable ex ){
                                TS.examineExceptionForIllegalArgumentCauseAndReact( testName, ex );
                                expectedExceptionOccured = true;
                            }//try
                            //\=> either AssertionError|IllegalArgumentException or NO exception at all
                            assertTrue( expectedExceptionOccured,  String.format( "undetected illegal argument -> %s accepted",  currentTestValueAsString ));
                        }//for
                    }catch( final TestSupportException ex ){
                        fail( String.format("%s",  ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  // end of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
}//class
