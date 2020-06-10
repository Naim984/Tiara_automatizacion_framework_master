package utils;

import com.google.inject.Inject;
import com.santander.appian.keywords.Utils.SeleniumHelper;
import com.test.helper.BasePage;
import com.test.pages.Tools;
import com.test.seleniumcustomframework.extension.PageElement;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {
    @Inject SeleniumHelper seleniumHelper;
    @Inject BasePage basePage;
    @Inject Tools tools;

    public ArrayList<String> getHeadesFromDataTable(DataTable table){
        List<Map<String, String>> maps = table.asMaps();
        ArrayList<String> headers = new ArrayList<>();
        for (Map<String, String> map : maps) {
            headers.addAll(map.keySet());
        }

        return headers;
    }

    public ArrayList<String> getValueFromDataTable(DataTable table){
        List<Map<String, String>> maps = table.asMaps();
        ArrayList<String> values = new ArrayList<>();
        for (Map<String, String> map : maps) {
            values.addAll(map.values());
        }

        return values;
    }

    public void appianDropdown(String dropdownElement, String dropdownvalue) {
        WebElement we = this.seleniumHelper.getElement(dropdownElement, 1);
        this.basePage.waitForElementToBecomeClickable(we);
        we.click();
        we = this.seleniumHelper.getElement(dropdownElement, 1);
        this.basePage.waitForElementToBecomeClickable(we);
        try {
            we = this.seleniumHelper.getElement("xpath://div[contains(text(),'" + dropdownvalue +"')]/parent::*[@role='option'][1]");
        } catch (Exception e){
            String[] campo = dropdownElement.split("'");
            we = this.tools.buscarElementoPorValorEnDropBox(campo[1], dropdownvalue, 5);
        }
        this.basePage.waitForElementToBecomeClickable(we);
        we.click();

    }

    public void seleccionarOpcionDeCombox(PageElement combox, String opcion, String listaXpath){
        WebElement webElement;
        By by = By.xpath(listaXpath);
        basePage.waitForElementToBecomeClickable(combox);
        combox.click();
        webElement = this.getElementFromList(opcion, by);
        basePage.waitForElementToBecomeClickable(webElement);
        webElement.click();

    }

    public WebElement getElementFromList(String opcion, By by){
        WebElement elemento = null;
        List<WebElement> opciones = this.basePage.driver.findElements(by);
        for(WebElement opcionMenu: opciones){
            if (opcionMenu.getText().equalsIgnoreCase(opcion)){
                elemento = opcionMenu;
                System.out.println("Element text: " + elemento.getText());
                basePage.scrollToElementWithJavascript(elemento);

            }
        }

        return elemento;
    }

    public WebElement getPrimerDatoLista(List<PageElement> listaDeElementos){
        WebElement we;
        if(listaDeElementos.size() != 0){
            we = listaDeElementos.get(0);
        }else{
            we = null;
            System.out.println("No hay elementos en la lista de elementos");
        }
        return we;
    }


}
