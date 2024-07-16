package _untouchable_.supportC2x00;


/**
 * LabExam11xx_4XIB1-P1    (PTP/Eclipse)<br />
 *<br />
 * Demo and Reference LabExam for P1<br />
 * <br />
 * Herald sent by you (the programmer) to convey messages or proclamations to the user ;-)
 *<br />
 *<br />
 * VCS: see ReadMe.txt
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  200609 #01
 */
public class Herald {
    
    /**
     * Medium respectively channel over that proclamation is done
     */
    static public enum Medium {
        SYS_ERR,
        SYS_OUT
      //WINDOW                          // maybe the day will come before doomsday ;-)
    }//enum
    
    
    
    
    
    // state containing:
    //  -)  Lock guarding that only one message is printed at time
    static final private Object classSpecificLock = new Object();
    
    
    
    
    
    /**
     * Proclaim test count via given medium
     *
     * param medium  Proclamation is done via THIS VERY MEDIUM,
     * param cnt     test count
     */
    static public void proclaimTestCount (
        final Medium  medium,
        final int  cnt
    ){
        final String msg = String.format( "\n[>>>> @test# %d <<<<]\n", cnt );
        Herald.proclaimMessage( medium, msg );
    }//method()
    
    
    
    /**
     * Proclaim message via given medium
     *
     * param medium  Proclamation is done via THIS VERY MEDIUM,
     * param text    message to be printed
     */
    static public void proclaimMessage (
        final Medium  medium,
        final StringBuffer  text
    ){
        proclaimMessage( medium, text.toString() );
    }//method()
    
    /**
     * Proclaim message via given medium
     *
     * param medium  Proclamation is done via THIS VERY MEDIUM,
     * param text    message to be printed
     */
    static public void proclaimMessage (
        final Medium  medium,
        final StringBuilder text
    ){
        proclaimMessage( medium, text.toString() );
    }//method()
    
    /**
     * Proclaim message via given medium
     *
     * param medium  Proclamation is done via THIS VERY MEDIUM,
     * param text    message to be printed
     */
    static public void proclaimMessage (
        final Medium  medium,
        final String  text
    ){
        synchronized( classSpecificLock ){
            switch( medium ){
                case SYS_ERR:
                    // error message to programmer - this SHALL NOT happen during lab exam
                    System.out.flush();
                    System.err.flush();
                    System.err.println( text );
                    System.err.flush();
                  break;
                case SYS_OUT:
                    // some kind of information
                    System.err.flush();
                    System.out.flush();
                    System.out.println( text );
                    System.out.flush();
                  break;
                default:
                    if( true )  throw new UnsupportedOperationException();      // "GUARD" - 2020/06/09 the "whole thing"(JavaFX window) is put on hold
                  break;
            }//switch
        }//synchronized
    }//method()
    
}//class
