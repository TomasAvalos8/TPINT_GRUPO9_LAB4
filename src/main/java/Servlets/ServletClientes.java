package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	        String idProvincia = request.getParameter("idProvincia");
	        if (idProvincia != null && !idProvincia.isEmpty()) {
	            int id = Integer.parseInt(idProvincia);
	            List<Localidad> localidades = locNeg.obtenerLocalidades(id);

	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");

	     
	            StringBuilder json = new StringBuilder("[");
	            for (int i = 0; i < localidades.size(); i++) {
	                Localidad localidad = localidades.get(i);
	                json.append("{")
	                    .append("\"id_localidad\":").append(localidad.getId_localidad()).append(",")
	                    .append("\"descripcion\":\"").append(localidad.getDescripcion()).append("\"")
	                    .append("}");
	                if (i < localidades.size() - 1) {
	                    json.append(",");
	                }
	            }
	            json.append("]");

	            response.getWriter().write(json.toString());
	            return;
	        }

	        List<Provincia> provincias = provNeg.obtenerProvincias();
	        request.setAttribute("provincias", provincias);
	        
	      
	        
	        List<Cliente> listaClientes = clienteNeg.listarClientes();
	        request.setAttribute("listaClientes", listaClientes);
	        
	        request.getRequestDispatcher("Clientes_Admin.jsp").forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la solicitud");
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
			if (IdGuardado > 0) {
			    cliente.setIdUsuario(IdGuardado);
			    cliente.setDni(Integer.parseInt(request.getParameter("dni")));
			    cliente.setCuil(request.getParameter("cuil"));
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
				request.setAttribute("mensaje", "El cliente fue registrado exitosamente.");
				List<Cliente> listaClientes = clienteNeg.listarClientes();
				request.setAttribute("listaClientes", listaClientes);
				List<Provincia> provincias = provNeg.obtenerProvincias();
				request.setAttribute("provincias", provincias);
				} else {
				request.setAttribute("mensaje", "Error al registrar el cliente.");
				}
			} else {
			    request.setAttribute("mensaje", "Error al registrar el usuario.");
			}
			}else {
				request.setAttribute("mensaje", "Error: las contraseñas son distintas");
			}
			List<Provincia> provincias = provNeg.obtenerProvincias();
	        request.setAttribute("provincias", provincias);
			
			request.getRequestDispatcher("Clientes_Admin.jsp").forward(request, response);
			
		}
		
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
		
		String modificarId = request.getParameter("modificarId");
		if (modificarId != null) {
            int dniModificar = Integer.parseInt(modificarId);
            List<Cliente> listaClientes = clienteNeg.listarClientes();
            Cliente clienteModificar = null;
            for (Cliente c : listaClientes) {
                if (c.getDni() == dniModificar) {
                    clienteModificar = c;
                    break;
                }
            }
            request.setAttribute("clienteModificar", clienteModificar);
            request.setAttribute("listaClientes", listaClientes);
            List<Provincia> provincias = provNeg.obtenerProvincias();
            request.setAttribute("provincias", provincias);
            if (clienteModificar != null) {
                List<Localidad> localidades = locNeg.obtenerLocalidades(clienteModificar.getId_provincia());
                request.setAttribute("localidades", localidades);
                Usuario usuarioModificar = usuarioNeg.obtenerUsuarioPorId(clienteModificar.getIdUsuario());
                request.setAttribute("usuarioModificar", usuarioModificar);
            }
            request.getRequestDispatcher("Clientes_Admin.jsp").forward(request, response);
            return;
        }
        String actualizar = request.getParameter("actualizar");
        if (actualizar != null) {
            try {
                Cliente cliente = new Cliente();
				cliente.setCuil(request.getParameter("cuil"));
          
                
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
                cliente.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                cliente.setActivo(true);

                boolean actualizado = clienteNeg.actualizarCliente(cliente);

                String nuevoUsuario = request.getParameter("usuario");
                if (actualizado && nuevoUsuario != null && !nuevoUsuario.isEmpty()) {
                    Usuario usuario = usuarioNeg.obtenerUsuarioPorId(cliente.getIdUsuario());
                    if (usuario != null && !nuevoUsuario.equals(usuario.getUsuario())) {
                        usuario.setUsuario(nuevoUsuario);
                        usuarioNeg.actualizarUsuario(usuario);
                    }
                }

                if (actualizado) {
                    request.setAttribute("mensaje", "Cliente actualizado exitosamente.");
                } else {
                    request.setAttribute("mensaje", "Error al actualizar el cliente.");
                }
            } catch (Exception e) {
                request.setAttribute("mensaje", "Error al actualizar: " + e.getMessage());
                e.printStackTrace();
            }

            List<Cliente> listaClientes = clienteNeg.listarClientes();
            request.setAttribute("listaClientes", listaClientes);
            List<Provincia> provincias = provNeg.obtenerProvincias();
            request.setAttribute("provincias", provincias);
            
            request.getRequestDispatcher("Clientes_Admin.jsp").forward(request, response);
            return;
        }
	
		
	}

}
