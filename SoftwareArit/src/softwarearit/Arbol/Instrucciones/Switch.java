/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Instrucciones;

import java.util.LinkedList;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Herramientas.CompararValor;
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Switch extends Instruccion {

    Expresion condicion;
    LinkedList<Instruccion> listaCasos;

    public Switch(Expresion condicion, LinkedList<Instruccion> listaCasos) {
        this.condicion = condicion;
        this.listaCasos = listaCasos;
        generarGrafica();
    }

    private void generarGrafica() {
        LinkedList<Instruccion> grafica = (LinkedList) this.listaCasos.clone();
        Valor casos = new Valor(new Tipo(Tipo.EnumTipo.STRING), "CASOS");

        casos.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijos("CASOS", casos, grafica);

        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("SWITCH", this, this.condicion, casos);

    }

    @Override
    public Object Ejecutar(Entorno e) {
        boolean esDefault = false;
        Expresion resulCondicion = this.condicion.getValor(e);
        Expresion resulCaso = null;
        Entorno entornoLocal = null;
        Object resultInstuccionesCaso = null;
        CompararValor comparar = new CompararValor();

        for (Instruccion caso : this.listaCasos) {
            /**
             * El caso existe
             */
            if (((CasoSwitch) caso).casoVerdadero != null) {
                resulCaso = ((CasoSwitch) caso).casoVerdadero.getValor(e);
                if (comparar.sonIguales(resulCondicion, resulCaso)) {// El caso es verdadero
                    /**
                     * Ejecuto las instrucciones del caso en un entorno local
                     */
                    entornoLocal = new Entorno(e, Entorno.EnumEntorno.SWITCH);
                    resultInstuccionesCaso = ((CasoSwitch) caso).Ejecutar(entornoLocal);
                    if (resultInstuccionesCaso != null) {
                        if (resultInstuccionesCaso instanceof Break) {
                            return null;
                        } else if (resultInstuccionesCaso instanceof Continue) {
                            return resultInstuccionesCaso;
                        } else if (resultInstuccionesCaso instanceof Return) {
                            return resultInstuccionesCaso;
                        } else {
                            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error en instrucciones switch", LINEA, COLUMNA));
                        }
                    }
                }
            } else {
                if (esDefault) {
                    Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), " Error switch con mas de un default", ((CasoSwitch) caso).LINEA, ((CasoSwitch) caso).COLUMNA));
                } else {
                    esDefault = true;
                    entornoLocal = new Entorno(e, Entorno.EnumEntorno.SWITCH);
                    resultInstuccionesCaso = ((CasoSwitch) caso).Ejecutar(entornoLocal);
                    if (resulCaso != null) {
                        if (resultInstuccionesCaso instanceof Break) {
                            return null;
                        } else if (resultInstuccionesCaso instanceof Continue) {
                            return resultInstuccionesCaso;
                        } else if (resultInstuccionesCaso instanceof Return) {
                            return resultInstuccionesCaso;
                        } else {
                            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error en instrucciones switch", LINEA, COLUMNA));
                        }
                    }
                }
            }
        }
        return null;
    }
}
