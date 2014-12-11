/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import static Sistema.Login.miConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Optimus
 */
public class PerfilEliminar extends javax.swing.JInternalFrame {

    /**
     * Creates new form NuevoPerfil
     */
    public PerfilEliminar() {
        initComponents();
        Inicializar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        butEliminar = new javax.swing.JButton();
        ButCancelarNuevoUsuario = new javax.swing.JButton();
        cboPerfil = new javax.swing.JComboBox();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Eliminar Perfil");
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setAutoscrolls(true);
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 16, 0, 16, 0};
        jPanel1Layout.rowHeights = new int[] {0, 14, 0, 14, 0, 14, 0};
        jPanel1.setLayout(jPanel1Layout);

        jLabel1.setText("Nombre Perfil:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jLabel1, gridBagConstraints);

        butEliminar.setText("Eliminar Perfil");
        butEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        jPanel1.add(butEliminar, gridBagConstraints);

        ButCancelarNuevoUsuario.setText("Cancelar");
        ButCancelarNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButCancelarNuevoUsuarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        jPanel1.add(ButCancelarNuevoUsuario, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(cboPerfil, gridBagConstraints);

        jScrollPane2.setViewportView(jPanel1);

        getContentPane().add(jScrollPane2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEliminarActionPerformed
        int codigo = cboPerfil.getSelectedIndex();
        String nombre = (String)cboPerfil.getSelectedItem();
        if(codigo < 1){
            JOptionPane.showMessageDialog(this, "Seleccione un perfil");
            cboPerfil.requestFocus();
        }
        else{
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro de eliminar perfil " + nombre + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.YES_OPTION){
                if(EliminarPerfil(codigo)){
                    JOptionPane.showMessageDialog(this, "Perfil modificado exitosamente");
                    Inicializar();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Error al intentar modificar perfil");
                }
            }
        }
    }//GEN-LAST:event_butEliminarActionPerformed

    private void ButCancelarNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButCancelarNuevoUsuarioActionPerformed
        Inicializar();
    }//GEN-LAST:event_ButCancelarNuevoUsuarioActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        ObtenerPerfiles();
    }//GEN-LAST:event_formFocusGained

    private boolean EliminarPerfil(int codigo){
        try {
            String delete = "delete from perfiles where codigo=?";
            PreparedStatement consulta = miConexion.prepareStatement(delete);
            consulta.setInt(1, codigo);
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    private void Inicializar(){
        ObtenerPerfiles();
    }
    
    private void ObtenerPerfiles(){
        cboPerfil.removeAllItems();
        cboPerfil.addItem("<Seleccione perfil>");
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from perfiles order by codigo");
            ResultSet rs = consulta.executeQuery();
            while(rs.next()){
                cboPerfil.addItem(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioNuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButCancelarNuevoUsuario;
    private javax.swing.JButton butEliminar;
    private javax.swing.JComboBox cboPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
