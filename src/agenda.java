import java.util.TreeSet;
import java.util.Set;

import java.util.TreeSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Agenda {

    private Map<String, Contacto> Busqueda = new TreeMap<>();

    private Set<String> Grupos = new TreeSet<>();

    private List<Contacto> Historial = new ArrayList<>();

    private Map<String, Set<String>> Relacion = new TreeMap<>();

    // Agregar contacto
    public void agregarContacto(Contacto c) {

        String nombreUser = c.getNombre();

        if (Busqueda.containsKey(nombreUser)) {
            System.out.println("Error: Ya existe un contacto con el nombre '"
                    + nombreUser + "'");
        } else {
            Busqueda.put(nombreUser, c);
            Historial.add(c);

            System.out.println("Contacto agregado: " + nombreUser);
        }
    }

    // Buscar contacto
    public Contacto buscarContacto(String nombre) {
        return Busqueda.get(nombre);
    }

    // Eliminar contacto del mapa, historial y grupos
    public boolean eliminarContacto(String nombre) {

        if (!Busqueda.containsKey(nombre)) {
            return false;
        }

        Contacto contacto = Busqueda.remove(nombre);

        Historial.remove(contacto);

        for (Set<String> contactosGrupo : Relacion.values()) {
            contactosGrupo.remove(nombre);
        }

        return true;
    }

    // Listar contactos en orden alfabetico
    public void listarContactos() {

        System.out.println("\n--- LISTA DE CONTACTOS (orden alfabetico) ---");

        if (Busqueda.isEmpty()) {
            System.out.println("No hay contactos registrados.");
            return;
        }

        for (Contacto c : Busqueda.values()) {
            System.out.println(c);
        }
    }

    // Listar historial en orden de insercion
    public void listarHistorial() {

        System.out.println("\n--- HISTORIAL (orden de insercion) ---");

        if (Historial.isEmpty()) {
            System.out.println("No hay contactos en el historial.");
            return;
        }

        for (Contacto c : Historial) {
            System.out.println(c);
        }
    }

    // Agregar un grupo
    public void agregarGrupo(String nombreGrupo) {

        if (Grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo
                    + "' ya existe");
        } else {
            Grupos.add(nombreGrupo);
            Relacion.put(nombreGrupo, new TreeSet<>());

            System.out.println("Grupo '" + nombreGrupo + "' creado");
        }
    }

    // Agregar contacto a un grupo
    public void agregarContactoAGrupo(
            String nombreContacto, String nombreGrupo) {

        if (!Busqueda.containsKey(nombreContacto)) {
            System.out.println("Error: El contacto '" + nombreContacto
                    + "' no existe");
        } else if (!Grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo
                    + "' no existe");
        } else {

            Relacion.get(nombreGrupo).add(nombreContacto);

            System.out.println("Contacto agregado al grupo '"
                    + nombreGrupo + "'");
        }
    }

    // Listar grupos en orden alfabetico
    public void listarGrupos() {

        System.out.println("\n--- LISTA DE GRUPOS ---");

        if (Grupos.isEmpty()) {
            System.out.println("No hay grupos registrados.");
            return;
        }

        for (String grupo : Grupos) {
            System.out.println(grupo);
        }
    }

    // Mostrar contactos de un grupo
    public void mostrarContactosPorGrupo(String nombreGrupo) {

        if (!Grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo
                    + "' no existe");
            return;
        }

        Set<String> nombres = Relacion.get(nombreGrupo);

        if (nombres.isEmpty()) {
            System.out.println("El grupo '" + nombreGrupo
                    + "' no tiene contactos");
            return;
        }

        System.out.println("\nContactos en '" + nombreGrupo + "':");

        for (String nombre : nombres) {
            System.out.println("- " + Busqueda.get(nombre));
        }
    }
}
