package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features"
        , glue = "Stepdefinitions"
        , plugin = { "pretty", "html:target/Destination/report.html","html:target/Destination/xmlReport.xml" }
        , monochrome = true)

public class TestRunner {
}
