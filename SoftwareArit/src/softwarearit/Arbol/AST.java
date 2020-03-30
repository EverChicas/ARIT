package softwarearit.Arbol;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Instrucciones.Instruccion;
import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Instrucciones.Funcion;
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

    public LinkedList<Nodo> INSTRUCCIONES;
    public Entorno TABLA;
    public StringBuilder GRAFICA_ARBOL;
    //public static int i;

    /**
     * Constructor del arbol AST
     *
     * @param - LISTA_INSTRUCCIONES lista de instrucciones que tienen
     */
    public AST(LinkedList<Nodo> instrucciones) {
        this.INSTRUCCIONES = instrucciones;
        this.TABLA = new Entorno(null, Entorno.EnumEntorno.GLOBAL);
        generarArbol();
    }

    private void generarArbol() {
        StringBuilder nodo = new StringBuilder();
        nodo.append("digraph G{\nrankir=TD;\n");

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
        /*
        for (Nodo nodo : this.INSTRUCCIONES) {
            if (nodo instanceof Instruccion) {
                Object resultBloque = ((Instruccion) nodo).Ejecutar(this.TABLA);
                
                if (resultBloque != null) {
                    if (resultBloque instanceof Break) {
                        return resultBloque;
                    } else if (resultBloque instanceof Continue) {
                        return resultBloque;
                    } else if (resultBloque instanceof Return) {
                        return resultBloque;
                    } else {
                        return null;
                    }
                }
                 
            } else if (nodo instanceof Expresion) {
                ((Expresion) nodo).getValor(this.TABLA);
            }
        }
         */
        for (Nodo nodo : this.INSTRUCCIONES) {
            if (nodo instanceof Funcion) {
                ((Funcion) nodo).Ejecutar(TABLA);
            }
        }

        for (Nodo nodo : this.INSTRUCCIONES) {
            if (nodo instanceof Instruccion) {
                if (!(nodo instanceof Funcion)) {
                    ((Instruccion) nodo).Ejecutar(TABLA);
                }
            } else if (nodo instanceof Expresion) {
                ((Expresion) nodo).getValor(TABLA);
            }
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
