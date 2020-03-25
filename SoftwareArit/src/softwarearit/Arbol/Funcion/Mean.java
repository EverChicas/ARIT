/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Funcion;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import softwarearit.Arbol.Estructura.AbstractFuncion;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Herramientas.Casteo;
import softwarearit.Arbol.Instrucciones.Instruccion;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Mean extends AbstractFuncion {

    LinkedList<Nodo> lista;

    public Mean(int linea, int columna, LinkedList<Nodo> lista) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.lista = lista;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos("MEAN", this, this.lista);
    }

    @Override
    public Expresion getValor(Entorno e) {
        LinkedList<Expresion> valoresParaMedia = new LinkedList<>();
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        ArrayList<Double> valoresOperar = new ArrayList<>();
        double sumaValores = 0;

        if (this.lista.size() == 0) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error Fucnion sin parametros", LINEA, COLUMNA));
            return resul;
        } else if (this.lista.size() > 2) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error Fucnion no soporta mas de 2 parametros", LINEA, COLUMNA));
            return resul;
        }

        if (this.lista.get(0) instanceof Expresion) {//valores 
            Expresion resulValor;
            resulValor = ((Expresion) this.lista.get(0)).getValor(e);
            if (resulValor.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                return resulValor;
            } else if (resulValor.TIPO.Tipo == Tipo.EnumTipo.NUMERIC || resulValor.TIPO.Tipo == Tipo.EnumTipo.ENTERO) {
                valoresParaMedia.add(resulValor);
            } else if (resulValor.TIPO.Tipo == Tipo.EnumTipo.STRING || resulValor.TIPO.Tipo == Tipo.EnumTipo.BOOLEAN) {
                valoresParaMedia.add(Casteo.toDouble(resulValor));
            } else if (resulValor.TIPO.Tipo == Tipo.EnumTipo.C) {
                ArrayList<Object> listaC = Casteo.CasteoImplicito(resulValor.VALOR, Tipo.EnumTipo.NUMERIC);
                if (valorConError(listaC)) {
                    return resul;
                } else {
                    for (Object item : listaC) {
                        valoresParaMedia.add((Expresion) item);
                    }
                }
            } else {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error mean no soporta valores de tipo: " + resulValor.TIPO.Tipo, LINEA, COLUMNA));
            }
        } else {//instrucciones que pueden retornar un valor 
            Object resulInstruccion;
            resulInstruccion = ((Instruccion) this.lista.get(0)).Ejecutar(e);
            if (resulInstruccion instanceof Expresion) {
                if (((Expresion) resulInstruccion).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                    return ((Expresion) resulInstruccion);
                } else if (((Expresion) resulInstruccion).TIPO.Tipo == Tipo.EnumTipo.NUMERIC
                        || ((Expresion) resulInstruccion).TIPO.Tipo == Tipo.EnumTipo.ENTERO) {
                    valoresParaMedia.add(((Expresion) resulInstruccion));
                } else if (((Expresion) resulInstruccion).TIPO.Tipo == Tipo.EnumTipo.STRING
                        || ((Expresion) resulInstruccion).TIPO.Tipo == Tipo.EnumTipo.BOOLEAN) {
                    valoresParaMedia.add(Casteo.toDouble(((Expresion) resulInstruccion)));
                } else if (((Expresion) resulInstruccion).TIPO.Tipo == Tipo.EnumTipo.C) {
                    ArrayList<Object> listaC = Casteo.CasteoImplicito(((Expresion) resulInstruccion).VALOR, Tipo.EnumTipo.NUMERIC);
                    if (valorConError(listaC)) {
                        return resul;
                    } else {
                        for (Object item : listaC) {
                            valoresParaMedia.add((Expresion) item);
                        }
                    }
                } else {
                    Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error mean no soporta valores de tipo: " + ((Expresion) resulInstruccion).TIPO.Tipo, LINEA, COLUMNA));
                }
            } else {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error mean " + resulInstruccion.getClass().getName(), LINEA, COLUMNA));
            }
        }

        if (this.lista.size() == 2) {//por si trae trim
            Expresion resulTrim;
            resulTrim = ((Expresion) this.lista.get(1)).getValor(e);
            if (resulTrim.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                return resulTrim;
            } else if (resulTrim.TIPO.Tipo == Tipo.EnumTipo.NUMERIC || resulTrim.TIPO.Tipo == Tipo.EnumTipo.ENTERO) {
                double valorTrim = Double.parseDouble(resulTrim.VALOR.get(0).toString());

                for (Object item : valoresParaMedia) {
                    if (!(item instanceof Expresion)) {
                        return resul;
                    } else {
                        if (Double.parseDouble(((Expresion) item).VALOR.get(0).toString()) > valorTrim) {
                            valoresOperar.add(Double.parseDouble(((Expresion) item).VALOR.get(0).toString()));
                        }
                    }
                }
            } else {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error mean no soporta valores de tipo: " + resulTrim.TIPO.Tipo, LINEA, COLUMNA));
            }
        } else {
            for (Object item : valoresParaMedia) {
                if (!(item instanceof Expresion)) {
                    return resul;
                } else {
                    valoresOperar.add(Double.parseDouble(((Expresion) item).VALOR.get(0).toString()));
                }
            }
        }

        for (Double item : valoresOperar) {
            sumaValores += item;
        }

        resul.TIPO.Tipo = Tipo.EnumTipo.NUMERIC;
        resul.VALOR.clear();
        resul.VALOR.add(sumaValores / valoresOperar.size());
        return resul;
    }

    private boolean valorConError(ArrayList<Object> valores) {
        for (Object valor : valores) {
            if (valor instanceof Expresion) {
                if (((Expresion) valor).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

}
