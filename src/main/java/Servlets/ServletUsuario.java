package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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
		ArrayList<Usuario> listaUsuarios = usuarioNeg.listarUsuarios();
		request.setAttribute("usuarios", listaUsuarios);
      
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("UsuariosAdmin.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        if(request.getParameter("btnRegistrarUsuario") != null) {
            String password = request.getParameter("password");
            String passwordConfirm = request.getParameter("passwordConfirm");
            if(password != null && password.equals(passwordConfirm)) {
                Usuario usuario = new Usuario();
                TipoUsuario tipo = new TipoUsuario(1, "admin");
                usuario.setUsuario(request.getParameter("usuario"));
                usuario.setContraseña(password);
                usuario.setTipoUsuario(tipo);
                usuario.setFechaAlta(LocalDate.now());
                
                boolean estado = usuarioNeg.insertar(usuario);
                
                if(estado) {
                    request.setAttribute("mensaje", "El usuario fue registrado exitosamente.");
                } else {
                    request.setAttribute("mensaje", "Error al registrar el usuario.");
                }
            } else {
                request.setAttribute("mensaje","Error: las contraseñas no coinciden");
            }
        }
        
        if(request.getParameter("btnEliminarUsuario") != null) {
            int usuarioEliminar = Integer.parseInt(request.getParameter("usuarioEliminar"));
            boolean estado = usuarioNeg.eliminarUsuario(usuarioEliminar);
            
            if(estado) {
                request.setAttribute("mensaje", "El usuario fue eliminado exitosamente.");
            } else {
                request.setAttribute("mensaje", "Error: No se pudo eliminar el usuario.");
            }
        }
        

        ArrayList<Usuario> listaUsuarios = usuarioNeg.listarUsuarios();
        request.setAttribute("usuarios", listaUsuarios);
        
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("mensaje", "Error: Ocurrió un error en la operación");
    }
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("UsuariosAdmin.jsp");
    dispatcher.forward(request, response);
}
}
