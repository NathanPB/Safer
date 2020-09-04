package dev.nathanpb.safer;

import java.util.function.Supplier;

public class Safer {

    private static final boolean shouldLog = !"test".equals(System.getProperty("env"));

    public static void run(Runnable runner) {
        try {
            runner.run();
        } catch (Throwable e) {
            catchThatShit(e);
        }
    }

    public static <T> T run(T defaultValue, Supplier<T> runner) {
        try {
            return runner.get();
        } catch (Throwable e) {
            catchThatShit(e);
            return defaultValue;
        }
    }

    public static <T> T runLazy(Supplier<T> defaultValue, Supplier<T> runner) {
        try {
            return runner.get();
        } catch (Throwable e) {
            catchThatShit(e);
            return defaultValue.get();
        }
    }


    private static void catchThatShit(Throwable t) {
        if (shouldLog) {
            new SaferException(t).printStackTrace();
        }
    }
}
