/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import java.util.ArrayList;
import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Simbolo;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Funcion.Matrix;
import static softwarearit.Arbol.Funcion.Matrix.mapeoLexicoGraficoMatriz;
import softwarearit.Arbol.Herramientas.Casteo;
import softwarearit.Arbol.Herramientas.TratamientoTipos;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class AsignacionMatrix extends Instruccion {

    String identificador;
    Expresion columnaMatrix;
    Expresion filaMatrix;
    Expresion nuevoValor;

    public AsignacionMatrix(int linea, int columna, String id, Expresion filaMatrix, Expresion columnaMatrix, Expresion nuevoValor) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = id;
        this.filaMatrix = filaMatrix;
        this.columnaMatrix = columnaMatrix;
        this.nuevoValor = nuevoValor;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
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

        Valor valorId = new Valor(new Tipo(Tipo.EnumTipo.STRING), this.identificador);
        valorId.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos(this.identificador, valorId, listaAcceso);

        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("AsignacionEstructura", this, valorId, this.nuevoValor);

    }

    @Override
    public Object Ejecutar(Entorno e) {
        Expresion resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");

        Expresion resultColumna;
        Expresion resultFila;
        Expresion resulNuevoValor = this.nuevoValor.getValor(e);
        Simbolo matrix = e.buscar(identificador);

        if (resulNuevoValor.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error con valor a insertar en matrix", LINEA, COLUMNA));
        } else if (matrix.Tipo.Tipo != Tipo.EnumTipo.MATRIZ) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error variable no es una matrix", LINEA, COLUMNA));
        } else {
            /**
             * Se hace unas validaciones y ahora es de esperar que se modifique
             * los valores en la matrix
             */
            if (columnaMatrix != null && filaMatrix != null) {
                resultColumna = columnaMatrix.getValor(e);
                resultFila = filaMatrix.getValor(e);
                if (esEntero(resultColumna) && esEntero(resultFila)) {
                    resul = accesoCelda(Integer.parseInt(resultFila.VALOR.get(0).toString()), Integer.parseInt(resultColumna.VALOR.get(0).toString()), matrix, resulNuevoValor);
                    if (resul.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                        e.insertar(matrix.Id, new Simbolo(resul.TIPO, matrix.Id, resul.VALOR), LINEA, COLUMNA);
                    }
                }
            } else if (columnaMatrix == null && filaMatrix != null) {
                resultFila = filaMatrix.getValor(e);
                if (esEntero(resultFila)) {
                    resul = accesoFila(Integer.parseInt(resultFila.VALOR.get(0).toString()), matrix, resulNuevoValor, e);
                    if (resul.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                        e.insertar(matrix.Id, new Simbolo(resul.TIPO, matrix.Id, resul.VALOR), LINEA, COLUMNA);
                    }
                }
            } else if (filaMatrix == null && columnaMatrix != null) {
                resultColumna = columnaMatrix.getValor(e);
                if (esEntero(resultColumna)) {
                    resul = accesoColumna(Integer.parseInt(resultColumna.VALOR.get(0).toString()), matrix, resulNuevoValor, e);
                    if (resul.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                        e.insertar(matrix.Id, new Simbolo(resul.TIPO, matrix.Id, resul.VALOR), LINEA, COLUMNA);
                    }
                }
            }
        }
        return null;
    }

    private Expresion accesoCelda(int fila, int columna, Simbolo matrix, Expresion nuevoValor) {
        ArrayList<Object> copiaValoresMatrix = (ArrayList<Object>) matrix.Valor.clone();
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");

        if (fila == 0 || columna == 0) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Indices no pueden ser 0 en matriz", LINEA, COLUMNA));
            return resul;
        } else if (fila * columna >= matrix.Valor.size() - 1) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error indice fuera de limite", LINEA, COLUMNA));
            return resul;
        } else {
            int posicion = Matrix.mapeoLexicoGraficoMatriz(fila, columna, Integer.parseInt(matrix.Valor.get(0).toString()));

            resul.TIPO.Tipo = matrix.Tipo.Tipo;
            resul.VALOR.clear();
            resul.VALOR.add(0, matrix.Valor.get(0));
            resul.VALOR.add(1, matrix.Valor.get(1));

            copiaValoresMatrix.set(posicion, nuevoValor);
            copiaValoresMatrix.remove(0);
            copiaValoresMatrix.remove(0);

            Tipo.EnumTipo tipoValorMatrix = TratamientoTipos.tipoSuperiorLista(copiaValoresMatrix);
            if (copiaValoresMatrix.size() > 1) {
                copiaValoresMatrix = Casteo.CasteoDeArray(copiaValoresMatrix, tipoValorMatrix);
            }

            for (Object item : copiaValoresMatrix) {
                resul.VALOR.add(item);
            }

            return resul;
        }
    }

    private Expresion accesoColumna(int columna, Simbolo matrix, Expresion valores, Entorno e) {
        ArrayList<Object> copiaValoresMatrix = (ArrayList<Object>) matrix.Valor.clone();
        Expresion resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        Expresion resultadoValor;
        int posicion = 0;
        int posicionValores = 0;
        if (columna == 0) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Indices no pueden ser 0 en matriz", LINEA, COLUMNA));
            return resul;
        } else if (columna > Integer.parseInt(matrix.Valor.get(1).toString())) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error indice fuera de limite", LINEA, COLUMNA));
            return resul;
        } else {
            resul.TIPO.Tipo = matrix.Tipo.Tipo;
            resul.VALOR.clear();
            resul.VALOR.add(0, matrix.Valor.get(0));
            resul.VALOR.add(1, matrix.Valor.get(1));

            for (int i = 1; i <= Integer.parseInt(matrix.Valor.get(0).toString()); i++) {
                if (valores.TIPO.Tipo == Tipo.EnumTipo.C) {
                    resultadoValor = (Expresion) valores.VALOR.get(posicionValores);
                } else {
                    resultadoValor = valores;
                }

                if (resultadoValor.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                    posicion = mapeoLexicoGraficoMatriz(i, columna, Integer.parseInt(matrix.Valor.get(0).toString()));
                    copiaValoresMatrix.set(posicion, resultadoValor.getValor(e));
                }

                if (posicionValores >= valores.VALOR.size()) {
                    posicionValores = 0;
                } else {
                    posicionValores++;
                }
            }

            copiaValoresMatrix.remove(0);
            copiaValoresMatrix.remove(0);

            Tipo.EnumTipo tipoValorMatrix = TratamientoTipos.tipoSuperiorLista(copiaValoresMatrix);
            if (copiaValoresMatrix.size() > 1) {
                copiaValoresMatrix = Casteo.CasteoDeArray(copiaValoresMatrix, tipoValorMatrix);
            }

            for (Object item : copiaValoresMatrix) {
                resul.VALOR.add(item);
            }
        }
        return resul;
    }

    private Expresion accesoFila(int fila, Simbolo matrix, Expresion valores, Entorno e) {
        ArrayList<Object> copiaValoresMatrix = (ArrayList<Object>) matrix.Valor.clone();
        Expresion resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        Expresion resultadoValor;
        int posicion = 0;
        int posicionValores = 0;
        if (fila == 0) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Indices no pueden ser 0 en matriz", LINEA, COLUMNA));
            return resul;
        } else if (fila > Integer.parseInt(matrix.Valor.get(1).toString())) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error indice fuera de limite", LINEA, COLUMNA));
            return resul;
        } else {
            resul.TIPO.Tipo = matrix.Tipo.Tipo;
            resul.VALOR.clear();
            resul.VALOR.add(0, matrix.Valor.get(0));
            resul.VALOR.add(1, matrix.Valor.get(1));

            for (int i = 1; i <= Integer.parseInt(matrix.Valor.get(1).toString()); i++) {
                if (valores.TIPO.Tipo == Tipo.EnumTipo.C) {
                    resultadoValor = (Expresion) valores.VALOR.get(posicionValores);
                } else {
                    resultadoValor = valores;
                }

                if (resultadoValor.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                    posicion = mapeoLexicoGraficoMatriz(fila, i, Integer.parseInt(matrix.Valor.get(0).toString()));
                    copiaValoresMatrix.set(posicion, resultadoValor.getValor(e));
                }

                if (posicionValores >= valores.VALOR.size()) {
                    posicionValores = 0;
                } else {
                    posicionValores++;
                }
            }

            copiaValoresMatrix.remove(0);
            copiaValoresMatrix.remove(0);

            Tipo.EnumTipo tipoValorMatrix = TratamientoTipos.tipoSuperiorLista(copiaValoresMatrix);
            if (copiaValoresMatrix.size() > 1) {
                copiaValoresMatrix = Casteo.CasteoDeArray(copiaValoresMatrix, tipoValorMatrix);
            }

            for (Object item : copiaValoresMatrix) {
                resul.VALOR.add(item);
            }
        }
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
