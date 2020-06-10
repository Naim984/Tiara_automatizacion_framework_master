package com.test.pages;

import com.google.inject.Inject;
import com.test.LoadProperties;
import com.test.helper.BasePage;
import com.test.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class PaginaLogin extends BasePage {
    @Inject Tools tools;

    /*######### Localizadores de Elementos #########*/
    @FindBy(id = "un") private PageElement inputNombreDeUsuario;
    @FindBy(id = "pw") private PageElement inputContrasena;
    @FindAll({  @FindBy(xpath = "//*[@value='Entrar']"),
                @FindBy(xpath = "//*[@id=\"loginForm\"]/div[4]/div/div[2]/input"),
                @FindBy(css = "#loginForm > div.button_box > div > div.button_box_buttons > input") })
    private PageElement btnEntrar;
    @FindBy(xpath = "//*[@id=\"loginForm\"]/div[3]/div/div/label") private PageElement cboxRecuerdame;

    /*######### KeyWords #########*/
    public void usuarioResolutorGdM(){
        inputNombreDeUsuario.sendKeys(LoadProperties.getProp("appian.datos.resolutor.usuario"));
        inputContrasena.sendKeys(LoadProperties.getProp("appian.datos.resolutor.contrasena"));
        btnEntrar.click();
    }

    public void ingresarConUsuario(String usuario){
        if(usuario.equalsIgnoreCase("Resolutor GdM")){
            usuarioResolutorGdM();
        }
        tools.waitForPageLoaded();

    }

}
