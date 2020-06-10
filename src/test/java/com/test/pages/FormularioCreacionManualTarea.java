package com.test.pages;

import com.google.inject.Inject;
import com.santander.appian.keywords.Utils.SeleniumHelper;
import com.test.helper.BasePage;
import com.test.seleniumcustomframework.extension.PageElement;
import com.test.stepDefs.FormulariosStep;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FormularioCreacionManualTarea extends BasePage {
    @Inject Tools tools;
    @Inject Utils utils;
    @Inject SeleniumHelper seleniumHelper;

    /*######### Localizadores de Elementos #########*/
    @FindBy(xpath = "//div[2]/div/div/div/div/div/button") private PageElement btnCrearTarea;
    @FindBy(xpath = "/html/body/div[1]/div[4]/div/div/div[2]/div[2]/div/div/button") private PageElement btnPopUpDeseaContinuarSi;

    public void rellenarFormulario(String formulario, DataTable table){
        ArrayList<String> headers = utils.getHeadesFromDataTable(table);
        ArrayList<String> values = utils.getValueFromDataTable(table);
        if (formulario.equalsIgnoreCase("Creacion Manual de Tareas")) {
            int cont = 0;
            for (String header : headers){
                this.rellenarCampoSegunTipoTTA(header, values.get(cont));
                cont++;
            }
        }
    }

    public void crearTarea(){
        this.btnCrearTarea.click();
        this.btnPopUpDeseaContinuarSi.click();
        waitForPageLoaded();

    }

    public void rellenarCampoSegunTipoTTA(String campo, String valor){
        WebElement we = this.seleniumHelper.getElement("xpath://*[contains(text(),'" + campo +"')]/following::*[1]/div/child::*[1]");
        String att = we.getAttribute("role");
        if(att == null){
            we.sendKeys(valor);
        }else if(att.contentEquals("listbox")){
            this.utils.appianDropdown("xpath://*[text()='"+ campo + "']/following::*[@role='listbox'][1]", valor);
        }else if(att.contentEquals("textbox")){
            this.seleniumHelper.getElement("xpath://label[contains(text(),'"+ campo +"')]/following::*[@role='textbox'][1]").sendKeys(valor);
        }
    }

}
