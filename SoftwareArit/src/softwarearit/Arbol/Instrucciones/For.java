/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class For extends Instruccion{
    Expresion identicador;
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
    public For(int linea, int columna, Expresion identificador, Expresion estructura, Instruccion bloque){
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identicador = identificador;
        this.estructura = estructura;
        this.bloque = bloque;
        generarGrafica();
    }
    
    private void generarGrafica(){
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("FOR", this, this.identicador);
        this.GRAFICA = this.GRAFICA.append(Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("FOR", this, this.estructura));
        this.GRAFICA = this.GRAFICA.append(Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("FOR", this,bloque));
    }

    @Override
    public Object Ejecutar(Entorno e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

 
