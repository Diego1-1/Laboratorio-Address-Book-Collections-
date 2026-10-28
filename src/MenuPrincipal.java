
import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();

        int opcion = 0;

        while (opcion != 10) {

            System.out.println("\n=== AGENDA DE CONTACTOS ===");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Listar contactos (orden alfabetico)");
            System.out.println("5. Listar historial (orden de insercion)");
            System.out.println("6. Agregar grupo");
            System.out.println("7. Agregar contacto a grupo");
            System.out.println("8. Listar grupos");
            System.out.println("9. Mostrar contactos por grupo");
            System.out.println("10. Salir");
            System.out.println("==========================");
            System.out.print("Elige una opcion: ");

            // Validar que la entrada sea un numero
            if (!scanner.hasNextInt()) {
                System.out.println("Opcion invalida, intenta de nuevo");
                scanner.nextLine();
                continue;
            }

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el salto de linea

            switch (opcion) {

                case 1:
                    System.out.println("\n--- AGREGAR CONTACTO ---");

                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Telefono: ");
                    String telefono = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    Contacto nuevo = new Contacto(
                            nombre, telefono, email);

                    agenda.agregarContacto(nuevo);
                    break;

                case 2:
                    System.out.print("Nombre del contacto: ");
                    nombre = scanner.nextLine();

                    Contacto encontrado =
                            agenda.buscarContacto(nombre);

                    if (encontrado != null) {
                        System.out.println("Contacto encontrado:");
                        System.out.println(encontrado);
                    } else {
                        System.out.println(
                                "Contacto no encontrado: " + nombre);
                    }
                    break;

                case 3:
                    System.out.print("Nombre del contacto a eliminar: ");
                    nombre = scanner.nextLine();

                    if (agenda.eliminarContacto(nombre)) {
                        System.out.println("Contacto eliminado: " + nombre);
                    } else {
                        System.out.println(
                                "Contacto no encontrado: " + nombre);
                    }
                    break;

                case 4:
                    agenda.listarContactos();
                    break;

                case 5:
                    agenda.listarHistorial();
                    break;

                case 6:
                    System.out.print("Nombre del grupo: ");
                    String nombreGrupo = scanner.nextLine();

                    agenda.agregarGrupo(nombreGrupo);
                    break;

                case 7:
                    System.out.print("Nombre del contacto: ");
                    nombre = scanner.nextLine();

                    System.out.print("Nombre del grupo: ");
                    nombreGrupo = scanner.nextLine();

                    agenda.agregarContactoAGrupo(
                            nombre, nombreGrupo);
                    break;

                case 8:
                    agenda.listarGrupos();
                    break;

                case 9:
                    System.out.print("Nombre del grupo: ");
                    nombreGrupo = scanner.nextLine();

                    agenda.mostrarContactosPorGrupo(nombreGrupo);
                    break;

                case 10:
                    System.out.println("Saliendo de la agenda...");
                    break;

                default:
                    System.out.println(
                            "Opcion invalida, intenta de nuevo");
                    break;
            }
        }

        scanner.close();
    }
}
