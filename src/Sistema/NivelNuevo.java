/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Clases.Nivel;
import Clases.NivelDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Optimus
 */
public class NivelNuevo extends Nuevo {

    /**
     * Creates new form NivelNuevo
     */
    private NivelDAO nivel;
    
    public NivelNuevo() {
        super();
        ModificarTitulos("Nivel");
        nivel = new NivelDAO();
    }
    
    @Override
    public void Guardar(){
        int codigo = nivel.Codigo();
        String nombre = getNombre();
        if(nombre.length() == 0){
            JOptionPane.showMessageDialog(this, "Ingrese Nombre Nivel");
        }
        else{
            Nivel nuevoN = new Nivel(codigo, nombre);
            if(nivel.Guardar(nuevoN)){
                JOptionPane.showMessageDialog(this, "Nuevo nivel guardado exitosamente");
            }
            else{
                JOptionPane.showMessageDialog(this, "Error al intentar guardar nuevo nivel");
            }
        }
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
