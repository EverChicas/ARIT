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
public class While extends Instruccion {

    Expresion condicion;
    Instruccion bloqueWhile;

    /**
     *
     * @param condicion
     * @param bloqueWhile
     */
    public While(Expresion condicion, Instruccion bloqueWhile) {
        this.condicion = condicion;
        this.bloqueWhile = bloqueWhile;
        generarGrafica();
    }

    /**
     * Metodo para generar la grafica del while
     */
    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("WHILE", this, this.condicion, this.bloqueWhile);
    }

    @Override
    public Object Ejecutar(Entorno e) {
        boolean condicionVerdadera = false;
        Expresion resulCondicion = this.condicion.getValor(e);
        Entorno entornoLocal = new Entorno(e, Entorno.EnumEntorno.WHILE);

        if (resulCondicion.TIPO.Tipo == Tipo.EnumTipo.C) {

            resulCondicion.VALOR = Casteo.CasteoImplicito(resulCondicion.VALOR, Tipo.EnumTipo.BOOLEAN);
            if (((Expresion) resulCondicion.VALOR.get(0)).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                return null;
            }

            for (Object item : resulCondicion.VALOR) {
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
            condicionVerdadera = true;

            while (condicionVerdadera) {

                Object resulInstruccion = this.bloqueWhile.Ejecutar(entornoLocal);
                if (resulInstruccion != null) {
                    if (resulInstruccion instanceof Break) {
                        return null;
                    } else if (resulInstruccion instanceof Continue) {
                        continue;
                    } else if (resulInstruccion instanceof Return) {
                        return resulInstruccion;
                    }
                }
                
                resulCondicion = this.condicion.getValor(e);
                resulCondicion.VALOR = Casteo.CasteoImplicito(resulCondicion.VALOR, Tipo.EnumTipo.BOOLEAN);
                if (((Expresion) resulCondicion.VALOR.get(0)).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                    condicionVerdadera = false;
                    return null;
                }

                for (Object item : resulCondicion.VALOR) {
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
            }

        } else {
            while ((Boolean.parseBoolean(resulCondicion.VALOR.get(0).toString()))) {

                Object resulInstruccion = this.bloqueWhile.Ejecutar(entornoLocal);
                if (resulInstruccion != null) {
                    if (resulInstruccion instanceof Break) {
                        return null;
                    } else if (resulInstruccion instanceof Continue) {
                        continue;
                    } else if (resulInstruccion instanceof Return) {
                        return resulInstruccion;
                    }
                }
                resulCondicion = this.condicion.getValor(e);
            }
        }
        return null;
    }

}
