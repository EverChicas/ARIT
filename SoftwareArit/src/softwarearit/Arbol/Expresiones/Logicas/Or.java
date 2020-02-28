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
public class Or extends Expresion {

    Expresion var1;
    Expresion var2;

    public Or(int linea, int columna, Expresion var1, Expresion var2) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.var1 = var1;
        this.var2 = var2;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("or", this, var1, var2);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");

        Expresion resul1 = var1.getValor(e);
        Expresion resul2 = var2.getValor(e);

        if (resul1.TIPO.Tipo != Tipo.EnumTipo.BOOLEAN || resul2.TIPO.Tipo != Tipo.EnumTipo.BOOLEAN) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "error de tipos: " + resul1.TIPO.Tipo + ", " + resul2.TIPO.Tipo, this.LINEA, this.COLUMNA));
        } else {
            resul.TIPO = new Tipo(Tipo.EnumTipo.BOOLEAN);
            resul.VALOR.clear();
            resul.VALOR.add(verificarOr(resul1.VALOR.get(0).toString(), resul2.VALOR.get(0).toString()));
        }
        return resul;
    }

    private boolean verificarOr(String resultado1, String resultado2) {
        if (Boolean.parseBoolean(resultado1) || Boolean.parseBoolean(resultado2)) {
            return true;
        } else {
            return false;
        }
    }

}
