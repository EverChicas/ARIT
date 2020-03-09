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
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Do extends Instruccion {

    Expresion condicion;
    Instruccion bloqueDo;

    /**
     *
     * @param linea
     * @param columna
     * @param cond
     * @param bloque
     */
    public Do(int linea, int columna, Expresion cond, Instruccion bloque) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.condicion = cond;
        this.bloqueDo = bloque;
        generarGrafica();
    }

    /**
     *
     */
    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("DO WHILE", this, this.bloqueDo, this.condicion);
    }

    @Override
    public Object Ejecutar(Entorno e) {
        /**
         * Voy a ejecutar las instrucciones la primera vez, si estan traen una
         * instancia de BREAK, RETURN debe de salir del do-while luego obtengo
         * el valor de la condicion, si esta es verdadera se mantiene el el
         * while de ser contrario returna null y sale del ciclo
         */

        Object resultBloque;
        Expresion resultCondicion;
        Entorno entornoLocal;

        entornoLocal = new Entorno(e, Entorno.EnumEntorno.DO);
        resultBloque = this.bloqueDo.Ejecutar(entornoLocal);

        if (resultBloque != null) {
            if (resultBloque instanceof Break) {
                return null;
            } else if (resultBloque instanceof Return || resultBloque instanceof Continue) {
                return resultBloque;
            }
        } else {
            resultCondicion = this.condicion.getValor(e);

            if (resultCondicion.TIPO.Tipo != Tipo.EnumTipo.BOOLEAN) {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error en tipo de condicion " + resultCondicion.TIPO.Tipo, this.LINEA, this.COLUMNA));
            } else {
                while (Boolean.parseBoolean(resultCondicion.VALOR.get(0).toString())) {

                    entornoLocal = new Entorno(e, Entorno.EnumEntorno.DO);
                    resultBloque = this.bloqueDo.Ejecutar(entornoLocal);

                    if (resultBloque != null) {
                        if (resultBloque instanceof Break) {
                            return null;
                        } else if (resultBloque instanceof Return || resultBloque instanceof Continue) {
                            return resultBloque;
                        }
                    } else {
                        resultCondicion = this.condicion.getValor(e);
                        if (resultCondicion.TIPO.Tipo != Tipo.EnumTipo.BOOLEAN) {
                            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error en tipo de condicion " + resultCondicion.TIPO.Tipo, this.LINEA, this.COLUMNA));
                            return null;
                        }
                    }
                }
            }
        }

        return null;
    }

}
