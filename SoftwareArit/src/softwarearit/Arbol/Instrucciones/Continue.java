/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Continue extends Instruccion {

    public Continue(int linea, int columna) {
        this.LINEA = linea;
        this.COLUMNA = columna;

        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarHojaInstruccion(this, "continue");
    }

    @Override
    public Object Ejecutar(Entorno e) {
        for (Entorno buscar = e; buscar != null; buscar = buscar.anterior) {
            if (buscar.tipoEntorno == Entorno.EnumEntorno.FOR || 
                    buscar.tipoEntorno == Entorno.EnumEntorno.SWITCH || 
                    buscar.tipoEntorno == Entorno.EnumEntorno.WHILE ||
                    buscar.tipoEntorno == Entorno.EnumEntorno.DO) {
                return this;
            }
        }
        Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error con el continue", LINEA, COLUMNA));
        return null;
    }

}
