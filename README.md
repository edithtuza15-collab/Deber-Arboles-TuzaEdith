# Deber-Arboles-TuzaEdith

**Estudiante:** Tuza Edith
### 1. Objetivo del Deber
Implementar en Java un árbol general n-ario y un Árbol Binario de Búsqueda (ABB) mediante POO y recursividad, aplicando inserción, búsqueda, recorridos, eliminación y análisis de estructura, según APE 3.

Caso práctico: La Biblioteca FISEI se organiza en Áreas y cada Área en Estantes. Esta jerarquía es el árbol general. Cada Estante almacena sus libros en un ABB ordenado por código (entero positivo, sin repetidos).

### 2. Estructura Inicial Obligatoria
BIB Biblioteca FISEI
├── SW Software
│ ├── SW-E1 Estante 1 de Software → 60, 35, 80, 20, 45, 70, 90, 40, 75
│ └── SW-E2 Estante 2 de Software → 15, 10, 25
├── TI Tecnologías de la Información
│ └── TI-E1 Estante 1 de TI → 50
└── TEL Telecomunicaciones
Total: 7 unidades, 4 hojas, altura 2 aristas, 13 libros.

### 3. Organización del Proyecto y Clases

| Paquete | Clase | Descripción |
| :--- | :--- | :--- |
| **enums** | `TipoUnidad.java` | BIBLIOTECA, AREA, ESTANTE + `puedeTenerHijo()` |
| | `TipoRecorrido.java` | PREORDEN, INORDEN, POSORDEN |
| **modelo** | `Libro.java` | codigo (sin setter), titulo, autor, anio (1900-2026) con validaciones |
| | `UnidadBiblioteca.java` | codigo (mayúsculas), nombre, tipo |
| **negocio** | `NodoUnidad.java` | dato, lista de hijos, CatalogoABB si es ESTANTE |
| | `NodoLibro.java` | dato, izquierdo, derecho |
| | `ArbolBiblioteca.java` | agregar(), buscar(), altura(), contarHojas(), nivel(), mostrarJerarquia(), contarLibrosTotal() |
| | `CatalogoABB.java` | insertar(), buscar(), contar(), recorrer(), trazaRecorrido(), modificar(), eliminar() + Retos: contarEnRango(), caminoBusqueda(), esABBValido(), desdePreorden() |
| **app** | `Main.java` | Menú interactivo con Scanner |
| | `DatosPrueba.java` | Carga de la estructura inicial |
| | `PruebasBiblioteca.java` | Mínimo 10 casos, muestra X/N OK |

Restricción: Todo recursivo. Prohibido TreeSet, TreeMap, Collections.sort, List.sort.

### 4. Instrucciones de Ejecución

**Pasos:**
1. Clonar el repositorio:
   git clone https://github.com/edithtuza15-collab/Deber-Arboles-TuzaEdith.git
2. Abrir la carpeta en VS Code
3. Esperar que cargue el proyecto Java
4. Ir a `src/app/Main.java` -> Click en `Run` o `F5`
5. También se puede ejecutar por terminal:
   javac -d bin src/enums/*.java src/modelo/*.java src/negocio/*.java src/app/*.java
   java -cp bin app.Main

**Funcionalidades del Menú:**
1. Cargar la estructura inicial
2. Mostrar la jerarquía
3. Agregar y buscar unidades
4. Consultar altura, hojas, nivel y total de libros
5. Insertar, buscar, modificar y eliminar libros de un estante
6. Mostrar los recorridos con su traza (entra X, VISITA X, null -> regresa)
7. Ejecutar los retos
8. Salir

### 5. Casos de Prueba Verificados

La clase PruebasBiblioteca.java valida:
- Biblioteca solo con raíz: altura 0 y 1 hoja
- Biblioteca inicial: altura 2, 4 hojas y 13 libros
- Nivel de BIB, SW, SW-E2 y XYZ: 0, 1, 2 y -1
- Agregar un ESTANTE como hijo de BIBLIOTECA: false
- Recorridos SW-E1 -> preorden: 60 35 20 45 40 80 70 75 90; inorden: 20 35 40 45 60 70 75 80 90; posorden: 20 40 45 35 75 70 90 80 60
- Insertar código repetido: false
- caminoBusqueda(42): [60, 35, 45, 40]. contarEnRango(40, 75): 5
- Insertar 42 y eliminar 20, 70 y 60: preorden final 75 35 45 40 42 80 90
- Buscar, modificar y eliminar código inexistente: null / false
- Libro con datos inválidos: IllegalArgumentException
- esABBValido() con nieto mal ubicado: false

### 6. Evidencia de Ejecución - PruebasBiblioteca

Cuando ejecutes PruebasBiblioteca, debe salir 10/10 OK. Pega tu captura aquí:
<img width="611" height="457" alt="image" src="https://github.com/user-attachments/assets/4ad94eb5-4fdd-4a31-a22a-7640357c6495" />


<img width="597" height="462" alt="image" src="https://github.com/user-attachments/assets/7fd83c43-3e28-4549-9a99-320a31120fef" />



