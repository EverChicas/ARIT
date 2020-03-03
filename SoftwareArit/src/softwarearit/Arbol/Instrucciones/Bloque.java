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
public class Bloque extends Instruccion {

    public LinkedList<Instruccion> instrucciones;

    public Bloque(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijos("instrucciones", this, instrucciones);
    }

    // TODO al hacer la grafica de los bloques tengo que mandar el padre del bloque y no el bloque como padre    
    @Override
    public Object Ejecutar(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
