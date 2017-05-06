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
## API https://github.com/a-gomezuc/WePlanning/blob/master/API.md

## Páginas diseñadas </br>
### Fallo en log-in</br>

Si por algún motivo nos hemos logueado incorrectamente en el sistema se nos mostrará la siguiente página:

![loginerror](/Maquetación/IMG/Screenshots3/loginError.png)

### Index (Página principal) e Index-logged </br>

Página principal de la web.Se visualizarán los planes más votados por la comunidad y se podrán filtrar por categorias y regiones.</br>

El buscador de planes en el index logged seria el siguiente:

![index1](/Maquetación/IMG/Screenshots3/indexSearch.png)

Aquí se muestran el top planes más votados si el usuario pulsa en mostrar más se visualizarán en el index más planes.

![index2](/Maquetación/IMG/Screenshots3/indexPlans.png)

Si nos encontramos logueados en la web  sufrirá cambios en la barra de navegación, la cual mostrará el nombre de usuario con el que nos hemos logueado en la parte derecha de la barra de navegación(Estos cambios en la barra de navegación se aplicarán en todos los HTML correspondientes a usuarios logueados) y se mostrarán los planes de tus amigos.</br>

Planes de los amigos.

![friends-plan](/Maquetación/IMG/Screenshots3/friendsPlans.png)

Barra de navegación de un usuario logueado.

![navbar-logged](/Maquetación/IMG/Screenshots3/loggedNavbar.png)

### ProfileHTML y ProfileHTML-logged</br>

Html en el cual de mostrar los datos del perfil de un usuario , su ubicación(si la ha añadido), el avatar y la lista de sus amigos.</br>

![profile](/Maquetación/IMG/Screenshots3/userProfile.png)

Si el usuario se encuentra logueado en el sistema podrá accedetr a su perfil, donde el botón azul "Agregar amigo" será sustituido por el botón "Encuentra amigos", como una serie de botones para podificar su perfil, su ubicación y  agregar o eliminar amigos.</br>

Si ya tenemos agregado a esa persona como amigo, se nos mostrará el siguiente botón:

![friendRemove](/Maquetación/IMG/Screenshots3/deleteFriend.png)

Si borramos a un susuario se nos mostrará lo siguiente:

![friendRemoveSuccess](/Maquetación/IMG/Screenshots3/removeFriendSucces.png)

Si lo agregamos como amigo:

![AddfriendSuccess](/Maquetación/IMG/Screenshots3/addFriendSuccess.png)

La barra de navegación en el caso de estar logueados se mostrará como hemos mostrado antes

![profile-button](/Maquetación/IMG/Screenshots/profile-button.png)

![button-agregar](/Maquetación/IMG/Screenshots/button-agregar.png)

![profile-logged](/Maquetación/IMG/Screenshots/profile-logged.png)

Si nos encontramos en nuestro perfil y damos damos al botón mostrado antes para modificarlo, se nos mostrará lo siguiente:

![changesProfile](/Maquetación/IMG/Screenshots3/changeProfile.png)

### SponsorHTML y SponsorHTML-logged</br>

Html en el que se mostrar los datos el perfil de usuario el cual es una empresa.</br>

![sponsor](/Maquetación/IMG/Screenshots/sponsor.png)

Si la empresa se encuentra logueada y visita su perfil desaparecera el botón "Seguir sponsor" y podrá modificar los datos de su perfil.</br>

![sponsor-logged](/Maquetación/IMG/Screenshots/sponsor-logged.png)

### Plan y Plan-logged </br>

Se mostrará una imagen del plan, su valoración y los comentarios de los usuarios.</br>

![plan](/Maquetación/IMG/Screenshots/plan.png)

En el caso de que nos encontremos logueados, se nos dará la posibilidad de añadir comentarios. Aparecerá un botón con el que podremos confirmar nuestra asistencia al evento.</br>

![plan-logged](/Maquetación/IMG/Screenshots/plan-logged.png)

Si pulsamos el botón "Voy a asistir" nos aparecerá la siguiente página:

![asistPlansSuccess](/Maquetación/IMG/Screenshots3/assitPlanSuccess.png)


### Newplan</br>

Formulario en que crearemos un nuevo plan que aparecerá a los usuarios de la página </br>

![newPlan](/Maquetación/IMG/Screenshots/newPlan.png)

Cuando creamos el plan nos aparecerá la siguente página:

![succesCreatePlan](/Maquetación/IMG/Screenshots3/succesCreatePlan.png)

### Aboutus y aboutus-logged</br>

Se mostrará una pequeña descripción sobre el contenido de la página y su temática.

![aboutus](/Maquetación/IMG/Screenshots/aboutus.png)

En el caso de estar logueados lo único que cambiará sera la barra de navegación.

### Contact </br>

Formulario con el cual se podra enviar un e-mail con sugerencias, quejas, incidencias etc- a los desarrollladores de la web.</br>

![contact](/Maquetación/IMG/Screenshots/contact.png)

### Register</br>

Formulario con los datos que debemos rellenar para poder crearnos una cuenta en la web.</br>

![register](/Maquetación/IMG/Screenshots/register.png)
</br>

Si nos registramos con un nombre de usuario que ya está en uso se nos mostrará la siguiente página avisandonos de ello:

![registerFailed](/Maquetación/IMG/Screenshots3/registerFail.png)

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

## Diagrama componentes </br>

![componentsDiagram](/Maquetación/IMG/Screenshots5/ComponentDiagram.png)