package persistencia;

import java.util.*;
import entidades.Cliente;

public class ClienteDAO extends DAO {
    public void guardarCliente(Cliente cliente) throws Exception {
        try {
            if (cliente == null) {
                throw new Exception("El cliente no puede ser nulo.");
            }
            String sql = "INSERT INTO Cliente (codigo_cliente, nombre_cliente,nombre_contacto, " +
                    "apellido_contacto, telefono, fax, ciudad, region, pais, codigo_postal, " +
                    "id_empleado, limite_credito) VALUES (" +
                    cliente.getCodigoCliente() + ", '" +
                    cliente.getNombreCliente() + "', '" +
                    cliente.getNombreContacto() + "', '" +
                    cliente.getApellidoContacto() + "', '" +
                    cliente.getTelefono() + "', '" +
                    cliente.getFax() + "', '" +
                    cliente.getCiudad() + "', '" +
                    cliente.getRegion() + "', '" +
                    cliente.getPais() + "', '" +
                    cliente.getCodigoPostal() + "', " +
                    cliente.getIdEmpleado() + ", " +
                    cliente.getLimiteCredito() + ");";
            insertarModificarEliminarDataBase(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarTodosLosClientes() throws Exception {
        try {
            String sql = "SELECT * FROM cliente";

            consultarDataBase(sql);
            //Cliente cliente = null;
            List<Cliente> clientes = new ArrayList<>();
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigoCliente(resultSet.getInt("codigo_cliente"));
                cliente.setNombreCliente(resultSet.getString("nombre_cliente")); 
                cliente.setNombreContacto(resultSet.getString("nombre_contacto")); 
                cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
                cliente.setTelefono(resultSet.getString("telefono"));  
                cliente.setFax(resultSet.getString("fax")); 
                cliente.setCiudad(resultSet.getString("ciudad")); 
                cliente.setRegion(resultSet.getString("region")); 
                cliente.setPais(resultSet.getString("pais")); 
                cliente.setCodigoPostal(resultSet.getString("codigo_postal")); 
                cliente.setIdEmpleado(resultSet.getInt("id_empleado")); 
                cliente.setLimiteCredito(resultSet.getInt("limite_credito")) ;
                clientes.add(cliente);
            }
            for (Cliente cliente : clientes) {
                //System.out.println(clienteUnitario.imprimirNyA());
                System.out.println(cliente.toString());
            }
            System.out.println("");
            desconectarDataBase();
        } catch (Exception e) {
            e.printStackTrace();
            desconectarDataBase();
            throw e;
        }
    }

    public Cliente buscarClientePorCodigo(int codigo) throws Exception {
        try {
            String sql = "SELECT * FROM cliente WHERE codigo_cliente = " + codigo;
            consultarDataBase(sql);
            Cliente cliente = null;
            while (resultSet.next()) {

                cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setCodigoCliente(resultSet.getInt("codigo_cliente"));
                cliente.setNombreCliente(resultSet.getString("nombre_cliente"));
                cliente.setNombreContacto(resultSet.getString("nombre_contacto"));
                cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setFax(resultSet.getString("fax"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setCodigoPostal(resultSet.getString("pais"));
                cliente.setRegion(resultSet.getString("region"));
                cliente.setIdEmpleado(resultSet.getInt("id_empleado"));
                cliente.setLimiteCredito(resultSet.getDouble("limite_credito"));
            }
            desconectarDataBase();
            return cliente;
        } catch (Exception e) {
            desconectarDataBase();
            throw e;
        }
    }
}
