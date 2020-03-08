/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Switch extends Instruccion {

    Expresion condicion;
    LinkedList<Instruccion> listaCasos;

    public Switch(Expresion condicion, LinkedList<Instruccion> listaCasos) {
        this.condicion = condicion;
        this.listaCasos = listaCasos;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijos("Switch", this, this.listaCasos);
    }

    @Override
    public Object Ejecutar(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
