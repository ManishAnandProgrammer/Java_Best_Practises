package com.manish.singleton.approach_01;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

        // check if all objects instance are referring to the same object
        // if not than singleton is not implemented properly
        testClass.checkAllCalculatorInstanceReferringToSameObject();

        /*
        *  check if we can create a new object using reflection, if we
        *  are able to do this, than our Singleton class is not
        *  reflection attack safe.
        * */
        testClass.checkIfCalculatorClassImmuneToReflection();
    }

    private void checkIfCalculatorClassImmuneToReflection() {
        Constructor[] constructors = Calculator.class.getDeclaredConstructors();
        Arrays.stream(constructors).forEach(constructor -> constructor.setAccessible(true));
        for(Constructor constructor: constructors) {
            try {
                Object object = constructor.newInstance();
                Calculator calculator = (Calculator) object;
                if(calculator != null)
                    System.err.println("Singleton Calculator is not immune to reflection..!");
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                System.out.println("Immune to Reflection Attack..!");
            }
        }
    }

    private void checkAllCalculatorInstanceReferringToSameObject() {
        Calculator firstCalculator = Calculator.CALCULATOR;
        Calculator secondCalculator = Calculator.CALCULATOR;
        Calculator thirdCalculator = Calculator.CALCULATOR;

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
