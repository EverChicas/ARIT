/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Logicas;

import softwarearit.Arbol.Estructura.Entorno;
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
public class Ternario extends Expresion {

    Expresion condicion;
    Expresion verdadera;
    Expresion falsa;

    /**
     * Crear objeto ternario
     *
     * @param linea - int
     * @param columna - int
     * @param condicion - expresion
     * @param verdadera - expresion
     * @param falsa - expresion
     */
    public Ternario(int linea, int columna, Expresion condicion, Expresion verdadera, Expresion falsa) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.condicion = condicion;
        this.verdadera = verdadera;
        this.falsa = falsa;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionTresHijos("?", this, condicion, verdadera, falsa);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");

        Expresion resulCondicion = this.condicion.getValor(e);

        if (resulCondicion.TIPO.Tipo != Tipo.EnumTipo.BOOLEAN) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Tipo de valor incorrecto "
                    + resulCondicion.TIPO.Tipo, LINEA, COLUMNA));
        } else {
            resul.VALOR.clear();
            if (Boolean.parseBoolean(resulCondicion.VALOR.get(0).toString())) {
                Expresion resultVerdadero = verdadera.getValor(e);
                resul.TIPO = resultVerdadero.TIPO;
                resul.VALOR = resultVerdadero.VALOR;
            } else {
                Expresion resultFalso = falsa.getValor(e);
                resul.TIPO = resultFalso.TIPO;
                resul.VALOR = resultFalso.VALOR;
            }
        }
        return resul;
    }
}
