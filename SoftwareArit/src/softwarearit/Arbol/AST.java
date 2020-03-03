package softwarearit.Arbol;

import java.io.IOException;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Instrucciones.Instruccion;
import java.util.LinkedList;
import javax.xml.transform.stax.StAXResult;
import softwarearit.Archivos.Archivo;
import softwarearit.Archivos.Guardar;
import softwarearit.Frame.Interfaz;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chicas
 */
public class AST {

    public LinkedList<Instruccion> INSTRUCCIONES;
    public Entorno TABLA;
    public StringBuilder GRAFICA_ARBOL;
    //public static int i;

    /**
     * Constructor del arbol AST
     *
     * @param - LISTA_INSTRUCCIONES lista de instrucciones que tienen
     */
    public AST(LinkedList<Instruccion> instrucciones) {
        this.INSTRUCCIONES = instrucciones;
        this.TABLA = new Entorno(null);
        generarArbol();
    }

    private void generarArbol() {
        StringBuilder nodo = new StringBuilder();
        nodo.append("digraph G{\n");

        nodo.append(Interfaz.GRAFICA_ARBOL.generarGraficaRoot(this.INSTRUCCIONES));

        nodo.append("\n}");
        this.GRAFICA_ARBOL = nodo;

        Guardar guardar = new Guardar();
        guardar.GuardarDot(new Archivo("", "AST.dot", this.GRAFICA_ARBOL.toString()));
        StringBuilder comando = new StringBuilder();
        comando.append("C:\\\\Program Files (x86)\\\\Graphviz2.38\\\\bin\\\\dot.exe ");
        comando.append("-Tpng AST.dot -o AST.png");
        
        Runtime run = Runtime.getRuntime();
        try {
            run.exec(comando.toString());
        } catch (Exception e) {
            System.out.println("Error con dot");
        }

    }

    /**
     * Retorna un valor de ser necesario
     *
     * @return
     */
    public Object ejecutar() {

        for (Instruccion instruccion : INSTRUCCIONES) {
            instruccion.Ejecutar(TABLA);
        }
        return null;
    }

    /**
     * Retorna el codigo para la grafica del arbol
     *
     * @return String
     */
    public String getGrafica() {
        return this.GRAFICA_ARBOL.toString();
    }

}
