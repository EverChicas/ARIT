/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Funcion;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.urls.CustomCategoryURLGenerator;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import softwarearit.Arbol.Estructura.AbstractFuncion;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;
import softwarearit.Archivos.AbrirNavegador;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class BarPlot extends AbstractFuncion {

    LinkedList<Nodo> parametros;

    BarPlot(int linea, int columna, LinkedList<Nodo> parametros) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.parametros = parametros;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos("BARPLOT", this, this.parametros);
    }

    @Override
    public Expresion getValor(Entorno e) {
        if (this.parametros.size() < 5) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error faltan parametros", LINEA, COLUMNA));
        } else if (this.parametros.size() > 5) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error sobrepasa numero de parametros", LINEA, COLUMNA));
        } else {
            if (parametros.get(0) instanceof Expresion && parametros.get(1) instanceof Expresion && parametros.get(2) instanceof Expresion) {
                Expresion data = ((Expresion) parametros.get(0)).getValor(e);
                Expresion ejeX = ((Expresion) parametros.get(1)).getValor(e);
                Expresion ejeY = ((Expresion) parametros.get(2)).getValor(e);
                Expresion titulo = ((Expresion) parametros.get(3)).getValor(e);
                Expresion labels = ((Expresion) parametros.get(4)).getValor(e);

                if (data.TIPO.Tipo != Tipo.EnumTipo.ERROR && data.TIPO.Tipo != Tipo.EnumTipo.ERROR && titulo.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                    DefaultCategoryDataset dataset = createDataSet(e, data, ejeX, ejeY);

                    Expresion labelX = null;
                    Expresion labelY = null;
                    JFreeChart barChart = null;
                    if (labels.TIPO.Tipo != Tipo.EnumTipo.C) {
                        labelX = labels;
                        barChart = ChartFactory.createBarChart(titulo.VALOR.get(0).toString(), labelX.VALOR.get(0).toString(), "DESCONOCIDO", dataset, PlotOrientation.VERTICAL, true, true, false);
                    } else {
                        labelX = ((Expresion) labels.VALOR.get(0)).getValor(e);
                        labelY = ((Expresion) labels.VALOR.get(1)).getValor(e);
                        barChart = ChartFactory.createBarChart(titulo.VALOR.get(0).toString(), labelX.VALOR.get(0).toString(), labelY.VALOR.get(0).toString(), dataset, PlotOrientation.VERTICAL, true, true, false);
                    }

                    ((BarRenderer) ((CategoryPlot) barChart.getCategoryPlot()).getRenderer()).setItemMargin(.01);

                    File pieChart = new File("BarChart.png");
                    try {
                        ChartUtilities.saveChartAsPNG(pieChart, barChart, 800, 500);
                        AbrirNavegador.openGrafica("BarChart.png");
                    } catch (IOException ex) {
                        Logger.getLogger(Pie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }

        return null;
    }

    private DefaultCategoryDataset createDataSet(Entorno e, Expresion data, Expresion ejeX, Expresion ejeY) {
        DefaultCategoryDataset retornar = new DefaultCategoryDataset();
        Expresion valor = null;
        Expresion x = null;
        Expresion y = null;
        Expresion valorCero = new Valor(new Tipo(Tipo.EnumTipo.ENTERO), 0);
        Expresion valorCeroX = new Valor(new Tipo(Tipo.EnumTipo.STRING), "DESCONOCIDO");
        int desconocidoX = 1;

        if (data.VALOR.size() == ejeX.VALOR.size()) {
            for (int i = 0; i < data.VALOR.size() - 1; i++) {

                if (ejeX.TIPO.Tipo != Tipo.EnumTipo.C) {
                    valor = data;
                    x = ejeX;
                } else {
                    valor = ((Expresion) data.VALOR.get(i)).getValor(e);
                    x = ((Expresion) ejeX.VALOR.get(i)).getValor(e);
                }

                if (ejeY.TIPO.Tipo != Tipo.EnumTipo.C) {
                    y = ejeY;
                } else {
                    if (ejeY.VALOR.size() <= i) {
                        y = valorCero;
                    } else {
                        y = ((Expresion) ejeY.VALOR.get(i)).getValor(e);
                    }
                }

                if (valor.TIPO.Tipo != Tipo.EnumTipo.ERROR && x.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                    retornar.addValue(Double.parseDouble(valor.VALOR.get(0).toString()), x.VALOR.get(0).toString(), y.VALOR.get(0).toString());
                }
            }
        } else if (data.VALOR.size() > ejeX.VALOR.size()) {
            for (int i = 0; i < data.VALOR.size(); i++) {

                if (data.TIPO.Tipo != Tipo.EnumTipo.C) {
                    valor = data;
                } else {
                    valor = ((Expresion) data.VALOR.get(i)).getValor(e);
                }

                if (ejeX.TIPO.Tipo != Tipo.EnumTipo.C) {
                    x = ejeX;
                } else {
                    if (ejeX.VALOR.size() <= i) {
                        Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error nombre de dato no definido", LINEA, COLUMNA));
                        valorCeroX.VALOR.clear();
                        valorCeroX.VALOR.add("DESCONOCIDO " + desconocidoX);
                        desconocidoX++;
                        x = valorCeroX;
                    } else {
                        x = ((Expresion) ejeX.VALOR.get(i)).getValor(e);
                    }
                }

                if (ejeY.TIPO.Tipo != Tipo.EnumTipo.C) {
                    y = ejeY;
                } else {
                    if (ejeY.VALOR.size() <= i) {
                        y = valorCero;
                    } else {
                        y = ((Expresion) ejeY.VALOR.get(i)).getValor(e);
                    }
                }

                if (valor.TIPO.Tipo != Tipo.EnumTipo.ERROR && x.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                    retornar.addValue(Double.parseDouble(valor.VALOR.get(0).toString()), x.VALOR.get(0).toString(), y.VALOR.get(0).toString());
                }
            }
        }
        return retornar;
    }

}
