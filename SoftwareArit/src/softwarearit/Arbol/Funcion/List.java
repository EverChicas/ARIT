/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Funcion;

import java.util.ArrayList;
import java.util.LinkedList;
import softwarearit.Arbol.Estructura.AbstractFuncion;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class List extends AbstractFuncion {

    LinkedList<Nodo> lista;

    public List(int linea, int columna, LinkedList<Nodo> lista) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.lista = lista;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos("LIST", this, this.lista);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        Expresion resulValor;

        resul.VALOR.clear();
        for (Nodo valor : lista) {
            if (valor instanceof Expresion) {
                resulValor = ((Expresion) valor).getValor(e);
                if (resulValor.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                    return resulValor;
                } else {
                    resul.VALOR.add(resulValor);
                }
            } else {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Tipo de no soportado", LINEA, COLUMNA));
                resul.TIPO.Tipo = Tipo.EnumTipo.ERROR;
                return resul;
            }
        }

        resul.TIPO.Tipo = Tipo.EnumTipo.LISTA;
        return resul;
    }

    private ArrayList<Object> valorEnLista(Entorno e, Expresion lista) {
        ArrayList<Object> valores = new ArrayList<>();
        Expresion resulValor;

        for (Object valor : lista.VALOR) {
            if (valor instanceof Expresion) {
                resulValor = ((Expresion) valor).getValor(e);
                if (resulValor.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                    return null;
                } else if (resulValor.TIPO.Tipo == Tipo.EnumTipo.LISTA) {
                    ArrayList<Object> tempValores = valorEnLista(e, resulValor);
                    if (tempValores == null) {
                        return null;
                    } else {
                        for (Object item : tempValores) {
                            valores.add(item);
                        }
                    }
                } else {
                    valores.add(resulValor);
                }
            } else {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Tipo de no soportado", LINEA, COLUMNA));
                return null;
            }
        }
        return valores;
    }

    public static StringBuilder imprimirLista(Entorno e, Valor v) {
        StringBuilder cadena = new StringBuilder();
        Object temp;
        for (int i = 0; i < v.VALOR.size(); i++) {
            cadena.append("[[" + (i + 1) + "]]\n");
            temp = v.VALOR.get(i);
            if (((Expresion) temp).TIPO.Tipo == Tipo.EnumTipo.C) {
                cadena.append(C.imprimirC(e, (Valor) temp).append("\n"));
            } else if ((((Expresion) temp).TIPO.Tipo == Tipo.EnumTipo.LISTA)) {
                cadena.append(List.imprimirLista(e, (Valor) temp).append("\n"));
            } else {
                cadena.append(((Expresion) temp).VALOR.get(0)).append("\n");
            }
        }
        return cadena;
    }

}
