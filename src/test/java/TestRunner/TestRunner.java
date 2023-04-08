package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features"
        , glue = "stepdefinitions"
        , plugin = { "pretty", "html:target/Destination/report.html" }
        ,monochrome = true)

public class TestRunner {
}
