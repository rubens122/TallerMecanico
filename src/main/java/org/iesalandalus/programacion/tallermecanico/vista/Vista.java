package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Objects;

public class Vista {

    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "ERROR: El controlador no puede ser nulo.");
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
        controlador.terminar();
    }

    public void terminar() {
        System.out.println("El programa ha finalizado.");
    }

    private void ejecutar(Opcion opcion) {
        try {
            switch (opcion) {
                case INSERTAR_CLIENTE -> insertarCliente();
                case BUSCAR_CLIENTE -> buscarCliente();
                case BORRAR_CLIENTE -> borrarCliente();
                case LISTAR_CLIENTES -> listarClientes();
                case MODIFICAR_CLIENTES -> modificarCliente();
                case INSERTAR_VEHICULO -> insertarVehiculo();
                case BUSCAR_VEHICULO -> buscarVehiculo();
                case BORRAR_VEHICULO -> borrarVehiculo();
                case LISTAR_VEHICULOS -> listarVehiculos();
                case INSERTAR_REVISIONES -> insertarRevision();
                case BUSCAR_REVISIONES -> buscarRevision();
                case BORRAR_REVISIONES -> borrarRevision();
                case LISTAR_REVISIONES -> listarRevisiones();
                case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
                case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
                case ANADIR_HORAS_REVISION -> anadirHoras();
                case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
                case CERRAR_REVISION -> cerrar();
                case SALIR -> salir();
            }
        } catch (Exception e) {
            System.out.printf("Error : %s%n", e.getMessage());
        }
    }

    private void insertarCliente() throws OperationNotSupportedException {
        Consola.mostrarCabecera("INSERTAR CLIENTE");
        controlador.insertar(Consola.leerCliente());
        System.out.println("Cliente insertado correctamente");
    }

    private void insertarVehiculo() throws OperationNotSupportedException {
        Consola.mostrarCabecera("INSERTAR VEHÍCULO");
        controlador.insertar(Consola.leerVehiculo());
        System.out.println("Vehículo insertado correctamente");
    }

    private void insertarRevision() throws OperationNotSupportedException {
        Consola.mostrarCabecera("INSERTAR REVISIÓN");
        controlador.insertar(new Revision(Consola.leerClienteDni(), Consola.leerVehiculoMatricula(), Consola.leerFecha("Introduce la fecha de inicio:"))); // Ya busca el dni solo
    }

    private void buscarCliente() {
        Consola.mostrarCabecera("BUSCAR CLIENTE");
        System.out.println(controlador.buscar(Consola.leerClienteDni()));

    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera("BUSCAR VEHÍCULO");
        System.out.println(controlador.buscar(Consola.leerVehiculoMatricula()));
    }

    private void buscarRevision() {
        Consola.mostrarCabecera("BUSCAR REVISIÓN");
        System.out.println(controlador.buscar(Consola.leerRevision()));
    }

    private void modificarCliente() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Modificar cliente");
        boolean modificado = controlador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
        System.out.println((modificado) ? "El cliente se ha modificado correctamente" : "El cliente no se ha modificado");
    }

    private void anadirHoras() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Añadir Horas Revision");
        controlador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());
        System.out.println("Horas añadidas correctamente");
    }

    private void anadirPrecioMaterial() throws OperationNotSupportedException {
        Consola.mostrarCabecera("Añadir Precio Material");
        controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerPrecioMaterial());
        System.out.println("Precio añadido correctamente");
    }

    private void cerrar() {
        Consola.mostrarCabecera("CERRAR REVISIÓN");
        try {
            controlador.cerrar(new Revision(Consola.leerRevision()), Consola.leerFechaCierre());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarCliente() {
        Consola.mostrarCabecera("BORRAR CLIENTE");
        try {
            controlador.borrar(Consola.leerClienteDni());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarVehiculo() throws OperationNotSupportedException {
        Consola.mostrarCabecera("BORRAR VEHÍCULO");
        controlador.borrar(Consola.leerVehiculoMatricula());
    }

    private void borrarRevision() {
        Consola.mostrarCabecera("BORRAR REVISIÓN");
        try {
            controlador.borrar(Consola.leerRevision());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarClientes() {
        Consola.mostrarCabecera("LISTAR CLIENTES");
        List<Cliente> clientes = controlador.getClientes();
        if (clientes.isEmpty()) {
            System.out.println("No se ha encontrado ningún cliente.");
        } else {
            System.out.println(clientes);
        }
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera("LISTAR VEHÍCULOS");
        List<Vehiculo> vehiculos = controlador.getVehiculos();
        if (vehiculos.isEmpty()) {
            System.out.println("No se ha encontrado ningún vehículo.");
        } else {
            System.out.println(vehiculos);
        }
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera("LISTAR REVISIONES");
        List<Revision> revisiones = controlador.getRevision();
        if (revisiones.isEmpty()) {
            System.out.println("No se ha encontrado ninguna revisión.");
        } else {
            System.out.println(revisiones);
        }
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera("LISTAR REVISIONES DEL CLIENTE");
        List<Revision> revisionesCliente = controlador.getRevisiones(Consola.leerClienteDni());
        if (revisionesCliente.isEmpty()) {
            System.out.println("No se ha encontrado ninguna revisión para ese cliente.");
        } else {
            System.out.println(revisionesCliente);
        }
    }

    private void listarRevisionesVehiculo() {
        List<Revision> vehiculoList = controlador.getRevisiones(Consola.leerVehiculoMatricula());
        if (vehiculoList.isEmpty()) {
            System.out.println("No se ha encontrado ninguna revisión para ese vehículo.");
        } else {
            System.out.println(vehiculoList);
        }
    }

    private void salir() {

    }
}