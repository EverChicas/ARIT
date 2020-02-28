/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Archivos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author chicas
 */
public class Guardar {

    public Guardar() {

    }

    public void Guardar(Archivo archivo) {
        try {
            FileWriter save = new FileWriter(archivo.getPath());
            save.write(archivo.getContenido());
            save.close();
            JOptionPane.showMessageDialog(null, "Guardado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error no se puedo guardar el archivo", "Informacion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void GuardarComo(Archivo archivo) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/home/me/Desktop"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter save = new FileWriter(chooser.getSelectedFile() + ".java");
                save.write(archivo.getContenido());
                save.close();

                Path name = Paths.get(chooser.getSelectedFile() + ".java");

                archivo.setPath(chooser.getSelectedFile() + ".java");
                archivo.setNombre(name.getFileName().toString());

                JOptionPane.showMessageDialog(null, "Guardado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
            }
        }
    }

    public void GuardarDot(Archivo archivo) {
        try {
            File file = new File(archivo.getNombre());
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter save = new FileWriter(archivo.getNombre());
            save.write(archivo.getContenido());
            save.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error no se puedo guardar el archivo", "Informacion", JOptionPane.ERROR_MESSAGE);
        }
    }

}
