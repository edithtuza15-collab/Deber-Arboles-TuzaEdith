package negocio;
import modelo.Libro;
import enums.TipoRecorrido;
import java.util.ArrayList;
import java.util.List;

public class CatalogoABB {
    private NodoLibro raiz;

    public NodoLibro getRaiz() { return raiz; }
    public void setRaiz(NodoLibro raiz) { this.raiz = raiz; }

    public boolean insertar(Libro libro) {
        if (libro == null) return false;
        if (buscar(libro.getCodigo()) != null) return false;
        raiz = insertarRec(raiz, libro);
        return true;
    }

    private NodoLibro insertarRec(NodoLibro nodo, Libro libro) {
        if (nodo == null) return new NodoLibro(libro);
        if (libro.getCodigo() < nodo.getDato().getCodigo()) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), libro));
        } else {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), libro));
        }
        return nodo;
    }

    public Libro buscar(int codigo) {
        NodoLibro n = buscarRec(raiz, codigo);
        return n == null ? null : n.getDato();
    }

    private NodoLibro buscarRec(NodoLibro nodo, int codigo) {
        if (nodo == null) return null;
        if (codigo == nodo.getDato().getCodigo()) return nodo;
        if (codigo < nodo.getDato().getCodigo()) return buscarRec(nodo.getIzquierdo(), codigo);
        return buscarRec(nodo.getDerecho(), codigo);
    }

    public int contar() { return contarRec(raiz); }
    private int contarRec(NodoLibro n) {
        if (n == null) return 0;
        return 1 + contarRec(n.getIzquierdo()) + contarRec(n.getDerecho());
    }

    public List<Libro> recorrer(TipoRecorrido tipo) {
        List<Libro> lista = new ArrayList<>();
        recorrerRec(raiz, tipo, lista);
        return lista;
    }

    private void recorrerRec(NodoLibro n, TipoRecorrido tipo, List<Libro> lista) {
        if (n == null) return;
        if (tipo == TipoRecorrido.PREORDEN) {
            lista.add(n.getDato());
            recorrerRec(n.getIzquierdo(), tipo, lista);
            recorrerRec(n.getDerecho(), tipo, lista);
        } else if (tipo == TipoRecorrido.INORDEN) {
            recorrerRec(n.getIzquierdo(), tipo, lista);
            lista.add(n.getDato());
            recorrerRec(n.getDerecho(), tipo, lista);
        } else {
            recorrerRec(n.getIzquierdo(), tipo, lista);
            recorrerRec(n.getDerecho(), tipo, lista);
            lista.add(n.getDato());
        }
    }

    public String trazaRecorrido(TipoRecorrido tipo) {
        StringBuilder sb = new StringBuilder();
        List<Integer> secuencia = new ArrayList<>();
        trazaRec(raiz, tipo, 0, sb, secuencia);
        sb.append("Secuencia ").append(tipo).append(": ");
        for (int i = 0; i < secuencia.size(); i++) {
            sb.append(secuencia.get(i));
            if (i < secuencia.size() - 1) sb.append(" ");
        }
        return sb.toString();
    }

    private void trazaRec(NodoLibro n, TipoRecorrido tipo, int nivel, StringBuilder sb, List<Integer> sec) {
        String indent = "  ".repeat(nivel);
        if (n == null) {
            sb.append(indent).append("null -> regresa\n");
            return;
        }
        sb.append(indent).append("entra ").append(n.getDato().getCodigo()).append("\n");
        if (tipo == TipoRecorrido.PREORDEN) {
            sb.append(indent).append("VISITA ").append(n.getDato().getCodigo()).append("\n");
            sec.add(n.getDato().getCodigo());
            trazaRec(n.getIzquierdo(), tipo, nivel + 1, sb, sec);
            trazaRec(n.getDerecho(), tipo, nivel + 1, sb, sec);
        } else if (tipo == TipoRecorrido.INORDEN) {
            trazaRec(n.getIzquierdo(), tipo, nivel + 1, sb, sec);
            sb.append(indent).append("VISITA ").append(n.getDato().getCodigo()).append("\n");
            sec.add(n.getDato().getCodigo());
            trazaRec(n.getDerecho(), tipo, nivel + 1, sb, sec);
        } else {
            trazaRec(n.getIzquierdo(), tipo, nivel + 1, sb, sec);
            trazaRec(n.getDerecho(), tipo, nivel + 1, sb, sec);
            sb.append(indent).append("VISITA ").append(n.getDato().getCodigo()).append("\n");
            sec.add(n.getDato().getCodigo());
        }
    }

    public boolean modificar(int codigo, String titulo, String autor, int anio) {
        NodoLibro n = buscarRec(raiz, codigo);
        if (n == null) return false;
        try {
            Libro temp = new Libro(codigo, titulo, autor, anio);
            n.getDato().setTitulo(temp.getTitulo());
            n.getDato().setAutor(temp.getAutor());
            n.getDato().setAnio(temp.getAnio());
            return true;
        } catch (Exception e) { return false; }
    }

    public boolean eliminar(int codigo) {
        if (buscar(codigo) == null) return false;
        raiz = eliminarRec(raiz, codigo);
        return true;
    }

    private NodoLibro eliminarRec(NodoLibro n, int codigo) {
        if (n == null) return null;
        if (codigo < n.getDato().getCodigo()) {
            n.setIzquierdo(eliminarRec(n.getIzquierdo(), codigo));
        } else if (codigo > n.getDato().getCodigo()) {
            n.setDerecho(eliminarRec(n.getDerecho(), codigo));
        } else {
            if (n.getIzquierdo() == null) return n.getDerecho();
            if (n.getDerecho() == null) return n.getIzquierdo();
            NodoLibro sucesor = minimo(n.getDerecho());
            n.setDato(sucesor.getDato());
            n.setDerecho(eliminarRec(n.getDerecho(), sucesor.getDato().getCodigo()));
        }
        return n;
    }

    private NodoLibro minimo(NodoLibro n) {
        while (n.getIzquierdo() != null) n = n.getIzquierdo();
        return n;
    }

    // RETOS
    public int contarEnRango(int a, int b) { return contarRangoRec(raiz, a, b); }
    private int contarRangoRec(NodoLibro n, int a, int b) {
        if (n == null) return 0;
        int cod = n.getDato().getCodigo();
        if (cod < a) return contarRangoRec(n.getDerecho(), a, b);
        if (cod > b) return contarRangoRec(n.getIzquierdo(), a, b);
        return 1 + contarRangoRec(n.getIzquierdo(), a, b) + contarRangoRec(n.getDerecho(), a, b);
    }

    public List<Integer> caminoBusqueda(int codigo) {
        List<Integer> camino = new ArrayList<>();
        caminoRec(raiz, codigo, camino);
        return camino;
    }
    private boolean caminoRec(NodoLibro n, int codigo, List<Integer> camino) {
        if (n == null) return false;
        camino.add(n.getDato().getCodigo());
        if (codigo == n.getDato().getCodigo()) return true;
        if (codigo < n.getDato().getCodigo()) {
            if (caminoRec(n.getIzquierdo(), codigo, camino)) return true;
        } else {
            if (caminoRec(n.getDerecho(), codigo, camino)) return true;
        }
        camino.remove(camino.size() - 1);
        return false;
    }

    public boolean esABBValido() { return validoRec(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE); }
    private boolean validoRec(NodoLibro n, int min, int max) {
        if (n == null) return true;
        int c = n.getDato().getCodigo();
        if (c <= min || c >= max) return false;
        return validoRec(n.getIzquierdo(), min, c) && validoRec(n.getDerecho(), c, max);
    }

    public static CatalogoABB desdePreorden(int[] preorden) {
        CatalogoABB abb = new CatalogoABB();
        abb.raiz = construirPreorden(preorden, new int[]{0}, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return abb;
    }
    private static NodoLibro construirPreorden(int[] pre, int[] idx, int min, int max) {
        if (idx[0] >= pre.length) return null;
        int val = pre[idx[0]];
        if (val <= min || val >= max) return null;
        idx[0]++;
        NodoLibro nodo = new NodoLibro(new Libro(val, "T"+val, "A"+val, 2000));
        nodo.setIzquierdo(construirPreorden(pre, idx, min, val));
        nodo.setDerecho(construirPreorden(pre, idx, val, max));
        return nodo;
    }
}

