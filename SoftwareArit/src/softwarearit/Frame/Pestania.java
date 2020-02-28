/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Frame;

import softwarearit.Archivos.Archivo;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

/**
 *
 * @author chicas
 */
public class Pestania {
    private JScrollPane Scroll;
    private RSyntaxTextArea Text;

    public Pestania() {

    }

    public JScrollPane nueva(Archivo archivo) {

        Text = new RSyntaxTextArea();
        Text.setName(archivo.getPath());
        Text.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_RUBY);
        Text.setCodeFoldingEnabled(true);

        Text.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                JTextArea txtArea = (JTextArea) e.getSource();
                int linea = 1;
                int columna = 1;

                try {
                    int caretPosicion = txtArea.getCaretPosition();
                    linea = txtArea.getLineOfOffset(caretPosicion);
                    columna = caretPosicion - txtArea.getLineStartOffset(linea);
                    linea += 1;
                    softwarearit.Frame.Interfaz.showPosition(linea, columna);
                } catch (Exception ex) {
                }
            }
        });
        
        Text.setText(archivo.getContenido());
        Scroll = new RTextScrollPane(Text);        
        return Scroll;

    } 
}
