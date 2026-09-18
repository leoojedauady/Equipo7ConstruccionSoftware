import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private String nombreAsignatura;
    private Profesor profesor;
    private List<Alumno> alumnos;

    public Grupo() {
        this.profesor = new Profesor();
        this.alumnos = new ArrayList<>();
    }

    public Grupo(String nombreAsignatura) {
        this();
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public Profesor getProfesor() {
        return new Profesor(profesor.getNombre(), profesor.getApellidoPaterno(),profesor.getApellidoMaterno(), profesor.getGradoAcademico(),profesor.getNumeroEmpleado());
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = new Profesor(profesor.getNombre(), profesor.getApellidoPaterno(), profesor.getApellidoMaterno(), profesor.getGradoAcademico(), profesor.getNumeroEmpleado());
    }

    public List<Alumno> getAlumnos() {
        List<Alumno> copia = new ArrayList<>();
        for (Alumno a : alumnos) {
            copia.add(new Alumno(a.getNombre(), a.getApellidoPaterno(), a.getApellidoMaterno(), a.getNumeroDeCuenta()));
        }
        return copia;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = new ArrayList<>();
        for (Alumno a : alumnos) {
            agregarAlumno(a.getNombre(), a.getApellidoPaterno(), a.getApellidoMaterno(), a.getNumeroDeCuenta());
        }
    }

    public void agregarAlumno(String nombre, String apellidoPaterno, String apellidoMaterno, String numeroDeCuenta) {
        Alumno alumno = new Alumno();
        alumno.setNombre(nombre);
        alumno.setApellidoPaterno(apellidoPaterno);
        alumno.setApellidoMaterno(apellidoMaterno);
        alumno.setNumeroDeCuenta(numeroDeCuenta);
        alumnos.add(alumno);
    }
}