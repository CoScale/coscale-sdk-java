package com.coscale.sdk.client.exceptions;

/**
 * If there is an unsuccessful response then an CoscaleApiException will be
 * thrown. The exception will have attached the status code of the response.
 *
 */
public class CoscaleApiException extends java.io.IOException {

    /** Serialization id. */
    private static final long serialVersionUID = 2829953954182524049L;

    /** Status code for the api call. */
    public final int statusCode;

    public CoscaleApiException(int statusCode) {
        this.statusCode = statusCode;
    }

    public CoscaleApiException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public CoscaleApiException(int statusCode, Throwable cause) {
        super(cause);
        this.statusCode = statusCode;
    }

    public CoscaleApiException(int statusCode, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

}
