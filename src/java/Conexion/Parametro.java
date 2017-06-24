/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Date;

/**
 *
 * @author Omar
 */
public class Parametro {
    
    public static final int Param_IN=1;
    public static final int Param_OUT=2;
    public static final int Param_INOUT=3;
    
    private String nombre;
    private double longitud;
    private int tipo;
    private int SqlType;
    
    private String datoString;
    private Date datoDate;
    private Double datoDouble;
    private Integer datoInteger;
    private Boolean datoBoolean;
    
    public Parametro() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

 
    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getSqlType() {
        return SqlType;
    }

    public void setSqlType(int SqlType) {
        this.SqlType = SqlType;
    }

    public String getDatoString() {
        return datoString;
    }

    public void setDatoString(String datoString) {
        this.datoString = datoString;
    }

    public Date getDatoDate() {
        return datoDate;
    }

    public void setDatoDate(Date datoDate) {
        this.datoDate = datoDate;
    }

    public Double getDatoDouble() {
        return datoDouble;
    }

    public void setDatoDouble(Double datoDouble) {
        this.datoDouble = datoDouble;
    }

    public Integer getDatoInteger() {
        return datoInteger;
    }

    public void setDatoInteger(Integer datoInteger) {
        this.datoInteger = datoInteger;
    }

    public Boolean getDatoBoolean() {
        return datoBoolean;
    }

    public void setDatoBoolean(Boolean datoBoolean) {
        this.datoBoolean = datoBoolean;
    }
    
    
}
