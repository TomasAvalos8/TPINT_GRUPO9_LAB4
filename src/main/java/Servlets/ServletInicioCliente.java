package Servlets;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Dominio.Cliente;
import Dominio.Cuenta;
import Negocio.ClienteNeg;
import Negocio.CuentaNeg;
import NegocioImpl.ClienteNegImpl;
import NegocioImpl.CuentaNegImpl;

@WebServlet("/ServletInicioCliente")
public class ServletInicioCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInicioCliente() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        Integer idUsuario = (Integer) session.getAttribute("id_usuario");
       
        if (idUsuario != null) {
            ClienteNeg clienteNeg = new ClienteNegImpl();
            CuentaNeg cuentaNeg = new CuentaNegImpl();
            Cliente cliente = clienteNeg.obtenerClientePorIdUsuario(idUsuario.intValue());
            
            if (cliente != null) {     
                request.setAttribute("cliente", cliente);
                List<Cuenta> cuentas = cuentaNeg.obtenerCuentasPorDniCliente(cliente.getDni()); 
                request.setAttribute("cuentas", cuentas);

                String cuentaSeleccionada = request.getParameter("cuentaSeleccionada");
                int indiceCuentaSeleccionada = -1;
                
                if (cuentaSeleccionada != null && !cuentaSeleccionada.isEmpty()) {
                    try {
                        indiceCuentaSeleccionada = Integer.parseInt(cuentaSeleccionada);
                        if (cuentas != null && (indiceCuentaSeleccionada < 0 || indiceCuentaSeleccionada >= cuentas.size())) {
                            indiceCuentaSeleccionada = -1;
                        }
                    } catch (NumberFormatException e) {
                        indiceCuentaSeleccionada = -1;
                        request.setAttribute("mensajeError", e);
                    }
                }  
                if (cuentas != null && cuentas.size() == 1) {
                    indiceCuentaSeleccionada = 0;
                }
                request.setAttribute("indiceCuentaSeleccionada", indiceCuentaSeleccionada);
                request.setAttribute("totalCuentas", cuentas != null ? cuentas.size() : 0);
                
            } else {
                request.setAttribute("mensajeError", "No se pudo cargar el cliente.");
            }
        } else {
            response.sendRedirect("Login.jsp");
            return;
        }
        RequestDispatcher rd = request.getRequestDispatcher("/InicioCliente.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}