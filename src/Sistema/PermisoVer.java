/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Clases.CargoDAO;
import Clases.Funcionario;
import Clases.FuncionarioDAO;
import Clases.Permiso;
import Clases.PermisoDAO;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Optimus
 */
public class PermisoVer extends javax.swing.JPanel {

    /**
     * Creates new form Ver
     */
    private int codigo;
    
    public PermisoVer(int codigo) {
        initComponents();
        DefaultListModel<String> modelo = new DefaultListModel<>();
        
        lstFechas.setModel(modelo);
        this.codigo = codigo;
        Permiso permiso = PermisoDAO.BuscarPermniso(codigo);
        if(permiso != null){
            Funcionario fun = FuncionarioDAO.BuscarFuncionario(permiso.getRutFuncionario());
            String [] nombre = fun.getNombre().split("_");
            txtCodigo.setText(Integer.toString(codigo));
            txtRut.setText(permiso.getRutFuncionario());
            txtNombre.setText(nombre[0]);
            txtApellido.setText(nombre[1]);
            txtCargo.setText(CargoDAO.ObtenerNombre(fun.getCargo()));
            txtFechaEmision.setText(permiso.getFecha());
            txtCantDias.setText(Integer.toString(permiso.getDias()));
            txtMotivo.setText(permiso.getMotivo());
            ArrayList<String> fechas = PermisoDAO.ObtenerFechas(codigo);
            modelo.removeAllElements();
            for (int i = 0; i < fechas.size(); i++) {
                modelo.addElement(TransformarFecha(fechas.get(i)));
            }
        }
        
    }
    
    private String TransformarFecha(String fecha){
        String fechaString = "";
        String [] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto",
            "Septiembre","Octubre","Noviembre","Diciembre"};
        String [] aux = fecha.split("-");
        fechaString = aux[0] + " de " + meses[Integer.parseInt(aux[1]) - 1] + " del " + aux[2];
        return fechaString;
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCantDias = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstFechas = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        txtFechaEmision = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        butAprobar = new javax.swing.JButton();
        butRechazar = new javax.swing.JButton();
        butCerrar = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 16, 0, 16, 0, 16, 0, 16, 0, 16, 0, 16, 0, 16, 0, 16, 0};
        jPanel2Layout.rowHeights = new int[] {0, 14, 0, 14, 0, 14, 0, 14, 0, 14, 0, 14, 0, 14, 0, 14, 0};
        jPanel2.setLayout(jPanel2Layout);

        jLabel1.setText("Código :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(jLabel1, gridBagConstraints);

        txtCodigo.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(txtCodigo, gridBagConstraints);

        jLabel2.setText("RUT Funcionario :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(jLabel2, gridBagConstraints);

        txtRut.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(txtRut, gridBagConstraints);

        jLabel3.setText("Nombres :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(jLabel3, gridBagConstraints);

        txtNombre.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(txtNombre, gridBagConstraints);

        jLabel4.setText("Apellidos :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(jLabel4, gridBagConstraints);

        txtApellido.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(txtApellido, gridBagConstraints);

        jLabel5.setText("Cargo :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(jLabel5, gridBagConstraints);

        txtCargo.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(txtCargo, gridBagConstraints);

        jLabel6.setText("Cantidad Días :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(jLabel6, gridBagConstraints);

        txtCantDias.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(txtCantDias, gridBagConstraints);

        jLabel7.setText("Fechas :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(jLabel7, gridBagConstraints);

        lstFechas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstFechas.setVisibleRowCount(4);
        jScrollPane1.setViewportView(lstFechas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        jLabel8.setText("Motivo :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(jLabel8, gridBagConstraints);

        txtMotivo.setEditable(false);
        txtMotivo.setColumns(20);
        txtMotivo.setRows(5);
        jScrollPane3.setViewportView(txtMotivo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jScrollPane3, gridBagConstraints);

        jLabel9.setText("Fecha Emisión :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel2.add(jLabel9, gridBagConstraints);

        txtFechaEmision.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(txtFechaEmision, gridBagConstraints);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        butAprobar.setText("Aprobar Permiso");
        butAprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAprobarActionPerformed(evt);
            }
        });
        jPanel1.add(butAprobar);

        butRechazar.setText("Rechazar Permiso");
        butRechazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butRechazarActionPerformed(evt);
            }
        });
        jPanel1.add(butRechazar);

        butCerrar.setText("Cerrar");
        butCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(butCerrar);

        add(jPanel1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void butAprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAprobarActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro de aprobar este permiso? ", "Confirmación", JOptionPane.YES_NO_OPTION);
        if(respuesta == JOptionPane.YES_OPTION){
            if(PermisoDAO.AprobarPermiso(codigo)){
                JOptionPane.showMessageDialog(this, "Permiso aprobado exitosamente");
                butAprobar.setEnabled(false);
                butRechazar.setEnabled(false);
            }
            else{
                JOptionPane.showMessageDialog(this, "Error al intentar aprobar permiso");
            }
        }
    }//GEN-LAST:event_butAprobarActionPerformed

    private void butRechazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butRechazarActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro de rechazar este permiso? ", "Confirmación", JOptionPane.YES_NO_OPTION);
        if(respuesta == JOptionPane.YES_OPTION){
            if(PermisoDAO.RechazarPermiso(codigo)){
                JOptionPane.showMessageDialog(this, "Permiso rechazado exitosamente");
                butAprobar.setEnabled(false);
                butRechazar.setEnabled(false);
            }
            else{
                JOptionPane.showMessageDialog(this, "Error al intentar rechazar permiso");
            }
        }
    }//GEN-LAST:event_butRechazarActionPerformed

    private void butCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCerrarActionPerformed
        JDialog padre = (JDialog)SwingUtilities.getWindowAncestor(this);
        padre.dispose();
    }//GEN-LAST:event_butCerrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAprobar;
    private javax.swing.JButton butCerrar;
    private javax.swing.JButton butRechazar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList lstFechas;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCantDias;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFechaEmision;
    private javax.swing.JTextArea txtMotivo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRut;
    // End of variables declaration//GEN-END:variables
}