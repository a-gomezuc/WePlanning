# WePlanning </br>
## ¿Qué es WePlanning? </br>
Se trata de una web donde los usuarios pueden publicar planes que han realizado o proponer nuevos planes indicando el lugar, el precio, el tiempo, el número de personas, y dando una descripción en la que pueden aparecer fotografías. Cada usuario visualizará una página de inicio con planes personalizados, podrá valorar y comentar planes de otros usuarios y subir a la página web sus planes.

## Entidades principales </br>
**Usuario:** El usuario tendrá nombre, apellidos, ubicación y lista de amigos y podrá añadir planes y valorar y comentar planes de otros usuarios.</br>
**Patrocinador:** Se trata de un perfil de empresa que tendrá planes patrocinados, nombre, email, página web y seguidores.</br>
**Plan:** Los planes podrán ser experiencias pasadas o propuestas, contarán con una ubicación, una hora, usuarios que asisten o han asistido y una descripción. Estos planes podrán ser patrocinados por un patrocinador o ser un plan de usuario simple. </br>
**Categoría de planes:** Los planes se podrán clasificar en distintas categorías nocturnos, escapadas, deportes, gastronomía, cultural, ocio, etc...</br>
**Comentario/Valoración:** Los planes podrán tener valoraciones y comentarios con imágenes adjuntas.</br>

## Integrantes del equipo de desarrollo </br> 
Rubén Golderos Serrano, r.golderos@alumnos.urjc.es, rgolderos</br>
Guillermo Navas García, g.navasg@alumnos.urjc.es, westernsquad</br>
Jorge Sánchez Márquez, j.sanchezmarq@alumnos.urjc.es, jorge0594</br>
Alex Gómez Uceda, a.gomezuc@alumnos.urjc.es, alexgomezuceda</br>

## Trello  https://trello.com/b/eZYgeXfU/weplanning

## Plan </br>

**URL:/api/plans**</br>
Metodo:GET</br>
Entrada:</br>
Salida:Devuelve todo los planes</br>
Descripcion:Si la peticion es correcta devolvera un status:200 OK  </br>

**URL:/api/viewFriendsPlans** </br>
Metodo:GET</br>
Entrada:</br>
Salida:Devuelve todos los planes amigos</br>
Descripcion:Se tendra que estar logeado para acceder a esta URL, si lo esta le devolvera un status 200 OK </br>

**URL:/api/plans/addPlan** </br>
Metodo:POST</br>
Entrada:"title":
 "category": 
"place": 
"address": 
"prize": 
 "date": 
 "description": 
 "imagePlanTitle": </br>
Salida:Devuelve el nuevo plan creado</br>
Descripcion:Si se crea correctamente el plan se le devolvera un status:201 Created </br>

**URL:/api/plans/{id}** </br>
Metodo:GET</br>
Entrada:</br>
Salida:Devuelve el plan con la id que se ha introduccido en la url</br>
Descripcion:Si la peticion es correcta devolvera un status:200 OK , en caso de que no esistiera esa id devolvera null y un estatus:404 not found </br>

**URL:/api/plans/{id}** </br>
Metodo:PUT</br>
Entrada:Los datos del plan que se quiera modificar</br>
Salida:Devuelve el plan modificado</br>
Descripcion:Si la peticion es correcta devolvera un status:200 OK , en caso de que no esistiera esa id devolvera null y un estatus:404 not found </br>

**URL:/api/plans/{id}** </br>
Metodo:DELETE</br>
Entrada:</br>
Salida:nul</br>
Descripcion:Si la peticion es correcta devolvera un status:200 OK , en caso de que no esistiera esa id devolvera null y un estatus:404 not found </br>

**URL:/api/plans/{id}/assist** </br>
Metodo:POST</br>
Entrada:</br>
Salida:Devuelve el plan y con la lista de los q asistiran actualizada</br>
Descripcion:Si la peticion es correcta devolvera un status:200 OK , en caso de que no esistiera esa id devolvera null y un estatus:404 not found </br>

**URL:/api/plans/{id}/comment** </br>
Metodo:Post</br>
Entrada:"comment":{tu comentario}</br>
Salida:Devuelve el plan con la lista de comentarios actualizada con el nuevo comentario</br>
Descripcion:Si la peticion es correcta devolvera un status:200 OK , en caso de que no esistiera esa id devolvera null y un estatus:404 not found </br>

**URL:/api/plans/{id}/modifyPlanPhoto** </br>
Metodo:put</br>
Entrada:Se legira un archivo png o jpg</br>
Salida:Devuelve el plan con la nueva foto </br>
Descripcion:Si la peticion es correcta devolvera un status:200 OK , en caso de que no esistiera esa id devolvera null y un estatus:404 not found </br>

**URL:/api/plans/searchPlans/title={title}/category={category}/place={place}**</br>
Metodo:GET</br>
Entrada:</br>
Salida:Devuelve los planes que coincidan con los parametron introducios en la url</br>
Descripcion:Si la peticion es correcta devolvera un status:200 OK , en caso de que no esistiera ningun plan que coincida devolvera null y un estatus:200 OK</br>





## Templates </br>
Para la realización de nuestra página web solo se ha usado un template para los htmls ProfileHTML.html, Sponsor.html, ProfileHTML-logged.html y Sponsor-logged.html. </br>
En ellos se han modificado todos los aspectos de diseño principales y se ha mantenido el "tab" y su funcionamiento.</br>
* Template utilizado: http://bootsnipp.com/snippets/featured/people-card-with-tabs </br>

## Diagrama de navegación </br>

![navigatorDiagram](/Maquetación/IMG/Screenshots3/NavigatorDiagram.png)

## Diagrama de clases y templates</br>

![ClassDiagram](/Maquetación/IMG/Screenshots4/ClassDiagram.png)

## Entidades de la base de datos.</brt>

![BDDiagram](/Maquetación/IMG/Screenshots3/BDDiagram.png)
