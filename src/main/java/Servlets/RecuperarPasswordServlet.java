package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatosImpl.UsuarioDaoImpl;

@WebServlet("/RecuperarPasswordServlet")
public class RecuperarPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
    	 String usuario = request.getParameter("usuario");
    	 String dni = request.getParameter("dni");
    	 String nuevaContraseña = request.getParameter("pass"); 
    	 String confirmarContraseña = request.getParameter("confirmpass"); 
        
        if (usuario == null || dni == null || nuevaContraseña == null || confirmarContraseña == null) {
            response.sendRedirect("RecuperarPassword.jsp?error=Datos incompletos");
            return;
        }
        
        usuario = usuario.trim();
        dni = dni.trim();
        nuevaContraseña = nuevaContraseña.trim();
        confirmarContraseña = confirmarContraseña.trim();
        
        if (nuevaContraseña.isEmpty() || confirmarContraseña.isEmpty()) {
            response.sendRedirect("RecuperarPassword.jsp?error=Las contraseñas no pueden estar vacías");
            return;
        }
        
        if (!nuevaContraseña.equals(confirmarContraseña)) {
            response.sendRedirect("RecuperarPassword.jsp?error=Las contraseñas no coinciden");
            return;
        }
        
        if (nuevaContraseña.length() < 6) {
            response.sendRedirect("RecuperarPassword.jsp?error=La contraseña debe tener al menos 6 caracteres");
            return;
        }
        
        boolean actualizado = usuarioDao.recuperarContraseña(usuario, dni, nuevaContraseña);
        
        if (actualizado) {
            response.sendRedirect("Inicio.jsp?success=Contraseña actualizada correctamente");
        } else {
            response.sendRedirect("RecuperarPassword.jsp?error=Usuario o DNI incorrectos");
        }
    }
}