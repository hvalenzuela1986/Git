/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Clases.Funcionario;
import Clases.FuncionarioDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Optimus
 */
public class FuncionarioEliminar extends EliminarPersona {

    /**
     * Creates new form FuncionarioEliminar
     */
    private FuncionarioDAO funcionario;
    
    public FuncionarioEliminar() {
        super();
        funcionario = new FuncionarioDAO();
        LlenarTabla();
        ModificarTitulos("Funcionario");
    }
    
    @Override
    public void LlenarTabla(){
        DefaultTableModel modelo = getModel();
        ArrayList<Funcionario> listado = new ArrayList<>();
        modelo.setColumnIdentifiers(new String[]{"RUT", "Nombres", "Apellidos", "Fecha de Ingreso"});
        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
        funcionario.ObtenerFuncionarios(listado);
        String [] nombres;
        for (int i = 0; i < listado.size(); i++) {
            nombres = listado.get(i).getNombre().split("_");
            modelo.addRow(new Object[]{listado.get(i).getRut(), nombres[0], nombres[1], listado.get(i).getFechaIngreso()});
        }
    }
    
    @Override
    public void Eliminar(){
        int fila = getNumeroFila();
        if(fila != -1){
            String rut = getValor(fila, 0);
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro de eliminar funcionario RUT = " + rut + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.YES_OPTION){
                if(funcionario.EliminarFuncionario(rut)){
                    JOptionPane.showMessageDialog(this, "Funcionario eliminado exitosamente");
                    LlenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Error al intentar eliminar funcionario");
                }
            }
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
