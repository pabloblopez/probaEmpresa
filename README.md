# probaEmpresa
É unha pequena proba dunha empresa consistente en consumir e procesar un servizo web RESTful

Non está feito o sistema de caché en disco nin en memoria. O máis óptimo sería cachear en disco con ficheiros.

# Trileuco RRHH App

El objetivo de la prueba es escribir un API RESTFul basado en Spring Boot que proporcione información sobre personajes de la saga de Star Wars.

## Fundamentos

* El API consumirá a su vez otros endpoint REST de los que obtener la información. En concreto, se apoyará en los servicios de la plataforma: `https://swapi.co/`
* Utilizando los endpoints necesarios, se pide la creación de un interfaz REST aceptando una única petición del tipo:

```sh
http://{host}:{port}/swapi-proxy/person-info?name=Luke%20Skywalker
```

* Ante una peticición de este tipo, la aplicación deberá retornar un JSON conteniendo la siguiente información y estructura:

```json
{
  "name": "Luke Skywalker",
  "birth_year": "19BBY",
  "gender": "male",
  "planet_name": "Tatooine",
  "fastest_vehicle_driven": "X-wing",
  "films": [
    {
      "name": "A New Hope",
      "release_date": "1977-05-25"
    },
    {
      "name": "The Empire Strikes Back",
      "release_date": "1980-05-17"
    },
    {
      "name": "Return of the Jedi",
      "release_date": "1983-05-25"
    },
    {
      "name": "Revenge of the Sith",
      "release_date": "2005-05-19"
    },
    {
      "name": "The Force Awakens",
      "release_date": "2015-12-11"
    }
  ]
}
```

* Obviamente, la aplicación deberá funcionar para cualquiera de los nombres de personaje incluídos en `Swapi`, y ante un nombre incorrecto, debería responder con un JSON de error y un código `404`.
* El atributo fastest_vehicle_driven debe ser rellenado con el nombre del "vehicle" o "starship" más rápido (con mayor `max_atmosphering_speed`) conducido por el personaje.

## Valoración

* Esperamos recibir el código fuente de la aplicación y las instrucciones para su build y ejecución.
* Idealmente, el proyecto debería ser compartido compartido en GitHub o Bitbucket.
* La aplicación debe ser construída con un JAR ejecutable.
* Idealmente se debe utilizar `Graddle` para la construcción del proyecto (también aceptaríamos `Maven`).
* La principal valoración será claridad y modularidad del código fuente.

## Extras

* Se valoraría positivamente la inclusión de algún mecanismo de caché que permita reducir el número de invocaciones a swapi ante varias búsquedas consecutivas.
  * Por ejemplo, que con la búsqueda de la primera persona se cacheen sus películas, y ante una búsqueda de una segunda persona que aparezca en una de esas películas, que su nombre y fecha sean recuperados de la caché.
* Se deja libertad al sistema de caché elegido, aúnque se valoraría que fuese un sistema con persistencia en disco para que la vida de la caché se mantuviese entre distintas ejecuciones del servidor.
