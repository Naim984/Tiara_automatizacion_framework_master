package com.test.stepDefs;

import com.google.inject.Inject;
import com.santander.appian.keywords.Utils.SeleniumHelper;
import com.test.pages.Tools;
import cucumber.api.java.en.Given;
import utils.ScreenshotUtils;

public class ModulosStep {

    @Inject ScreenshotUtils screenshotUtils;
    @Inject SeleniumHelper seleniumHelper;
    @Inject Tools tools;

    @Given(".*accedo al modulo \"([^\"]*)\"$")
    public void accedoAlModulo(String keyWord) {
        tools.waitforLoadProgressBarNotVisible();
        this.seleniumHelper.getElement("xpath://*[contains(@title,'"+ keyWord +"')]/a", 1).click();

    }

}
