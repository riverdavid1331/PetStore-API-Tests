package com.nttdata;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        features = "src/test/resources/features",//"classpath:features",
        glue = "com.nttdata.glue"   //  ==> Definir el @tag  a ejecutar
        //@todo //pueden estar los tagg arriba y ejecutarlo todo a la vez
        // @consulta or @test1
        // @consultaArticulos
        // @consultaArticulos or @test1
)
public class CucumberTestSuite {
}
