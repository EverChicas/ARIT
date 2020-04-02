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
import softwarearit.Arbol.Estructura.AbstractFuncion;
import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Nodo;
import softwarearit.Arbol.Estructura.NodoError;
import softwarearit.Arbol.Estructura.TipoError;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Frame.Interfaz;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Archivos.AbrirNavegador;

/**
 * En la lista de parametros tiene que venir 3 elementos los cuales corresponden
 * a [1]vector de numero [2]vector de labels [3]titulo
 *
 * @author chicas
 */
public class Pie extends AbstractFuncion {

    LinkedList<Nodo> parametros;

    Pie(int linea, int columna, LinkedList<Nodo> parametros) {
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.parametros = parametros;
        generarGrafica();
    }

    private void generarGrafica() {
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaPadreHijosNodos("PIE", this, this.parametros);
    }

    @Override
    public Expresion getValor(Entorno e) {

        if (this.parametros.size() < 3) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error faltan parametros", LINEA, COLUMNA));
        } else if (this.parametros.size() > 3) {
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error sobrepasa numero de parametros", LINEA, COLUMNA));
        } else {
            if (parametros.get(0) instanceof Expresion && parametros.get(1) instanceof Expresion && parametros.get(2) instanceof Expresion) {
                Expresion data = ((Expresion) parametros.get(0)).getValor(e);
                Expresion labels = ((Expresion) parametros.get(1)).getValor(e);
                Expresion titulo = ((Expresion) parametros.get(2)).getValor(e);

                if (data.TIPO.Tipo != Tipo.EnumTipo.ERROR && data.TIPO.Tipo != Tipo.EnumTipo.ERROR && titulo.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                    DefaultPieDataset dataset = createDataSet(e, data, labels);
                    JFreeChart pie = ChartFactory.createPieChart(titulo.VALOR.get(0).toString(), dataset, true, true, false);
                    File pieChart = new File("PieChart.png");
                    try {
                        ChartUtilities.saveChartAsPNG(pieChart, pie, 800, 500);
                        AbrirNavegador.openGrafica("PieChart.png");
                    } catch (IOException ex) {
                        Logger.getLogger(Pie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }

        return null;
    }

    private DefaultPieDataset createDataSet(Entorno e, Expresion data, Expresion labels) {
        DefaultPieDataset retornar = new DefaultPieDataset();
        Expresion valor;
        Expresion label;
        int desconocido = 1;

        if (data.VALOR.size() == labels.VALOR.size()) {
            for (int i = 0; i < data.VALOR.size(); i++) {
                valor = ((Expresion) data.VALOR.get(i)).getValor(e);
                label = ((Expresion) labels.VALOR.get(i)).getValor(e);
                if (valor.TIPO.Tipo != Tipo.EnumTipo.ERROR && label.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                    retornar.setValue(label.VALOR.get(0).toString(), Double.parseDouble(valor.VALOR.get(0).toString()));
                }
            }
        } else if (data.VALOR.size() > labels.VALOR.size()) {
            for (int i = 0; i < data.VALOR.size() - 1; i++) {
                if (i < labels.VALOR.size()) {
                    valor = ((Expresion) data.VALOR.get(i)).getValor(e);
                    label = ((Expresion) labels.VALOR.get(i)).getValor(e);
                    if (valor.TIPO.Tipo != Tipo.EnumTipo.ERROR && label.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                        retornar.setValue(label.VALOR.get(0).toString(), Double.parseDouble(valor.VALOR.get(0).toString()));
                    }
                } else {
                    valor = ((Expresion) data.VALOR.get(i)).getValor(e);
                    if (valor.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
                        Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error nombre de dato no definido", LINEA, COLUMNA));
                        retornar.setValue("DESCONOCIDO " + desconocido, Double.parseDouble(valor.VALOR.get(0).toString()));
                        desconocido++;
                    }
                }
            }
        }
        return retornar;
    }

}
