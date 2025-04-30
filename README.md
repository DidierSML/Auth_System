# 💻 Sistema de Autenticación con Spring Boot

Este proyecto es una implementación sencilla de un sistema de autenticación y gestión de usuarios, construido con **Spring Boot** y documentado con **OpenAPI (Swagger)**.

## ✅  Funcionalidades

- Registro de usuarios con nombre completo y correo electrónico.
- Envío de un **correo de activación** con token personalizado.
- Asignación de contraseña mediante enlace con token (token de un solo uso).
- Inicio de sesión (login) con validación de:
    - Usuario activo.
    - Credenciales correctas.
- Generación de **JWT** tras autenticación exitosa.
- Protección de endpoints con JWT.
- Documentación de la API con Swagger UI.

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Security
- JWT (JSON Web Tokens)
- Spring Data JPA
- MySQL
- OpenAPI (springdoc-openapi-ui)

## 🔁 Endpoints principales

| Método | Endpoint                    | Descripción                           |
|--------|-----------------------------|---------------------------------------|
| POST   | `/api/auth/register`        | Registro de usuario (sin contraseña)  |
| POST   | `/api/auth/assign-password` | Asignación de contraseña con token    |
| POST   | `/api/auth/login`           | Inicio de sesión, retorna JWT         |
| POST   | `/api/auth/forgot-password` | Olvida el Password, retorna JWT       |
| POST   | `/api/auth/reset-password`  | Actualización de contraseña con token |

## 📧 Pruebas

- **Mailtrap** fue utilizado como servicio para pruebas de envío de correos electrónicos.
- **Postman** fue utilizado como herramienta para simular el comportamiento del cliente (frontend).

## 📁 Uso

1. Registrar un usuario enviando su nombre y correo.
2. Recibirá un correo con un enlace único para establecer su contraseña.
3. Al establecer la contraseña, el usuario podrá iniciar sesión.
4. Tras iniciar sesión correctamente, se genera un JWT que permite acceder a los recursos protegidos.
5. En caso de olvidar la contraseña, esta puede ser actualizada por el usuario.

## 📄 Documentación

Una vez el proyecto esté corriendo localmente, puedes acceder a la documentación interactiva generada con OpenAPI (Swagger UI) en la siguiente URL: http://localhost:8080/swagger-ui/index.html#/


## 📬 Ejemplos de API Requests usando "Postman" para las Funcionalidades requeridas


### 🔹 Registro de Usuario
  
    ```http
    [POST] localhost:8080/api/auth/register
    Body (JSON):
      {
        "fullName": "Juan Pérez",
        "email": "juanperez@example.com"
      }
  
    [Respuesta]: Mensaje en Postman / Mensaje + token de Asignación via Email.


### 🔐 Asignación de Contraseña

    ```http
    [POST] localhost:8080/api/auth/assign-password
    Body (JSON):

      {
        "token": "7b95bcd4-d647-4ef1-92bf-a33cbff5c5f0",
        "newPassword": "juan_contraseña_segura"
      }

    [Respuesta]: Mensaje en Postman / Mensaje via Email.

### 🔑 Login que Valida un Usuario y devuelve Token de acceso

    ```http
    [POST] localhost:8080/api/auth/login
    Body (JSON):

      {
        "email": "juanperez@example.com",
        "password": "juan_contraseña_segura"
      }

    [Respuesta]: Token.

### 🔄 Endpoint que en caso de Olvidar la Contraseña

    ```http
    [POST] localhost:8080/api/auth/forgot-password
    Body (JSON):

      {
        "email": "juanperez@example.com"
      }

    [Respuesta]: Mensaje en Postman / Mensaje + Token de restauración de Contraseña via Email.

### ✔️ Endpoint para Resetear/Actualizar la Contraseña de un Usuario

    ```http
    [POST] localhost:8080/api/auth/reset-password
    Body (JSON):

      {
        "token": "03a6c2d1-8611-4db3-9ad3-15f7067401ac",
        "newPassword": "juan_contraseña_segura_actualizada"
      }

    [Respuesta]: Mensaje en Postman / Mensaje via Email.

## ⚙️ Variables de Entorno

Para ejecutar el proyecto, necesitas configurar un archivo .env en la raíz del proyecto. Renombra el archivo .env.example a .env y configura las siguientes variables de entorno con "tus credenciales":

    # 📚 Base de Datos
    DB_URL=jdbc:mysql://localhost:3306/auth_system
    DB_USER=root
    DB_PASSWORD=admin_password
    
    # 🔑 JWT
    JWT_SECRET=your_jwt_secret
    
    # ✉️ Configuración de Correo
    MAIL_HOST=smtp.mailtrap.io
    MAIL_PORT=587
    MAIL_USER=your_mailtrap_user
    MAIL_PASSWORD=your_mailtrap_password
    MAIL_FROM=admin@demo.test

📝 Explicación de Variables:

  - DB_URL: URL de conexión a la base de datos MySQL.

  - DB_USER y DB_PASSWORD: Credenciales para tu base de datos.

  - JWT_SECRET: Clave secreta para firmar los tokens JWT.

  - MAIL_HOST, MAIL_PORT, MAIL_USER, MAIL_PASSWORD, MAIL_FROM: Configuración para el servicio de correo (por ejemplo, Mailtrap).

📍 Consejo: No olvides reemplazar estos valores por tus credenciales personales o por las de los servicios que utilices, como Mailtrap, Gmail, etc. Una vez configurado el archivo .env, podrás ejecutar la aplicación con Docker o directamente en tu entorno local.
