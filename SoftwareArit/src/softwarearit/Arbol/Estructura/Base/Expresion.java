/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Arbol.Estructura.Base;

/**
 *
 * @author chicas
 */
public abstract class Expresion extends Nodo{
    
    public Tipo Tipo;
    public Object Valor;
    
    public abstract Expresion getValor(Entorno e);
    
}
