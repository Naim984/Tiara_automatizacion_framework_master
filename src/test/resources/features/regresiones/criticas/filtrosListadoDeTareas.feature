# language: en

#@RC @FiltrosTareas @GdM
@wip
Feature: Filtros del Listado de Tareas
  Como un Usuario Resolutor desde el listado de tareas
  quiero poder filtrar por cualquier campo
  para obtener las tareas de los filtros utilizados

  Background:
    Given ingreso a la aplicacion de "GdM"
    And ingreso como un usuario "Resolutor GdM"
    And accedo al modulo "Tareas"
    #And pulso el enlace de "Filtros de búsqueda"

  #@CasosPositivos
  Scenario Outline: 01 - Filtrar por el campo "<Filtro>" con el valor "<Valor>" en el listado de Tareas
    When relleno el campo "<Filtro>" con el valor "<Valor>"
    And pulso el enlace de "Aplicar filtros"
    Then verifico que la columna "<Columna>" contiene "<Valor>"
    Examples: Opciones a rellenar en el formulario Creacion Manual de Tareas
      | Filtro          | Valor                   | Columna      |
      | ID de tarea     | 151419                  | ID tarea     |
#      | ID de tarea | 150451 | ID tarea |
#      | ID de tarea | 150446 | ID tarea |
      | estado de tarea | En Curso                | Estado tarea |
#      | estado de tarea | Pausada por terceros   | Estado tarea |
#      | estado de tarea | Pausada por el usuario | Estado tarea |
#      | estado de tarea | Pendiente de iniciar   | Estado tarea |
#      | clientes | Banco Santander (Boadilla) | Cliente |
      | clientes        | Banco Santander Beijing | Cliente      |
#      | clientes | Allfunds Bank              | Cliente |
#      | clientes | ACPM                       | Cliente |
#      | clientes | Banco Santander Uruguay    | Cliente |
#      | clientes | Santander UK               | Cliente |

      | colas           | CALENDARIOS             | Cola         |
#      | colas  | GESTION DOCUMENTAL  | Cola    |
#      | colas  | VIAS CONFIRMACION   | Cola    |
#      | colas  | CONSULTAS ENTIDADES | Cola    |
#      | colas  | Controles           | Cola    |
#      | colas  | DERIVADOS           | Cola    |
#      | colas  | DUPLICIDADES        | Cola    |

#      | procedencia     | Manual                 |
#      | procedencia     | Automática             |
#      | procedencia     | Periódica              |
#      | procedencia     | Correo                 |
#      | procedencia     | Proyecto               |
#      | asunto          | Prueba automatizada    |
#      | ID de tarea     | 151422                 |

#  Scenario Outline: 02 - Filtrar por varios campos
#    When relleno varios filtros de busqueda dentro del modulo de "Tareas“
#      |Seleccione grupo|Seleccione colas|Introduza una TTA|Seleccione clientes|Introduza un nombre de usuario|Seleccione una procedencia
#      |<Seleccione grupo>|<Seleccione colas>|<Introduza una TTA>|<Seleccione clientes>|<Introduza un nombre de usuario>|<Seleccione una procedencia>
#    And pulso el boton del formulario "Aplicar Filtros"
#    Then devuelve tareas que cumplen con los filtro utilizados
#      |<Seleccione grupo>|<Seleccione colas>|<Introduza una TTA>|<Seleccione clientes>|<Introduza un nombre de usuario>|<Seleccione una procedencia>
#
#    Examples: Rellenar todos los filtros
#      |Seleccione grupo|Seleccione colas|Introduza una TTA|Seleccione clientes|Introduza un nombre de usuario|Seleccione una procedencia...
#	  | Entidades |  | Alta contrapartidas GTB | Banco Santander (Boadilla) | jose.garrido@... |...
#
#  Scenario Outline: 03 - Limpiar Filtros
#	When relleno todos los filtros de busqueda del modulo "Tareas"
#      | Selecciones grupo | Seleccione colas | Introduza una TTA | Seleccione clientes | Introduza un nombre de usuario | Seleccione una procedencia |
#      | <Selecciones grupo> | <Seleccione colas> | <Introduza una TTA> | <Seleccione clientes> | <Introduza un nombre de usuario> | <Seleccione una procedencia>
#    And pulso el boton del formulario "Limpiar"
#    Then se borran todos los datos de los filtros del modulo "Tareas"
#      | Selecciones grupo | Seleccione colas | Introduza una TTA | Seleccione clientes | Introduza un nombre de usuario | Seleccione una procedencia | Seleccione un estado de tarea |
#
#    Examples: Rellenar todos los filtros
#	 | Selecciones grupo | Seleccione colas | Introduza una TTA | Seleccione clientes | Introduza un nombre de usuario | Seleccione una procedencia |....
#     | Entidades |  | Alta contrapartidas GTB | Banco Santander (Boadilla) | jose.garrido@... |...
#



