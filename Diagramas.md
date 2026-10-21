classDiagram
    class TipoUnidad {
        <<enumeration>>
        BIBLIOTECA
        AREA
        ESTANTE
        +puedeTenerHijo(hijo) bool
    }

    class TipoRecorrido {
        <<enumeration>>
        PREORDEN
        INORDEN
        POSORDEN
    }

    class Libro {
        -codigo: int
        -titulo: String
        -autor: String
        -anio: int
        +Libro(codigo, titulo, autor, anio)
        +getCodigo() int
    }

    class UnidadBiblioteca {
        -codigo: String
        -nombre: String
        -tipo: TipoUnidad
    }

    class NodoLibro {
        -dato: Libro
        -izquierdo: NodoLibro
        -derecho: NodoLibro
    }

    class CatalogoABB {
        -raiz: NodoLibro
        +insertar(libro) bool
        +buscar(codigo) Libro
        +eliminar(codigo) bool
        +recorrer(tipo) List
        +contarEnRango(a,b) int
        +caminoBusqueda(codigo) List
        +esABBValido() bool
    }

    class NodoUnidad {
        -dato: UnidadBiblioteca
        -hijos: List~NodoUnidad~
        -catalogo: CatalogoABB
    }

    class ArbolBiblioteca {
        -raiz: NodoUnidad
        +agregar(codigoPadre, nueva) bool
        +buscar(codigo) NodoUnidad
        +altura() int
        +contarHojas() int
        +nivel(codigo) int
        +mostrarJerarquia() String
        +contarLibrosTotal() int
    }

    NodoUnidad --> UnidadBiblioteca : tiene
    NodoUnidad --> CatalogoABB : si es ESTANTE
    NodoLibro --> Libro : tiene
    CatalogoABB --> NodoLibro : raiz
    ArbolBiblioteca --> NodoUnidad : raiz
    UnidadBiblioteca --> TipoUnidad
    CatalogoABB ..> TipoRecorrido
<img width="273" height="344" alt="image" src="https://github.com/user-attachments/assets/78e8da52-20ef-478f-8bc6-486f6e161f07" />
