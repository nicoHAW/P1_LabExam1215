package template_raw.a5;


import static _untouchable_.supportC2x00.Configuration.dbgConfigurationVector;
import static _untouchable_.supportC2x00.Herald.Medium.SYS_ERR;
import static _untouchable_.supportC2x00.PointDefinition.countsOnePoint;
import static _untouchable_.supportC2x00.PointDefinition.countsTwoPoints;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
//
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
import _untouchable_.thingy.Color;
import _untouchable_.thingy.Item;
import _untouchable_.thingy.Size;
import _untouchable_.thingy.ItemFabric;
import _untouchable_.thingy.Weight;


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
    final static private TE exercise = TE.A5;                                   // <<<<===  _HERE_
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
    
    private static String convertQuintetToString( final Quintet_I quintet ){
        final StringBuffer sb = new StringBuffer();
        if( null==quintet ){
            sb.append( "null" );
        }else{
            try {
                final int numberOfElements = 5;
                sb.append( "[ " );
                for( int i=0; i<numberOfElements; i++ ){
                    final Item currentItem = quintet.getItem(i);
                    if( null==currentItem ){
                        sb.append( "null" );
                    }else{
                        sb.append( currentItem.toString());
                    }//if
                    if( numberOfElements-1 >= i ){
                        sb.append( " , " );
                    }//if
                }//for
                sb.append( " ]" );
            }catch( final Exception ex ){
                // es kann eigentlich nur getItem() das auslösende Problem sein
                sb.append( ex.getMessage() );
            }//try
        }//if
        return sb.toString();
    }//method()
    
    
    
    private void doGuardTest_general(
        final String testName,
        final String requestedRefTypeWithPath,
        final boolean enableAdditionalGuardTests
    ){
        // test uses multiple Objects of ItemProcessor on purpose
        try{
            final int numberOfObjects = 3;
            final int numberOfItemsPerObject = 4;
            final ItemFabric itemFabric = new ItemFabric();
            final ItemProcessor_I itemProcessor[] = new ItemProcessor_I[numberOfItemsPerObject];
            // 1st(!) create objects
            for( int objCnt=0; objCnt<numberOfObjects; objCnt++ ){
                itemProcessor[objCnt] = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            }//for
            // 2nd(!) use objects (after creation of all objects is done)
            for( int objCnt=0; objCnt<numberOfObjects; objCnt++ ){
                for( int itemCntPerObject=0; itemCntPerObject<numberOfItemsPerObject; itemCntPerObject++ ){
                    final Item guardSomeParameter = itemFabric.createDeterministicItem( 73 );
                    final Quintet_I guardComputedResult = itemProcessor[objCnt].process( guardSomeParameter );
                    assertNull( guardComputedResult, String.format(
                        "[Guard test failed] -->> null was expected ;  last incoming item was %s ;  unexpectly reported quintet is %s",
                        guardSomeParameter,
                        convertQuintetToString( guardComputedResult )
                    ));
                    if( enableAdditionalGuardTests ){
                        assertEquals( itemCntPerObject+1, itemProcessor[objCnt].itemsProcessed(),  "[Guard test failed]" );
                        assertEquals( 0, itemProcessor[objCnt].quintetsFound(),  "[Guard test failed]" );
                    }//if
                }//for
            }//for
        }catch( final Exception ex ){
            fail( String.format( "[Guard test failed] -->> %s <<--",  ex.getMessage() ));
        }//try
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
    
    /** Ausgabe auf Bildschirm zur visuellen Kontrolle */
    @Test
    public void tol_1e_printSupportForManualReview_CC(){
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
                        
                        final String requestedRefTypeName = "ItemProcessor";
                        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;        
                        try{
                            TS.printDetailedInfoAboutClass( requestedRefTypeWithPath );
                            System.out.printf( "\n" );
                            final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                            TS.printDetailedInfoAboutObject( itemProcessor, "itemProcessor" );
                            //
                            if( TS.isActualMethod( itemProcessor.getClass(), "toString", String.class, null )){
                                System.out.printf( "~.toString(): \"%s\"     again ;-)\n",  itemProcessor.toString() );
                            }else{
                                System.out.printf( "NO! toString() implemented by class \"%s\" itself\n",  itemProcessor.getClass().getSimpleName() );
                            }//if
                            
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
    
    /** Testing: "ItemProcessor" */
    @Test
    public void tol_1e_classExistence_ItemProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
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
    
    /** Testing: Eigenschaften des Referenz-Typs ItemProcessor incl. Constructor */
    @Test
    public void tol_1e_properties_ItemProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
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
                        assertTrue( TS.isClass(             classUnderTest ),                      String.format( "requested class %s missing", requestedRefTypeName ));
                        assertTrue( TS.isClassPublic(       classUnderTest ),                      "false class access modifier" );
                        assertTrue( TS.isImplementing(      classUnderTest,   "ItemProcessor_I" ), "requested supertype missing" );
                        assertTrue( TS.isConstructor(       classUnderTest ),                      "requested constructor missing");
                        assertTrue( TS.isConstructorPublic( classUnderTest ),                      "false constructor access modifier");
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
    
    /** Testing: Eigenschaften "ItemProcessor" - Access Modifier Methoden */
    @Test
    public void tol_1e_propertiesMethods_ItemProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
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
                        assertTrue( TS.isFunction(        classUnderTest, "process",        new Class<?>[]{ Item.class }, Quintet_I.class ), "requested method missing" );
                        assertTrue( TS.isFunction(        classUnderTest, "itemsProcessed",                               int.class ),       "requested method missing");
                        assertTrue( TS.isFunction(        classUnderTest, "quintetsFound",                                int.class ),       "requested method missing" );
                        assertTrue( TS.isProcedure(       classUnderTest, "reset" ),                                                         "requested method missing" );
                        //
                        assertTrue( TS.isFunctionPublic(  classUnderTest, "process",        new Class<?>[]{ Item.class }, Quintet_I.class ), "false method access modifier" ); // -D interface ;-)
                        assertTrue( TS.isFunctionPublic(  classUnderTest, "itemsProcessed",                               int.class ),       "false method access modifier" );                                                   // -D interface ;-)
                        assertTrue( TS.isFunctionPublic(  classUnderTest, "quintetsFound",                                int.class ),       "false method access modifier" );                                                   // -D interface ;-)
                        assertTrue( TS.isProcedurePublic( classUnderTest, "reset" ),                                                         "false method access modifier" );                                                   // -D interface ;-)
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
    
    /** Testing: Eigenschaften "ItemProcessor" - Access Modifier Variablen */
    @Test
    public void tol_1e_propertiesFields_ItemProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
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
    
    /** Testing: Eigenschaften "ItemProcessor" - Schreibweise Methoden */
    @Test
    public void tol_1e_notationMethods_ItemProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
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
    
    /** Testing: Eigenschaften "ItemProcessor" - Schreibweise Variablen */
    @Test
    public void tol_1e_notationFields_ItemProcessor(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
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
    
    /** Testing: Kann ueberhaupt ein Objekt erzeugt werden? - "ItemProcessor erzeugen"(ohne Parameter) */
    @Test
    public void tol_1e_objectCreationNP_ItemProcessor(){                        // NP ::= No Parameter
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
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
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /** Testing: (NUR-)Methoden-Aufruf "process()" */
    @Test
    public void tol_1e_behavior_process(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        final Item anyItem = new Item( Color.CYAN, Size.MEDIUM, Weight.MEDIUM, 2L );
                        final Quintet_I computedResult = itemProcessor.process( anyItem );
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
    
    
    /** Testing: (NUR-)Methoden-Aufruf "itemsProcessed()" */
    @Test
    public void tol_1e_behavior_itemsProcessed(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        final int anyValue = itemProcessor.itemsProcessed();
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
    
    
    /** Testing: (NUR-)Methoden-Aufruf "quintetsFound()" */
    @Test
    public void tol_1e_behavior_quintetsFound(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        final int anyValue = itemProcessor.quintetsFound();
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
    
    
    /** Testing: (NUR-)Methoden-Aufruf "reset()" */
    @Test
    public void tol_1e_behavior_reset(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        itemProcessor.reset();
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
    
    /** Testing: (NUR-)Sequenz-Methoden-Aufruf: "alle ;-)" */
    @Test
    public void tol_2b_behavior_sequencePureMethodCall_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        itemProcessor.process( new Item( Color.values()[0], Size.values()[0], Weight.values()[0], 0L ) );
                        itemProcessor.itemsProcessed();
                        itemProcessor.quintetsFound();
                        itemProcessor.reset();
                        itemProcessor.itemsProcessed();
                        itemProcessor.quintetsFound();
                        itemProcessor.process( new Item( Color.values()[1], Size.values()[1], Weight.values()[1], 1L ) );
                        itemProcessor.itemsProcessed();
                        itemProcessor.quintetsFound();
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
    
    
    /** Testing: itemsProcessed() */
    @Test
    public void tol_2b_behavior_itemsProcessed_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        final Item testParameter = new Item( Color.values()[2], Size.values()[1], Weight.values()[0], 42L );
                        assertEquals( 0, itemProcessor.itemsProcessed() );
                        itemProcessor.process( testParameter );
                        assertEquals( 1, itemProcessor.itemsProcessed() );
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
    
    
    /** Testing: process() */
    @Test
    public void tol_2b_behavior_process_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        // test with 4 items of equal value
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        int itemCreationId;
                        for( itemCreationId=0; itemCreationId<4; itemCreationId++ ){
                            final Item currentItem = new Item(
                                Color.values()[  itemCreationId % Color.values().length ],
                                Size.values()[   itemCreationId % Size.values().length ],
                                Weight.values()[ itemCreationId % Weight.values().length ],
                                42L
                            );
                            assertNull( itemProcessor.process( currentItem ));
                        }//for
                        //
                        // and now the 5th item of equal value
                        final Item currentItem = new Item(
                            Color.values()[  itemCreationId % Color.values().length ],
                            Size.values()[   itemCreationId % Size.values().length ],
                            Weight.values()[ itemCreationId % Weight.values().length ],
                            42L
                        );
                        final Quintet_I computedResult = itemProcessor.process( currentItem );
                        assertNotNull( computedResult );
                        assertTrue( computedResult instanceof Quintet_I, "TYPE-CONFLICT-ERROR" );
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
    
    
    /** Testing: quintetsFound() */
    @Test
    public void tol_2b_behavior_quintetsFound_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        // test with 4 items of equal value
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        int itemCreationId;
                        for( itemCreationId=0; itemCreationId<4; itemCreationId++ ){
                            final Item currentItem = new Item(
                                Color.values()[  itemCreationId % Color.values().length ],
                                Size.values()[   itemCreationId % Size.values().length ],
                                Weight.values()[ itemCreationId % Weight.values().length ],
                                42L
                            );
                            itemProcessor.process( currentItem );
                            assertEquals( 0, itemProcessor.quintetsFound() );
                        }//for
                        //
                        // and now the 5th item of equal value
                        final Item currentItem = new Item(
                            Color.values()[  itemCreationId % Color.values().length ],
                            Size.values()[   itemCreationId % Size.values().length ],
                            Weight.values()[ itemCreationId % Weight.values().length ],
                            42L
                        );
                        itemProcessor.process( currentItem );
                        assertEquals( 1, itemProcessor.quintetsFound() );
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
    
    
    /** Testing: reset() */
    @Test
    public void tol_2b_behavior_reset_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        // test with 4 items of equal value
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        int itemCreationId;
                        for( itemCreationId=0; itemCreationId<4; itemCreationId++ ){
                            final Item currentItem = new Item(
                                Color.values()[  itemCreationId % Color.values().length ],
                                Size.values()[   itemCreationId % Size.values().length ],
                                Weight.values()[ itemCreationId % Weight.values().length ],
                                42L
                            );
                            final Quintet_I computedResult = itemProcessor.process( currentItem );
                            assertNull( computedResult, String.format(
                                "null was expected ;  last incoming item was %s ;  unexpectly reported quintet is %s",
                                currentItem,
                                convertQuintetToString( computedResult )
                            ));
                            assertEquals( 0, itemProcessor.quintetsFound() );
                            assertNotEquals( 0, itemProcessor.itemsProcessed() );
                        }//for
                        //
                        // reset before 5th item with equal value
                        itemProcessor.reset();
                        //
                        assertEquals( 0, itemProcessor.itemsProcessed() );
                        assertEquals( 0, itemProcessor.quintetsFound() );
                        //
                        // test with 5th item (reset was done before)
                        final Item currentItem = new Item(
                            Color.values()[  itemCreationId % Color.values().length ],
                            Size.values()[   itemCreationId % Size.values().length ],
                            Weight.values()[ itemCreationId % Weight.values().length ],
                            42L
                        );
                        final Quintet_I computedResult = itemProcessor.process( currentItem );
                        assertNull( computedResult, String.format(
                            "null was expected ;  last incoming item was %s ;  unexpectly reported quintet is %s",
                            currentItem,
                            convertQuintetToString( computedResult )
                        ));
                        assertEquals( 0, itemProcessor.quintetsFound() );
                        assertNotEquals( 0, itemProcessor.itemsProcessed() );
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
    
    /** Testing: reset() */
    @Test
    public void tol_2b_behavior_reset_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    // reset after each processed item there will NEVER be two equal items and hence NOT any quintet
                    try{
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        Quintet_I computedResult;
                        for( int itemCreationId=0; itemCreationId<16; itemCreationId++ ){
                            final Item currentItem = new Item(
                                Color.values()[  itemCreationId % Color.values().length ],
                                Size.values()[   itemCreationId % Size.values().length ],
                                Weight.values()[ itemCreationId % Weight.values().length ],
                                42L
                            );
                            computedResult = itemProcessor.process( currentItem );
                            assertNull( computedResult, String.format(
                                "null was expected ;  last incoming item was %s ;  unexpectly reported quintet is %s",
                                currentItem,
                                convertQuintetToString( computedResult )
                            ));
                            assertEquals( 0, itemProcessor.quintetsFound() );
                            assertNotEquals( 0, itemProcessor.itemsProcessed() );
                            itemProcessor.reset();
                            assertEquals( 0, itemProcessor.quintetsFound() );
                            assertEquals( 0, itemProcessor.itemsProcessed() );
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
    
    
    
    
    
    
    
    
    
    
    //##########################################################################
    //###
    //###   C / "3n"
    //###
    
    /** Funktions-Test: process() */
    @Test
    public void tol_3n_behavior_process_simple_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final int differentValues = 7;
                        final int numberOfIterations = differentValues * 4;
                        final ItemFabric itemFabric = new ItemFabric( differentValues, false );
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        int i;
                        for( i=0; i<numberOfIterations; i++ ){
                            final Item currentItem = itemFabric.createDeterministicItem( i );
                            final Quintet_I computedResult = itemProcessor.process( currentItem );
                            assertNull( computedResult, String.format(
                                "null was expected ;  last incoming item was %s ;  unexpectly reported quintet is %s",
                                currentItem,
                                convertQuintetToString( computedResult )
                            ));
                        }//for
                        //
                        final Item currentItem = itemFabric.createDeterministicItem( i );
                        final Quintet_I computedResult = itemProcessor.process( currentItem );
                        assertNotNull( computedResult, String.format(
                            "quintet was expected  but null was computed ;  last incoming item was %s",
                            currentItem
                        ));
                        assertTrue( computedResult instanceof Quintet_I, "TYPE-CONFLICT-ERROR" );
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
    
    /** Funktions-Test: process() */
    @Test
    public void tol_3n_behavior_process_simple_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final int differentValues = 4583;                                   //4583 > 4575 = 61*3*5 *5
                        final int numberOfIterations = differentValues * 4;
                        final ItemFabric itemFabric = new ItemFabric( differentValues, true );
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        int i;
                        for( i=0; i<numberOfIterations; i++ ){
                            final Item currentItem = itemFabric.createDeterministicItem( i );
                            final Quintet_I computedResult = itemProcessor.process( currentItem );
                            assertNull( computedResult, String.format(
                                "null was expected ;  last incoming item was %s ;  unexpectly reported quintet is %s",
                                currentItem,
                                convertQuintetToString( computedResult )
                            ));
                        }//for
                        //
                        final Item currentItem = itemFabric.createDeterministicItem( i );
                        final Quintet_I computedResult = itemProcessor.process( currentItem );
                        assertNotNull( computedResult, String.format(
                            "quintet was expected  but null was computed ;  last incoming item was %s",
                            currentItem
                        ));
                        assertTrue( computedResult instanceof Quintet_I, "TYPE-CONFLICT-ERROR" );
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
    
    
    /** Funktions-Test: quintetsFound() */
    @Test
    public void tol_3n_behavior_quintetsFound_simple_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final int differentValues = 7;
                        final int numberOfIterations = differentValues * 4;
                        final ItemFabric itemFabric = new ItemFabric( differentValues, false );
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        int i;
                        for( i=0; i<numberOfIterations; i++ ){
                            final Item currentItem = itemFabric.createDeterministicItem( i );
                            final Quintet_I computedResult = itemProcessor.process( currentItem );
                            assertEquals( 0, itemProcessor.quintetsFound() );
                        }//for
                        //
                        final Item currentItem = itemFabric.createDeterministicItem( i );
                        final Quintet_I computedResult = itemProcessor.process( currentItem );
                        assertEquals( 1, itemProcessor.quintetsFound() );
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
    
    /** Funktions-Test: quintetsFound() */
    @Test
    public void tol_3n_behavior_quintetsFound_simple_no2(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final int differentValues = 4583;                                   //4583 > 4575 = 61*3*5 *5
                        final int numberOfIterations = differentValues * 4;
                        final ItemFabric itemFabric = new ItemFabric( differentValues, true );
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        int i;
                        for( i=0; i<numberOfIterations; i++ ){
                            final Item currentItem = itemFabric.createDeterministicItem( i );
                            final Quintet_I computedResult = itemProcessor.process( currentItem );
                            assertEquals( 0, itemProcessor.quintetsFound() );
                        }//for
                        //
                        final Item currentItem = itemFabric.createDeterministicItem( i );
                        final Quintet_I computedResult = itemProcessor.process( currentItem );
                        assertEquals( 1, itemProcessor.quintetsFound() );
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
    
    /** Funktions-Test: quintetsFound() */
    @Test
    public void tol_3n_behavior_quintetsFound_simple_no3(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    try{
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        int itemCreationId = 0;
                        for( itemCreationId=0; itemCreationId<4; itemCreationId++ ){
                            final Item currentItem = new Item(
                                Color.values()[  itemCreationId % Color.values().length ],
                                Size.values()[   itemCreationId % Size.values().length ],
                                Weight.values()[ itemCreationId % Weight.values().length ],
                                42L
                            );
                            itemProcessor.process( currentItem );
                            assertEquals( 0, itemProcessor.quintetsFound() );
                        }//for
                        //
                        final Item currentItem = new Item(
                            Color.values()[  itemCreationId % Color.values().length ],
                            Size.values()[   itemCreationId % Size.values().length ],
                            Weight.values()[ itemCreationId % Weight.values().length ],
                            42L
                        );
                        itemProcessor.process( currentItem );
                        assertEquals( 1, itemProcessor.quintetsFound() );
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
    
    
    /** Funktions-Test: itemsProcessed() */
    @Test
    public void tol_3n_behavior_itemsProcessed_simple_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final int differentValues = 4583;                       //4583 > 4575 = 61*3*5 *5
                        final int numberOfIterations = differentValues * 4;
                        final ItemFabric itemFabric = new ItemFabric( differentValues, true );
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        int i;
                        for( i=0; i<numberOfIterations; i++ ){
                            final Item currentItem = itemFabric.createDeterministicItem( i );
                            assertEquals( i, itemProcessor.itemsProcessed() );
                            itemProcessor.process( currentItem );
                        }//for
                        assertEquals( i, itemProcessor.itemsProcessed() );
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
    
    
    
    
    
    /** Funktions-Test: combined */
    @Test
    public void tol_3n_behavior_process_itemSequence_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    try{
                        final int[] delta = { 1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 141 };
                        final int[] offset = new int[delta.length];
                        offset[0]=delta[0];
                        for( int i=1; i<delta.length; i++ )  offset[i] = offset[i-1] + delta[i];
                        //
                        final int differentValues = 911;                                    //911 <~ 915 = 3*5*61   &   911 < 915  !
                        final ItemFabric itemFabric = new ItemFabric( differentValues, true );
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        
                        int expectedQuintetsFound = 0;
                        int itemCreationId;
                        /*scope*/{
                            final int numberOfIterations = differentValues * 3;
                            for( itemCreationId=0;  itemCreationId<numberOfIterations;  itemCreationId++ ){
                                final Item currentItem = itemFabric.createDeterministicItem( itemCreationId );
                                final Quintet_I computedResult = itemProcessor.process( currentItem );
                                assertNull( computedResult, String.format(
                                    "null was expected ;  last incoming item was %s ;  unexpectly null was NOT computed/reported; reported quintet is %s",
                                    currentItem,
                                    convertQuintetToString( computedResult )
                                ));
                                assertEquals( expectedQuintetsFound, itemProcessor.quintetsFound() );
                            }//for
                        }//scope
                        //\=> itemCreationId contains item-creation-id of next item to be created
                        
                        /*scope*/{
                            final int itemCreationBasisId = itemCreationId;
                            int deltaIndex = 0;
                            int localIndex = delta[deltaIndex];
                            while( differentValues > localIndex ){
                                final Item currentItem = itemFabric.createDeterministicItem( itemCreationBasisId + localIndex );
                                final Quintet_I computedResult = itemProcessor.process( currentItem );
                                assertNull( computedResult, String.format(
                                    "null was expected ;  last incoming item was %s ;  unexpectly null was NOT computed/reported; reported quintet is %s",
                                    currentItem,
                                    convertQuintetToString( computedResult )
                                ));
                                assertEquals( expectedQuintetsFound, itemProcessor.quintetsFound() );
                                deltaIndex++;
                                assert deltaIndex < delta.length : "UUUUpppss : internal ERROR";
                                localIndex += delta[deltaIndex];
                            }//while
                            itemCreationId += differentValues;
                        }//scope
                        //\=> itemCreationId contains item-creation-id of next item to be created
                        
                        /*scope*/{
                            final int itemCreationBasisId = itemCreationId;
                            int offsetIndex = 0;
                            int itemCreationIdRelatedToExpectedQuintet = itemCreationBasisId + offset[offsetIndex];
                            //
                            for( int localIndex=0;  localIndex<differentValues;  localIndex++ ){
                                final Item currentItem = itemFabric.createDeterministicItem( itemCreationId );
                                final Quintet_I computedResult = itemProcessor.process( currentItem );
                                //
                                if( itemCreationId < itemCreationIdRelatedToExpectedQuintet ){
                                    //\=> NO quintet expected
                                    assertEquals( expectedQuintetsFound, itemProcessor.quintetsFound() );
                                    assertNull( computedResult, String.format(
                                        "null was expected ;  last incoming item was %s ;  unexpectly null was NOT computed/reported; reported quintet is %s",
                                        currentItem,
                                        convertQuintetToString( computedResult )
                                    ));
                                }else{
                                    //\=> localIndex == localItemCreationIdRelatedToExpectedQuintetlocalIndex   => quintet expected
                                    //
                                    expectedQuintetsFound++;
                                    //
                                    // was quintet computed ?
                                    assertEquals( expectedQuintetsFound, itemProcessor.quintetsFound() );                        
                                    assertNotNull( computedResult, String.format(
                                        "quintet was expected  but null was computed ;  last incoming item was %s",
                                        currentItem
                                    ));
                                    //
                                    // was correct quintet computed ?
                                    final Item reported1stItem = computedResult.getItem(0);
                                    final Item reported2ndItem = computedResult.getItem(1);
                                    final Item reported3rdItem = computedResult.getItem(2);
                                    final Item reported4thItem = computedResult.getItem(3);
                                    final Item reported5thItem = computedResult.getItem(4);
                                    final Item expected1stItem = itemFabric.createDeterministicItem( offset[offsetIndex] + 0*differentValues );
                                    final Item expected2ndItem = itemFabric.createDeterministicItem( offset[offsetIndex] + 1*differentValues );
                                    final Item expected3rdItem = itemFabric.createDeterministicItem( offset[offsetIndex] + 2*differentValues );
                                    final Item expected4thItem = itemFabric.createDeterministicItem( offset[offsetIndex] + 3*differentValues );
                                    final Item expected5thItem = itemFabric.createDeterministicItem( offset[offsetIndex] + 4*differentValues );
                                    assertArrayEquals(
                                        new Item[]{ expected1stItem, expected2ndItem, expected3rdItem, expected4thItem, expected5thItem },
                                        new Item[]{ reported1stItem, reported2ndItem, reported3rdItem, reported4thItem, reported5thItem },
                                        "quintet is NOT as expected"
                                    );
                                    //
                                    // prepare for next test
                                    offsetIndex++;
                                    itemCreationIdRelatedToExpectedQuintet = itemCreationBasisId + offset[offsetIndex];
                                }//if
                                //
                                itemCreationId++;
                            }//for
                        }//scope
                        
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
    
    /** TODOs inside Code marking code NOT ready. */
    @Test
    public void tol_4s_containsSuspiciousTodoMarks(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeName, requestedRefTypeWithPath, true );
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
    
    /** Stress-Test: parameter is null" */
    @Test
    public void tol_4s_behavior_parameter_null_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeName, requestedRefTypeWithPath, false );
                    //
                    // start of actual test
                    try{
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        boolean expectedExceptionOccured = false;
                        try{
                            final Quintet_I computedResult = itemProcessor.process( null );
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
    
    
    /** Funktions-Test: combined focus on reset() ;  dieser Test k&ouml;nnte l&auml;nger dauern (abh&auml;ngig vom Rechner so ca. 10[s] ?)*/
    @Test
    public void tol_4s_behavior_reset_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( 15_000 ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeName, requestedRefTypeWithPath, true );
                    //
                    // start of actual test
                    try{
                        final int numberOfRuns = 42;
                        final int differentValues = 4583;                                   //4583 > 4575 = 61*3*5 *5
                        final int numberOfIterationsPerRun = differentValues * 4;
                        final ItemFabric itemFabric = new ItemFabric( differentValues, true );
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        int creationId=0;
                        int expectedItemsProcessed = 0;
                        for( int testRun=0; testRun<numberOfRuns; testRun++ ){
                            itemProcessor.reset();
                            expectedItemsProcessed = 0;
                            assertEquals( expectedItemsProcessed, itemProcessor.itemsProcessed() );
                            do {
                                final Item currentItem = itemFabric.createDeterministicItem( creationId );
                                assertEquals( expectedItemsProcessed, itemProcessor.itemsProcessed() );
                                final Quintet_I computedResult = itemProcessor.process( currentItem );
                                expectedItemsProcessed++;
                                assertEquals( expectedItemsProcessed, itemProcessor.itemsProcessed() );
                                assertEquals( 0, itemProcessor.quintetsFound() );
                                assertNull( computedResult, String.format(
                                    "null was expected ;  last incoming item was %s ;  unexpectly null was NOT computed/reported; reported quintet is %s",
                                    currentItem,
                                    convertQuintetToString( computedResult )
                                ));
                                creationId++;
                            }while( 0 != creationId % numberOfIterationsPerRun );
                        }//for
                        final Item currentItem = itemFabric.createDeterministicItem( creationId );
                        //
                        // was quintet computed ?
                        final Quintet_I computedResult = itemProcessor.process( currentItem );
                        expectedItemsProcessed++;
                        assertEquals( expectedItemsProcessed, itemProcessor.itemsProcessed() );
                        assertEquals( 1, itemProcessor.quintetsFound() );
                        assertNotNull( computedResult, String.format(
                            "quintet was expected  but null was computed ;  last incoming item was %s",
                            currentItem
                        ));
                        //
                        // was correct quintet computed ?
                        final Item reported1stItem = computedResult.getItem(0);
                        final Item reported2ndItem = computedResult.getItem(1);
                        final Item reported3rdItem = computedResult.getItem(2);
                        final Item reported4thItem = computedResult.getItem(3);
                        final Item reported5thItem = computedResult.getItem(4);
                        assert creationId >= 4*differentValues : "Uuuupppss : internal programming/test setup error";
                        final Item expected1stItem = itemFabric.createDeterministicItem( creationId - 4*differentValues );
                        final Item expected2ndItem = itemFabric.createDeterministicItem( creationId - 3*differentValues );
                        final Item expected3rdItem = itemFabric.createDeterministicItem( creationId - 2*differentValues );
                        final Item expected4thItem = itemFabric.createDeterministicItem( creationId - 1*differentValues );
                        final Item expected5thItem = itemFabric.createDeterministicItem( creationId - 0*differentValues );
                        assertArrayEquals(
                            new Item[]{ expected1stItem, expected2ndItem, expected3rdItem, expected4thItem, expected5thItem },
                            new Item[]{ reported1stItem, reported2ndItem, reported3rdItem, reported4thItem, reported5thItem },
                            "quintet is NOT as expected"
                        );
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
    
    
    /** Funktions-Test: combined, focus on process() */
    @Test
    public void tol_4s_behavior_combined_no1(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        //
        final String requestedRefTypeName = "ItemProcessor";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        //
        assertTimeoutPreemptively(
            Duration.ofMillis( commonDefaultLimit ),
            new Executable(){                                                   // Executable is executed in a different thread
                @Override
                public void execute() throws Throwable {
                    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                    
                    doGuardTest_general( requestedRefTypeName, requestedRefTypeWithPath, true );
                    //
                    // start of actual test
                    try{
                        final int[] delta = { 7, 5, 3, 2, 2, 3, 5, 7, 11, 13, 3, 2, 1, 2, 3, 1, 17, 1, 1, 1, 19, 3, 2, 2, 3, 23, 29, 31, 1, 1, 1, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 141 };
                        final int[] offset = new int[delta.length];
                        offset[0]=delta[0];
                        for( int i=1; i<delta.length; i++ )  offset[i] = offset[i-1] + delta[i];
                        //
                        final int differentValues = 911;                                    //911 <~ 915 = 3*5*61   &   911 < 915  !
                        final ItemFabric itemFabric = new ItemFabric( differentValues, true );
                        final ItemProcessor_I itemProcessor = (ItemProcessor_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                        
                        int expectedItemsProcessed = 0;
                        int expectedQuintetsFound = 0;
                        int itemCreationId;
                        /*scope*/{
                            final int numberOfIterations = differentValues * 3;
                            for( itemCreationId=0;  itemCreationId<numberOfIterations;  itemCreationId++ ){
                                final Item currentItem = itemFabric.createDeterministicItem( itemCreationId );
                                final Quintet_I computedResult = itemProcessor.process( currentItem );
                                expectedItemsProcessed++;
                                //
                                assertEquals( expectedItemsProcessed, itemProcessor.itemsProcessed() );
                                assertEquals( expectedQuintetsFound, itemProcessor.quintetsFound() );
                                assertNull( computedResult, String.format(
                                    "null was expected ;  last incoming item was %s ;  unexpectly null was NOT computed/reported; reported quintet is %s",
                                    currentItem,
                                    convertQuintetToString( computedResult )
                                ));
                            }//for
                        }//scope
                        //\=> itemCreationId contains item-creation-id of next item to be created
                        
                        /*scope*/{
                            final int itemCreationBasisId = itemCreationId;
                            int deltaIndex = 0;
                            int localIndex = delta[deltaIndex];
                            while( differentValues > localIndex ){
                                final Item currentItem = itemFabric.createDeterministicItem( itemCreationBasisId + localIndex );
                                final Quintet_I computedResult = itemProcessor.process( currentItem );
                                expectedItemsProcessed++;
                                //
                                assertEquals( expectedItemsProcessed, itemProcessor.itemsProcessed() );
                                assertEquals( expectedQuintetsFound, itemProcessor.quintetsFound() );
                                assertNull( computedResult, String.format(
                                    "null was expected ;  last incoming item was %s ;  unexpectly null was NOT computed/reported; reported quintet is %s",
                                    currentItem,
                                    convertQuintetToString( computedResult )
                                ));
                                //
                                deltaIndex++;
                                assert deltaIndex < delta.length : "UUUUpppss : internal ERROR";
                                localIndex += delta[deltaIndex];
                            }//while
                            itemCreationId += differentValues;
                        }//scope
                        //\=> itemCreationId contains item-creation-id of next item to be created
                        
                        /*scope*/{
                            final int itemCreationBasisId = itemCreationId;
                            int offsetIndex = 0;
                            int itemCreationIdRelatedToExpectedQuintet = itemCreationBasisId + offset[offsetIndex];
                            //
                            for( int localIndex=0;  localIndex<differentValues;  localIndex++ ){
                                final Item currentItem = itemFabric.createDeterministicItem( itemCreationId );
                                final Quintet_I computedResult = itemProcessor.process( currentItem );
                                expectedItemsProcessed++;
                                //
                                assertEquals( expectedItemsProcessed, itemProcessor.itemsProcessed() );
                                if( itemCreationId < itemCreationIdRelatedToExpectedQuintet ){
                                    //\=> NO quintet expected
                                    assertEquals( expectedQuintetsFound, itemProcessor.quintetsFound() );
                                    assertNull( computedResult, String.format(
                                        "null was expected ;  last incoming item was %s ;  unexpectly reported quintet is %s",
                                        currentItem,
                                        convertQuintetToString( computedResult )
                                    ));
                                }else{
                                    //\=> localIndex == localItemCreationIdRelatedToExpectedQuintetlocalIndex   => quintet expected
                                    //
                                    expectedQuintetsFound++;
                                    //
                                    // was quintet computed ?
                                    assertEquals( expectedQuintetsFound, itemProcessor.quintetsFound() );
                                    assertNotNull( computedResult, String.format(
                                        "quintet was expected  but null was computed ;  last incoming item was %s",
                                        currentItem
                                    ));
                                    //
                                    // was correct quintet computed ?
                                    final Item reported1stItem = computedResult.getItem(0);
                                    final Item reported2ndItem = computedResult.getItem(1);
                                    final Item reported3rdItem = computedResult.getItem(2);
                                    final Item reported4thItem = computedResult.getItem(3);
                                    final Item reported5thItem = computedResult.getItem(4);
                                    final Item expected1stItem = itemFabric.createDeterministicItem( offset[offsetIndex] + 0*differentValues );
                                    final Item expected2ndItem = itemFabric.createDeterministicItem( offset[offsetIndex] + 1*differentValues );
                                    final Item expected3rdItem = itemFabric.createDeterministicItem( offset[offsetIndex] + 2*differentValues );
                                    final Item expected4thItem = itemFabric.createDeterministicItem( offset[offsetIndex] + 3*differentValues );
                                    final Item expected5thItem = itemFabric.createDeterministicItem( offset[offsetIndex] + 4*differentValues );
                                    assertArrayEquals(
                                        new Item[]{ expected1stItem, expected2ndItem, expected3rdItem, expected4thItem, expected5thItem },
                                        new Item[]{ reported1stItem, reported2ndItem, reported3rdItem, reported4thItem, reported5thItem },
                                        "quintet is NOT as expected"
                                    );
                                    //
                                    // prepare for next test
                                    offsetIndex++;
                                    itemCreationIdRelatedToExpectedQuintet = itemCreationBasisId + offset[offsetIndex];
                                }//if
                                //
                                itemCreationId++;
                            }//for
                        }//scope
                        
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
            dbManager.enterLocally( new TestTopic( TL.D, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsTwoPoints ));
        }//if
    }//method()
    
}//class
