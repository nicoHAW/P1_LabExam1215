package _untouchable_.thingy;


import java.util.Random;


//"HOME-VCS": git@git.HAW-Hamburg.de:SHF/Px/LabExercise/CXZ_Thingy[.git]
/**
 * Die Klasse ItemFabric erlaubt es mittels der create()-Methoden Items zu generieren.
 * Die ItemFabric ist &quot;optimiert&quot;f&uuml;r Pr&uuml;fungen.
 *<br />
 * 
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  2021/06/26 #007
 */
public class ItemFabric {
    
    static final private int numberOfColors = Color.values().length;
    static final private int numberOfSizes = Size.values().length;
    static final private int numberOfWeights = Weight.values().length;
    static final private int defaultNumberOfValues = 1_000_000;                 // "int" as tribute to "Random"-class
    
    static final private Random randomGenerator = new Random();
    
    
    /**
     * Die (statische) Methode createRandomBasedItem() generiert ein zufaelliges Item.
     * 
     * @return zufaellig generiertes Item
     */
    static public Item createRandomBasedItem(){
        final Color randomGeneratedColor = Color.values()[ randomGenerator.nextInt( numberOfColors ) ];     // any color
        final Size randomGeneratedSize = Size.values()[ randomGenerator.nextInt( numberOfSizes ) ];         // any size
        final Weight randomGeneratedWeight = Weight.values()[ randomGenerator.nextInt( numberOfWeights ) ]; // any weight
        final Long ramdonGeneratedValue = (long)( randomGenerator.nextInt( defaultNumberOfValues ));        // any value
        final Item randomGeneratedItem = new Item( randomGeneratedColor, randomGeneratedSize, randomGeneratedWeight, ramdonGeneratedValue );
        return randomGeneratedItem;
    }//method()
    
    
    
    
    
    
    
    
    
    
    static final private long defaultErzwinger = 2_147_483_647L;                // 2147483647 is Integer.MAX_VALUE and prime number !
    
    // configure characteristic of item attribute value
    final private int numberOfValues;
    final private int modulus;
    final private long erzwinger;                                               // TODO is there NO englisch name ?
    final private boolean forceLongAsTypeForValue;
    
    
    
    
    
    /**
     * Der Konstruktor erzeugt ein Item ... TODO
     * 
     * @param numberOfValues  Anzahl unterschiedliche Werte die der Item-Wert (value) annehmen kann
     * @param modulus Begrenzung des creationsValues ... TODO
     * @param erzwinger &quot;multiplicand&quot; to force usage of type Long/long for item value
     * @param forceLongAsTypeForValue  stellt sicher das Long/long fuer den Item-Wert (value) verwendet werden muss 
     */
    public ItemFabric(
        final int numberOfValues,
        final int modulus,
        final long erzwinger,
        final boolean forceLongAsTypeForValue
    ){
        this.numberOfValues = numberOfValues;
        this.modulus = modulus;
        this.erzwinger = erzwinger;
        this.forceLongAsTypeForValue = forceLongAsTypeForValue;
    }//constructor()
    //
    /**
     * Der Konstruktor erzeugt ein Item ... TODO
     * 
     * @param numberOfValues  Anzahl unterschiedliche Werte die der Item-Wert (value) annehmen kann
     * @param modulus Begrenzung des creationsValues ... TODO
     * @param forceLongAsTypeForValue  stellt sicher das Long/long fuer den Item-Wert (value) verwendet werden muss 
     */
    public ItemFabric(
        final int numberOfValues,
        final int modulus,
        final boolean forceLongAsTypeForValue
    ){
        this( numberOfValues, modulus, defaultErzwinger, forceLongAsTypeForValue );
    }//constructor()
    //
    public ItemFabric( final int numberOfValues,  final boolean forceLongAsTypeForValue ){
        this( numberOfValues, Integer.MAX_VALUE, forceLongAsTypeForValue );
    }//constructor()
    //
    public ItemFabric( final int numberOfValues ){
        this( numberOfValues, true );
    }//constructor()
    //
    public ItemFabric( final boolean forceLongAsTypeForValue ){
        this( defaultNumberOfValues, forceLongAsTypeForValue );
    }//constructor()
    //
    public ItemFabric(){
        this( defaultNumberOfValues );
    }//constructor()
    
    
    
    
    
    /**
     * Die Methode createDeterministicItem() generiert ein Item basierend auf dem creationValue.
     * 
     * @param creationValue ist der &quot;magische&quot; Wert fuer die Objekterzeugung
     * @return generiertes Item
     */
    public Item createDeterministicItem( final int creationValue ){
        final int adaptedCreationValue =
           ( Integer.MAX_VALUE >= modulus )
               ?   creationValue
               :   creationValue % modulus;
        
        final Item generatedItem = new Item(
            Color.values()[  adaptedCreationValue % numberOfColors ],
            Size.values()[   adaptedCreationValue % numberOfSizes ],
            Weight.values()[ adaptedCreationValue % numberOfWeights ],
            ( forceLongAsTypeForValue )
                ?   (adaptedCreationValue % numberOfValues) * erzwinger
                :   (adaptedCreationValue % numberOfValues)
        );
        return generatedItem;
    }//method()
    
    
    
    
    
    
    
    
    
    
    //--------------------------------------------------------------------------
    //
    // Kern Idee ist
    //
    //  final Item currentItem = new Item(
    //      Color.values()[  itemGenerationId % Color.values().length ],
    //      Size.values()[   itemGenerationId % Size.values().length ],
    //      Weight.values()[ itemGenerationId % Weight.values().length ],
    //      2_147_483_647L*( itemGenerationId % requestedDifferentValues )
    //  );
    
}//class