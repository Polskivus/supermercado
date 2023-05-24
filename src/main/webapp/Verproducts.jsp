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
	
	<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Id producto</th>
					<th scope="col">Codigo producto</th>
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
			<option value="1">Alimentacion</option>
			<option value="2">Frescos</option>
			<option value="3">Bazar</option>
			<option value="4">Ferreteria</option>
		</select>

	<input type="submit" value="Insertar nuevo producto">
	
	<c:if test="${ requestScope.mensaje != null }">

	<span style="font-size: 25px">${ requestScope.mensaje }</span>

	</c:if>
	
	</form>
	
</body>
</html>