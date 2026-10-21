# Deber-Arboles-TuzaEdith
# Deber-Árboles - Biblioteca FISEI - Tuza Edith

**Asignatura:** Estructura de Datos - Tercero Software
### 1. Descripción del Problema
Implementar el catálogo de la Biblioteca FISEI con dos estructuras:
1.  **Árbol General n-ario:** Jerarquía BIBLIOTECA -> AREA -> ESTANTE. Estructura inicial: BIB con 7 unidades, 4 hojas, altura 2 y 13 libros.
2.  **ABB:** Cada ESTANTE guarda sus libros en un ABB ordenado por codigo (sin repetidos).
Todo debe ser recursivo. Prohibido TreeSet, TreeMap, Collections.sort.

### 2. Estructura del Proyecto
src/
├── enums/ TipoUnidad.java, TipoRecorrido.java
├── modelo/ Libro.java, UnidadBiblioteca.java
├── negocio/ NodoUnidad.java, NodoLibro.java, ArbolBiblioteca.java, CatalogoABB.java
└── app/ Main.java, DatosPrueba.java, PruebasBiblioteca.java

### 3. Instrucciones de Ejecución
1. Clonar: git clone https://github.com/edithtuza15-collab/Deber-Arboles-TuzaEdith.git
2. Abrir en VS Code
3. Ejecutar src/app/Main.java
4. Menú: Cargar estructura, Mostrar jerarquía, Agregar/buscar unidades, altura/hojas/nivel/total, Insertar/buscar/modificar/eliminar libros, Recorridos con traza, Retos.

### 4. Casos de Prueba (PruebasBiblioteca)
- Biblioteca solo raíz: altura 0, 1 hoja
- Biblioteca inicial: altura 2, 4 hojas, 13 libros
- Nivel BIB, SW, SW-E2, XYZ: 0, 1, 2, -1
- Agregar ESTANTE hijo de BIBLIOTECA: false
- Recorridos SW-E1 -> preorden: 60 35 20 45 40 80 70 75 90 / inorden: 20 35 40 45 60 70 75 80 90 / posorden: 20 40 45 35 75 70 90 80 60
- Código repetido: false
- caminoBusqueda(42): [60,35,45,40] / contarEnRango(40,75): 5
- Insertar 42 y eliminar 20,70,60: preorden final 75 35 45 40 42 80 90

### 5. Evidencia PruebasBiblioteca
Pega aquí tu captura cuando ejecutes PruebasBiblioteca.java. Debe decir 10/10 OK.
Ejemplo:
10/10 pruebas OK
[OK] altura solo raiz
[OK] biblioteca inicial
<img width="629" height="442" alt="image" src="https://github.com/user-attachments/assets/0ee96de5-f793-4c75-b007-925508d8d498" />

### 6. Estudiante
Edith Tuza - FISEI - Ingeniería de Software
