/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Herramientas;

/**
 *
 * @author chicas
 * 
 * clase para pasar cadenas a un valor double, equivalente a la suma ascii
 */
public class StringTo {

    public StringTo() {
    }
    
    /**
     * 
     * @param cadena - cadena tipo string
     * @return double de la suma de los valores ascii de la cadena
     */
    public double StringToDouble(String cadena){
        int valor = 0;
        for(char caracter : cadena.toCharArray()){
            valor += (double)caracter;
        }
        return valor;
    }
}
