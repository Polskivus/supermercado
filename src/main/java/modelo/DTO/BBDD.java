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
				producto.setCodigo(resultSet.getString("codigo"));
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

			pst.setString(1, producto.getCodigo());
			pst.setString(2, producto.getNombre());
			pst.setInt(3, producto.getCantidad());
			pst.setDouble(4, producto.getPrecio());
			pst.setDate(5, new Date(producto.getCaducidad().getTime()));

			pst.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void insertarProductoSeccion(Producto producto) {

		String insertarProductoSeccion = "INSERT INTO productos (codigo,nombre,cantidad,precio,caducidad,id_seccion) VALUES (?,?,?,?,?,?)";

		try {

			PreparedStatement pst = super.conexion.prepareStatement(insertarProductoSeccion);

			pst.setString(1, producto.getCodigo());
			pst.setString(2, producto.getNombre());
			pst.setInt(3, producto.getCantidad());
			pst.setDouble(4, producto.getPrecio());
			pst.setDate(5, new Date(producto.getCaducidad().getTime()));
			pst.setInt(6, producto.getId_seccion());

			pst.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean codigoExiste(String codigo) {

		String codigoExiste = "SELECT codigo FROM productos WHERE codigo=?";

		try {

			PreparedStatement pst = super.conexion.prepareStatement(codigoExiste);
			pst.setString(1, codigo);
			ResultSet resultSet = pst.executeQuery();
			
			resultSet.next();
				return resultSet.getString("codigo").equals(codigo) ? true : false;
  
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public void ModificarProducto(Producto producto) {
		
		String modprod ="UPDATE productos SET codigo=?, nombre=?, cantidad=?, precio=?, caducidad=?, id_seccion=? WHERE id=?";
		
		try {
			
			PreparedStatement pst = super.conexion.prepareStatement(modprod);
			pst.setInt(7, producto.getId());
			
			pst.setString(1, producto.getCodigo());
			pst.setString(2, producto.getNombre());
			pst.setInt(3, producto.getCantidad());
			pst.setDouble(4, producto.getPrecio());
			pst.setDate(5, new Date(producto.getCaducidad().getTime()));
			pst.setInt(6, producto.getId_seccion());
			
			pst.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
