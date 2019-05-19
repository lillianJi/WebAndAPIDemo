package com.Runner;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(ExtendedCucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "classpath:Features",
        glue = {"com.StepDefinitions", "com.Common"},
        plugin = {"pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
                "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml"},
        tags = {"@WebSanity"})

public class TestRunner {

}