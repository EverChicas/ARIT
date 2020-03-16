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
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Instrucciones.Instruccion;
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
    LlamadaFuncion(String nombre) {
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
    LlamadaFuncion(String nombre, LinkedList<Nodo> parametros) {
        this.nombre = nombre;
        this.parametros = parametros;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        if (this.parametros.size() == 0) {
            this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarHojaExpresion(this);
        } else {
            this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos(this.nombre, this, parametros);
        }
    }

    @Override
    public Expresion getValor(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
        // Tools | Templates.
    }

}
