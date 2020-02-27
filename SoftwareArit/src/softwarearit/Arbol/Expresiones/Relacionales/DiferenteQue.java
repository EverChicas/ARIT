/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Expresiones.Relacionales;

import softwarearit.Arbol.Estructura.Entorno;
import softwarearit.Arbol.Estructura.Tipo;
import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;

/**
 *
 * @author chicas
 */
public class DiferenteQue extends Expresion{
    
    Expresion var1;
    Expresion var2;
    
    public DiferenteQue(int linea,int columna,Expresion var1,Expresion var2){
        this.LINEA = linea;
        this.COLUMNA = columna;
        this.var1 = var1;
        this.var2 = var2;
    }

    @Override
    public Expresion getValor(Entorno e) {
        Valor resul = new Valor(new Tipo(Tipo.EnumTipo.ERROR), "Error");

        Expresion resul1 = var1.getValor(e);
        Expresion resul2 = var2.getValor(e);

        if (resul1.TIPO.Tipo != Tipo.EnumTipo.ERROR && resul2.TIPO.Tipo != Tipo.EnumTipo.ERROR) {
            resul.TIPO.Tipo = Tipo.EnumTipo.BOOLEAN;
            resul.VALOR.clear();
            if (resul1.VALOR.get(0).equals(resul2.VALOR.get(0).toString())) {
                resul.VALOR.add(false);
            } else {
                resul.VALOR.add(true);
            }
        }

        return resul;
    }
    
}
