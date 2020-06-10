package com.test.stepDefs;

import com.google.inject.Inject;
import com.santander.appian.keywords.Utils.SeleniumHelper;
import com.test.helper.BasePage;
import com.test.pages.FormularioCreacionManualTarea;
import com.test.pages.Listas;
import com.test.pages.Tools;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ScreenshotUtils;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormulariosStep {
    @Inject FormularioCreacionManualTarea formularioTTA;
    @Inject SeleniumHelper seleniumHelper;
    @Inject Tools tools;
    @Inject ScreenshotUtils screenshotUtils;
    @Inject BasePage basePage;
    @Inject Utils utils;
    @Inject Listas listas;

    @When("^relleno los datos del formulario \"([^\"]*)\".*$")
    public void rellenoLosDatosDelFormulario(String formulario, DataTable table) {
        formularioTTA.rellenarFormulario(formulario, table);
        screenshotUtils.takeScreenShot();
    }

    @When("^.*pulso el boton \"([^\"]*)\".*$")
    public void pulsoElBotonDelFormulario(String keyWord) {
        tools.waitforLoadProgressBarNotVisible();
        this.seleniumHelper.getElement("xpath://button[contains(text(),'" + keyWord + "')]", 1).click();

    }

    @When("^.*pulso el boton del dialogo \"([^\"]*)\".*$")
    public void pulsoElBotonDelDialogo(String keyWord) {
        tools.waitforLoadProgressBarNotVisible();
        this.seleniumHelper.getElement("xpath://button[contains(text(),'" + keyWord + "')]", 1).click();
        this.basePage.waitForPageLoaded();

    }

    @When("^.*pulso el boton del formulario \"([^\"]*)\".*$")
    public void pulsoElBotonDelFormularioCrearTarea(String keyWord) {
        formularioTTA.crearTarea();
        screenshotUtils.takeScreenShot();

    }

    @When("^.*pulso el enlace de \"([^\"]*)\".*$")
    public void pulsoElEnlaceDe(String nombreDelEnlace) {
        tools.waitforLoadProgressBarNotVisible();
        this.seleniumHelper.getElement("xpath://*[contains(text(),'" + nombreDelEnlace + "')]/parent::a", 1).click();

    }

    @When("^.*acepto la alerta del navegador.*$")
    public void aceptoLaAlertaDelNavegador() {
        tools.acceptDialogBrowser();

    }

    @When("^relleno el campo \"([^\"]*)\" con el valor \"([^\"]*)\"$")
    public void rellenoElCampoConElValor(String campo, String valor) {
        WebElement we = this.seleniumHelper.getElement("xpath://span[contains(text(), '" + campo + "')]/parent::div");
        String att = we.getAttribute("role");
        if(att == null){
            this.seleniumHelper.getElement("xpath://span[contains(text(), '" + campo + "')]/parent::div/child::*[1]").sendKeys(valor);
        }else if(att.contentEquals("listbox")){
            this.utils.appianDropdown("xpath://span[contains(text(), '" + campo + "')]/parent::div[@role = \"listbox\"]", valor);
        }
    }

    @Then("^verifico los valores de la lista con los siguientes datos:$")
    public void verificoLaListaConLosSiguienteDatos(DataTable table) {
        this.tools.waitforLoadProgressBarNotVisible();
        String tablaLocator = "//table/thead/tr/th";
        List<String> tablaDeDatos = table.asList(String.class);
        for(String datos : tablaDeDatos){ System.out.println("Valor a buscar: " + datos); }
        this.basePage.scrollToElementWithJavascript(this.seleniumHelper.getElement("xpath:" + tablaLocator));
        List<WebElement> thCabeceras = this.basePage.driver.findElements(By.xpath(tablaLocator));
        int indexDeColumna = this.listas.obtenerIndexCabeceraPorNombre(thCabeceras, tablaDeDatos.get(2));
        System.out.println("-------------------------------");
        this.listas.verificarDatosDeTodasLasFilasDeUnaColumna(tablaDeDatos.get(1), indexDeColumna);
        this.listas.verificarFiltradoUnicoConPaginacionUsandoOrdenacion(indexDeColumna, tablaDeDatos.get(1));

    }

    @Then("^verifico que la columna \"([^\"]*)\" contiene \"([^\"]*)\"$")
    public void verificoQueLaColumnaContiene(String columna, String valor) {
        this.tools.waitforLoadProgressBarNotVisible();
        String tablaLocator = "//table/thead/tr/th";
        System.out.println("Datos a buscar: columna: " + " | valor: " + valor);
        this.basePage.scrollToElementWithJavascript(this.seleniumHelper.getElement("xpath:" + tablaLocator));
        List<WebElement> thCabeceras = this.basePage.driver.findElements(By.xpath(tablaLocator));
        int indexDeColumna = this.listas.obtenerIndexCabeceraPorNombre(thCabeceras, columna);
        System.out.println("-------------------------------");
        this.listas.verificarDatosDeTodasLasFilasDeUnaColumna(valor, indexDeColumna);
        this.listas.verificarFiltradoUnicoConPaginacionUsandoOrdenacion(indexDeColumna, valor);

    }

}
