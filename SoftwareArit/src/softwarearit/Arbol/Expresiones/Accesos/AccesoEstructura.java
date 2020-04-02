/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Accesos;

import java.util.ArrayList;
import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Simbolo;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class AccesoEstructura extends Expresion {

    String identificador;
    LinkedList<Nodo> listaAcceso;

    public AccesoEstructura(int linea, int columna, String id, LinkedList<Nodo> lista) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.identificador = id;
        this.listaAcceso = lista;
        generarGrafica();
    }

    private void generarGrafica() {
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos(identificador, this, listaAcceso);
    }

    @Override
    public Expresion getValor(Entorno e) {
        Expresion result = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        Simbolo simbolo = e.buscar(this.identificador);

        if (simbolo == null) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "No se encontro valor de variable", LINEA, COLUMNA));
            return result;
        } else {

            if (simbolo.Tipo.Tipo == Tipo.EnumTipo.MATRIZ) {
                if (this.listaAcceso.size() > 1) {
                    Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error con acceso en matrix", LINEA, COLUMNA));
                    return result;
                } else {
                    if (esEntero(((AccesoProfundo) this.listaAcceso.get(0)).valor)) {
                        Expresion indice = ((AccesoProfundo) this.listaAcceso.get(0)).valor;
                        indice = indice.getValor(e);
                        if (Integer.parseInt(indice.VALOR.get(0).toString()) < 1) {
                            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error valor de indice menor que 1", LINEA, COLUMNA));
                            return result;
                        } else if (Integer.parseInt(indice.VALOR.get(0).toString()) >= simbolo.Valor.size() - 1) {
                            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error indice superior al limite", LINEA, COLUMNA));
                            return result;
                        } else {
                            result = ((Expresion) simbolo.Valor.get(Integer.parseInt(indice.VALOR.get(0).toString()) + 1)).getValor(e);
                            return new Valor(new Tipo(result.TIPO.Tipo), result.VALOR);
                        }
                    } else {
                        Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error con tipo de indice ", LINEA, COLUMNA));
                        return result;
                    }
                }
            }

            for (Nodo nodoNivel : this.listaAcceso) {
                result = buscarValor(e, result.VALOR, nodoNivel);
                if (result.TIPO.Tipo == Tipo.EnumTipo.ERROR) {
                    return result;
                } else if (simbolo.Tipo.Tipo == Tipo.EnumTipo.LISTA && ((AccesoProfundo) nodoNivel).profundidad == 1) {
                    ArrayList<Object> nuevoValores = new ArrayList<>();
                    result.TIPO.Tipo = Tipo.EnumTipo.LISTA;
                    for (Object item : result.VALOR) {
                        if (item instanceof Valor) {
                            nuevoValores.add(item);
                        } else if (item instanceof String) {
                            nuevoValores.add(new Valor(new Tipo(Tipo.EnumTipo.STRING), item));
                        } else if (item instanceof Integer) {
                            nuevoValores.add(new Valor(new Tipo(Tipo.EnumTipo.ENTERO), item));
                        } else if (item instanceof Double) {
                            nuevoValores.add(new Valor(new Tipo(Tipo.EnumTipo.NUMERIC), item));
                        } else if (item instanceof Boolean) {
                            nuevoValores.add(new Valor(new Tipo(Tipo.EnumTipo.BOOLEAN), item));
                        }
                    }
                    result.VALOR = nuevoValores;
                }
            }
        }
        return result;
    }

    private Expresion buscarValor(Entorno e, ArrayList<Object> sim, Nodo nivel) {
        Expresion resultValor = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");
        Expresion resultIndice = ((Expresion) nivel).getValor(e);

        if (resultIndice.TIPO.Tipo != Tipo.EnumTipo.ENTERO) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error tipo de indice incorrecto", LINEA, COLUMNA));
        } else if (Integer.parseInt(resultIndice.VALOR.get(0).toString()) < 1) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error valor de indice menor que 1", LINEA, COLUMNA));
        } else {
            if (sim.size() >= Integer.parseInt(resultIndice.VALOR.get(0).toString())) {
                if (sim.get(Integer.parseInt(resultIndice.VALOR.get(0).toString()) - 1) instanceof Expresion) {
                    Expresion resul = (Expresion) sim.get(Integer.parseInt(resultIndice.VALOR.get(0).toString()) - 1);
                    resultValor = resul;
                }else{
                    Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error indice superior al limite", LINEA, COLUMNA));
                }
            } else {
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error indice superior al limite", LINEA, COLUMNA));
            }
        }
        return resultValor;
    }

    /**
     * Validar si el indice es de tipo entero
     *
     * @param e
     * @return
     */
    private boolean esEntero(Expresion e) {
        if (e.TIPO.Tipo == Tipo.EnumTipo.ENTERO) {
            return true;
        } else {
            return false;
        }
    }
}
