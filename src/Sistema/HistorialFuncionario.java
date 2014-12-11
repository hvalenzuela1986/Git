/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Clases.Funcionario;
import Clases.FuncionarioDAO;
import Clases.Permiso;
import Clases.PermisoDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Optimus
 */
public class HistorialFuncionario extends javax.swing.JInternalFrame {

    /**
     * Creates new form HistorialFuncionario
     */
    public HistorialFuncionario() {
        initComponents();
        ObtenerListados();
    }
    
    private void ObtenerListados(){
        cboRut.removeAllItems();
        cboRut.addItem("<Seleccione RUT>");
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        FuncionarioDAO.ObtenerFuncionarios(funcionarios);
        for (int i = 0; i < funcionarios.size(); i++) {
            cboRut.addItem(funcionarios.get(i).getRut());
        }
        cboAnyo.removeAllItems();
        cboAnyo.addItem("<Seleccione Año>");
        cboAnyo.addItem("Todos");
        Calendar cal = Calendar.getInstance();
        for (int i = cal.get(Calendar.YEAR); i >= cal.get(Calendar.YEAR) - 20; i--) {
            cboAnyo.addItem(i);
        }
    }
    
    public void createPdf(String rut, String anyo) throws ParseException{
        /*Declaramos documento como un objeto Document
         *Asignamos el tamaño de hoja y los margenes 
        */    
        Document documento = new Document(PageSize.LETTER, 80, 80, 75, 75);
        String archivo = System.getProperty("user.dir")+"/historial"+ rut + ".pdf";
        //writer es declarado como el método utilizado para escribir en el archivo
        PdfWriter writer = null;
        ArrayList<Permiso> permisos;
        if(anyo.equalsIgnoreCase("todos")){
            permisos = PermisoDAO.PermisosFuncionario(rut);
        }
        else{
            permisos = PermisoDAO.PermisosFuncionarioAnyo(rut, anyo);
        }
        try {      
            //Obtenemos la instancia del archivo a utilizar
            writer = PdfWriter.getInstance(documento, new FileOutputStream(archivo));
            
            //Agregamos un titulo al archivo
            documento.addTitle("Historial Permisos");

            //Abrimos el documento para edición
            documento.open();

            //Declaramos un texto como Paragraph
            //Le podemos dar formado como alineación, tamaño y color a la fuente.
            Paragraph titulo = new Paragraph();
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.setFont(FontFactory.getFont("Sans",12,Font.BOLD));
            titulo.add("Historial Permisos Administrativos\n");
            titulo.add("Por Funcionario\n\n\n");
            
            Funcionario fun = FuncionarioDAO.BuscarFuncionario(rut);
            Paragraph subtitulo = new Paragraph();
            subtitulo.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            subtitulo.setFont(FontFactory.getFont("Sans",10,Font.NORMAL));
            subtitulo.add("RUT Funcionario : " + rut + "\n");
            subtitulo.add("Nombres : " + fun.getNombre().split("_")[0] + "\n");
            subtitulo.add("Apellidos : " + fun.getNombre().split("_")[1] + "\n\n\n");
            
            Paragraph vacio = new Paragraph();
            vacio.setAlignment(Paragraph.ALIGN_CENTER);
            vacio.setFont(FontFactory.getFont("Sans",10,Font.NORMAL));
            vacio.add("No registra permisos durante el período " + anyo + "\n");
            
            try {
                //Agregamos el texto al documento
                documento.add(titulo);
                documento.add(subtitulo);
                if(permisos.size() > 0){
                    documento.add(tabla(permisos));
                }
                else{
                    documento.add(vacio);
                }
            } catch (DocumentException ex) {
                ex.getMessage();
            }

            documento.close(); //Cerramos el documento
            writer.close(); //Cerramos writer
            try {
                File path = new File (archivo);
                Desktop.getDesktop().open(path);
           }catch (IOException ex) {
                ex.printStackTrace();
           }
        } catch (FileNotFoundException|DocumentException ex) {
            ex.getMessage();
        }

    }
    
    public PdfPTable tabla(ArrayList<Permiso> permisos) throws DocumentException, ParseException{
        
        //Instanciamos una tabla de 3 columnas
        String anyo = permisos.get(0).getFecha().split("-")[2];
        PdfPTable tabla = new PdfPTable(5);
        
        tabla.setWidthPercentage(100f);
        tabla.setWidths(new int[]{1, 1, 1, 3, 1});
        
        //Declaramos un objeto para manejar las celdas
        
        Font negrita = FontFactory.getFont("Sans",10,Font.BOLD);
        Font normal =  FontFactory.getFont("Sans",10,Font.NORMAL);
        tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(new Phrase("Código",negrita));
        tabla.addCell(new Phrase("Fecha Emisión",negrita));
        tabla.addCell(new Phrase("Cantidad Días",negrita));
        tabla.addCell(new Phrase("Fechas",negrita));
        tabla.addCell(new Phrase("Resolución",negrita));
        tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.getDefaultCell().setColspan(5);
        tabla.addCell(new Phrase("Permisos año " + anyo + "\n",negrita));
        for (int i = 0; i < permisos.size(); i++) {
            if(!permisos.get(i).getFecha().split("-")[2].equals(anyo)){
                anyo = permisos.get(i).getFecha().split("-")[2];
                tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.getDefaultCell().setColspan(5);
                tabla.addCell(new Phrase("Permisos año " + anyo,negrita));
            }
            tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.getDefaultCell().setColspan(1);
            tabla.addCell(new Phrase(Integer.toString(permisos.get(i).getCodigo()),normal));
            tabla.addCell(new Phrase(permisos.get(i).getFecha(),normal));
            tabla.addCell(new Phrase(Integer.toString(permisos.get(i).getDias()),normal));
            ArrayList<String> fechas = PermisoDAO.ObtenerFechas(permisos.get(i).getCodigo());
            String lista = "";
            for (int j = 0; j < fechas.size(); j++) {
                lista = lista + TransformarFecha(fechas.get(j)) + "\n";
            }
            tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            tabla.addCell(new Phrase(lista,normal));
            tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(new Phrase(permisos.get(i).getResolucion(),normal));
        }
        return tabla;
    }
    
    private String TransformarFecha(String fecha) throws ParseException{
        String fechaString = "";
        String [] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto",
            "Septiembre","Octubre","Noviembre","Diciembre"};
        String [] aux = fecha.split("-");
        GregorianCalendar cal = new GregorianCalendar();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
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

        jLabel1 = new javax.swing.JLabel();
        cboRut = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        butHistorial = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cboAnyo = new javax.swing.JComboBox();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Historial por Funcionario");
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 16, 0, 16, 0, 16, 0, 16, 0};
        layout.rowHeights = new int[] {0, 14, 0, 14, 0, 14, 0, 14, 0};
        getContentPane().setLayout(layout);

        jLabel1.setText("RUT Funcionario :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(jLabel1, gridBagConstraints);

        cboRut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(cboRut, gridBagConstraints);

        jLabel2.setText("Nombres :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(jLabel2, gridBagConstraints);

        txtNombre.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtNombre, gridBagConstraints);

        txtApellido.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtApellido, gridBagConstraints);

        jLabel3.setText("Apellidos :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(jLabel3, gridBagConstraints);

        butHistorial.setText("Ver Historial");
        butHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butHistorialActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        getContentPane().add(butHistorial, gridBagConstraints);

        jLabel4.setText("Año :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(jLabel4, gridBagConstraints);

        cboAnyo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(cboAnyo, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboRutActionPerformed
        int indice = cboRut.getSelectedIndex();
        if(indice > 0){
            String rut = (String)cboRut.getSelectedItem();
            Funcionario func = FuncionarioDAO.BuscarFuncionario(rut);
            if(func != null){
                String [] nombres = func.getNombre().split("_");
                txtNombre.setText(nombres[0]);
                txtApellido.setText(nombres[1]);
            }
        }
        else if(indice == 0){
            txtNombre.setText("");
            txtApellido.setText("");
        }
    }//GEN-LAST:event_cboRutActionPerformed

    private void butHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butHistorialActionPerformed
        if(cboRut.getSelectedIndex() < 1){
            JOptionPane.showMessageDialog(this, "Seleccione un funcionario");
            cboRut.requestFocus();
        }
        else if(cboAnyo.getSelectedIndex() < 1){
            JOptionPane.showMessageDialog(this, "Seleccione un año");
            cboAnyo.requestFocus();
        }
        else{
            String rut = (String)cboRut.getSelectedItem().toString();
            String anyo = (String)cboAnyo.getSelectedItem().toString();
            try {
                createPdf(rut, anyo);
            } catch (ParseException ex) {
                Logger.getLogger(HistorialFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_butHistorialActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butHistorial;
    private javax.swing.JComboBox cboAnyo;
    private javax.swing.JComboBox cboRut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
