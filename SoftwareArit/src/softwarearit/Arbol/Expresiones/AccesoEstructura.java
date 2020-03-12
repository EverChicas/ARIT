/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones;

import java.util.ArrayList;
import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Simbolo;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class AccesoEstructura extends Expresion {

    String identificador;
    LinkedList<Nodo> listaAcceso;

    public AccesoEstructura(int linea, int columna, String id, LinkedList<Nodo> lista) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = id;
        this.listaAcceso = lista;
        generarGrafica();
    }

    private void generarGrafica() {
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos(identificador, this, listaAcceso);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Expresion result = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        Simbolo simbolo = e.buscar(this.identificador);

        if (simbolo == null) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "No se encontro valor de variable", LINEA, COLUMNA));
        } else {
            result = new Valor(new Tipo(Tipo.EnumTipo.ERROR), simbolo.Valor);

            for (Nodo nivel : this.listaAcceso) {
                result = buscarValor(e, result.VALOR, nivel);
                if (result.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                    return result;
                }
            }
        }
        return result;
    }

    private Expresion buscarValor(Entorno e, ArrayList<Object> sim, Nodo nivel) {
        Expresion resultValor = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        Expresion resultIndice = ((Expresion) nivel).getValor(e);

        if (resultIndice.TIPO.Tipo != Tipo.EnumTipo.ENTERO) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error tipo de indice incorrecto", LINEA, COLUMNA));
        } else if (Integer.parseInt(resultIndice.VALOR.get(0).toString()) < 1) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error valor de indice menor que 1", LINEA, COLUMNA));
        } else {
            if (sim.size() >= Integer.parseInt(resultIndice.VALOR.get(0).toString())) {
                Expresion resul = (Expresion) sim.get(Integer.parseInt(resultIndice.VALOR.get(0).toString()) - 1);
                resultValor = resul;
            } else {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error indice superior al limite", LINEA, COLUMNA));
            }
        }
        return resultValor;
    }

}
