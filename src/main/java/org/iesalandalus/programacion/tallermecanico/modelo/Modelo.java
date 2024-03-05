package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Modelo {
    private Revisiones revisiones;
    private Clientes clientes;
    private Vehiculos vehiculos;

    public Modelo() {
        comenzar();
    }
    public void comenzar(){
        revisiones = new Revisiones();
        clientes = new Clientes();
        vehiculos = new Vehiculos();
    }
    public void terminar(){
        System.out.println("El modelo ha terminado.");
    }
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(new Cliente(cliente));
    }
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }
    public void insertar(Revision revision) throws OperationNotSupportedException {
        Cliente buscarCliente = clientes.buscar(revision.getCliente());
        Vehiculo buscarVehiculo = vehiculos.buscar(revision.getVehiculo());
        revision = new Revision(buscarCliente, buscarVehiculo, revision.getFechaInicio());
        revisiones.insertar(revision);
    }
    public Cliente buscar (Cliente cliente){
        cliente = Objects.requireNonNull(clientes.buscar(cliente), "No existe un cliente igual");
        return new Cliente(cliente);
    }
    public Vehiculo buscar (Vehiculo vehiculo){
        vehiculo = Objects.requireNonNull(vehiculos.buscar(vehiculo), "No existe un vehículo igual");
        return vehiculo;
    }
    public Revision buscar (Revision revision){
        revision = Objects.requireNonNull(revisiones.buscar(revision), "No existe una revision igual");
        return new Revision(revision);
    }
    public boolean modificar (Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente, nombre, telefono);
    }
    public void anadirHoras (Revision revision, int horas) throws OperationNotSupportedException {
        revisiones.anadirHoras(revision, horas);
    }
    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws OperationNotSupportedException {
        revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }
    public void cerrar(Revision revision, LocalDate fechaFin) throws OperationNotSupportedException {
        revisiones.cerrar(revision, fechaFin);
    }
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        for (Revision revision : revisiones.get(cliente)){
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }
    public void borrar (Vehiculo vehiculo) throws OperationNotSupportedException {
        for (Revision revision : revisiones.get(vehiculo)){
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }
    public void borrar (Revision revision) throws OperationNotSupportedException {
        revisiones.borrar(revision);
    }
    public List<Cliente> getClientes(){
        List<Cliente> nuevaListaCliente = new ArrayList<>();
        for (Cliente cliente : clientes.get()){
            nuevaListaCliente.add(new Cliente(cliente));
        }
        return nuevaListaCliente;
    }
    public List<Vehiculo> getVehiculos(){
        return vehiculos.get();
    }
    public List<Revision> getRevisiones(){
        List<Revision> nuevaListaRevision = new ArrayList<>();
        for (Revision revision : revisiones.get()){
            nuevaListaRevision.add(new Revision(revision));
        }
        return nuevaListaRevision;
    }
    public List<Revision> getRevisiones(Cliente cliente){
        List<Revision> listaRevisionCliente = new ArrayList<>();
        for (Revision revision : revisiones.get(cliente)){
            listaRevisionCliente.add(new Revision(revision));

        }
        return listaRevisionCliente;
    }
    public List<Revision> getRevisiones(Vehiculo vehiculo){
        List<Revision> listaRevisionVehiculo = new ArrayList<>();
        for (Revision revision : revisiones.get(vehiculo)){
            listaRevisionVehiculo.add(new Revision(revision));
        }
        return listaRevisionVehiculo;
    }
}
