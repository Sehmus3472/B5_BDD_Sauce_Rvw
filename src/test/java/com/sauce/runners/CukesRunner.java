package com.sauce.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class) // Bu bize CukesRunner calistirir bu clas olmasa cukesRunneri calistiramayiz.
@CucumberOptions(
        plugin = { "json:target/cucumber.json",
                "html:target/defaul-html-reports.html",
                "rerun:target/rerun.txt"
        },
        features ="src/test/resources/features",
        glue="com/sauce/StepDefs",
        dryRun=false,
        tags="@wip"

)



public class CukesRunner {


}
