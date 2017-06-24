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

public class Clase {
    private int codClase;
    private String desClase;
    
    public Clase(){}
    
    public Clase(int codClase, String desClase) {
        this.codClase = codClase;
        this.desClase = desClase;
    }
    
    

    public int getCodClase() {
        return codClase;
    }

    public void setCodClase(int codClase) {
        this.codClase = codClase;
    }

    public String getDesClase() {
        return desClase;
    }

    public void setDesClase(String desClase) {
        this.desClase = desClase;
    }
    
    
}
