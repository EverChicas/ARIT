/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
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
public class Funcion extends Instruccion {

    String identificador;
    public LinkedList<Instruccion> parametros;
    public Instruccion bloque;

    /**
     *
     * @param linea
     * @param columna
     * @param identificador
     * @param bloque
     */
    public Funcion(int linea, int columna, String identificador, Instruccion bloque) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = identificador;
        this.parametros = new LinkedList<>();
        this.bloque = bloque;
        generarGrafica();
    }

    /**
     *
     * @param linea
     * @param columna
     * @param identificador
     * @param parametro
     * @param bloque
     */
    public Funcion(int linea, int columna, String identificador, Instruccion parametro, Instruccion bloque) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = identificador;
        this.parametros = new LinkedList<>();
        this.parametros.add(parametro);
        this.bloque = bloque;
        generarGrafica();
    }

    /**
     *
     * @param linea
     * @param columna
     * @param identificador
     * @param parametros
     * @param bloque
     */
    public Funcion(int linea, int columna, String identificador, LinkedList<Instruccion> parametros, Instruccion bloque) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = identificador;
        this.parametros = parametros;
        this.bloque = bloque;
        generarGrafica();
    }

    public Funcion(int linea, int columna, String identificador, Instruccion primerParametro, LinkedList<Instruccion> parametros, Instruccion bloque) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = identificador;
        this.bloque = bloque;
        this.parametros = new LinkedList<>();
        this.parametros.add(primerParametro);

        for (Instruccion parametro : parametros) {
            this.parametros.add(parametro);
        }

        generarGrafica();
    }

    private void generarGrafica() {
        Valor id = new Valor(new Tipo(Tipo.EnumTipo.STRING), this.identificador);
        Valor para = new Valor(new Tipo(Tipo.EnumTipo.STRING), "PARAMETROS");

        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();

        if (this.parametros != null) {
            para.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijos("PARAMETROS", para, parametros);
        }
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionTresHijos("FUNCION " + identificador, this, id, para, bloque);
    }

    @Override
    public Object Ejecutar(Entorno e) {
        //FUCNIONES NATIVAS
        if (this.identificador.equals("typeof")
                || this.identificador.equals("length")
                || this.identificador.equals("ncol")
                || this.identificador.equals("nrow")
                || this.identificador.equals("stringlength")
                || this.identificador.equals("remove")
                || this.identificador.equals("tolowercase")
                || this.identificador.equals("touppercase")
                || this.identificador.equals("trunk")
                || this.identificador.equals("round")
                || this.identificador.equals("mean")
                || this.identificador.equals("median")
                || this.identificador.equals("mode")
                || this.identificador.equals("pie")
                || this.identificador.equals("barplot")
                || this.identificador.equals("plot")
                || this.identificador.equals("hist")) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "La funcion " + this.identificador + " no puede tener nombre de funciones nativas", LINEA, COLUMNA));
            return null;
        }

        this.identificador = "$$" + this.identificador;

        Simbolo existe = e.buscar(this.identificador);

        if (existe == null) {
            e.insertar(this.identificador, new Simbolo(new Tipo(Tipo.EnumTipo.FUNCION), this.identificador, this), LINEA, COLUMNA);
        } else {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "La funcion " + identificador.replace("$", "") + " ya exite", LINEA, COLUMNA));
        }

        return null;
    }

}
