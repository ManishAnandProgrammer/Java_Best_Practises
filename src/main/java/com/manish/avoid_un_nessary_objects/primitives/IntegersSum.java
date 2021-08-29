package com.manish.avoid_un_nessary_objects.primitives;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

public class IntegersSum {
    public static void main(String[] args) {
        IntegersSum integersSum = new IntegersSum();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Long allIntsSumUsingWrapperLong = integersSum.allIntsSumUsingWrapperLong();
        System.out.println("Result From allIntsSumUsingWrapperLong Method:: " + allIntsSumUsingWrapperLong);

        stopWatch.stop();
        long secondsTookInExecutingAllIntsSumUsingWrapperLongMethod =
                TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime());
        System.out.println("allIntsSumUsingWrapperLong Method Took Seconds:: "
                + secondsTookInExecutingAllIntsSumUsingWrapperLongMethod);

        stopWatch.reset();
        stopWatch.start();

        long allIntsSumUsingPrimitiveLong = integersSum.allIntsSumUsingPrimitiveLong();
        System.out.println("Result From allIntsSumUsingPrimitiveLong Method:: " + allIntsSumUsingPrimitiveLong);

        stopWatch.stop();

        long secondsTookInExecutingAllIntsSumUsingPrimitiveLongMethod =
                TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime());
        System.out.println("allIntsSumUsingPrimitiveLong Method Took Seconds:: "
                + secondsTookInExecutingAllIntsSumUsingPrimitiveLongMethod);
    }

    // this took 6 seconds on my system
    // wrapping and unwrapping made this method slow
    // un-necessary object creation happening here.!
    private Long allIntsSumUsingWrapperLong() {
        Long sum = 0L;
        for(int i = 1; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }

    // this took 0 second and few milli-seconds
    private long allIntsSumUsingPrimitiveLong() {
        long sum = 0L;
        for(int i = 1; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
}
