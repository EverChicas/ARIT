/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Aritmeticas;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Herramientas.TratamientoTipos;
import softwarearit.Arbol.Herramientas.ValidarTiposVectores;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Negativo extends Expresion {

    Expresion var1;

    public Negativo(int linea, int columna, Expresion valor) {
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
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaUnHijo("-", this, var1);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Expresion resul = this.var1.getValor(e);

        /* OPERACIONES PARA TIPO C */
        if (resul.TIPO.Tipo == Tipo.EnumTipo.C) {
            if (ValidarTiposVectores.validarVectorAritmeticoNegativo((Expresion) resul.VALOR.get(0))) {
                return operarC(e, resul);
            }
            /* OPERACIONES PARA MATRIX */
        } else if (resul.TIPO.Tipo == Tipo.EnumTipo.MATRIZ) {
            if (ValidarTiposVectores.validarVectorAritmeticoNegativo((Expresion) resul.VALOR.get(2))) {
                return operacionMatrices(e, resul);
            }
            /* OPERACION NORMAL */
        } else {
            return operar(resul);
        }
        return new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
    }

    /**
     * Operacion cuando hay un tipo c y un valor normal
     *
     * @param e
     * @param valorTipoC
     * @param var2
     * @return
     */
    private Expresion operarC(Entorno e, Expresion valorTipoC) { //el var1 siempre va hacer el tipo c
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");

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

    /*  OPERACIONES DE MATRICES */
    private Expresion operacionMatrices(Entorno e, Expresion matrix) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");
        Expresion resul1;

        resul.VALOR.clear();

        resul.VALOR.add(0, matrix.VALOR.get(0));
        resul.VALOR.add(1, matrix.VALOR.get(1));

        for (int i = 2; i < matrix.VALOR.size(); i++) {
            resul1 = ((Expresion) matrix.VALOR.get(i)).getValor(e);
            resul.VALOR.add(operar(resul1));
        }

        resul.TIPO.Tipo = Tipo.EnumTipo.MATRIZ;
        return resul;
    }

    private Expresion operar(Expresion resul1) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");

        switch (TratamientoTipos.tipoSuperiorExpresion(resul1, resul1)) {
            case ERROR:
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "error de tipos: " + resul1.TIPO.Tipo, this.LINEA, this.COLUMNA));
                break;
            case STRING:
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "error de tipos: " + resul1.TIPO.Tipo, this.LINEA, this.COLUMNA));
                break;
            case ENTERO:
                resul.TIPO = new Tipo(Tipo.EnumTipo.ENTERO);
                resul.VALOR.clear();
                resul.VALOR.add(Integer.parseInt(resul1.VALOR.get(0).toString()) * -1);
                break;
            case NUMERIC:
                resul.TIPO = new Tipo(Tipo.EnumTipo.NUMERIC);
                resul.VALOR.clear();
                resul.VALOR.add(Double.parseDouble(resul1.VALOR.get(0).toString()) * -1);
                break;
        }
        return resul;
    }
}
