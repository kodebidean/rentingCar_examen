# Renting Car Examen

Este es un proyecto de backend para la gestión de alquiler de coches utilizando Spring Boot y PostgreSQL.

## Requisitos

- Java 17
- Maven
- PostgreSQL

## Configuración

Configura las propiedades de la base de datos en el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/renting_car_db
spring.datasource.username=postgres
spring.datasource.password=0077777
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Ejecución

Para ejecutar la aplicación, usa el siguiente comando de Maven:

```sh
./mvnw spring-boot:run
```

## Documentación de la API

La documentación de la API está disponible en Postman:

[Documentación de Postman](https://documenter.getpostman.com/view/40683149/2sAYk7T4dE)

## Endpoints

### CocheController

- `GET /zapatos`: Buscar coches por marca y modelo.
- `GET /zapatos/available`: Obtener coches disponibles.
- `GET /zapatos/{id}`: Obtener coche por ID.
- `POST /zapatos`: Crear un nuevo coche.
- `PUT /zapatos/{id}`: Actualizar un coche existente.
- `DELETE /zapatos/{id}`: Eliminar un coche.

### UsuarioController

- `GET /animales`: Obtener todos los usuarios.
- `GET /animales/{id}`: Obtener usuario por ID.
- `POST /animales`: Crear un nuevo usuario.
- `PUT /animales/{id}`: Actualizar un usuario existente.
- `DELETE /animales/{id}`: Eliminar un usuario.

### AlquilerController

- `POST /rentals`: Crear un nuevo alquiler.
- `PUT /rentals/{id}/return`: Devolver un alquiler.
- `GET /rentals/users/{userId}/rentals`: Obtener alquileres por ID de usuario.

## Estructura del Proyecto

- `src/main/java/com/real_ia/backend`: Código fuente principal.
- `src/main/resources`: Archivos de configuración.
- `src/test/java/com/real_ia/backend`: Pruebas unitarias.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para discutir cualquier cambio que desees realizar.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT.
