package com.manish.avoid_un_nessary_objects.string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.stream.IntStream;

public class StringObjectsWithLiterals {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringObjectsWithLiterals.class);

    public static void main(String[] args) {

        int numberOfStringObjects = 2_00_000;

        // using identity-hashmap to check all references
        // are pointing to different objects or not
        Set<String> set = Collections.newSetFromMap(new IdentityHashMap<>());

        IntStream.rangeClosed(1, numberOfStringObjects)
                .forEach(value -> {
                    // all objects which are created inside stream will be
                    // referring to the same object in SCP
                    String text = "This will be created 2_00_000 times";
                    set.add(text);
                });

        LOGGER.info("Set size should be {}", 1);
        LOGGER.info("Actual set size is {}", set.size());

        if(set.size() == 1) {
            // hence we saved 1_99_999 objects here
            LOGGER.info("There is only {} object created", 1);
        }

    }
}
