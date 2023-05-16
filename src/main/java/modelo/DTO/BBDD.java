package modelo.DTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.clases.Producto;

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

				productos.add(producto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productos;
	}
}
