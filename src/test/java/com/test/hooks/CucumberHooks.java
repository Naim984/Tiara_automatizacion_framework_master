//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.hooks;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.test.configuration.Configuration;
import com.test.context.Device;
import com.test.context.DeviceContext;
import com.test.exceptions.StopTestException;
import com.test.helper.PropertiesHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import com.test.pages.Tools;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CucumberHooks {
    @Inject
    public DeviceContext deviceContext;
    @Inject
    public Device device;
    protected String scenarioName;
    @Inject
    @Named("test.application")
    private String applicationName;
    private String setUpSessionModifier = null;
    private int count = 0;

    public CucumberHooks() {
    }

    public static String getScenarioName(List<String> tags) throws Exception {
        System.out.println("*** ***");
        System.out.println("tag size = " + tags.size());
        String scenarioName = "";

        for(int x = 0; x < tags.size(); ++x) {
            System.out.println("tag name = " + (String)tags.get(x));
            if (((String)tags.get(x)).contains("Test")) {
                scenarioName = (String)tags.get(x);
            }
        }

        scenarioName = scenarioName.replace("@", "");
        Configuration.getConfiguration().setTestCaseName(scenarioName);
        return scenarioName;
    }

    @Before(
            order = 1
    )
    public void beforeScenario(Scenario scenario) throws Exception {
        System.out.printf("Cucumber hook intance: %s", this);
        List<String> tags = new ArrayList(scenario.getSourceTagNames());
        this.scenarioName = getScenarioName(tags);
        PropertiesHelper.loadRunConfigProps();
        if (this.applicationName.contains("mobile")) {
            Configuration.getConfiguration().setApplicationMode("MOBILE");
        } else if (this.applicationName.contains("api")) {
            Configuration.getConfiguration().setApplicationMode("API");
        }

        this.testLevelSetUp(this.scenarioName, tags);
        this.deviceContext.device = this.device.getDeviceType(Configuration.getConfiguration().getApplicatonMode());
        Tools tools = new Tools();


    }

    @After
    public void afterAll() throws Exception {
        System.out.println("This will run after all the scenario ");
        ++this.count;
    }

    public void testLevelSetUp(String scenario_name, List<String> tags) throws StopTestException, MalformedURLException {
        this.scenarioName = scenario_name;
        Iterator var3 = tags.iterator();

        while(var3.hasNext()) {
            String tag = (String)var3.next();
            String strippedTag = tag.replace("@", "");
            if (tag.equals("@Mobile")) {
                Configuration.getConfiguration().setApplicationMode("MOBILE");
            } else if (tag.equals("@API")) {
                Configuration.getConfiguration().setApplicationMode("API");
            }
        }

        this.setUpSessionModifier = Configuration.getConfiguration().getSetUpModifier();
    }
}
