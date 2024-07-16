package _untouchable_.data;

 
/**
 * LabExam1113_4XIB1-P1    (PTP-Eclipse)<br />
 *<br />
 * Die Klasse Data dient als Platzhalter-Klasse.
 * Diese Klasse steht "dort", wo typischer Weise ein Generic stehen wuerde.
 * "Hier" in der Rechnerpruefung, soll die Klasse von moeglichen Problemen mit Generics entlasten.
 * Die Klasse selbst ist nicht weiter interessant - sie dient nur als Platzhalter-Klasse.
 *<br />
 *<br />
 * VCS: git@BitBucket.org:schaefers/Prg_P1_LE_s17w_TI1_Proto[.git]
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  LabExam1113_4XIB1-P1_172v01_180122_v01
 */
public class Data implements Comparable<Data> {
    
    private int data;
    
    
    
    public Data( final int data ){
        this.data = data;
    }//constructor()
    //
    public Data (){
        this(0);
    }//constructor()
    
    
    
    @Override
    public boolean equals( final Object otherObject ){
        if( this == otherObject )  return true;
        if( null == otherObject )  return false;
        if( getClass()!=otherObject.getClass() )  return false;
        
        final Data other = (Data)( otherObject );
        if( data != other.data  )  return false;
        return true;
    }//method()
    
    @Override
    public int hashCode(){ return data; }
    
    @Override
    public int compareTo( final Data other ){ return Integer.compare( data, other.data ); }
    
    @Override
    public String toString(){
        return String.format(
            "[<%s>: data=%d]",
            Data.class.getSimpleName(),
            this.data
        );
    }//method()
    
}//class
