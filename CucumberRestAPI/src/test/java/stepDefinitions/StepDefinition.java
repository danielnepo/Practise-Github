package stepDefinitions;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;
import cucumber.api.java.en.Then;


public class StepDefinition extends Utils{
	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuilder testData = new TestDataBuilder();
	@Given("^Add place payload with \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_payload(String address, String language) throws Exception {
		res = given().spec(requestSpecification())
		.body(testData.addplace(address, language));
	}

	@When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_with_http_request(String resource, String method) throws Exception {
		// Write code here that turns the phrase above into concrete actions
		//constructor will be called with value of resource which you pass
				APIResources resourceAPI=APIResources.valueOf(resource);
				System.out.println(resourceAPI.getResource());
				
				
				resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
				
				if(method.equalsIgnoreCase("POST"))
				 response =res.when().post(resourceAPI.getResource());
				else if(method.equalsIgnoreCase("GET"))
					 response =res.when().get(resourceAPI.getResource());
	}

	@Then("^the API call got success with status code (\\d+)$")
	public void the_API_call_got_success_with_status_code(int arg1) throws Exception {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String keyValue, String Expectedvalue) throws Exception {
		assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	}


}

