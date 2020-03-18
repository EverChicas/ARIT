/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
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
public class For extends Instruccion {

    String identicador;
    Expresion estructura;
    Instruccion bloque;

    /**
     *
     * @param linea
     * @param columna
     * @param identificador
     * @param estructura
     * @param bloque
     */
    public For(int linea, int columna, String identificador, Expresion estructura, Instruccion bloque) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identicador = identificador;
        this.estructura = estructura;
        this.bloque = bloque;
        generarGrafica();
    }

    private void generarGrafica() {
        Valor ide = new Valor(new Tipo(Tipo.EnumTipo.STRING), this.identicador);
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("FOR", this, ide);
        this.GRAFICA = this.GRAFICA.append(Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("FOR", this, this.estructura));
        this.GRAFICA = this.GRAFICA.append(Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("FOR", this, bloque));
    }

    @Override
    public Object Ejecutar(Entorno e) {
        Expresion tempResul;
        Expresion resulEstructura = this.estructura.getValor(e);
        Entorno entornoLocal = new Entorno(e, Entorno.EnumEntorno.FOR);
        Object resultInstruccion;

        for (Object item : resulEstructura.VALOR) {
            if (item instanceof Expresion) {
                tempResul = ((Expresion) item).getValor(e);
                entornoLocal.insertar(this.identicador, new Simbolo(new Tipo(tempResul.TIPO.Tipo), this.identicador, ((Expresion) item).VALOR), LINEA, COLUMNA);
            } else {
                entornoLocal.insertar(this.identicador, new Simbolo(new Tipo(resulEstructura.TIPO.Tipo), this.identicador, item), LINEA, COLUMNA);
            }
            resultInstruccion = this.bloque.Ejecutar(entornoLocal);

            if (resultInstruccion != null) {
                if (resultInstruccion instanceof Break) {
                    return null;
                } else if (resultInstruccion instanceof Continue) {
                    continue;
                } else if (resultInstruccion instanceof Return) {
                    return resultInstruccion;
                }
            }
        }

        return null;
    }
}
