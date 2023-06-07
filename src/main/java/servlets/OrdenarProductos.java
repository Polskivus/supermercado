package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DTO.BBDD;
import modelo.clases.Producto;
import modelo.clases.Seccion;

/**
 * Servlet implementation class OrdenarProductos
 */
@WebServlet("/OrdenarProductos")
public class OrdenarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrdenarProductos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BBDD bbdd = new BBDD();

		bbdd.abrirconexion();

		ArrayList<Producto> productos = bbdd.verProductos();
		ArrayList<Seccion> seccion = bbdd.setSeccion();

		bbdd.cerrarconexion();

		String comp = request.getParameter("orden");

		if (comp.contains("des")) {
			productos.sort((p1, p2) -> p1.getCodigo().compareTo(p2.getCodigo()));
		} else if (comp.contains("asc")) {
			productos.sort((p1, p2) -> p2.getCodigo().compareTo(p1.getCodigo()));
		}

		request.setAttribute("products", productos);
		request.setAttribute("secciones", seccion);
		request.getRequestDispatcher("Verproducts.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
