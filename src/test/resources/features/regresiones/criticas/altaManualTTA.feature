# language: en

@RC @CreacionTareasManual @GdM
Feature: Alta Manual de una TTA en GdM
  Como usuario Resolutor
  quiero poder dar de alta una tarea manual para notificar al usuario desde la lista de tarea y poder gestionarla.
  Esta nueva tarea se verá en la lista de tareas.

  Background:
    Given ingreso a la aplicacion de "GdM"
    And ingreso como un usuario "Resolutor GdM"
    And accedo al modulo "Crear Solicitud"

  @CasosPositivos
  Scenario Outline: 01 - Dar de Alta una TTA Manual en GdM - "<Tipo de tarea (TTA)>" - "<Asunto>"
    When relleno los datos del formulario "Creacion Manual de Tareas"
      | Asunto   | Grupo   | Tipo de tarea (TTA)   | Cliente   | Comentario   |
      | <Asunto> | <Grupo> | <Tipo de tarea (TTA)> | <Cliente> | <Comentario> |
    And pulso el boton "Crear tarea"
    And pulso el boton del dialogo "Sí"
    Then accedo al modulo "Tareas"
    And aparece en el listado de tareas la TTA creada en la fila numero "1"
      | <Asunto> | <Tipo de tarea (TTA)> | <Cliente> |
    Examples: Opciones a rellenar en el formulario Creacion Manual de Tareas
      | Asunto                | Grupo                     | Tipo de tarea (TTA)                        | Cliente                    | Comentario                       |
      | Prueba automatizada   | Entidades                 | Alta contrapartidas GTB                    | Banco Santander (Boadilla) | Comentario prueba automatizada   |
#      | Prueba automatizada 2 | Entidades                 | Alta Gestora                               | Banco Santander España     | Comentario prueba automatizada 2 |
#      | Prueba automatizada 3 | DQ                        | Actualización de Peticionarios Autorizados | Allfunds Bank              | Comentario prueba automatizada 3 |
#      | Prueba automatizada 4 | Securities and Parameters | Alta Basket                                | Banco Santander Beijing    | Comentario prueba automatizada 4 |
#      | Prueba automatizada 5 | Entidades                 | Alta contrapartidas GTB                    | Banco Santander España     | Comentario prueba automatizada 5 |
#      | Prueba automatizada 6 | DQ                        | Alertas TDLG                               | Banco Santander Chile      | Comentario prueba automatizada 6 |
#      | Prueba automatizada 7 | Securities and Parameters | Alta Calendarios                           | ACPM                       | Comentario prueba automatizada 7 |


  @CasosNegativos
  Scenario Outline: 02 - Error al dar de Alta una TTA Manual en GdM con campos obligatorios sin rellenar - "<Tipo de tarea (TTA)>" - "<Asunto>"
    When relleno los datos del formulario "Creacion Manual de Tareas"
      | Asunto   | Grupo   | Tipo de tarea (TTA)   | Comentario   |
      | <Asunto> | <Grupo> | <Tipo de tarea (TTA)> | <Comentario> |
    And pulso el boton "Crear tarea"
    And pulso el boton del dialogo "Sí"
    Then aparece un mensaje de error con el mensaje "Un valor es requerido"
    When accedo al modulo "Tareas"
    And acepto la alerta del navegador
    Then no aparece en el listado de tareas la TTA creada en la fila numero "1"
      | <Asunto> | <Tipo de tarea (TTA)> |

    Examples: Opciones a rellenar en el formulario Creacion Manual de Tareas
      | Asunto                         | Grupo                     | Tipo de tarea (TTA)                        | Comentario                                |
      | Prueba automatizada negativa   | Entidades                 | Alta contrapartidas GTB                    | Comentario prueba automatizada negativa   |
#      | Prueba automatizada negativa 2 | Entidades                 | Alta Gestora                               | Comentario prueba automatizada negativa 2 |
#      | Prueba automatizada negativa 3 | DQ                        | Actualización de Peticionarios Autorizados | Comentario prueba automatizada negativa 3 |
#      | Prueba automatizada negativa 4 | Securities and Parameters | Alta Basket                                | Comentario prueba automatizada negativa 4 |
#      | Prueba automatizada negativa 5 | Entidades                 | Alta contrapartidas GTB                    | Comentario prueba automatizada negativa 5 |
#      | Prueba automatizada negativa 6 | DQ                        | Alertas TDLG                               | Comentario prueba automatizada negativa 6 |
#      | Prueba automatizada negativa 7 | Securities and Parameters | Alta Calendarios                           | Comentario prueba automatizada negativa 7 |
#

