package com.luxoft.omalovanyi.reader;

/**
 * Created by maoleks on 6/18/2016.
 */
public class JsonReaderException extends Exception{
    /**
     * Just to avoid the warning
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exception which accepts error message.
     *
     * @param message
     */
    public JsonReaderException(String message) {
        super(message);
    }

    /**
     * Exception which accepts error and cause as parameters.
     *
     * @param message
     * @param cause
     */
    public JsonReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
