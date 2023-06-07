<%@page import="modelo.clases.Producto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ver Productos</title>
</head>
<body>

<h1>Productos</h1>
	
<form method="get" action="VerProductos">
	<label>Busqueda por nombre y/o ID</label>
	
	<input type="search" id="busca" name="busqueda">
	<input type="submit" value="Buscar">
</form>
<form method="post" action="VerProductos">
	
	<label>Busqueda por precio min y/o max</label>

	<label>Max:</label>
	<input type="number" id="preciomax" name="preciomax" value="0.00" step=".01" min="0.00">
	
	<label>Min:</label>
	<input type="number" id="preciomin" name="preciomin" value="0.00" step=".01" min="0.00">
	
	<input type="submit" value="Buscar">
</form>
	<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Id producto</th>
					<th scope="col">Codigo producto<a href="OrdenarProductos?orden=asc">&#8593;</a> <a href="OrdenarProductos?orden=des">&#8595;</a></th>
					<th scope="col">Nombre</th>
					<th scope="col">Cantidad</th>
					<th scope="col">Precio</th>
					<th scope="col">Caducidad</th>
					<th scope="col">Nombre seccion</th>
				</tr>
			</thead>
			<tbody>	
	<c:forEach items="${products}" var="producto">
				<tr>
					<td>${producto.id }</td>
					<td>${producto.codigo }</td>
					<td>${producto.nombre }</td>
					<td>${producto.cantidad }</td>
					<td>${producto.precio }</td>
					<td>${producto.caducidad }</td>
					<td>${producto.seccion.nombre }</td>
					<td>
						<button>
							<a href="ModificarProducto?id=${producto.id}">Editar</a>
						</button>
					</td>
				</tr>
	</c:forEach>
		</tbody>
	</table>
	<form method="post" action="Insertproducto">
	
		<label for="Codigo producto">Codigo producto</label>
		<input type="text" id="codprod" name="codprod">
		
		<label for="Nombre producto">Nombre producto</label>
		<input type="text" id="nomprod" name="nomprod">
		
		<label for="Cantidad producto">Cantidad de producto</label>
		<input type="text" id="stockprod" name="stockprod">
	
		<label for="Precio producto">Precio producto</label>
		<input type="text" id="priceprod" name="priceprod">
		
		<label for="Caducidad producto">Caducidad producto</label>
		<input type="date" id="cadprod" name="cadprod">
		
		<label for="id_seccion">Id seccion</label>
		<select id="id_seccion" name="id_seccion" required>
 			<c:forEach items="${secciones}" var="seccion">
    			<option value="${seccion.id}">${seccion.nombre}</option>
  			</c:forEach>
		</select>

	<input type="submit" value="Insertar nuevo producto">
	
	<c:if test="${ requestScope.mensaje != null }">

	<span style="font-size: 25px">${ requestScope.mensaje }</span>

	</c:if>
	
	</form>
	
</body>
</html>