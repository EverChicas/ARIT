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
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class MenorQue extends Expresion{
    Expresion var1;
    Expresion var2;
    
    public MenorQue(int linea,int columna,Expresion var1,Expresion var2){
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.var1 = var1;
        this.var2 = var2;
        generarGrafica();
    }
    
    /**
     * Metodo para generar el codigo del grafo
     */
    private void generarGrafica(){
        this.NOMBRE = Interfaz.GRAFICA_ARBOL.getNombreNodo();
        this.GRAFICA = Interfaz.GRAFICA_ARBOL.generarGraficaExpresionDosHijos("<", this, var1,var2);
    }

    @Override
    public Expresion getValor(Entorno e) {
        TratamientoTipos tratamiento = new TratamientoTipos();
        StringTo valorCadena = new StringTo();
        
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR),"error");
        
        Expresion resul1 = this.var1.getValor(e);
        Expresion resul2 = this.var2.getValor(e);
        
        Tipo.EnumTipo TipoSuperiorValores = tratamiento.tipoSuperior(resul1, resul2);
        
        if(resul1.TIPO.Tipo == Tipo.EnumTipo.STRING && resul2.TIPO.Tipo == Tipo.EnumTipo.STRING){
            resul.TIPO.Tipo = Tipo.EnumTipo.BOOLEAN;
            resul.VALOR.clear(); 
            double cadena1 = valorCadena.StringToDouble(resul1.VALOR.get(0).toString());
            double cadena2 = valorCadena.StringToDouble(resul2.VALOR.get(0).toString());
            resul.VALOR.add(cadena1 < cadena2);
        }else if(TipoSuperiorValores == Tipo.EnumTipo.ENTERO || TipoSuperiorValores == Tipo.EnumTipo.NUMERIC){
            resul.TIPO.Tipo = Tipo.EnumTipo.BOOLEAN;
            resul.VALOR.clear(); 
            resul.VALOR.add(Double.parseDouble(resul1.VALOR.get(0).toString()) < Double.parseDouble(resul2.VALOR.get(0).toString()));
        }else{
            Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO), "Error de tipos para menor que " + resul1.TIPO.Tipo + ", " + resul2.TIPO.Tipo, LINEA, COLUMNA));
        }
        return resul;
    }
}
