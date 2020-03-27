/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class AsignacionMatrix extends Instruccion {

    String identificador;
    Expresion columnaMatrix;
    Expresion filaMatrix;
    Expresion nuevoValor;

    public AsignacionMatrix(int linea, int columna, String id, Expresion columnaMatrix, Expresion filaMatrix, Expresion nuevoValor) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = id;
        this.columnaMatrix = columnaMatrix;
        this.filaMatrix = filaMatrix;
        this.nuevoValor = nuevoValor;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        LinkedList<Nodo> listaAcceso = new LinkedList<>();
        listaAcceso.add(columnaMatrix);
        listaAcceso.add(filaMatrix);

        Valor valorId = new Valor(new Tipo(Tipo.EnumTipo.STRING), this.identificador);

        valorId.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos(this.identificador, valorId, listaAcceso);

        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("AsignacionEstructura", this, valorId, this.nuevoValor);
    }

    @Override
    public Object Ejecutar(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
