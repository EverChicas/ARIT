/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class CasoSwitch extends Instruccion {

    Expresion casoVerdadero;
    Instruccion bloqueInstrucciones;

    /**
     * Caso cuando es defaul y no trae instrucciones
     *
     * @param linea
     * @param columna
     */
    public CasoSwitch(int linea, int columna) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.casoVerdadero = null;
        this.bloqueInstrucciones = null;
        generarGrafica();
    }

    /**
     * Caso cuando es dafault y trae instrucciones
     *
     * @param linea
     * @param columna
     * @param bloque
     */
    public CasoSwitch(int linea, int columna, Instruccion bloque) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.casoVerdadero = null;
        this.bloqueInstrucciones = bloque;
        generarGrafica();
    }

    /**
     * Caso cuando trae una valor a comparar, y no trae instrucciones
     *
     * @param linea
     * @param columna
     * @param caso
     */
    public CasoSwitch(int linea, int columna, Expresion caso) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.casoVerdadero = caso;
        this.bloqueInstrucciones = null;
        generarGrafica();
    }

    /**
     * Caso cuando trae valor a comparar y trae instrucciones
     *
     * @param linea
     * @param columna
     * @param caso
     * @param bloque
     */
    public CasoSwitch(int linea, int columna, Expresion caso, Instruccion bloque) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.casoVerdadero = caso;
        this.bloqueInstrucciones = bloque;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        if (this.casoVerdadero != null) {
            if (this.bloqueInstrucciones != null) {
                this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("CASO", this, this.casoVerdadero, this.bloqueInstrucciones);
            } else {
                this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("CASO", this, this.bloqueInstrucciones);
            }
        } else {
            Valor etiquetaDefault = new Valor(new Tipo(Tipo.EnumTipo.STRING), "DEFAULT");
            if (this.bloqueInstrucciones != null) {
                this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("CASO", this, etiquetaDefault, this.bloqueInstrucciones);
            } else {
                this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarHojaExpresion(etiquetaDefault);
            }
        }

    }

    @Override
    public Object Ejecutar(Entorno e) {
        return this.bloqueInstrucciones.Ejecutar(e);
    }

}
