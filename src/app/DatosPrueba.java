package app;
import negocio.ArbolBiblioteca;
import modelo.UnidadBiblioteca;
import enums.TipoUnidad;
import modelo.Libro;

public class DatosPrueba {
    public static ArbolBiblioteca crearBibliotecaInicial() {
        ArbolBiblioteca arbol = new ArbolBiblioteca();
        arbol.agregar(null, new UnidadBiblioteca("BIB", "Biblioteca FISEI", TipoUnidad.BIBLIOTECA));
        arbol.agregar("BIB", new UnidadBiblioteca("SW", "Software", TipoUnidad.AREA));
        arbol.agregar("BIB", new UnidadBiblioteca("TI", "Tecnologias de la Informacion", TipoUnidad.AREA));
        arbol.agregar("BIB", new UnidadBiblioteca("TEL", "Telecomunicaciones", TipoUnidad.AREA));
        arbol.agregar("SW", new UnidadBiblioteca("SW-E1", "Estante 1 de Software", TipoUnidad.ESTANTE));
        arbol.agregar("SW", new UnidadBiblioteca("SW-E2", "Estante 2 de Software", TipoUnidad.ESTANTE));
        arbol.agregar("TI", new UnidadBiblioteca("TI-E1", "Estante 1 de TI", TipoUnidad.ESTANTE));

        int[] librosE1 = {60, 35, 80, 20, 45, 70, 90, 40, 75};
        for (int c : librosE1) {
            arbol.buscar("SW-E1").getCatalogo().insertar(new Libro(c, "Libro "+c, "Autor "+c, 2020));
        }
        int[] librosE2 = {15, 10, 25};
        for (int c : librosE2) {
            arbol.buscar("SW-E2").getCatalogo().insertar(new Libro(c, "Libro "+c, "Autor "+c, 2021));
        }
        arbol.buscar("TI-E1").getCatalogo().insertar(new Libro(50, "Libro 50", "Autor 50", 2022));
        return arbol;
    }
}

