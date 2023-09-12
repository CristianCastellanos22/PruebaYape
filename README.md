<div id="top"></div>

[![LinkedIn][linkedin-shield]][linkedin-url]


<!-- PROJECT LOGO -->
<br />
<div align="center">

<h3 align="center">Proyecto Prueba Yape</h3>

  <p align="center">
    Se realiza App escrita en kotlin para proceso de seleccion Yape, la cual muestra un listado de recetas, una vista de detalle y una vista de mapa con un marcador.
    <br />
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Tabla de contenidos</summary>
  <ol>
    <li>
      <a href="#Acerca del proyecto">Acerca del proyecto</a>
      <ul>
        <li><a href="#Construido con">Construido con</a></li>
      </ul>
    </li>
    <li>
      <a href="#Primeros pasos">Primeros pasos</a>
      <ul>
        <li><a href="#Pre-requisitos">Pre-requisitos</a></li>
        <li><a href="#Instalación">Instalación</a></li>
      </ul>
    </li>
    <li>
      <a href="#automatizadas">Pruebas Automatizadas</a>
    </li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
### Acerca del proyecto

La APP cuenta con tres pantallas.
* Listado de recetas y busqueda, por nombre e ingredientes.
![Product Name Screen Shot][screenshot-main]

* Vista de detalle para la receta seleccionada.
![Product Name Details Shot][screenshot-details]

* Mapa con un marcador, indicando el país de origen de la receta.
![Product Name Map Shot][screenshot-map]

<p align="right">(<a href="#top">back to top</a>)</p>

### Construido con

* [Kotlin](https://kotlinlang.org/)
* [Librerias de Jeppack](https://developer.android.com/jetpack/androidx/explorer?hl=es-419)
* [Retrofit](https://square.github.io/retrofit/)
* [Coil](https://coil-kt.github.io/coil/compose/)
* [Google Maps](https://developers.google.com/maps/documentation/android-sdk/maps-compose?hl=es-419)
* [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-jetpack?hl=es-419)


### Arquitectura usada
* [MVVM]
* [Clean architecture]

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Primeros pasos

Para poder correr el proyecto se debe contar con el IDE Android Studio, para este proyecto se utilizo la version Android Studio Giraffe | 2022.3.1 Patch 1, si se pretende utilizar una version anterior del IDE se deben realizar las migraciones necesarias.

### Pre-requisitos

El proyecto cuenta con dependencias, las cuales se encuentran en el archivo build.gradle, la instalación de estas dependencias se realiza automaticamente por el IDE.

### Instalación

Pasos para la ejecución del proyecto.

1. Clonar el repositorio
   ```sh
   git clone https://github.com/CristianCastellanos22/AppRecetas.git
   ```
2. Abrir con el IDE Android Studio

<p align="right">(<a href="#top">back to top</a>)</p>

### Automatizadas

Para las pruebas automatizadas se utiliza el framework Maestro, en un sistema Mac OS, un emulador dentro del IDE Android Studio.

* [Maestro](https://maestro.mobile.dev/)

Se debe instalar el CLI

* [Instalar CLI](https://maestro.mobile.dev/getting-started/installing-maestro)

Para ejecutar los test debemos abrir una terminal, navegar hasta la ruta del proyecto, luego ingresar al directorio automatizadas, donde se encuentran los archivos .yaml y ejecutar el comando.

```sh
   maestro test pruebaYape.yaml
   ```

Las pruebas automatizadas se realizaron sobre un emulador en Android Studio, antes de ejecutar el test se debe iniciar el emulador e instalar el proyecto.   

![Emulator Screen Shot][screenshot-test]

![Terminal Screen Shot][emulator-screenshot]


<p align="right">(<a href="#top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/cristianjcb/
[screenshot-main]: images/Main.png
[screenshot-details]: images/Details.png
[screenshot-map]: images/Map.png
[emulator-screenshot]: images/Automatizadas.gif
[screenshot-test]: images/Test.png
