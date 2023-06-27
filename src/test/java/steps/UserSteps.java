package steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;

import support.ApiManager;
import support.World;
import support.RandomNumberGenerator;
import support.RandomStringGenerator;
import dto.invoker.ApiCallback;
import dto.invoker.ApiException;
import dto.invoker.ApiResponse;

import dto.model.User;

public class UserSteps {

    private ApiResponse<Void> deleteUserApiResponse;
    private ApiResponse<User> getUserApiResponse;
    private ApiResponse<Void> updateUserApiResponse;
    private ApiResponse<Void> createUserApiResponse;

    String firstName = RandomStringGenerator.generateRandomName();
    String lastName = RandomStringGenerator.generateRandomLastName();
    private String usernameGlobal;

    @Given("I am a user of the petstore")
    public void userOfPetstore(){
        return;
    }

    @When("I add a new user")
    public void addUserToPetStore(){

        String username = firstName + RandomNumberGenerator.generateRandomNumber(0, 1000);
        Integer random = RandomNumberGenerator.generateRandomNumber(123456789, 987654321);

        Long phoneNumber = Long.valueOf(random);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", Long.valueOf(RandomNumberGenerator.generateRandomNumber(1,999999999)));
        map.put("username", username);
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("email", lastName + "." + firstName + RandomNumberGenerator.generateRandomNumber(0, 100) + "@test.com");
        map.put("password", "admin");
        map.put("phone", "+34" + String.valueOf(phoneNumber));
        map.put("userStatus", 0);

        User user = new User();
        user.id((Long) map.get("id"));
        user.username((String) map.get("username"));
        user.firstName((String) map.get("firstName"));
        user.lastName((String) map.get("lastName"));
        user.email((String) map.get("email"));
        user.password((String) map.get("password"));
        user.phone((String) map.get("phone"));
        user.userStatus((Integer) map.get("userStatus"));

        try{
            createUserApiResponse = new ApiManager().getUserApi().createUserWithHttpInfo(user);
        } catch (ApiException e) {
            setLatestApiException(e);
        }
        this.usernameGlobal = username;
        
    }

    private void setLatestApiException(ApiException e) {
    }

    @Then("The user is added")
    public void addedUserCheck(){
        Assertions.assertThat(createUserApiResponse.getStatusCode()).isEqualTo(200);
    }


    @When("I update the created user")
    public void updateCreatedUser(){
        String username = firstName + RandomNumberGenerator.generateRandomNumber(0, 1000);
        Integer prod = 0;

        for (Integer i = 0; i < 9; i++){
            Integer random = RandomNumberGenerator.generateRandomNumber(123456789, 987654321);
            prod = prod + random;
        }

        Long phoneNumber = Long.valueOf(prod);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", Long.valueOf(RandomNumberGenerator.generateRandomNumber(1,999999999)));
        map.put("username", username);
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("email", lastName + "." + firstName + RandomNumberGenerator.generateRandomNumber(0, 100) + "@fake.com");
        map.put("password", "admin");
        map.put("phone", "+34" + String.valueOf(phoneNumber));
        map.put("userStatus", 0);

        User user = new User();
        user.id((Long) map.get("id"));
        user.username((String) map.get("username"));
        user.firstName((String) map.get("firstName"));
        user.lastName((String) map.get("lastName"));
        user.email((String) map.get("email"));
        user.password((String) map.get("password"));
        user.phone((String) map.get("phone"));
        user.userStatus((Integer) map.get("userStatus"));

        try{
            updateUserApiResponse = new ApiManager().getUserApi().updateUserWithHttpInfo(username, user);
        } catch (ApiException e) {
            setLatestApiException(e);
        }

        this.usernameGlobal = username;
    }

    @Then("The user is updated")
    public void updatedUserCheck(){
        Assertions.assertThat(updateUserApiResponse.getStatusCode()).isEqualTo(200);
    }


    @Then("I check the info for the user")
    public void checkInfoForUser(){
        try{
            getUserApiResponse = new ApiManager().getUserApi().getUserByNameWithHttpInfo(usernameGlobal);
        } catch (ApiException e) {
            setLatestApiException(e);
        }
    }

    @And("The info is correct")
    public void checkInfoForUserCreated(){
        Assertions.assertThat(getUserApiResponse.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(getUserApiResponse.getData().getUsername()).isEqualTo(usernameGlobal);
        Assertions.assertThat(getUserApiResponse.getData().getFirstName()).isEqualTo(firstName);
        Assertions.assertThat(getUserApiResponse.getData().getLastName()).isEqualTo(lastName);
    }

    @When("I delete the created user")
    public void deleteUser(){
        try{
            deleteUserApiResponse = new ApiManager().getUserApi().deleteUserWithHttpInfo(usernameGlobal);
        } catch (ApiException e) {
            setLatestApiException(e);
        }
    }

    @Then("The user is deleted")
    public void deletedUserCheck(){
        Assertions.assertThat(deleteUserApiResponse.getStatusCode()).isEqualTo(200);
    }
}
    

    
