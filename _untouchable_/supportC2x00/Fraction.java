package _untouchable_.supportC2x00;


import java.io.Serializable;
import java.math.BigInteger;


/**
 * LabExam11xx_4XIB1-P1    (PTP/Eclipse)<br />
 *<br />
 * This code belongs to the LabExam test support routine collection as part of some LabExam<br />
 * ...
 *<br />
 *<br />
 * VCS: see ReadMe.txt
 *
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  200606 #01
 */
class Fraction implements Cloneable,Serializable {                              // package scope on purpose
    
    static Fraction add( final Fraction f1, final Fraction f2 ){                // package scope on purpose
        final Fraction resu = (Fraction)( f1.clone() );                         // the upcoming fesult
        resu.add( f2 );
        return resu;
    }//method()
    
    static Fraction mul( final Fraction f1, final Fraction f2 ){                // package scope on purpose
        final Fraction resu = (Fraction)( f1.clone() );                         // the upcoming fesult
        resu.mul( f2 );
        return resu;
    }//method()
    
    
    
    
    
    @Override
    public Object clone(){
        return new Fraction( nominator, denominator );
    }//method()
    
    
    @Override
    public String toString(){
        return String.format(
            "[<%s>:  %s/%s ]",
            Fraction.class.getSimpleName(),
            nominator.toString(),
            denominator.toString()
        );
    }//method()
    
    
    
    void add( final Fraction otherFraction ){                                   // package scope on purpose
        if( null==otherFraction )  throw new IllegalArgumentException( "null is NOT supported as argument" );
        
        // add (other) fraction
        final BigInteger expandedDenominator = denominator.multiply( otherFraction.denominator );
        final BigInteger expandedNominator = nominator.multiply( otherFraction.denominator );
        final BigInteger otherExpandedNominator = denominator.multiply( otherFraction.nominator );
        final BigInteger sumNominator = expandedNominator.add( otherExpandedNominator );
        //
        // reduce (this) fraction
        final BigInteger gcd = sumNominator.gcd( expandedDenominator );
        nominator = sumNominator.divide( gcd );
        denominator = expandedDenominator.divide( gcd );
    }//method()
    //
    void add( final Long nominator,  final Long denominator ){                  // package scope on purpose
        add( new Fraction( nominator, denominator ));
    }//method()
    //
    void add( final long nominator,  final long denominator ){                  // package scope on purpose
        add( new Fraction( new Long( nominator ),  new Long( denominator )));
    }//method()
    
    void mul( final Fraction otherFraction ){                                   // package scope on purpose
        if( null==otherFraction )  throw new IllegalArgumentException( "null is NOT supported as argument" );
        
        // multiply with (other) fraction
        final BigInteger expandedNominator = nominator.multiply( otherFraction.nominator );
        final BigInteger expandedDenominator = denominator.multiply( otherFraction.denominator );
        //
        // reduce (this) fraction
        final BigInteger gcd = expandedNominator.gcd( expandedDenominator );
        nominator = expandedNominator.divide( gcd );
        denominator = expandedDenominator.divide( gcd );
    }//method()
    //
    void mul( final Long nominator,  final Long denominator ){                  // package scope on purpose
        mul( new Fraction( nominator, denominator ));
    }//method()
    //
    void mul( final long nominator,  final long denominator ){                  // package scope on purpose
        mul( new Fraction( new Long( nominator ),  new Long( denominator )));
    }//method()    
    
    Fraction reduce( final Fraction originalFraction ){                         // package scope on purpose
        final BigInteger nominator = originalFraction.nominator;
        final BigInteger denominator = originalFraction.denominator;
        final BigInteger gcd = nominator.gcd( denominator );
        final BigInteger reducedNominator = nominator.divide( gcd );
        final BigInteger reducedDenominator = denominator.divide( gcd );
        return new Fraction( reducedNominator, reducedDenominator );
    }//method()
    
    
    
    Double toPercentagePoint(){                                                 // package scope on purpose
        final Long factor = 100 * accuracyFactor;                               // 1 <=> 100% ,  hence multiply with 100
        
        final BigInteger dividend = nominator.multiply( new BigInteger( factor.toString() ));
        final BigInteger divisor = denominator;
        final BigInteger quotient = divideAndRoundHalfAwayFromZero( dividend, divisor );
        final Double percentagePointWithImprovedAccuracy = Double.valueOf( quotient.toString() );
        final Double percentagePoint = percentagePointWithImprovedAccuracy  / accuracyFactor;
        return percentagePoint;
    }//method()
    private BigInteger divideAndRoundHalfAwayFromZero( final BigInteger dividend,  final BigInteger divisor ){
    //
        //
        // change operands to positive (if necessary)
        boolean changeSignOfResultingQuotient = false;
        //
        BigInteger modifiedDividend = dividend;
        if( 0 > dividend.compareTo( BigInteger.ZERO )){
            modifiedDividend = dividend.negate();
            changeSignOfResultingQuotient = ! changeSignOfResultingQuotient;
        }//if
        //
        BigInteger modifiedDivisor = divisor;
        if( 0 > divisor.compareTo( BigInteger.ZERO )){
            modifiedDivisor = divisor.negate();
            changeSignOfResultingQuotient = ! changeSignOfResultingQuotient;
        }//if
        //=> at least the modfied values are positive
        
        final BigInteger[] result = modifiedDividend.divideAndRemainder( modifiedDivisor );
        BigInteger quotient = result[0];
        final BigInteger remainder = result[1];
        final BigInteger remx2 = remainder.add( remainder );
        if( 0 <= remx2.compareTo( modifiedDivisor ) ){
            // 2*rem >= divisor   =>   round away from zero
            quotient = quotient.add( BigInteger.ONE );
        }//if
        
        // correct sign if necessary
        if( changeSignOfResultingQuotient ){
            quotient = quotient.negate();
        }//if
        
        return quotient;
    }//method()
    
    
    
    
    
    Fraction( final BigInteger nominator,  final BigInteger denominator ){      // package scope on purpose
        if(( null==nominator )  ||  ( null==denominator ))  throw new IllegalArgumentException();
        this.nominator   = nominator;
        this.denominator = denominator;
    }//constructor()
    //
    Fraction( final Long nominator,  final Long denominator ){                  // package scope on purpose
        this( new BigInteger( nominator.toString() ),  new BigInteger( denominator.toString() ));
        if(( null==nominator )  ||  ( null==denominator ))  throw new IllegalArgumentException();
    }//constructor()
    //
    Fraction( final long nominator,  final long denominator ){                  // package scope on purpose
        this( new Long( nominator ),  new Long( denominator ));
        if( 0==denominator )  throw new IllegalArgumentException();
    }//constructor()
    //
    Fraction(){                                                                 // package scope on purpose
        this( 0, 1 );
    }//constructor()
    
    
    
    
    
    private BigInteger  nominator;
    private BigInteger  denominator;
    
    
    
    
    
    /*
    TODO: CONSTANT FRACTION required
    static final public Fraction zero       = new Fraction( 0,   1 );
    static final public Fraction one        = new Fraction( 1,   1 );
    static final public Fraction oneQuarter = new Fraction( 1,   4 );
    static final public Fraction oneHundred = new Fraction( 1, 100 );
    */
    
    
    static final private long accuracyFactor = 1_000_000L;                      // 6 (fractional) decimal digits behind point
    //
    static final private int numberOfBitsForDoubleMantissa = 52;
    static final private long maxDoubleMantiassa = ( 1L<<( numberOfBitsForDoubleMantissa +1 )) -1;
    //
    static{
        assert accuracyFactor <= Long.MAX_VALUE/100 : String.format( "accuracyFactor = %d >= %d",  accuracyFactor, Long.MAX_VALUE/100 );
        assert accuracyFactor <= maxDoubleMantiassa : String.format( "accuracyFactor = %d >= %d",  accuracyFactor, maxDoubleMantiassa );
    }//initializer()
    
    final static private long serialVersionUID = CentralVersionData.centralTestResultDataBaseRelatedSerialVersionUID;        // Note: TestResultTable is THE VERY class that is serialized
}//class
