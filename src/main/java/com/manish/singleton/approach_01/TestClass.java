package com.manish.singleton.approach_01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestClass {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        /*
            Calculator calculator = new Calculator();
            can't create object in this way, as calculator constructor is
            private
         */

        testClass.checkAllCalculatorInstanceReferringToSameObject();

    }

    private void checkAllCalculatorInstanceReferringToSameObject() {
        Calculator firstCalculator = Calculator.CALCULATOR;
        Calculator secondCalculator = Calculator.CALCULATOR;
        Calculator thirdCalculator = Calculator.CALCULATOR;

        // check if all objects instance are referring to the same object
        // if not than singleton is not implemented properly

        boolean isReferringToSameObject =
            isReferringToSameObject(firstCalculator,
                    secondCalculator,
                    thirdCalculator);


        if(!isReferringToSameObject)
            System.err.println("Broken Singleton..!");
        else
            System.out.println("All Objects Are Referring To Same Object..!");
    }

    private boolean isReferringToSameObject(Calculator ...calculators) {
        Set<Calculator> set = new HashSet<>(Arrays.asList(calculators));
        return set.size() == 1;
    }

}
