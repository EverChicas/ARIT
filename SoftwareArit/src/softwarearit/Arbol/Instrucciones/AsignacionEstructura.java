/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Simbolo;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Funcion.C;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class AsignacionEstructura extends Instruccion {

    LinkedList<Nodo> listaAcceso;
    String identificador;
    Expresion valor;

    /**
     *
     * @param linea
     * @param columna
     * @param identificador
     * @param valor
     */
    public AsignacionEstructura(int linea, int columna, String identificador, LinkedList<Nodo> acceso, Expresion valor) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = identificador;
        this.valor = valor;
        this.listaAcceso = acceso;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {

        Valor valorId = new Valor(new Tipo(Tipo.EnumTipo.STRING), this.identificador);

        valorId.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos(this.identificador, valorId, this.listaAcceso);

        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("AsignacionEstructura", this, valorId, this.valor);
    }

    @Override
    public Object Ejecutar(Entorno e) {
        Expresion resul;
        Expresion valorNuevo = this.valor.getValor(e);
        Simbolo simbolo = e.buscar(this.identificador);

        if (valorNuevo != null) {
            if (simbolo.Tipo.Tipo == Tipo.EnumTipo.LISTA) {

            } else {
                resul = C.modificarVector(e, (Expresion) this.listaAcceso.get(0), simbolo, valorNuevo, this.LINEA, this.COLUMNA);
                if (resul.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                    e.insertar(simbolo.Id, new Simbolo(resul.TIPO, simbolo.Id, resul.VALOR), LINEA, COLUMNA);
                }
            }
        } else {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error con el valor de la variable " + this.identificador, LINEA, COLUMNA));
        }

        return null;
    }
}
