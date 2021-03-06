/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Funcion;

import java.util.ArrayList;
import softwarearit.Arbol.Estructura.AbstractFuncion;
import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Simbolo;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Herramientas.Casteo;
import softwarearit.Arbol.Herramientas.TratamientoTipos;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class C extends AbstractFuncion {

    LinkedList<Nodo> lista;

    public C(int linea, int columna, LinkedList<Nodo> lista) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.lista = lista;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos("FUNCION C", this, lista);
    }

    @Override
    public Expresion getValor(Entorno e) {
        ArrayList<Object> valores = new ArrayList<>();
        Expresion resulValor;
        /**
         * Busco que sea una lista
         */

        if (hayUnaLista(e, this.lista)) {
            LinkedList<Nodo> valoresDeLista = new LinkedList<>(); // getValoresLista(e, resulValor);
            LinkedList<Nodo> listaTemp;
            for (Object valor : this.lista) {
                if (valor instanceof Expresion) {
                    resulValor = ((Expresion) valor).getValor(e);
                    listaTemp = getValoresLista(e, resulValor);
                    for (Nodo item : listaTemp) {
                        if (!(item instanceof Expresion) || ((Expresion) item).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                            return new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
                        } else {
                            valoresDeLista.add(item);
                        }
                    }
                } else {
                    Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error de tipo en c", LINEA, COLUMNA));
                    return new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
                }
            }
        }

        for (Nodo nodo : this.lista) {
            if (nodo instanceof Expresion) {
                resulValor = ((Expresion) nodo).getValor(e);
                if (resulValor.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                    return new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");
                } else if (resulValor.TIPO.Tipo == Tipo.EnumTipo.C) {

                    ArrayList<Object> listaTemporal = getValorRecursivo(e, (Valor) resulValor);

                    for (Object item : listaTemporal) {
                        if (((Expresion) item).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                            valores.clear();
                            valores.add(new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error"));
                            return new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error");
                        } else {
                            valores.add((Expresion) item);
                        }
                    }

                } else {
                    valores.add(resulValor);
                }
            } else {
                System.out.println("Error no es tipo expresion");
            }
        }

        Tipo.EnumTipo tipoValoresC = TratamientoTipos.tipoSuperiorLista(valores);
        if (valores.size() > 1) {
            return new Valor(new Tipo(Tipo.EnumTipo.C), Casteo.CasteoDeArray(valores, tipoValoresC));
        } else {
            return (Expresion) valores.get(0);
        }
    }

    /**
     * Cuando tiene estructuras tipo c dentro de otra
     *
     * @param e
     * @param valor
     * @return
     */
    private ArrayList<Object> getValorRecursivo(Entorno e, Expresion valor) {
        ArrayList<Object> valores = new ArrayList<Object>();
        Expresion result;

        for (Object nodo : valor.VALOR) {
            result = ((Expresion) nodo).getValor(e);

            if (result.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                valores.clear();
                valores.add(new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error"));
                return valores;
            } else if (result.TIPO.Tipo == Tipo.EnumTipo.C) {

                ArrayList<Object> listaTemporal = getValorRecursivo(e, (Valor) result);

                for (Object item : listaTemporal) {
                    if (((Expresion) item).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                        valores.clear();
                        valores.add(new Valor(new Tipo(Tipo.EnumTipo.ERROR), "error"));
                        return valores;
                    } else {
                        valores.add((Expresion) item);
                    }
                }
            } else {
                valores.add(result);
            }
        }
        return valores;
    }

    /**
     * Imprimir en cosola la variable de una funcion tipo C
     *
     * @param e
     * @param valor
     */
    public static StringBuilder imprimirC(Entorno e, Valor valor) {
        StringBuilder cadena = new StringBuilder();

        for (Object nodo : valor.VALOR) {
            cadena.append(printRecursivo(e, (Valor) nodo));
        }
        return cadena;
    }

    /**
     * Recursivo para sacar la informacion de las demas estructuras tipo c
     *
     * @param e
     * @param valor
     * @return
     */
    private static StringBuilder printRecursivo(Entorno e, Valor valor) {
        StringBuilder cadena = new StringBuilder();
        for (Object item : valor.VALOR) {
            if (item instanceof Valor) {
                cadena.append(printRecursivo(e, (Valor) item));
            } else {
                cadena.append(" " + item.toString());
            }
        }
        return cadena;
    }

    public static Expresion modificarVector(Entorno e, Expresion indice, Simbolo vector, Expresion nuevoValor, int linea, int columna) {
        indice = indice.getValor(e);
        ArrayList<Object> lista = new ArrayList<>();

        if (indice.TIPO.Tipo != Tipo.EnumTipo.ENTERO) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error en tipo de indice", linea, columna));
            indice.TIPO.Tipo = Tipo.EnumTipo.ERROR;
            return indice;
        } else if (nuevoValor.VALOR.size() > 1) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error se quiere asignar mas de un valor a una posicion", linea, columna));
            indice.TIPO.Tipo = Tipo.EnumTipo.ERROR;
            return indice;
        }

        if (vector.Valor.size() > (Integer.parseInt(indice.VALOR.get(0).toString()) - 1)) {
            for (int i = 0; i < vector.Valor.size(); i++) {
                if (i == (Integer.parseInt(indice.VALOR.get(0).toString()) - 1)) {
                    lista.add(nuevoValor);
                } else {
                    lista.add(vector.Valor.get(i));
                }
            }
        } else {
            for (int i = 0; i < (Integer.parseInt(indice.VALOR.get(0).toString()) - 1); i++) {
                if (i >= vector.Valor.size()) {
                    lista.add(new Valor(new Tipo(Tipo.EnumTipo.NULL), "null"));
                } else {
                    if (vector.Valor.get(i) instanceof Valor) {
                        lista.add(new Valor(new Tipo(vector.Tipo.Tipo), ((Expresion) vector.Valor.get(i)).VALOR));
                    } else {
                        lista.add(new Valor(new Tipo(vector.Tipo.Tipo), vector.Valor.get(i)));
                    }
                }
            }
            lista.add(nuevoValor);
        }

        Tipo.EnumTipo tipoValoresC = TratamientoTipos.tipoSuperiorLista(lista);
        if (lista.size() > 1) {
            return new Valor(new Tipo(Tipo.EnumTipo.C), Casteo.CasteoDeArray(lista, tipoValoresC));
        } else {
            return (Expresion) lista.get(0);
        }
    }

    private LinkedList<Nodo> getValoresLista(Entorno e, Expresion valor) {
        LinkedList<Nodo> lista = new LinkedList<>();
        Expresion resul;

        for (Object nodo : valor.VALOR) {
            if (nodo instanceof Expresion) {
                resul = ((Expresion) nodo).getValor(e);
                if (sePuedeOperarList(resul)) {
                    if (resul.TIPO.Tipo == Tipo.EnumTipo.LISTA) {
                        LinkedList<Nodo> listaTemp = getValoresLista(e, resul);

                        for (Nodo item : listaTemp) {
                            if (!(item instanceof Expresion) || ((Expresion) item).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                                lista.clear();
                                lista.add(new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error"));
                                return lista;
                            } else {
                                lista.add(item);
                            }
                        }
                    } else if (resul.TIPO.Tipo == Tipo.EnumTipo.C) {
                        ArrayList<Object> arrayC = getValorRecursivo(e, resul);

                        for (Object item : arrayC) {
                            if (!(item instanceof Expresion) || ((Expresion) item).TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                                lista.clear();
                                lista.add(new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error"));
                                return lista;
                            } else {
                                lista.add((Nodo) item);
                            }
                        }
                    } else {
                        lista.add(resul);
                    }
                } else {
                    Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error no se puede operar el tipo: " + resul.TIPO.Tipo, LINEA, COLUMNA));
                    lista.clear();
                    lista.add(new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error"));
                    return lista;
                }
            } else {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error tipo de valor ", LINEA, COLUMNA));
                lista.add(new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error"));
                return lista;
            }
        }

        return lista;
    }

    private boolean sePuedeOperarList(Expresion valor) {
        switch (valor.TIPO.Tipo) {
            case ARRAY:
            case MATRIZ:
            case DEFAULT:
            case ERROR:
            case FUNCION:
            case VECTOR:
                return false;
            default:
                return true;
        }
    }

    private boolean hayUnaLista(Entorno e, LinkedList<Nodo> lista) {
        Expresion resulValor;
        for (Object valor : lista) {
            if (valor instanceof Expresion) {
                resulValor = ((Expresion) valor).getValor(e);
                if (resulValor.TIPO.Tipo == Tipo.EnumTipo.LISTA) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

}
