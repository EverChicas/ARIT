/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Logicas;

import java.util.ArrayList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
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
public class Not extends Expresion {

    Expresion var1;

    public Not(int linea, int columna, Expresion valor) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.var1 = valor;
        generarGrafica();
    }

    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("not", this, var1);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Expresion resul1 = var1.getValor(e);

        if (resul1.TIPO.Tipo == Tipo.EnumTipo.C) {
            return operarC(e, resul1);
        } else if (resul1.TIPO.Tipo == Tipo.EnumTipo.MATRIZ) {
            return operarMatrix(e, resul1);
        } else {
            return operar(resul1);
        }
    }

    private Expresion operarC(Entorno e, Expresion valorTipoC) { //el var1 siempre va hacer el tipo c
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
                resul.VALOR.add(operar(resulValor));
            }
        }
        resul.TIPO.Tipo = Tipo.EnumTipo.C;
        return resul;
    }

    private Expresion operarMatrix(Entorno e, Expresion matrix) { //el var1 siempre va hacer el tipo c
        ArrayList<Object> copiaMatrix = (ArrayList<Object>) matrix.VALOR.clone();
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");
        Expresion resulValor;

        copiaMatrix.remove(0);
        copiaMatrix.remove(0);

        copiaMatrix = Casteo.CasteoImplicito(copiaMatrix, Tipo.EnumTipo.BOOLEAN);
        if (((Expresion) copiaMatrix.get(0)).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
            return resul;
        }

        resul.VALOR.clear();
        resul.VALOR.add(0, matrix.VALOR.get(0));
        resul.VALOR.add(1, matrix.VALOR.get(1));

        for (int i = 0; i < copiaMatrix.size(); i++) {
            resulValor = ((Expresion) copiaMatrix.get(i)).getValor(e);
            if (resulValor == null) {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "valor nulo", LINEA, COLUMNA));
                resul.VALOR.clear();
                resul.VALOR.add("Error");
                return resul;
            } else {
                resul.VALOR.add(operar(resulValor));
            }
        }
        resul.TIPO.Tipo = Tipo.EnumTipo.MATRIZ;
        return resul;
    }

    private Expresion operar(Expresion resul1) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        if (resul1.TIPO.Tipo != Tipo.EnumTipo.BOOLEAN) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "error de tipos: " + resul1.TIPO.Tipo, this.LINEA, this.COLUMNA));
        } else {
            resul.TIPO = new Tipo(Tipo.EnumTipo.BOOLEAN);
            resul.VALOR.clear();
            resul.VALOR.add(!Boolean.parseBoolean(resul1.VALOR.get(0).toString()));
        }
        return resul;
    }

}
