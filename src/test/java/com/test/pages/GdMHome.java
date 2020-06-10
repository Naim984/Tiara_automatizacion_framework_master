package com.test.pages;

import com.google.inject.Inject;
import com.santander.appian.keywords.Utils.SeleniumHelper;
import com.test.LoadProperties;
import com.test.helper.BasePage;
import com.test.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class GdMHome extends BasePage {
    @Inject Tools tools;
    @Inject SeleniumHelper seleniumHelper;
    /*######### Localizadores de Elementos #########*/
    @FindBy(xpath = "//nav/div[1]/ul/li[3]/a") private PageElement moduloCrearSolicitud;
    @FindBy(xpath = "//nav/div[1]/ul/li[2]/a") private PageElement moduloTarea;

    /*######### KeyWords #########*/
    public void seleccionarModulo(String modulo, int a){
        if (modulo.equalsIgnoreCase("Crear Solicitud")){
            waitForElementToBecomeClickable(this.moduloCrearSolicitud);
            this.moduloCrearSolicitud.click();
        }else if(modulo.equalsIgnoreCase("Tareas")){
            waitForElementToBecomeClickable(this.moduloTarea);
            this.moduloTarea.click();
        }
        tools.waitForPageLoaded();
    }

    public void seleccionarModulo(String modulo){


    }
}
