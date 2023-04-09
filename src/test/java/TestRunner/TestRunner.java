package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Features"
        , glue = "stepdefinitions"
        , plugin = { "pretty", "html:target/Destination/report.html","html:target/Destination/report_json.json","html:target/Destination/report_xml.xml" }
        ,monochrome = true)

public class TestRunner {
}
