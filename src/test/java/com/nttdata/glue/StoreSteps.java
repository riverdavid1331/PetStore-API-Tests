package com.nttdata.glue;

//import com.nttdata.steps.PetStoreStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import net.thucydides.core.annotations.Steps;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import com.nttdata.model.Order;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import lombok.var;

//import io.cucumber.junit;

import java.util.Map;

//import static io.restassured.RestAssured.given;
//import static org.junit.Assert.assertEquals;

public class StoreSteps {
    private Response response;
    private Order order;

    @Given("que el usuario desea crear una nueva orden en la tienda")
    public void usuarioDeseaCrearOrden() {
        order = new Order();
    }

    @Given("que el usuario desea consultar una orden existente")
    public void que_el_usuario_desea_consultar_una_orden_existente() {
        // Este paso no necesita lógica específica porque no requiere inicializar datos.
        System.out.println("El usuario desea consultar una orden existente.");
    }


    @When("realiza una petición POST al endpoint {string} con la siguiente información:")
    public void realizaPost(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> datos = dataTable.asMaps().get(0);
        order.setId(Integer.parseInt(datos.get("id")));
        order.setPetId(Integer.parseInt(datos.get("petId")));
        order.setQuantity(Integer.parseInt(datos.get("quantity")));
        order.setShipDate(datos.get("shipDate"));
        order.setStatus(datos.get("status"));
        order.setComplete(Boolean.parseBoolean(datos.get("complete")));

        response = given()
                .contentType("application/json")
                .body(order)
                .when()
                .post("https://petstore.swagger.io/v2" + endpoint);
    }

    @Then("la respuesta debe retornar un status code 200")
    public void validarStatusCode() {
        assertEquals(200, response.getStatusCode());
    }

    @And("el body debe contener la información enviada correctamente")
    public void validarBodyCreacion() {
        Order responseBody = response.as(Order.class);
        assertEquals(order.getId(), responseBody.getId());
        assertEquals(order.getPetId(), responseBody.getPetId());
    }

    @When("realiza una petición GET al endpoint {string}")
    public void realizaGet(String endpoint) {
        response = given()
                .when()
                .get("https://petstore.swagger.io/v2" + endpoint);
    }

    @And("el body debe contener el id de la orden {string}")
    public void validarBodyConsulta(String id) {
        Order responseBody = response.as(Order.class);
        assertEquals(Integer.parseInt(id), responseBody.getId());
    }
}
