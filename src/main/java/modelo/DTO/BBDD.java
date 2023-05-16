package modelo.DTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.clases.Producto;
import modelo.clases.Seccion;

public class BBDD extends Conexion {

	public ArrayList<Producto> verProductos() {

		String mostrarProductos = "SELECT * FROM productos";

		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		try {

			PreparedStatement productosMostrar = super.conexion.prepareStatement(mostrarProductos);
			ResultSet resultSet = productosMostrar.executeQuery();

			while (resultSet.next()) {

				Producto producto = new Producto();

				producto.setId(resultSet.getInt("id"));
				producto.setCodigo(resultSet.getInt("codigo"));
				producto.setNombre(resultSet.getString("nombre"));
				producto.setCantidad(resultSet.getInt("cantidad"));
				producto.setPrecio(resultSet.getDouble("precio"));
				producto.setCaducidad(resultSet.getDate("caducidad"));
				producto.setId_seccion(resultSet.getInt("id_seccion"));
				producto.setSeccion(getSeccion(producto.getId_seccion()));

				productos.add(producto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productos;
	}
	
	public Seccion getSeccion(int id) {
		
		String getseccion = "SELECT * FROM secciones WHERE id=?";
		
		Seccion seccion = new Seccion();
		
		try {
			PreparedStatement pst = super.conexion.prepareStatement(getseccion);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			
			resultSet.next();
			
			seccion.setId(resultSet.getInt("id"));
			seccion.setNombre(resultSet.getString("nombre"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return seccion;
	}
	
	public void insertProducto(Producto producto) {
		
		String insertarProducto = "INSERT INTO productos (codigo,nombre,cantidad,precio,caducidad) VALUES (?,?,?,?,?)";
		
		try {
			
			PreparedStatement pst = super.conexion.prepareStatement(insertarProducto);
			
			pst.setInt(1, producto.getCodigo());
			pst.setString(2, producto.getNombre());
			pst.setInt(3, producto.getCantidad());
			pst.setDouble(4, producto.getPrecio());
			pst.setDate(5, new Date(producto.getCaducidad().getTime()));
			
			pst.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
