package app;
import negocio.ArbolBiblioteca;
import negocio.CatalogoABB;
import negocio.NodoLibro;
import modelo.Libro;
import modelo.UnidadBiblioteca;
import enums.TipoUnidad;
import enums.TipoRecorrido;
import java.util.List;

public class PruebasBiblioteca {
    static int ok = 0, total = 0;

    public static void ejecutar() {
        ok = 0; total = 0;
        System.out.println("\n=== PRUEBAS BIBLIOTECA ===");

        // 1. Biblioteca solo con raiz: altura 0 y 1 hoja
        ArbolBiblioteca soloRaiz = new ArbolBiblioteca();
        soloRaiz.agregar(null, new UnidadBiblioteca("BIB", "Root", TipoUnidad.BIBLIOTECA));
        test("Altura solo raiz =0", soloRaiz.altura() == 0);
        test("Hojas solo raiz =1", soloRaiz.contarHojas() == 1);

        // 2. Biblioteca inicial: altura 2, 4 hojas y 13 libros
        ArbolBiblioteca bib = DatosPrueba.crearBibliotecaInicial();
        test("Altura inicial 2", bib.altura() == 2);
        test("Hojas inicial 4", bib.contarHojas() == 4);
        test("Total libros 13", bib.contarLibrosTotal() == 13);

        // 3. Nivel de BIB, sw, SW-E2 y XYZ: 0,1,2 y -1
        test("Nivel BIB 0", bib.nivel("BIB") == 0);
        test("Nivel sw 1", bib.nivel("sw") == 1);
        test("Nivel SW-E2 2", bib.nivel("SW-E2") == 2);
        test("Nivel XYZ -1", bib.nivel("XYZ") == -1);

        // 4. Agregar ESTANTE como hijo de BIBLIOTECA: false
        boolean agregoMal = bib.agregar("BIB", new UnidadBiblioteca("MAL", "Malo", TipoUnidad.ESTANTE));
        test("Agregar ESTANTE hijo de BIB false", !agregoMal);

        // 5. Recorridos SW-E1
        CatalogoABB cat = bib.buscar("SW-E1").getCatalogo();
        List<Libro> pre = cat.recorrer(TipoRecorrido.PREORDEN);
        List<Libro> in = cat.recorrer(TipoRecorrido.INORDEN);
        List<Libro> pos = cat.recorrer(TipoRecorrido.POSORDEN);
        test("Preorden SW-E1", toCodigos(pre).equals("60 35 20 45 40 80 70 75 90"));
        test("Inorden SW-E1", toCodigos(in).equals("20 35 40 45 60 70 75 80 90"));
        test("Posorden SW-E1", toCodigos(pos).equals("20 40 45 35 75 70 90 80 60"));

        // 6. Insertar codigo repetido false
        test("Insertar repetido false", !cat.insertar(new Libro(60, "Dup", "A", 2020)));

        // 7. caminoBusqueda(42) y contarEnRango
        cat.insertar(new Libro(42, "L42", "A", 2020));
        test("caminoBusqueda(42) [60,35,45,40,42]", cat.caminoBusqueda(42).toString().equals("[60, 35, 45, 40, 42]"));
        test("contarEnRango(40,75)=5", cat.contarEnRango(40, 75) == 5);
        cat.eliminar(42); // lo quitamos para siguiente prueba

        // 8. Insertar 42 y eliminar 20,70,60 preorden final 75 35 45 40 42 80 90
        cat.insertar(new Libro(42, "L42", "A", 2020));
        cat.eliminar(20);
        cat.eliminar(70);
        cat.eliminar(60);
        test("Preorden tras eliminar 20,70,60", toCodigos(cat.recorrer(TipoRecorrido.PREORDEN)).equals("75 35 45 40 42 80 90"));

        // 9. Buscar, modificar y eliminar inexistente null/false
        test("Buscar inexistente null", cat.buscar(9999) == null);
        test("Modificar inexistente false", !cat.modificar(9999, "X", "Y", 2000));
        test("Eliminar inexistente false", !cat.eliminar(9999));

        // 10. Libro invalido IllegalArgumentException
        boolean lanzo = false;
        try { new Libro(0, "", "", 1800); } catch (IllegalArgumentException e) { lanzo = true; }
        test("Libro invalido IllegalArgumentException", lanzo);

        // 11. esABBValido con nieto mal ubicado false
        CatalogoABB mal = new CatalogoABB();
        NodoLibro r = new NodoLibro(new Libro(50, "R", "A", 2020));
        NodoLibro izq = new NodoLibro(new Libro(30, "I", "A", 2020));
        izq.setDerecho(new NodoLibro(new Libro(60, "MALO", "A", 2020))); // 60 en subarbol izq -> invalido
        r.setIzquierdo(izq);
        mal.setRaiz(r);
        test("esABBValido mal ubicado false", !mal.esABBValido());

        System.out.println("\nResultado: " + ok + "/" + total + " pruebas OK");
    }

    static void test(String nombre, boolean cond) {
        total++;
        if (cond) { ok++; System.out.println("✓ " + nombre); }
        else System.out.println("X " + nombre + " - FALLO");
    }

    static String toCodigos(List<Libro> lista) {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<lista.size();i++) {
            sb.append(lista.get(i).getCodigo());
            if (i < lista.size()-1) sb.append(" ");
        }
        return sb.toString();
    }
}

