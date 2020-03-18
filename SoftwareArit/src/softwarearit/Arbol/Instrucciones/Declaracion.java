/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Simbolo;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Declaracion extends Instruccion {

    public String identificador;
    public Expresion valor;

    public Declaracion(int linea, int columna, String identificador) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = identificador;
        this.valor = null;
        generarGrafica();
    }

    public Declaracion(int linea, int columna, String identificador, Expresion valor) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = identificador;
        this.valor = valor;
        generarGrafica();
    }

    private void generarGrafica() {
        Valor valorId = new Valor(new Tipo(Tipo.EnumTipo.STRING), this.identificador);
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        if (this.valor == null) {
            this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("Asignacion", this, valorId);
        } else {
            this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("Asignacion", this, valorId, this.valor);
        }
    }

    @Override
    public Object Ejecutar(Entorno e) {
        if (this.valor == null) {
            e.insertar(this.identificador, new Simbolo(new Tipo(Tipo.EnumTipo.NULL), this.identificador, "null"), LINEA, COLUMNA);
        } else {
            Expresion result = this.valor.getValor(e);
            e.insertar(this.identificador, new Simbolo(new Tipo(((Valor) result).TIPO.Tipo), identificador, result.VALOR), LINEA, COLUMNA);
        }
        return null;
    }

}
