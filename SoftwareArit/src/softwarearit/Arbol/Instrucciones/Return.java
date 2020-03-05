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
public class Return extends Instruccion {

    Expresion retornarValor;

    /**
     *
     * @param linea
     * @param columna
     */
    public Return(int linea, int columna) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.retornarValor = null;
        generarGrafica();
    }

    /**
     *
     * @param linea
     * @param columna
     * @param valor
     */
    public Return(int linea, int columna, Expresion valor) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.retornarValor = valor;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        if (this.retornarValor != null) {
            this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("Return", this, this.retornarValor);
        } else {
            this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarHojaNodo(this, "Return");
        }
    }

    @Override
    public Object Ejecutar(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
