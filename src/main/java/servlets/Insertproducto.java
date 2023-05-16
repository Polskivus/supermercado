package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DTO.BBDD;
import modelo.clases.Producto;

/**
 * Servlet implementation class Insertproducto
 */
@WebServlet("/Insertproducto")
public class Insertproducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insertproducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BBDD bbdd = new BBDD();
		
		Producto producto = new Producto();
		
		int codproducto = Integer.parseInt(request.getParameter("codprod"));
		String nombreproducto = request.getParameter("nomprod");
		int stockproducto = Integer.parseInt(request.getParameter("stockprod"));
		double precioproducto = Double.parseDouble(request.getParameter("priceprod"));
		String caducidadproducto = request.getParameter("cadprod");
		
		Date fecha = null;
		
		try {
			fecha = new SimpleDateFormat("yyyy-MM-dd").parse(caducidadproducto);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		producto.setCodigo(codproducto);
		producto.setNombre(nombreproducto);
		producto.setCantidad(stockproducto);
		producto.setPrecio(precioproducto);
		producto.setCaducidad(fecha);
		
		bbdd.abrirconexion();
		
		bbdd.insertProducto(producto);
		
		bbdd.cerrarconexion();
		
		response.sendRedirect("VerProductos");
		
	}

}
