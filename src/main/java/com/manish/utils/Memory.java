package com.manish.utils;

public class Memory {
    private Memory() {
        throw new UnsupportedOperationException("No Object For This Class Buddy..!");
    }

    public static String usedMemoryIn_KBs() {
        double kbs = usedMemory() / 1024;
        return kbs  + " kbs ";
    }

    public static String usedMemoryIn_Mbs() {
        double mbs = usedMemory() / ( 1024 * 1024 );
        return mbs + " mbs ";
    }

    private static long usedMemory() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();

        // in bytes
        return totalMemory - freeMemory;
    }
}
