# Candidate test Citibox
Esta es un proyecto que se ha desarrollado como prueba técnica para Citibox. En ella se solicitaba la creación de dos pantallas:
 - Pantalla en forma de listado de posts.
 - Pantalla para ver el detalle de un post, indicando el usuario y el numero de comentarios del mismo.
Los datos a mostrar se obtendrían del servicio: http://jsonplaceholder.typicode.com

Además se ofrecia la posibilidad de añadir un avatar al usuario a través del servicio https://api.adorable.io/avatars pero actualmente no está disponible ese servicio.

Como recomendaciones se estableció las siguientes:
 - Fomentan el uso de libreria de terceros.
 - Se considera importante el testing en el código funciona.
 - Realizar una arquitectura escalable.
 - Enfocarse más en la arquitectura que en la interfaz.
 - Preferiblemente desarrollado en Kotlin aunque tambien se acepta Java.

 ## Tecnologías usadas
 Desarrollada la prueba en lenguaje Kotlin. Las librerías usadas aparte de las de android son:

 #### Consumo de apis
 - Gson
 - Retrofit
 - Okhttp
 #### Injeccion de dependencias
 - Dagger2
 #### Testing
 - Mockito
  #### Debug
 - Timber para mostrar logs por consola.
 - LeakCanary para prevenir memory leaks.

 Además de kotlin se han usado las librerias para hacer uso de las coroutinas.

 ## Arquitectura
 Se ha decidido realizar la arquitectura MVVM ya que con esta arquitectura se puede testear muy bien la aplicación, además hace que los datos en la vista se puedan actualizar de forma automatica sin tener que estar seteandolos.

La organización de carpetas se ha distribuido por pantallas y una carpeta común, cada pantalla se ha dividido en tres carpetas que serán las encargadas de incluir la capa de datos, dominio y ui. Además, en el apartado de common se han añadido los modelos de datos usados y la configuración del inyector de dependencias.

  ## Testing
Se ha desarrolla desting de la capa de datos, dominio e interfaz, distribuidas por carpetas igual que lo comentado en el punto anterior.

Por falta de tiempo no he podido realizar una cobertura de test acorde a mis criterios, esto sería que fuese superior al 80%. Pero se ha desarrollado diferentes test para mostrar el testing en diferentes capas de la arquitectura.

  ## Mejoras
Como mejoras incluiría:
 - Cacheo de la información en una base de datos sqlite a través de Room, con esta arquitecutura sería facil añadirlo. Me parece interesante porque alguna vez el servicio ha tardado algún segundo y para mejorar la experiencia de usuario se le debe mostrar alguna información a este.
 - Añadir más testing unitario para superar el 80% de cobertura.
 - Añadir testing de interfaz.

  ## Comentarios
  El servicio que se ha usado no tiene un control de errores que se pueden dar, por ello he implementado como si se produjese algun error desconocido para controlar también el caso en el que pueda fallar.

  En la documentación del servicio de post aparece que se pueden filtrar por diferentes ids pero al realizar ese filtro lo unico que hace es una ordenación de los elementos por ese id, pero no un filtrado. Para solucionar esto se ha solicitado todos los datos y se ha realizado el filtro a nivel de código.

  
