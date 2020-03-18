/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Return extends Instruccion {

    public Expresion retornarValor;
    public Instruccion asignacion;

    /**
     *
     * @param linea
     * @param columna
     */
    public Return(int linea, int columna) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.retornarValor = null;
        this.asignacion = null;
        generarGrafica();
    }

    /**
     *
     * @param linea
     * @param columna
     * @param valor
     */
    public Return(int linea, int columna, Expresion valor) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.retornarValor = valor;
        this.asignacion = null;
        generarGrafica();
    }

    /**
     *
     * @param linea
     * @param columna
     * @param valor
     */
    public Return(int linea, int columna, Instruccion asignacion) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.retornarValor = null;
        this.asignacion = asignacion;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();

        if (this.retornarValor != null && this.asignacion == null) {
            this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("Return", this, this.retornarValor);
        } else if (this.retornarValor != null && this.asignacion == null) {
            this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("Return", this, this.asignacion);
        } else {
            this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarHojaNodo(this, "Return");
        }

    }

    @Override
    public Object Ejecutar(Entorno e) {
        if (e.tipoEntorno == Entorno.EnumEntorno.GLOBAL) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error return en entorno global", LINEA, COLUMNA));
        } else {
            if (this.retornarValor != null && this.asignacion == null) {
                Expresion result = this.retornarValor.getValor(e);
                return new Return(LINEA, COLUMNA, result);
            } else if (this.asignacion != null && this.retornarValor == null) {
                this.asignacion.Ejecutar(e);
            }
        }
        return null;
    }
}
