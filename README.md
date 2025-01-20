# LiterAlura

## Descripción
`LiterAlura` es una aplicación desarrollada en Java utilizando Spring Boot, diseñada para la gestión y consulta de datos relacionados con autores y libros. Permite el registro de autores, la categorización de libros y la interacción con APIs externas para ampliar la funcionalidad.

---

## Estructura del Proyecto

- **`src/main/java`**: Contiene el código fuente principal:
  - **`app`**: Configuración de la aplicación.
  - **`domain`**: Clases principales del dominio, incluyendo `autor` y `libro`.
  - **`service`**: Servicios para consumir APIs externas y procesar datos.
- **`src/main/resources`**: Archivos de configuración, como `application.properties`.

---

## Requisitos

- **Java 17 o superior**
- **Maven** para la gestión de dependencias.
- **Spring Boot** ya configurado en el proyecto.
- Conexión a Internet para consumir APIs externas.

---

## Instalación

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/tu-usuario/literatura.git
   ```
2. **Importa el proyecto en tu IDE favorito:**
   - Importa como un proyecto Maven.
3. **Configura las propiedades de la aplicación:**
   - Edita el archivo `src/main/resources/application.properties` según tus necesidades.

4. **Instala las dependencias:**
   ```bash
   mvn install
   ```

---

## Uso

1. **Ejecuta la aplicación:**
   ```bash
   mvn spring-boot:run
   ```

2. **Interacción con la aplicación:**
   - Accede a la API REST localmente en `http://localhost:8080`.
   - Endpoints principales:
     - `/api/autores`: Gestión de autores.
     - `/api/libros`: Gestión de libros.

---

## Estructura de Datos

### Autor
- **ID**: Identificador único.
- **Nombre**: Nombre completo del autor.
- **Nacionalidad**: País de origen.

### Libro
- **ID**: Identificador único.
- **Título**: Nombre del libro.
- **Idioma**: Idioma del libro.
- **Autor**: Relación con un autor.

---

## Contribución

1. Haz un fork del proyecto.
2. Crea una rama para tus cambios:
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Realiza un pull request describiendo tus mejoras.

---

## Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

---

## Contacto
Para más información o sugerencias, contacta a través de tu correo o perfil de GitHub.
