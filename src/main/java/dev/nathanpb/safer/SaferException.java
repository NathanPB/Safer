package dev.nathanpb.safer;

public class SaferException extends Exception {
    SaferException(Throwable cause) {
        super("Safer has prevented a possible fatal error. Please report this to the software author as soon as possible", cause);
    }
}
