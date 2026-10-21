package modelo;

public class Libro {
    private final int codigo;
    private String titulo;
    private String autor;
    private int anio;

    public Libro(int codigo, String titulo, String autor, int anio) {
        if (codigo <= 0) throw new IllegalArgumentException("Codigo debe ser > 0");
        validar(titulo, autor, anio);
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    private void validar(String titulo, String autor, int anio) {
        if (titulo == null || titulo.trim().isEmpty()) throw new IllegalArgumentException("Titulo invalido");
        if (autor == null || autor.trim().isEmpty()) throw new IllegalArgumentException("Autor invalido");
        if (anio < 1900 || anio > 2026) throw new IllegalArgumentException("Anio debe estar entre 1900 y 2026");
    }

    public int getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnio() { return anio; }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) throw new IllegalArgumentException("Titulo invalido");
        this.titulo = titulo;
    }
    public void setAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) throw new IllegalArgumentException("Autor invalido");
        this.autor = autor;
    }
    public void setAnio(int anio) {
        if (anio < 1900 || anio > 2026) throw new IllegalArgumentException("Anio invalido");
        this.anio = anio;
    }

    @Override
    public String toString() {
        return codigo + " - " + titulo;
    }
}

