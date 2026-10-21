package negocio;
import modelo.UnidadBiblioteca;

public class ArbolBiblioteca {
    private NodoUnidad raiz;

    public NodoUnidad getRaiz() { return raiz; }

    public boolean agregar(String codigoPadre, UnidadBiblioteca nueva) {
        if (nueva == null) return false;
        if (buscar(nueva.getCodigo()) != null) return false;

        if (raiz == null) {
            if (nueva.getTipo() != enums.TipoUnidad.BIBLIOTECA) return false;
            raiz = new NodoUnidad(nueva);
            return true;
        }

        NodoUnidad padre = buscar(codigoPadre);
        if (padre == null) return false;
        if (!padre.getDato().getTipo().puedeTenerHijo(nueva.getTipo())) return false;

        padre.getHijos().add(new NodoUnidad(nueva));
        return true;
    }

    public NodoUnidad buscar(String codigo) {
        if (codigo == null || raiz == null) return null;
        return buscarRec(raiz, codigo.trim().toUpperCase());
    }

    private NodoUnidad buscarRec(NodoUnidad nodo, String codigo) {
        if (nodo.getDato().getCodigo().equalsIgnoreCase(codigo)) return nodo;
        for (NodoUnidad hijo : nodo.getHijos()) {
            NodoUnidad r = buscarRec(hijo, codigo);
            if (r != null) return r;
        }
        return null;
    }

    public int altura() { return alturaRec(raiz); }
    private int alturaRec(NodoUnidad nodo) {
        if (nodo == null) return -1;
        if (nodo.getHijos().isEmpty()) return 0;
        int max = -1;
        for (NodoUnidad h : nodo.getHijos()) {
            int a = alturaRec(h);
            if (a > max) max = a;
        }
        return max + 1;
    }

    public int contarHojas() { return hojasRec(raiz); }
    private int hojasRec(NodoUnidad nodo) {
        if (nodo == null) return 0;
        if (nodo.getHijos().isEmpty()) return 1;
        int c = 0;
        for (NodoUnidad h : nodo.getHijos()) c += hojasRec(h);
        return c;
    }

    public int nivel(String codigo) { return nivelRec(raiz, codigo.toUpperCase(), 0); }
    private int nivelRec(NodoUnidad nodo, String codigo, int nivel) {
        if (nodo == null) return -1;
        if (nodo.getDato().getCodigo().equalsIgnoreCase(codigo)) return nivel;
        for (NodoUnidad h : nodo.getHijos()) {
            int r = nivelRec(h, codigo, nivel + 1);
            if (r != -1) return r;
        }
        return -1;
    }

    public String mostrarJerarquia() {
        StringBuilder sb = new StringBuilder();
        jerarquiaRec(raiz, 0, sb);
        return sb.toString();
    }
    private void jerarquiaRec(NodoUnidad nodo, int nivel, StringBuilder sb) {
        if (nodo == null) return;
        sb.append("  ".repeat(nivel)).append(nodo.getDato().getCodigo()).append(" ").append(nodo.getDato().getNombre()).append("\n");
        for (NodoUnidad h : nodo.getHijos()) jerarquiaRec(h, nivel + 1, sb);
    }

    public int contarLibrosTotal() { return totalLibrosRec(raiz); }
    private int totalLibrosRec(NodoUnidad nodo) {
        if (nodo == null) return 0;
        int total = 0;
        if (nodo.getCatalogo() != null) total += nodo.getCatalogo().contar();
        for (NodoUnidad h : nodo.getHijos()) total += totalLibrosRec(h);
        return total;
    }
}

