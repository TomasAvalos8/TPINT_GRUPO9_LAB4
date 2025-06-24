package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Negocio.ClienteNeg;
import NegocioImpl.ClienteNegImpl;
import Negocio.UsuarioNeg;
import NegocioImpl.UsuarioNegImpl;
import Dominio.Cliente;
import Dominio.TipoUsuario;
import Dominio.Usuario;
/**
 * Servlet implementation class ServletClientes
 */
@WebServlet("/ServletClientes")
public class ServletClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   ClienteNeg clienteNeg = new ClienteNegImpl();
   UsuarioNeg usuarioNeg = new UsuarioNegImpl();
    public ServletClientes() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnRegistrar")!=null) {
			Usuario usuario = new Usuario();
			Cliente cliente = new Cliente();
			TipoUsuario tipo= new TipoUsuario(2, "cliente");
			usuario.setUsuario(request.getParameter("usuario"));
			usuario.setContraseña(request.getParameter("contraseña"));
			usuario.setTipoUsuario(tipo);
			cliente.setUsuario(usuario);
			
		}
	}

}
