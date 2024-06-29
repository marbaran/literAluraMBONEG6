# LiterAluraMB
Aplicacion que permite realizar consultas de libros a una API y guardarlas en una BD.

# Cacteristicas
- Esta aplicacion consume la API de Gutendex y guarda los datos en una BD PostgreSQL.
- Previamente se cargaron las siguientes dependencias:
  - jackson-databind: que permite manipular los datos de la API en formato JSON.
  - postgresql: para generar la bd y poder interactuar con la misma.
- Uso de HttpClient, HttpRequest, HttpResponse, que permite realizar peticiones a la API

##  Ejecucion de la Aplicacion
- Clonar el repositorio
- Abrirlo en algun IDE que permita la ejecucion de archivos .java
- Ejecutar la aplicacion desde la clase Principal
![1 Ejecutar aplicación.jpg](1%20Ejecutar%20aplicaci%F3n.jpg)

- En la consola, aparecera el menu con las diferentes opciones
![2 Menú Principal.jpg](2%20Men%FA%20Principal.jpg)

- Eligiendo la 1º opción e ingresando el nombre del libro deseado obtendremos los datos del mismo que se encuentran disponibles en la API.
- Si realizamos una consulta por un libro que ya fue guardado en la BD se nos informa con un mensaje "el libro ya está registrado". 
![3 Buscar libro por título.jpg](3%20Buscar%20libro%20por%20t%EDtulo.jpg)

- Se muestran los datos del libro consultado y que se guardan en la BD.
![4 Mostrar libro.jpg](4%20Mostrar%20libro.jpg)

- Previamente se hizo otra consulta con el libro Don Quijote y utilizando la 2º opción se muestran los 2 libros guardados en la BD.
![5 Mostrar libros guardados en BD.jpg](5%20Mostrar%20libros%20guardados%20en%20BD.jpg)

- Con la 3º opción del menú accedemos a los autores guardados en la BD.
![6 Mostrar autores guardados en BD.jpg](6%20Mostrar%20autores%20guardados%20en%20BD.jpg)

- Si se selecciona la opción 4 se nos pide ingresar un año para mostrar luego los autores vivos en ese año.
![7 Elegir año para mostrar los autores vivos.jpg](7%20Elegir%20a%F1o%20para%20mostrar%20los%20autores%20vivos.jpg)

- Se muestran los autores vivos en ese año ingresado.
![8 Mostrar autores vivos en un año.jpg](8%20Mostrar%20autores%20vivos%20en%20un%20a%F1o.jpg)

- La aplicación mediante la opción 5 nos muestra un menú con diferentes opciones de idiomas.
![9 Menú de idiomas.jpg](9%20Men%FA%20de%20idiomas.jpg)

- De acuerdo a que se seleccione se mostraran los diferentes libros guardados en la BD que fueron escritos en ese idioma.
![10 Muestra los libros en el idioma elegido.jpg](10%20Muestra%20los%20libros%20en%20el%20idioma%20elegido.jpg)

- Finalmente si seleccionamos la opción "0" cerramos la app y aparece un mensaje de agradecimiento por su uso
![11 Salir de la app.jpg](11%20Salir%20de%20la%20app.jpg)
