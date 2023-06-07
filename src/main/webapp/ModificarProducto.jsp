<%@page import="modelo.clases.Producto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar producto</title>
</head>
<body>

	<%
	Producto producto = (Producto) request.getAttribute("producto");
	%>

<form method="post" action="ModificarProducto">

	<input type="hidden" id="id" name="id" value="${producto.id }">
	
	<label for="Codigo producto">Codigo del producto:</label>
	<input type="text" id="codprod" name="codprod" value="${producto.codigo}">
	
	<label for="Nombre producto">Nombre producto:</label>
	<input type="text" id="nomprod" name="nomprod" value="${producto.nombre }">
		
	<label for="Cantidad producto">Cantidad de producto:</label>
	<input type="text" id="stockprod" name="stockprod" value="${producto.cantidad }">
	
	<label for="Precio producto">Precio producto</label>
	<input type="text" id="priceprod" name="priceprod" value="${producto.precio } ">
		
	<label for="Caducidad producto">Caducidad producto</label>
	<input type="date" id="cadprod" name="cadprod" value="${producto.caducidad }">
	<br>
	<label for="id_seccion" id="id_seccion">Seccion:</label>
	<input type="text" id="id_seccion" value="${producto.seccion.nombre }" readonly>
	
	<label for="id_seccion">Id seccion</label>
		<select id="id_seccion" name="id_seccion">
 			<c:forEach items="${secciones}" var="seccion">
    			<option value="${seccion.id}">${seccion.nombre}</option>
  			</c:forEach>
		</select>
	<input type="submit" value="Modificar">
</form>
</body>
</html>