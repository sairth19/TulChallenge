# TulChallenge
Tul challenge project

Prueba Tul
El siguiente es un proyecto gradle.

Usar el IDE de su preferencia.
Por defecto se lanzará en la ruta: locahost:8080
Las url root de la api es "/api"

Endpoints:

Productos:
GET http://localhost:8080/api/products Retorna el listado de productos

Carts:
GET  http://localhost:8080/api/carts/{user} retorna el carrito en estado pendiente asociado al usuario, si no tiene ninguno se crea uno y lo retorna
POST http://localhost:8080/api/carts/{user} Añade un nuevo producto al carrito, si ya existe se actualiza a la nueva cantidad enviada.
Retorna el nuevo estado del carrito
<h6>BODY</h6>
<pre>
<code>
{
	"cartUuid":"301a9d6e-96ad-469b-9495-c85fa2660b6f",
	"product":
	  {
	    "productUUID": "8a8e141b-6dbb-441e-bb1d-0bc5abe693c7",
	    "quantity": 3
	  }
} 
</code>
</pre>
DELETE http://localhost:8080/api/carts/{user}/{product-uuid} Elimina un producto del carrito en caso de que este lo contenga
POST http://localhost:8080/api/carts/{user}/checkout   Cambia el estado del carrito a completado. 
