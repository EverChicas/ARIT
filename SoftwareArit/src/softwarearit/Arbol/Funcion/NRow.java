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
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Accesos.Acceso;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class NRow extends AbstractFuncion {

    LinkedList<Nodo> matrix;

    public NRow(int linea, int columna, LinkedList<Nodo> parametros) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.matrix = parametros;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos("NROW", this, this.matrix);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");

        if (this.matrix.size() > 1) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error cantidad de atributos mayor al numero de parametros", LINEA, COLUMNA));
        } else {
            if (this.matrix.get(0) instanceof Acceso) {
                Expresion matrixEnTabla = ((Acceso) this.matrix.get(0)).getValor(e);
                if (matrixEnTabla.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                    resul.TIPO.Tipo = Tipo.EnumTipo.ENTERO;
                    resul.VALOR.clear();
                    resul.VALOR.add(matrixEnTabla.VALOR.get(0));
                }
            } else if (this.matrix.get(0) instanceof Matrix) {
                Expresion resulMatrix = ((Expresion) this.matrix.get(0)).getValor(e);
                resul.TIPO.Tipo = Tipo.EnumTipo.ENTERO;
                resul.VALOR.clear();
                resul.VALOR.add(resulMatrix.VALOR.get(0));
            } else {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error tipo de valor incorrecto", LINEA, COLUMNA));
            }
        }
        return resul;
    }

}
