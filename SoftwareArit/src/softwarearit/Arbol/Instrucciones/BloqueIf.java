/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Herramientas.Casteo;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class BloqueIf extends Instruccion {

    Expresion condicion;
    Instruccion bloque;
    boolean condificionCumplida;

    public BloqueIf(int linea, int columna, Expresion condicion, Instruccion bloque) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.condicion = condicion;
        this.bloque = bloque;
        this.condificionCumplida = false;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("Bloque if", this, condicion, bloque);
    }

    @Override
    public Object Ejecutar(Entorno e) {
        /**
         * Primero vamos a poner que la condicion es falsa, para limpiar la
         * variable luego verifico si la condicion es boolean luego tengo que
         * verificar si la condicion es verdadera de ser verdadera tengo que
         * ejecutar el bloque de codigo que tiene de ser falsa solo me salgo
         * para ejecutar el bloque creo un entorno local y comienzo a ejecutar
         * las intrucciones luego de ejecutar el entorno, verifico si retorna
         * una instancia de break, continue, return y retorno el objecto. cambio
         * la variable que si se ejecuto este bloque if
         */
        this.condificionCumplida = false;
        Expresion resultado = this.condicion.getValor(e);

        if (resultado.TIPO.Tipo == Tipo.EnumTipo.C) {

            resultado.VALOR = Casteo.CasteoImplicito(resultado.VALOR, Tipo.EnumTipo.BOOLEAN);
            if (((Expresion) resultado.VALOR.get(0)).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error de tipo", LINEA, COLUMNA));
                return null;
            }

            for (Object item : resultado.VALOR) {
                if (item instanceof Expresion) {
                    if (((Expresion) item).TIPO.Tipo == Tipo.EnumTipo.BOOLEAN) {
                        if (!Boolean.parseBoolean(((Expresion) item).VALOR.get(0).toString())) {
                            return null;
                        }
                    } else {
                        Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error de tipo", LINEA, COLUMNA));
                        return null;
                    }
                } else {
                    Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error no es una expresion", LINEA, COLUMNA));
                    return null;
                }
            }
            /**
             * Se cumple la condicion y ejecuta lo que esta dentro
             *
             */
            Entorno entornoLocal = new Entorno(e, Entorno.EnumEntorno.IF);
            Object resultadoBloque = this.bloque.Ejecutar(entornoLocal);
            this.condificionCumplida = true;

            if (resultadoBloque != null) {
                if (resultadoBloque instanceof Break) {
                    return resultadoBloque;
                } else if (resultadoBloque instanceof Continue) {
                    return resultadoBloque;
                } else if (resultadoBloque instanceof Return) {
                    return resultadoBloque;
                }
            }
        } else if (resultado.TIPO.Tipo == Tipo.EnumTipo.BOOLEAN) {
            if (Boolean.parseBoolean(resultado.VALOR.get(0).toString())) {
                /**
                 * Se cumple la condicion y ejecuta lo que esta dentro
                 *
                 */
                Entorno entornoLocal = new Entorno(e, Entorno.EnumEntorno.IF);
                Object resultadoBloque = this.bloque.Ejecutar(entornoLocal);
                this.condificionCumplida = true;

                if (resultadoBloque != null) {
                    if (resultadoBloque instanceof Break) {
                        return resultadoBloque;
                    } else if (resultadoBloque instanceof Continue) {
                        return resultadoBloque;
                    } else if (resultadoBloque instanceof Return) {
                        return resultadoBloque;
                    }
                }
            }
        } else {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error de tipo", LINEA, COLUMNA));
        }
        return null;
    }

}
