package com.test.pages;

import com.google.inject.Inject;
import com.test.LoadProperties;
import com.test.helper.BasePage;
import com.test.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class Navegacion extends BasePage {
    @Inject Tools tools;

    public void datosGestionDeDemanda() {
        System.out.println("neo......." + LoadProperties.getProp("appian.site.gdm"));
        driver.navigate().to(LoadProperties.getProp("appian.site.gdm"));
    }

    public void datosPipeline() {
        System.out.println("neo......." + LoadProperties.getProp("appian.site.pipeline"));
        driver.navigate().to(LoadProperties.getProp("appian.site.pipeline"));
    }

    public void seleccionarUrl(String url){
        if(url.equalsIgnoreCase("gdm")){
            datosGestionDeDemanda();
        }else if(url.equalsIgnoreCase("pipeline")){
            datosPipeline();
        }
        tools.waitForPageLoaded();
    }
}
