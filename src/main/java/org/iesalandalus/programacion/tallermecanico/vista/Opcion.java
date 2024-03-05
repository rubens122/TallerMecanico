package org.iesalandalus.programacion.tallermecanico.vista;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public enum Opcion {
    INSERTAR_CLIENTE(1, "Insertar cliente"),
    BUSCAR_CLIENTE(2, "Buscar cliente"),
    BORRAR_CLIENTE(3, "Borrar cliente"),
    LISTAR_CLIENTES(4, "Listar cliente"),
    MODIFICAR_CLIENTES(5, "Modificar cliente"),
    INSERTAR_VEHICULO(6, "Insertar vehiculo"),
    BUSCAR_VEHICULO(7, "Buscar vehiculo"),
    BORRAR_VEHICULO(8, "Borrar vehiculo"),
    LISTAR_VEHICULOS(9, "Listar vehiculos"),
    INSERTAR_REVISIONES(10, "Insertar revisiones"),
    BUSCAR_REVISIONES(11, "Buscar revisiones"),
    BORRAR_REVISIONES(12, "Borrar revisiones"),
    LISTAR_REVISIONES(13, "Listar revisiones"),
    LISTAR_REVISIONES_CLIENTE(14, "Listar revisiones cliente"),
    LISTAR_REVISIONES_VEHICULO(15, "Listar revisiones vehiculo"),
    ANADIR_HORAS_REVISION(16, "Añadir horas a la revision."),
    ANADIR_PRECIO_MATERIAL_REVISION(17, "Añadir precio material a revision"),
    CERRAR_REVISION(18, "Cerrar revision"),
    SALIR(19, "Salir");
    
    private final int numeroOpcion;
    private final String mensaje;
    public static Map<Integer, Opcion> opciones = new TreeMap<>();

    private  Opcion(int numeroOpcion, String mensaje) {
        this.numeroOpcion = numeroOpcion;
        this.mensaje = mensaje;
    }

    static {
        for (Opcion opcion : values()) {
            opciones.put(opcion.numeroOpcion, opcion);
        }
    }

    public static boolean esValida(int numeroOpcion) {
        return opciones.containsKey(numeroOpcion);
    }

    public static Opcion get(int numeroOpcion) {
        if (!esValida(numeroOpcion)) {
            throw new IllegalArgumentException("La opción es incorrecta");
        }
        return opciones.get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("%d.- %s%n", numeroOpcion, mensaje);
    }
}

