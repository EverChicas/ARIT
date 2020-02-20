/*//GEN-LINE:variables
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwarearit.Frame;

import softwarearit.Analizadores.Lexico;
import softwarearit.Arbol.AST;
import softwarearit.Arbol.Estructura.Base.NodoError;
import softwarearit.Archivos.AbrirArchivo;
import softwarearit.Archivos.Archivo;
import softwarearit.Archivos.Guardar;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author chicas
 */
public class Interfaz extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz
     */
    private final AbrirArchivo ABRIR;
    private final Guardar GUARDAR;
    private final Pestania PESTANIA;
    public static LinkedList<NodoError> LISTA_ERRORES;

    public Interfaz() {
        initComponents();

        ABRIR = new AbrirArchivo();
        PESTANIA = new Pestania();
        GUARDAR = new Guardar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(102, 255, 0));
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Fila :   Columna :");

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Abrir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Guardar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Guardar como");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ventana");

        jMenuItem4.setText("Abrir pestaña");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Cerrar pestaña");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ejecutar");

        jMenuItem6.setText("Ejecutar");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Reporte");

        jMenuItem7.setText("Arbol AST");
        jMenu4.add(jMenuItem7);

        jMenuItem8.setText("Errores");
        jMenu4.add(jMenuItem8);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 647, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane1))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
        );

        pack();
    }// </editor-fold>                        

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
        Archivo tmp = getArchivo();
        GUARDAR.GuardarComo(tmp);
        jTabbedPane1.setTitleAt(jTabbedPane1.getSelectedIndex(), tmp.getNombre());
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        abrirArchivo();
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        Archivo tmp = getArchivo();

        if (tmp.getPath().equals("nuevo")) {
            GUARDAR.GuardarComo(tmp);
            jTabbedPane1.setTitleAt(jTabbedPane1.getSelectedIndex(), tmp.getNombre());
        } else {
            GUARDAR.Guardar(tmp);
        }

    }

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
        Archivo tmp = new Archivo("nuevo", "nueva.java", "");
        JScrollPane agregar = PESTANIA.nueva(tmp);
        jTabbedPane1.addTab(tmp.getNombre(), agregar);
    }

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {
        int index = jTabbedPane1.getSelectedIndex();
        if (index >= 0) {
            jTabbedPane1.remove(index);
        }
    }

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {
        ejecutarInterprete();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    public static javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTextArea jTextArea1;
    // End of variables declaration                   

    /**
     * Muestra la posicion del curso en linea y columna
     *
     * @param linea - linea de posicion del cursor
     * @param columna - columna de posicion del cursor
     */
    public static void showPosition(int linea, int columna) {
        jLabel1.setText("Linea: " + linea + " Columna: " + columna);
    }

    /**
     * Metodo para limpiar la consola de la interfaz
     */
    public static void cleanConsola() {
        jTextArea1.setText("");
    }

    /**
     * Metodo para imprimir text en la terminal, actualmente imprime linea por
     * linea actualizar para que imprima en una sola linea o agregar parametro
     * para sabeer si va con salto de linea
     *
     * @param text - texto para mostrar en la terminal
     */
    public static void printConsola(String text) {
        String tmp = jTextArea1.getText();
        if (tmp.equals("")) {
            tmp = " ";
        } else {
            tmp += "\n ";
        }
        jTextArea1.setText(tmp + text);
    }

    /**
     * Agrega un error a la lista de errores global
     *
     * @param error - error de tipo NodoError
     */
    public static void addError(NodoError error) {
        LISTA_ERRORES.add(error);
        String text = "Error tipo: " + error.getTipoError() + " Descripcion: " + error.Descripcion + " Linea: " + error.Linea + " Columna: " + error.Columna;
        printConsola(text);
    }

    private void abrirArchivo() {
        Archivo tmp = ABRIR.Abrir();
        JScrollPane agregar = PESTANIA.nueva(tmp);
        jTabbedPane1.addTab(tmp.getNombre(), agregar);
    }

    /**
     * @return Archivo metodo para obtener un archivo del la pestania actual
     */
    private Archivo getArchivo() {
        JScrollPane tmp = (JScrollPane) jTabbedPane1.getSelectedComponent();
        JTextArea tmp2 = (JTextArea) tmp.getViewport().getComponent(0);
        Archivo tmp3 = new Archivo(tmp2.getName(), "", tmp2.getText());
        return tmp3;
    }

    /**
     * Metodo para ejecutar el interprete
     */
    private void ejecutarInterprete() {
        JScrollPane tmp = (JScrollPane) jTabbedPane1.getSelectedComponent();
        JTextArea tmp2 = (JTextArea) tmp.getViewport().getComponent(0);
        LISTA_ERRORES = new LinkedList<>();
        AST arbol;
        cleanConsola();

        try {
            Lexico tokens = new Lexico(new BufferedReader(new StringReader(tmp2.getText())));
            Symbol sym = (Symbol) tokens.next_token();
            while (sym.sym != 0) {
                printConsola(String.valueOf(sym.value) + " ->                  " + sym.sym);
                sym = (Symbol) tokens.next_token();
            }
            
            /*
            Lexico lexico = new Lexico(new BufferedReader(new StringReader(tmp2.getText())));
            Sintactico sintactico = new Sintactico(lexico);
            sintactico.parse();

            arbol = sintactico.AST;
            if (arbol != null) {
                //arbol.ejecutar();
            }
            */
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
