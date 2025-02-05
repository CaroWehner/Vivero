package servicios;

import entidades.Cliente;
import persistencia.ClienteDAO;

public class ClienteServicio {
    private ClienteDAO cd;

    public ClienteServicio() {
        this.cd = new ClienteDAO();
    }

    public Cliente crearNuevoCliente(int codigoC, String nombre, String nombreContacto, String apellidoContacto,
            String telefono, String fax, String ciudad, String region, String pais, String codigoPostal,
            int idEmpleado, double limiteCredito) throws Exception {
        // Validaciones - Pueden estar metodo independiente.
        validacionesNyA(nombreContacto, apellidoContacto);
        Cliente cliente = new Cliente(codigoC, nombre, nombreContacto, apellidoContacto, telefono, fax, ciudad, region,
                pais, codigoPostal, idEmpleado, limiteCredito);
        cd.guardarCliente(cliente);
        return cliente;
    }

    public void validacionesNyA(String nombreContacto, String apellidoContacto) throws Exception {
        if (nombreContacto == null) {
            throw new Exception("El nombre del contacto no puede ser nulo.");
        }
        if (apellidoContacto == null) {
            throw new Exception("El apellido del contacto no puede ser nulo.");
        }
    }

    public void listarLosClientes() throws Exception{
        cd.listarTodosLosClientes();
    }
    public void validacionClienteExistente(Cliente clienteValidar) throws Exception{
        if(clienteValidar == null)
        {
            throw new Exception("El cliente no existe.");
        }

    }

    public void validacionParametroCliente(int codigo) throws Exception{
        if(codigo < 1){
            throw new Exception("El codigo debe ser mayor a 0");
        }
    }

    public Cliente mostrarClienteCodigo (int codigo) throws Exception{
        validacionParametroCliente(codigo);
        Cliente clienteDB = cd.buscarClientePorCodigo(codigo);
        validacionClienteExistente(clienteDB);
        return clienteDB;        
    }
}
