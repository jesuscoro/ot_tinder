# Aplicación de ejemplo.

Aplicación que muestra un listado de personas permitiéndonos seleccionar qué personas nos gustan. 

Está estructurada en 3 módulos, app, data y domain

## Módulos
### App
Capa de presentación de datos. Es una aplicación Android que Contiene Activities, Fragments y todos los controles y componentes típicos de una aplicación. 

Sigue el patrón MVP (Model-View-Presenter), con el que se consigue separar los casos de uso de la vista. En las Activities y los Fragments (View) no hay ningún tipo de lógica más allá de qué hacer con la vista (ocultar o mostrar controles, modificar el aspecto de un botón, etc). Por su parte, la capa de presentación (Presenter) es el encargado de obtener los datos (Model) a través de implementaciones de los casos de uso del dominio.

Utiliza las librerías:
* Glide ([https://github.com/bumptech/glide](https://github.com/bumptech/glide)) para mostrar imágenes
* ButterKnife ([http://jakewharton.github.io/butterknife/](http://jakewharton.github.io/butterknife/)) para inyección de dependencias de la vista

Tiene dependecia de las otras 2 capas.

### Domain
Capa con la lógica de negocio. Contiene los casos de uso y se comunica con el resto de capas a través de interfaces. Es un módulo java sin ningún tipo de dependencia con el resto de módulos. 

Hace de puente entre los datos y la vista a través de la interface PersonRepository que expone métodos para obtener una lista de personas y para actualizar una persona y los siguientes casos de uso:

* GetPersons.- Para obtener el listado de personas.
* UpdatePerson.- Para modificar una persona.

### Data
Capa de datos de la aplicación. Contiene las llamadas a los servicios rest y la capa de persistencia local. Es una librería android con dependencia de la capa del dominio.

Utiliza las librerías:

* SugarORM ([http://satyan.github.io/sugar/](http://satyan.github.io/sugar/)) Para la capa de persistencia 
### IMPORTANTE Debido a la forma de funcionar de esta libreria, es recomendable desactivar el Instant Run de Android Studio
* Retrofit ([http://square.github.io/retrofit/](http://square.github.io/retrofit/)) Para las llamadas REST
* GSON ([https://github.com/google/gson](https://github.com/google/gson)) Para el tratamiento de JSON

Cuando la capa de presentación quiere obtener o modificar datos del modelo, esta capa será la encargada de obtenerlos y devolvérselos para que los muestre. Tenemos dos fuentes de datos distintas, una base de datos local y un servicio rest. Primero trata de obtener datos en local y, si no los hay, llama al servicio rest. Si consigue obtener datos, se persisten y se devuelven a la vista.

## Esquema general
![Esquema general](https://github.com/jesuscoro/ot_tinder/blob/master/esquema.png)
