package template_raw.a2;


/**
 * LabExam1215_4XIB1-P1    (PTP-Eclipse)<br />
 * <br />
 * Das Interface WordProcessor_I
 * <ul>
 *     <li>beschreibt einen WordProcessor und</li>
 *         und
 *     </li>
 *     <li>definiert die Funktionalit&auml;t m&ouml;glicher Implementierungen und
 *         fordert die entsprechende(n) Methode(n) ein.
 *     </li>
 * </ul>
 * Die von Ihnen zu implementierende Klasse WordProcessor muss
 * <ul>
 *     <li>einen &ouml;ffentlichen Konstruktor aufweisen, der ein Wort entgegen nimmt
 *         und daher der folgenden Signatur gen&uuml;gen muss:
 *         <br />
 *         <code>WordProzessor( String )</code>
 *         <br />
 *         Das als Parameter &uuml;bergebene Wort darf nicht <code>null</code> sein.
 *     </li>
 *     <li>einen &ouml;ffentlichen parameterlosen Konstruktor aufweisen.
 *         Das zu untersuchende Wort wird vom parameterlosen Konstruktor auf einen leeren String gesetzt.
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

public interface WordProcessor_I {
    
    /**
     * Die Operation isNeoLatin() pr&uuml;ft, ob das zu untersuchende Wort nur aus Buchstaben des modernen lateinischen Alphabets besteht.
     *
     * @return  <code>true</code> falls das zu untersuchende Wort nur aus Buchstaben des modernen lateinischen Alphabets besteht und
     *          sonst <code>false</code>.
     */
    boolean isNeoLatin();
    
    /**
     * Die Operation countVocals() z&auml;hlt die Anzahl Vokale im zu untersuchenden Wort.
     * Vokale sind die Buchstaben a, e, i, o, u unabh&auml;ngig von ihrer Gro&szlig;-/Klein-Schreibung.
     * Sonderzeichen wie z.B. Umlaute z&auml;hlen nicht als Vokale.
     * 
     * @return      die Anzahl Vokale im zu untersuchenden Wort.
     */
    public int countVocals();
    
    /**
     * Die Operation removeVocals() entfernt die Vokale aus dem zu untersuchenden Wort.
     * Vokale sind die Buchstaben a, e, i, o, u unabh&auml;ngig von ihrer Gro&szlig;-/Klein-Schreibung.
     * Sonderzeichen wie z.B. Umlaute z&auml;hlen nicht als Vokale.<br/>
     *<br/>
     *????? <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< TODO
     * <strong>Achtung:</strong> Als Erleichterung gibt es f&uuml;r diese Operation
     * keine &quot;Level tol_3n_*&quot-Funktionstests.
     * Derartige Tests sind in den &quot;Level tol_4s_* &quot; verschoben.
     */
    void removeVocals();
    
    
    /**
     * Die Operation getOriginaltWord() liefert das aktuell zu untersuchende Wort
     * in original Zustand.
     * 
     * @return  das zu untersuchenden Wort in seinem original Zustand.
     */
    String getOriginalWord();
    
    
    /**
     * Die Operation getCurrentWord() liefert das aktuell zu untersuchende Wort.
     * 
     * @return  das zu untersuchenden Wort in seinem aktuellen Zustand.
     */
    String getCurrentWord();
    
    
    /**
     * Die Operation setWord() setzt das zu untersuchende Wort.
     * Das als Parameter &uuml;bergebene Wort darf nicht <code>null</code> sein.
     * 
     * @param word  das neue zu untersuchende Wort.
     */
    void setWord( final String word );
    
}//interface
