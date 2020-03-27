/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Accesos;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class AccesoMatrix extends Expresion {

    String identificador;
    Expresion columnaMatrix;
    Expresion filaMatrix;

    public AccesoMatrix(int linea, int columna, String id, Expresion columnaMatrix, Expresion filaMatrix) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = id;
        this.columnaMatrix = columnaMatrix;
        this.filaMatrix = filaMatrix;
        generarGrafica();
    }
    
    private void generarGrafica() {
         LinkedList<Nodo> listaAcceso = new LinkedList<>();
         listaAcceso.add(columnaMatrix);
         listaAcceso.add(filaMatrix);
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos(identificador, this, listaAcceso);
    }

    @Override
    public Expresion getValor(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
