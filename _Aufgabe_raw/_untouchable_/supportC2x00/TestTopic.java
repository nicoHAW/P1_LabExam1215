package _untouchable_.supportC2x00;


import java.io.Serializable;


/**
 * LabExam11xx_4XIB1-P1    (PTP/Eclipse)<br />
 *<br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * TestTopic ...
 *<br />
 *<br />
 * VCS: see ReadMe.txt
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  200606 #01
 */
public class TestTopic implements Comparable<TestTopic>,Serializable {
    
    @Override
    public int hashCode(){
        return (    level.hashCode()
            + 31 *( exercise.hashCode()
            + 31 *( category.hashCode()
        )));
    }//method()
    
    @Override
    public boolean equals( final Object otherObject ){
        if( this == otherObject )  return true;
        if( null == otherObject )  return false;
        if( getClass()!=otherObject.getClass() )  return false;
        final TestTopic other = (TestTopic)( otherObject );
        if( level != other.level )  return false;
        if( isUnequal( exercise, other.exercise ))  return false;
        if( category != other.category )  return false;
        return true;
    }//method()
    //
    private static boolean isUnequal( final Object o1, final Object o2 ){
        return (o1!=o2) && ( (null==o1) || ( ! o1.equals( o2 )));
    }//method()
    
    @Override
    public int compareTo( final TestTopic other ){
        int tmp;
        tmp = level.compareTo( other.level );
        if( 0 != tmp )  return tmp;
        tmp = exercise.compareTo( other.exercise );
        if( 0 != tmp )  return tmp;
        tmp = category.compareTo( other.category );
        return tmp;
    }//method()
    
    @Override
    public String toString(){
        return String.format(
            "[<%s>: %s %s %s]",
            TestTopic.class.getSimpleName(),
            level,
            exercise,
            category
        );
    }//method()
    
    /**
     * Generates String containing level, exercise and category.
     */
    public String toSpecialString(){
        return String.format(
            "[ %s %s %s ]",
            level,
            exercise,
            category
        );
    }//method()
    
    public TL getLevel(){ return level; }
    public TE getExercise(){ return exercise; }
    public TC getCategory(){ return category; }
    
    
    
    
    
    public TestTopic(
        final TL  level,                                                        // test level - e.g. A for basic
        final TE  exercise,                                                     // exercise - e.g. a1
        final TC  category                                                      // test topic - short description of test
    ){
        this.level = level;
        this.exercise = exercise;
        this.category = category;
    }//class
    
    
    
    
    
    private final TL  level;                                                    // test level
    private final TE  exercise;                                                 // exercise - e.g. A1
    private final TC  category;                                                 // test category
    
    final static private long serialVersionUID = CentralVersionData.centralTestResultDataBaseRelatedSerialVersionUID;
}//class
