package template_raw.a2;


import static _untouchable_.supportC2x00.Configuration.dbgConfigurationVector;
import static _untouchable_.supportC2x00.Herald.Medium.*;
import static _untouchable_.supportC2x00.PointDefinition.countsOnePoint;
import static _untouchable_.supportC2x00.PointDefinition.countsTwoPoints;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
//
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.util.Random;
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
    final static private int commonDefaultLimit = 4_000;                               // timeout resp. max. number of ms for test
    
    // exercise related
    final static private TE exercise = TE.A2;                                   // <<<<===  _HERE_
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
    //###   local support
    //###
    
    private void doGuardTest_general(
        final String requestedRefTypeWithPath,
        final boolean enableGuardTestSetGetWord,
        final boolean enableGuardTestIsNeoLatin
    ){
        // test uses multiple Objects of WordProcesso on purpose
        try{
            String guardSomeWord1 = "Hallo";
            final WordProcessor_I guardWordProcessor1 = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { guardSomeWord1 } ));
            if( enableGuardTestSetGetWord ){
                guardSomeWord1 = "Welt";
                guardWordProcessor1.setWord( guardSomeWord1 );
            }//if
            
            String guardSomeWord2 = "hi";
            final WordProcessor_I guardWordProcessor2 = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { guardSomeWord2 } ));
            if( enableGuardTestSetGetWord ){
                guardSomeWord2 = "world";
                guardWordProcessor2.setWord( guardSomeWord2 );
            }//if
            
            if( enableGuardTestIsNeoLatin ){
                String guardSomeWord3;
                //
                guardSomeWord3 = "##";
                final WordProcessor_I guardWordProcessor31 = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { guardSomeWord3 } ));
                //
                guardSomeWord3 = "abc";
                final WordProcessor_I guardWordProcessor32 = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { guardSomeWord3 } ));
                //
                guardSomeWord3 = "###";
                final WordProcessor_I guardWordProcessor33 = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { guardSomeWord3 } ));
                assertFalse( guardWordProcessor33.isNeoLatin(),  "Guard test failed" );
                //
                assertFalse( guardWordProcessor31.isNeoLatin(),  "Guard test failed" );
                assertTrue(  guardWordProcessor32.isNeoLatin(),  "Guard test failed" );
                assertFalse( guardWordProcessor33.isNeoLatin(),  "Guard test failed" );
            }//if
            
            assertEquals( guardSomeWord1, guardWordProcessor1.getCurrentWord(),  "Guard test failed" );
            assertEquals( guardSomeWord2, guardWordProcessor2.getCurrentWord(),  "Guard test failed" );
            //\=> NO crash/error yet => guard test successfully passed ;-)
        }catch( final Exception ex ){
            fail( String.format( "Guard test failed -->> %s <<--",  ex.getMessage() ));
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
                        
                        final String requestedRefTypeName = "WordProcessor";
                        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;        
                        try{
                            TS.printDetailedInfoAboutClass( requestedRefTypeWithPath );
                            System.out.printf( "\n" );
                            final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                            TS.printDetailedInfoAboutObject( wp, "WordProcessor" );
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
    
    /** Existenz-Test: Gibt es die Klasse ueberhaupt? - "WordProcessor" */
    @Test
    public void tol_1e_classExistence_WordProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
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
    
    /** Test einiger Eigenschaften des Referenz-Typs WordProcessore */
    @Test
    public void tol_1e_properties_WordProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
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
                        assertTrue( TS.isClass(                                 classUnderTest ),                        String.format( "requested class %s missing", requestedRefTypeName ));
                        assertTrue( TS.isRequestedClassAccessModifierSet(       classUnderTest, Modifier.PUBLIC ),       "false class access modifier");
                        assertTrue( TS.isImplementing(                          classUnderTest, "WordProcessor_I" ),     "requested supertype missing");
                        assertTrue( TS.isConstructor(                           classUnderTest, null ),                  "requested constructor missing");
                        assertTrue( TS.isRequestedConstructorAccessModifierSet( classUnderTest, null, Modifier.PUBLIC ), "false constructor access modifier");
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
    
    /** Test Eigenschaften "WordProcessor" - Access Modifier Methoden */
    @Test
    public void tol_1e_propertiesMethods_WordProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
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
                        assertTrue( TS.isProcedure(       classUnderTest, "removeVocals" ),                                                      "requested method missing" );
                        assertTrue( TS.isFunction(        classUnderTest, "countVocals",       int.class ),                                      "requested method missing" );
                        assertTrue( TS.isFunction(        classUnderTest, "isNeoLatin",        boolean.class ),                                  "requested method missing" );
                        assertTrue( TS.isFunction(        classUnderTest, "getCurrentWord",    String.class ),                                   "requested method missing" );
                        assertTrue( TS.isProcedure(       classUnderTest, "setWord",                          new Class<?>[]{ String.class } ),  "requested method missing" );
                        assertTrue( TS.isProcedurePublic( classUnderTest, "removeVocals" ),                                                      "false method access modifier" );
                        assertTrue( TS.isFunctionPublic(  classUnderTest, "countVocals",       int.class ),                                      "false method access modifier" );
                        assertTrue( TS.isFunctionPublic(  classUnderTest, "isNeoLatin",        boolean.class ),                                  "false method access modifier" );
                        assertTrue( TS.isFunctionPublic(  classUnderTest, "getCurrentWord",    String.class ),                                   "false method access modifier" );
                        assertTrue( TS.isProcedurePublic( classUnderTest, "setWord",                          new Class<?>[]{ String.class } ),  "false method access modifier" );
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
    
    /** Test Eigenschaften "WordProcessor" - Access Modifier Variablen */
    @Test
    public void tol_1e_propertiesFields_WordProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
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
                        assertTrue( TS.allVariableFieldAccessModifiersPrivate( classUnderTest ), "false field access modifier");
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
    
    /** Test Eigenschaften "WordProcessor" - Schreibweise Methoden */
    @Test
    public void tol_1e_notationMethods_WordProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
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
    
    /** Test Eigenschaften "WordProcessor" - Schreibweise Variablen */
    @Test
    public void tol_1e_notationFields_WordProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
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
    
    /** Grundsaetzlicher Test: Kann ueberhaupt ein Objekt erzeugt werden? - "WordProcessor erzeugen ohne Parameter" - Default Constructor*/
    @Test
    public void tol_1e_objectCreationDC_WordProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
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
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.CREATION ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Grundsaetzlicher Test: Kann ueberhaupt ein Objekt erzeugt werden? - "WordProcessor erzeugen mit Parameter" - With Parameter*/
    @Test
    public void tol_1e_objectCreationWP_WordProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final String anyWord = "abc";
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { anyWord } ));
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
            dbManager.enterLocally( new TestTopic( TL.A, exercise, TC.CREATION ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Einfach(st)er Test: (NUR-)Methoden-Aufruf "isNeoLatin()"*/
    @Test
    public void tol_1e_behavior_isNeoLatin(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final String anyWord = "abcde";
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { anyWord } ));
                        wp.isNeoLatin();
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
    
    /** Einfach(st)er Test: (NUR-)Methoden-Aufruf "countVocals()"*/
    @Test
    public void tol_1e_behavior_countVocals(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final String anyWord = "bcdef";
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { anyWord } ));
                        wp.countVocals();
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
    
    /** Einfach(st)er Test: (NUR-)Methoden-Aufruf "removeVocals()"*/
    @Test
    public void tol_1e_behavior_removeVocals(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final String anyWord = "cdefg";
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { anyWord } ));
                        wp.removeVocals();
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
    
    /** Einfach(st)er Test: (NUR-)Methoden-Aufruf "getWord()"*/
    @Test
    public void tol_1e_behavior_getWord(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final String anyWord = "defgh";
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { anyWord } ));
                        wp.getCurrentWord();
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
    
    /** Einfach(st)er Test: (NUR-)Methoden-Aufruf "setWord()"*/
    @Test
    public void tol_1e_behavior_setWord(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final String anyWord = "efghi";
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        wp.setWord( anyWord );
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
    
    /** Einfacher Test: (NUR-)Sequenz-Methoden-Aufruf: "alle ;-)"*/
    @Test
    public void tol_2b_behavior_sequencePureMethodCall_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        String anyWord = "wvuxyz";
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { anyWord } ));
                        wp.isNeoLatin();
                        wp.countVocals();
                        wp.removeVocals();
                        wp.countVocals();
                        wp.isNeoLatin();
                        wp.getCurrentWord();
                        //
                        anyWord = "tuvxoy";
                        wp.setWord( anyWord );
                        wp.getCurrentWord();
                        wp.isNeoLatin();
                        wp.countVocals();
                        wp.removeVocals();
                        wp.countVocals();
                        wp.isNeoLatin();
                        wp.getCurrentWord();
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
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Funktions-Test:"isNeoLatin()"*/
    @Test
    public void tol_2b_behavior_isNeoLatin_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        String testWord = "acdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { testWord } ));
                        assertTrue( wp.isNeoLatin() );
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
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Funktions-Test:"isNeoLatin()"*/
    @Test
    public void tol_2b_behavior_isNeoLatin_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        String testWord = "äöüßÄÖÜ€";
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { testWord } ));
                        assertFalse( wp.isNeoLatin() );
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
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Funktions-Test:"countVocals()"*/
    @Test
    public void tol_2b_behavior_countVocals_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final String vocals = "aeiouAEIOU";
                        for( final char vocal : vocals.toCharArray() ){
                            final String testWord = "q" + vocal + "p";
                            final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { testWord } ));
                            assertEquals( 1, wp.countVocals() );
                        }//for
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
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()    
    
    /** Funktions-Test:"countVocals()"*/
    @Test
    public void tol_2b_behavior_countVocals_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        String testWord = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { testWord } ));
                        assertEquals( 0, wp.countVocals() );
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
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Funktions-Test:"removeVocals() - test is relying on getWord()"*/
    @Test
    public void tol_2b_behavior_removeVocals_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final String vocals = "aeiouAEIOU";
                        for( final char vocal : vocals.toCharArray() ){
                            final String testWord = "d" + vocal + "b";
                            final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { testWord } ));
                            wp.removeVocals();
                            assertEquals( "db", wp.getCurrentWord() );
                        }//for
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
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Funktions-Test:"setWord() and getWord()"*/
    @Test
    public void tol_2b_behavior_setWord_and_getWord_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        String testWord = "aeiouAEIOU";
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { testWord } ));
                        assertEquals( testWord, wp.getCurrentWord() );
                        //
                        testWord = "xyz";
                        wp.setWord( testWord );
                        assertEquals( testWord, wp.getCurrentWord() );
                        //
                        testWord = "qHXHp";
                        wp.setWord( testWord );
                        assertEquals( testWord, wp.getCurrentWord() );
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
            dbManager.enterLocally( new TestTopic( TL.B, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    
    
    //##########################################################################
    //###
    //###   C / "3n"
    //###
    
    /** Funktions-Test:"isNeoLatin()"*/
    @Test
    public void tol_3n_behavior_isNeoLatin_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final String someChars = "äöüÄÖÜß€@#+*\"'\\/?!§$%&()=`´";
                        for( final char unwanted : someChars.toCharArray() ){
                            final String testWord = "aBcDeFgHiJkLmNoPqRsTuVwXyZ" + unwanted + "AbCdEfGhIjKlMnOpQrStUvWxYz";
                            final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { testWord } ));
                            assertFalse( wp.isNeoLatin() );
                        }//for
                        //
                        String testWord = "aBcDeFgHiJkLmNoPqRsTuVwXyZxAbCdEfGhIjKlMnOpQrStUvWxYz";
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { testWord } ));
                        assertTrue( wp.isNeoLatin() );
                        //
                        for( final char unwanted : someChars.toCharArray() ){
                            testWord = "AbCdEfGhIjKlMnOpQrStUvWxYz" + unwanted + "aBcDeFgHiJkLmNoPqRsTuVwXyZ";
                            wp.setWord( testWord );
                            assertFalse( wp.isNeoLatin() );
                        }//for
                        
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
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Funktions-Test: "countVocals()"*/
    @Test
    public void tol_3n_behavior_countVocals_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        String testWord;
                        //
                        testWord = "ooooo";
                        wp.setWord( testWord );
                        assertEquals( 5, wp.countVocals() );
                        assertEquals( testWord, wp.getCurrentWord() );
                        //
                        testWord = "xxxxx";
                        wp.setWord( testWord );
                        assertEquals( 0, wp.countVocals() );
                        assertEquals( testWord, wp.getCurrentWord() );
                        //
                        testWord = "xooox";
                        wp.setWord( testWord );
                        assertEquals( 3, wp.countVocals() );
                        assertEquals( testWord, wp.getCurrentWord() );
                        //
                        testWord = "abcdefghijkl";
                        wp.setWord( testWord );
                        assertEquals( 3, wp.countVocals() );
                        assertEquals( testWord, wp.getCurrentWord() );
                        //
                        testWord = "EEEUUIMpqXdbWWWe";
                        wp.setWord( testWord );
                        assertEquals( 7, wp.countVocals() );
                        assertEquals( testWord, wp.getCurrentWord() );
                        //
                        testWord = "kOOoVbbbbbcdfAhuazzzZZZZZ";
                        wp.setWord( testWord );
                        assertEquals( 6, wp.countVocals() );
                        assertEquals( testWord, wp.getCurrentWord() );
                        //
                        testWord = "aaaeeeiiiooouuuAAAEEEIIIOOOUUU";
                        wp.setWord( testWord );
                        assertEquals( 30, wp.countVocals() );
                        assertEquals( testWord, wp.getCurrentWord() );
                        
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
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if    
    }//method()
    
    
    /** Funktions-Test: "removeVocals()"*/
    @Test
    public void tol_3n_behavior_removeVocals_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        String testWord;
                        //
                        testWord = "ooxoo";
                        wp.setWord( testWord );
                        assertEquals( testWord, wp.getCurrentWord() );
                        wp.removeVocals();
                        assertEquals( "x", wp.getCurrentWord() );
                        assertEquals( 0, wp.countVocals() );
                        //
                        testWord = "xxxxx";
                        wp.setWord( testWord );
                        assertEquals( testWord, wp.getCurrentWord() );
                        wp.removeVocals();
                        assertEquals( "xxxxx", wp.getCurrentWord() );
                        assertEquals( 0, wp.countVocals() );
                        //
                        testWord = "xooox";
                        wp.setWord( testWord );
                        assertEquals( testWord, wp.getCurrentWord() );
                        wp.removeVocals();
                        assertEquals( "xx", wp.getCurrentWord() );
                        assertEquals( 0, wp.countVocals() );
                        //
                        testWord = "EEEUUIMpqXdbWWWe";
                        wp.setWord( testWord );
                        assertEquals( testWord, wp.getCurrentWord() );
                        wp.removeVocals();
                        assertEquals( "MpqXdbWWW", wp.getCurrentWord() );
                        assertEquals( 0, wp.countVocals() );
                        //
                        testWord = "kOOoVbbbbbcdfAhuazzzZZZZZ";
                        wp.setWord( testWord );
                        assertEquals( testWord, wp.getCurrentWord() );
                        wp.removeVocals();
                        assertEquals( "kVbbbbbcdfhzzzZZZZZ", wp.getCurrentWord() );
                        assertEquals( 0, wp.countVocals() );
                        //
                        testWord = "aaaeeeiiiooouuuAAAEEEIIIOOOUUU";
                        wp.setWord( testWord );
                        assertEquals( testWord, wp.getCurrentWord() );
                        wp.removeVocals();
                        assertEquals( "", wp.getCurrentWord() );
                        assertEquals( 0, wp.countVocals() );
                        
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
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if    
    }//method()
    
    
    
    //--------------------------------------------------------------------------
    //
    //      multiple objects
    //
    
    /** Funktions-Test: multiple objects*/
    @Test
    public void tol_3n_behavior_multipleObjects_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    final String[] testWord = {
                        "xooox",
                        "xoxox",
                        "ooxoo",
                        "oxoxo",
                        "öoöoö",
                        "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz",
                        "AAaaEEeeIIiiOOooUUuu"
                        
                    };
                    final boolean[] expectedNeoLatin = {
                        true,
                        true,
                        true,
                        true,
                        false,
                        true,
                        true
                    };
                    final int[] expectedNumberOfVocals = {
                        3,
                        2,
                        4,
                        3,
                        2,
                        0,
                        20
                    };
                    final String[] expectedWord = {
                        "xx",
                        "xxx",
                        "x",
                        "xx",
                        "ööö",
                        "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz",
                        ""
                    };
                    //
                    assert testWord.length == expectedNeoLatin.length       : "Internal setup error - call schafers";
                    assert testWord.length == expectedNumberOfVocals.length : "Internal setup error - call schafers";
                    assert testWord.length == expectedWord.length           : "Internal setup error - call schafers";
                    //
                    final WordProcessor_I[] wp = new WordProcessor_I[testWord.length<<1];
                    try{
                        // setup
                        for( int i=0, j=0;  i<testWord.length;  i++ ){
                            wp[j] = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { testWord[i] } ));
                            j++;
                            
                            wp[j] = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                            wp[j].setWord(testWord[i]);
                            j++;
                        }//for
                        //
                        // getWord()
                        for( int i=0, j=0;  i<testWord.length;  i++ ){
                            assertEquals( testWord[i], wp[j].getCurrentWord() );
                            j++;
                            
                            assertEquals( testWord[i], wp[j].getCurrentWord() );
                            j++;
                        }//for
                        //
                        // isNeoLatin()
                        for( int i=0, j=0;  i<testWord.length;  i++ ){
                            assertEquals( expectedNeoLatin[i], wp[j].isNeoLatin() );
                            j++;
                            
                            assertEquals( expectedNeoLatin[i], wp[j].isNeoLatin() );
                            j++;
                        }//for
                        //
                        // countVocals()
                        for( int i=0, j=0;  i<testWord.length;  i++ ){
                            //check if NeoLatin ?  ->  Wuerde sonst u.U. nachtraeglich den Schwierigkeitsgrad "unfair" anheben
                            if( expectedNeoLatin[i] ){
                                assertEquals( expectedNumberOfVocals[i], wp[j].countVocals() );
                                j++;
                                
                                assertEquals( expectedNumberOfVocals[i], wp[j].countVocals() );
                                j++;
                            }else{
                                j+=2;
                            }//if
                        }//for
                        //
                        // removeVocals()  combined with countVocals()
                        for( int i=0, j=0;  i<testWord.length;  i++ ){
                            //check if NeoLatin ?  ->  Wuerde sonst u.U. nachtraeglich den Schwierigkeitsgrad "unfair" anheben
                            if( expectedNeoLatin[i] ){
                                assertEquals( expectedNumberOfVocals[i], wp[j].countVocals() );
                                wp[j].removeVocals();
                                assertEquals( 0, wp[j].countVocals() );
                                j++;
                                
                                assertEquals( expectedNumberOfVocals[i], wp[j].countVocals() );
                                wp[j].removeVocals();
                                assertEquals( 0, wp[j].countVocals() );
                                j++;
                            }else{
                                j+=2;
                            }//if
                        }//for
                        //
                        // getWord() again ;-)
                        for( int i=0, j=0;  i<testWord.length;  i++ ){
                            //check if NeoLatin ?  ->  Wuerde sonst u.U. nachtraeglich den Schwierigkeitsgrad "unfair" anheben
                            if( expectedNeoLatin[i] ){
                                assertEquals( expectedWord[i], wp[j].getCurrentWord() );
                                j++;
                                
                                assertEquals( expectedWord[i], wp[j].getCurrentWord() );
                                j++;
                            }else{
                                j+=2;
                            }//if
                        }//for
                        //
                        // countVocals() again ;-)
                        for( int i=0, j=0;  i<testWord.length;  i++ ){
                            //check if NeoLatin ?  ->  Wuerde sonst u.U. nachtraeglich den Schwierigkeitsgrad "unfair" anheben
                            if( expectedNeoLatin[i] ){
                                assertEquals( 0, wp[j].countVocals() );
                                j++;
                                
                                assertEquals( 0, wp[j].countVocals() );
                                j++;
                            }else{
                                j+=2;
                            }//if
                        }//for
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
            dbManager.enterLocally( new TestTopic( TL.C, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsTwoPoints ));
        }//if    
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
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
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
    
    
    /** Stress-Test: parameter is null" */
    @Test
    public void tol_4s_behavior_parameter_null_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeWithPath, false, false );
                    //
                    // start of actual test
                    try{
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { null } ));
                        fail( "undetected illegal argument -> null accepted" );
                    }catch( final TestSupportException ex ){
                        TS.examineInternallyRaisedTestSupportExceptionForIllegalArgumentCauseAndReact( testName, ex );
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
    
    /** Stress-Test: parameter is null" */
    @Test
    public void tol_4s_behavior_parameter_null_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeWithPath, true, false );
                    //\=> NO crash yet => guard test successfully passed ;-)
                    //
                    // start of actual test
                    try{
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        boolean expectedExceptionOccured = false;
                        try{
                            wp.setWord( null );                                             // ATTENTION! : This will raise NO TestSupportException -> hence, do NOT check for it ;-)
                        }catch( final Throwable ex ){
                            TS.examineExceptionForIllegalArgumentCauseAndReact( testName, ex );
                            expectedExceptionOccured = true;
                        }//try
                      //\=> either AssertionError|IllegalArgumentException or NO exception at all
                        assertTrue( expectedExceptionOccured, "undetected illegal argument -> null accepted" );
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
    
    
    /** Stress-Test: parameter is empty" */
    @Test
    public void tol_4s_behavior_parameter_empty_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeWithPath, false, true );
                    //
                    // start of actual test
                    try{
                        final String testWord = "";
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath, new Class<?>[]{ String.class }, new Object[] { testWord } ));
                        assertTrue( wp.isNeoLatin() );
                        assertEquals( 0, wp.countVocals() );
                        wp.removeVocals();
                        assertEquals( testWord, wp.getCurrentWord() );
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
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Stress-Test: parameter is empty" */
    @Test
    public void tol_4s_behavior_parameter_empty_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeWithPath, true, true );
                    //
                    // start of actual test
                    try{
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        final String testWord = "";
                        wp.setWord( testWord );
                        assertTrue( wp.isNeoLatin() );
                        assertEquals( 0, wp.countVocals() );
                        wp.removeVocals();
                        assertEquals( testWord, wp.getCurrentWord() );
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
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    /** Stress-Test:"setWord() and getWord()"*/
    @Test
    public void tol_4s_behavior_setWord_and_getWord_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "WordProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeWithPath, true, true );
                    //
                    // start of actual test
                    try{
                        final Random randomGenerator = new Random( 0xc7 );
                        final char[] vocalContainer = "aeiouAEIOUaAaAaEeEeEiIiIiOoOoOuUuUu".toCharArray();
                        final char[] otherContainer = "BcDfGhJkLmNpQrStVwXyZbCdFgHjKlMnPqRsTvWxYz".toCharArray();
                        final WordProcessor_I wp = (WordProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        for( int runNo=127;  --runNo>=0; ){
                            // compute test word
                            char c;
                            StringBuffer sb = new StringBuffer( "" );
                            int vc=0;
                            //
                            if( randomGenerator.nextBoolean() ){
                                for( int m=randomGenerator.nextInt( 3 );  --m>=0;  ){
                                    c = otherContainer[ randomGenerator.nextInt( otherContainer.length ) ];
                                    sb.append( c );
                                }//for
                            }//if
                            //
                            for( int f=randomGenerator.nextInt( 19 );  --f>=0;  ){
                                for( int m=randomGenerator.nextInt( 11 );  --m>=0;  ){
                                    c = vocalContainer[ randomGenerator.nextInt( vocalContainer.length ) ];
                                    sb.append( c );
                                    vc++;
                                }//for
                                //
                                for( int m=randomGenerator.nextInt( 7 );  --m>=0;  ){
                                    c = otherContainer[ randomGenerator.nextInt( otherContainer.length ) ];
                                    sb.append( c );
                                }//for
                            }//for
                            //
                            if( randomGenerator.nextBoolean() ){
                                for( int m=randomGenerator.nextInt( 5 );  --m>=0;  ){
                                    c = vocalContainer[ randomGenerator.nextInt( vocalContainer.length ) ];
                                    sb.append( c );
                                    vc++;
                                }//for
                            }//if                
                            final String testWord = sb.toString();
                            //
                            // test with test word
                            wp.setWord( testWord );
                            assertTrue( wp.isNeoLatin() );
                            int vocalCnt = wp.countVocals();
                            assertEquals(
                                vc,
                                vocalCnt,
                                String.format( "%s -> expected:<%d> but was:<%d>",  testWord, vc, vocalCnt )
                            );
                            //
                            wp.removeVocals();
                            assertTrue( wp.isNeoLatin() );
                            vocalCnt = wp.countVocals();
                            assertEquals(
                                0,
                                vocalCnt,
                                String.format( "%s -> expected:<0> but was:<%d>",  testWord, vocalCnt )
                            );
                        }//for
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
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
}//class
