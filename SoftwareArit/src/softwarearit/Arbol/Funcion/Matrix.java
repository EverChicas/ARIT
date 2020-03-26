/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Funcion;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.AbstractFuncion;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Herramientas.Casteo;
import softwarearit.Arbol.Herramientas.TratamientoTipos;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 * la matrix, guardara el tamanio de columnas y tamanio de filas en la posicion
 * 0 columnas en la posicion 1 filas
 *
 * @author chicas
 */
public class Matrix extends AbstractFuncion {

    LinkedList<Nodo> lista;

    public Matrix(int linea, int columna, LinkedList<Nodo> lista) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.lista = lista;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos("FUNCION MATRIX", this, lista);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        if (lista.size() == 3
                && (!(((Expresion) lista.get(0)).getValor(e) instanceof List)
                || !(((Expresion) lista.get(0)).getValor(e) instanceof Matrix))) {
            Expresion valores = ((Expresion) lista.get(0)).getValor(e);
            Expresion columnas = ((Expresion) lista.get(1)).getValor(e);
            Expresion filas = ((Expresion) lista.get(2)).getValor(e);
            int tamanioCol = Integer.parseInt(columnas.VALOR.get(0).toString());
            int tamanioFila = Integer.parseInt(filas.VALOR.get(0).toString());
            if (tamanioCol > 0 && tamanioFila > 0) {

                resul.TIPO.Tipo = Tipo.EnumTipo.MATRIZ;
                resul.VALOR.clear();
                resul.VALOR.add(0, tamanioCol);
                resul.VALOR.add(1, tamanioFila);
                int punteroValores = 0;

                Tipo.EnumTipo tipoValoresMatrix = TratamientoTipos.tipoSuperiorLista(valores.VALOR);
                if (valores.VALOR.size() > 1) {
                    valores.VALOR = Casteo.CasteoDeArray(valores.VALOR, tipoValoresMatrix);
                }

                for (int numFila = 1; numFila <= tamanioFila; numFila++) {
                    for (int numCol = 1; numCol <= tamanioCol; numCol++) {

                        if (punteroValores == valores.VALOR.size()) {
                            punteroValores = 0;
                            if (valores instanceof Valor && valores.TIPO.Tipo != Tipo.EnumTipo.C) {
                                resul.VALOR.add(mapeoLexicoGraficoColumna(numCol, numFila, tamanioCol), valores);
                            } else {
                                resul.VALOR.add(mapeoLexicoGraficoColumna(numCol, numFila, tamanioCol), valores.VALOR.get(punteroValores));
                            }
                            punteroValores++;
                        } else {
                            if (valores instanceof Valor && valores.TIPO.Tipo != Tipo.EnumTipo.C) {
                                resul.VALOR.add(mapeoLexicoGraficoColumna(numCol, numFila, tamanioCol), valores);
                            } else {
                                resul.VALOR.add(mapeoLexicoGraficoColumna(numCol, numFila, tamanioCol), valores.VALOR.get(punteroValores));
                            }
                            punteroValores++;
                        }
                    }
                }
            } else {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error con indices de matriz", LINEA, COLUMNA));
            }
        } else {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error con numero de parametros en matrix ", LINEA, COLUMNA));
        }

        return resul;
    }

    private static int mapeoLexicoGraficoColumna(int numCol, int numFila, int tamanioCol) {
        return ((numFila - 1) * tamanioCol + numCol + 1);
    }

    public static StringBuilder imprimirMatriz(Entorno e, Expresion matrix) {
        StringBuilder cadena = new StringBuilder();
        int tamanioCol = Integer.parseInt(matrix.VALOR.get(0).toString());
        int tamanioFila = Integer.parseInt(matrix.VALOR.get(1).toString());
        Expresion valor;

        for (int i = 1; i <= tamanioFila; i++) {
            if (i == 1) {
                cadena.append("     [," + i + "]");
            } else {
                cadena.append(" [," + i + "]");
            }
        }

        cadena.append("\n");
        for (int i = 1; i <= tamanioCol; i++) {
            cadena.append("[" + i + ",]");
            for (int j = 1; j <= tamanioFila; j++) {
                valor = ((Expresion) matrix.VALOR.get(mapeoLexicoGraficoColumna(i, j, tamanioCol))).getValor(e);
                cadena.append("  " + valor.VALOR.get(0).toString() + "  ");
            }
            cadena.append("\n");
        }

        return cadena;
    }

}