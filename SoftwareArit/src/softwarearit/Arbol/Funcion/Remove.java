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
import softwarearit.Arbol.Instrucciones.Instruccion;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Remove extends AbstractFuncion {

    LinkedList<Nodo> lista;

    public Remove(int linea, int columna, LinkedList<Nodo> lista) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.lista = lista;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos("REMOVE", this, this.lista);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");

        if (this.lista.size() < 2) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error Fucnion sin parametros", LINEA, COLUMNA));
            return resul;
        } else if (this.lista.size() > 2) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error Fucnion resive 1 parametro", LINEA, COLUMNA));
            return resul;
        }

        Expresion resulCadena;
        Expresion resulRemove;

        if (this.lista.get(0) instanceof Expresion) {
            resulCadena = ((Expresion) this.lista.get(0)).getValor(e);
            resulRemove = ((Expresion) this.lista.get(1)).getValor(e);

            if (resulCadena.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                return resulCadena;
            } else if (resulRemove.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                return resulRemove;
            } else if (resulCadena.TIPO.Tipo == Tipo.EnumTipo.STRING && resulRemove.TIPO.Tipo == Tipo.EnumTipo.STRING) {
                resul.VALOR.clear();
                resul.TIPO.Tipo = Tipo.EnumTipo.STRING;
                String resultString = resulCadena.VALOR.get(0).toString().replace(resulRemove.VALOR.get(0).toString(), "");
                resul.VALOR.add(resultString);
            } else if (resulCadena.TIPO.Tipo == Tipo.EnumTipo.C || resulRemove.TIPO.Tipo == Tipo.EnumTipo.C) {
                if (resulCadena.VALOR.size() > 1 || resulRemove.VALOR.size() > 1) {
                    Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error estructura con tamaño mayor a uno", LINEA, COLUMNA));
                }
            } else {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error funcion solo permite tipo string", LINEA, COLUMNA));
            }

        } else {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error no es una expresion", LINEA, COLUMNA));
        }

        return resul;
    }

}
