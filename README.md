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

## 📄 Documentación

Una vez el proyecto esté corriendo, puedes acceder a la documentación en la página oficial de openApi:
