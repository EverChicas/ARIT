/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol;

import java.util.ArrayList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Valor extends Expresion {

    /**
     * Toma los valores que usa la expresion y de este nivel tambien se puede
     * pasar los que usa nodo por ser extends de esta
     *
     * @param tipo
     * @param valor
     */
    public Valor(Tipo tipo, Object valor) {
        this.TIPO = tipo;
        this.VALOR = new ArrayList<Object>();
        this.VALOR.add(valor);

        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarHojaExpresion(this);
    }

    /**
     *
     * @param tipo - tipo de valor
     * @param valores - vector de valores
     */
    public Valor(Tipo tipo, ArrayList<Object> valores) {
        this.TIPO = tipo;
        this.VALOR = new ArrayList<Object>();
        this.VALOR = valores;
    }

    @Override
    public Expresion getValor(Entorno e) {
        if (VALOR.size() == 1) {
            return new Valor(TIPO, VALOR.get(0));
        } else {
            return new Valor(TIPO, VALOR);
        }
    }

}
