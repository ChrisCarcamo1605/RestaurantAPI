# Instrucciones de Ejecución

Este proyecto utiliza Docker Compose para gestionar los servicios. Antes de ejecutar la aplicación, asegúrate de tener Docker y Docker Compose instalados en tu sistema.

## Pasos para Ejecutar

1  **Ejecutar Docker Compose:**

    Desde el directorio raíz del proyecto, ejecuta el siguiente comando para iniciar todos los servicios definidos en el archivo `docker-compose.yml`:

    ```bash
    docker-compose up --build
    ```

    * La opción `--build` asegura que las imágenes de Docker se construyan si han cambiado o si no existen localmente.

2.  **Acceder a la Aplicación:**

    Una vez que los contenedores estén en ejecución, puedes acceder a la aplicación a través de las URLs o puertos definidos en el archivo `docker-compose.yml`. Consulta el archivo para obtener más detalles.

## Detener la Aplicación

Para detener todos los servicios y eliminar los contenedores, ejecuta el siguiente comando:

```bash
docker-compose down
