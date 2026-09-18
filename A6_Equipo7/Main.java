import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Grupo grupo = new Grupo();
        System.out.print("Nombre de la asignatura: ");
        grupo.setNombreAsignatura(sc.nextLine());

        String opcion;
        do {
            System.out.println();
            System.out.println("1. Asignar profesor");
            System.out.println("2. Agregar alumno");
            System.out.println("3. Mostrar información del grupo");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    asignarProfesor(grupo);
                    break;
                case "2":
                    agregarAlumno(grupo);
                    break;
                case "3":
                    System.out.println();
                    System.out.println("     INFORMACIÓN DEL GRUPO      ");
                    System.out.println("Asignatura: " + grupo.getNombreAsignatura());

                    System.out.println("     Profesor     ");
                    Profesor p = grupo.getProfesor();
                    if (p.getNumeroEmpleado() == null) {
                        System.out.println("Sin profesor asignado.");
                    } else {
                        System.out.println("Nombre: " + p.getNombre() + " "
                                + p.getApellidoPaterno() + " " + p.getApellidoMaterno());
                        System.out.println("Grado académico: " + p.getGradoAcademico());
                        System.out.println("Número de empleado: " + p.getNumeroEmpleado());
                    }

                    List<Alumno> alumnos = grupo.getAlumnos();
                    System.out.println("   Alumnos (" + alumnos.size() + ")   ");
                    if (alumnos.isEmpty()) {
                        System.out.println("No hay alumnos inscritos.");
                    } else {
                        int i = 1;
                        for (Alumno a : alumnos) {
                            System.out.println(i + ". " + a.getNombre() + " "
                                    + a.getApellidoPaterno() + " " + a.getApellidoMaterno()
                                    + " | Cuenta: " + a.getNumeroDeCuenta());
                            i++;
                        }
                    }
                    break;
                case "4":
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (!opcion.equals("4"));
    }

    private static void asignarProfesor(Grupo grupo) {
        System.out.println("    Datos del profesor   ");
        Profesor profesor = new Profesor();
        System.out.print("Nombre: ");
        profesor.setNombre(sc.nextLine());
        System.out.print("Apellido paterno: ");
        profesor.setApellidoPaterno(sc.nextLine());
        System.out.print("Apellido materno: ");
        profesor.setApellidoMaterno(sc.nextLine());
        System.out.print("Grado académico: ");
        profesor.setGradoAcademico(sc.nextLine());
        System.out.print("Número de empleado: ");
        profesor.setNumeroEmpleado(sc.nextLine());
        grupo.setProfesor(profesor);
        System.out.println("Profesor asignado correctamente.");
    }

    private static void agregarAlumno(Grupo grupo) {
        System.out.println("--- Datos del alumno ---");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido paterno: ");
        String apellidoPaterno = sc.nextLine();
        System.out.print("Apellido materno: ");
        String apellidoMaterno = sc.nextLine();
        System.out.print("Número de cuenta: ");
        String numeroDeCuenta = sc.nextLine();
        grupo.agregarAlumno(nombre, apellidoPaterno, apellidoMaterno, numeroDeCuenta);
        System.out.println("Alumno agregado correctamente.");
    }
}