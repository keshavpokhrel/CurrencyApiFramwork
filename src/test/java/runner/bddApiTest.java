package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"./src/test/resources/features/"},
		glue={"priceapiresults.stepdefs"},
		monochrome = true,
		plugin=  { 
				"pretty",
				"html:target/cucumber-reports/cucumberReport.html"
		},
		tags = "not @ignore"
		)
public class bddApiTest {

}
