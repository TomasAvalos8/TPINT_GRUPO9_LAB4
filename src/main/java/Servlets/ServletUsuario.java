package Servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dominio.TipoUsuario;
import Dominio.Usuario;
import Negocio.UsuarioNeg;
import NegocioImpl.UsuarioNegImpl;

/**
 * Servlet implementation class ServletUsuario
 */
@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 UsuarioNeg usuarioNeg = new UsuarioNegImpl();
    public ServletUsuario() {
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
if(request.getParameter("btnRegistrarUsuario")!=null) {
			
			
			Usuario usuario = new Usuario();
			TipoUsuario tipo= new TipoUsuario(1, "admin");
			usuario.setUsuario(request.getParameter("usuario"));
			usuario.setContraseña(request.getParameter("password"));
			usuario.setTipoUsuario(tipo);
			LocalDate fechaActual = LocalDate.now();
			usuario.setFechaAlta(fechaActual);
			
			boolean estado=true;
			estado = usuarioNeg.insertar(usuario);
			
			if(estado) {
			request.setAttribute("mensaje", "El usuario fue registrado correctamente.");
			}else {
				request.setAttribute("mensaje", "Error: El usuario No fue registrado correctamente.");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("UsuariosAdmin.jsp");
			dispatcher.forward(request, response);
		}
	}

}
