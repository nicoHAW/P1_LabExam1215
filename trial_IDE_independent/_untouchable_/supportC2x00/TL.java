package _untouchable_.supportC2x00;


/**
 * LabExam11xx_4XIB1-P1    (PTP/Eclipse)<br />
 *<br />
 * TL ::= Test Level<br />
 * Enum defining the test levels.
 *<br />
 *<br />
 * VCS: see ReadMe.txt
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  200606 #01
 */
public enum TL {
    I,                                  // 0i: indispensable stuff for identification of examinee -> examinee info
    A,                                  // 1e: elementary/indispensable knowledge - 100% required
    B,                                  // 2b: basic/indispensable knowledge - 100% required ( 2b <=> guard tests )
    C,                                  // 3n: normal knowledge
    D;                                  // 4s: sophisticated/expert knowledge
    
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
