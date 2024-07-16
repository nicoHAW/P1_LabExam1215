package template_raw.a5;


import _untouchable_.thingy.*;


/**
 * LabExam1215_4XIB1-P1    (PTP-Eclipse)<br />
 * <br />
 * Das Interface Quintet_I
 * <ul>
 *     <li>beschreibt ein Quintet bzw. eine Zusammenstellung von 5 Items gleichen Werts
 *         und
 *     </li>
 *     <li>definiert die Funktionalit&auml;t m&ouml;glicher Implementierungen und
 *         fordert die entsprechende(n) Methode(n) ein.
 *     </li>
 * </ul>
 * In einem Quintet sind 5 Items gleichen Werts zusammengestellt.
 * Beginnend (an der Position 0) mit dem &auml;ltesten bzw. dem zuerst aufgetretenen Item
 * hin zum j&uuml;ngsten bzw. zuletzt aufgetretenen Item (an der Position 4).
 * 
 * Eine genaue Auflistung der Anforderungen an die zu implementierende Klasse
 * findet sich auf dem Aufgabenzettel.<br />
 *<br />
 *<br />
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1215_4XIB1-P1_211v10_210705_v01
 */
public interface Quintet_I {
    
    /**
     * Die Operation get() liefert ein Item im Quintet, wobei der Zugriff entsprechend des jeweiligen Auftrittsalters erfolgt.
     * Ein Quintet besteht auf 5 Items gleichen Werts. Mit Hilfe von getItem() kann
     * auf diese Items zugegriffen werden. getItem(0) liefert das erste bzw. &auml;lteste Item aus dem Quintet
     * und getItem(4) das letzte bzw. j&uuml;ngste Item aus dem Quintet.
     * 
     * @param index  die Position im Quintet. Es sind nur Werte von 0 bis 4 zul&auml;ssig.
     * @return  das Item an der spezifizierten Position.
     */
    Item getItem( int appearenceRelatedIndex );
    
}//interface
