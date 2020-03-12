/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Accesos;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class AccesoProfundo extends Expresion {

    public int profundidad;
    Expresion valor;

    public AccesoProfundo(int linea, int columna, Expresion valor, int profundidad) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.valor = valor;
        this.profundidad = profundidad;
        generarGrafica();
    }

    private void generarGrafica() {
        String nombre = valor.VALOR.get(0).toString();
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();

        for (int i = 1; i <= profundidad; i++) {
            nombre = "[" + nombre + "]";
        }
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo(nombre, this, valor);
    }

    @Override
    public Expresion getValor(Entorno e) {
        if (profundidad == 1) {
            return this.valor.getValor(e);
        }
        return null;
    }

}
