package com.test.pages;

import com.google.inject.Inject;
import com.santander.appian.keywords.Utils.SeleniumHelper;
import com.test.LoadProperties;
import com.test.helper.BasePage;
import com.test.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Listas extends BasePage {
    @Inject Tools tools;
    @Inject SeleniumHelper seleniumHelper;
    @Inject BasePage basePage;

    @FindBy(xpath = "//a[contains(@title,'Página siguiente')]") public PageElement aSiguientePaginacion;

    public boolean hayPaginacion(){
        if(aSiguientePaginacion.isDisplayed()){
            return true;
        }else { return false; }
    }

    public void verificarFiltradoUnicoConPaginacionUsandoOrdenacion(int indexDeColumna, String datoCelda ){
        WebElement we = this.seleniumHelper.getElement("xpath://table/thead/tr/th[" + indexDeColumna + "]/child::div");
        we.click();
        this.tools.waitforLoadProgressBarNotVisible();

        WebElement tdCelda = this.seleniumHelper.getElement("xpath://table/tbody/tr[1]/td[" + indexDeColumna +"]");
        boolean b = tdCelda.getText().contentEquals(datoCelda);
        System.out.println("Texto de la celda: " + tdCelda.getText() + " | Valor del DataTable: " + datoCelda + " | Coinciden: " + b );
        assertThat(b, is(equalTo(true)));

        we.click();
        this.tools.waitforLoadProgressBarNotVisible();

        tdCelda = this.seleniumHelper.getElement("xpath://table/tbody/tr[1]/td[" + indexDeColumna +"]");
        b = tdCelda.getText().contentEquals(datoCelda);
        System.out.println("Texto de la celda: " + tdCelda.getText() + " | Valor del DataTable: " + datoCelda + " | Coinciden: " + b );
        assertThat(b, is(equalTo(true)));
    }

    public int obtenerIndexCabeceraPorNombre(List<WebElement> thCabeceras, String nombreColumna){
        int indexDeColumna = 0;
        for (WebElement thCabecera : thCabeceras){
            System.out.println(thCabecera.getText() + " : " + (thCabeceras.indexOf(thCabecera) + 1));
            if(nombreColumna.contains(thCabecera.getText())) {
                indexDeColumna = thCabeceras.indexOf(thCabecera) + 1;
                System.out.println("Index de la columna: " + indexDeColumna);
            }
        }
        return indexDeColumna;
    }

    public void verificarDatosDeTodasLasFilasDeUnaColumna(String datoAComprobar, int indexDeColumna){
        List<WebElement> trFilas = this.basePage.driver.findElements(By.xpath("//table/tbody/tr"));
        int indexFilas = trFilas.size();
        System.out.println("Cantidad de fila de la tabla: " + indexFilas);
        for (int i = 0; i < indexFilas; i++){
            WebElement tdCelda = this.seleniumHelper.getElement("xpath://table/tbody/tr[" + (i+1) + "]/td[" + indexDeColumna +"]");
            boolean b = tdCelda.getText().contentEquals(datoAComprobar);
            System.out.println("Texto de la celda: " + tdCelda.getText() + " | Valor del DataTable: " + datoAComprobar + " | Coinciden: " + b );
            assertThat(b, is(equalTo(true)));
        }
    }

}
