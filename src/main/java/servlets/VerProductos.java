package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DTO.BBDD;
import modelo.clases.Producto;
import modelo.clases.Seccion;

/**
 * Servlet implementation class VerProductos
 */
@WebServlet("/VerProductos")
public class VerProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerProductos() {
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

		String busca = request.getParameter("busqueda");

		bbdd.abrirconexion();

		ArrayList<Producto> productos = bbdd.verProductos();

		ArrayList<Producto> productos2 = new ArrayList<>();

		ArrayList<Seccion> seccion = bbdd.setSeccion();

		bbdd.cerrarconexion();

		if (busca != null) {

			for (Producto producto : productos) {

				if (producto.getCodigo().contains(busca) || producto.getNombre().contains(busca)) {

					productos2.add(producto);
				}
			}
			request.setAttribute("products", productos2);
		}

		else {

			request.setAttribute("products", productos);
		}
		request.setAttribute("secciones", seccion);
		request.getRequestDispatcher("Verproducts.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BBDD bbdd = new BBDD();

		double pMax = Double.parseDouble(request.getParameter("preciomax"));
		double pMin = Double.parseDouble(request.getParameter("preciomin"));

		bbdd.abrirconexion();

		ArrayList<Producto> productos = bbdd.verProductos();

		ArrayList<Producto> productos2 = new ArrayList<>();

		ArrayList<Seccion> seccion = bbdd.setSeccion();

		bbdd.cerrarconexion();

		if (pMax != 0.0 || pMin != 0.0 && pMax > pMin) {

			for (Producto producto : productos) {

				if (producto.getPrecio() < pMax && producto.getPrecio() > pMin) {

					productos2.add(producto);
				}
			}

			request.setAttribute("products", productos2);
		} else {
			request.setAttribute("products", productos);
		}
		request.setAttribute("secciones", seccion);
		request.getRequestDispatcher("Verproducts.jsp").forward(request, response);
	}

}
