package com.manish.singleton.approach_02;

import com.manish.exceptions.NonDeterministicStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestClass.class);

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
        *
        */
        testClass.checkIfCalculatorClassImmuneToReflection();

        /*
        *   if serialization process creates a new object of the
        *   class, than it is not well written singleton..!
        * */
        testClass.checkIfCalculatorClassImmuneToSerialization();

        /**
         *  if we can create clone of singleton class than
         *  it is not singleton in true sense..!
         * */
        testClass.checkIfCalculatorClassIsImmuneToCloning();
    }

    private void checkAllCalculatorInstanceReferringToSameObject() {
        Calculator firstCalculator = Calculator.getInstance();
        Calculator secondCalculator = Calculator.getInstance();
        Calculator thirdCalculator = Calculator.getInstance();

        boolean isReferringToSameObject =
            isReferringToSameObject(firstCalculator,
                    secondCalculator,
                    thirdCalculator);


        if(!isReferringToSameObject)
            LOGGER.error("Broken Singleton..!");
        else
           LOGGER.info("All Objects Are Referring To Same Object..!");
    }

    private boolean isReferringToSameObject(Calculator...calculators) {
        Set<Calculator> set = new HashSet<>(Arrays.asList(calculators));
        return set.size() == 1;
    }

    private void checkIfCalculatorClassImmuneToReflection() {
        Constructor[] constructors = Calculator.class.getDeclaredConstructors();
        Arrays.stream(constructors).forEach(constructor -> constructor.setAccessible(true));
        for(Constructor constructor: constructors) {
            try {
                Object object = constructor.newInstance();
                if(object != null)
                    System.err.println("Singleton Calculator is not immune to reflection..!");
            } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                LOGGER.error("Exception in Creating Object By Reflection", e);
                LOGGER.info("Immune to Reflection Attack..!");
            }
        }
    }

    private void checkIfCalculatorClassImmuneToSerialization() {
        String fileName = "serialized_files/calculator.ser";

        if(saveSerializeObjectToFile(fileName)) {
            try {
                boolean isSame = checkDeserializedObjectIsSameAsSingletonObject(fileName);
                if(!isSame) {
                    LOGGER.info("Immune to Serialization Attack..!");
                }
            } catch (NonDeterministicStateException nonDeterministicStateException) {
                LOGGER.error("Can't find Calculator Class is Serialization attack immune or not",
                        nonDeterministicStateException);
            }
        }
    }

    private boolean saveSerializeObjectToFile(final String fileName) {
        Calculator calculator = Calculator.getInstance();
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(calculator);
            LOGGER.info("Calculator Object is Saved To File..!");
            return true;

        } catch (FileNotFoundException fileNotFoundException) {
            LOGGER.error("File Not Found With Name:: {}", fileName, fileNotFoundException);
            return false;
        } catch (IOException ioException) {
            LOGGER.error("Exception in Saving Object in file {}", fileName, ioException);
            return false;
        }
    }

    private boolean checkDeserializedObjectIsSameAsSingletonObject(final String fileName) {
        try(FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Calculator calculatorFromFile = (Calculator) objectInputStream.readObject();
            Calculator originalSingletonCalculator = Calculator.getInstance();
            LOGGER.info("Calculator Object From File {}", calculatorFromFile);
            LOGGER.info("Original Singleton Calculator {}", originalSingletonCalculator);

            if(calculatorFromFile != originalSingletonCalculator) {
                LOGGER.error("Calculator class is not Immune to Serialization Attack..!");
                return true;
            }

            return false;
        } catch (FileNotFoundException fileNotFoundException) {
            LOGGER.error("File Not Found With Name:: {}", fileName, fileNotFoundException);
            throw new NonDeterministicStateException("File Not Found With Name:: " + fileName, fileNotFoundException);
        } catch (IOException ioException) {
            LOGGER.error("IO exception in Reading Object From File:: {}", fileName, ioException);
            throw new NonDeterministicStateException("IO exception in Reading Object From File:: " + fileName, ioException);
        } catch (ClassNotFoundException classNotFoundException) {
            LOGGER.error("Class Not Found In Deserialization of Calculator ", classNotFoundException);
            throw new NonDeterministicStateException("Class Not Found In Deserialization of Calculator " , classNotFoundException);
        }
    }

    private void checkIfCalculatorClassIsImmuneToCloning() {
        Calculator calculator = Calculator.getInstance();
        try {
            Calculator calculatorClone = (Calculator) calculator.clone();
            LOGGER.info("Original Calculator Object {}", calculator);
            LOGGER.info("Cloned Calculator Object {}", calculatorClone);
            LOGGER.error("Calculator class is not immune to Clone attack ");
        } catch (CloneNotSupportedException e) {
            LOGGER.error("Exception in Cloning Calculator Object ", e);
            LOGGER.info("Calculator class is Clone attack proof");
        }
    }
}
