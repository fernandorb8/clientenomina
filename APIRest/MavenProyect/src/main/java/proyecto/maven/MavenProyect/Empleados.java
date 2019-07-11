package proyecto.maven.MavenProyect;

public class Empleados {

    private final long id;
    private final String salario;
    private final String nombre;

    public Empleados (long id, String salario, String nombre) {
        this.id = id;
        this.salario = salario;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public String getSalario() {
        return salario;
    }
    
    public String getNombre() {
        return nombre;
    }
}