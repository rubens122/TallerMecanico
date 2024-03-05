package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {
    private List<Cliente> clientes;

    public Clientes() {
        this.clientes = new ArrayList<>();
    }

    public List<Cliente> get() {
        return new ArrayList<>(clientes);
    }

    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (clientes.contains(cliente)) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        clientes.add(cliente);
    }

    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        Cliente clienteEncontrado = buscar(cliente);
        if (clienteEncontrado == null){
            throw new OperationNotSupportedException("");
        }
        boolean modificado = false;
        if (nombre != null && !nombre.isBlank()){
            clienteEncontrado.setNombre(nombre);
            modificado = true;
        }
        if(telefono != null && !telefono.isBlank()){
            clienteEncontrado.setTelefono(telefono);
            modificado = true;
        }
        return modificado;
    }



    public Cliente buscar(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("No se puede buscar un cliente nulo.");
        }
        int indice = clientes.indexOf(cliente);
        if (indice != -1) {
            return clientes.get(indice);
        }
        return null;
    }


    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (!clientes.remove(cliente)) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        else {
            clientes.remove(cliente);
        }
    }
}
