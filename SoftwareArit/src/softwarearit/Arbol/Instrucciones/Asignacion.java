/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Simbolo;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Asignacion extends Instruccion {

    public String identificador;
    public Expresion valor;

    /**
     *
     * @param linea
     * @param columna
     * @param identificador
     * @param valor
     */
    public Asignacion(int linea, int columna, String identificador, Expresion valor) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = identificador;
        this.valor = valor;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        Valor valorId = new Valor(new Tipo(Tipo.EnumTipo.STRING), this.identificador);
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("Asignacion", this, valorId, this.valor);
    }

    @Override
    public Object Ejecutar(Entorno e) {
        Expresion result = this.valor.getValor(e);
        if (result != null) {
            if (result.TIPO.Tipo == Tipo.EnumTipo.NULL) {
                e.insertar(identificador, new Simbolo(new Tipo(((Valor) result).TIPO.Tipo), identificador, "null"), LINEA, COLUMNA);
            } else {
                e.insertar(identificador, new Simbolo(new Tipo(((Valor) result).TIPO.Tipo), identificador, result.VALOR), LINEA, COLUMNA);
            }
        } else {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error con el valor de la variable " + this.identificador, LINEA, COLUMNA));
        }
        return null;
    }

}
