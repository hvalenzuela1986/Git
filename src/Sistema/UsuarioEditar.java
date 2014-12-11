/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Clases.Usuario;
import static Sistema.Login.miConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Optimus
 */
public class UsuarioEditar extends javax.swing.JInternalFrame {

    /**
     * Creates new form UsuarioEditar
     */
    public UsuarioEditar() {
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
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ButAceptarNuevoUsuario = new javax.swing.JButton();
        ButCancelarNuevoUsuario = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cboPerfil = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        cboRut = new javax.swing.JComboBox();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Editar Usuario");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setAutoscrolls(true);
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 16, 0, 16, 0};
        jPanel1Layout.rowHeights = new int[] {0, 14, 0, 14, 0, 14, 0, 14, 0, 14, 0};
        jPanel1.setLayout(jPanel1Layout);

        jLabel1.setText("Nombres:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(txtNombre, gridBagConstraints);

        jLabel2.setText("Apellidos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(txtApellido, gridBagConstraints);

        jLabel3.setText("RUT:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel3, gridBagConstraints);

        ButAceptarNuevoUsuario.setText("Guardar Cambios");
        ButAceptarNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButAceptarNuevoUsuarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        jPanel1.add(ButAceptarNuevoUsuario, gridBagConstraints);

        ButCancelarNuevoUsuario.setText("Cancelar");
        ButCancelarNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButCancelarNuevoUsuarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        jPanel1.add(ButCancelarNuevoUsuario, gridBagConstraints);

        jLabel5.setText("Perfil:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(cboPerfil, gridBagConstraints);

        jLabel7.setText("Fecha de Creación:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel7, gridBagConstraints);

        txtFecha.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(txtFecha, gridBagConstraints);

        cboRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(cboRut, gridBagConstraints);

        jScrollPane2.setViewportView(jPanel1);

        getContentPane().add(jScrollPane2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButAceptarNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButAceptarNuevoUsuarioActionPerformed
        if(cboRut.getSelectedIndex() != 0){
            String rut = (String)cboRut.getSelectedItem();
            String nombres = txtNombre.getText();
            String apellidos = txtApellido.getText();
            int perfil = cboPerfil.getSelectedIndex();
            if(nombres.length() == 0){
                JOptionPane.showMessageDialog(this, "Ingrese Nombres");
                txtNombre.requestFocus();
            }
            else{
                if(apellidos.length() == 0){
                   JOptionPane.showMessageDialog(this, "Ingrese Apellidos");
                   txtApellido.requestFocus();
                }
                else{
                    if(perfil == 0){
                        JOptionPane.showMessageDialog(this, "Seleccione un perfil");
                        cboPerfil.requestFocus();
                    }
                    else{
                        Usuario edit = new Usuario();
                        edit.setRut(rut);
                        edit.setNombre(nombres + "_" + apellidos);
                        edit.setPerfil(perfil);
                        if(ActualizarUsuario(edit)){
                            JOptionPane.showMessageDialog(this, "Usuario modificado exitosamente");
                            Inicializar();
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Error al intentar modificar usuario");
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_ButAceptarNuevoUsuarioActionPerformed

    private void cboRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboRutActionPerformed
        int indice = cboRut.getSelectedIndex();
        if(indice != 0){
            ObtenerDatos((String)cboRut.getSelectedItem());
            txtNombre.setEditable(true);
            txtApellido.setEditable(true);
            cboPerfil.setEnabled(true);
        }
    }//GEN-LAST:event_cboRutActionPerformed

    private void ButCancelarNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButCancelarNuevoUsuarioActionPerformed
        Inicializar();
    }//GEN-LAST:event_ButCancelarNuevoUsuarioActionPerformed

    private void ObtenerDatos(String rut){
        Usuario editU = new Usuario();
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from usuarios where rut = ?");
            consulta.setString(1, rut);
            ResultSet rs = consulta.executeQuery();
            if(rs.next()){
                String [] nombres = rs.getString("nombre").split("_");
                txtNombre.setText(nombres[0]);
                txtApellido.setText(nombres[1]);
                cboPerfil.setSelectedIndex(rs.getInt("perfil"));
                txtFecha.setText(rs.getString("fecha_creacion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioNuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean ActualizarUsuario(Usuario edit){
        try {
            String insert = "update usuarios set nombre=?, perfil=? where rut=?";
            PreparedStatement consulta = miConexion.prepareStatement(insert);
            consulta.setString(1, edit.getNombre());
            consulta.setInt(2, edit.getPerfil());
            consulta.setString(3, edit.getRut());
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    private void Inicializar(){
        txtNombre.setText("");
        txtNombre.setEditable(false);
        txtApellido.setText("");
        txtApellido.setEditable(false);
        cboPerfil.setEnabled(false);
        ObtenerPerfiles();
        txtFecha.setText("");
        ObtenerUsuarios();
    }
    
    private void ObtenerUsuarios(){
        cboRut.removeAllItems();
        cboRut.addItem("<Seleccione RUT>");
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select rut from usuarios where estado = 1");
            ResultSet rs = consulta.executeQuery();
            while(rs.next()){
                cboRut.addItem(rs.getString("rut"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioNuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void ObtenerPerfiles(){
        cboPerfil.removeAllItems();
        try {
            PreparedStatement consulta = miConexion.prepareStatement("select * from perfiles order by codigo");
            ResultSet rs = consulta.executeQuery();
            cboPerfil.addItem("<Seleccione perfil>");
            while(rs.next()){
                cboPerfil.addItem(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioNuevo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButAceptarNuevoUsuario;
    private javax.swing.JButton ButCancelarNuevoUsuario;
    private javax.swing.JComboBox cboPerfil;
    private javax.swing.JComboBox cboRut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
