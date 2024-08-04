package _untouchable_.supportC2x00;


import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * LabExam11xx_4XIB1-P1    (PTP/Eclipse)<br />
 *<br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * Test Result Table is a wrapper that in some way configures a map for the very purpose.
 * The Map contains TestTopic as key and TestResult as data.
 * The idea of usage is that only successfully passed tests are entered into the table.
 * <br />
 * <br />
 * <code>
 *...........................................................<br />
 *.......TestResultTable.....................................<br />
 *......+-------------------------------------------------+..<br />
 *......|..KEY:TestTopic...............VALUE:TestResult...|..<br />
 *......|.+---------------------------+-----------------+.|..<br />
 *......|.|..exercise.level.category..|..weight.method..|.|..<br />
 *......|.|.+--------+-----+--------+ | +------+------+.|.|..<br />
 *......|.|.+.~~~....+.~~~.+.~~~....+ | |.~~~..|.~~~..+.|.|..<br />
 *......|.|.+--------+-----+--------+ | +------+------+.|.|..<br />
 *......|.|.+.~~~....+.~~~.+.~~~....+ | |.~~~..|.~~~..+.|.|..<br />
 *......|.|.+--------+-----+--------+ | +------+------+.|.|..<br />
 *......:.:.:........:.....:........: : :......:......:.:.:..<br />
 *......|.|.+--------+-----+--------+ | +------+------+.|.|..<br />
 *......|.|.+.~~~....+.~~~.+.~~~....+ | |.~~~..|.~~~..+.|.|..<br />
 *......|.|.+--------+-----+--------+ | +------+------+.|.|..<br />
 *......|.+---------------------------+-----------------+.|..<br />
 *......+-------------------------------------------------+..<br />
 *...........................................................<br />
 * </code>
 *<br />
 *<br />
 * VCS: see ReadMe.txt
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  200606 #01
 */
public class TestResultTable implements Serializable {
    
    //--------------------------------------------------------------------------
    // simple map method(s) delegated to map
    
    /**
     * Self-explanatory - see Map.isEmpty()
     */
    boolean isEmpty(){                                                          // package scope on purpose
        return table.isEmpty();
    }//method()
    
    /**
     * Self-explanatory - see Map.clear()
     */
    void clear(){                                                               // package scope on purpose
        table.clear();
    }//method()
    
    /**
     * Self-explanatory - see Map.put()
     *
     * param key    self-explanatory - see Map.put()
     * param value  self-explanatory - see Map.put()
     */
    void put( final TestTopic key,  final Set<TestResult> value ){              // package scope on purpose
        table.put( key, value );
    }//method()
    
    /**
     * Self-explanatory - see Map.keySet()
     *
     * return     self-explanatory - see Map.keySet()
     */
    Set<TestTopic> keySet(){                                                    // package scope on purpose
        return table.keySet();
    }//method()
    
    /**
     * Self-explanatory - see Map.get()
     *
     * param key  self-explanatory - see Map.get()
     * return     self-explanatory - see Map.get()
     */
    Set<TestResult> get( final TestTopic key ){                                 // package scope on purpose
        return table.get( key );
    }//method()
    
    
    
    //--------------------------------------------------------------------------
    // "evaluation" method(s)
    
    /*
     * ...
     *
     * @param exercise ...
     * @param level    ...
     *
     * @return ...
     */
    int getPoints( final TE exercise,  TL level ){                              // package scope on purpose
        int points = 0;
        //
        // for each test category : e.g. EXISTENZ, CREATION, ATTRIBUTE, BEHAVIOR
        for( final TC category : TC.values() ){
            final TestTopic testTopic = new TestTopic( level, exercise, category );
            final Set<TestResult> testResultSet = table.get( testTopic );
            if( null!=testResultSet ){
                for( final TestResult testResult : testResultSet ){
                    points += testResult.getWeight();
                }//for
            }//if
        }//for
        //
        return points;
    }//method()
    
    
    /*
     * ...
     *
     * @param exercise ...
     * @param level    ...
     *
     * @return ...
     */
    Fraction getPortion( final TE exercise,  final TL level ){                  // package scope on purpose
        Fraction exerciseRelatedPortion = null;                                 // the upcoming result
        boolean portionComputationRelatedError = false;                         // default just for checkin internal consistency
        
        final Integer hundredPercent = Configuration.valuationTable.get( new ValuationUnit( level, exercise ));
        final int points = getPoints( exercise, level );
        
        if( null==hundredPercent ){
            //\=> there are NO points to achieve for this very exercise
            if( 0 != points ){
                //\=> INTERNAL ERROR: contratdiction . points achieved without any points to be achieved ???
                portionComputationRelatedError = true;                          // mark error
                exerciseRelatedPortion = null;                                  // just to show, that NO reasonable assignment is possible ;-)
            }else{
                exerciseRelatedPortion = new Fraction( 0, 1 );
            }//if
        }else{
            if( points > hundredPercent ){
                //\=> INTERNAL ERROR: you can NOT get more points than possible
                portionComputationRelatedError = true;                          // mark error
                exerciseRelatedPortion = new Fraction( points, hundredPercent );// just to have it computed ;-)
            }//if
            if( 0 == hundredPercent ){
                //\=> there are NO points to achieve for this very exercise
                if( 0 < points ){
                    //\=> INTERNAL ERROR: contratdiction detected - points achieved without any points to be achieved ???
                    portionComputationRelatedError = true;
                }//if
                exerciseRelatedPortion = new Fraction( 0, 1 );
            }else{
                //\=> everything is fine ;-)
                exerciseRelatedPortion = new Fraction( points, hundredPercent );
            }//if
        }//if
        
        // has any (internal consistency/configuration) error occurred ?? 
        if( portionComputationRelatedError ){
            //\=> an (internal consistency) error was detected during computation before
            throw new IllegalStateException( String.format(
                "INTERNAL ERROR - it was NOT expected to end up \"HERE\" - call schaefers  ->  exercise.level:%s.%s -> %d > %d",
                exercise,
                level,
                points,
                hundredPercent
            ));
        }//if
        if( null == exerciseRelatedPortion ){
            //\=> internal error (this shall NOT happen any more - tribute to former code versions ;-)
            throw new IllegalStateException( String.format(
                "INTERNAL ERROR - it was NOT expected to end up \"HERE\" - call schaefers  ->  exercise.level:%s.%s",
                exercise,
                level
            ));
        }//if
        
        return exerciseRelatedPortion;
    }//method()
    //
    /*
     * ...
     *
     * @param level    ...
     *
     * @return ...
     */
    Fraction getPortion( final TL level ){                                      // package scope on purpose
        if( TL.I != level ){
            //\=> NOT the "identification level" => all exercises but a1
            //
            final Fraction levelRelatedPortion = new Fraction( 0, 1 );          // the upcoming result
            for( final TE exercise : TE.values() ){
                if( TE.A1 != exercise ){
                    //\=> NOT the "identification exercise"
                    //
                    final Fraction portion = getPortion( exercise, level );     // portion related to execise
                    portion.mul( 1, Configuration.ratioVector[level.ordinal()]);// scale portion related to all relevant execises
                    levelRelatedPortion.add( portion );
                }//if
            }//for
            return levelRelatedPortion;
            
        }else{
            //\=> THE VERY "identification level" => only a1
            //
            return getPortion( TE.A1, TL.I );
        }//if
    }//method()
    
    
    
    //--------------------------------------------------------------------------
    // "complex" update method(s)
    
    /**
     * This method removes to a given exercise related entries from the test result table
     * 
     * @param exercise  the exercise to be removed from table
     */
    void removeExcercise( final TE exercise ){                                  // package scope on purpose
        final Set<TestTopic> keys = new HashSet<TestTopic>( table.keySet() );   // get (COPY(!) OF) keys from table
        for( final TestTopic currentKey : keys ){                               // loop over all keys given before
            final TE currentExercise = currentKey.getExercise();                // check if key
            if( exercise == currentExercise ){                                  //   is related to exercise  that shall be removed
                table.remove( currentKey );                                     //   and remove entry (in case ;-)
            }//if
        }//for 
    }//method()
        
    /**
     * This method puts an entries into the table test result table.
     * It is expected (as result of the implementation) that the test topic is NOT already inside the table
     *
     * @param testTopic     test topic (resp. the key)
     * @param testResult    test result (resp. the value to be entered)
    */
    void enterTestResult(                                                      // package scope on purpose
        final TestTopic     testTopic,
        final TestResult    testResult
    ){
        Set<TestResult> tmpSet = table.get( testTopic );
        if( null == tmpSet ){
            tmpSet = new HashSet<TestResult>();
            table.put( testTopic, tmpSet );
        }//if
        final boolean success = tmpSet.add( testResult );
        assert success :  "Uuupps : unexpected internal situation - this might indicate an internal coding error => call schaefers -> testTopic was already inside testReultTable";
    }//method()
    
    /**
     * This method integrates "missing" entries from another resp. an old test result table
     * into the current test result table  (contained by this object ;-)  that is under contruction.
     * This resp. the current test result table has to contain test results from a single exercise.
     * (Since a single exercise is checked by a single test frame ;-)
     *
     * @param oldResults        the old test result table that is checked for missing entries that have to be complemented
     */
    void integrateTestResults(                                                  // package scope on purpose
        final TestResultTable   oldResults                                      // the other table to be integrated - generally the old stored table
    ){
        // check if this table contains only a single exercise
        TE theExercise = null;
        for( final TestTopic currentKey : keySet() ){
            final TE currentExercise = currentKey.getExercise();
            if( null == theExercise ){
                theExercise = currentExercise;
            }else{
                if( ! theExercise.equals( currentExercise )){
                    throw new IllegalStateException( String.format(
                        "this TestResultTable does NOT fulfill requirements - it contains different exercises - at least : %s and %s",
                        theExercise,
                        currentExercise
                    ));
                }//if
            }//if
        }//for
        //=> given test result table is valid
        
        // integrates "missing" entries (those entries related to other exercises)
        for( final TestTopic currentKey : oldResults.keySet() ){
            final TE currentExercise = currentKey.getExercise();
            if( ! currentExercise.equals( theExercise )){
                final Set<TestResult> tmpSet = table.get( currentKey );
                final Set<TestResult> newSet =  ( null==tmpSet )  ?  new HashSet<TestResult>()  :  tmpSet;
                final Set<TestResult> oldSet = oldResults.get( currentKey );
                for( final TestResult tr : oldSet ){
                    if( newSet.contains( tr )){
                        throw new IllegalStateException( String.format(
                            "Uuupps : unexpected internal situation - this might indicate an internal coding error => call schaefers"
                        ));
                    }//if
                    newSet.add( tr );
                }//for
                put( currentKey, newSet );                                      // put() since newSet might just be created
            }//if
        }//for
    }//method()
    
    
    
    //--------------------------------------------------------------------------
    // "print" method(s) at least for support of "printing result(s)" in different ways
    
    @Override
    public String toString(){
        final StringBuffer sb = new StringBuffer( "[<" );
        sb.append( TestResultTable.class.getSimpleName() );
        sb.append( ">: " );
        sb.append( table.toString() );
        sb.append( " ]" );
        return sb.toString();
    }//method()
    
    /**
     * print full table in readable form
     */
    public void prettyPrint(){
        final Map<TestTopic,Set<TestResult>> sortedTable = new TreeMap<TestTopic,Set<TestResult>>( table );
        for( final TestTopic tt : sortedTable.keySet() ){
            System.out.printf( "%-18s  ->  [ ", tt.toSpecialString() );
            final Set<TestResult> sortedSet = new TreeSet<TestResult>( table.get( tt ) );
            int points = 0;
            for( final TestResult tr : sortedSet ){
                points += tr.getWeight();
            }//for
            System.out.printf( "%4d  ",  points );
            for( final TestResult tr : sortedSet ){
                System.out.printf( "[%4d  %s() ]",  tr.getWeight(), tr.getTestMethodName() );
            }//for
            System.out.printf( "]\n" );
        }//for
    }//method()
    
    
    
    
    
    /**
     * creates empty test resut table
     *
     * @return created test resut table
     */
    static TestResultTable createEmptyTable(){ return new TestResultTable(); }  // package scope on purpose
    
    private TestResultTable(){
        table = new HashMap<TestTopic,Set<TestResult>>();
    }//constructor()
    
    
    
    
    
    final private Map<TestTopic,Set<TestResult>> table;                         // package scope on purpose
    
    
    final static private long serialVersionUID = CentralVersionData.centralTestResultDataBaseRelatedSerialVersionUID;        // Note: TestResultTable is THE VERY class that is serialized
}//class
