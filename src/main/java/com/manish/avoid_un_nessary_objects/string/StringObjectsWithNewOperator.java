package com.manish.avoid_un_nessary_objects.string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.IntStream;

public class StringObjectsWithNewOperator {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringObjectsWithNewOperator.class);

    public static void main(String[] args) {

        int numberOfStringObjects = 2_00_000;

        // using identity-hashmap to check all references
        // are pointing to different objects or not
        Set<String> set = Collections.newSetFromMap(new IdentityHashMap<>(numberOfStringObjects));

        IntStream.rangeClosed(1, numberOfStringObjects)
                .forEach(value -> {
                    // if we use constructor with string literal than we can avoid
                    // creating 2_00_000 objects here
                    // that can further save memory
                    String text = new String("This will be created 2_00_000 times");
                    set.add(text);
                });

        LOGGER.info("Set size should be {}", numberOfStringObjects);
        LOGGER.info("Actual set size is {}", set.size());

        if(set.size() == numberOfStringObjects) {
            LOGGER.error("There are {} string objects created above which is un-necessary", numberOfStringObjects);
        }

    }
}
