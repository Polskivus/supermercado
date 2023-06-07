package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DTO.BBDD;
import modelo.clases.Producto;
import modelo.clases.Seccion;

/**
 * Servlet implementation class ModificarProducto
 */
@WebServlet("/ModificarProducto")
public class ModificarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarProducto() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		BBDD bbdd = new BBDD();

		bbdd.abrirconexion();

		Producto productomod = bbdd.seleccionporID(id);

		ArrayList<Seccion> seccion = bbdd.setSeccion();

		bbdd.cerrarconexion();

		request.setAttribute("producto", productomod);
		request.setAttribute("secciones", seccion);
		request.getRequestDispatcher("ModificarProducto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BBDD bbdd = new BBDD();

		Producto modprod = new Producto();

		int id = Integer.parseInt(request.getParameter("id"));
		String codpro = request.getParameter("codprod");
		String nomprod = request.getParameter("nomprod");
		int stockprod = Integer.parseInt(request.getParameter("stockprod"));
		Double precioprod = Double.parseDouble(request.getParameter("priceprod"));
		String caducidad = request.getParameter("cadprod");
		int id_seccion = Integer.parseInt(request.getParameter("id_seccion"));

		Date caducidadD = null;
		try {
			caducidadD = new SimpleDateFormat("dd-mm-yyyy").parse(caducidad);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		modprod.setId(id);
		modprod.setCodigo(codpro);
		modprod.setNombre(nomprod);
		modprod.setCantidad(stockprod);
		modprod.setPrecio(precioprod);
		modprod.setCaducidad(caducidadD);
		modprod.setId_seccion(id_seccion);

		bbdd.abrirconexion();

		bbdd.ModificarProducto(modprod);
		
		bbdd.cerrarconexion();
		
		response.sendRedirect("VerProductos");
	}

}
