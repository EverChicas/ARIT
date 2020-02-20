/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Archivos;

/**
 *
 * @author chicas
 */
public class Archivo {
    private String Path;
    private String Nombre;
    private String Contenido;

    public Archivo(){
        
    }
    
    public Archivo(String Path, String Nombre, String Contenido) {
        this.Path = Path;
        this.Nombre = Nombre;
        this.Contenido = Contenido;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String Contenido) {
        this.Contenido = Contenido;
    }
            
}
