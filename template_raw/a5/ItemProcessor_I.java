package template_raw.a5;


import _untouchable_.thingy.Item;


/**
 * LabExam1215_4XIB1-P1    (PTP-Eclipse)<br />
 * <br />
 * Das Interface ItemProcessor_I
 * <ul>
 *     <li>beschreibt einen ItemProcessor, der Items verarbeitet
 *         und
 *     </li>
 *     <li>definiert die Funktionalit&auml;t m&ouml;glicher Implementierungen und
 *         fordert die entsprechende(n) Methode(n) ein.
 *     </li>
 * </ul>
 * Die von Ihnen zu implementierende Klasse ItemProcessor muss
 * <ul>
 *     <li>einen &ouml;ffentlichen parameterlosen Konstruktor aufweisen.
 *     </li>
 * </ul>
 * 
 * Aufgabe des ItemProcessors ist es Items zun&auml;chst zu sammeln und immer sobald
 * f&uuml;nf Items gleichen Werts vorliegen, diese abzuliefern.
 * 
 * Eine genaue Auflistung der Anforderungen an die zu implementierende Klasse
 * findet sich auf dem Aufgabenzettel.<br />
 *<br />
 *<br />
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1215_4XIB1-P1_211v10_210705_v01
 */
public interface ItemProcessor_I {
    
    /**
     * Die Operation process() verarbeitet ein Item.<br />
     * Der von Ihnen zu implementierende ItemProcessor reagiert auf ein jeweils erscheinendes
     * Item (bzw. konkret auf das als Parameter &uuml;bergebene Item ).
     * Immer wenn ein Item mit process() an dem ItemProcessor &uuml;bergeben wird, wird dieses Item
     * (zun&auml;chst) in den internen Bestand &uuml;bernommen. Sobald f&uuml;nf Items gleichen Werts
     * im internen Bestand vorliegen, werden diese als Ergebnis von process() abgeliefert
     * und aus dem internen Bestand entfernt.<br />
     * Der als Parameter &uuml;bergebene Wert darf nicht <code>null</code> sein.
     *<br />
     * Bemerkungen:
     * <ul>
     *     <li>Der ItemProcessor reagiert unmittelbar sofort.
     *         D.h., das als Parameter &uuml;bergebene Item ist bei einer möglichen Zusammenstellung
     *         eines Quintets zu ber&uuml;cksichtigen.
     *     </li>
     *     <li>Es wird zugesichert, dass solange sich ein und dasselbe Item (korrekter Weise)
     *         noch im Bestand befindet, dieses Item nicht noch einmal dem ItemProcessor &uuml;bergeben wird.
     *         Ein bereits aus dem internen Bestand entferntes Item kann dem ItemProcessor
     *         wieder mit process() &uuml;bergeben werden.
     *         (Ma&szlig;stab f&uuml;r diese Zusicherung ist das Verhalten bei korrekter Funktion.)
     *     </li>
     * </ul>
     *
     *
     * @param item  bestimmt das (neue;) Item, das zu verarbeiten ist.
     * 
     * @return  ein Quintet (gebildet aus f&uuml;nf Items gleichen Werts) - sofern diesed zusammengestellt werden kann
     *          oder sonst <code>null</code>.
     */
    public Quintet_I process( final Item item );
    
    /**
     * Die Operation itemsProcessed() liefert die Anzahl der Items, die vom ItemProcessor
     * verarbeitet wurden (bzw. die Anzahl Aufrufe von process())
     * seit dem letzten Aufruf von reset()
     * oder sofern kein reset() aufgerufen wurde, seit dem Programmstart.
     *<br/>
     * @return  Die Anzahl der vom ItemProcessor verarbeiteten Items.
     */
    public int itemsProcessed();
    
    /**
     * Die Operation quintetsFound() liefert die Anzahl der bisher gefundenen Quintets
     * (gebildet aus 5 Items gleichen Werts) seit dem letzten Aufruf von reset()
     * oder sofern kein reset() aufgerufen wurde, seit dem Programmstart.
     *<br />
     * @return  Die Anzahl der bisher gefundenen Quintets (gebildet aus 5 Items gleichen Werts)
     */
    public int quintetsFound();
    
    /**
     * Die Methode reset() l&ouml;scht den internen Bestand und setzt alle eingeforderten Z&auml;hler auf den Startwert 0.<br />
     * (<i>reset macht halt einen reset. Der Name sollte selbsterkl&auml;rend sein.</i>)
     */
    public void reset();
    
}//interface
