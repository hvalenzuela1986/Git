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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Optimus
 */
public class PermisoAprobado extends PermisoListado {

    /**
     * Creates new form PermisoAprobado
     */
    public PermisoAprobado() {
        super();
    }
    
    @Override
    public ArrayList<Permiso> ObtenerLista(){
        return PermisoDAO.ObtenerAprobados();
    }
    
    @Override
    public void VerPermiso(int codigo){
        try {
            createPdf(codigo);
        } catch (ParseException ex) {
            Logger.getLogger(PermisoAprobado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createPdf(int codigo) throws ParseException{
        /*Declaramos documento como un objeto Document
         *Asignamos el tamaño de hoja y los margenes 
        */    
        Document documento = new Document(PageSize.LETTER, 80, 80, 75, 75);
        String archivo = System.getProperty("user.dir")+"/archivo.pdf";
        //writer es declarado como el método utilizado para escribir en el archivo
        PdfWriter writer = null;
        Permiso permiso = PermisoDAO.BuscarPermniso(codigo);
        try {      
            //Obtenemos la instancia del archivo a utilizar
            writer = PdfWriter.getInstance(documento, new FileOutputStream(archivo));
            
            //Agregamos un titulo al archivo
            documento.addTitle("Permiso Administrativo");

            //Abrimos el documento para edición
            documento.open();

            //Declaramos un texto como Paragraph
            //Le podemos dar formado como alineación, tamaño y color a la fuente.
            Paragraph titulo = new Paragraph();
            titulo.setAlignment(Paragraph.ALIGN_RIGHT);
            titulo.setFont(FontFactory.getFont("Sans",12,Font.BOLD));
            titulo.add("REF. : PERMISO ADMINISTRATIVO\n");
            titulo.add("CON GOCE DE REMUNERACIÓN.\n\n");
            String cod = Integer.toString(codigo);
            while(cod.length() < 3){
                cod = "0" + cod;
            }
            titulo.add("AUTORIZACIÓN N° " + cod + "\n");
            titulo.add("PUTÚ, " + TransformarFecha(permiso.getFecha()) + "\n\n\n");

            Paragraph ultimo = new Paragraph();
            ultimo.setAlignment(Paragraph.ALIGN_RIGHT);
            ultimo.setFont(FontFactory.getFont("Sans",10,Font.NORMAL));
            ultimo.add("\n\n\nPara su conocimiento y fines.\n\n\n\n\n\n");
            
            PdfPTable firma = new PdfPTable(2);
            firma.setWidthPercentage(100f);
            firma.setWidths(new int[]{3, 2});
            firma.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            firma.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            firma.addCell(new Phrase(""));
            firma.addCell(new Phrase("Nelly Apolonia Hidalgo Ojeda", FontFactory.getFont("Sans",10,Font.BOLDITALIC)));
            firma.addCell(new Phrase(""));
            firma.addCell(new Phrase("Directora (S)", FontFactory.getFont("Sans",10,Font.ITALIC)));
            firma.addCell(new Phrase(""));
            firma.addCell(new Phrase("Liceo Rural Putú", FontFactory.getFont("Sans",10,Font.ITALIC)));
            
            try {
                //Agregamos el texto al documento
                documento.add(titulo);
                documento.add(tabla(permiso));
                documento.add(ultimo);
                documento.add(firma);
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
    
    public PdfPTable tabla(Permiso permiso) throws DocumentException, ParseException{
        Funcionario fun = FuncionarioDAO.BuscarFuncionario(permiso.getRutFuncionario());
        //Instanciamos una tabla de 3 columnas
        
        PdfPTable tabla = new PdfPTable(2);
        
        tabla.setWidthPercentage(100f);
        tabla.setWidths(new int[]{1, 3});
        
        //Declaramos un objeto para manejar las celdas
        PdfPCell celda = new PdfPCell();
        celda.setBorder(Rectangle.NO_BORDER);
        
        Font negrita = FontFactory.getFont("Sans",10,Font.BOLD);
        Font normal =  FontFactory.getFont("Sans",10,Font.NORMAL);

        tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        tabla.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        tabla.addCell(new Phrase("Visto :",negrita));
        tabla.addCell(new Phrase("La ley 19.070, del Estatuto de los Profesionales "
                + "de la Educación en su Art.36 Inciso 4° y del Decreto "
                + "Alcaldicio N° 1.196/ C del 23/12/92.\n\n\n",normal));
        tabla.addCell(new Phrase("Considerando :",negrita));
        tabla.addCell(new Phrase("La solicitud presentada por el interesado(a).\n\n\n",normal));
        tabla.addCell(new Phrase("Autorizo :",negrita));
        tabla.addCell(new Phrase(permiso.getDias() + " día(s) de Permiso Administrativo con Goce de Remuneraciones.\n\n\n",normal));
        tabla.addCell(new Phrase("RUN :",negrita));
        tabla.addCell(new Phrase(fun.getRut() + ".\n\n\n",normal));
        String [] nombre = fun.getNombre().split("_");
        tabla.addCell(new Phrase("Nombre Funcionario :",negrita));
        tabla.addCell(new Phrase(nombre[0] + " " + nombre[1] + ".\n\n\n",normal));
        tabla.addCell(new Phrase("Cargo :",negrita));
        tabla.addCell(new Phrase(CargoDAO.ObtenerNombre(fun.getCargo()) + " del Liceo Rural Putú.\n\n\n",normal));
        ArrayList<String> fechas = PermisoDAO.ObtenerFechas(permiso.getCodigo());
        tabla.addCell(new Phrase("Por el(los) día(s) :",negrita));
        String lista = "";
        for (int i = 0; i < fechas.size(); i++) {
            lista = lista + "- " + TransformarFecha(fechas.get(i)) + ".\n";
        }
        String anyo = permiso.getFecha().split("-")[2];
        lista = lista + "(Acumulados a la fecha " + PermisoDAO.ObtenerTotal(fun.getRut(), anyo) + " días de 6)";
        for (int i = fechas.size(); i < 6; i++) {
            lista = lista + "\n";
        }
        tabla.addCell(new Phrase(lista + "\n\n",normal));
        return tabla;
    }
    
    private String TransformarFecha(String fecha) throws ParseException{
        String fechaString = "";
        String [] meses = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto",
            "Septiembre","Octubre","Noviembre","Diciembre"};
        String [] dias = {"Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"};
        String [] aux = fecha.split("-");
        GregorianCalendar cal = new GregorianCalendar();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formato.parse(fecha);
        cal.setTime(date);
        int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
        fechaString = dias[diaSemana - 1] + ", " + aux[0] + " de " + meses[Integer.parseInt(aux[1]) - 1] + " del " + aux[2];
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
