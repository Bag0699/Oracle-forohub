# ForoHub API

API REST para un sistema de foros con autenticación JWT, gestión de usuarios, cursos, temas y respuestas.

## Características Principales

- Autenticación JWT
- Gestión completa de usuarios (CRUD)
- Cursos y temas de discusión
- Sistema de respuestas anidadas
- Perfiles de usuario personalizables
- Validaciones de datos
- Migraciones de base de datos con Flyway

## Tecnologías Utilizadas

- Java 21
- Spring Boot 4.0.0
- Spring Security
- Spring Data JPA / Hibernate
- MySQL 8.0+
- Flyway (para migraciones)
- MapStruct (para mapeo de DTOs)
- Bean Validation

## Requisitos Previos

- Java 21
- Maven
- MySQL 8.0+
- Variables de entorno configuradas

## Instalación

```bash
# Clonar el repositorio
git clone [URL_DEL_REPOSITORIO]

# Configurar base de datos MySQL
# Crear base de datos
CREATE DATABASE foro_hub;

# Configurar variables de entorno
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/foro_hub
export SPRING_DATASOURCE_USERNAME=tu_usuario
export SPRING_DATASOURCE_PASSWORD=tu_contraseña
export JWT_SECRET=tu_clave_secreta_jwt

# Compilar y ejecutar la aplicación
mvn clean install
mvn spring-boot:run
```

## Documentación de la API

La documentación interactiva de la API está disponible en:

```
http://localhost:8080/swagger-ui.html
```

## Endpoints Principales

### Autenticación

- `POST /api/auth/register` - Registrar nuevo usuario
- `POST /api/auth/login` - Iniciar sesión

### Usuario

- `GET /api/users/profile` - Obtener perfil del usuario autenticado
- `PUT /api/users/profile` - Actualizar perfil del usuario

### Cursos

- `GET /api/courses` - Listar todos los cursos
- `GET /api/courses/{id}` - Buscar curso por ID
- `POST /api/admin/courses` - Crear nuevo curso (ADMIN)
- `PUT /api/admin/courses/{id}` - Actualizar curso (ADMIN)
- `DELETE /api/admin/courses/{id}` - Eliminar curso (ADMIN)

### Tópicos

- `GET /api/topics` - Listar todos los tópicos (paginado)
- `GET /api/topics/{id}` - Obtener tópico por ID
- `POST /api/topics` - Crear nuevo tópico
- `PUT /api/topics/{id}` - Actualizar tópico (solo autor)
- `DELETE /api/topics/{id}` - Eliminar tópico (solo autor)
- `GET /api/topics/search` - Buscar tópicos por curso y año

### Respuestas

- `GET /api/replies` - Listar todas las respuestas
- `GET /api/replies/{id}` - Obtener respuesta por ID
- `POST /api/replies` - Crear nueva respuesta
- `PATCH /api/replies/{id}/solution` - Marcar respuesta como solución

### Administración (ADMIN)

- `GET /api/admin/users` - Listar todos los usuarios
- `POST /api/admin/users` - Crear nuevo administrador

## Estructura del Proyecto

```
src/main/java/com/bag/foro_hub/
├── config/          # Configuraciones
├── controller/      # Controladores REST
├── dto/             # Objetos de Transferencia de Datos
├── exceptions/      # Manejo de excepciones
├── mapper/          # Mapeos con MapStruct
├── model/           # Entidades y modelos
├── repository/      # Repositorios JPA
└── service/         # Lógica de negocio
```

## Seguridad

- Autenticación basada en JWT
- Roles de usuario (ADMIN, USER)
- Protección CSRF
- Validación de datos de entrada

## Migraciones

Las migraciones de base de datos se gestionan con Flyway y se encuentran en:

```
src/main/resources/db/migration/
```

## Dependencias Principales

- Spring Boot Starter Web
- Spring Data JPA
- Spring Security
- MySQL Connector
- Flyway
- MapStruct
- Lombok
- Spring Boot Starter Validation
