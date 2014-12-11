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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Optimus
 */
public class UsuarioNuevo extends javax.swing.JInternalFrame {

    /**
     * Creates new form UsuarioNuevo
     */
    public UsuarioNuevo() {
        initComponents();
        Inicializar();
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
    
    private boolean GuardarUsuario(Usuario nuevo){
        try {
            String insert = "insert into usuarios values(?,?,?,?,?,?)";
            PreparedStatement consulta = miConexion.prepareStatement(insert);
            consulta.setString(1, nuevo.getRut());
            consulta.setString(2, nuevo.getNombre());
            consulta.setString(3, nuevo.getClave());
            consulta.setInt(4, nuevo.getPerfil());
            consulta.setInt(5, nuevo.getEstado());
            consulta.setString(6, nuevo.getFechaCreacion());
            consulta.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    private void Inicializar() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtRut.setText("");
        txtClave.setText("");
        ObtenerPerfiles();
        cboPerfil.setSelectedIndex(0);
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        txtFecha.setText(formato.format(fecha));
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
        jLabel4 = new javax.swing.JLabel();
        ButCancelarNuevoUsuario = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cboPerfil = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtClave = new javax.swing.JPasswordField();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Nuevo Usuario");
        setDesktopIcon(getDesktopIcon());
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setAutoscrolls(true);
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 16, 0, 16, 0};
        jPanel1Layout.rowHeights = new int[] {0, 14, 0, 14, 0, 14, 0, 14, 0, 14, 0, 14, 0, 14, 0};
        jPanel1.setLayout(jPanel1Layout);

        jLabel1.setText("Nombres:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(txtNombre, gridBagConstraints);

        jLabel2.setText("Apellidos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(txtApellido, gridBagConstraints);

        jLabel3.setText("Rut:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel3, gridBagConstraints);

        ButAceptarNuevoUsuario.setText("Aceptar");
        ButAceptarNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButAceptarNuevoUsuarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        jPanel1.add(ButAceptarNuevoUsuario, gridBagConstraints);

        jLabel4.setText("Contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel4, gridBagConstraints);

        ButCancelarNuevoUsuario.setText("Cancelar");
        ButCancelarNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButCancelarNuevoUsuarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        jPanel1.add(ButCancelarNuevoUsuario, gridBagConstraints);

        jLabel5.setText("Perfil:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(cboPerfil, gridBagConstraints);

        jLabel7.setText("Fecha de Creación:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(txtRut, gridBagConstraints);

        txtFecha.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(txtFecha, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(txtClave, gridBagConstraints);

        jScrollPane2.setViewportView(jPanel1);

        getContentPane().add(jScrollPane2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButAceptarNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButAceptarNuevoUsuarioActionPerformed
        String nombres = txtNombre.getText();
        String apellidos = txtApellido.getText();
        String rut = txtRut.getText();
        String clave = new String(txtClave.getPassword());
        int perfil = cboPerfil.getSelectedIndex();
        int estado = 1;
        String fecha = txtFecha.getText();
        if(nombres.length() == 0){
            JOptionPane.showMessageDialog(this, "Ingrese Nombres");
            txtNombre.requestFocus();
        }
        else if(apellidos.length() == 0){
            JOptionPane.showMessageDialog(this, "Ingrese Apellidos");
            txtApellido.requestFocus();
        }
        else if(rut.length() == 0){
            JOptionPane.showMessageDialog(this, "Ingrese RUT");
            txtRut.requestFocus();
        }
        else if(!Login.ValidarRut(rut)){
            JOptionPane.showMessageDialog(this, "RUT no válido");
            txtRut.requestFocus();
        }
        else if(clave.length() == 0){
            JOptionPane.showMessageDialog(this, "Ingrese contraseña");
            txtClave.requestFocus();
        }
        else if(perfil == 0){
            JOptionPane.showMessageDialog(this, "Seleccione un perfil");
            cboPerfil.requestFocus();
        }
        else{
            Usuario nuevo = new Usuario(rut, nombres + "_" + apellidos, clave, perfil, estado, fecha);
            if(GuardarUsuario(nuevo)){
                JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente");
                Inicializar();
            }
            else{
                JOptionPane.showMessageDialog(this, "Error al intentar guardar usuario");
            }
        }
    }//GEN-LAST:event_ButAceptarNuevoUsuarioActionPerformed

    
    private void ButCancelarNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButCancelarNuevoUsuarioActionPerformed
        Inicializar();
    }//GEN-LAST:event_ButCancelarNuevoUsuarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButAceptarNuevoUsuario;
    private javax.swing.JButton ButCancelarNuevoUsuario;
    private javax.swing.JComboBox cboPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRut;
    // End of variables declaration//GEN-END:variables

}