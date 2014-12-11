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
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Optimus
 */
public class HistorialGeneral extends javax.swing.JInternalFrame {

    /**
     * Creates new form HistorialGeneral
     */
    public HistorialGeneral() {
        initComponents();
    }
    
    public void createPdf() throws ParseException{
        /*Declaramos documento como un objeto Document
         *Asignamos el tamaño de hoja y los margenes 
        */    
        Document documento = new Document(PageSize.LETTER, 80, 80, 75, 75);
        String archivo = System.getProperty("user.dir")+"/historial_general.pdf";
        //writer es declarado como el método utilizado para escribir en el archivo
        PdfWriter writer = null;
        ArrayList<Permiso> permisos = PermisoDAO.ObtenerPermisos();
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
            titulo.add("General\n\n\n");
            
            Paragraph vacio = new Paragraph();
            vacio.setAlignment(Paragraph.ALIGN_CENTER);
            vacio.setFont(FontFactory.getFont("Sans",10,Font.NORMAL));
            vacio.add("No se registran permisos administrativos\n");
            
            try {
                //Agregamos el texto al documento
                documento.add(titulo);
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
        PdfPTable tabla = new PdfPTable(7);
        
        tabla.setWidthPercentage(100f);
        tabla.setWidths(new int[]{1, 2, 2, 3, 1, 2, 2});
        
        //Declaramos un objeto para manejar las celdas
        
        Font negrita = FontFactory.getFont("Sans",10,Font.BOLD);
        Font normal =  FontFactory.getFont("Sans",10,Font.NORMAL);
        tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(new Phrase("Cód.",negrita));
        tabla.addCell(new Phrase("Fecha",negrita));
        tabla.addCell(new Phrase("RUT",negrita));
        tabla.addCell(new Phrase("Nombre",negrita));
        tabla.addCell(new Phrase("Cant. Días",negrita));
        tabla.addCell(new Phrase("Fechas",negrita));
        tabla.addCell(new Phrase("Resolución",negrita));
        tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.getDefaultCell().setColspan(7);
        tabla.addCell(new Phrase("Permisos año " + anyo + "\n",negrita));
        for (int i = 0; i < permisos.size(); i++) {
            if(!permisos.get(i).getFecha().split("-")[2].equals(anyo)){
                anyo = permisos.get(i).getFecha().split("-")[2];
                tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.getDefaultCell().setColspan(7);
                tabla.addCell(new Phrase("Permisos año " + anyo,negrita));
            }
            tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.getDefaultCell().setColspan(1);
            tabla.addCell(new Phrase(Integer.toString(permisos.get(i).getCodigo()),normal));
            tabla.addCell(new Phrase(permisos.get(i).getFecha(),normal));
            Funcionario fun = FuncionarioDAO.BuscarFuncionario(permisos.get(i).getRutFuncionario());
            tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            tabla.addCell(new Phrase(fun.getRut(),normal));
            tabla.addCell(new Phrase(fun.getNombre().replaceFirst("_", " "),normal));
            tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(new Phrase(Integer.toString(permisos.get(i).getDias()),normal));
            ArrayList<String> fechas = PermisoDAO.ObtenerFechas(permisos.get(i).getCodigo());
            String lista = "";
            for (int j = 0; j < fechas.size(); j++) {
                lista = lista + fechas.get(j) + "\n";
            }
            tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            tabla.addCell(new Phrase(lista,normal));
            tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(new Phrase(permisos.get(i).getResolucion(),normal));
        }
        return tabla;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        butHistorial = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Historial General");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        butHistorial.setText("Ver Historial General");
        butHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butHistorialActionPerformed(evt);
            }
        });
        getContentPane().add(butHistorial, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butHistorialActionPerformed
        try {
            createPdf();
        } catch (ParseException ex) {
            Logger.getLogger(HistorialGeneral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_butHistorialActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butHistorial;
    // End of variables declaration//GEN-END:variables
}
