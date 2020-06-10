package com.test.pages;

import com.google.inject.Inject;
import com.test.LoadProperties;
import com.test.helper.BasePage;
import com.test.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MensajesDeError extends BasePage {
    @Inject Tools tools;

    @FindBy(xpath = "//*[contains(text(),'Un valor es requerido')]") private PageElement txtValorRequerido;

    public void comprobarValidacionDeCampo(String error){
        if(error.equalsIgnoreCase("Un valor es requerido")){
            assertThat(txtValorRequerido.getText().equalsIgnoreCase("Un valor es requerido"), is(equalTo(true)));
        }
    }

}
