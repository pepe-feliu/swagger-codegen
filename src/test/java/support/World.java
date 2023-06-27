package support;

import dto.invoker.ApiException;

public class World {
    private ApiException exception;

    public ApiException getLatestApiException(){
        return exception;
    }

    public void setLatestApiException(ApiException exception){
        this.exception = exception;
    }
}