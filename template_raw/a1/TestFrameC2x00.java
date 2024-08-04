package template_raw.a1;


import static _untouchable_.supportC2x00.Configuration.dbgConfigurationVector;
import static _untouchable_.supportC2x00.Herald.Medium.*;
import static _untouchable_.supportC2x00.PointDefinition.countsOnePoint;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
//
import java.lang.reflect.Modifier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import _untouchable_.supportC2x00.EnvironmentAnalyzer;
import _untouchable_.supportC2x00.Herald;
import _untouchable_.supportC2x00.TC;
import _untouchable_.supportC2x00.TE;
import _untouchable_.supportC2x00.TL;
import _untouchable_.supportC2x00.TS;
import _untouchable_.supportC2x00.TestResult;
import _untouchable_.supportC2x00.TestResultDataBaseManager;
import _untouchable_.supportC2x00.TestSupportException;
import _untouchable_.supportC2x00.TestTopic;


/**
 * LabExam1113_4XIB1-P1    (PTP-Eclipse)<br />
 * <br />
 * Diese Sammlung von Tests soll nur die Sicherheit vermitteln, dass Sie die Aufgabe richtig verstanden haben.
 * Dass von den Tests dieser Testsammlung keine Fehler gefunden wurden, kann nicht als Beweis dienen, dass der Code fehlerfrei ist.
 * Es liegt in Ihrer Verantwortung sicherzustellen, dass Sie fehlerfreien Code geschrieben haben.
 * Bei der Bewertung werden u.U. andere - konkret : modifizierte und haertere Tests - verwendet.
 * <br />
 * <br />
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1215_4XIB1-P1_211v10_210705_v01
 */
//@TestMethodOrder( MethodOrderer.Alphanumeric.class )
@TestMethodOrder( MethodOrderer.MethodName.class )
public class TestFrameC2x00 {
    
    // constant(s)
    
    // limit for test time
    final static private int commonLimit = 4_000;                               // timeout resp. max. number of ms for test - attention unit is defined in @Timeout
    
    // exercise related
    final static private TE exercise = TE.A1;
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
        // THIS METHOD HAS AN INDIVIDUAL IMPLEMENTATION BODY ON PURPOSE
        //
        System.out.printf( "Informationen zum Environment:\n" );
        System.out.printf( "    O.-P.:      %s\n",          new Object().getClass().getPackage() );
        System.out.printf( "    Java (v1):  %s bzw. %s\n",  System.getProperty( "java.specification.version" ), System.getProperty( "java.version" ) );
        System.out.printf( "    Java (v2):          %s\n",  EnvironmentAnalyzer.getJavaVersion() );
        System.out.printf( "    JUnit5/Jupiter:     %s\n",  EnvironmentAnalyzer.getJUnitJupiterVersion() );
        System.out.printf( "    JUnit5/Platform:    %s\n",  EnvironmentAnalyzer.getJUnitPlatformVersion() );
        System.out.printf( "    #Cores:             %d\n",  EnvironmentAnalyzer.getAvailableCores() );
        System.out.printf( "    ------------\n" );
        System.out.printf( "    WPI computer :                                              %s\n",    _untouchable_.supportC2x00.CentralVersionData.centralWPIComputerVersionID );
        System.out.printf( "    Engine   (test support routine collection) :                %s\n",    _untouchable_.supportC2x00.CentralVersionData.centralTestSupportVersionID );
        System.out.printf( "    Test     (test defined by version ID) :                     %s\n",    _untouchable_.supportC2x00.CentralVersionData.centralLabExamVersionID );
        System.out.printf( "    Exam     (test defined by path) :                           %s%s\n",  TestFrameC2x00.class.getProtectionDomain().getCodeSource().getLocation().getPath(), packagePath );
        System.out.printf( "    Exercise (test defined by internal test definition/enum) :  %s\n",    exercise );
        System.out.printf( "\n" );
        System.out.printf( "################################################################################\n" );
        System.out.printf( "\n\n" );
        
        guaranteeExerciseConsistency( exercise.toString().toLowerCase(), packagePath );
        if( enableAutomaticEvaluation ){
            dbManager = new TestResultDataBaseManager( exercise, rootPackageName );
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
              + "    Have you already done \"%s\" in a proper way?\n"
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
    
    
    
    
    
    
    
    
    
    
    //--------------------------------------------------------------------------
    
    /** Ausgabe auf Bildschirm zur visuellen Kontrolle (fuer Studenten idR. abgeschaltet, wird aber IMMER mit angestartet). */
    @Test
    @Timeout( value = commonLimit, unit = MILLISECONDS )
    public void tol_0i_printExamineeInfoForManualReview(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final boolean dbgLocalOutputEnable = TS.checkTestConfiguration( 0x0100 );
        if( dbgLocalOutputEnable ){
            System.out.printf( "\n\n" );
            System.out.printf( "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv\n" );
            System.out.printf( "%s():\n",  testName );
            System.out.printf( "\n\n" );
            
            
            final String requestedRefTypeName = "ExamineeInfo";
            final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
            try{
                final ExamineeInfo_I examineeInfo = (ExamineeInfo_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
                
                final int snl = examineeInfo.getExamineeSurName().length();
                final int fnl = examineeInfo.getExamineeFirstName().length();
                final int reservedSpace = ((snl<fnl) ? fnl : snl);
                
                // print examinee information
                System.out.printf( "%67s", "" );    for( int i=reservedSpace; --i>=0; ){ System.out.printf( "v" ); }    System.out.printf( "\n" );
                System.out.printf( "Nachname / Familienname bzw. surname / family name / last name :   %-"+(reservedSpace+3)+"s<<<===\n",  examineeInfo.getExamineeSurName() );
                System.out.printf( "Vorname / Rufname       bzw. first name                        :   %-"+(reservedSpace+3)+"s<<<===\n",  examineeInfo.getExamineeFirstName() );
                System.out.printf( "%67s", "" );    for( int i=reservedSpace; --i>=0; ){ System.out.printf( "^" ); }    System.out.printf( "\n" );
                System.out.printf( "\n\n" );
                
                // print info about class
                TS.printDetailedInfoAboutClass( requestedRefTypeWithPath );
                System.out.printf( "\n" );
                // print info about object generated from class
                TS.printDetailedInfoAboutObject( examineeInfo, "examineeInfo" );
                //
                // print info about toString()
                if( TS.isActualMethod( examineeInfo.getClass(), "toString", String.class, null )){
                    System.out.printf( "~.toString(): \"%s\"     again ;-)\n",  examineeInfo.toString() );
                }else{
                    System.out.printf( "NO! toString() implemented by class \"%s\" itself\n",  examineeInfo.getClass().getSimpleName() );
                }//if
                System.out.printf( "\n\n" );
                
                System.out.printf( "%s_%s\n",  examineeInfo.getExamineeSurName(), examineeInfo.getExamineeFirstName() );
            }catch( final TestSupportException ex ){
                ex.printStackTrace();
                fail( ex.getMessage() );
            }//try
            
            System.out.printf( "\n" );
            System.out.printf( "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" );
            System.out.printf( "\n\n" );
        }//if
        
        // at least the unit test was NOT destroyed by student ;-)
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.I, exercise, TC.DBGPRINT ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()  
    
    
    
    /** Existenz-Test: "ExamineeInfo". */
    @Test
    @Timeout( value = commonLimit, unit = MILLISECONDS )
    public void tol_0i_classExistence_ExamineeInfo(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "ExamineeInfo";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        try{
            @SuppressWarnings("unused")
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.I, exercise, TC.EXISTENCE ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Test einiger Eigenschaften des Referenz-Typs "ExamineeInfo". */
    @Test
    @Timeout( value = commonLimit, unit = MILLISECONDS )
    public void tol_0i_properties_ExamineeInfo(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "ExamineeInfo";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            //
            assertTrue( TS.isClass(             classUnderTest ),                      String.format( "requested class %s missing", requestedRefTypeName ));
            assertTrue( TS.isImplementing(      classUnderTest,   "ExamineeInfo_I" ),  "requested supertype missing");
            assertTrue( TS.isClassPublic(       classUnderTest ),                      "false class access modifier" );
            assertTrue( TS.isConstructor(       classUnderTest ),                      "requested constructor missing");
            assertTrue( TS.isConstructorPublic( classUnderTest ),                      "false constructor access modifier");
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.I, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Test: Eigenschaften "ExamineeInfo" - Existenz & Access Modifier Methoden. */
    @Test
    @Timeout(value = commonLimit, unit = MILLISECONDS)
    public void tol_0i_propertiesMethods_ExamineeInfo(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "ExamineeInfo";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            assertTrue( TS.isFunction(                           classUnderTest, "getExamineeSurName",   String.class ),                     "requested method missing" );
            assertTrue( TS.isFunction(                           classUnderTest, "getExamineeFirstName", String.class ),                     "requested method missing" );
            assertTrue( TS.isRequestedFunctionAccessModifierSet( classUnderTest, "getExamineeSurName",   String.class,   Modifier.PUBLIC ),  "false method access modifier" ); // -D interface ;-)
            assertTrue( TS.isRequestedFunctionAccessModifierSet( classUnderTest, "getExamineeFirstName", String.class,   Modifier.PUBLIC ),  "false method access modifier" ); // -D interface ;-)
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.I, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Test: Eigenschaften "ExamineeInfo" - Access Modifier Variablen. */
    @Test
    @Timeout( value = commonLimit, unit = MILLISECONDS )
    public void tol_0i_propertiesFields_ExamineeInfo(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "ExamineeInfo";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            assertTrue( TS.allVariableFieldAccessModifiersPrivate( classUnderTest ), "false field access modifier" );
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.I, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Test: Eigenschaften "ExamineeInfo" - Schreibweise Methoden. */
    @Test
    @Timeout( value = commonLimit, unit = MILLISECONDS )
    public void tol_0i_notationMethods_ExamineeInfo(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "ExamineeInfo";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            final String faultExample = TS.hasInvalidMethodNotation( classUnderTest );
            if( null != faultExample ){
                fail( String.format( "method name: \"%s\" does NOT follow the VERY JAVA LAW !",  faultExample ));
            }//if
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.I, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Test: Eigenschaften "ExamineeInfo" - Schreibweise Variablen. */
    @Test
    @Timeout( value = commonLimit, unit = MILLISECONDS )
    public void tol_0i_notationFields_ExamineeInfo(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "ExamineeInfo";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        try{
            final Class<?> classUnderTest = Class.forName( requestedRefTypeWithPath );
            final String faultExample = TS.hasInvalidFieldNotation( classUnderTest );
            if( null != faultExample ){
                fail( String.format( "field name: \"%s\" does NOT follow the VERY JAVA LAW !",  faultExample ));
            }//if
        }catch( final ClassNotFoundException ex ){
            fail( String.format( "can NOT find \"%s\" -> %s",  requestedRefTypeName, ex.getMessage() ));
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.I, exercise, TC.PROPERTIES ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    /** Grundsaetzlicher Test: ExamineeInfo erzeugen. */
    @Test
    @Timeout( value = commonLimit, unit = MILLISECONDS )
    public void tol_0i_objectCreation_ExamineeInfo(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "ExamineeInfo";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        try{
            @SuppressWarnings("unused")
            final ExamineeInfo_I examineeInfo = (ExamineeInfo_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.I, exercise, TC.CREATION ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
     
    
    
    /** Einfacher Test: Funktion "ExamineeInfo". */
    @Test
    @Timeout( value = commonLimit, unit = MILLISECONDS )
    public void tol_0i_behavior_ExamineeInfo(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "ExamineeInfo";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        try{
            final ExamineeInfo_I examineeInfo = (ExamineeInfo_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            
            final Pattern pattern = Pattern.compile( "^[a-z]+$" );
            final Matcher matcherSurName = pattern.matcher( examineeInfo.getExamineeSurName() );
            assertTrue( matcherSurName.find() );
            final Matcher matcherFirstName = pattern.matcher( examineeInfo.getExamineeFirstName() );
            assertTrue( matcherFirstName.find() );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.I, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
    
    
    /** Konsistenz-Test: Entspricht "Root-Package-Name" ExamineeInfo. */
    @Test
    @Timeout( value = commonLimit, unit = MILLISECONDS )
    public void tol_0i_consistency_ExamineeInfo(){
        final String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        
        final String requestedRefTypeName = "ExamineeInfo";
        final String requestedRefTypeWithPath = packagePath+"."+requestedRefTypeName;
        try{
            final ExamineeInfo_I examineeInfo = (ExamineeInfo_I)( TS.generateRequestedObject( requestedRefTypeWithPath ));
            final String examineeName = examineeInfo.getExamineeSurName() + "_" + examineeInfo.getExamineeFirstName();
            assertEquals( rootPackageName, examineeName );
        }catch( final TestSupportException ex ){
            fail( ex.getMessage() );
        }//try
        
        if( enableAutomaticEvaluation ){
            dbManager.enterLocally( new TestTopic( TL.I, exercise, TC.BEHAVIOR ),  new TestResult( testName, countsOnePoint ));
        }//if
    }//method()
    
}//class
