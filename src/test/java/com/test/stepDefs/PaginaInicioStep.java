package com.test.stepDefs;

import com.google.inject.Inject;
import com.test.pages.Navegacion;
import com.test.pages.PaginaLogin;
import cucumber.api.java.en.Given;
import utils.ScreenshotUtils;
import utils.Utils;

public class PaginaInicioStep {

    @Inject Navegacion navegacion;
    @Inject PaginaLogin login;
    @Inject ScreenshotUtils screenshotUtils;


    @Given(".*ingreso a la aplicacion de \"([^\"]*)\"$")
    public void queIngresoALaAplicacionDe(String keyWord) {
        navegacion.seleccionarUrl(keyWord);
        this.screenshotUtils.takeScreenShot();
    }

    @Given(".*ingreso como un usuario \"([^\"]*)\"$")
    public void queIngresoComoUnUsuario(String keyWord) {
        login.ingresarConUsuario(keyWord);
        this.screenshotUtils.takeScreenShot();

    }
}
