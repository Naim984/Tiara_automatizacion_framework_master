package com.test.pages;

import com.google.inject.Inject;
import com.santander.appian.keywords.Utils.SeleniumHelper;
import com.test.LoadProperties;
import com.test.helper.BasePage;
import com.test.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tools extends BasePage {

    @Inject SeleniumHelper seleniumHelper;
    @Inject Utils utils;
    @Inject BasePage basePage;

    @FindAll({  @FindBy(xpath = "//*[@id=\"appian-nprogress-parent\"]"),
            @FindBy(id = "appian-nprogress-parent") })
    private PageElement loadProgressBar;
    private String loadProgressBarLocator= "appian-nprogress";
    public void waitForPageLoaded(){
        driver.manage().timeouts().pageLoadTimeout(LoadProperties.LARGE_WAIT, TimeUnit.SECONDS);
    }

    public boolean waitforElementIsNotVisible(String webElementString) {
        try {
            WebElement webElement = this.seleniumHelper.getElement(webElementString, 1);
            WebDriverWait wait = new WebDriverWait(driver, 15L);
            wait.ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.invisibilityOf(webElement));
            return true;
        } catch (Exception var4) {
            throw var4;
        }
    }

    public void waitforLoadProgressBarNotVisible() {
        try{
//            WebDriverWait wait = new WebDriverWait(driver, LoadProperties.LARGE_WAIT);
//            wait.ignoring(StaleElementReferenceException.class);
//            wait.until(ExpectedConditions.invisibilityOf(loadProgressBar));
            new WebDriverWait(driver, LoadProperties.LARGE_WAIT).until(ExpectedConditions.invisibilityOfElementLocated(By.id(loadProgressBarLocator)));

        }catch (Exception ignored){
            System.out.println("Error en la espera del ProgressBar.");
        }

    }


    public void acceptDialogBrowser(){
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Mensaje de la Alerta: " + alertText);
        alert.accept();
    }

    public String getTypeFieldByPlaceholder(String placerholder){
        WebElement we = this.seleniumHelper.getElement("xpath://*[contains(text(), '" + placerholder +"')]/parent::div");
        String att = we.getAttribute("role");
        String tag = we.getTagName();
        if(att == null){
            we = this.seleniumHelper.getElement("xpath://*[contains(text(), '" + placerholder + "')]/parent::div/child::*[1]");
            att = we.getAttribute("role");
            tag = we.getTagName();
            if (att == null){
                we = this.seleniumHelper.getElement("xpath://*[contains(text(), '" + placerholder + "')]//following::input[1]");
                tag = we.getTagName();
                att = we.getAttribute("role");
                if(att == null && !tag.contentEquals("input")){
                    try{
                        we = this.seleniumHelper.getElement("xpath://label[contains(text(),'"+ placerholder +"')]/following::*[@role='textbox'][1]");
                        return "textbox:" + we.getTagName();

                    }catch (Exception e){
                        we = this.seleniumHelper.getElement("xpath://label[contains(text(),'"+ placerholder +"')]/following::*[@role='listbox'][1]");
                        return "listbox:" + we.getTagName();
                    }
                }
                return "label"+ ":" + tag;
            }else {
                return "norole" + ":" + tag;
            }
        }else{
            return att + ":" + tag;
        }

    }

    public boolean elementExist(WebElement element){
        try{
            element.isDisplayed();
            element.isEnabled();
            element.getTagName();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean elementExist(String elementLocator){
        try{
            WebElement element = this.seleniumHelper.getElement(elementLocator);
            element.isDisplayed();
            element.isEnabled();
            element.getTagName();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public WebElement buscarElementoPorValorEnDropBox(String campo, String valor, int saltoDeBusqueda){
        String opcionScroll = "";
        boolean controlador = true;
        int contadorVueltasWhile = 0;
        while(controlador && contadorVueltasWhile != 100) {
            By localizador = By.xpath("//span[contains(text(), '" + campo + "')]/parent::div/following::li");
            List<WebElement> listaDeElementos = this.basePage.driver.findElements(localizador);
            for (int i = 0; i < listaDeElementos.size(); i++) {
                if (listaDeElementos.get(i).getText().contentEquals(valor)) {
                    opcionScroll = listaDeElementos.get(i).getText();
                    controlador = false;
                    break;
                }
            }
            String ultimoElemento = listaDeElementos.get(listaDeElementos.size()-5).getText();
            WebElement we = basePage.driver.findElement(By.xpath("//div[contains(text(),'" + ultimoElemento +"')]/parent::*[@role='option'][1]"));
            Actions actions = new Actions(driver);
            actions.moveToElement(we);
            for (int i = 0; i < saltoDeBusqueda; i++) {
                actions.sendKeys(Keys.ARROW_DOWN);
                actions.build().perform();

            }
            contadorVueltasWhile++;
        }
        if (!controlador){
            System.out.println("--------------------------------");
            System.out.println("Elemento encontrado: " + opcionScroll);
            WebElement elementoFinal = this.seleniumHelper.getElement("xpath://div[contains(text(),'" + opcionScroll +"')]/parent::*[@role='option'][1]");
            System.out.println("Texto del elemento encontrado: " + elementoFinal.getText());
            return elementoFinal;
        }else {
            System.out.println("--------------------------------");
            System.out.println("Elemento NO encontrado. ");
            return null;
        }


    }




}
