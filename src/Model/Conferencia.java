package Model;

public class Conferencia {
    private int idConferencia;
    private String titulo;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private String tema;
    private String marca;
    private int idUsuario;
    private String recursos;
    private int idSala;
    private int disponibilidad;
    private int cupos;

    // Constructor
    public Conferencia(int idConferencia, String titulo, String descripcion, String fechaInicio, String fechaFin,
                       String tema, String marca, int idUsuario, String recursos, int idSala, int disponibilidad, int cupos) {
        this.idConferencia = idConferencia;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tema = tema;
        this.marca = marca;
        this.idUsuario = idUsuario;
        this.recursos = recursos;
        this.idSala = idSala;
        this.disponibilidad = disponibilidad;
        this.cupos = cupos;
    }

    // Getters
    public int getIdConferencia() {
        return idConferencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public String getTema() {
        return tema;
    }

    public String getMarca() {
        return marca;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getRecursos() {
        return recursos;
    }

    public int getIdSala() {
        return idSala;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public int getCupos() {
        return cupos;
    }
}
