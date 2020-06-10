package com.test.stepDefs;
import com.google.inject.Inject;
import com.test.pages.ResolutorHome;
import cucumber.api.java.en.Given;

public class HomeStep {
    @Inject
    ResolutorHome paginaPrincipal;

    @Given(".*elijo la opcion de \"([^\"]*)\" del menu$")
    public void elijoLaOpcionDelMenu(String keyWord) {
        paginaPrincipal.abrirMenuPrincipal();
        paginaPrincipal.seleccionarOpcionDelMenu(keyWord);
    }
}
