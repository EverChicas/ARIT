/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Expresiones.Expresion;
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
        Expresion resulCondicion = this.condicion.getValor(e);
        Entorno entornoLocal = new Entorno(e, Entorno.EnumEntorno.WHILE);
        
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
        return null;
    }

}
