package com.thor.tech.roman;

import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class RomanNumber {

    private final TreeMap<Integer,String> basicRomanNumbers = new TreeMap<Integer, String>();

    public RomanNumber(){

        basicRomanNumbers.put(1,"I");
        basicRomanNumbers.put(4,"IV");
        basicRomanNumbers.put(5,"V");

        basicRomanNumbers.put(9,"IX");
        basicRomanNumbers.put(10,"X");

        basicRomanNumbers.put(40,"XL");
        basicRomanNumbers.put(50,"L");

        basicRomanNumbers.put(90,"XC");
        basicRomanNumbers.put(100,"C");

        basicRomanNumbers.put(400,"CD");
        basicRomanNumbers.put(500,"D");

        basicRomanNumbers.put(900,"CM");
        basicRomanNumbers.put(1000,"M");

    }

    @SuppressWarnings( "deprecation" )
    public String convertFromNumberRoman(int numberToBeConverted, String romanNumberAccumulator){

        final Integer lessGreaterNumber = basicRomanNumbers.floorKey(numberToBeConverted);
        final String newRomanDigit = basicRomanNumbers.get(lessGreaterNumber);
        romanNumberAccumulator = romanNumberAccumulator + newRomanDigit;
        int calculatedNumber = numberToBeConverted - lessGreaterNumber;

        if (calculatedNumber == 0 ) {  return  romanNumberAccumulator.trim();  }

        else { return  convertFromNumberRoman(calculatedNumber,romanNumberAccumulator ); }
    }


    private BiFunction<Integer, String,String> loop;

    @SuppressWarnings( "deprecation" )
    public String convertFromNumberRomanJava8(int numberToBeConverted, String romanNumberAccumulator){

       loop = (number, romanNumber) -> {

            final Integer lessGreaterNumber = basicRomanNumbers.floorKey(number);
            final String newRomanDigit = basicRomanNumbers.get(lessGreaterNumber);
            final String romanNumberNewValue = romanNumber + newRomanDigit;

            final int calculatedNumber = number - lessGreaterNumber;

            if (calculatedNumber == 0 ) {  return  romanNumberNewValue.trim();  }

            else { return  loop.apply(calculatedNumber,romanNumberNewValue ); }
        };

        return loop.apply(numberToBeConverted,"");
    }
}
