package org.apache.coyote.http11.exception;


import org.apache.coyote.http11.HttpResponse;
import org.apache.coyote.http11.HttpStatusCode;

public class ErrorResponseHandler {

    private static final ErrorResponseHandler errorResponseHandler = new ErrorResponseHandler();

    private HttpResponse response;

    public static ErrorResponseHandler getInstance() {
        return errorResponseHandler;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }

    public void handleErrorMessage(HttpStatusCode statusCode, String errorPagePath) {
        response.statusCode(statusCode)
                .staticResource(errorPagePath);
    }
}
