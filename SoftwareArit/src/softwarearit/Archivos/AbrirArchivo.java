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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AbrirArchivo {
    
    public AbrirArchivo(){
    
    }
    
    public Archivo Abrir(){
        Archivo ArchivoRetornar = null;
        String cadena = "";
        String aux = "";
        
        try{
            JFileChooser file = new JFileChooser();
            file.setFileFilter(new FileNameExtensionFilter("*.java","java"));
            file.showOpenDialog(file);
            File archivo = file.getSelectedFile();
                
            if(archivo != null){
                
                FileReader archivos = new FileReader(archivo);
                try (BufferedReader leer = new BufferedReader(archivos)) {
                    while((aux = leer.readLine()) != null){
                        cadena += aux + "\n";
                    }
                    
                    ArchivoRetornar = new Archivo(archivo.getPath(),archivo.getName(),cadena);
                    return ArchivoRetornar;
                    
                }catch(IOException e){
                    
                }
            }
        }catch(IOException e){
            softwarearit.Frame.Interfaz.printConsola("Error al abrir el archivo");
        }
        return ArchivoRetornar;
    }
    
}

