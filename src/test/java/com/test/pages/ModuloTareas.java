package com.test.pages;

import com.google.inject.Inject;
import com.santander.appian.keywords.Utils.SeleniumHelper;
import com.test.helper.BasePage;
import com.test.seleniumcustomframework.extension.PageElement;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModuloTareas extends BasePage {
    @Inject SeleniumHelper seleniumHelper;
    /*######### Localizadores de Elementos #########*/
    @FindBy(xpath = "//table/tbody/tr") private List<PageElement> trListadosDeTareas;
    @FindBy(xpath = "//table/tbody/tr[1]") private PageElement primeraFilaTablaListadoDeTareas;



    public boolean comprobarValoresDeLaListaPorFila(DataTable table, String numeroFila){
        WebElement filaDeLaLista = this.seleniumHelper.getElement("xpath://table/tbody/tr[" + numeroFila + "]");
        List<String> tablaDeDatos = table.asList(String.class);
        List<String> textosDeLaPrimeraFila = Arrays.asList(filaDeLaLista.getText().split("\n"));

        System.out.println("datos de la Tabla de datos de gherkin:");
        for (String s: tablaDeDatos){
            System.out.println(s);
        }
        System.out.println("----------------------------------------");
        System.out.println("datos de la fila de la lista");
        for (String s: textosDeLaPrimeraFila){
            System.out.println(s);
        }

        return textosDeLaPrimeraFila.containsAll(tablaDeDatos);

    }




}
