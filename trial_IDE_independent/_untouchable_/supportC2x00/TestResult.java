package _untouchable_.supportC2x00;


import java.io.Serializable;


/**
 * LabExam11xx_4XIB1-P1    (PTP/Eclipse)<br />
 *<br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * TestResult ...
 *<br />
 *<br />
 * VCS: see ReadMe.txt
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  200606 #01
 */
public class TestResult implements Comparable<TestResult>,Serializable {
    
    @Override
    public int hashCode(){
        return (    testMethodName.hashCode()
            + 31 *( weight )
        );
    }//method()
    
    @Override
    public boolean equals( final Object otherObject ){
        if( this == otherObject )  return true;
        if( null == otherObject )  return false;
        if( getClass()!=otherObject.getClass() )  return false;
        final TestResult other = (TestResult)( otherObject );
        if( isUnequal( testMethodName, other.testMethodName ))  return false;
        if( weight != other.weight )  return false;
        return true;
    }//method()
    //
    private static boolean isUnequal( final Object o1, final Object o2 ){
        return (o1!=o2) && ( (null==o1) || ( ! o1.equals( o2 )));
    }//method()
    
    @Override
    public int compareTo( final TestResult other ){
        int tmp;
        tmp = testMethodName.compareTo( other.testMethodName );
        if( 0 != tmp )  return tmp;
        tmp = Integer.compare( weight, other.weight );
        return tmp;
    }//method()
    
    @Override
    public String toString(){
        return String.format(
            "[<%s>: %s %s]",
            TestResult.class.getSimpleName(),
            testMethodName,
            weight
        );
    }//method()
    
    /**
     * Delivers test method name or stored test result.
     *
     * @return test method name
     */
    public String getTestMethodName(){ return testMethodName; }
    //
    /**
     * Delivers weight of test.
     *
     * @return weight
     */
    public int getWeight(){ return weight; }
    
    
    
    
    
    /**
     * ...
     *
     * @param testMethodName
     * @param testWeight
     */
    public TestResult(
        final String  testMethodName,                                           //
        final int  testWeight                                                   // weight for evaluation
    ){
        if( null == testMethodName )  throw new IllegalArgumentException( "test method name must NOT be null" );
        if( 0 > testWeight )  throw new IllegalArgumentException( "test weight shall be positve" );
        
        this.testMethodName = testMethodName;
        this.weight = testWeight;
    }//constructor()
    
    
    
    
    
    private final String  testMethodName;                                       //
    private final int  weight;                                                  // weight for this very test (default is one)    
    
    final static private long  serialVersionUID = CentralVersionData.centralTestResultDataBaseRelatedSerialVersionUID;
}//class
