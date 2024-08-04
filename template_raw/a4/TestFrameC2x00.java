package template_raw.a4;


import static _untouchable_.supportC2x00.Configuration.dbgConfigurationVector;
import static _untouchable_.supportC2x00.Herald.Medium.SYS_ERR;
import static _untouchable_.supportC2x00.PointDefinition.countsFourPoints;
import static _untouchable_.supportC2x00.PointDefinition.countsOnePoint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
//
import java.math.BigInteger;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;

import _untouchable_.supportC2x00.Herald;
import _untouchable_.supportC2x00.ManualExceptionInvestigationRelatedAddOn;
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
    final static private int commonDefaultLimit = 4_000;                        // timeout resp. max. number of ms for test
    final static private int bigNumberLimit = 10_000;                           // timeout resp. max. number of ms for test
    
    // exercise related
    final static private TE exercise = TE.A4;                                   // <<<<===  _HERE_
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
    //###
    //###   local test support
    //###
    
    final private class TestStringContainer {
        private String tv;                                                      // TestparameterValue
        private String[] ev;                                                    // ExpectedresultValue
        private TestStringContainer( final String tv,  final String... ev ){ this.tv=tv; this.ev=ev; }
    }//class
    
    
    
    private void checkFactorization (
        final String testName,
        final String requestedRefTypeWithPath,
        final String number,
        final String... factors
    ){
        try{
            final PrimeFactorComputer_I pfc = (PrimeFactorComputer_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            final BigInteger testParameter = new BigInteger( number );
            final List<BigInteger> computedResultList = pfc.compute( testParameter );
          //Collections.sort( computedResultList );                             // AUFSTEIGENDE REIHENFOLGE (!!!) war eingefordert
            final String[] expectedResult = factors;
            assertEquals(
                expectedResult.length,
                computedResultList.size(),
                String.format(
                    "Wrong number %d of computed prime factors for %s",
                    computedResultList.size(),
                    number
                )
            );
            for( int i=computedResultList.size(); --i>=0; ){
                final BigInteger computedResultElem = computedResultList.get(i);
                final BigInteger expectedResultElem = new BigInteger( expectedResult[i] );
                assertEquals(
                    expectedResultElem,
                    computedResultElem,
                    String.format(
                        "At least computed prime factor %d was NOT expected at this position %d for %s",
                        computedResultElem,
                        i,
                        number
                    )
                );
            }//for
        }catch( final TestSupportException ex ){                                // there shall be NO exception - hence, handle all exceptions the same way
            fail( ex.getMessage() );
        }//try
    }//method()
    
    
    
    private void doGuardTest_general(
        final String testName,
        final String requestedRefTypeWithPath
    ){
        // 3 simple checks executed on individual PrimeFactorComputer each
        try{
            checkFactorization( "Guard test:" + testName,   requestedRefTypeWithPath,   "51",  "3", "17" );
            checkFactorization( "Guard test:" + testName,   requestedRefTypeWithPath,   "65",  "5", "13" );
            checkFactorization( "Guard test:" + testName,   requestedRefTypeWithPath,   "77",  "7", "11" );
        }catch( final Exception ex ){
            fail( String.format( "[Guard test failed] -->> %s <<--",  ex.getMessage() ));
        }//try
        //\=> NO crash yet => guard test(s) successfully passed ;-)                    
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
    //###   A / "1e"
    //###
    
    /** Ausgabe auf Bildschirm zur visuellen Kontrolle (fuer Studenten idR. abgeschaltet => 0 Punkte) */
    @Test
    public void tol_1e_printSupportForManualReview(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        final boolean dbgLocalOutputEnable = ( 0 != ( dbgConfigurationVector & 0x0200 ));
        if( dbgLocalOutputEnable ){
            System.out.printf( "\n\n" );
            System.out.printf( "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv\n" );
            System.out.printf( "%s():\n",  testName );
            System.out.printf( "\n\n" );    
            //
            final String requestedRefTypeName = "PrimeFactorComputer";
            final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;        
            //
            assertTimeoutPreemptively(
                Duration.ofMillis( commonDefaultLimit ),
                new Executable(){                                                   // Executable is executed in a different thread
                    @Override
                    public void execute() throws Throwable {
                        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                        
                        try{
                            TS.printDetailedInfoAboutClass( requestedRefTypeWithPath );
                            System.out.printf( "\n" );
                            final PrimeFactorComputer_I pfc = (PrimeFactorComputer_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            
                            TS.printDetailedInfoAboutObject( pfc, "PrimeFactorComputer" );
                            //
                            if( TS.isActualMethod( pfc.getClass(), "toString", String.class, null )){
                                System.out.printf( "~.toString(): \"%s\"     again ;-)\n",  pfc.toString() );
                            }else{
                                System.out.printf( "NO! toString() implemented by class \"%s\" itself\n",  pfc.getClass().getSimpleName() );
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
                        
                        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                    }//method()
                },                                                              //END of Executable
                "computation took too long"
            );
        }//if
        
        // at least the unit test was NOT destroyed by student ;-)
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.DBGPRINT ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: Gibt es die Klasse ueberhaupt? - "TextProcessor" */
    @Test
    public void tol_1e_classExistence_PrimeFactorComputer(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
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
            },                                                              //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.EXISTENCE ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: Eigenschaften des Referenz-Typs TextProcessor incl. Constructor */
    @Test
    public void tol_1e_properties_PrimeFactorComputer(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
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
                        assertTrue( TS.isClass(             classUnderTest ),                               String.format( "requested class %s missing", requestedRefTypeName ));
                        assertTrue( TS.isClassPublic(       classUnderTest ),                               "false class access modifier" );
                        assertTrue( TS.isImplementing(      classUnderTest,   "PrimeFactorComputer_I" ),    "requested supertype missing" );
                        assertTrue( TS.isConstructor(       classUnderTest ),                               "requested constructor missing" );
                        assertTrue( TS.isConstructorPublic( classUnderTest ),                               "false constructor access modifier" );
                    }catch( final ClassNotFoundException ex ){
                        fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "PrimeFactorComputer" - Access Modifier Methoden */
    @Test
    public void tol_1e_propertiesMethods_PrimeFactorComputer(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
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
                        assertTrue( TS.isFunction(        classUnderTest, "compute",  new Class<?>[]{ BigInteger.class },  List.class ),  "requested method missing" );
                        //
                        assertTrue( TS.isFunctionPublic(  classUnderTest, "compute",  new Class<?>[]{ BigInteger.class },  List.class ),  "false method access modifier" );
                    }catch( final ClassNotFoundException ex ){
                        fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "PrimeFactorComputer" - Access Modifier Variablen */
    @Test
    public void tol_1e_propertiesFields_TextProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
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
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "PrimeFactorComputer" - Schreibweise Methoden */
    @Test
    public void tol_1e_notationMethods_TextProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
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
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: Eigenschaften "PrimeFactorComputer" - Schreibweise Variablen */
    @Test
    public void tol_1e_notationFields_TextProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
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
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: Kann ueberhaupt ein Objekt erzeugt werden? - "PrimeFactorComputer erzeugen"(ohne Parameter) */
    @Test
    public void tol_1e_objectCreationNP_TextProcessor(){                        // NP ::= No Parameter
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final PrimeFactorComputer_I tp = (PrimeFactorComputer_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        // NO crash yet => success ;-)
                    }catch( final TestSupportException ex ){
                        fail( ex.getMessage() );
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.CREATION ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: (NUR-)Methoden-Aufruf "compute()" */
    @Test
    public void tol_1e_behavior_setText(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final BigInteger anyParameterForMethod = BigInteger.TEN;
                        final PrimeFactorComputer_I pfc = (PrimeFactorComputer_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        pfc.compute( anyParameterForMethod );
                        // NO crash yet => success ;-)
                    }catch( final TestSupportException ex ){
                        fail( ex.getMessage() );
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
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
    
    /** Einfacher Test: (NUR-)Sequenz-Methoden-Aufruf: "alle ;-)" */
    @Test
    public void tol_2b_behavior_sequencePureMethodCall_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final PrimeFactorComputer_I pfc = (PrimeFactorComputer_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));        
                        final BigInteger testParameter1 = new BigInteger( "15" );
                        final BigInteger testParameter2 = new BigInteger( "21" );
                        final BigInteger testParameter3 = new BigInteger( "35" );
                        List<BigInteger> uncheckedResultList;
                        uncheckedResultList = pfc.compute( testParameter1 );
                        uncheckedResultList = pfc.compute( testParameter2 );
                        uncheckedResultList = pfc.compute( testParameter3 );
                        // NO crash yet => success ;-)
                    }catch( final TestSupportException ex ){                                // there shall be NO exception - hence, handle all exceptions the same way
                        fail( ex.getMessage() );
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Funktions-Test: compute() */
    @Test
    public void tol_2b_behavior_simple_computer_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    checkFactorization( testName,  requestedRefTypeWithPath,  "15", "3", "5" );
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Funktions-Test: compute() */
    @Test
    public void tol_2b_behavior_simple_computer_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    checkFactorization( testName,  requestedRefTypeWithPath,  "21", "3", "7" );
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Funktions-Test: compute() */
    @Test
    public void tol_2b_behavior_simple_computer_no3(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    checkFactorization( testName,  requestedRefTypeWithPath,  "35", "5", "7" );
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   C / "3n"
    //###
    
    /** Funktions-Test: parameter acceptance() */
    @Test
    public void tol_3n_behavior_parameter_acceptance_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    checkFactorization( testName,  requestedRefTypeWithPath,  "18446744073709551616", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2" );
                    checkFactorization( testName,  requestedRefTypeWithPath,  "73786976294838206465", "5", "13", "397", "2113", "312709", "4327489" );
                    checkFactorization( testName,  requestedRefTypeWithPath,  "147573952589676412930", "2", "5", "13", "397", "2113", "312709", "4327489" );
                    checkFactorization( testName,  requestedRefTypeWithPath,  "110680464442257309699", "3", "3", "11", "131", "2731", "409891", "7623851" );
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsFourPoints ));
        }//if
    }//method()
    
    
    /** Funktions-Test: compute() */
    @Test
    public void tol_3n_behavior_simple_computer_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    checkFactorization( testName,  requestedRefTypeWithPath,  "4", "2", "2" );
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Funktions-Test: compute() */
    @Test
    public void tol_3n_behavior_compute_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    String[] testCollection = { "2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31", "37", "41", "43", "47", "53", "59", "61", "67", "71", "73", "79", "83", "89", "97" };
                    for( final String currentTestValue : testCollection ){
                        checkFactorization( testName,  requestedRefTypeWithPath,  currentTestValue, currentTestValue );
                    }//for
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Funktions-Test: compute() */
    @Test
    public void tol_3n_behavior_compute_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    final TestStringContainer[] tsc = {
                        new TestStringContainer(   "4",   "2",  "2" ),
                        new TestStringContainer(   "9",   "3",  "3" ),
                        new TestStringContainer(  "16",   "2",  "2",  "2",  "2" ),
                        new TestStringContainer(  "25",   "5",  "5" ),
                        new TestStringContainer(  "36",   "2",  "2",  "3",  "3" ),
                        new TestStringContainer(  "49",   "7",  "7" ),
                        new TestStringContainer(  "64",   "2",  "2",  "2",  "2",  "2",  "2" ),
                        new TestStringContainer(  "81",   "3",  "3",  "3",  "3" ),
                        new TestStringContainer( "100",   "2",  "2",  "5",  "5" ),
                        new TestStringContainer( "121",  "11", "11" )
                    };
                    for( final TestStringContainer testElem : tsc ){
                        checkFactorization( testName,  requestedRefTypeWithPath,  testElem.tv, testElem.ev );
                    }//for
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Funktions-Test: compute() */
    @Test
    public void tol_3n_behavior_compute_no3(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    final TestStringContainer tsc = new TestStringContainer(
                        "539187656870252429091450791100420590516970263172211",
                        "1117", "1117", "1777", "2111", "2221", "2333", "2777", "4943", "5557", "7717", "7727", "7757", "7877", "79997"
                    );
                    checkFactorization( testName,  requestedRefTypeWithPath,  tsc.tv, tsc.ev );
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()    
    
    /** Funktions-Test: compute() */
    @Test
    public void tol_3n_behavior_compute_no4(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    final TestStringContainer tsc = new TestStringContainer(
                        "1645783550795210387735581011435590727981167322669649249414629852197255934130751870910",
                        "2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31", "37", "41", "43", "47", "53", "59", "61", "67", "71", "73", "79", "83", "89", "97", "101", "103", "107", "109", "113", "127", "131", "137", "139", "149", "151", "157", "163", "167", "173", "179", "181", "191", "193", "197", "199", "211"
                    );
                    checkFactorization( testName,  requestedRefTypeWithPath,  tsc.tv, tsc.ev );
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()    
    
    /** Funktions-Test: compute() */
    @Test
    public void tol_3n_behavior_compute_no5(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    final TestStringContainer tsc = new TestStringContainer(
                        "8722143984670823503916867756095874541573374095180050188756414017621240114787956951789661754112982371",
                        "94999", "94999", "98999", "98999", "98999", "99929", "99929", "99929", "99929", "99989", "99989", "99989", "99989", "99989", "99991", "99991", "99991", "99991", "99991", "99991"
                    );
                    checkFactorization( testName,  requestedRefTypeWithPath,  tsc.tv, tsc.ev );
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()    
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   D / "4s"
    //###
    
    /** TODOs inside Code marking code NOT ready. */
    @Test
    public void tol_4s_containsSuspiciousTodoMarks(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeName, requestedRefTypeWithPath );
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
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: compute() - illegal parameter - parameter is null */
    @Test
    public void tol_4s_compute_parameter_null_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( testName, requestedRefTypeWithPath );
                    //
                    // start of actual test
                    try{
                        final PrimeFactorComputer_I pfc = (PrimeFactorComputer_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        boolean expectedExceptionOccured = false;
                        try{
                            final List<BigInteger> computedResultList = pfc.compute( null );
                        }catch( final Throwable ex ){
                            TS.examineExceptionForIllegalArgumentCauseAndReact( testName, ex, ManualExceptionInvestigationRelatedAddOn.ENABLE_PRINT_STACK_TRACE );
                            expectedExceptionOccured = true;
                        }//try
                        //\=> either AssertionError|IllegalArgumentException or NO exception at all
                        assertTrue( expectedExceptionOccured, "undetected illegal argument -> null accepted" );
                    }catch( final TestSupportException ex ){
                        fail( String.format("%s",  ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: compute() - illegal parameter - parameter is negative */
    @Test
    public void tol_4s_compute_parameter_negative_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( testName, requestedRefTypeWithPath );
                    //
                    // start of actual test
                    try{
                        final PrimeFactorComputer_I pfc = (PrimeFactorComputer_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        boolean expectedExceptionOccured = false;
                        final String[] testValueAsString = { "-1", "-2", "-999", "-1234567890", "-2147483647", "-1267650600228229401496703205344" };
                        for( final String currentTestValueAsString : testValueAsString ){
                            try{
                                final BigInteger currentTestValue = new BigInteger( currentTestValueAsString );
                                final List<BigInteger> computedResultList = pfc.compute( currentTestValue );
                            }catch( final Throwable ex ){
                                TS.examineExceptionForIllegalArgumentCauseAndReact( testName, ex, ManualExceptionInvestigationRelatedAddOn.ENABLE_PRINT_STACK_TRACE );
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
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: compute() - illegal parameter - parameter is zero */
    @Test
    public void tol_4s_compute_parameter_zero_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( testName, requestedRefTypeWithPath );
                    //
                    // start of actual test
                    try{
                        final PrimeFactorComputer_I pfc = (PrimeFactorComputer_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        boolean expectedExceptionOccured = false;
                        final BigInteger testParameter = BigInteger.ZERO;
                        try{
                            final List<BigInteger> computedResultList = pfc.compute( testParameter );
                        }catch( final Throwable ex ){
                            TS.examineExceptionForIllegalArgumentCauseAndReact( testName, ex, ManualExceptionInvestigationRelatedAddOn.ENABLE_PRINT_STACK_TRACE );
                            expectedExceptionOccured = true;
                        }//try
                        //\=> either AssertionError|IllegalArgumentException or NO exception at all
                        assertTrue( expectedExceptionOccured, "undetected illegal argument -> zero accepted" );
                    }catch( final TestSupportException ex ){
                        fail( String.format("%s",  ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: compute() - illegal parameter - parameter is one */
    @Test
    public void tol_4s_compute_parameter_one_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( testName, requestedRefTypeWithPath );
                    //
                    // start of actual test
                    try{
                        final PrimeFactorComputer_I pfc = (PrimeFactorComputer_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        boolean expectedExceptionOccured = false;
                        final BigInteger testParameter = BigInteger.ONE;
                        try{
                            final List<BigInteger> computedResultList = pfc.compute( testParameter );
                        }catch( final Throwable ex ){
                            TS.examineExceptionForIllegalArgumentCauseAndReact( testName, ex, ManualExceptionInvestigationRelatedAddOn.ENABLE_PRINT_STACK_TRACE );
                            expectedExceptionOccured = true;
                        }//try
                        //\=> either AssertionError|IllegalArgumentException or NO exception at all
                        assertTrue( expectedExceptionOccured, "undetected illegal argument -> one accepted" );
                    }catch( final TestSupportException ex ){
                        fail( String.format("%s",  ex.getMessage() ));
                    }//try
                    
                    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                }//method()
            },                                                                  //END of Executable
            "computation took too long"
        );
        //
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Testing: compute() - big number */
    @Test
    public void tol_4s_compute_big_number(){            // name tol_4s_performance_compute_no1_maxOneSecondExpected() führt(e) zu BUG ????
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( testName, requestedRefTypeWithPath );
                    //
                    // start of actual test
                    checkFactorization(
                        testName,
                        requestedRefTypeWithPath,
                        "20023620209044528159511741092026111575721096287952033280890216911029466309299244261280131263405759302102973368274392117584712154554147852782521923413761337061812444922609817603902014814625260877983870114563454641565468417567572976451068314416743541124863024839827028842502970154324854269188122507157260575794215703271997986950447857877037520410269496235087062782560401130103628214344829307099367702771821422466053941751470",
                        "2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31", "37", "41", "43", "47", "53", "59", "61", "67", "71", "73", "79", "83", "89", "97", "101", "103", "107", "109", "113", "127", "131", "137", "139", "149", "151", "157", "163", "167", "173", "179", "181", "191", "193", "197", "199", "211", "223", "227", "229", "233", "239", "241", "251", "257", "263", "269", "271", "277", "281", "283", "293", "307", "311", "313", "317", "331", "337", "347", "349", "353", "359", "367", "373", "379", "383", "389", "397", "401", "409", "419", "421", "431", "433", "439", "443", "449", "457", "461", "463", "467", "479", "487", "491", "499", "503", "509", "521", "523", "541", "547", "557", "563", "569", "571", "577", "587", "593", "599", "601", "607", "613", "617", "619", "631", "641", "643", "647", "653", "659", "661", "673", "677", "683", "691", "701", "709", "719", "727", "733", "739", "743", "751", "757", "761", "769", "773", "787", "797", "809", "811", "821", "823", "827", "829", "839", "853", "857", "859", "863", "877", "881", "883", "887", "907", "911", "919", "929", "937", "941", "947", "953", "967", "971", "977", "983", "991", "997", "1009", "1013"
                    );
                    
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
    
    /** Testing: compute() - performance */
    @Test
    public void tol_4s_performance_check_no1_about1secondExpected(){            // name tol_4s_performance_compute_no1_maxOneSecondExpected() führt(e) zu BUG ????
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "PrimeFactorComputer";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( bigNumberLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( testName, requestedRefTypeWithPath );
                    //
                    // start of actual test
                    checkFactorization( testName,  requestedRefTypeWithPath,  "99999999999973", "99999999999973" );
                    
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
