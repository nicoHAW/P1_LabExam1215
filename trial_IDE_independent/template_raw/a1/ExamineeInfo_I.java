package template_raw.a1;


/**
 * LabExam1114_4XIB1-P1    (PTP-Eclipse)<br />
 *<br />
 * &Uuml;ber das Interface ExamineeInfo_I wird der Zugriff auf Information(en) &uuml;ber den Pr&uuml;fling sicher gestellt.<br />
 * <br />
 * Das Interface ExamineeInfo_I definiert die Funktionalit&auml;t m&ouml;glicher Implementierungen und fordert die entsprechenden Methoden ein.
 * Objekte, die diesem Interface gen&uuml;gen werden verwendet um Sie zu identifizieren.<br />
 * <br />
 * Die von Ihnen zu implementierenden Klasse ExamineeInfo muss
 * <ul>
 *     <li>einen &ouml;ffentlichen Kontruktor aufweisen, der der folgenden Signatur gen&uuml;gt:<br />
 *         <code>ExamineeInfo()</code>
 *     </li>
 * </ul>
 * Eine genaue Auflistung der Anforderungen an die zu implementierende Klasse findet sich auf dem Aufgabenzettel,
 * sowie weitere (Teil-)Aufgaben, die gel&ouml;st werden m&uuml;ssen.
 *<br />
 *<br />
 * VCS: see ReadMe.txt
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1215_4XIB1-P1_211v10_210705_v01
 */
public interface ExamineeInfo_I {
    
    /**
     * getExamineeSurName() liefert den Nachnamen des Pr&uuml;flings in Kleinbuchstaben ohne m&ouml;gliche Bindestriche oder Leerzeichen.
     * (Mit Kleinbuchstaben sind die 26 Kleinbuchstaben a bis z des (modernen) lateinischen Alphabets gemeint.)
     * Sollten Sie mehr als einen Nachnamen haben, ist derjenige zu verwenden mit dem Sie auch bei der HAW (zuerst) gef&uuml;hrt werden.
     * Im Zweifelsfall schauen Sie einfach auf Ihrem Studentenausweis nach, der vor Ihnen auf den Tisch liegt.
     * Ferner m&uuml;ssen m&ouml;gliche Namenszus&auml;tze (wie z.B. von, van, ten, oder Mc) hinten angestellt werden.<br />
     * Beispielsweise w&uuml;rde &quot;von Cro&yuml;-Gr&auml;&szlig;ler&quot; zu &quot;croygraesslervon&quot;.<br />
     * 
     * @return Nachname des Pr&uuml;flings in Kleinbuchstaben.
     */
    public String getExamineeSurName();
    
    /**
     * getExamineeFirstName() liefert den Vornamen des Pr&uuml;flings in Kleinbuchstaben ohne m&ouml;gliche Bindestriche oder Leerzeichen.
     * (Mit Kleinbuchstaben sind die 26 Kleinbuchstaben a bis z des (modernen) lateinischen Alphabets gemeint.)
     * Sollten Sie mehr als einen Vornamen haben, ist derjenige zu verwenden mit dem Sie auch bei der HAW (zuerst) gef&uuml;hrt werden.
     * Im Zweifelsfall schauen Sie einfach auf Ihrem Studentenausweis nach, der vor Ihnen auf den Tisch liegt.<br />
     * Beispielsweise w&uuml;rde &quot;Andr&eacute;-&Euml;v&iuml;no&quot; zu &quot;andreevino&quot;.<br />
     * 
     * @return Vorname des Pr&uuml;flings in Kleinbuchstaben.
     */
    public String getExamineeFirstName();

}//interface
