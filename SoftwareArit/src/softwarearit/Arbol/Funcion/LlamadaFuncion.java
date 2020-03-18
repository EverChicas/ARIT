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
import softwarearit.Arbol.Estructura.Simbolo;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Instrucciones.Asignacion;
import softwarearit.Arbol.Instrucciones.Declaracion;
import softwarearit.Arbol.Instrucciones.Funcion;
import softwarearit.Arbol.Instrucciones.Instruccion;
import softwarearit.Arbol.Instrucciones.Return;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class LlamadaFuncion extends AbstractFuncion {

    String nombre;
    LinkedList<Nodo> parametros;

    /**
     * Llamada de funicion sin parametros
     *
     * @param nombre
     */
    LlamadaFuncion(int linea, int columna, String nombre) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.nombre = nombre;
        this.parametros = new LinkedList<>();
        generarGrafica();
    }

    /**
     * Llamada de funcion com parametros
     *
     * @param nombre
     * @param parametros
     */
    LlamadaFuncion(int linea, int columna, String nombre, LinkedList<Nodo> parametros) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.nombre = nombre;
        this.parametros = parametros;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        if (this.parametros.size() == 0) {
            this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarHojaNodo(this, this.nombre);
        } else {
            this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos(this.nombre, this, parametros);
        }
    }

    @Override
    public Expresion getValor(Entorno e) {
        Valor resultDeFuncion = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error"); //valor a retornar
        Entorno entornoLocal = new Entorno(e, Entorno.EnumEntorno.FUNCION); //entorno local
        String idParametro = "";
        Expresion valorParametro;// valor de parametro 
        Instruccion asignarParametro = null;
        Simbolo simboloFuncion = e.buscar("$$" + this.nombre);

        if (simboloFuncion == null) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error la funcion " + this.nombre + ", no se encuentra declarada", LINEA, COLUMNA));
        } else {
            if (((Funcion) simboloFuncion.Valor.get(0)).parametros.size() == this.parametros.size()) {

                for (int i = 0; i < ((Funcion) simboloFuncion.Valor.get(0)).parametros.size(); i++) {
                    idParametro = ((Declaracion) ((Funcion) simboloFuncion.Valor.get(0)).parametros.get(i)).identificador;//declaraciones 
                    valorParametro = ((Expresion) this.parametros.get(i)).getValor(e);

                    if (valorParametro.TIPO.Tipo == Tipo.EnumTipo.DEFAULT) {
                        if (((Declaracion) ((Funcion) simboloFuncion.Valor.get(0)).parametros.get(i)).valor == null) {
                            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Valor default no definido", LINEA, COLUMNA));
                        } else {
                            valorParametro = ((Declaracion) ((Funcion) simboloFuncion.Valor.get(0)).parametros.get(i)).valor.getValor(entornoLocal);
                            //asignarParametro = new Asignacion(LINEA, COLUMNA, idParametro, valorParametro);
                        }
                    } else {
                        //asignarParametro = new Asignacion(LINEA, COLUMNA, idParametro, valorParametro);
                    }

                    if (!idParametro.equals("") && valorParametro != null) {
                        entornoLocal.insertarParametro(idParametro, new Simbolo(new Tipo(valorParametro.TIPO.Tipo), idParametro, valorParametro.VALOR), LINEA, COLUMNA);
                    }
                }

                Object resultBloque = ((Funcion) simboloFuncion.Valor.get(0)).bloque.Ejecutar(entornoLocal);

                /**
                 * si el retorno de valor es diferente de null entonces esta
                 * retornando un valor y ese debe de retornar la funcion tengo
                 * que corregir eso
                 */
                if (resultBloque instanceof Return) {
                    return ((Return) resultBloque).retornarValor;
                }

            } else {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Numero de parametros no es igual", LINEA, COLUMNA));
            }
        }

        return resultDeFuncion;
    }

}
