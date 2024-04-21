package priceapiresults.stepdefs;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import utils.ApiConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ValidateApiSchema {

	private static final Logger log = LoggerFactory.getLogger(ValidateApiStepDef.class);
	InputStream getCurrencyPriceJsonSchema;

	@Given("Configure api schema.")
	public void configure_api_schema() {
		log.info("Setting the json schema.");
		getCurrencyPriceJsonSchema = getClass ().getClassLoader ()
				.getResourceAsStream ("getCurrencyPriceJsonSchema.json");    
	}

	@Then("validate that api response matches with json schema")
	public void validate_that_api_response_matches_with_json_schema() {
		log.info("Validation of schema begins.");
		RestAssured.
		given().baseUri(ApiConstants.baseUrl).
		when().get(ApiConstants.getUsdPrice).
		then().statusCode(200).
		and().
		assertThat().
		body (JsonSchemaValidator.matchesJsonSchema(getCurrencyPriceJsonSchema));
		log.info("schema is validated");
	}
}
