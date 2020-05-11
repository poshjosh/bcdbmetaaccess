package com.bc.db.meta.access;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 4, 2019 11:55:15 PM
 */
public class SqlRuntimeException extends RuntimeException {

    /**
     * Creates a new instance of <code>SqlRuntimeException</code> without detail message.
     */
    public SqlRuntimeException() { }


    /**
     * Constructs an instance of <code>SqlRuntimeException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public SqlRuntimeException(String msg) {
        super(msg);
    }

    public SqlRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlRuntimeException(Throwable cause) {
        super(cause);
    }

    public SqlRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
