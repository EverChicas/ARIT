/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Herramientas;

import softwarearit.Arbol.Expresiones.Expresion;
import softwarearit.Arbol.Valor;

/**
 * Clase para comparar valores se debe de poner compara el tamaño y el valor en
 * cada posicion, asi como el tipo de los mismos
 *
 * @author chicas
 */
public class CompararValor {
    
    public CompararValor() {
    }
    
    public boolean sonIguales(Expresion valor1, Expresion valor2) {
        if (valor1.VALOR.size() == valor2.VALOR.size()) {
            for (int i = 0; i < valor1.VALOR.size(); i++) {
                if (!valor1.VALOR.get(i).toString().equals(valor2.VALOR.get(i).toString())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
