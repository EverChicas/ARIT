/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class If extends Instruccion {

    LinkedList<Instruccion> listaCondiciones;
    Instruccion bloqueElse; //BLOQUE DE INSTRUCCIONES DEL ELSE

    public If(LinkedList<Instruccion> condiciones) {
        this.listaCondiciones = condiciones;
        this.bloqueElse = null;
        generarGrafica();
    }

    public If(LinkedList<Instruccion> condiciones, Instruccion BloqueElse) {
        this.listaCondiciones = condiciones;
        this.bloqueElse = BloqueElse;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        LinkedList hijos = this.listaCondiciones;

        if (this.bloqueElse != null) {
            this.bloqueElse.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
            this.bloqueElse.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijos("Bloque else", this.bloqueElse, ((Bloque) this.bloqueElse).instrucciones);
            hijos.add(this.bloqueElse);

        }

        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijos("if", this, hijos);
    }

    @Override
    public Object Ejecutar(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
