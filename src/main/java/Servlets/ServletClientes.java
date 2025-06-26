package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Negocio.ClienteNeg;
import Negocio.ProvinciaNeg;
import Negocio.LocalidadNeg;
import NegocioImpl.ClienteNegImpl;
import NegocioImpl.ProvinciaNegImpl;
import NegocioImpl.LocalidadNegImpl;
import Negocio.UsuarioNeg;
import NegocioImpl.UsuarioNegImpl;

import Dominio.Cliente;
import Dominio.TipoUsuario;
import Dominio.Usuario;
import Dominio.Provincia;
import Dominio.Localidad;
/**
 * Servlet implementation class ServletClientes
 */
@WebServlet("/ServletClientes")
public class ServletClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   ClienteNeg clienteNeg = new ClienteNegImpl();
   UsuarioNeg usuarioNeg = new UsuarioNegImpl();
   ProvinciaNeg provNeg = new ProvinciaNegImpl();
   LocalidadNeg locNeg = new LocalidadNegImpl();
   public ServletClientes() {
        super();
        
    }

	
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        // Obtener provincias (esto debería estar antes de cualquier redirección)
	        List<Provincia> provincias = provNeg.obtenerProvincias();
	        request.setAttribute("provincias", provincias);
	        
	        // Resto de tu lógica...
	        String idProvincia = request.getParameter("idProvincia");
	        if (idProvincia != null && !idProvincia.isEmpty()) {
	            int id = Integer.parseInt(idProvincia);
	            List<Localidad> localidades = locNeg.obtenerLocalidades(id);
	            request.setAttribute("localidades", localidades);
	            request.setAttribute("idProvinciaSeleccionada", idProvincia); 
	        }
	        
	        // Listar clientes
	        List<Cliente> listaClientes = clienteNeg.listarClientes();
	        request.setAttribute("listaClientes", listaClientes);
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Clientes_Admin.jsp");
	        dispatcher.forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Manejo de errores
	    }
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnRegistrar")!=null) {
			if (request.getParameter("password").equals(request.getParameter("passwordConfirm"))) {

			Usuario usuario = new Usuario();
			Cliente cliente = new Cliente();
			TipoUsuario tipo= new TipoUsuario(2, "cliente");
			usuario.setUsuario(request.getParameter("usuario"));
			usuario.setContraseña(request.getParameter("password"));
			usuario.setTipoUsuario(tipo);
			LocalDate fechaActual = LocalDate.now();
			usuario.setFechaAlta(fechaActual);
			
			int IdGuardado = 0;
			IdGuardado = usuarioNeg.insertarYDevuelveId(usuario);
			if(IdGuardado!=0) {
			cliente.setIdUsuario(IdGuardado);
			cliente.setDni(Integer.parseInt(request.getParameter("dni")));
			cliente.setCuil(Integer.parseInt(request.getParameter("cuil")));
			cliente.setNombre(request.getParameter("nombre"));
			cliente.setApellido(request.getParameter("apellido"));
			cliente.setSexo(request.getParameter("sexo"));
			cliente.setNacionalidad(request.getParameter("nacionalidad"));
			cliente.setFecha_nacimiento(LocalDate.parse(request.getParameter("fechaNacimiento")));
			cliente.setDireccion(request.getParameter("direccion"));
			cliente.setId_localidad(Integer.parseInt(request.getParameter("idLocalidad")));
			cliente.setId_provincia(Integer.parseInt(request.getParameter("idProvincia")));
			cliente.setCorreo_electronico(request.getParameter("email"));
			cliente.setTelefono(request.getParameter("telefono"));
			cliente.setActivo(true);
			boolean estado= true;
			estado=clienteNeg.insertar(cliente);
			if (estado) {
				request.setAttribute("estadoCliente", estado);
				request.setAttribute("mensaje", "El cliente fue registrado correctamente.");
			}
			}
			}else {
				request.setAttribute("mensaje", "Error: las contraseñas son distintas");
			}
			List<Provincia> provincias = provNeg.obtenerProvincias();
	        request.setAttribute("provincias", provincias);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("Clientes_Admin.jsp");
			dispatcher.forward(request, response);
			
		}
		
		//eliminardni
		String eliminarId = request.getParameter("eliminarId");
		
		if (eliminarId != null) {
		    int dniEliminar = Integer.parseInt(eliminarId);
		    boolean eliminado = clienteNeg.eliminarCliente(dniEliminar);
		    
		    if (eliminado) {
		        request.setAttribute("mensajeServlet", "Cliente eliminado exitosamente.");
		    } else {
		        request.setAttribute("mensajeServlet", "No se pudo eliminar el cliente.");
		    }

		    List<Cliente> listaClientes = clienteNeg.listarClientes();
		    request.setAttribute("listaClientes", listaClientes);
		    request.getRequestDispatcher("Clientes_Admin.jsp").forward(request, response);
		    return;
		}
	
		
	}

}
