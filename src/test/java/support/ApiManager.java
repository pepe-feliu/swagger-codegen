package support;

import dto.invoker.ApiClient;
import dto.api.PetApi;
import dto.api.StoreApi;
import dto.api.UserApi;

public class ApiManager {
    private final ApiClient apiClient;

    public ApiManager() {
        this.apiClient = getDefaultApiClient();
    }

    public static ApiClient getDefaultApiClient(){
        ApiClient apiClient = new ApiClient();
        apiClient.setDebugging(false);

        return apiClient;
    }

    public PetApi getPetApi() {
        return new PetApi(apiClient);
    }

    public StoreApi getStoreApi() {
        return new StoreApi(apiClient);
    }

    public UserApi getUserApi() {
        return new UserApi(apiClient);
    }
    
}