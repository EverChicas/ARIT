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
import softwarearit.Arbol.Funcion.Matrix;
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

    public AccesoMatrix(int linea, int columna, String id, Expresion columnaMatrix, Expresion filaMatrix) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = id;
        this.columnaMatrix = columnaMatrix;
        this.filaMatrix = filaMatrix;
        generarGrafica();
    }

    private void generarGrafica() {
        LinkedList<Nodo> listaAcceso = new LinkedList<>();
        listaAcceso.add(columnaMatrix);
        listaAcceso.add(filaMatrix);
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
                    return accesoCelda(Integer.parseInt(resultColumna.VALOR.get(0).toString()), Integer.parseInt(resultFila.VALOR.get(0).toString()), matrix);
                }
            }
        }
        return null;
    }

    private Valor accesoCelda(int columna, int fila, Simbolo matrix) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");

        int posicion = Matrix.mapeoLexicoGraficoMatriz(columna, fila, Integer.parseInt(matrix.Valor.get(0).toString()));

        if (matrix.Valor.get(posicion) instanceof Valor && ((Expresion) matrix.Valor.get(posicion)).TIPO.Tipo != Tipo.EnumTipo.ERROR) {
            Expresion temp = ((Expresion) matrix.Valor.get(posicion));
            resul = new Valor(new Tipo(temp.TIPO.Tipo), temp.VALOR);
        }

        return resul;
    }

    private Valor accesoColumna(int columna) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        return resul;
    }

    private Valor accesoFila(int fila) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        return resul;
    }

    private boolean esEntero(Expresion e) {
        if (e.TIPO.Tipo == Tipo.EnumTipo.ENTERO) {
            return true;
        } else {
            return false;
        }
    }

}
