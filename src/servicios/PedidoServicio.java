package servicios;

import java.sql.Date;

import entidades.DetallePedido;
import entidades.Pedido;
import persistencia.PedidoDAO;

public class PedidoServicio {
    private PedidoDAO pd;
    private DetallePedido dp;
    
    public PedidoServicio() {
        this.pd = new PedidoDAO();
        this.dp = new DetallePedido();
    }

    public Pedido crearNuevoPedido(int codigoPedido, Date fechaPedido, Date fechaEsperada, Date fechaEntrega, String estado,
            String comentarios, int idCliente) throws Exception {

        Pedido pedido = new Pedido(codigoPedido, fechaPedido, fechaEsperada, fechaEntrega, estado,
        comentarios, idCliente);
        pd.guardarPedido(pedido);
        return pedido;
    }

    public int obtenerIdPedido(){
        
    }

    
}
