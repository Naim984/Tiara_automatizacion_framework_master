//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package utils;

import com.google.inject.Inject;
import com.santander.appian.keywords.Utils.CommonFunctions;
import com.test.helper.BasePage;
import java.io.File;

import com.test.stepDefs.AppianKeywords;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotUtils extends BasePage {
    final String USER_DIR = System.getProperty("user.dir");
    public String folderName = null;
    public String DATESTRING = null;
    @Inject
    AppianKeywords keywordsSteps;
    @Inject
    CommonFunctions cmnFunct;
    String stringScenarioName = null;
    String screenShotDir;
    String screenShotDir_CucumberReport;

    public ScreenshotUtils() {
        this.screenShotDir = this.USER_DIR + "\\src\\test\\resources\\TestData\\Screenshots\\";
        this.screenShotDir_CucumberReport = this.USER_DIR + "/target/Screenshots/";
    }

    public String getDateTimestamp() {
        return this.cmnFunct.getCurrentDate();
    }

    public String dateSplitter(String str, String formatter) {
        String[] strings = str.split(formatter);
        return strings[0] + strings[1] + strings[2];
    }

    public void directory(String directoryname) {
        this.folderName = this.stringScenarioName;
        this.DATESTRING = this.getDateTimestamp();
        File dir = new File(this.screenShotDir + this.folderName);
        dir.mkdir();

        try {
            File scrnsht = (File)((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrnsht, new File(directoryname + this.folderName + "\\" + this.DATESTRING + ".png"));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public void takeScreenShot() {
        this.folderName = this.keywordsSteps.testScenarioName.replace("\"", "");
        this.DATESTRING = this.getDateTimestamp();
        File dir = new File(this.screenShotDir + this.folderName);
        dir.mkdir();

        try {
            File scrnsht = (File)((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrnsht, new File(this.screenShotDir + this.folderName + "\\" + this.DATESTRING + ".png"));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public void takeScreenShot(String screenshotName) {
        File dir = new File(this.screenShotDir_CucumberReport);
        dir.mkdir();
        System.out.println(dir.toString());

        try {
            File scrnsht = (File)((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrnsht, new File(this.screenShotDir_CucumberReport + "/" + screenshotName + ".png"));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
}
