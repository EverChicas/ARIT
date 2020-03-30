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
public class Suma extends Expresion {

    Expresion var1;
    Expresion var2;

    public Suma(int linea, int columna, Expresion var1, Expresion var2) {
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
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("+", this, var1, var2);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Expresion resul1 = this.var1.getValor(e);
        Expresion resul2 = this.var2.getValor(e);

        if (resul1.TIPO.Tipo == Tipo.EnumTipo.C && resul2.TIPO.Tipo == Tipo.EnumTipo.C) {
            if (ValidarTiposVectores.validarVectorAritmeticoS(this.LINEA, this.COLUMNA, (Expresion) resul1.VALOR.get(0), (Expresion) resul2.VALOR.get(0))) {
                return operar2C(e, resul1, resul2);
            }
        } else if (resul1.TIPO.Tipo == Tipo.EnumTipo.C) {
            if (ValidarTiposVectores.validarVectorAritmeticoS(this.LINEA, this.COLUMNA, (Expresion) resul1.VALOR.get(0), resul2)) {
                return operarCIzquierda(e, resul1, resul2);
            }
        } else if (resul2.TIPO.Tipo == Tipo.EnumTipo.C) {
            if (ValidarTiposVectores.validarVectorAritmeticoS(this.LINEA, this.COLUMNA, resul1, (Expresion) resul2.VALOR.get(0))) {
                return operarCDerecha(e, resul1, resul2);
            }
        } else {
            return operar(resul1, resul2);
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
    private Expresion operarCIzquierda(Entorno e, Expresion valorTipoC, Expresion var2) { //el var1 siempre va hacer el tipo c
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");

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

    private Expresion operarCDerecha(Entorno e, Expresion var1, Expresion valorC) { //el var2 siempre va hacer el tipo c
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");

        Expresion resulValor;

        resul.VALOR.clear();
        for (Object valor : valorC.VALOR) {
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

    /**
     * Realizo la operacion mas basica que se puede
     *
     * @param resul1
     * @param resul2
     * @return
     */
    private Expresion operar(Expresion resul1, Expresion resul2) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");

        switch (TratamientoTipos.tipoSuperiorExpresion(resul1, resul2)) {
            case ERROR:
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "error de tipos: " + resul1.TIPO.Tipo + ", " + resul2.TIPO.Tipo, this.LINEA, this.COLUMNA));
                break;
            case STRING:
                resul.TIPO = new Tipo(Tipo.EnumTipo.STRING);
                resul.VALOR.clear();
                resul.VALOR.add(resul1.VALOR.get(0).toString() + resul2.VALOR.get(0).toString());
                break;
            case ENTERO:
                resul.TIPO = new Tipo(Tipo.EnumTipo.ENTERO);
                resul.VALOR.clear();
                resul.VALOR.add(Integer.parseInt(resul1.VALOR.get(0).toString()) + Integer.parseInt(resul2.VALOR.get(0).toString()));
                break;
            case NUMERIC:
                resul.TIPO = new Tipo(Tipo.EnumTipo.NUMERIC);
                resul.VALOR.clear();
                resul.VALOR.add(Double.parseDouble(resul1.VALOR.get(0).toString()) + Double.parseDouble(resul2.VALOR.get(0).toString()));
                break;
        }
        return resul;
    }

}
