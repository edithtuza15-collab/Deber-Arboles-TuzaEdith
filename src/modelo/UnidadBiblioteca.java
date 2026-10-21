package modelo;
import enums.TipoUnidad;

public class UnidadBiblioteca {
    private String codigo;
    private String nombre;
    private TipoUnidad tipo;

    public UnidadBiblioteca(String codigo, String nombre, TipoUnidad tipo) {
        if (codigo == null || nombre == null || tipo == null) throw new IllegalArgumentException("Datos nulos");
        this.codigo = codigo.trim().toUpperCase();
        if (this.codigo.isEmpty()) throw new IllegalArgumentException("Codigo vacio");
        this.nombre = nombre.trim();
        this.tipo = tipo;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public TipoUnidad getTipo() { return tipo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
}

