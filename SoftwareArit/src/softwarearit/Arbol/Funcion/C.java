/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Funcion;

import java.util.ArrayList;
import softwarearit.Arbol.Estructura.AbstractFuncion;
import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Herramientas.Casteo;
import softwarearit.Arbol.Herramientas.TratamientoTipos;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;
import static softwarearit.Frame.Interfaz.printConsolaLinea;

/**
 *
 * @author chicas
 */
public class C extends AbstractFuncion {

    LinkedList<Nodo> lista;

    public C(LinkedList<Nodo> lista) {
        this.lista = lista;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos("FUNCION C", this, lista);
    }

    @Override
    public Expresion getValor(Entorno e) {
        ArrayList<Object> valores = new ArrayList<>();
        Expresion resulValor;

        /**
         * voy a recorrer el arreglo, si encuentro otro arreglo llama a
         * recursivo cuando recursivo retorne, me va a dar una lista de valores,
         * voy a recorrer en busca de errores si encuentro un error lo mando
         * como error tanto en lista del tipo c, que estoy recorriendo, como en
         * la del recursivo
         */
        for (Nodo nodo : this.lista) {
            if (nodo instanceof Expresion) {
                resulValor = ((Expresion) nodo).getValor(e);
                if (resulValor.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                    return new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");
                } else if (resulValor.TIPO.Tipo == Tipo.EnumTipo.C) {
                    ArrayList<Object> listaTemporal = getValorRecursivo(e, (Valor) resulValor);
                    for (Object item : listaTemporal) {
                        if (((Expresion) item).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                            valores.clear();
                            valores.add(new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error"));
                            return new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");
                        } else {
                            valores.add((Expresion) item);
                        }
                    }
                } else {
                    valores.add(resulValor);
                }
            } else {
                System.out.println("Error no es tipo expresion");
            }
        }

        Tipo.EnumTipo tipoValoresC = TratamientoTipos.tipoSuperiorLista(valores);
        if (valores.size() > 1) {
            return new Valor(new Tipo(Tipo.EnumTipo.C), Casteo.CasteoDeArray(valores, tipoValoresC));
        } else {
            return (Expresion) valores.get(0);
        }
    }

    private ArrayList<Object> getValorRecursivo(Entorno e, Valor valor) {
        ArrayList<Object> valores = new ArrayList<Object>();
        Expresion result;

        for (Object nodo : valor.VALOR) {
            result = ((Expresion) nodo).getValor(e);

            if (result.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                valores.clear();
                valores.add(new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error"));
                return valores;
            } else if (result.TIPO.Tipo == Tipo.EnumTipo.C) {

                ArrayList<Object> listaTemporal = getValorRecursivo(e, (Valor) result);

                for (Object item : listaTemporal) {
                    if (((Expresion) item).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                        valores.clear();
                        valores.add(new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error"));
                        return valores;
                    } else {
                        valores.add((Expresion) item);
                    }
                }
            } else {
                valores.add(result);
            }
        }
        return valores;
    }

    /**
     * Imprimir en cosola la variable de una funcion tipo C
     *
     * @param e
     * @param valor
     */
    public static void imprimirC(Entorno e, Valor valor) {
        StringBuilder cadena = new StringBuilder();

        for (Object nodo : valor.VALOR) {
            cadena.append(printRecursivo(e, (Valor) nodo));
        }
        printConsolaLinea(cadena.toString());
    }

    /**
     * Recursivo para sacar la informacion de las demas estructuras tipo c
     *
     * @param e
     * @param valor
     * @return
     */
    private static StringBuilder printRecursivo(Entorno e, Valor valor) {
        StringBuilder cadena = new StringBuilder();
        for (Object item : valor.VALOR) {
            if (item instanceof Valor) {
                cadena.append(printRecursivo(e, (Valor) item));
            } else {
                cadena.append(" " + item.toString());
            }
        }
        return cadena;
    }

}
