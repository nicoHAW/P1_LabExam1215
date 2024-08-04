package _untouchable_.supportC2x00;


/**
 * LabExam11xx_4XIB1-P1    (PTP/Eclipse)<br />
 *<br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * Valuation Unit
 *<br />
 *<br />
 * VCS: see ReadMe.txt
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  200606 #01
 */
public class ValuationUnit {
        
    @Override
    public int hashCode(){
        return (        level.hashCode()
                + 31 *( exercise.hashCode()
        ));
    }//method()
    
    @Override
    public boolean equals( final Object otherObject ){
        if( this == otherObject )  return true;
        if( null == otherObject )  return false;
        if( getClass()!=otherObject.getClass() )  return false;
        final ValuationUnit other = (ValuationUnit)( otherObject );
        if( level != other.level )  return false;
        if( isUnequal( exercise, other.exercise ))  return false;
        return true;
    }//method()
    //
    private static boolean isUnequal( final Object o1, final Object o2 ){
        return (o1!=o2) && ( (null==o1) || ( ! o1.equals( o2 )));
    }//method()
    
    @Override
    public String toString(){
        return String.format(
            "[%s>: %s %s]",
            ValuationUnit.class.getSimpleName(),
            level,
            exercise
        );
    }//method()
    
    
    
    
    
    /**
     * ...
     *
     * @param level  ...
     * @param exercise  ...
     */
    ValuationUnit( final TL level, final TE exercise ){
        this.level = level;
        this.exercise = exercise;
    }//constructor()
    
    
    
    
    
    /**
     * ...
     */
    final TL level;                                                             // test level
    
    /**
     * ...
     */
    final TE exercise;                                                          // test exercise - e.g. A1
    
}//class

