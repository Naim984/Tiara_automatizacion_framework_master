package com.test.pages;

import com.test.LoadProperties;
import com.test.annotations.Page;
import com.test.helper.BasePage;
import com.test.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactoryFinder;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class ResolutorHome extends BasePage {
    /*######### Localizadores de Elementos #########*/
    @FindAll({  @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div/nav/div[2]/div/button"),
                @FindBy(css = "div > button"),
                @FindBy(xpath = "//*[@class='Button_SITE_HEADER_LAYOUT_ICON_NAVIGATION_MENU---btn']")})
    private PageElement btnMenuNavegacion;
    @FindBy(xpath = "/html/body/div[3]/div/div/ul") private PageElement divMenuNavegacion;
    @FindBy(xpath = "/html/body/div[3]/div/div/ul/li") private List<PageElement> MenuOpciones;

    public void seleccionarOpcionDelMenu(String opcion){
        String opcionTraduccion = "";
        if(opcion.equalsIgnoreCase("Gestion Demanda")){
            opcionTraduccion = "GDM_Gestion_Demanda";
        }else{
            System.out.println("La opcion: " + opcion + " no esta implementada.");
        }

        for(PageElement opcionMenu: this.MenuOpciones){
            if (opcionMenu.getText().equalsIgnoreCase(opcionTraduccion)){
                System.out.println("por aqui: " + opcionMenu.toString());
                System.out.println("por aqui: " + opcionMenu.getText());
                scrollToElementWithJavascript(opcionMenu);
                opcionMenu.click();
                break;
            }
        }
    }

    public void abrirMenuPrincipal() {
        this.btnMenuNavegacion.click();
        try {
            waitForPageToBecomeStable(this.divMenuNavegacion);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
