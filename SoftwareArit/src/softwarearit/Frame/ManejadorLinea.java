/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.Utilities;
import jflex.Options;

/**
 *
 * @author chicas
 */
public class ManejadorLinea extends JPanel implements CaretListener{
    
    private final static int ALTO = Integer.MAX_VALUE - 1000000;
    private JTextComponent component;
    private int digitoMinimo;
    private int ultimoDigito;
    private int ultimaLinea;
    
    public ManejadorLinea(JTextComponent componente){
        this.component = componente;
        setFont(componente.getFont());
        initComponent();
        componente.addCaretListener(this);
    }
    
    private void initComponent(){
        this.digitoMinimo = 4;
    
        Element root = component.getDocument().getDefaultRootElement();
        int lineas = root.getElementCount();
        int digitos = Math.max(String.valueOf(lineas).length(), digitoMinimo);
        
        if(ultimoDigito != digitos){
            ultimoDigito = digitos;
            FontMetrics fontMetrics = getFontMetrics(getFont());
            int ancho = fontMetrics.charWidth('0') * digitos;
            Insets insets = getInsets();
            int AnchoPreferido = insets.left + insets.right + ancho;
            
            Dimension d = getPreferredSize();
            d.setSize(AnchoPreferido,ALTO);
            setPreferredSize(d);
            setSize(30, getPreferredSize().height);
        }
    }
    
    public String getNumeroLinea(int FilaInicio){
        Element root = component.getDocument().getDefaultRootElement();
        int posicion = root.getElementIndex(FilaInicio);
        Element linea = root.getElement(posicion);
        
        if(linea.getStartOffset() == FilaInicio){
            return String.valueOf(posicion+1);
        }else{
            return "";
        }
    }
    
    private int getY(int FilaInicio, FontMetrics fontMetrics) throws BadLocationException{
        Rectangle r = component.modelToView(FilaInicio);
        int altoLinea = fontMetrics.getHeight();
        int y = r.y + r.height;
        int descent = 0;
        
        if(r.height == altoLinea){
            descent = fontMetrics.getDescent();
        }else{
            Font font = new Font("ARIAL",Font.PLAIN,12);
            descent = Math.max(descent, component.getFontMetrics(font).getDescent());
        }
        return y - descent;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        FontMetrics fontMetrics = component.getFontMetrics(component.getFont());
        Rectangle clip = g.getClipBounds();
        int FilaInicio = component.viewToModel(new Point(0,clip.y));
        int fin = component.viewToModel(new Point(0, clip.y + clip.height));
        
        while(FilaInicio <= fin){
            try{
                String numeroLinea = getNumeroLinea(FilaInicio);
                int y = getY(FilaInicio,fontMetrics);
                g.drawString(numeroLinea, 1, y);
                FilaInicio = Utilities.getRowEnd(component,FilaInicio) + 1;
            } catch (BadLocationException ex) {
                Logger.getLogger(ManejadorLinea.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }
    
    @Override
    public void caretUpdate(CaretEvent e) {
        int positionActual = component.getCaretPosition();
        Element root = component.getDocument().getDefaultRootElement();
        int lineaActual = root.getElementIndex(positionActual);
        
        if(ultimaLinea != lineaActual){
            repaint();
            ultimaLinea = lineaActual;
        }
    }
    
    
}
