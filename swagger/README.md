En SwaggerHub, empezar por Create new API:
  - OpenAPI Version: 3.0.0 (es la más nueva y que más recomiendan utilizar)
  - Template: None (se puede escoger alguna si se quiere aprender la especificación, pero aquí partimos del .yml)
  - Name: cursosboot (o el nombre de nuestra API)
  - Visibility: Public
  - Auto Mock API: ON (para que se levante un servidor que mockee el comportamiento de la API)

Teniendo el editor listo, podemos pegar el contenido de cursosboot-openapi3.yml. Esto nos permite, al mismo tiempo, visualizar la documentación de la API y editarla.
El servidor se levanta en https://virtserver.swaggerhub.com/<nombre del creador\>/cursosboot/1.0.0 y permite realizar peticiones que nos devuelven los resultados que produciría nuestra API.
Por ejemplo, se podría enviar la petición https://virtserver.swaggerhub.com/<nombre\>/cursosboot/1.0.0/courses para obtener toda la lista de cursos.

Más allá de poder mockear el comportamiento, podríamos querer que la documentación de nuestra API fuera visible en nuestro propio servidor, para lo cuál existe [SwaggerUI](https://github.com/swagger-api/swagger-ui).