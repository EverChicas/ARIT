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
public class Break extends Instruccion {

    public Break(int linea, int columna) {
        this.LINEA = linea;
        this.COLUMNA = columna;

        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarHojaInstruccion(this, "break");
    }

    @Override
    public Object Ejecutar(Entorno e) {
        for (Entorno buscar = e; e != null; buscar = e.anterior) {
            if (buscar.tipoEntorno == Entorno.EnumEntorno.FOR || buscar.tipoEntorno == Entorno.EnumEntorno.SWITCH || buscar.tipoEntorno == Entorno.EnumEntorno.WHILE) {
                return this;
            }
        }
        Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error con el break", LINEA, COLUMNA));
        return null;
    }

}
