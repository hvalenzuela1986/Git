/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import BD.ConexionBD;
import Clases.Usuario;
import static Sistema.Login.ValidarRut;
import static Sistema.Login.miConexion;
import static Sistema.Login.user;
import java.sql.Connection;
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
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public static Connection miConexion;
    public static Usuario user;
    
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        miConexion = ConexionBD.GetConnection();
        if(miConexion == null){
            System.exit(0);
        }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        butEntrar = new javax.swing.JButton();
        butSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(400, 217));
        setMinimumSize(new java.awt.Dimension(400, 217));
        setPreferredSize(new java.awt.Dimension(400, 217));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("Inicio de Sesión");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 16, 0, 16, 0, 16, 0};
        jPanel2Layout.rowHeights = new int[] {0, 14, 0};
        jPanel2.setLayout(jPanel2Layout);

        jLabel2.setText("RUT Usuario :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Contraseña :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(jLabel3, gridBagConstraints);

        txtRut.setMaximumSize(new java.awt.Dimension(150, 30));
        txtRut.setMinimumSize(new java.awt.Dimension(150, 30));
        txtRut.setPreferredSize(new java.awt.Dimension(150, 30));
        txtRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(txtRut, gridBagConstraints);

        txtPassword.setMaximumSize(new java.awt.Dimension(150, 30));
        txtPassword.setMinimumSize(new java.awt.Dimension(150, 30));
        txtPassword.setPreferredSize(new java.awt.Dimension(150, 30));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(txtPassword, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        butEntrar.setText("Ingresar");
        butEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEntrarActionPerformed(evt);
            }
        });
        jPanel3.add(butEntrar);

        butSalir.setText("Salir");
        butSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSalirActionPerformed(evt);
            }
        });
        jPanel3.add(butSalir);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEntrarActionPerformed
        String rut = txtRut.getText();
        rut = rut.replace(".", "");
        String clave = new String(txtPassword.getPassword());
        if(rut.length() == 0){
            JOptionPane.showMessageDialog(null,"Ingrese un RUT de usuario","Error Login",JOptionPane.ERROR_MESSAGE);
            txtRut.requestFocus();
        }
        else if(!ValidarRut(rut)){
            JOptionPane.showMessageDialog(null,"RUT no válido","Error Login",JOptionPane.ERROR_MESSAGE);
            txtRut.requestFocus();
        }
        else if(clave.length() == 0){
            JOptionPane.showMessageDialog(null,"Ingrese una contraseña","Error Login",JOptionPane.ERROR_MESSAGE);
            txtPassword.requestFocus();
        }
        else{
            try {
                PreparedStatement consulta = miConexion.prepareStatement("select * from usuarios where rut = ? and estado = 1");
                consulta.setString(1, rut);
                ResultSet rs = consulta.executeQuery();
                if(rs.next()){
                    if(rs.getString("clave").equals(MD5(clave))){
                        String nombre = rs.getString("nombre");
                        int perfil = rs.getInt("perfil");
                        int estado = rs.getInt("estado");
                        String fechaCreacion = rs.getString("fecha_creacion");
                        user = new Usuario(rut, nombre, clave, perfil, estado, fechaCreacion);
                        dispose();
                        new Menu().setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Usuario no registrado");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_butEntrarActionPerformed

    private void txtRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutActionPerformed
        butEntrar.doClick();
    }//GEN-LAST:event_txtRutActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        butEntrar.doClick();
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void butSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_butSalirActionPerformed

    public String MD5(String md5) {
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(md5.getBytes());
             StringBuilder sb = new StringBuilder();
             for (int i = 0; i < array.length; ++i) {
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
             return sb.toString();
         } catch (java.security.NoSuchAlgorithmException e) {
         }
         return null;
     }
    
    public static boolean ValidarRut(String rut) {
        boolean validacion = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            char dv = rut.charAt(rut.length() - 1);
            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
        }
        catch (java.lang.NumberFormatException e) {
        } 
        catch (Exception e) {
        }
        return validacion;
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butEntrar;
    private javax.swing.JButton butSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtRut;
    // End of variables declaration//GEN-END:variables
}