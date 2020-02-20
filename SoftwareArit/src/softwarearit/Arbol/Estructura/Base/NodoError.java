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
public class NodoError {
    public TipoError Tipo;
    public String Descripcion;
    public int Linea;
    public int Columna;

    /**
     * 
     * @param Tipo - TipoError
     * @param Descripcion - String
     * @param Linea - int
     * @param Columna  - int
     * 
     * Tomo los parametros para saber que tipo de error
     * se esta reconociendo y en que lugar se reconocio
     */
    public NodoError(TipoError Tipo, String Descripcion, int Linea, int Columna) {
        this.Tipo = Tipo;
        this.Descripcion = Descripcion;
        this.Linea = Linea;
        this.Columna = Columna;
    }
    
    public String getTipoError(){
        if(this.Tipo.TipoError == TipoError.EnumTipoError.LEXICO){
            return "LEXICO";
        }else if(this.Tipo.TipoError == TipoError.EnumTipoError.SINTACTICO){
            return "SINTACTICO";
        }else if(this.Tipo.TipoError == TipoError.EnumTipoError.SEMANTICO){
            return  "SEMANTICO";
        }
        return "DEFAULT";
    }
    
}
