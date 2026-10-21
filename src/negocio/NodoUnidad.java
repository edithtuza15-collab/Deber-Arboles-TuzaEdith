package negocio;
import modelo.UnidadBiblioteca;
import java.util.ArrayList;
import java.util.List;

public class NodoUnidad {
    private UnidadBiblioteca dato;
    private List<NodoUnidad> hijos;
    private CatalogoABB catalogo;

    public NodoUnidad(UnidadBiblioteca dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
        if (dato.getTipo() == enums.TipoUnidad.ESTANTE) {
            this.catalogo = new CatalogoABB();
        }
    }

    public UnidadBiblioteca getDato() { return dato; }
    public List<NodoUnidad> getHijos() { return hijos; }
    public CatalogoABB getCatalogo() { return catalogo; }
} 

