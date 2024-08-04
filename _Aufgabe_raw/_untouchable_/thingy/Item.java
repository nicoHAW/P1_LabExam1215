package _untouchable_.thingy;


import java.io.Serializable;
import java.util.Objects;


//"HOME-VCS": git@git.HAW-Hamburg.de:SHF/Px/LabExercise/CXZ_Thingy[.git]
/**
 * Die Klasse Item beschreibt ein Item.
 * Ein Item hat die folgenden Eigenschaften:
 * <ul>
 *     <li>Farbe (color) - hierauf kann mit getColor() zugegriffen werden.</li>
 *     <li>Gewicht (weight) - hierauf kann mit getWeight() zugegriffen werden.</li>
 *     <li>Groesse (size) - hierauf kann mit getSize() zugegriffen werden.</li>
 *     <li>Wert (value) gemessen in (Euro-)Cent - hierauf kann mit getValue() zugegriffen werden.</li>
 * </ul>
 * Die Eigenschaften eines (bereits erzeugten) Items koennen sich nicht mehr veraendern.
 * D.h. die Werte eines Items sind (nach der Erzeugung) unveraenderlich.
 *<br />
 * 
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  2021/06/07 001
 */
final public class Item implements Serializable {
    //                                           /--------------------------->> "Serial Version Unique IDentifier" (coding) version: 1-7__4-2-2__3
    // serial version unique identification      |///////-------------------->> "main" version
    // ------------------------------------      ||||||||              ///--->> local version resp. version of day
    //                                           ||||||||__YYYY_MM_DD__|||
    static final private long serialVersionUID = 10000001__2021_06_07__001L;
    
    
    
    
    
    // Die Farbe eines Items
    final private Color color;
    
    // Das Gewicht eines Items
    final private Weight weight;
    
    // Die Groesse eines Items
    final private Size size;
    
    // Der Wert eines Items
    final private Long value;
    
    
    
    
    
    /**
     * Der Konstruktor erzeugt ein Item.
     * Ein Item hat die folgenden Eigenschaften:
     * <ul>
     *     <li>Farbe (color)</li>
     *     <li>Gewicht (weight)</li>
     *     <li>Groesse (size)</li>
     *     <li>Wert (value) gemessen in (Euro-)Cent</li>
     * </ul>
     *
     * @param color  bestimmt die Farbe des zu erzeugenden Items.
     * @param size   bestimmt die Groesse des zu erzeugenden Items.
     * @param weight bestimmt das Gewicht des zu erzeugenden Items.
     * @param value  bestimmt den Wert (gemessen in (Euro-)Cent) des zu erzeugenden Items.
     */
    public Item(
        final Color  color,
        final Size   size,
        final Weight weight,
        final Long   value
    ){
        this.color  = color;
        this.size   = size;
        this.weight = weight;
        this.value  = value;
    }//constructor()
    
    
    
    
    
    @Override
    public boolean equals( final Object otherObject ){
        if( this == otherObject )  return true;
        if( null == otherObject )  return false;
        if( getClass() != otherObject.getClass() )  return false;
        
        final Item other = ((Item)( otherObject ));
        if( size != other.size )  return false;
        if( color != other.color )  return false;
        if( weight != other.weight )  return false;
        if( ! Objects.equals( value, other.value  ))  return false;             // NOTE: constants only exist once -> hence "!=" is ok ;-)
        return true;
    }//method()
    
    @Override
    public int hashCode(){
        final int prime = 31;
        int hashCode =              ((null==size) ? 0 : size.hashCode());
        hashCode = prime*hashCode + ((null==color)  ? 0 : color.hashCode());
        hashCode = prime*hashCode + ((null==weight) ? 0 : weight.hashCode());
        hashCode = prime*hashCode + ((null==weight) ? 0 : value.hashCode());
        return  hashCode;
    }//method();
    
    @Override
    public String toString(){
        return String.format(
            "[<%s>: %s %s %s %s]",
            Item.class.getSimpleName(),
            color,
            size,
            weight,
            value
        );
    }//method()
    
    
    /**
     * getColor() liefert die Farbe des jeweiligen Items.
     */
    public Color getColor(){ return color; }
    
    /**
     * getSize() liefert die Groesse des jeweiligen Items.
     */
    public Size getSize(){ return size; }
    
    /**
     * getSpeed() liefert die Gewicht des jeweiligen Items.
     */
    public Weight getWeight(){ return weight; }
    
    /**
     * getSize() liefert den Wert gemessen in (Euro-)Cent des jeweiligen Items.
     */
    public Long getValue(){ return value; }
    
}//class
//Note: Objects of this class are immutable
