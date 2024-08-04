package _untouchable_.supportC2x00;


import static _untouchable_.supportC2x00.Configuration.dbgConfigurationVector;
import static _untouchable_.supportC2x00.Herald.Medium.*;

//
//
import java.util.HashMap;
import java.util.Map;


/**
 * LabExam11xx_4XIB1-P1    (PTP/Eclipse)<br />
 *<br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * Test Result Analyzer ...
 *<br />
 *<br />
 * VCS: see ReadMe.txt
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  210803 #01
 */
public class TestResultAnalyzer {
    
    final private boolean dbgLocalCompensationInformationOutputEnable = ( 0 != ( dbgConfigurationVector & 0x0040_0000 ));
    final private boolean dbgShowAlternativeWpiUnderDevelopmentEnable = ( 0 != ( dbgConfigurationVector & 0x0080_0000 ));
    
    final private TestResultTable theTable;                                     // package scope on purpose
    
    
    
    
    
    /**
     * Constructs a test result table.
     *
     * @param testResultTable   the test result table that is analyzed
     */
    TestResultAnalyzer( final TestResultTable testResultTable ){                // package scope on purpose
        theTable = testResultTable;
    }//constructor()
    
    
    
    
    
    /**
     * Print detailed performance on screen (as performance is resulting out of test result table)
     */
    public void printPerformanceDetailed(){
        //
        // for each test level: e.g. I, A, B, C, D
        for( final TL level : TL.values() ){
            int sumExpectedPointsPerLevel = 0;
            int sumActualPointsPerLevel = 0;
            //
            // for each exercises: e.g. a1, a2, a3, a4, a5
            for( final TE exercise : TE.values() ){
                int points = theTable.getPoints( exercise, level );
                sumActualPointsPerLevel += points;
                
                // do some checks & printing
                final Integer hundredPercent = Configuration.valuationTable.get( new ValuationUnit( level, exercise ));
                if( null!=hundredPercent ){
                    sumExpectedPointsPerLevel += hundredPercent;
                    if( points > hundredPercent ){
                        throw new IllegalStateException( String.format(
                            "INTERNAL ERROR - it was NOT expected to end up \"HERE\" - call schaefers  ->  %s: %d > %d",
                            level,
                            points,
                            hundredPercent
                        ));
                    }//if
                    if( 0 < hundredPercent ){
                        System.out.printf(
                            "%s %s  -> %3d /%3d  ->  %6.2f%%\n",
                            exercise,
                            level,
                            points,
                            hundredPercent,
                            100.0 * points / hundredPercent
                        );
                    }//if
                }//if
            }//for
            System.out.printf(                                                  //  changed @18/01/15 to improve readability
                "====>>>> %3d /%3d\n",                                          //  "====>>>> %3d /%3d  => (%6.2f)\n",
                sumActualPointsPerLevel,                                        //
                sumExpectedPointsPerLevel                                       //
                //                                                              //  100.0 * sumActualPointsPerLevel / sumExpectedPointsPerLevel
            );
        }//for
    }//method()
    
    /**
     * Print performance on screen (as performance is resulting out of test result table)
     *
     * @param testResultMap  ...
     * @param sb  ...
     */
    public void printPerformance(){
        final Map<TL,Fraction> testResultMap = new HashMap<>();                 // required by computeWeakPerformanceIndex()
        final StringBuffer sb = new StringBuffer( "[" );
        //
        // for each test level: e.g. I, A, B, C, D
        for( final TL level : TL.values() ){
            sb.append( " " );
            sb.append( level.toString() );
            final Fraction achievement = theTable.getPortion( level );
            sb.append( String.format( ":%.2f ",  achievement.toPercentagePoint() ));
            testResultMap.put( level, achievement );
        }//for
        final double wpi = computeWeakPerformanceIndex( testResultMap );
        final String wpiAsString = String.format( "%.2f",  wpi );
        if(       6 <= wpiAsString.length() ){                                  //  100.00
            sb.append( String.format( "] =>  >> WPI: %s <<",    wpiAsString ));
        }else if( 5 <= wpiAsString.length() ){                                  //  oXX.XX
            sb.append( String.format( "] =>  >> WPI: _%s <<",   wpiAsString ));
        }else{                                                                  //  ooX.XX
            sb.append( String.format( "] =>  >> WPI: __%s <<",  wpiAsString ));
        }//if
        //
        if( dbgShowAlternativeWpiUnderDevelopmentEnable ){
            sb.append( "\n" );
            sb.append( "TRIAL-WPI(s) =>  " );
            final double[] alternativeWpiResult = computeNewWPI( testResultMap );
            for ( final double awpi : alternativeWpiResult ){
                sb.append( String.format( "   %6.2f",  awpi ));
            }//for
        }//if
        System.out.printf( "%s\n",  sb.toString() );
        System.out.flush();
    }//method()
    
    
    
    // TODO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    // \-> auslagern der eigentlich Berechnung zur besseren Versionierung
    //##########################################################################
    //###
    //###   WPI computation
    //###
    //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //
    // Ueberlegungen auf denen die Bewertung basiert:
    // ==============================================
    //
    // Es ist nur schwer vorstellbar, dass in einer Aufgabe "4s"-Punkte erzielt werden ohne die zugehoerigen "1e"-&"2b"-Punkte (Ohne "3n"-Punkte bedingt moeglich).
    // Es ist nur schwer vorstellbar, dass in einer Aufgabe "3n"-Punkte erzielt werden ohne die zugehoerigen "1e"-&"2b"-Punkte.
    // Kompensation erfolgt (also) nur fuer fehlende Punkte in anderen Aufgabenteilen.
    //
    // Wer 3 von 4 Aufgaben 100% richtig hat, aber die 4.Aufgabe gar nicht, der sollte bestanden haben.
    // "Durchfallszenarien" solten also nicht mehr WPI erzielen.
    // Nur mit ueberall "1e"-&"2b"-Punkte darf man NICHT bestehen.
    //
    // Licht im Dunkel des Namens-Wirrwarrs (bzgl. der Level):
    //  Level I = Level 0i          Idee: Information bzw. Student kann sich fehlerfrei identifizieren
    //  Level A = Level 1e          Idee: Existenz bzw. die Dinge existieren.
    //  Level B = Level 2b          Idee: Basic bzw. Anstarten (der Methoden) ohne Absturz moeglich
    //  Level C = Level 3n          Idee: Normal bzw. "Laeuft" mit einfachen bis leicht komplizierten Eingaben (die zulaessig sind) 
    //  Level D = Level 4s          Idee: Speziell bzw. extrem komplizierte oder unzulaessige Eingaben 
    //  --------------------------------
    //  Level 1 = Level A + Level B
    //  Level 2 = Level C
    //  Level 3 = Level D
    //  Bemerkung: Ohne 100% in Level I / 0i geht eh ueberhaupt nichts ;-)
    //
    //
    //
    //
    /**
     * Version ID of WPI computer
     */
    final static String wpiComputerVersionID = "2017/02/03 v2.91  -->>  [#5]";
    //
    //
    //
    //
    // HISTORY:
    // ========
    //
    // since:   170203 | 170127 160710 160709 160707
    // ------
    // fL1/fA ---+o.35 |   o.25   o.35   o.4    o.4    ( 160707-160710 was fA )
    // fL1/fB --/      |   o.1o   ----   ----   ----
    // fL2/fC     o.5o |   o.5o   o.5o   o.45   o.45   ( 160707-160710 was fB )
    // fL3/fD     o.15 |   o.15   o.15   o.15   o.15   ( 160707-160710 was fC )
    // ------
    // cL1xL3     2.34     2.oo   2.oo  10.oo   ?.??   ( 160707-160710 was fABXD, fAC )
    // cL1xL2     2.oo    10.oo  10.oo  25.oo   ?.??   ( 160707-160710 was fABXC  fAB )
    //
    // there is NO compensation fBC ,  since fC is smaller than fB anyway
    //
    // this method is related to the very actual lab exam and has to be adapted for each lab exam
    // NO MORE static since state is accessed for debugging purposes
    private double computeWeakPerformanceIndex( final Map<TL,Fraction> testResultMap ){
        final double fL1 = 0.35;                                                // <<-- HERE: configuration of WPI computation
        final double fL2 = 0.5;                                                 // <<-- HERE: configuration of WPI computation
        final double fL3 = 0.15;                                                // <<-- HERE: configuration of WPI computation
        if( ! aboutEqual( 1.0, fL1+fL2+fL3 )){
            Herald.proclaimMessage( SYS_ERR, "[[[[>>>> ATTENTION : INTERNAL setup ERROR <<<<]]]]" );
        }//if
        //
        final double cL1xL2 = 2.0;                                              // <<-- HERE: configuration of WPI computation - factor for compensation of missing A points with B points
        //\=> "penalty per point":       2.0 * (fL2/fL1)  ~> 2,857
        final double cL1xL3 = fL1/fL3;                  //~> 2,334              // <<-- HERE: configuration of WPI computation - factor for compensation of missing A points with C points
        //\=> "penalty per point": (fL1/fL3) * (fL3/fL1)  ~> 1,00
        // there is NO compensation fBC ,  since fC is smaller than fB anyway
        
        final double pI = testResultMap.get( TL.I ).toPercentagePoint();
        final double pA = testResultMap.get( TL.A ).toPercentagePoint();
        final double pB = testResultMap.get( TL.B ).toPercentagePoint();
        final double pC = testResultMap.get( TL.C ).toPercentagePoint();
        final double pD = testResultMap.get( TL.D ).toPercentagePoint();
        //
        double level0 = pI;
        double level1 = (pA+pB) - 100.0;
        double level2 = pC;
        double level3 = pD;
        //
        if( dbgLocalCompensationInformationOutputEnable ){
            //__SCHMUDDEL___vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv  
            for( final TE exercise : TE.values() ){
                if( TE.A1 != exercise ){
                    //\=> a2,a3,a4,a5
                    final int[] points = new int[ TL.values().length ];
                    for( final TL level : TL.values() ){
                        //\=> I/0i,A/1e,B/2b,C/3n,D/4s
                        //
                        final int levelIndex = level.ordinal();
                        points[levelIndex] = theTable.getPoints( exercise, level );
                    }//for
                    
                    //------------------------------->   X/0i A/1e B/2b C/3n D/4s       // first entry is never used
                    final double percentageOnXABCD[] = { 0.0, 0.0, 0.0, 0.0, 0.0 };     // it's just for convenience ;-)
                    for( int i=1; i<=4; i++ ){
                        final Integer pointsForHundredPercent = Configuration.valuationTable.get( new ValuationUnit( TL.values()[i], exercise ));
                        if( null!=pointsForHundredPercent ){
                            percentageOnXABCD[i] = 1.0*points[i]/pointsForHundredPercent;
                        }//if
                    }//for
                    
                    final double lpol1 = percentageOnXABCD[1] + percentageOnXABCD[2];   // Local Percantage On Level 1 - better name required
                    final double lpol2 = percentageOnXABCD[3];                          // Local Percantage On Level 2 - better name required
                    final double lpol3 = percentageOnXABCD[4];                          // Local Percantage On Level 3 - better name required
                    //
                    if( lpol1 < lpol2 ){
                        Herald.proclaimMessage( SYS_ERR, String.format(
                            "@%s:  level1=%6.2f < %6.2f=level2",
                            exercise, lpol1, lpol2
                        ));
                    }//if
                    if( lpol1 < lpol3 ){
                        Herald.proclaimMessage( SYS_ERR, String.format(
                            "@%s:  level1=%6.2f < %6.2f=level3",
                            exercise, lpol1, lpol3
                        ));
                    }//if
                    if( lpol2 < lpol3 ){
                        Herald.proclaimMessage( SYS_ERR, String.format(
                            "@%s:  level2=%6.2f < %6.2f=level3",
                            exercise, lpol2, lpol3
                        ));
                    }//if
                }//if
            }//for
            //--SCHMUDDEL---^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 
        }//if
        
        
        double wpi;
        double delta;
        //
        if( 99.999999 >= level0 ){
            //\=> examinee info is NOT ok, hence unsuccessfull
            wpi = 0.0;
            
            
        }else{
            //\=> at least examinee info is ok
            if( 99.999999 >= level1 ){
                //\=> NOT all indispensable elementary/basic exercises were solved
                //
                // take level3 points first (it is assumed that only neglecteble level3 points are gained without related level2 points
                delta = cL1xL3 * ( 100.0 - level1 );
                if( level3 >= delta ){
                    //\=> enough D/level3 to compensate missing A+B/level1
                    level3 -= delta;
                    level1 = 100.0;
                }else{
                    //\=> NOT enough C/level2 to compensate missing A+B/level1
                    level1 += level3 / cL1xL3;
                    level3 = 0.0;
                    
                    delta = cL1xL2 * ( 100.0 - level1 );
                    if( level2 >= delta ){
                        //\=> enough C/level2 to compensate missing A+B/level1
                        level2 -= delta;
                        level1 = 100.0;
                    }else{
                        //\=> NOT enough C/level2 to compensate missing A+B/level1
                        level1 += level2 / cL1xL2;
                        level2 = 0.0;
                    }//if
                }//if
            }//if
            if( dbgLocalCompensationInformationOutputEnable ){
                if( 99.999999 >= level1 ){
                    Herald.proclaimMessage( SYS_ERR,  "NOT ENOUGH   level-1 / A+B / \"1e\"+\"2b\"   points  !!!" );
                }//if
            }//if
            wpi  =  level1 * fL1  +  level2 * fL2  +  level3 * fL3;
        }//if
        if( dbgLocalCompensationInformationOutputEnable ){
            System.out.printf( "\n" );
            System.out.printf(
                "wpi=%6.2f    <-    pI=%6.2f  pA=%6.2f  pB=%6.2f  pC=%6.2f  pD=%6.2f    l1=%6.2f  l2=%6.2f  l3=%6.2f\n",
                wpi,
                pI, pA, pB, pC, pD,
                level1, level2, level3
            );
            System.out.printf( "\n" );
        }//if
        if( 2.0 > wpi ){
            //\=> scale calues below 2 into range [0,..,2]
            if( 0.0 > wpi ){
                //\=> [-35,..,0] -> [0,..,+1]
                wpi = 1.0 + (wpi / 35.0);
            }else{
                //\=> [0,..,2] -> [1,..,2]
                wpi = 1.0 + (wpi / 2.0);
            }//if
        }//if
        return wpi;
    }//method()
    //
    private static boolean aboutEqual( final double value1,  final double value2 ){
        final double epsilon = 0.001;
        
        return (( value1 < value2+epsilon ) && ( value1 > value2-epsilon ));
    }//method()
    //
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    
    
    
    
    private double[] computeNewWPI( final Map<TL,Fraction> testResultMap ){
        final double pI = testResultMap.get( TL.I ).toPercentagePoint();
        final double pA = testResultMap.get( TL.A ).toPercentagePoint();
        final double pB = testResultMap.get( TL.B ).toPercentagePoint();
        final double pC = testResultMap.get( TL.C ).toPercentagePoint();
        final double pD = testResultMap.get( TL.D ).toPercentagePoint();
        /*
        final double level0 = pI;
        final double level1 = (pA+pB);
        final double level2 = pC;
        final double level3 = pD;
        */
        
        final AlternativeWpiFormula[] awpif = {
            //                         __cA_  __cB_  __cC_   expo
            new AlternativeWpiFormula(  8.80,  4.20,  1.00,  2.40 ),            // 210729 v3.00
            new AlternativeWpiFormula( 29.20, 24.40,  3.50,  2.40 ),            // 210731 v3.01
            new AlternativeWpiFormula( 11.20,  5.60,  1.00,  2.60 ),            // 210802 v3.03
            new AlternativeWpiFormula( 59.20, 29.60,  3.80,  3.60 )             // 210801 v3.02
        };
        final double[] wpi = new double[awpif.length];
        
        for( int i=0; i<awpif.length; i++ ){
            wpi[i] = awpif[i].compute( pI,  pA, pB,  pC,  pD );
        }//for
        
        return wpi;
    }//method()
    
}//class
//
//
//
//
//
// Namen für die Level in den verschiedenen Varianten ihres Auftretens
// ===================================================================
//
// Level_____________________vv  (method prefix)
// Level____    internal_    ID
//
// Level0/L0    I/pI/TL.I    0i  (tol_0i_*)    Information/Identification (about/of examinee) - precondition for any other test
//
// Level1/L1    A/pA/TL.A    1e  (tol_1e_*)    Elementary
// Level1/L1    B/pB/TL.B    2b  (tol_2b_*)    Basic
//
// Level2/L2    C/pC/TL.C    3n  (tol_3n_*)    Normal
//
// Level3/L3    D/pD/TL.D    4s  (tol_4s_*)    Sophisticated
//
