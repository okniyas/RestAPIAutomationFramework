package com.automation.steps;

import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RequestSteps {

    @Given("user wants to call {string} end point")
    public void user_wants_to_call_end_point(String endPoint) {
        if(endPoint.contains("@")){
            String bookingId = ConfigReader.getConfigValue("booking.id");
            endPoint = endPoint.replace("@id", bookingId);
        }
        RestAssuredUtils.setEndPoint(endPoint);
        
    }

    @And("set header {string} to {string}")
    public void set_header_to(String key, String value) {
        if(value.contains("@token")){
            String apiToken = ConfigReader.getConfigValue("api.token");
            value = value.replace("@token", apiToken);
        }
        RestAssuredUtils.setHeader(key, value);
        
    }

    @Given("set request body from file {string}")
    public void set_request_body_from_file(String fileName) {
        RestAssuredUtils.setBody(fileName);
        
    }

    @When("user performs post call")
    public void user_performs_post_call() {
        RestAssuredUtils.post();
        
    }


    @When("user performs put call")
    public void userPerformsPutCall() {
        RestAssuredUtils.put();
    }
}
