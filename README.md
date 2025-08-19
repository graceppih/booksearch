assets/Buscador de Libros en Colores.png

# 📚 Buscador de Libros  

✨ Bienvenido al **Buscador de Libros** ✨  
Este proyecto es una **aplicación de consola en Java** que te permite buscar libros a través de la **API de Gutenberg** y guardar la información en una **base de datos local**.  
Perfecto para amantes de la lectura que desean **catalogar sus hallazgos digitales**.  

---

## 🚀 Características principales  

- 🔍 **Búsqueda y guardado**: Busca libros por título y guarda automáticamente los resultados en tu base de datos.  
- 📑 **Listado completo**: Visualiza todos los libros guardados con título, autor e idioma.  
- ✍️ **Búsqueda por autor e idioma**: Filtra tus libros por autor o idioma.  
- 🔢 **Conteo de libros**: Muestra cuántos libros has guardado.  
- 🖥️ **Menú interactivo**: Navegación sencilla con opciones claras.  

![Demo](assets/demo.gif)

---

## 🛠️ Tecnologías utilizadas  

- ☕ **Java** → Lenguaje principal.  
- ⚡ **Spring Boot** → Simplificación de configuración y desarrollo.  
- 📦 **Maven** → Gestión de dependencias.  
- 🗄️ **JPA / Hibernate** → Persistencia de datos.  
- 📝 **Lombok** → Menos código repetitivo (getters/setters).  
- 🛢️ **H2 / PostgreSQL** → Bases de datos soportadas.  

---

## 📂 Estructura del proyecto  

src/main/java/com/literatura/booksearch/
│
├── model/ # Entidad Book y atributos
├── service/ # Lógica de negocio y conexión con API
├── repository/ # CRUD con JPA
└── BookSearchApplication.java # Punto de entrada con menú interactivo

---

## ⚙️ Funcionamiento  

1. 🌐 Llama a la **API de Gutenberg** para buscar libros.  
2. 💾 Guarda la información en una base de datos (H2 o PostgreSQL).  
3. 📋 Usa un menú interactivo en consola para navegar entre opciones.  
4. 🛡️ Incluye manejo de errores y validaciones para una mejor experiencia.  

---

## 🎬 Vista previa (GIF de prueba)  

👉 Aquí va un **GIF demostrativo** de cómo funciona el programa:  

![Demo del Buscador de Libros](ruta/del/gif.gif)  

---

## 🤝 Contribuciones  

¡Las contribuciones son bienvenidas! 💡  
Si quieres mejorar el proyecto, haz un **fork**, crea tu rama y envía un **pull request**.  

---

## 📜 Licencia  

Este proyecto está bajo la licencia **MIT**.  
Puedes usarlo, modificarlo y compartirlo libremente.  

---

💡 *Hecho con cariño para lectores curiosos* 📚💻  
