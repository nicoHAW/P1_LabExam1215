package _untouchable_.cards;


import static _untouchable_.cards.Card.*;  // only for JavaDoc ;-)

import java.lang.reflect.Constructor;
import java.util.*;


/**
 * Die Klasse {@link Deck} ist der Datentyp eines 52 Blatt Poker-Karten-<strong>Deck</strong>s.
 * Das 52 Blatt Poker-Karten-Deck besteht aus Spielkarten <em>(bzw. {@link Card})</em>, die<br />
 * die R&auml;nge <em>(bzw. {@link Rank})</em>:<br />
 * &nbsp; &nbsp;<code>2</code>,&nbsp;<code>3</code>,&nbsp; <code>4</code>,&nbsp;&hellip;,&nbsp;<code>ACE</code><br />
 * in den Farben <em>(bzw. {@link Suit})</em>:<br />
 * &nbsp; &nbsp;<code>CLUB</code> (&clubs;), <code>DIAMOND</code> (<font color="red">&diams;</font>), <code>HEART</code> (<font color="red">&hearts;</font>) und <code>SPADES</code> (&spades;)<br />
 * aufweisen.<br />
 * <br />
 * Wichtige Attribute dieser Klasse sind:<br />
 * &bull;{@link #deal()} gibt die &quot;oberste&quot; Karte <em>(bzw. {@link Card})</em> des {@link Deck}s.<br />
 * &bull;{@link #removeTopCard()} zum Entfernen der &quot;obersten&quot; Karte <em>(bzw. {@link Card})</em> des {@link Deck}s.<br />
 * &bull;{@link #shuffleDeck()}} zum Mischen des {@link Deck}s.<br />
 */
//@ClassPreamble (
//  vcs             = "git.HAW-Hamburg.de:SHF/Px/LabExercise/CXZ_CardsLeanVersion[.git]",
//  author          = "Michael Schaefers",
//  contact         = "Michael.Schaefers@HAW-Hamburg.de",
//  organization    = "Dept.Informatik; HAW Hamburg",
//  date            = "2020/04/04",
//  version         = "2.4.2",
//  note            = "release for SS20 ;  1st release WS07/08",
//  lastModified    = "2020/04/04",
//  lastModifiedBy  = "Michael Schaefers",
//  reviewers       = ( "none" )
//)
public class Deck{
   
    
    //
    //
    // CONSTANTs----------------------------------------------------------------

    //private final int DECK_SIZE = 52;

    
    
    //
    //
    // VARIABLEs----------------------------------------------------------------

    //@ChunkPreamble ( lastModified="2012/02/12", lastModifiedBy="Michael Schaefers" )
    private List<Card> pack1st;
    //@ChunkPreamble ( lastModified="2012/02/12", lastModifiedBy="Michael Schaefers" )
    private List<Card> pack2nd;
    
    
   
    //
    //
    // CONSTRUCTORs-------------------------------------------------------------
    
    /**
     * Der Konstruktor erzeugt ein {@link Deck}.
     * Es werden dabei interne Variablen gesetzt.
     */
    //@ChunkPreamble ( lastModified="2014/04/24", lastModifiedBy="Michael Schaefers" )
    public Deck(){
        // "assert" that the world is as expected - SORRY, this mysterious comment is on purpose
        for (Constructor<?> constructor : Card.class.getDeclaredConstructors()){
            if (0!=constructor.getModifiers()){
                throw new IllegalStateException( String.format( "You have have done strange disturbing things - please call Michael Schaefers" ) );
            }//if
        }//for
                
        pack1st = new LinkedList<Card>();  // cards in game/pack
        pack2nd = new LinkedList<Card>();  // cards out of game/pack
        
        for(Card.Suit suit : Card.Suit.values()){
            for(Card.Rank rank : Card.Rank.values()){
               pack1st.add( new Card( suit, rank ) );
            }//for
        }//for
        
        Collections.shuffle( pack1st );
    }//constructor()
   
    
    
    //
    //
    // METHODs------------------------------------------------------------------

    /**
     * Die Methode {@link #shuffleDeck()} mischt die noch verf&uuml;gbaren Karten <em>(bzw. {@link Card})</em> im {@link Deck}.
     */
    //@ChunkPreamble ( lastModified="2012/02/12", lastModifiedBy="Michael Schaefers" )
    public void shuffleDeck(){
        Collections.shuffle( pack1st );
    }//method()
   
    /**
     * Die Methode {@link #deal()} liefert die &quot;oberste&quot; Karte <em>(bzw. {@link Card})</em> des {@link Deck}s.
     * Sollte keine Karte <em>(bzw. {@link Card})</em> im {@link Deck} vorhanden sein, so wird
     * die Meldung:<br />
     * &nbsp; &nbsp;<code>>>>> ERROR !!! : no more cards in deck - opening new pack</code><br />
     * auf dem Bildschirm ausgegeben
     * und eine neues {@link Deck} &quot;aufgemacht&quot; und diesem die oberste Karte <em>(bzw. {@link Card})</em> entnommen.
     * @return &quot;oberste&quot; Karte <em>(bzw. {@link Card})</em> des {@link Deck}s.
     */
    //@ChunkPreamble ( lastModified="2012/02/12", lastModifiedBy="Michael Schaefers" )
    public Card deal(){
        if (pack1st.isEmpty()){
            System.out.printf("\n>>>> ERROR !!! : no more cards in deck - opening new pack\n");
            while(!pack2nd.isEmpty()){
                 pack1st.add( pack2nd.remove(0) );
            }//for
            shuffleDeck();
        }//if
        Card card = pack1st.remove( 0 );
        pack2nd.add( card );
        return card;
    }//method()
   
    /**
     * Die Methode {@link #removeTopCard()} entfernt die &quot;oberste&quot; Karte <em>(bzw. {@link Card})</em> des {@link Deck}s.
     * Sollte keine Karte <em>(bzw. {@link Card})</em> im {@link Deck} vorhanden sein, so wird
     * die Meldung:<br />
     * &nbsp; &nbsp;<code>>>>> ERROR !!! : no more cards in deck</code><br />
     * auf dem Bildschirm ausgegeben.
     */
    //@ChunkPreamble ( lastModified="2012/02/12", lastModifiedBy="Michael Schaefers" )
    public void removeTopCard(){
       if (pack1st.isEmpty()){
           System.out.printf("\n>>>> ERROR !!! : no more cards in deck\n");
       }else{
           pack2nd.add( pack1st.remove( 0 ) );
       }//if
    }//method()
    
    //@ChunkPreamble ( lastModified="2012/11/19", lastModifiedBy="Michael Schaefers" )
    @Override
    public String toString(){
        StringBuffer resu = new StringBuffer();        
        for( Card c : pack1st )  resu.append( c );
        return resu.toString();
    }//method()
    
    
    //@ChunkPreamble ( lastModified="2012/11/19", lastModifiedBy="Michael Schaefers" )
    @Override
    public boolean equals( final Object other ) {
        return getClass() == other.getClass()
            && pack1st!=null && ((Deck)other).pack1st!=null && pack1st.equals( ((Deck)other).pack1st )
            && pack2nd!=null && ((Deck)other).pack2nd!=null && pack2nd.equals( ((Deck)other).pack2nd );
    }//method()
    
    //@ChunkPreamble ( lastModified="2012/11/19", lastModifiedBy="Michael Schaefers" )
    @Override
    public int hashCode() {
        final int prime = 31;
        return ((pack1st==null) ? 0 : pack1st.hashCode())  +  prime*((pack2nd==null) ? 0 : pack2nd.hashCode());
    }//method()
    
}//class
