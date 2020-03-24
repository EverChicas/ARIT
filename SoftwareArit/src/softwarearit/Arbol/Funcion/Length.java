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
public class Length extends AbstractFuncion {

    LinkedList<Nodo> lista;

    public Length(int linea, int columna, LinkedList<Nodo> lista) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.lista = lista;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos("LENGTH", this, this.lista);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");

        if (this.lista.size() == 0) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error Fucnion sin parametros", LINEA, COLUMNA));
            return resul;
        } else if (this.lista.size() > 1) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error Fucnion resive 1 parametro", LINEA, COLUMNA));
            return resul;
        }

        Expresion resulValor;
        Object resulInstruccion;

        if (this.lista.get(0) instanceof Expresion) {
            resulValor = ((Expresion) this.lista.get(0)).getValor(e);

            if (resulValor.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                return resulValor;
            } else {
                resul.VALOR.clear();
                resul.TIPO.Tipo = Tipo.EnumTipo.STRING;
                resul.VALOR.add(resulValor.VALOR.size());
            }

        } else {
            resulInstruccion = ((Instruccion) this.lista.get(0)).Ejecutar(e);
            if (resulInstruccion instanceof Expresion) {
                if (((Expresion) resulInstruccion).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                    return ((Expresion) resulInstruccion);
                } else {
                    resul.VALOR.clear();
                    resul.TIPO.Tipo = Tipo.EnumTipo.STRING;
                    resul.VALOR.add(((Expresion) resulInstruccion).VALOR.size());
                }
            } else {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error no es un tipo de valor", LINEA, COLUMNA));
            }
        }

        return resul;
    }

}
