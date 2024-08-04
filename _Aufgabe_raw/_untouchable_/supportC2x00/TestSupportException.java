package _untouchable_.supportC2x00;


/**
 * LabExam12xx_4XIB1-P1    (PTP/Eclipse)<br />
 *<br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * <br />
 * Test Support Exception
 *<br />
 *<br />
 * VCS: see ReadMe.txt
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  200830 #01
 */
public class TestSupportException extends Exception {
    
    final private Throwable causingThrowable;
    
    // even so the test support exception is actually NOT test result data base related,  it gets the same ID for simplification
    final static private long serialVersionUID = CentralVersionData.centralTestResultDataBaseRelatedSerialVersionUID;
    
    
    
    
    
    /**
     * ...
     *
     * @param message                   ...
     * @param causingThrowable          ...
     */
    TestSupportException( final String message,  final Throwable causingThrowable ){     // package scope on purpose
        super( message );
        this.causingThrowable = causingThrowable;
    }//constructor()
    //
    /**
     * ...
     *
     * @param message    ...
     */
    TestSupportException( final String message ){                               // package scope on purpose
        this( message, null );
    }//constructor()
    //
    /**
     * ...
     */
    TestSupportException(){                                                     // package scope on purpose
        this( "test support function broken" );
    }//constructor()
    
    
    
    
    
    @Override
    public Throwable getCause(){
        return causingThrowable;
    }//method()
    
    // see definition of Throwable.getCause() : Returns ... or null if the cause is nonexistent or unknown... 
    public boolean causingThrowableExists(){
        return null == causingThrowable;
    }//method()
    
}//class
