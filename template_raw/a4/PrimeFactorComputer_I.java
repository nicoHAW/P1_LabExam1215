package template_raw.a4;


import java.math.BigInteger;
import java.util.List;


/**
 * LabExam1215_4XIB1-P1    (PTP-Eclipse)<br />
 * <br />
 * Das Interface PrimeFactorComputer_I
 * <ul>
 *     <li>beschreibt einen PrimeFactorComputer, der eine Zahl in seine Primfaktoren zerlegt
 *         und
 *     </li>
 *     <li>definiert die Funktionalit&auml;t m&ouml;glicher Implementierungen und
 *         fordert die entsprechende(n) Methode(n) ein.
 *     </li>
 * </ul>
 * Die von Ihnen zu implementierende Klasse PrimeFactorComputer muss
 * <ul>
 *     <li>einen &ouml;ffentlichen parameterlosen Konstruktor aufweisen.
 *     </li>
 * </ul>
 * 
 * Eine genaue Auflistung der Anforderungen an die zu implementierende Klasse
 * findet sich auf dem Aufgabenzettel.<br />
 *<br />
 *<br />
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1215_4XIB1-P1_211v10_210705_v01
 */
public interface PrimeFactorComputer_I {
    
    /**
     * Die Operation compute() zerlegt eine als Parameter &uuml;bergebene positive Zahl (gr&ouml;&szlig;er 1) in ihre Primfaktoren.
     * Die Primfaktoren werden aufsteigend geordnet in einer Ergebnisliste abgeliefert.
     * Die Primfaktoren werden so oft in der Liste aufgef&uuml;hrt, wie sie die zu zerlegende Zahl teilen.<br/>
     *<br />
     * Beispiele:
     * <ul><li>Die Ergebnisliste f&uuml;r die Zahl 12 enth&auml;lt (in dieser Reihenfolge) 2 mal die 2 und 1 mal die 3, da 12=2*2*3</li>
     *     <li>Die Ergebnisliste f&uuml;r die Zahl 4725 enth&auml;lt (in dieser Reihenfolge) 3 mal die 3, 2 mal die 5 und 1 mal die 7, da 4725=3*3*3*5*5*7</li>
     * </ul>
     * 
     * @param number  die in Primfaktoren zu zerlegende Zahl. Es sind nur positive Zahlen gr&ouml;&szlig;er 1 zul&auml;ssig.
     * @return  eine aufsteigend geordnete Liste aller Primfaktoren der als Parameter ubergebenen Zahl.
     */
    public List<BigInteger> compute( BigInteger number );
    
    
    
    /**
     * Die Operation amountOfNumbersChecked() ...
     *
     * @return  Anzahl der Zahlen (numbers), die ...
     */
    public int amountOfNumbersFactorized();
    
    /**
     * Die Operation amountOfDifferentNumbersChecked() ...
     *
     * @return  Anzahl unterschiedlicher Zahlen (numbers), die ... 
     */
    public int amountOfDifferentNumbersFactorized();
    
    
    
    /**
     * Die Methode reset() versetzt den NumberPalindromeChecker in seinen Startzustand.<br />
     * (<i>reset macht halt einen reset. Der Name sollte selbsterkl&auml;rend sein.</i>)
     */
    public void reset();
    
}//interface
