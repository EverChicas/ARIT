/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Relacionales;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Herramientas.StringTo;
import softwarearit.Arbol.Herramientas.TratamientoTipos;
import softwarearit.Arbol.Herramientas.ValidarTiposVectores;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class MenorIgual extends Expresion {

    Expresion var1;
    Expresion var2;

    public MenorIgual(int linea, int columna, Expresion var1, Expresion var2) {
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
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("<=", this, var1, var2);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Expresion resul1 = this.var1.getValor(e);
        Expresion resul2 = this.var2.getValor(e);

        /* OPERACIONES CON VECTORES */
        if (resul1.TIPO.Tipo == Tipo.EnumTipo.C && resul2.TIPO.Tipo == Tipo.EnumTipo.C) {
            if (ValidarTiposVectores.validarVectoresRelacionales(this.LINEA, this.COLUMNA, (Expresion) resul1.VALOR.get(0), (Expresion) resul2.VALOR.get(0))) {
                return operar2C(e, resul1, resul2);
            }
        } else if (resul1.TIPO.Tipo == Tipo.EnumTipo.C) {
            if (ValidarTiposVectores.validarVectoresRelacionales(this.LINEA, this.COLUMNA, (Expresion) resul1.VALOR.get(0), resul2)) {
                return operarCIzquierda(e, resul1, resul2);
            }
        } else if (resul2.TIPO.Tipo == Tipo.EnumTipo.C) {
            if (ValidarTiposVectores.validarVectoresRelacionales(this.LINEA, this.COLUMNA, resul1, (Expresion) resul2.VALOR.get(0))) {
                return operarCDerecha(e, resul1, resul2);
            }
            /* OPERACIONES CON MATRIX*/
        } else if (resul1.TIPO.Tipo == Tipo.EnumTipo.MATRIZ && resul2.TIPO.Tipo == Tipo.EnumTipo.MATRIZ) {
            if (ValidarTiposVectores.validarVectoresRelacionales(LINEA, COLUMNA, (Expresion) resul1.VALOR.get(2), (Expresion) resul2.VALOR.get(2))) /* OPERACION NORMAL */ {
                return operar2Matrix(e, resul1, resul2);
            }
        } else if (resul1.TIPO.Tipo == Tipo.EnumTipo.MATRIZ) {
            if (ValidarTiposVectores.validarVectoresRelacionales(LINEA, COLUMNA, (Expresion) resul1.VALOR.get(2), resul2)) {
                return operarMatrixIzquierda(e, resul1, resul2);
            }
        } else if (resul2.TIPO.Tipo == Tipo.EnumTipo.MATRIZ) {
            if (ValidarTiposVectores.validarVectoresRelacionales(LINEA, COLUMNA, resul1, (Expresion) resul2.VALOR.get(2))) {
                return operarMatrixDerecha(e, resul1, resul2);
            }
            /* OPERACION NORMAL */
        } else {
            return operar(resul1, resul2);
        }
        return new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
    }

    private Expresion operarCIzquierda(Entorno e, Expresion valorTipoC, Expresion var2) { //el var1 siempre va hacer el tipo c
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");

        Expresion resulValor;

        resul.VALOR.clear();
        for (Object valor : valorTipoC.VALOR) {
            resulValor = ((Expresion) valor).getValor(e);
            if (resulValor == null) {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "valor nulo", LINEA, COLUMNA));
                resul.VALOR.clear();
                resul.VALOR.add("Error");
                return resul;
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
                resul.VALOR.clear();
                resul.VALOR.add("Error");
                return resul;
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
                resul.TIPO.Tipo = Tipo.EnumTipo.C;
            }
        } else {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error tamaño de vectores diferente, no se puede realizar la operacion", LINEA, COLUMNA));
            return resul;
        }

        return resul;
    }

    /* OPERACIONES PARA MATRIX*/
    private Expresion operar2Matrix(Entorno e, Expresion matrix1, Expresion matrix2) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");
        Expresion resul1;
        Expresion resul2;

        if (matrix1.VALOR.get(0) == matrix2.VALOR.get(0) && matrix1.VALOR.get(1) == matrix2.VALOR.get(1)) {
            resul.VALOR.clear();

            resul.VALOR.add(0, matrix1.VALOR.get(0));
            resul.VALOR.add(1, matrix1.VALOR.get(1));

            for (int i = 2; i < matrix1.VALOR.size(); i++) {
                resul1 = ((Expresion) matrix1.VALOR.get(i)).getValor(e);
                resul2 = ((Expresion) matrix2.VALOR.get(i)).getValor(e);
                resul.VALOR.add(operar(resul1, resul2));
            }
            resul.TIPO.Tipo = Tipo.EnumTipo.MATRIZ;
        } else {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error dimeciones de matriz diferentes", LINEA, COLUMNA));
        }
        return resul;
    }

    private Expresion operarMatrixIzquierda(Entorno e, Expresion matrix, Expresion constante) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");

        Expresion resulValor;

        resul.VALOR.clear();
        resul.VALOR.add(0, matrix.VALOR.get(0));
        resul.VALOR.add(1, matrix.VALOR.get(1));

        for (int i = 2; i < matrix.VALOR.size(); i++) {
            resulValor = ((Expresion) matrix.VALOR.get(i)).getValor(e);
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
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");

        Expresion resulValor;

        resul.VALOR.clear();
        resul.VALOR.add(0, matrix.VALOR.get(0));
        resul.VALOR.add(1, matrix.VALOR.get(1));

        for (int i = 2; i < matrix.VALOR.size(); i++) {
            resulValor = ((Expresion) matrix.VALOR.get(i)).getValor(e);
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
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");
        Tipo.EnumTipo TipoSuperiorValores = TratamientoTipos.tipoSuperiorExpresion(resul1, resul2);

        if (resul1.TIPO.Tipo == Tipo.EnumTipo.STRING && resul2.TIPO.Tipo == Tipo.EnumTipo.STRING) {
            resul.TIPO.Tipo = Tipo.EnumTipo.BOOLEAN;
            resul.VALOR.clear();
            double cadena1 = StringTo.StringToDouble(resul1.VALOR.get(0).toString());
            double cadena2 = StringTo.StringToDouble(resul2.VALOR.get(0).toString());
            resul.VALOR.add(cadena1 <= cadena2);
        } else if (TipoSuperiorValores == Tipo.EnumTipo.ENTERO || TipoSuperiorValores == Tipo.EnumTipo.NUMERIC) {
            resul.TIPO.Tipo = Tipo.EnumTipo.BOOLEAN;
            resul.VALOR.clear();
            resul.VALOR.add(Double.parseDouble(resul1.VALOR.get(0).toString()) <= Double.parseDouble(resul2.VALOR.get(0).toString()));
        } else {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error de tipos para menor igual " + resul1.TIPO.Tipo + ", " + resul2.TIPO.Tipo, LINEA, COLUMNA));
        }
        return resul;
    }

}
