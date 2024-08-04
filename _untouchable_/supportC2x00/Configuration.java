package _untouchable_.supportC2x00;


import java.util.HashMap;
import java.util.Map;


/**
 * LabExam1215_4XIB1-P1    (PTP-Eclipse)<br />
 *<br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * Configuration
 *<br />
 *<br />
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1215_4XIB1-P1_211v01_210705_v02
 */
public class Configuration {
    
    /**
     * The debug-output enable vector controls the debug messages printed on screen
     */
    //                                                 vvvv_vvvv__vvvv_vvvv____vvvv_vvvv__vvvv_vvvv
    final static public int dbgConfigurationVector = 0b1000_0000__0000_0000____0000_0000__0000_0011;
    //                                                 +..._....__---._..--____...._..-?__...._.-?+
    //                                                 ^          ^^^    ^^    ^      ^^        ^^^
    //                                                 |          |||    ||    |      ||        |||
    //                       enable automatic test   --/          |||    ||    |      ||        |||
    //                                                            |||    ||    |      ||        |||
    //      show alternative WPI under development   -------------/||    ||    |      ||        |||
    //        wpi related compensation information   --------------/|    ||    |      ||        |||
    //   additional information on $variable names   ---------------/    ||    |      ||        |||
    //                                                                   ||    |      ||        |||
    //                         OBSOLETE/DEPRECATED   --------------------/|    |      ||        |||     <-  was support for BlueJ JUnit annotation bug(fix)
    //                         OBSOLETE/DEPRECATED   ---------------------/    |      ||        |||     <-  was             BlueJ JUnit annotation bug fix
    //                                                                         |      ||        |||
    // (temporary) debug output (for test testing)   --------------------------/      ||        |||
    //                                                                                ||        |||
    //        manual review of basic general stuff(*)---------------------------------/|        |||
    //            printExamineeInfoForManualReview(*)----------------------------------/        |||
    //                                                                                          |||
    //                       detailed result table   -------------------------------------------/||
    //                                result table   --------------------------------------------/|
    //                                         WPI   ---------------------------------------------/
    //
    //
    // 20/06/29: LabExam is Eclipse based - it is very unlikely, that BlueJ is used again (to execute this kind of lab exam)
    // OBSOLETE/DEPRECATED(*) ATTENTION: As result of BlueJ-BUG, this influences the workaround: "numberOfTests" (in TestFrame)
    
    
    
    /**
     * Path to data base respectively name of directory containing test results without database file
     */
    final static String mainPartOfTestResultDataBasePath = "TestResultDatabaseC2x00";               // package scope on purpose
    
    /**
     * (Base) name without extension
     * E.g. there will be ".data" and ".guard"
     */
    final static String mainPartOfTestResultDataBaseFileName = "testResultDB";                      // package scope on purpose
    
    
    /**
     * Version ID of configuration this very Configuration-class with its internal structure respectively possibilities
     */
    final static String configurationVersionID = "2016/12/31 v1.00";

    
    
    
    
    
    //--------------------------------------------------------------------------
    //
    // INTERNAL
    //
    
    /**
     * Table respectively map containing max. point that can be achieved.
     */
    final static Map<ValuationUnit,Integer> valuationTable = new HashMap<ValuationUnit,Integer>(){
        {   //---~~~~~~~~~~~~~~~~~~~VVVVVVVVVVV~~~~VVV~~~
            put( new ValuationUnit( TL.I, TE.A1 ),  10 );
            //------------------------------------------
            put( new ValuationUnit( TL.A, TE.A2 ),  14 );
            put( new ValuationUnit( TL.A, TE.A3 ),   9 );
            put( new ValuationUnit( TL.A, TE.A4 ),   9 );
            put( new ValuationUnit( TL.A, TE.A5 ),  12 );
            //------------------------------------------
            put( new ValuationUnit( TL.B, TE.A2 ),   7 );
            put( new ValuationUnit( TL.B, TE.A3 ),   4 );
            put( new ValuationUnit( TL.B, TE.A4 ),   4 );
            put( new ValuationUnit( TL.B, TE.A5 ),   6 );
            //------------------------------------------
            put( new ValuationUnit( TL.C, TE.A2 ),   5 );
            put( new ValuationUnit( TL.C, TE.A3 ),  41 );
            put( new ValuationUnit( TL.C, TE.A4 ),  10 );
            put( new ValuationUnit( TL.C, TE.A5 ),   8 );
            //------------------------------------------
            put( new ValuationUnit( TL.D, TE.A2 ),   6 );
            put( new ValuationUnit( TL.D, TE.A3 ),   3 );
            put( new ValuationUnit( TL.D, TE.A4 ),   7 );
            put( new ValuationUnit( TL.D, TE.A5 ),   5 );
        }//constructor()
        //
        final static private long serialVersionUID = CentralVersionData.centralTestResultDataBaseRelatedSerialVersionUID;   // __???___<161214>: Is this actually necessary ???
    };//class
    //
    /**
     * Number of levels per exercise (Attention there are different definitions of level - still cleanup required ;-)
     */
    //                                                                             ___1___  ___4____________  ___4____________  ___4____________  ___4____________
    final static int[] ratioVector = { 1, 4, 4, 4, 4 };                         // TL.I:A1, TL.A:A2,A3,A4,A5, TL.B:A2,A3,A4,A5, TL.C:A2,A3,A4,A5, TL.D:A2,A3,A4,A5  used in TestResultTable.printPerformance()
    
}//class
