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
 * @author consultor3
 */
public class SubClase {
    private int codSubClase;
    private String desSubClase;
    private Clase clase;
    public SubClase(){}

    public SubClase(int codSubClase, String desSubClase) {
        this.codSubClase = codSubClase;
        this.desSubClase = desSubClase;
    }

    public SubClase(int codSubClase, String desSubClase, Clase clase) {
        this.codSubClase = codSubClase;
        this.desSubClase = desSubClase;
        this.clase = clase;
    }

    public int getCodSubClase() {
        return codSubClase;
    }

    public void setCodSubClase(int codSubClase) {
        this.codSubClase = codSubClase;
    }

    public String getDesSubClase() {
        return desSubClase;
    }

    public void setDesSubClase(String desSubClase) {
        this.desSubClase = desSubClase;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }
    
    
    
}
