# Mercado-Libre-clone-test

Este es una app de búsqueda basada en el api de mercado libre, por medio de la app se podran realizar busquedas de articulos y ver algunos de sus detalles disponibles

### Aplicación
 | Back online |Item clicked |
 |-------------|-------------|
 | <img width="200" src="https://github.com/FranciscoMontufar28/Mercado-Libre/blob/master/gifs/no%20internet.gif"/>| <img width="200" src="https://github.com/FranciscoMontufar28/Mercado-Libre/blob/master/gifs/item%20click.gif"/>|
 | Item search | No item found |
 |<img width="200" src="https://github.com/FranciscoMontufar28/Mercado-Libre/blob/master/gifs/item%20search.gif"/>|<img width="200" src="https://github.com/FranciscoMontufar28/Mercado-Libre/blob/master/gifs/no%20item%20found.gif"/>|                   
 |Load items from local (no internet) |
 |<img width="200" src="https://github.com/FranciscoMontufar28/Mercado-Libre/blob/master/gifs/local%20load.gif"/>|

## Clean Code

![Android-Clean-Architecture](https://user-images.githubusercontent.com/15860430/116630703-562d0680-a919-11eb-80d6-08d140c3b09a.png)

La aplicacion implementa modulos, para separar sus componentes por medio de capas, esta implementacion permite que se hagan cambios a nivel de modulos sin afectar toda la aplicacion.

## MVVM

Es un patrón de arquitectura sugerido por Google para aprovechar al maximo todos los componentes de JetPack de Android, buscando desacoplar al máximo la lógica con la vista.

### ViewModel
Cuando una aplicación interactúa con el usuario y por ejemplo, el dispositivo es girado, la actividad se destruye y se vuelve a crear, lo que implica que si una vista muestra cierta información al usuario, esta se deba guardar y posteriormente cuando la actividad o el fragment se vuelva a cargar, mostrar los datos de nuevo.
En el año 2017 se presentó ViewModel, un componente que sobrevivía a los ciclos de vida de la aplicación y permitía tener un mejor control de esta situación.
Por otra parte permite implementar "single responsibility principle", ya que toda la interacción con la vista la haría la activity o los fragments y la lógica la realizaría el ViewModel.

[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)

### LiveData
Implementa el patrón observador, pero con la ventaja de que es consiente del ciclo de vida de la aplicación, esto quiere decir, cada vez que nueva información llega al livedata, este notifica a la vista siempre y cuando la actividad o el fragment asociado este visible.


[LiveData](https://developer.android.com/topic/libraries/architecture/livedata)


### Single Activity - Navigation

Antes los fragmentos se los consideraba una forma muy compleja de implementar vistas, ya que la navegación entre estos generaba muchos "Bugs". Con la llegada del Jetpack de Android, esto ha mejorado notablemente; el componente de navegación (Navigation), permite no solo navegar de manera más fácil entre fragmentos, sino que además su implementación utiliza una interfaz gráfica, permitiendo que la mayor parte del trabajo la realice el componente y así minimizar errores.

[Navigation](https://developer.android.com/guide/navigation)

## Inyección de dependencias

Para la creación del árbol de dependencias y para cumplir con uno de los principios de SOLID, se implementó Dagger2 el cual provee las dependencias necesarias durante el tiempo de ejecución, mediante el uso de anotaciones.

[Dagger](https://dagger.dev/)

## Retrofit, ReactiveX y Coroutines
Rx fue implementado para responder a los cambios (buscador reactivo dentro de la app) por otra parte, para tareas asíncronas se migro a corrutinas en union con retrofit para obtner los datos del api.

[Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)

[ReactiveX](http://reactivex.io/)

[Retrofit](https://square.github.io/retrofit/)

[Mercado Libre Api](https://developers.mercadolibre.com.co/es_ar/api-docs-es)

## Room
Almancena los datos de la lista de productos, en caso de perder la conexion carga los ultimos datos disponibles

[Room](https://developer.android.com/topic/libraries/architecture/room)

## Imagenes
Para visualizar las imágenes de los productos se implementó las librerías de Glide y carouselview.

```bash
implementation 'com.github.bumptech.glide:glide:4.12.0'
implementation 'com.synnapps:carouselview:0.1.5'
```

## UI Test
Using the robot pattern implemented Espresso framework

## Unit Test 
Se implementaron las librerías de mockito y mockitokotlin2 , para facilitar el uso en lenguaje Kotlin.

```bash
testImplementation 'org.mockito:mockito-core:3.11.0'
testImplementation 'com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0'
```
