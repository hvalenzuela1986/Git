/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Optimus
 */
public class EliminarPersona extends javax.swing.JInternalFrame {

    /**
     * Creates new form UsuarioEliminar
     */
    public EliminarPersona() {
        initComponents();
    }
    
    public void ModificarTitulos(String nombre){
        setTitle("Eliminar " + nombre);
        butEliminar.setText("Eliminar " + nombre);
    }
    
    public void LlenarTabla(){
        
    }
    
    public void Eliminar(){
        
    }
    
    public DefaultTableModel getModel(){
        return (DefaultTableModel) tabPersona.getModel();
    }
    
    public int getNumeroFila(){
        return tabPersona.getSelectedRow();
    }
    
    public String getValor(int fila, int columna){
        return tabPersona.getValueAt(fila, columna).toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabPersona = new javax.swing.JTable();
        butEliminar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Eliminar");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        tabPersona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "RUT", "Nombres", "Apellidos", "Fecha de Creación"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabPersona);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        butEliminar.setText("Eliminar");
        butEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(butEliminar, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_butEliminarActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        
    }//GEN-LAST:event_formInternalFrameClosed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butEliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabPersona;
    // End of variables declaration//GEN-END:variables
}
