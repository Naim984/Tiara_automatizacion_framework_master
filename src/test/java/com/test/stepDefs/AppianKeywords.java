package com.test.stepDefs;

import com.google.inject.Inject;
import com.santander.appian.keywords.Utils.CommonFunctions;
import com.santander.appian.keywords.Utils.ScreenshotUtils;
import com.santander.appian.keywords.Utils.SeleniumHelper;
import com.test.helper.BasePage;
import com.test.helper.NavigationHelper;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.*;

import static org.openqa.selenium.Keys.*;

public class AppianKeywords extends BasePage {
    public static String usernameElement = "id:un";
    public static String passwordElement = "id:pw";
    public String testScenarioName = null;
    @Inject
    SeleniumHelper seleniumHelper;
    @Inject
    NavigationHelper navigationHelper;
    @Inject
    ScreenshotUtils screenshotUtils;
    @Inject
    CommonFunctions commonFunctions;

    public AppianKeywords() {
    }

    @Before
    public void getScenarioName(Scenario scenario) {
        this.testScenarioName = scenario.getName();

    }

    @Given("^Selecciono el menu \"([^\"]*)\"$")
    public void iClickOnMenu(String menuName) {
        this.seleniumHelper.getElement("xpath://*[@role='tablist']/li[@title='" + menuName + "']/a", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Selecciono el primer elemento de la tabla \"([^\"]*)\"$")
    public void iClickOnTheFirstRecordOfGridOrTable(String tableName) {
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + tableName + "')]/following::table[1]/tbody[1]/tr[1]/td[1]/*[last()]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Selecciono el ultimo elemento de la tabla  \"([^\"]*)\"$")
    public void iClickOnTheLastRecordOfGridOrTable(String tableName) {
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + tableName + "')]/following::table[1]/tbody[1]/tr[last()]/td[1]/*[last()]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que aparecen (\\d+) registros en la tabla  \"([^\"]*)\"$")
    public void iVerifyThatRecordsAppearInTheGridOrTable(int numberofRecords, String tableName) {
        int recordCounter = this.driver.findElements(By.xpath("//*[contains(text(),'" + tableName + "')]/following::table[1]/tbody[1]/tr/td[1]/*[last()]")).size();
        Assert.assertEquals(numberofRecords, recordCounter);
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Selecciono el primer registro de la seccion \"([^\"]*)\"$")
    public void iClickOnFirstRecordOfSection(String sectionName) {
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')]/following::table[1]/tbody[1]/tr[1]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que la cabecera de la columna \"([^\"]*)\" se visualiza en la tabla \"([^\"]*)\"$")
    public void iVerifyTheColumnHeadersAndAreDisplayedForTheTable(String headers, String tableName) {
        List<String> expected_list = Arrays.asList("\\|".split(headers.trim()));
        System.out.println("Expect Headers" + expected_list);
        Set<String> setExpected = new HashSet<>(expected_list);
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + tableName + "')]/following::tr[1]/th/div[contains(text(),'" + expected_list.get(0) + "')]", 1).isDisplayed());
        List<WebElement> gridcols = this.seleniumHelper.getElementList("xpath", "//*[contains(text(),'" + tableName + "')]/following::tr[1]/th/div[1]");
        ArrayList<String> actual_list = new ArrayList<>();

        for (WebElement ele : gridcols) {
            actual_list.add(ele.getText());
        }

        System.out.println("Actual Headers" + actual_list);
        Set<String> setActual = new HashSet<>(actual_list);
        Assert.assertTrue(setActual.containsAll(setExpected));
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Selecciono la opcion \"([^\"]*)\" del campo desplegable \"([^\"]*)\"$")
    public void iSelectTheDropdownWithValue(String dropdownValue, String dropdownName) {
        this.seleniumHelper.appianDropdown("xpath://*[text()='" + dropdownName + "']/following::*[@role='listbox'][1]", dropdownValue);
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Accedo con el usuario \"([^\"]*)\" y la contraseña \"([^\"]*)\" y presiono el boton \"([^\"]*)\"$")
    public void iSetupLoginWithUsernameFieldAndPasswordFieldAndLoginButton(String username, String password, String loginBtn) {
        this.seleniumHelper.enterText(usernameElement, username);
        this.seleniumHelper.enterText(passwordElement, password);
        this.seleniumHelper.enter("xpath://input[@value='" + loginBtn + "']");
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Accedo a la URL \"([^\"]*)\"$")
    public void iLaunchTheURL(String url) {
        this.navigationHelper.navigateTo(url);
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Realizo un busqueda en el campo \"([^\"]*)\" el valor \"([^\"]*)\"$")
    public void iSearchForFieldWith(String searchfield, String searchValue) {
        this.seleniumHelper.enterText("xpath://label[contains(text(),'" + searchfield + "')]/following::input[1]", searchValue);
        WebElement ele = this.driver.switchTo().activeElement();
        ele.sendKeys(TAB);
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Hago clic en grabar accion relacionada \"([^\"]*)\"$")
    public void iClickOnRecordRelatedAction(String relatedActionName) {
        this.seleniumHelper.getElement("xpath://li[@role='presentation']/following::*[contains(text(),'Related Actions')]", 1).click();
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + relatedActionName + "')]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que el registro de accion relacionada \"([^\"]*)\" esta presente$")
    public void iVerifyRecordRelatedActionIsPresent(String relatedActionName) {
        this.seleniumHelper.getElement("xpath://li[@role='presentation']/following::*[contains(text(),'Related Actions')]", 1).click();
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + relatedActionName + "')]", 1).isDisplayed());
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que el registro de accion relacionada \"([^\"]*)\" no esta presente$")
    public void iVerifyRecordRelatedActionIsNotPresent(String relatedActionName) {
        this.seleniumHelper.getElement("xpath://li[@role='presentation']/following::*[contains(text(),'Related Actions')]", 1).click();

        try {
            this.seleniumHelper.getElement("xpath://*[contains(text(),'" + relatedActionName + "')]", 1).isDisplayed();
            Assert.fail();
            this.screenshotUtils.takeScreenShot();
        } catch (org.openqa.selenium.NoSuchElementException var3) {
            Assert.assertTrue(true);
            this.screenshotUtils.takeScreenShot();
        }

    }

    @Given("^Verificar que el campo \"([^\"]*)\" esta presente$")
    public void iVerifyFieldIsPresent(String labelName) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + labelName + "')]", 1).isDisplayed());
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verificar que el campo \"([^\"]*)\" no esta presente$")
    public void iVerifyFieldIsNotPresent(String labelName) {
        try {
            this.seleniumHelper.getElement("xpath://*[contains(text(),'" + labelName + "')]", 1).isDisplayed();
            Assert.fail();
            this.screenshotUtils.takeScreenShot();
        } catch (org.openqa.selenium.NoSuchElementException var3) {
            Assert.assertTrue(true);
            this.screenshotUtils.takeScreenShot();
        }

    }

    @Given("^Verifico que el campo \"([^\"]*)\" en la seccion \"([^\"]*)\" contiene \"([^\"]*)\"$")
    public void iVerifyFieldInSectionContains(String sectionName, String labelName, String labelValue) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')]/following::*[contains(text(),'" + labelName + "')]/following::*[contains(text(),'" + labelValue + "')]", 1).isDisplayed());
        this.screenshotUtils.takeScreenShot();
    }

    public boolean equalLists(List<String> one1, List<String> two1) {
        if (one1 == null && two1 == null) {
            return true;
        } else if (one1 != null && two1 != null && one1.size() == two1.size()) {
            List<String> one = new ArrayList<>(one1);
            List<String> two = new ArrayList<>(two1);
            Collections.sort(one);
            Collections.sort(two);
            return one.equals(two);
        } else {
            return false;
        }
    }

    @And("^Hago clic en el boton \"([^\"]*)\"$")
    public void iClickOnButton(String btnName) {
        this.seleniumHelper.getElement("xpath://button[contains(text(),'" + btnName + "')]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Hago clic en el boton \"([^\"]*)\" que esta a la izquierda del boton \"([^\"]*)\"$")
    public void iClickOnButtonWhichIsPreviousToButton(String clickonbutton, String referencebutton) {
        this.seleniumHelper.getElement("xpath://button[contains(text(),'" + referencebutton + "')][1]/preceding::*[contains(text(),'" + clickonbutton + "')][1]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Hago clic en el boton \"([^\"]*)\" que esta a la derecha del boton \"([^\"]*)\"$")
    public void iClickOnButtonWhichIsNextToButton(String clickonbutton, String referencebutton) {
        this.seleniumHelper.getElement("xpath://button[contains(text(),'" + referencebutton + "')][1]/following::*[contains(text(),'" + clickonbutton + "')][1]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Hago clic sobre el primer elemento de la columna \"([^\"]*)\" de la tabla \"([^\"]*)\"$")
    public void iClickOnTheFirstRowOfColumnOfGridOrTable(String columnNo, String tableName) {
        this.seleniumHelper.enter("xpath://*[contains(text(),'" + tableName + "')]/following::table[1]/tbody[1]/tr[1]/td[" + columnNo + "]/*[last()]/p/a");
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Hago clic en la tarjeta o parrafo que contiene el texto \"([^\"]*)\"$")
    public void iClickOnCardOrParagraphWhichContainsTextAs(String fieldName) {
        this.seleniumHelper.getElement("xpath://strong[contains(text(),'" + fieldName + "')]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que el campo \"([^\"]*)\" contiene un error de validacion \"([^\"]*)\"$")
    public void iVerifyFieldContainsValidationMessage(String fieldName, String errormessage) {
        String temp = this.seleniumHelper.getElement("xpath://*[contains(text(),'" + fieldName + "')]/following::*[@role='alert'][1]", 1).getText();
        Assert.assertEquals(temp, errormessage);
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que el campo \"([^\"]*)\" de la seccion \"([^\"]*)\" contiene el mensaje de validacion \"([^\"]*)\"$")
    public void iVerifyFieldFromSectionOrGridWhichContainsValidationMessage(String fieldname, String sectionName, String errormessage) {
        String temp = this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')]/following::*[contains(text(),'" + fieldname + "')]/following::*[@role='alert'][1]", 1).getText();
        Assert.assertEquals(temp, errormessage);
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Hago clic sobre el registro \"([^\"]*)\" y fila \"([^\"]*)\"$")
    public void iClickOnRecordTypeAndTheRow(String sectionName, String rownum) {
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')]//following::table[1]/tbody[1]/tr[" + rownum + "]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Relleno el filtro de usuario de registro \"([^\"]*)\" con \"([^\"]*)\"$")
    public void iPopulatesRecordTypeUserFilterWith(String searchfield, String searchValue) {
        this.seleniumHelper.enterText("xpath://*[contains(text(),'" + searchfield + "')]/following::input[1]", searchValue);
        WebElement ele = this.driver.switchTo().activeElement();
        ele.sendKeys(TAB);
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Borro el filtro de registro \"([^\"]*)\"$")
    public void iClearRecordTypeUserFilter(String searchfield) {
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + searchfield + "')]/following::input[1]", 1).clear();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Espero (\\d+) segundos")
    public void iWaitForSeconds(int waitingperiod) throws InterruptedException {
        Thread.sleep(waitingperiod * 1000);
    }

    @Given("Hago clic en la navegación de la cuadrícula \"([^\"]*)\" de registros$")
    public void iClickOnRecordGridNavigation(String page) {
        this.seleniumHelper.getElement("xpath://*[(@aria-label='" + page + "') or (@title='" + page + "')]", 1).click();
    }

    @Given("^Ordeno los registro de la columna \"([^\"]*)\"$")
    public void iSortRecordGridByColumn(String columnName) {
        this.seleniumHelper.getElement("xpath://*[*]/following::table[1]/thead[1]/tr[1]/th/*[contains(text(),'" + columnName + "')]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Ordeno los registro de la columna \"([^\"]*)\" de la tabla \"([^\"]*)\"$")
    public void iSortRecordGridByColumnFromTableOrGrid(String columnName, String sectionorGrid) {
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionorGrid + "')]/following::table[1]/thead[1]/tr[1]/th/*[contains(text(),'" + columnName + "')]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Relleno el campo \"([^\"]*)\" con \"([^\"]*)\"$")
    public void iPopulateFieldWith(String searchfield, String searchValue) {
        this.seleniumHelper.enterText("xpath://label[contains(text(),'" + searchfield + "')]/following::input[1]", searchValue);
        WebElement ele = this.driver.switchTo().activeElement();
        ele.sendKeys(TAB);
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Hago clic en el campo \"([^\"]*)\" para plegarlo o desplegarlo$")
    public void iClickASectionToExpandOrCollapse(String sectionorGrid) {
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionorGrid + "')]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Relleno el campo \"([^\"]*)\" de la seccion \"([^\"]*)\" con \"([^\"]*)\"$")
    public void iPopulateFieldInSectionWith(String sectionName, String searchfield, String searchValue) {
        this.seleniumHelper.enterText("xpath://*[contains(text(),'" + sectionName + "')]/following::*[contains(text(),'" + searchfield + "')]/following::input[1]", searchValue);
        WebElement ele = this.driver.switchTo().activeElement();
        ele.sendKeys(TAB);
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que el enlace \"([^\"]*)\" esta presente$")
    public void iVerifyLinkIsPresent(String link) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://a[contains(text(),'" + link + "')]", 1).isDisplayed());
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que el enlace \"([^\"]*)\" no esta presente$")
    public void iVerifyLinkNotPresent(String link) {
        try {
            this.seleniumHelper.getElement("xpath://a[contains(text(),'" + link + "')]", 1).isDisplayed();
            Assert.fail();
            this.screenshotUtils.takeScreenShot();
        } catch (org.openqa.selenium.NoSuchElementException var3) {
            Assert.assertTrue(true);
            this.screenshotUtils.takeScreenShot();
        }

    }

    @Given("^Verifico que el enlace \"([^\"]*)\" esta presente en la seccion \"([^\"]*)\"$")
    public void iVerifyLinkIsPresentInSECTION(String link, String sectionName) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')]/following::a[contains(text(),'" + link + "')]", 1).isDisplayed());
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que la URL del enlace \"([^\"]*)\" contiene el texto \"([^\"]*)\"$")
    public void iVerifyLinkURLContains(String link, String val) {
        String temp = this.seleniumHelper.getElement("xpath://a[contains(text(),'" + link + "')]", 1).getText();
        Assert.assertTrue(temp.contains(val));
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que la URL del enlace \"([^\"]*)\" contiene el texto \"([^\"]*)\" en la seccion \"([^\"]*)\"$")
    public void iVerifyLinkURLContainsInSection(String link, String val, String sectionName) {
        String temp = this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')]/following::a[contains(text(),'" + link + "')]", 1).getText();
        Assert.assertTrue(temp.contains(val));
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que el boton \"([^\"]*)\" esta presente$")
    public void iVerifyButtonIsPresent(String btnName) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://button[contains(text(),'" + btnName + "')]", 1).isDisplayed());
    }

    @Given("^Verifico que el boton \"([^\"]*)\" no esta presente$")
    public void iVerifyButtonIsNotPresent(String btnName) {
        try {
            this.seleniumHelper.getElement("xpath://button[contains(text(),'" + btnName + "')]", 1).isDisplayed();
            Assert.fail();
        } catch (org.openqa.selenium.NoSuchElementException var3) {
            Assert.assertTrue(true);
        }

    }

    @Given("^Ordeno los registro por la columna con index \"([^\"]*)\" de la tabla \"([^\"]*)\"$")
    public void iSortRecordGridByColumnIndexFromTableOrGrid(String columnIndex, String sectionorGrid) {
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionorGrid + "')]/following::table[1]/thead[1]/tr[1]/th[" + columnIndex + "]/div", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Hago clic en el enlace \"([^\"]*)\"$")
    public void iClickOnLink(String link) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://a[contains(text(),'" + link + "')]", 1).isDisplayed());
        this.seleniumHelper.getElement("xpath://a[contains(text(),'" + link + "')]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Hago clic en el enlace \"([^\"]*)\" de la seccion o tabla \"([^\"]*)\"$")
    public void iClickOnLinkFromSectionOrTable(String link, String section) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + section + "')]/following::a[contains(text(),'" + link + "')]", 1).isDisplayed());
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + section + "')]/following::a[contains(text(),'" + link + "')]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que la tabla \"([^\"]*)\" columna \"([^\"]*)\" fila \"([^\"]*)\" contiene \"([^\"]*)\"$")
    public void iVerifyGridColumnRowContains(String sectionName, String columnNo, String rowNo, String value) {
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')][1]", 1).isDisplayed();
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')]/following::table[1]/tbody[1]/tr[" + rowNo + "]/td[" + columnNo + "]/p[contains(text(),'" + value + "')]", 1).isDisplayed();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que la tabla \"([^\"]*)\" contiene la columna \"([^\"]*)\" y la fila \"([^\"]*)\"$")
    public void iVerifyGridColumnRowContains(String sectionName, String columnNo, String rowNo, List<String> values) {
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')][1]", 1).isDisplayed();

        for (String val : values) {
            this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')]/following::table[1]/tbody[1]/tr[" + rowNo + "]/td[" + columnNo + "]/p[contains(text(),'" + val + "')]", 1).isDisplayed();
            this.screenshotUtils.takeScreenShot();
        }

    }

    @Given("^Verifico que la columna \"([^\"]*)\" de la tabla \"([^\"]*)\" esta seleccionada$")
    public void iVerifyGridRowIsSelected(String rownNum, String sectionName) {
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')][1]", 1).isDisplayed();
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')][1]/following::table[1]/tbody[1]/tr[" + rownNum + "][not (@aria-selected='false')]", 1).isDisplayed();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Hago clic sobre la opcion \"([^\"]*)\"$ del radius button")
    public void iClickOnRadioOption(String radioval) {
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + radioval + "')][1]", 1).isDisplayed();
        this.seleniumHelper.getElement("xpath://*[@role='radiogroup']/*/input[@type='radio']/following::label[contains(text(),'" + radioval + "')]", 1).isDisplayed();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Hago clic sobre el radius option \"([^\"]*)\" del campo \"([^\"]*)\"$")
    public void iClickOnRadioOptionForField(String radioval, String fieldname) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + fieldname + "')]", 1).isDisplayed());
        this.seleniumHelper.getElement("xpath:(//strong[contains(text(),'" + fieldname + "')]/following::input[@value='" + radioval + "'])[1]/following::label[1]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que el boton \"([^\"]*)\" esta deshabilitado$")
    public void iVerifyButtonIsDisabled(String btnName) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://button[(contains(text(),'" + btnName + "') and @disabled='')]", 1).isDisplayed());
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que el boton \"([^\"]*)\" esta habilitado$")
    public void iVerifyButtonIsEnabledOrNotDisabled(String btnName) {
        try {
            this.seleniumHelper.getElement("xpath://button[(contains(text(),'" + btnName + "') and @disabled='')]", 1);
            Assert.fail();
            this.screenshotUtils.takeScreenShot();
        } catch (org.openqa.selenium.NoSuchElementException var3) {
            Assert.assertTrue(true);
            this.screenshotUtils.takeScreenShot();
        }

    }

    @And("^Selecciono la opcion \"([^\"]*)\" del checkbox \"([^\"]*)\"$")
    public void iSelectTheCheckbox(String checkBoxHeading, String checkboxLabelName) {
        String checkboxElement = "xpath://*[contains(text(), '" + checkBoxHeading + "')]/following::*[@type='checkbox']/following::*[text()='" + checkboxLabelName + "']";
        this.seleniumHelper.getElement(checkboxElement, 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Verifico que todos los campos \"([^\"]*)\" estan presentes$")
    public void iVerifyAllTheFieldsPresent(String fields) {
        this.seleniumHelper.waitForPageToLoad();
        List<String> fieldsList = Arrays.asList(fields.split(","));
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + fieldsList.get(0) + "')][1]", 1).isDisplayed());

        for (String webElementLocator : fieldsList) {
            Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + webElementLocator + "')]", 1).isDisplayed());
        }

    }

    @Given("^Verifico que todos los campos \"([^\"]*)\" estan presentes en la seccion \"([^\"]*)\"$")
    public void iVerifyAllTheFieldsPresentInTheSection(String fields, String sectionName) {
        List<String> fieLdsList = Arrays.asList(fields.split(","));
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')]", 1).isDisplayed());
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')]/following::*[contains(text(),'" + fieLdsList.get(0) + "')][1]", 1).isDisplayed());

        for (String webElementLocator : fieLdsList) {
            Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + sectionName + "')]/following::*[contains(text(),'" + webElementLocator + "')]", 1).isDisplayed());
        }

    }

    @And("^Verifico que todas las opciones \"([^\"]*)\" son visibible para el desplegable \"([^\"]*)\" con valores por defecto \"([^\"]*)\"$")
    public void iVerifyAllOptionsDisplayedForDropdownWithDefaultValue(String dropDownValuesList, String dropdownFieldName, String defaultDrpDwnVal) throws InterruptedException {
        List<String> expDropDownValues = Arrays.asList(dropDownValuesList.split(","));
        List<String> actDropDownValues = this.commonFunctions.getDropdownOptions(dropdownFieldName, 1);
        if (!defaultDrpDwnVal.equals("Select an option")) {
            actDropDownValues.add(defaultDrpDwnVal);
        }

        Collections.sort(expDropDownValues);
        Collections.sort(actDropDownValues);
        Set<String> set = new HashSet<>(actDropDownValues);
        actDropDownValues.clear();
        actDropDownValues.addAll(set);

        for (String actDropDownValue : actDropDownValues) {
            Assert.assertTrue(expDropDownValues.contains(actDropDownValue));
        }

    }

    @Given("^Hago clic en la primera opcion del enlace de la columna, si el valor \"([^\"]*)\" existe en la tabla \"([^\"]*)\"$")
    public void iClickOnTheFirstColumnLinkIFExistsFromTheTableOf(String value, String sectionName) {
        String linkVal = this.seleniumHelper.getElement("xpath:(//*[contains(text(),'" + sectionName + "')]/following::tbody[1]/tr//*[contains(text(),'" + value + "')]/../preceding-sibling::td[last()]/div/p/a)[1]", 1).getText();
        System.out.println("Found link text for the value : " + linkVal);
        this.seleniumHelper.getElement("xpath:(//*[contains(text(),'" + sectionName + "')]/following::tbody[1]/tr//*[contains(text(),'" + value + "')]/../preceding-sibling::td[last()]/div/p/a)[1]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Hago clic en el primer registro de la tabla \"([^\"]*)\" que contega el valor \"([^\"]*)\"$")
    public void iClickOnTheFirstRecordOfGridOrTableWhichHasValue(String tableName, String value) {
        try {
            Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + tableName + "')]/following::table[1]/tbody[1]/tr[1]/td[1]/div/p/a[contains(text(),'" + value + "')]", 1).isDisplayed());
            this.seleniumHelper.getElement("xpath://*[contains(text(),'" + tableName + "')]/following::table[1]/tbody[1]/tr[1]/td[1]/div/p/a[contains(text(),'" + value + "')]", 1).click();
            this.screenshotUtils.takeScreenShot();
        } catch (NoSuchElementException var4) {
            System.out.println("No value exists in this table");
        }

    }

    @Given("^Presiono el boton \"([^\"]*)\" solo si esta habilitado$")
    public void iClickOnButtonOnlyWhenButtonEnabled(String btnName) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://button[(text()='" + btnName + "') and not(@disabled='')]", 1).isDisplayed());
        this.seleniumHelper.getElement("xpath://button[(text()='" + btnName + "') and not(@disabled='')]", 1).click();
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Introduzco en el area de texto de la seccion \"([^\"]*)\" para el campo \"([^\"]*)\" el valor \"([^\"]*)\"$")
    public void iInputTheTextareaForFieldFromSectionForTheFieldWithTheValue(String section, String field, String value) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + section + "')]/following::*[contains(text(),'" + field + "')]", 1).isDisplayed());
        this.seleniumHelper.enterText("xpath://*[contains(text(),'" + section + "')]/following::*[contains(text(),'" + field + "')]/following::textarea[1]", value);
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Introduzco en el area de texto de la seccion \"([^\"]*)\" para el campo \"([^\"]*)\" que contenga el valor numerico (\\d+)$")
    public void iInputTheTextareaForFieldFromSectionForTheFieldWithTheValueAndNumberOfChars(String section, String field, int nChars) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + section + "')]/following::*[contains(text(),'" + field + "')]", 1).isDisplayed());
        String value = this.commonFunctions.getRandomString(nChars);
        this.seleniumHelper.enterText("xpath://*[contains(text(),'" + section + "')]/following::*[contains(text(),'" + field + "')]/following::textarea[1]", value);
        this.screenshotUtils.takeScreenShot();
    }

    @Given("^Obtengo el valor del area de texto de la seccion \"([^\"]*)\" del campo \"([^\"]*)\" que contenga el valor \"([^\"]*)\"$")
    public void iGetTheValueFromTextareaFieldOfSectionForTheFieldWhichHasText(String section, String field, String value) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + section + "')]/following::*[contains(text(),'" + field + "')]", 1).isDisplayed());
        Assert.assertEquals(this.seleniumHelper.getText("xpath://*[contains(text(),'" + section + "')]/following::*[contains(text(),'" + field + "')]/following::textarea[1]").trim(), value.trim());
        this.screenshotUtils.takeScreenShot();
    }

    @Then("Deberia ver los enlaces a continuacion en la pagina")
    public void iShouldSeeBelowLinksOnThePage(List<String> linkNames) {

        for (String linkData : linkNames) {
            System.out.println(linkData);
            this.commonFunctions.findByLinkText(linkData).isDisplayed();
        }

    }

    @Then("Verifico que el campo \"([^\"]*)\" no esta presente bajo la seccion \"([^\"]*)\"")
    public void IverifyFiledIsNotPresentUnderSection(String fieldName, String sectionName) {
        Assert.assertFalse(this.seleniumHelper.isElementVisible("xpath:((//*[contains(text(),'" + sectionName + "')])[1]/following::*[contains(text(),'" + fieldName + "')])[1]", 2));
    }

    @And("^Verifico que la seccion \"([^\"]*)\" este presente bajo la seccion \"([^\"]*)\"")
    public void iVerifySectionPresentBelowTheSection(String childSectionName, String parentSectionName) {
        Assert.assertTrue(this.seleniumHelper.getElement("xpath://*[contains(text(),'" + parentSectionName + "')]/following::*[contains(text(),'" + childSectionName + "')][1]").isDisplayed());
    }

    @And("^Selecciono \"([^\"]*)\" del desplegable \"([^\"]*)\" con el valor por defecto \"([^\"]*)\"$")
    public void iSelectFromDropdownWithDefaultValue(String valueToSelect, String dropdownHeader, String defaultValue) {
        this.commonFunctions.selectDropdownElement(dropdownHeader, defaultValue, valueToSelect, 1);
    }

    @And("^Verifico que el desplegable con nombre \"([^\"]*)\" de la seccion \"([^\"]*)\" tenga el valor por defecto \"([^\"]*)\"$")
    public void iVerifyDropdownNameAsOfSectionWithDefaultValue(String dropdownName, String sectionName, String defaultValue) {
        try {
            this.seleniumHelper.getElement("xpath://*[text()='" + sectionName + "']/following::*[text()='" + dropdownName + "']/following::*[text()='" + defaultValue + "'][1]", 10);
        } catch (Exception var5) {
            Assert.fail("Dorpdown name doesn't exists in the section");
        }

    }

    @And("^Hago clic en el icono de la columna numero (\\d+) de la tabla \"([^\"]*)\" en la fila numero (\\d+)$")
    public void iClickOnCellElementOfTableOnRow(int cellNumber, String tableHeader, int rowNumber) {
        this.commonFunctions.iClickOnAnyCellOfAnyRowOfAnyTable(cellNumber, tableHeader, rowNumber);
    }

    @Given("^Selecciono el desplegable \"([^\"]*)\" con valor \"([^\"]*)\"$")
    public void iSelectTheDropdownWithMultipleValues(String dropdownName, String dropdownValues) throws InterruptedException {
        this.seleniumHelper.appianDropdownWithMultiValues("xpath://span[text()='" + dropdownName + "']/following::*[@role='listbox'][1]", dropdownValues);
        this.screenshotUtils.takeScreenShot();
    }

    @And("^Ingreso en el campo de fecha \"([^\"]*)\" con (\\d+) dia mas que hoy$")
    public void i_enter_date_field_with_text(String textArea, int numberOfDays) {
        this.seleniumHelper.appianEnterInDatePickerField(this.seleniumHelper.getElementName(textArea), this.seleniumHelper.getStartDateFromToday(numberOfDays), this.seleniumHelper.getElementIndex(textArea));
        this.screenshotUtils.takeScreenShot();
    }

    @Then("^Verifico que la seccion \"([^\"]*)\"  esta desplegada$")
    public void iVerifySectionsAreCollapsed(String sectionNames) {
        Assert.assertTrue(this.isSectionsCollapsed(sectionNames));
    }

    public boolean isSectionsCollapsed(String sectionNames) {
        this.sleepms(1000L);
        boolean flag = false;
        String[] list = sectionNames.split("\\|");

        for (String sectionName : list) {
            flag = this.isSectionCollapsed(sectionName);
            if (!flag) {
                System.out.println("broke at-->" + sectionName);
                break;
            }
        }

        return flag;
    }

    public boolean isSectionCollapsed(String section) {
        String collapseElement = "xpath://p[contains(.,'" + section + "')]/ancestor::div[7][@class='FieldLayout---field_layout FieldLayout---inColumnArrayLayout appian-context-last-in-list']";
        return this.seleniumHelper.isElementDisplayed(collapseElement);
    }
}


