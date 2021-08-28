package com.manish.singleton.approach_01;

import java.io.Serializable;
import java.util.Objects;

public class Calculator implements Serializable, Cloneable {

    public static final Calculator CALCULATOR = new Calculator();
    private Calculator() {
        if(Objects.nonNull(CALCULATOR))
            throw new UnsupportedOperationException("Can't call reflectively..!");
    }

    public int sum(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }

    protected Object readResolve() {
        return CALCULATOR;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
