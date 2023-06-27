package steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
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
import org.checkerframework.checker.units.qual.C;

import support.ApiManager;
import support.World;
import support.RandomNumberGenerator;
import support.RandomStringGenerator;
import dto.invoker.ApiCallback;
import dto.invoker.ApiException;
import dto.invoker.ApiResponse;
import dto.model.Category;
import dto.model.Pet;
import dto.model.Tag;
import dto.model.Pet.StatusEnum;

public class PetSteps {

    private ApiResponse<List<Pet>> petsByStatusApiResponse;
    private ApiResponse<Void> addPetApiResponse;
    private ApiResponse<Pet> getPetByIdApiResponse;

    private Long addedPetIdGlobal;
    private String petNameGlobal;

    @Given("^I look for pets by status (sold|pending|available)$")
    public void iFindPetsByStatus(String status){
        List<String> statusList = Arrays.asList(status);
        System.out.println(statusList.toArray());

        try{
            petsByStatusApiResponse = new ApiManager().getPetApi().findPetsByStatusWithHttpInfo(statusList);
        } catch (ApiException e) {
            setLatestApiException(e);
        }
    }

    @Then("I see the list of pets with that status")
    public void getPetsList(){
        Assertions.assertThat(petsByStatusApiResponse.getStatusCode()).isEqualTo(200);
    }

    @When("I add a pet")
    public void addPetToStore(){

        HashMap<String, Object> map = new HashMap<String, Object>();
        Long addedPetId = Long.valueOf(RandomNumberGenerator.generateRandomNumber(1,99999999));
        String petName = RandomStringGenerator.generateRandomName();
        //map.put("id", addedPetId);

        Pet pet = new Pet();

        pet.setId(addedPetId);
        pet.photoUrls(null);

        Category category = new Category();
        category.setId(addedPetId);
        category.name("bull");
        pet.setCategory(category);

        pet.name(petName);

        Tag tag = new Tag();
        tag.setId(addedPetId);
        tag.setName("bulltaco");
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        
        pet.status(StatusEnum.AVAILABLE);

        System.out.println(pet);

        try{
            addPetApiResponse = new ApiManager().getPetApi().addPetWithHttpInfo(pet);
        } catch (ApiException e) {
            setLatestApiException(e);
        }
        this.addedPetIdGlobal = addedPetId;
        this.petNameGlobal = petName;
    }

    @Then("The pet is added")
    public void petIsAdded(){
        Assertions.assertThat(addPetApiResponse.getStatusCode()).isEqualTo(200);
    }

    @When("I check the pet by its id")
    public void checkPetById(){
        try{
            getPetByIdApiResponse = new ApiManager().getPetApi().getPetByIdWithHttpInfo(addedPetIdGlobal);
        } catch (ApiException e) {
            setLatestApiException(e);
        }
    }

    @Then("The pet I added can be checked")
    public void petCanBeCheckedByItsId(){
        Assertions.assertThat(getPetByIdApiResponse.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(getPetByIdApiResponse.getData().getStatus()).isEqualTo(StatusEnum.AVAILABLE);
        Assertions.assertThat(getPetByIdApiResponse.getData().getCategory().getName()).isEqualTo("bull");
        Assertions.assertThat(getPetByIdApiResponse.getData().getId()).isEqualTo(addedPetIdGlobal);
        Assertions.assertThat(getPetByIdApiResponse.getData().getName()).isEqualTo(petNameGlobal);
    }

    private void setLatestApiException(ApiException e) {
    }
}