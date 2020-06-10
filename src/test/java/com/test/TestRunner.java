package com.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber.html",
                "junit:target/cucumber.xml",
                "json:target/cucumber-report.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue = { "classpath:com/test/stepDefs","classpath:com/test/hooks"},
        junit = "--step-notifications",
        features = {"classpath:features"},
        tags = {"@wip"})
public class TestRunner {

}
//"classpath:com/santander/appian/keywords/features"
//,"classpath:com/santander/appian/keywords/stepDefs"

// TAGS:
/*
"@RC"
"@CreacionTareasManual"
"@GdM"
"@FiltrosTareas"
"@CasosPositivos"
"@wip"

 */