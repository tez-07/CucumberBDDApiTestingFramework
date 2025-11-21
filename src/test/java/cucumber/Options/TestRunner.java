package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
	    features="src/test/java/features",
	    glue = {"stepDefinitions"}, // <-- Changed to stepDefinitions (plural and correct casing)
	    tags = "@AddPlace",
	    plugin = {
	            "html:target/cucumber-reports.html",
	            "json:target/cucumber.json"},
	    monochrome = true
	)


public class TestRunner {
	// ...
	}