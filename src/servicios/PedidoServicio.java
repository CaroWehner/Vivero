package servicios;

import java.sql.Date;
import entidades.Pedido;

public class PedidoServicio {
    
    public Pedido crearNuevoCliente(int codigoPedido, Date fechaPedido, Date fechaEsperada, Date fechaEntrega, String estado,
            String comentarios, int idCliente) throws Exception {
        // Validaciones - Pueden estar metodo independiente.
        //validacionesNyA(nombreContacto, apellidoContacto);
        Pedido pedido = new Pedido(codigoPedido, fechaPedido, fechaEsperada, fechaEntrega, estado,
        comentarios, idCliente);
        cd.guardarPedido(pedido);
        return pedido;
    }
}
