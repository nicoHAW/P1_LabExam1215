package template_raw.a3;


import java.math.BigInteger;


/**
 * LabExam1215_4XIB1-P1    (PTP-Eclipse)<br />
 * <br />
 * Das Interface NumberPalindromeChecker_I
 * <ul>
 *     <li>beschreibt einen NumberPalindromeChecker, der Zahlenpalindrome erkennen kann
 *         und
 *     </li>
 *     <li>definiert die Funktionalit&auml;t m&ouml;glicher Implementierungen und
 *         fordert die entsprechende(n) Methode(n) ein.
 *     </li>
 * </ul>
 * Die von Ihnen zu implementierende Klasse NumberPalindromeChecker muss
 * <ul>
 *     <li>muss Zahlenpalindrome in positiven Zahlen (einschlie&szlig;lich 0 erkennen),
 *     </li>
 *     <li>darf <u>keine</u> negativen Zahlen akzeptieren,
 *     </li>
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
public interface NumberPalindromeChecker_I {
    
    /**
     * Die Operation isNumberPalindrome() &uuml;berpr&uuml;ft eine als Parameter &uuml;bergebene
     * Zahl darauf, ob sie ein Zahlenpalindrom ist. Vergleichbar einem Wortpalindorm,
     * dass unabh&auml;ngig von der Leserichtung (links nach rechts oder rechts nach links),
     * das gleiche Wort ist,
     * gilt f&uuml;r ein Zahlenpalindrom, dass die Zahl im Dezimalsystem unabh&auml;ngig
     * von der Leserichtung, die gleiche Zahl ist.<br />
     * <br />
     * Beispiele:
     * <ul><li>8 ist ein Zahlenpalindrom</li>
     *     <li>99 ist ein Zahlenpalindrom</li>
     *     <li>121 ist ein Zahlenpalindrom</li>
     *     <li>7557 ist ein Zahlenpalindrom</li>
     *     <li>42 ist KEIN Zahlenpalindrom</li>
     *     <li>123 ist KEIN Zahlenpalindrom</li>
     *     <li>1223 ist KEIN Zahlenpalindrom</li>
     * </ul>
     * 
     * @param number  die zu untersuchende Zahl.
     *                <code>null</code> und negative Zahlen als (aktuelle) Parameter werden <u>nicht</u> unterst&uuml;tzt und sind daher verboten.
     * @return  <code>true</code> falls die zu untersuchende Zahl ein Zahlenpalindrom ist und
     *          sonst <code>false</code>.
     */
    public boolean isNumberPalindrome( BigInteger number );
    
    
    
    /**
     * Die Operation amountOfNumbersChecked() ...
     *
     * @return  Anzahl der Zahlen (numbers), die darauf &uuml;berp&uuml;ft wurden ob sie ein Zahlenpalindrome sind. 
     */
    public int amountOfNumbersChecked();
    
    /**
     * Die Operation amountOfPalindromesFound() ...
     *
     * @return  Anzahl gefundener Zahlenpalindrome. 
     */
    public int amountOfPalindromesFound();
    
    
    
    /**
     * Die Methode reset() versetzt den NumberPalindromeChecker in seinen Startzustand.<br />
     * (<i>reset macht halt einen reset. Der Name sollte selbsterkl&auml;rend sein.</i>)
     */
    public void reset();
    
}//interface
