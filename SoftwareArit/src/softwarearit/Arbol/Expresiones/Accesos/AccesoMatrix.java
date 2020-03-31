/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Accesos;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Simbolo;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Funcion.C;
import softwarearit.Arbol.Funcion.Matrix;
import static softwarearit.Arbol.Funcion.Matrix.mapeoLexicoGraficoMatriz;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class AccesoMatrix extends Expresion {

    String identificador;
    Expresion columnaMatrix;
    Expresion filaMatrix;

    public AccesoMatrix(int linea, int columna, String id, Expresion filaMatrix, Expresion columnaMatrix) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = id;
        this.filaMatrix = filaMatrix;
        this.columnaMatrix = columnaMatrix;
        generarGrafica();
    }

    private void generarGrafica() {
        LinkedList<Nodo> listaAcceso = new LinkedList<>();
        if (columnaMatrix != null && filaMatrix != null) {
            listaAcceso.add(columnaMatrix);
            listaAcceso.add(filaMatrix);
        } else if (columnaMatrix == null && filaMatrix != null) {
            listaAcceso.add(filaMatrix);
        } else if (filaMatrix == null && columnaMatrix != null) {
            listaAcceso.add(columnaMatrix);
        }
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos(identificador, this, listaAcceso);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Expresion resultColumna;
        Expresion resultFila;
        Simbolo matrix = e.buscar(identificador);
        if (matrix.Tipo.Tipo != Tipo.EnumTipo.MATRIZ) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error variable no es una matrix", LINEA, COLUMNA));
            return new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        } else {
            if (columnaMatrix != null && filaMatrix != null) {
                resultColumna = columnaMatrix.getValor(e);
                resultFila = filaMatrix.getValor(e);
                if (esEntero(resultColumna) && esEntero(resultFila)) {
                    return accesoCelda(Integer.parseInt(resultFila.VALOR.get(0).toString()), Integer.parseInt(resultColumna.VALOR.get(0).toString()), matrix);
                }
            } else if (columnaMatrix == null && filaMatrix != null) {
                resultFila = filaMatrix.getValor(e);
                if (esEntero(resultFila)) {
                    return accesoFila(e, matrix, Integer.parseInt(resultFila.VALOR.get(0).toString()));
                }
            } else if (filaMatrix == null && columnaMatrix != null) {
                resultColumna = columnaMatrix.getValor(e);
                if (esEntero(resultColumna)) {
                    return accesoColumna(e, matrix, Integer.parseInt(resultColumna.VALOR.get(0).toString()));
                }
            }
        }
        return new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
    }

    private Expresion accesoCelda(int fila, int columna, Simbolo matrix) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        if (fila == 0 || columna == 0) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Indices no pueden ser 0 en matriz", LINEA, COLUMNA));
            return resul;
        } else if (fila * columna >= matrix.Valor.size() - 1) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error indice fuera de limite", LINEA, COLUMNA));
            return resul;
        } else {
            int posicion = Matrix.mapeoLexicoGraficoMatriz(fila, columna, Integer.parseInt(matrix.Valor.get(0).toString()));

            Object temp = ((Expresion) matrix.Valor.get(posicion));
            if (temp instanceof Valor && ((Expresion) temp).TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                resul = new Valor(new Tipo(((Expresion) temp).TIPO.Tipo), ((Expresion) temp).VALOR);
            }

            return resul;
        }

    }

    private Expresion accesoColumna(Entorno e, Simbolo matrix, int columna) {
        Expresion resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");

        if (columna == 0) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Indices no pueden ser 0 en matriz", LINEA, COLUMNA));
            return resul;
        } else if (columna > Integer.parseInt(matrix.Valor.get(1).toString())) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error indice fuera de limite", LINEA, COLUMNA));
            return resul;
        } else {
            LinkedList<Nodo> valores = new LinkedList<>();
            Expresion valor;
            for (int i = 1; i <= Integer.parseInt(matrix.Valor.get(0).toString()); i++) {
                valor = ((Expresion) matrix.Valor.get(mapeoLexicoGraficoMatriz(i, columna, Integer.parseInt(matrix.Valor.get(0).toString())))).getValor(e);
                valores.add(valor);
            }
            resul = new C(this.LINEA, this.COLUMNA, valores).getValor(e);
            return resul;
        }
    }

    private Expresion accesoFila(Entorno e, Simbolo matrix, int fila) {
        Expresion resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");

        if (fila == 0) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Indices no pueden ser 0 en matriz", LINEA, COLUMNA));
            return resul;
        } else if (fila > Integer.parseInt(matrix.Valor.get(0).toString())) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error indice fuera de limite", LINEA, COLUMNA));
            return resul;
        } else {
            LinkedList<Nodo> valoresFila = new LinkedList<>();
            Expresion valor;
            for (int i = 1; i <= Integer.parseInt(matrix.Valor.get(1).toString()); i++) {
                valor = ((Expresion) matrix.Valor.get(mapeoLexicoGraficoMatriz(fila, i, Integer.parseInt(matrix.Valor.get(0).toString())))).getValor(e);
                valoresFila.add(valor);
            }
            resul = new C(this.LINEA, this.COLUMNA, valoresFila).getValor(e);
            return resul;
        }
    }

    private boolean esEntero(Expresion e) {
        if (e.TIPO.Tipo == Tipo.EnumTipo.ENTERO) {
            return true;
        } else {
            return false;
        }
    }

}
