package com.manish.singleton.approach_01;

import java.util.Objects;

public class Calculator {

    public static final Calculator CALCULATOR = new Calculator();
    private Calculator() {
        if(Objects.nonNull(CALCULATOR))
            throw new UnsupportedOperationException("Can't call reflectively..!");
    }

    public int sum(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }
}
