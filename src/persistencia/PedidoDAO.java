package persistencia;
import entidades.Pedido;
import java.util.*;
import persistencia.DAO;

public class PedidoDAO {
        public void guardarPedido(Pedido pedido) throws Exception {
        try {
            if (pedido == null) {
                throw new Exception("El pedido no puede ser nulo.");
            }
            String sql = "INSERT INTO pedido (codigo_pedido,fecha_pedido, fecha_esperada, fecha_entrega, estado, comentarios VALUES (" +
                    pedido.getCodigoPedido() + ", '" +
                    pedido.getFechaPedido() + "', '" +
                    pedido.getFechaEsperada() + "', '" +
                    pedido.getFechaEntrega() + "', '" +
                    pedido.getEstado() + "', '" +
                    pedido.getComentarios() +  ");";

            insertarModificarEliminarDataBase(sql);

        } catch (Exception e) {
            throw e;
        }
    }
}
