package Servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.CuotaNeg;
import NegocioImpl.CuotaNegImpl;
import Dominio.Cuota;
import Dominio.Cliente;
import Dominio.Cuenta;
import Negocio.ClienteNeg;
import Negocio.CuentaNeg;
import NegocioImpl.ClienteNegImpl;
import NegocioImpl.CuentaNegImpl;
import Dominio.Movimiento;
import Dominio.TipoMovimiento;
import Negocio.MovimientoNeg;
import NegocioImpl.MovimientoNegImpl;

@WebServlet("/CuotaServlet")
public class CuotaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuotaNeg cuotaNeg = new CuotaNegImpl();
    private ClienteNeg clienteNeg = new ClienteNegImpl();

    public CuotaServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Object idUsuarioSession = request.getSession().getAttribute("id_usuario");

            int idUsuario = Integer.parseInt(idUsuarioSession.toString());
            Cliente cliente = clienteNeg.obtenerClientePorIdUsuario(idUsuario);
            List<Cuota> listaCuotas = cuotaNeg.obtenerCuotasPorCliente(cliente.getDni());
            System.out.println("Lista de cuotas obtenida: " + listaCuotas.size() + " cuotas encontradas.");
            request.setAttribute("listaCuotas", listaCuotas);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al obtener las cuotas: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("CuotasCliente.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("pagar".equals(accion)) {
            try {
                int idCuota = Integer.parseInt(request.getParameter("idCuota"));

                // Marcar la cuota como pagada
                Cuota cuota = cuotaNeg.obtenerCuotaPorId(idCuota);
                cuota.setPagado(true);
                boolean cuotaActualizada = cuotaNeg.actualizarCuota(cuota);

                if (cuotaActualizada) {
                    // Registrar el movimiento
                    Movimiento movimiento = new Movimiento();
                    Cuenta cuenta = cuota.getPrestamo().getCuenta();
                    
                    movimiento.setNumeroCuenta(cuenta.getId());
                    movimiento.setDetalle("Pago de cuota " + cuota.getNumeroCuota());
                    movimiento.setMonto((float) -cuota.getMonto());
                    movimiento.setFecha(java.time.LocalDate.now());

                    TipoMovimiento tipoMovimiento = new TipoMovimiento();
                    tipoMovimiento.setIdTipoMovimiento(3); 
                    movimiento.setTipoMovimiento(tipoMovimiento);

                    MovimientoNeg movimientoNeg = new MovimientoNegImpl();
                    movimientoNeg.insertarMovimiento(movimiento);

                    cuenta.setSaldo((float)(cuenta.getSaldo() - cuota.getMonto()));
                    CuentaNeg cuentaNeg = new CuentaNegImpl();
                    boolean cuentaActualizada = cuentaNeg.actualizarCuenta(cuenta);

                    request.setAttribute("mensaje", "Cuota pagada exitosamente.");
                } else {
                    request.setAttribute("mensaje", "Error al actualizar la cuota.");
                }
                // ACTUALIZAR LA LISTA DE CUOTAS DESPUÉS DEL PAGO
                Object idUsuarioSession = request.getSession().getAttribute("id_usuario");
                int idUsuario = Integer.parseInt(idUsuarioSession.toString());
                Cliente cliente = clienteNeg.obtenerClientePorIdUsuario(idUsuario);
                List<Cuota> listaCuotas = cuotaNeg.obtenerCuotasPorCliente(cliente.getDni());
                request.setAttribute("listaCuotas", listaCuotas);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensaje", "Error al procesar el pago: " + e.getMessage());
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("CuotasCliente.jsp");
            dispatcher.forward(request, response);
        }
    }
}
