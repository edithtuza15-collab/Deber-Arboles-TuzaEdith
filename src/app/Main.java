package app;
import negocio.ArbolBiblioteca;
import negocio.CatalogoABB;
import negocio.NodoUnidad;
import modelo.Libro;
import modelo.UnidadBiblioteca;
import enums.TipoUnidad;
import enums.TipoRecorrido;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArbolBiblioteca arbol = null;

    public static void main(String[] args) {
        int op;
        do {
            System.out.println("\n=== BIBLIOTECA FISEI ===");
            System.out.println("1. Cargar estructura inicial");
            System.out.println("2. Mostrar jerarquia");
            System.out.println("3. Agregar unidad");
            System.out.println("4. Buscar unidad");
            System.out.println("5. Altura, hojas, nivel, total libros");
            System.out.println("6. Gestionar libros de un estante");
            System.out.println("7. Retos (rango, camino, esValido, desdePreorden)");
            System.out.println("8. Ejecutar PruebasBiblioteca");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            op = leerInt();

            switch (op) {
                case 1 -> { arbol = DatosPrueba.crearBibliotecaInicial(); System.out.println("Biblioteca inicial cargada: altura 2, 4 hojas, 13 libros"); }
                case 2 -> { check(); System.out.println(arbol.mostrarJerarquia()); }
                case 3 -> agregarUnidad();
                case 4 -> buscarUnidad();
                case 5 -> consultas();
                case 6 -> gestionLibros();
                case 7 -> retos();
                case 8 -> PruebasBiblioteca.ejecutar();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida");
            }
        } while (op != 0);
    }

    static void check() {
        if (arbol == null) {
            System.out.println("Primero carga la estructura inicial (opcion 1)");
            arbol = DatosPrueba.crearBibliotecaInicial();
        }
    }

    static void agregarUnidad() {
        check();
        System.out.print("Codigo padre (BIB, SW, etc): "); String padre = sc.nextLine();
        System.out.print("Codigo nuevo (ej SW-E3): "); String cod = sc.nextLine();
        System.out.print("Nombre: "); String nom = sc.nextLine();
        System.out.print("Tipo (BIBLIOTECA, AREA, ESTANTE): "); String tipoS = sc.nextLine().toUpperCase();
        try {
            TipoUnidad tipo = TipoUnidad.valueOf(tipoS);
            boolean ok = arbol.agregar(padre, new UnidadBiblioteca(cod, nom, tipo));
            System.out.println(ok ? "Agregado OK" : "No se pudo agregar (verifica jerarquia BIB->AREA->ESTANTE y codigo repetido)");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void buscarUnidad() {
        check();
        System.out.print("Codigo a buscar: "); String c = sc.nextLine();
        NodoUnidad n = arbol.buscar(c);
        System.out.println(n == null ? "No existe" : "Encontrado: " + n.getDato().getCodigo() + " " + n.getDato().getNombre() + " nivel=" + arbol.nivel(c));
    }

    static void consultas() {
        check();
        System.out.println("Altura: " + arbol.altura());
        System.out.println("Hojas: " + arbol.contarHojas());
        System.out.println("Total libros: " + arbol.contarLibrosTotal());
        System.out.print("Codigo para nivel: "); String c = sc.nextLine();
        System.out.println("Nivel de " + c + ": " + arbol.nivel(c));
    }

    static void gestionLibros() {
        check();
        System.out.print("Codigo del estante (ej SW-E1): "); String codEst = sc.nextLine();
        NodoUnidad est = arbol.buscar(codEst);
        if (est == null || est.getCatalogo() == null) { System.out.println("No es un estante"); return; }
        CatalogoABB cat = est.getCatalogo();
        System.out.println("Estante " + codEst + " tiene " + cat.contar() + " libros");
        System.out.println("a) Insertar  b) Buscar  c) Modificar  d) Eliminar  e) Recorridos con traza");
        String op = sc.nextLine();
        switch (op.toLowerCase()) {
            case "a" -> { System.out.print("Codigo titulo autor anio separados por coma: "); 
                try { String[] p = sc.nextLine().split(","); int cod = Integer.parseInt(p[0].trim()); boolean ok = cat.insertar(new Libro(cod, p[1].trim(), p[2].trim(), Integer.parseInt(p[3].trim()))); System.out.println(ok ? "Insertado" : "Codigo repetido"); } catch (Exception ex) { System.out.println("Error: " + ex.getMessage()); } }
            case "b" -> { System.out.print("Codigo: "); int cod = leerInt(); Libro l = cat.buscar(cod); System.out.println(l == null ? "null" : l.getTitulo()); }
            case "c" -> { System.out.print("Codigo a modificar: "); int cod = leerInt(); System.out.print("Nuevo titulo, autor, anio (coma): "); String[] p = sc.nextLine().split(","); boolean ok = cat.modificar(cod, p[0].trim(), p[1].trim(), Integer.parseInt(p[2].trim())); System.out.println(ok ? "Modificado" : "false"); }
            case "d" -> { System.out.print("Codigo a eliminar: "); int cod = leerInt(); boolean ok = cat.eliminar(cod); System.out.println(ok ? "Eliminado" : "false"); }
            case "e" -> {
                for (TipoRecorrido t : TipoRecorrido.values()) {
                    System.out.println("\n--- " + t + " ---");
                    System.out.println(cat.trazaRecorrido(t));
                }
            }
        }
    }

    static void retos() {
        check();
        System.out.print("Codigo estante para retos (SW-E1): "); String cod = sc.nextLine();
        NodoUnidad n = arbol.buscar(cod);
        if (n == null || n.getCatalogo() == null) { System.out.println("No es estante"); return; }
        CatalogoABB cat = n.getCatalogo();
        System.out.println("contarEnRango(40,75): " + cat.contarEnRango(40, 75) + " esperado 5");
        System.out.println("caminoBusqueda(42): " + cat.caminoBusqueda(42) + " esperado [60, 35, 45, 40] si existe, vacio si no");
        System.out.println("esABBValido: " + cat.esABBValido());
        System.out.print("Probar desdePreorden? ingresa numeros coma ej 60,35,20: ");
        String line = sc.nextLine();
        if (!line.trim().isEmpty()) {
            String[] parts = line.split(",");
            int[] pre = new int[parts.length];
            for (int i=0;i<parts.length;i++) pre[i]=Integer.parseInt(parts[i].trim());
            CatalogoABB nuevo = CatalogoABB.desdePreorden(pre);
            System.out.println("Nuevo ABB inorden: " + nuevo.recorrer(TipoRecorrido.INORDEN));
        }
    }

    static int leerInt() {
        try { int v = Integer.parseInt(sc.nextLine().trim()); return v; } catch (Exception e) { return -1; }
    }
}

