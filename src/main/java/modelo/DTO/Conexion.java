package modelo.DTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	protected Connection conexion;

	protected Conexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conexion = DriverManager.getConnection("jdbc:mysql://localhost/supermercado", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public void abrirconexion() {

		try {

			this.conexion = DriverManager.getConnection("jdbc:mysql://localhost/supermercado", "root", "");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void cerrarconexion() {

		try {

			conexion.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
