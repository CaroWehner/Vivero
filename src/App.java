
import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conexion = getConnection();
        Integer codigo_cliente = 2;
        System.out.println("Buscar Clientes:");
        buscarClientes(conexion);
        System.out.println("Buscar Clientes Por Código:");
        buscarClientePorCodigo(conexion, codigo_cliente);

        cerrarConexion(conexion);

    }

    public static Connection getConnection() {
        String host = "127.0.0.1"; // localhost
        String port = "3306"; // por defecto es el puerto que utiliza
        String name = "root"; // usuario de la base de datos
        String password = "root"; // password de la base de datos
        String database = "vivero"; // nombre de la base de datos recien creada, en este caso vivero.
        // Esto especifica una zona horaria, no es obligatorio de utilizar, pero en
        // algunas zonas genera conflictos de conexión si no existiera
        String zona = "?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database + zona;
        // esto indica la ruta de conexion, que es la combinacion de
        // host,usuario,puerto, nombre de la base de datos a la cual queremos
        // conectarnos y la zona horaria (si se precisara).

        Connection conexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(url, name, password);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el conector JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            e.printStackTrace(); // Muestra toda la traza del error
        }
        return conexion;
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("La conexión a la base de datos fue cerrada de manera exitosa");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión:" + e.getMessage());
            }
        }
    }

    public static void buscarClientes(Connection conexion) {
        String sql = "SELECT nombre_contacto, apellido_contacto, telefono FROM cliente ";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                String nombre = rs.getString("nombre_contacto");
                String apellido = rs.getString("apellido_contacto");
                String telefono = rs.getString("telefono");
                count++;
                System.out.println(count + " - " + nombre + " " + apellido + " -  " + telefono);
            }
            // Cerrar ResultSet y Statement dentro del bloque try-catch-finally
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    // Realiza un método llamado buscarClientePorCodigo(codigo) que reciba como
    // parámetro el código del
    // cliente y muestre por pantalla los datos que tiene el cliente guardado en la
    // base de datos.

    public static void buscarClientePorCodigo(Connection conexion, Integer codigo) {
        String sql = "SELECT * FROM cliente WHERE codigo_cliente = " + codigo;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnas = md.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i < columnas + 1; i++) {
                    System.out.print(rs.getString(i) + " | ");
                }
            }
            // Cerrar ResultSet y Statement dentro del bloque try-catch-finally
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    // Realiza un método llamado buscarClientesPorEmpleado(codigo) que reciba el
    // código del empleado como parámetro y muestre todos los clientes asociados a
    // un empleado en particular. Puedes elegir qué campos mostrar en tu método.
    public static void buscarClientePorEmpleado(Connection conexion, int codigo) {
        String sql = "SELECT nombre_contacto, apellido_contacto, telefono FROM cliente WHERE id_empleado = " + codigo;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            System.out.println("Clientes por según empleado: " + codigo);
            while (rs.next()) {
                String nombre = rs.getString("nombre_contacto");
                String apellido = rs.getString("apellido_contacto");
                String telefono = rs.getString("telefono");
                count++;
                System.out.println(count + " - " + nombre + " " + apellido + " - " + telefono);
            }
            // Cerrar ResultSet y Statement dentro del bloque try-catch-finally
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void getProductosParaReponer(Connection conexion, Integer punto) {
        String sql = "SELECT * FROM producto WHERE cantidad_en_stock < " + punto;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columnas = md.getColumnCount();
            System.out.println("Productos con un stock menor a :" + punto + " puntos");
            while (rs.next()) {
                for (int i = 1; i < columnas + 1; i++) {
                    System.out.print(rs.getString(i) + " | ");
                }
                System.out.println();
            }
            // Cerrar ResultSet y Statement dentro del bloque try-catch-finally
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void getPedidosPorCliente(Connection conexion, int idCliente) {
        String sql = "SELECT * FROM pedido WHERE id_cliente = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnas = md.getColumnCount();
            System.out.println("\nPedidos con id cliente: " + idCliente);
            while (rs.next()) {
                for (int i = 1; i <= columnas; i++) {
                    System.out.print(md.getColumnName(i) + ": " + rs.getString(i) + " | ");
                }
                System.out.println();
            }
            // Cerrar ResultSet y Statement dentro del bloque try-catch-finally
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void getPedidosPorEstado(Connection conection, String estado) {
        String sql = "SELECT estado, codigo_pedido,fecha_pedido FROM pedido WHERE estado = ? ";
        try {
            PreparedStatement ps = conection.prepareStatement(sql);
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            int count = 0;
            System.out.println("number | estado | codigo | fecha");
            while (rs.next()) {
                String db_estado = rs.getString("estado");
                Integer codigo = rs.getInt("codigo_pedido");
                Date date = rs.getDate("fecha_pedido");
                count++;
                System.out.println(count + " - " + db_estado + " " + codigo + " - " + date);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }

    }

}
