/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Omar
 */
public class Procedimiento {

    private String nombre;
    private String Espacio;
    private ResultSet rpta;
    private ArrayList<Parametro> parametros;

    public Procedimiento() {
        parametros= new ArrayList<>();
    }
    /**
     * Metodo que devuelve la lista de parametros según el tipo (Entrada, salida, o ambos)
     * @param TipoParam el Tipo de parametro In, OUT, INOUT. Usar Clase Parametro "Param_"
     * @return 
     */
    public ArrayList<Parametro> getParametros(int TipoParam){
        ArrayList<Parametro> params= new ArrayList<>();       
        for(Parametro param : parametros){
            if(param.getTipo()==TipoParam){
                params.add(param);
            }
        }
        return params;
    }

    public String getCallable() {
        String callable = "";
        boolean nombreEspacioLleno;
        boolean nombreLleno;
        
        nombreLleno=!nombre.trim().isEmpty();
        nombreEspacioLleno=!Espacio.trim().isEmpty() && nombreLleno;
        
        
        if (nombreEspacioLleno) {
            callable = "{call " + Espacio + "." + nombre + "";
        } else if (nombreLleno) {
            callable = "{call " + nombre + "";

        }
        if (parametros != null && parametros.size() > 0) {
            
                callable = callable + "(";
                for (Parametro param : parametros) {
                    if (!param.getNombre().trim().isEmpty()) {
                        callable = callable + "?,";
                    }
                }
                callable = callable.substring(0, callable.length() - 1);
                callable = callable + ")";
            
        }
        
       if (nombreLleno) {
            callable = callable + "}";

        }
        
        return callable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspacio() {
        return Espacio;
    }

    public void setEspacio(String Espacio) {
        this.Espacio = Espacio;
    }

    public ResultSet getRpta() {
        return rpta;
    }

    public void setRpta(ResultSet rpta) {
        this.rpta = rpta;
    }

    public ArrayList<Parametro> getParametros() {
        return parametros;
    }

    public void setParametros(ArrayList<Parametro> parametros) {
        this.parametros = parametros;
    }

}
