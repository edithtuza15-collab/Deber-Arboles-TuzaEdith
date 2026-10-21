## Diagrama de Clases
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
        +esABBValido() bool
    }
    class NodoUnidad {
        -dato: UnidadBiblioteca
        -hijos: List
        -catalogo: CatalogoABB
    }
    class ArbolBiblioteca {
        -raiz: NodoUnidad
        +agregar(codigoPadre, nueva) bool
        +buscar(codigo) NodoUnidad
        +altura() int
        +contarHojas() int
    }
    NodoUnidad --> UnidadBiblioteca
    NodoUnidad --> CatalogoABB
    NodoLibro --> Libro
    CatalogoABB --> NodoLibro
    ArbolBiblioteca --> NodoUnidad
<img width="273" height="344" alt="image" src="https://github.com/user-attachments/assets/78e8da52-20ef-478f-8bc6-486f6e161f07" />
