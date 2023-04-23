# TEST IBM #
Este repositorio contiene el código fuente de la aplicación de prueba de IBM.

## Instalación ##
### Compilación ###
Primero se debe clonar el repositorio. Una vez hecho esto basta con copiar el archivo `.env.example`, renombrarlo a `.env` 
y modificar los valores de las variables para que se ajusten a las credenciales de acceso de la base de datos.
Finalmente, se ejecutan los tests y se hace la build con el siguiente comando:

```bash
./gradlew build
```
### Base de datos ###
Basta con ejecutar el fichero `script.sql` que se encuentra en la raíz del proyecto. Este fichero crea dos bases de datos;
`ibm` para la ejecución del programa y `test` para los tests.

## Uso ##
Después de ejecutar el comando de gradle, se habrá generado el fichero `ibm-prueba-1.0-SNAPSHOT.zip` en el directorio `build/distributions`.
Para ejecutar la aplicación, basta con descomprimir el fichero y ejecutar el script `ibm-prueba` que se encuentra en la carpeta `bin`.

El programa acepta un parámetro, el `client_id`, que es el identificador del cliente del proveedor. Por ejemplo:

```bash
./ibm-prueba 5
```

El programa genera un fichero de texto plano `proveedores.txt` que contiene el listado de proveedeores con ese `client_id`.
Este fichero es generado en la misma carpeta donde se ejecuta el programa.

## Dependencias ##
El proyecto utiliza las siguientes dependencias:
- [Junit 5](https://junit.org/junit5/)
- [Dotenv-java](https://github.com/cdimascio/dotenv-java)
- [Mysql-connector-j](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j)