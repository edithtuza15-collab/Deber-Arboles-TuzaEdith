# TRAZAS.md - Deber Arboles - Tuza Edith
Cedula ultimos 4 digitos: 1700

1. CODIGOS PERSONALES
Regla PDF: con abcd formar ab, bc y cd. Si uno es 0, se repite o ya existe, sumarle 1.
a=1, b=7, c=0, d=0
ab = 17 -> valido, no existe en SW-E1
bc = 70 -> contiene 0, 70+1 = 71 -> valido
cd = 00 = 0 -> contiene 0, 0+1=1 -> 01 contiene 0 -> 11 -> valido
Codigos finales: 17, 71, 11

2. SECUENCIA DE INSERCION
Base SW-E1: 60, 35, 80, 20, 45, 70, 90, 40, 75
Final (base + personales): 60, 35, 80, 20, 45, 70, 90, 40, 75, 17, 71, 11

3. ABB FINAL EN MERMAID (aristas I/D) - Copia esto en https://mermaid.live
graph TD
    60 -->|I| 35
    35 -->|I| 20
    20 -->|I| 17
    17 -->|I| 11
    35 -->|D| 45
    45 -->|I| 40
    60 -->|D| 80
    80 -->|I| 70
    70 -->|D| 75
    75 -->|I| 71
    80 -->|D| 90

4. RECORRIDOS DEL ABB FINAL
Preorden: 60 35 20 17 11 45 40 80 70 75 71 90
Inorden: 11 17 20 35 40 45 60 70 71 75 80 90
Posorden: 11 17 20 40 45 35 71 75 70 90 80 60

5. TRAZA DEL RECORRIDO PREORDEN
Formato: entra X, VISITA X, null -> regresa, 2 espacios por nivel

entra 60
VISITA 60
  entra 35
  VISITA 35
    entra 20
    VISITA 20
      entra 17
      VISITA 17
        entra 11
        VISITA 11
          null -> regresa
          null -> regresa
        null -> regresa
      null -> regresa
    entra 45
    VISITA 45
      entra 40
      VISITA 40
        null -> regresa
        null -> regresa
      null -> regresa
  entra 80
  VISITA 80
    entra 70
    VISITA 70
      null -> regresa
      entra 75
      VISITA 75
        entra 71
        VISITA 71
          null -> regresa
          null -> regresa
        null -> regresa
    entra 90
    VISITA 90
      null -> regresa
      null -> regresa
Secuencia PREORDEN: 60 35 20 17 11 45 40 80 70 75 71 90

6. TRES ELIMINACIONES 
- Eliminar hoja: Eliminar 11
  Preorden: 60 35 20 17 45 40 80 70 75 71 90

- Eliminar un hijo: Eliminar 20 (tiene solo hijo 17)
  Preorden: 60 35 17 11 45 40 80 70 75 71 90

- Eliminar dos hijos: Eliminar 35 (dos hijos, sucesor inorden es 40)
  Preorden: 60 40 20 17 11 45 80 70 75 71 90
