package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Negocio.UsuarioNeg;
import NegocioImpl.UsuarioNegImpl;

@WebServlet("/RecuperarPasswordServlet")
public class RecuperarPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioNeg usuarioNeg = new UsuarioNegImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String contexto = request.getParameter("contexto");
        String usuario = request.getParameter("usuario");
        String dni = request.getParameter("dni");
        String nuevaContraseña = request.getParameter("pass");
        String confirmarContraseña = request.getParameter("confirmpass");

        
        if (!validarContraseñas(nuevaContraseña, confirmarContraseña)) {
            redirigirConError(response, contexto, "Las contraseñas no coinciden o no cumplen los requisitos");
            return;
        }

        try {
            boolean actualizado;
            if ("perfil".equals(contexto)) {
            	 String usuarioSesion = (String) session.getAttribute("usuario");
                 actualizado = usuarioNeg.recuperarContraseñaCliente(usuarioSesion, nuevaContraseña);
            } else {
                if (!validarCamposLogin(usuario, dni)) {
                    redirigirConError(response, contexto, "Usuario y DNI son requeridos");
                    return;
                }
                actualizado = usuarioNeg.recuperarContraseña(usuario, dni, nuevaContraseña);
            }

            if (actualizado) {
                redirigirConExito(response, contexto);
            } else {
                String mensajeError = "perfil".equals(contexto) ? 
                    "Error al actualizar contraseña" : "Usuario o DNI incorrectos";
                redirigirConError(response, contexto, mensajeError);
            }
        } catch (Exception e) {
            redirigirConError(response, contexto, "Error en el servidor");
        }
    }

    private boolean validarContraseñas(String pass, String confirmPass) {
        return pass != null && confirmPass != null &&
               pass.equals(confirmPass) && 
               pass.length() >= 6;
    }

    private boolean validarCamposLogin(String usuario, String dni) {
        return usuario != null && !usuario.trim().isEmpty() &&
               dni != null && !dni.trim().isEmpty();
    }

    private void redirigirConError(HttpServletResponse response, String contexto, String error) 
            throws IOException {
        String pagina = "perfil".equals(contexto) ? 
            "RecuperarPasswordCliente.jsp" : "RecuperarPassword.jsp";
        response.sendRedirect(pagina + "?error=" + java.net.URLEncoder.encode(error, "UTF-8"));
    }

    private void redirigirConExito(HttpServletResponse response, String contexto) 
            throws IOException {
        String pagina = "perfil".equals(contexto) ? 
            "DatosPersonales.jsp" : "Inicio.jsp";
        response.sendRedirect(pagina + "?success=Contraseña actualizada correctamente");
    }
}