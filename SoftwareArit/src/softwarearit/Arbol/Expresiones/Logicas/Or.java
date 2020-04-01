/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Logicas;

import java.util.ArrayList;
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

        /* OPERACIONES CON VECTORES*/
        if (resul1.TIPO.Tipo == Tipo.EnumTipo.C && resul2.TIPO.Tipo == Tipo.EnumTipo.C) {
            return operar2C(e, resul1, resul2);
        } else if (resul1.TIPO.Tipo == Tipo.EnumTipo.C) {
            return operarCIzquierda(e, resul1, resul2);
        } else if (resul2.TIPO.Tipo == Tipo.EnumTipo.C) {
            return operarCDerecha(e, resul1, resul2);
            /* OPERACIONES CON MATRICES */
        } else if (resul1.TIPO.Tipo == Tipo.EnumTipo.MATRIZ && resul2.TIPO.Tipo == Tipo.EnumTipo.MATRIZ) {
            return operar2Matrix(e, resul1, resul2);
        } else if (resul1.TIPO.Tipo == Tipo.EnumTipo.MATRIZ) {
            return operarCIzquierda(e, resul1, resul2);
        } else if (resul2.TIPO.Tipo == Tipo.EnumTipo.MATRIZ) {
            return operarMatrixDerecha(e, resul1, resul2);
            /* OPERACIONES CON VALORES REALES */
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

    /* OPERACIONES PARA MATRIX*/
    private Expresion operar2Matrix(Entorno e, Expresion matrix1, Expresion matrix2) {
        ArrayList<Object> copiaMatrix1 = (ArrayList<Object>) matrix1.VALOR.clone();
        ArrayList<Object> copiaMatrix2 = (ArrayList<Object>) matrix2.VALOR.clone();
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");
        Expresion resul1;
        Expresion resul2;

        copiaMatrix1.remove(0);
        copiaMatrix1.remove(0);
        copiaMatrix2.remove(0);
        copiaMatrix2.remove(0);

        copiaMatrix1 = Casteo.CasteoImplicito(copiaMatrix1, Tipo.EnumTipo.BOOLEAN);
        copiaMatrix2 = Casteo.CasteoImplicito(copiaMatrix2, Tipo.EnumTipo.BOOLEAN);

        if (((Expresion) copiaMatrix1.get(0)).TIPO.Tipo == Tipo.EnumTipo.ERROR || ((Expresion) copiaMatrix2.get(0)).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
            return resul;
        }

        if (matrix1.VALOR.get(0) == matrix2.VALOR.get(0) && matrix1.VALOR.get(1) == matrix2.VALOR.get(1)) {
            resul.VALOR.clear();

            resul.VALOR.add(0, matrix1.VALOR.get(0));
            resul.VALOR.add(1, matrix1.VALOR.get(1));

            for (int i = 0; i < copiaMatrix1.size(); i++) {
                resul1 = ((Expresion) copiaMatrix1.get(i)).getValor(e);
                resul2 = ((Expresion) copiaMatrix2.get(i)).getValor(e);
                resul.VALOR.add(operar(resul1, resul2));
            }
            resul.TIPO.Tipo = Tipo.EnumTipo.MATRIZ;
        } else {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error dimeciones de matriz diferentes", LINEA, COLUMNA));
        }
        return resul;
    }

    private Expresion operarMatrixIzquierda(Entorno e, Expresion matrix, Expresion constante) {
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
                resul.VALOR.add(operar(resulValor, constante));
            }
        }
        resul.TIPO.Tipo = Tipo.EnumTipo.MATRIZ;
        return resul;
    }

    private Expresion operarMatrixDerecha(Entorno e, Expresion constante, Expresion matrix) {
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
                resul.VALOR.add(operar(constante, resulValor));
            }
        }

        resul.TIPO.Tipo = Tipo.EnumTipo.MATRIZ;
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
