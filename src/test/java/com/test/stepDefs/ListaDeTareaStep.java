package com.test.stepDefs;

import com.google.inject.Inject;
import com.test.pages.ModuloTareas;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;
import utils.ScreenshotUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ListaDeTareaStep {
    @Inject ModuloTareas moduloTareas;
    @Inject ScreenshotUtils screenshotUtils;



    @Then("^aparece en el listado de tareas la TTA creada en la fila numero \"([^\"]*)\"$")
    public void apareceEnElListadoDeTareas(String numeroFila, DataTable table) {
        boolean b = moduloTareas.comprobarValoresDeLaListaPorFila(table, numeroFila);
        assertThat(b, is(equalTo(true)));
        screenshotUtils.takeScreenShot();
    }

    @Then("^no aparece en el listado de tareas la TTA creada en la fila numero \"([^\"]*)\"$")
    public void noApareceEnElListadoDeTareas(String numeroFila, DataTable table) {
        boolean b = moduloTareas.comprobarValoresDeLaListaPorFila(table, numeroFila);
        assertThat(b, is(equalTo(false)));
        screenshotUtils.takeScreenShot();

    }
}
