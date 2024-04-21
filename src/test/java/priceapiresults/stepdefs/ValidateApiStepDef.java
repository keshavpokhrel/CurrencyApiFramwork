package priceapiresults.stepdefs;

import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import priceapiresults.TestContext;
import utils.ApiConstants;

public class ValidateApiStepDef {

	private static final Logger log = LoggerFactory.getLogger(ValidateApiStepDef.class);
	TestContext testcontext;

	public ValidateApiStepDef(TestContext testcontext){
		this.testcontext = testcontext;
	}

	@Given("Api configs are created")
	public void hit_the_given_api() {
		RequestSpecification reqSpec =RestAssured.given();		
		reqSpec.relaxedHTTPSValidation();
		reqSpec.baseUri(ApiConstants.baseUrl);
		reqSpec.header("Content-Type","application/json");
		log.info("Request specfication : "+ reqSpec.toString());
		testcontext.setRqspec(reqSpec);

	}
	
	@When("Hit configure api and get price result")
	public void api_provides_the_result() {
		log.info("API URI : "+ ApiConstants.baseUrl + ApiConstants.getUsdPrice);
		Response response = testcontext.getRqspec().get(ApiConstants.getUsdPrice);
		testcontext.setResp(response);
		log.info("Response  : "+ response.getBody().asString());
	}
		
	@Then("Validate api result")
	public void validate_api_result() {
		if(testcontext.getResp().getStatusCode() != ApiConstants.HTTPS_OK ) {
			log.info("Response is invalid with Status code : "+ testcontext.getResp().getStatusCode());
			Assert.fail();
		}else {
			String result = testcontext.getResp().jsonPath().getString("result");
			if(ApiConstants.success.equalsIgnoreCase(result)) {
				log.info("Response with Status code : "+ testcontext.getResp().getStatusCode()+ "And status as : " +result);			
			}else {
				Assert.fail("Response is invalid with status : "+result);
			}
		}
	}
	@Then("check api call is successful and returns valid price")
	public void check_for_valid_price_in_result() {
		Assert.assertEquals(ApiConstants.HTTPS_OK, testcontext.getResp().getStatusCode());
		String base_code = testcontext.getResp().jsonPath().getString("base_code");
		double price = testcontext.getResp().jsonPath().getDouble("rates."+base_code);
		if(price != Double.valueOf(1)) {
			Assert.fail("The price value of "+base_code+" is incorrect : "+ price);
		}		
	}
	
	@Then("check that USD price against {string} is within range on {string} to {string}")
	public void check_that_usd_price_for_aed_is_within_range_on_to(String currency, String priceFrom, String priceTo) {
		JSONObject priceResponse = new JSONObject(testcontext.getResp().getBody().asString());
		JSONObject rates = priceResponse.getJSONObject("rates");
		double aedCurrecnyPrice = rates.getDouble(currency);
		if(Double.valueOf(priceFrom)<= aedCurrecnyPrice && aedCurrecnyPrice <= Double.valueOf(priceTo)) {
			log.info("Current "+currency+" price is : "+ aedCurrecnyPrice);
		}else {
			Assert.fail("Current "+currency+" price is not in correct range.");
		}
		
		
	}

	@Then("Validate that api result have {int} currency pairs")
	public void validate_that_api_result_have_currency_pairs(Integer currencyPairs) {
		JSONObject priceResponse = new JSONObject(testcontext.getResp().getBody().asString());
		JSONObject rates = priceResponse.getJSONObject("rates");
		int currencyKeys = rates.keySet().size();
		if(currencyKeys >= currencyPairs) {
			log.info("Total currency pairs count is : " + currencyKeys);
		}else {
			Assert.fail("Currency pairs count is less then "+ currencyPairs);
		}
	}
}
