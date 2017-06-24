/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Omar
 */
public class Material {
    private String codigoMaterial;
    private String descMaterial;
    private double precioMaterial;
    private String unidad;
    private SubClase subclase;
    
    public Material(){}
    
    public Material(String codigoMaterial, String descMaterial, double precioMaterial) {
        this.codigoMaterial = codigoMaterial;
        this.descMaterial = descMaterial;
        this.precioMaterial = precioMaterial;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public SubClase getSubclase() {
        return subclase;
    }

    public void setSubclase(SubClase subclase) {
        this.subclase = subclase;
    }
    
    

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }

    public String getDescMaterial() {
        return descMaterial;
    }

    public void setDescMaterial(String descMaterial) {
        this.descMaterial = descMaterial;
    }

    public double getPrecioMaterial() {
        return precioMaterial;
    }

    public void setPrecioMaterial(double precioMaterial) {
        this.precioMaterial = precioMaterial;
    }
    
    
    
}
