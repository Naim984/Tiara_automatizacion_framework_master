package com.test.stepDefs;

import com.google.inject.Inject;
import com.test.pages.MensajesDeError;
import cucumber.api.java.en.Then;

public class CreacionManualDeTareasStep {
    @Inject MensajesDeError mensajesDeError;

    @Then("^aparece un mensaje de error con el mensaje \"([^\"]*)\"$")
    public void apareceEnElListadoDeTareas(String keyWord) {
        mensajesDeError.comprobarValidacionDeCampo(keyWord);

    }
}
