package com.thor.tech.roman;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.HashMap;

public class TestRomanNumbers {

    private final HashMap<Integer,String > expectedNumber = new HashMap<Integer,String >();


    @Before
    public void runBeforeTestMethod() {

        expectedNumber.put(1,"I");
        expectedNumber.put(2,"II");
        expectedNumber.put(3,"III");
        expectedNumber.put(4,"IV");
        expectedNumber.put(5,"V");
        expectedNumber.put(6,"VI");
        expectedNumber.put(7,"VII");
        expectedNumber.put(8,"VIII");
        expectedNumber.put(9,"IX");
        expectedNumber.put(10,"X");
        expectedNumber.put(11,"XI");
    }


    @Test
    public void test_method_1() {
        for (int number=1; number < 11; number++ ){

            final RomanNumber romanNumber = new RomanNumber();
            final String result =romanNumber.convertFromNumberRoman(number,"");
            assertEquals(expectedNumber.get(number), result);
        }

        System.out.println("@Test - test_method_1");
    }


    @Test
    public void test_method_2() {

        final RomanNumber romanNumber = new RomanNumber();
        final String result =romanNumber.convertFromNumberRoman(455,"");
        assertEquals("CDLV", result);
    }

    @Test
    public void test_method_2_java_8() {

        final RomanNumber romanNumber = new RomanNumber();
        final String result =romanNumber.convertFromNumberRomanJava8(455,"");
        assertEquals("CDLV", result);
    }

    @Test
    public void test_method_1_Java_8() {
        for (int number=1; number < 11; number++ ){

            final RomanNumber romanNumber = new RomanNumber();
            final String result =romanNumber.convertFromNumberRomanJava8(number,"");
            assertEquals(expectedNumber.get(number), result);
        }
    }

}