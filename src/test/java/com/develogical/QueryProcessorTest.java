package com.develogical;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QueryProcessorTest {

    QueryProcessor queryProcessor = new QueryProcessor();

    @Test
    public void returnsEmptyStringIfCannotProcessQuery() {
        assertThat(queryProcessor.process("test"), is(""));
    }

    @Test
    public void knowsAboutShakespeare() {
        assertThat(queryProcessor.process("Shakespeare"), containsString("playwright"));
    }

    @Test
    public void isNotCaseSensitive() {
        assertThat(queryProcessor.process("shakespeare"), containsString("playwright"));
    }

     // /api?q=xxxxx: what is your name
    @Test
    public void returnsName() {
        assertThat(queryProcessor.process("what is your name"), is("Gareth & Lucas"));
    }

    @Test
    public void returnsLargest() {
        assertThat(queryProcessor.process("awefawef:which of the following numbers is the largest: 2016, 20345, 132123"), is("132123"));
    }

    ///api?q=416be190: what is 14 multiplied by 19
    @Test
    public void returnsMultiplication() {
        assertThat(queryProcessor.process("416be190:what is 14 multiplied by 19"), is("266"));
    }

    // 0which of the following numbers are primes: %20584,%2047
//    @Test
//    public void returnsPrimeNumbers() {
//        assertThat(queryProcessor.process("416be190:which of the following numbers are primes: 584, 47"), is("47"));
//    }

    //  which of the following numbers is both a square and a cube: 713, 2116

    //  what is 7 minus 8
    @Test
    public void returnsMinus() {
        assertThat(queryProcessor.process("416be190:what is 7 minus 8"), is("-1"));
    }

    //  what is the 7th number in the Fibonacci%

    //  which year was Theresa May first elected as the Prime Minister of Great Britain

    @Test
    public void returnsWhichYearWasTheresaMayFirstElectedAsThePrimeMinisterOfGreatBritain() {
        assertThat(queryProcessor.process("416be190:which year was Theresa May first elected as the Prime Minister of Great Britain"), is("2017"));
    }

}
