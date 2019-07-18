package hu.feketendras.splendex.movies.network;

public class ApiError {

    private final String errorJson;
    private final Throwable throwable;

    public ApiError(String errorJson) {
        this.errorJson = errorJson;
        this.throwable = null;
    }

    public ApiError(Throwable throwable) {
        this.throwable = throwable;
        this.errorJson = null;
    }

    public String getErrorJson() {
        return errorJson;
    }

    public Throwable getThrowable() {
        return throwable;
    }

}
