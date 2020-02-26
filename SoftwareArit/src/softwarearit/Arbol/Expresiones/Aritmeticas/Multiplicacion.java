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
import softwarearit.Arbol.Valor;
import softwarearit.Frame.Interfaz;

/**
 *
 * @author chicas
 */
public class Multiplicacion extends Expresion{
    Expresion var1;
    Expresion var2;
    
    public Multiplicacion(int linea,int columna,Expresion var1,Expresion var2){
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.var1 = var1;
        this.var2 = var2;
    }

    @Override
    public Expresion getValor(Entorno e) {
        TratamientoTipos tratamiento = new TratamientoTipos();
        
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR),"error");
        
        Expresion resul1 = this.var1.getValor(e);
        Expresion resul2 = this.var2.getValor(e);
        
        switch (tratamiento.tipoSuperior(resul1, resul2)) {
            case ERROR:
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO),"error de tipos: "+resul1.TIPO.Tipo + ", " + resul2.TIPO.Tipo, this.LINEA, this.COLUMNA));
                break;
            case STRING:
                Interfaz.addError(new NodoError(new TipoError(TipoError.EnumTipoError.SEMANTICO),"error de tipos: "+resul1.TIPO.Tipo + ", " + resul2.TIPO.Tipo, this.LINEA, this.COLUMNA));
                break;
            case ENTERO:
                resul.TIPO = new Tipo(Tipo.EnumTipo.ENTERO);
                resul.VALOR.clear();
                resul.VALOR.add(Integer.parseInt(resul1.VALOR.get(0).toString()) * Integer.parseInt(resul2.VALOR.get(0).toString()));  
                break;
            case NUMERIC:
                resul.TIPO = new Tipo(Tipo.EnumTipo.NUMERIC);
                resul.VALOR.clear();
                resul.VALOR.add(Double.parseDouble(resul1.VALOR.get(0).toString()) * Double.parseDouble(resul2.VALOR.get(0).toString()));  
                break;
        }
        return resul;
    }

}
