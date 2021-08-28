package com.manish.singleton.approach_03;

public enum Calculator {
    INSTANCE;

    public int sum(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }
}
