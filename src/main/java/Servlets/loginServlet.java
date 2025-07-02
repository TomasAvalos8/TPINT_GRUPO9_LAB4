package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatosImpl.ClienteDaoImpl;
import DatosImpl.UsuarioDaoImpl;
import Dominio.Cliente;
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
	        
	        ClienteDaoImpl clienteDao = new ClienteDaoImpl();
	        Cliente cliente = clienteDao.obtenerClienteConLocalidadProvincia(usuario.getId_usuario());
	        request.getSession().setAttribute("clienteLogueado", cliente);


	        if (usuario != null) {
	        	HttpSession sesion = request.getSession();
	        	sesion.setAttribute("usuario", usuario.getUsuario());
	        	sesion.setAttribute("id_usuario", usuario.getId_usuario());
	        	sesion.setAttribute("tipoUsuario",usuario.getTipoUsuario().getDescripcion());
	        	sesion.setAttribute("tipoUsuarioId",usuario.getTipoUsuario().getIdTipoUsuario());
	        	
	            if (usuario.getTipoUsuario().getIdTipoUsuario() == 1) {
	                response.sendRedirect("InicioAdmin.jsp");
	            } else {
	                response.sendRedirect(request.getContextPath() + "/ServletInicioCliente");
	            }
	        } else {
	        	response.sendRedirect("Inicio.jsp?error=1");
	        }
	}

}
