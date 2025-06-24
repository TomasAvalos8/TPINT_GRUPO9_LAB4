package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatosImpl.UsuarioDaoImpl;
import Dominio.Usuario;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String user = request.getParameter("usuario").trim();
	        String pass = request.getParameter("pass").trim();
	        
	        System.out.println("Usuario ingresado: " + user);
	        System.out.println("Contraseña ingresada: " + pass);

	        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
	        Usuario usuario = usuarioDao.login(user, pass);

	        if (usuario != null) {
	            if (usuario.getTipoUsuario().getIdTipoUsuario() == 1) {
	                response.sendRedirect("InicioAdmin.jsp");
	            } else {
	                response.sendRedirect("InicioCliente.jsp");
	            }
	        } else {
	        	response.sendRedirect("Inicio.jsp?error=1");
	        }
	}

}
