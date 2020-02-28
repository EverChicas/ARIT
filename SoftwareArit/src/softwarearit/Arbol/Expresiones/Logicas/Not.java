/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Logicas;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Not extends Expresion {

    Expresion var1;

    public Not(int linea, int columna, Expresion valor) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.var1 = valor;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresion("not", this, var1);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");

        Expresion resul1 = var1.getValor(e);

        if (resul1.TIPO.Tipo != Tipo.EnumTipo.BOOLEAN) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "error de tipos: " + resul1.TIPO.Tipo, this.LINEA, this.COLUMNA));
        } else {
            resul.TIPO.Tipo = var1.TIPO.Tipo;
            resul.VALOR.clear();
            resul.VALOR.add(!Boolean.parseBoolean(var1.VALOR.get(0).toString()));
        }
        return resul;
    }

}
