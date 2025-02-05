package persistencia;

import entidades.DetallePedido;

public class DetallePedidoDAO {
    public void guardarDetallePedido(DetallePedido detallePedido) throws Exception {
        try {
            if (detallePedido == null) {
                throw new Exception("El pedido no puede ser nulo.");
            }
            String sql = "INSERT INTO pedido (id_pedido,id_producto, cantidad, precio_unidad, numero_linea VALUES ("
                    +
                    detallePedido.getIdPedido() + ", '" +
                    detallePedido.getIdProducto() + ", '" +
                    detallePedido.getCantidad() + "', '" +
                    detallePedido.getPrecioUnidad() + "', '" +
                    detallePedido.getNumero_linea() + ");";

            insertarModificarEliminarDataBase(sql);

        } catch (Exception e) {
            throw e;
        }
    }
}
