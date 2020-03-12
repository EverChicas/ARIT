/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones;

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
public class Acceso extends Expresion {

    String identificador;

    public Acceso(int linea, int columna, String id) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = id;

        Valor grafica = new Valor(new Tipo(Tipo.EnumTipo.STRING), identificador);
        this.NOMBRE = grafica.NOMBRE;
        this.GRAFICA = grafica.GRAFICA;

    }

    @Override
    public Expresion getValor(Entorno e) {
        Expresion result = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        Simbolo simbolo = e.buscar(this.identificador);

        if (simbolo == null) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "No se encontro valor de variable", LINEA, COLUMNA));
        } else {
            result = new Valor(new Tipo(simbolo.Tipo.Tipo), simbolo.Valor);
        }
        return result;
    }

}
