
Feature: Automatización de pruebas del API de Store en PetStore

  Como automatizador principal de NTT
  Quiero garantizar la integridad del API de Store
  Para asegurar que la creación y consulta de órdenes funcionan correctamente

  Scenario Outline: Creación de una orden en la tienda
    Given que el usuario desea crear una nueva orden en la tienda
    When realiza una petición POST al endpoint "/store/order" con la siguiente información:
      | id       | petId   | quantity | shipDate         | status  | complete |
      | <id>     | <petId> | <qty>    | <shipDate>       | <status>| <comp>   |
    Then la respuesta debe retornar un status code 200
    And el body debe contener la información enviada correctamente

    Examples:
      | id     | petId | qty | shipDate            | status    | comp   |
      | 101    | 202   | 2   | 2024-06-01T10:00:00Z | approved  | true   |

  Scenario Outline: Consulta de una orden existente en la tienda
    Given que el usuario desea consultar una orden existente
    When realiza una petición GET al endpoint "/store/order/<orderId>"
    Then la respuesta debe retornar un status code 200
    And el body debe contener el id de la orden "<orderId>"

    Examples:
      | orderId |
      | 101     |
