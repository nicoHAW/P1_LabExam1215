package _untouchable_.supportC2x00;


/**
 * LabExam11xx_4XIB1-P1    (PTP/Eclipse)<br />
 *<br />
 * TE ::= Test Exercise<br />
 * Enum defining supported/provided exercises.
 *<br />
 *<br />
 * VCS: see ReadMe.txt
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  200606 #01
 */
public enum TE {
    A1,                                 // THIS has to be the first exercise - hard coded connection (->TestResultTable)
    A2,
    A3,
    A4,
    A5;
    
    /*
     * enum are inherently serializable since Class java.lang.Enum extends Object implements Serializable.
     * Futher declared serialVersionUID fields are ignored anyway.
     * See  http://docs.oracle.com/javase/8/docs/api/java/lang/Enum.html
     *      http://docs.oracle.com/javase/8/docs/api/serialized-form.html#java.lang.Enum
     *      http://docs.oracle.com/javase/8/docs/platform/serialization/spec/serialTOC.html ->  1.12 Serialization of Enum Constants
     *      http://docs.oracle.com/javase/8/docs/platform/serialization/spec/serial-arch.html#a6469
     * Feel free to check with serialver ;-)
     */
    //final static private long serialVersionUID = Configuration.centralTestResultDataBaseRelatedSerialVersionUID;
}//enum
