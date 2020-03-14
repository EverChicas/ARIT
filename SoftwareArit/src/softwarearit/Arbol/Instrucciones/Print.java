/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import java.util.ArrayList;
import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Funcion.C;
import softwarearit.Arbol.Funcion.List;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;
import static softwarearit.Frame.Interfaz.*;

/**
 *
 * @author chicas
 */
public class Print extends Instruccion {

    Expresion var1;

    public Print(int linea, int columna, Expresion var1) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.var1 = var1;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("Print", this, var1);
    }

    @Override
    public Object Ejecutar(Entorno e) {
        Expresion resul = var1.getValor(e);

        if (resul.TIPO.Tipo == Tipo.EnumTipo.C) {
            printConsolaLinea(C.imprimirC(e, (Valor) resul).toString());
        } else if (resul.TIPO.Tipo == Tipo.EnumTipo.LISTA) {
            printConsolaLinea(List.imprimirLista(e, (Valor) resul).toString());
        } else if (resul.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
            printConsolaLinea(resul.VALOR.get(0).toString());
        }
        return null;
    }
}
