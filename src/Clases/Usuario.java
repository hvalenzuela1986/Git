/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Optimus
 */
public class Usuario {
    private String rut;
    private String nombre;
    private String clave;
    private int perfil;
    private int estado;
    private String fechaCreacion;

    public Usuario() {
    }
    
    public Usuario(String rut, String nombre, String fechaCreacion) {
        this.rut = rut;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario(String rut, String nombre, String clave, int perfil, int estado, String fechaCreacion) {
        this.rut = rut;
        this.nombre = nombre;
        this.clave = MD5(clave);
        this.perfil = perfil;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
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
}
