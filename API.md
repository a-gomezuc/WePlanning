# Documentación API REST </br>

## Métodos exclusivos del administrador del sistema

## User </br>

### URL:"/api/users"</br>
**Método:** GET </br>
**Entrada:** </br>
**Salida:** Muestra la información de todos los usuarios de la aplicación en formato JSON.</br>
**Descripción:** Si la petición de ha realizado de manera correcta, se devolverá un código de estado: 200 OK </br>

### URL:"/api/user/{id}"</br>
**Método:** GET </br>
**Entrada:** </br>
**Salida:** Muestra la información del usuario cuyo ID se corresponda con el que hemos introducido.</br>
**Descripción:** Si el usuario existe en la base de datos se devolverá un código de estado: 200 OK. En caso contrario se devolverá el codigo de estado: 404 NOT FOUND</br>

### URL:"/api/user/searchUsers/filter={filter}/usearch={usearch}"</br>
**Método:** GET </br>
**Entrada:** </br>
**Salida:** La información de los usuarios que cumplan con los criterios de búsqueda introducidos.</br>
**Descripción:** Para realizar esta petición es necesario que el usuario este logueado en el sistema. En caso de no estarlo se devolverá el codigo de estado: 401 Unauthorized.</br>
Si el usuario esta registrado en el sistema y la petición se realiza de forma correcta se devolverá el código de estado: 200 OK.</br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

### URL:"/api/user/addUser"</br>
**Método:** POST </br>
**Entrada:** Se introducirá un JSON con la siguiente estructura:</br>
"sponsor": </br>
"uname": </br>
"surname": </br>
"province": </br>
"age": </br>
"uemail": </br>
"passwordHash" </br>
"description":</br>
**Salida:** Nos mostrará la información del usuario que acabamos de crear. </br>
**Descripción:** Si la creación del usuario se ha realizado de manera correcta se devolverá un código de estado: 200 OK.</br> 
En caso de haber introducido una provincia inexistente de España mostrará un código de estado: 406 NOT ACCEPTABLE.</br>
Si introducimos un id ya existente en la base de datos, se muestra: 409 CONFLICT.</br>
Y por último si ha habido algun error, se visualizará: 404 NOT FOUND.  </br>

### URL:"/api/user/modifyProfile/{id}"</br>
**Método:** POST  </br>
**Entrada:** Se introducirá un JSON con la siguiente estructura.</br>
"uname": </br>
"surname": </br>
"province": </br>
"age": </br>
"uemail": </br>
"description":</br>
**Salida:** Se mostrará la infromación del perfil de ese usuario </br>
**Descripción:** Solo se podrán modificar los campos "age", "uname", "surname", "province", "description" y "uemail".</br>
Para realizar esta petición es necesario que el usuario este logueado en el sistema. En caso de no estarlo o intentar modificar un perfil que no sea el nuestro, se devolverá el codigo de estado: 401 UNAUTHORIZED.</br>
Si la modificación del perfil de usuario se ha realizado de manera correcta se mostrará un código de estado: 200 OK.</br>
En caso de haber introducido una provincia inexistente de España mostrará un código de estado: 406 NOT ACCEPTABLE.</br>
Y si se ha producido algún tipo de error, se visualizará: 404 NOT FOUND.</br>

### URL:"/api/user/{id}/modifyProfilePhoto"</br>
**Método:** POST </br>
**Entrada:** En body accederemos a form-data. Elegiremos el tipo "file". En el campo key escribiremos "file" y adjuntaremos la imagen que queramos añadir a nuestro perfil.</br>
**Salida:** Se nos mostrarán los datos del usuario que hemos modificado.</br>
**Descripción:** Para realizar esta petición es necesario que el usuario este logueado en el sistema. En caso de no estarlo o que intentemos modificar un perfil que no sea el nuestro, se devolverá el codigo de estado: 401 UNAUTHORIZED.</br>
Si la modificación de la foto de perfil se ha realizado de manera correcta se mostrará el código de estado: 200 OK.</br>
Y si se ha producido algún tipo de error, se visualizará: 404 NOT FOUND.</br>

### URL:"/api/user/myPlans"</br>
**Método:** GET </br>
**Entrada:** </br>
**Salida:** Se mostrará la información de los planes subidos por el usuario </br>
**Descripción:** Con este método podremos visualizar los planes que ha subido el usuario con el que nos encontremos logueados en el sistema.</br>
Para realizar esta petición es necesario que el usuario este logueado en el sistema. En caso de no estarlo se devolverá el codigo de estado: 401 UNAUTHORIZED.</br>
Si la petición se ha realizado de manera correcta se mostrará el código de estado: 200 OK.</br>
Si se ha producido algún tipo de error, se visualizará: 404 NOT FOUND.</br>

### URL:"/api/user/addFriend/{id}"</br>
**Método:** PUT  </br>
**Entrada:** </br>
**Salida:** Se mostrará la lista de amigos actualizada, con la infromación de cada uno.</br>
**Descripción:** Añadiremos a la lista de amigos al usuario cuyo id coincida con el que hemos introducido.</br>
Para realizar esta petición es necesario que el usuario este logueado en el sistema. En caso de no estarlo se devolverá el codigo de estado: 401 UNAUTHORIZED.</br>
Si la petición se ha realizado de manera correcta se mostrará el código de estado: 200 OK.</br>
Si se ha producido algún tipo de error, un sponsor intenta añadir a alguien a amigos o el amigo no existe en la base de datos, se visualizará: 404 NOT FOUND.</br>

### URL:"/api/user/removeFriend/{id}"</br>
**Método:** DELETE</br>
**Entrada:** </br>
**Salida:** Se mostrará la lista de amigos actualizada, con la infromación de cada uno. </br>
**Descripción:** Eliminaremos de la lista al amigo cuyo ID conincida con el que hemos introducido.</br>
Para realizar esta peticón es necesario que el usuario este logueado en el sistema. En caso de no estarlo se devolverá el codigo de estado: 401 UNAUTHORIZED.</br>
Si la petición se ha realizado de manera correcta se mostrará el código de estado: 200 OK.</br>
Si se ha producido algún tipo de error o el ID que hemos introducido no corresponde a ningúm amigo de nuestra lista se moestrará: 404 NOT FOUND </br> 

## Plan </br>

### URL:"/api/plans"</br>
**Método:** GET</br>
**Entrada:** </br>
**Salida:** Devuelve todo los planes existentes </br>
**Descripción:** Si la petición es correcta devolverá un código de estado:200 OK  </br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

### URL:"/api/viewFriendsPlans" </br>
**Método:** GET</br>
**Entrada:** </br>
**Salida:** Devuelve todos los planes de tus amigos</br>
**Descripción:** Se tendra que estar logeado para acceder a esta URL, si lo esta le devolverá un código de estado:200 OK</br> 
Si no se esta logeado le devolvera el código de estado: 401 Unauthorized </br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

### URL:"/api/plans/addPlan" </br>
**Método:** POST</br>
**Entrada:** Se introducirá un JSON con la siguiente estructura:</br>
"title": </br>
 "category": </br> 
"place": </br>
"address": </br>
"prize": </br>
 "date": </br>
 "description": </br>
 "imagePlanTitle": </br>
**Salida:** Devuelve el nuevo plan creado</br>
**Descripción:** se tendra que estar logueado para añadir un plna, si se creo correctamente el plan se le devolverá un código de estado::201 Created </br>
Si no se esta logeado le devolvera el código de estado: 401 Unauthorized </br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

### URL:"/api/plans/{id}" </br>
**Método:GET** </br>
**Entrada:** </br>
**Salida:** Devuelve el plan con la id que se ha introduccido en la url</br>
**Descripción:** Si la petición es correcta devolverá un código de estado:200 OK, </br>
Si no se esta logeado le devolvera el código de estado: 401 Unauthorized </br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

### URL:"/api/plans/{id}" </br>
**Método:** PUT</br>
**Entrada:** Se introducirá un JSON con la siguiente estructura</br>
"Título": </br>
"Categoría": </br>
"Fecha": </br>
"Provicia": </br>
"Direcciòn": </br>
"Precio": </br>
"Descripción":</br>
**Salida:** Devuelve el plan modificado</br>
**Descripción:** Si la petición es correcta y se esta logeado devolverá un código de estado:200 OK </br>
Si no se esta logeado o no eres el autor de ese plan le devolverá el código de estado: 401 Unauthorized </br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

### URL:"/api/plans/{id}" </br>
**Método:** DELETE</br>
**Entrada:** </br>
**Salida:** null</br>
Descripción:Si la petición es correcta devolverá un código de estadó:200 OK </br>
Si no se esta logeado o no eres el usuario que ha creado el plan le devolverá el código de estado: 401 Unauthorized </br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

### URL:"/api/plans/{id}/assist" </br>
**Método:** POST</br>
**Entrada:** </br>
**Salida:** Devuelve el plan y con la lista de los q asistiran actualizada</br>
**Descripción:** Si la petición es correcta devolvera un status:200 OK </br>
Si no se esta logeado le devolverá el código de estado: 401 Unauthorized </br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

### URL:"/api/plans/{id}/comment" </br>
**Método:** POST</br>
**Entrada:** Se introducirá un JSON con la siguiente estructura </br>
"comment":{tu comentario}</br>
**Salida:** Devuelve el plan con la lista de comentarios actualizada con el nuevo comentario</br>
**Descripción:** Si la petición es correcta devolverá un código de estado:200 OK </br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

### URL:"/api/plans/{id}/modifyPlanPhoto" </br>
**Método:** PUT</br>
**Entrada:**  En body accederemos a form-data. Elegiremos el tipo "file". En el campo key escribiremos "file" y adjuntaremos la imagen que queramos añadir a nuestro plan.</br>
**Salida:** Devuelve el plan con la nueva foto </br>
Descripción:Si la petición es correcta devolverá un código de estado:200 OK </br>
Si no se esta logeado o no es el autor del plan le devolverá el código de estado: 401 Unauthorized </br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

### URL:"/api/plans/searchPlans/title={title}/category={category}/place={place}"</br>
**Método:** GET</br>
**Entrada:** </br>
**Salida:** Devuelve los planes que coincidan con los parametron introducios en la url</br>
**Descripción:** Si la petición es correcta devolverá un código de estado:200 OK </br>
en caso de que no existiera ningún plan que coincida devolvera null y un código de estado :200 OK</br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

## Comment </br>

### URL:"/api/comments" </br>
**Metodo:** GET </br>
**Entrada:** </br>
**Salida:** </br>
**Descripcion:** Si la petición es correcta devolverá un status:200 OK </br>
en caso de que no existiera ningun comentario devolverá null y un estatus:200 OK</br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

### URL:"/api/comments/{id}"</br>
**Metodo:** GET </br>
**Entrada:** </br>
**Salida:** Devuelve el comentario que tenga la id de la url junto con el autor </br>
Descripcion:Si la petición es correcta devolvera un status:200 OK </br>
en caso de que no existiera ningun comentario devolverá null y un estatus:200 OK</br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

### URL:"/api/comments/author/{id}"</br>
**Metodo:** GET </br>
**Entrada:** </br>
**Salida:** Devuelve el comentario del autor que se introduzca en la URL </br>
**Descripcion:** Si la petición es correcta devolverá un status:200 OK </br>
en caso de que no existiera ningun comentario devolverá null y un estatus:200 OK</br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

### URL:"/api/comments/{id}"</br>
**Metodo:** PUT </br>
**Entrada:** Se introducirá un JSON con la siguiente estructura </br>
"comment":</br>
**Salida:** Devuelve el comentario modificado </br>
**Descripcion:** Si la petición es correcta devolvera un status:200 OK , en caso de que no existiera ningun comentario devolverá null y un estatus:200 OK</br>
Si no se esta logeado o no es el autor del comentario le devolvera el código de estado: 401 Unauthorized </br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>

## Contact

### URL:"/api/admin/contacts"</br>
**Metodo:** PUT </br>
**Entrada:** </br>
**Salida:** Devuelve todos los comentarios </br>
**Descripcion:** Si la petición es correcta devolvera un status:200 OK</br>
en caso de que no existiera ningun comentario devolverá null y un estatus:200 OK</br>
Si no eres un admin le devolverá el código de estado: 401 Unauthorized </br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>


### URL:"/api/addcontacts"</br>
**Metodo:** POST</br>
**Entrada:**  Se introducirá un JSON con la siguiente estructura</br>
"Nombre":</br>
"Apellido":</br>
"Compañia":</br>
"Telefono": </br>
"email": </br>
"Descripcion":</br>
**Salida:** Devuelve el comentario modificado </br>
**Descripcion:** Si la petición es correcta devolvera un status:200 OK </br>
en caso de que no existiera ningun comentario devolverá null y un estatus:200 OK</br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>


### URL:"/api/removecontact/{id}"</br>
**Metodo:** DELETE</br>
**Entrada:** </br>
**Salida:** </br>
**Descripcion:** Si la petición es correcta devolvera un status:200 OK </br>
en caso de que no existiera ningun comentario devolverá null y un estatus:200 OK</br>
Si no eres un admin le devolverá el código de estado: 401 Unauthorized </br>
En caso de que hubiera algún error mostrará el estado: 404 NOT FOUND</br>


