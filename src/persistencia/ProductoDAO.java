package persistencia;
import java.util.*;

public class ProductoDAO extends DAO {
    public void eliminarProducto(int codigo) throws Exception {
        try {
            String sql = "DELETE FROM Producto WHERE id_producto = " + codigo + "";
            insertarModificarEliminarDataBase(sql);
            System.out.println("El registro fue eliminado de manera exitosa");
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDataBase();
        }
    }
}
