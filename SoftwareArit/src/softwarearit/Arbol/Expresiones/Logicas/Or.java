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
import softwarearit.Arbol.Herramientas.Casteo;
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
        Expresion resul1 = var1.getValor(e);
        Expresion resul2 = var2.getValor(e);

        if (resul1.TIPO.Tipo == Tipo.EnumTipo.C && resul2.TIPO.Tipo == Tipo.EnumTipo.C) {
            return operar2C(e, resul1, resul2);
        } else if (resul1.TIPO.Tipo == Tipo.EnumTipo.C) {
            return operarCIzquierda(e, resul1, resul2);
        } else if (resul2.TIPO.Tipo == Tipo.EnumTipo.C) {
            return operarCDerecha(e, resul1, resul2);
        } else {
            return operar(resul1, resul2);
        }
    }

    private Expresion operarCIzquierda(Entorno e, Expresion valorTipoC, Expresion var2) { //el var1 siempre va hacer el tipo c
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");

        valorTipoC.VALOR = Casteo.CasteoImplicito(valorTipoC.VALOR, Tipo.EnumTipo.BOOLEAN);
        if (((Expresion) valorTipoC.VALOR.get(0)).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
            return resul;
        }

        Expresion resulValor;
        resul.VALOR.clear();

        for (Object valor : valorTipoC.VALOR) {
            resulValor = ((Expresion) valor).getValor(e);
            if (resulValor == null) {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "valor nulo", LINEA, COLUMNA));
            } else {
                resul.VALOR.add(operar(resulValor, var2));
            }
        }
        resul.TIPO.Tipo = Tipo.EnumTipo.C;
        return resul;
    }

    private Expresion operarCDerecha(Entorno e, Expresion var1, Expresion valorTipoC) { //el var2 siempre va hacer el tipo c
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");

        valorTipoC.VALOR = Casteo.CasteoImplicito(valorTipoC.VALOR, Tipo.EnumTipo.BOOLEAN);
        if (((Expresion) valorTipoC.VALOR.get(0)).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
            return resul;
        }

        Expresion resulValor;
        resul.VALOR.clear();

        for (Object valor : valorTipoC.VALOR) {
            resulValor = ((Expresion) valor).getValor(e);
            if (resulValor == null) {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "valor nulo", LINEA, COLUMNA));
            } else {
                resul.VALOR.add(operar(var1, resulValor));
            }
        }
        resul.TIPO.Tipo = Tipo.EnumTipo.C;
        return resul;
    }

    private Expresion operar2C(Entorno e, Expresion var1, Expresion var2) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");

        var1.VALOR = Casteo.CasteoImplicito(var1.VALOR, Tipo.EnumTipo.BOOLEAN);
        var2.VALOR = Casteo.CasteoImplicito(var2.VALOR, Tipo.EnumTipo.BOOLEAN);
        if (((Expresion) var1.VALOR.get(0)).TIPO.Tipo == Tipo.EnumTipo.ERROR || ((Expresion) var2.VALOR.get(0)).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
            return resul;
        }

        Expresion resul1;
        Expresion resul2;

        if (var1.VALOR.size() == var2.VALOR.size()) {
            resul.VALOR.clear();
            for (int i = 0; i < var1.VALOR.size(); i++) {
                resul1 = ((Expresion) var1.VALOR.get(i)).getValor(e);
                resul2 = ((Expresion) var2.VALOR.get(i)).getValor(e);
                resul.VALOR.add(operar(resul1, resul2));
            }
        } else {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error tamaño de vectores diferente, no se puede realizar la operacion", LINEA, COLUMNA));
            return resul;
        }
        resul.TIPO.Tipo = Tipo.EnumTipo.C;
        return resul;
    }

    private Expresion operar(Expresion resul1, Expresion resul2) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");

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
