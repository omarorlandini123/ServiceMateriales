/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class Conector {

    private String IP = "10.10.26.11";
    private String puerto = "1521";
    private String SID = "SIMAPRD";
    private String usuario = "consultor4";
    private String password = "consultor4";

    private final static int Conec_OK = 100;
    private final static int Conec_FAIL = 200;

    private int estadoConexion = 0;

    private Connection conn;

    public Conector() throws Exception {

        estadoConexion = Conec_FAIL;
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        conn = DriverManager.getConnection("jdbc:oracle:thin:@(description=(address=(host=" + IP
                + ")(protocol=tcp)(port=" + puerto + "))(connect_data=(sid=" + SID
                + ")))", usuario, password);
        estadoConexion = Conec_OK;
    }

    public ResultSet ejecutarQuery(String query) throws Exception {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }

    public ResultSet ejecutarProcedimiento(Procedimiento procedure) throws Exception {
        ResultSet rs=null;
                
        CallableStatement cst = conn.prepareCall(procedure.getCallable());
        ArrayList<Parametro> paramsIn = procedure.getParametros(Parametro.Param_IN);
        ArrayList<Parametro> paramsINOUT = procedure.getParametros(Parametro.Param_INOUT);
        ArrayList<Parametro> paramsOUT = procedure.getParametros(Parametro.Param_OUT);
        
        paramsIn.addAll(paramsINOUT);
        paramsOUT.addAll(paramsINOUT);
        
        for (Parametro param : paramsIn) {

            switch (param.getSqlType()) {
                case Types.INTEGER:
                    cst.setInt(param.getNombre(), param.getDatoInteger());
                    break;
                case Types.DOUBLE:
                case Types.DECIMAL:
                    cst.setDouble(param.getNombre(), param.getDatoDouble());
                    break;
                case Types.BOOLEAN:
                    cst.setBoolean(param.getNombre(), param.getDatoBoolean());
                    break;
                case Types.VARCHAR:                   
                    cst.setString(param.getNombre(), param.getDatoString());
                    break;
                case Types.DATE:
                    cst.setDate(param.getNombre(), param.getDatoDate());
                    break;
            }

        }
        
        for (Parametro param : paramsOUT) {
           cst.registerOutParameter(param.getNombre(), param.getSqlType());
        }
        
        boolean tieneResultados= cst.execute();
        if(tieneResultados){
            rs=cst.getResultSet();
        }
        
        for (Parametro param : paramsOUT) {
           switch (param.getSqlType()) {
                case Types.INTEGER:
                    param.setDatoInteger(cst.getInt(param.getNombre()));
                    break;
                case Types.DOUBLE:
                case Types.DECIMAL:
                    param.setDatoDouble(cst.getDouble(param.getNombre()));
                    break;
                case Types.BOOLEAN:
                    param.setDatoBoolean(cst.getBoolean(param.getNombre()));
                    break;
                case Types.VARCHAR:                   
                    param.setDatoString(cst.getString(param.getNombre()));
                    break;
                case Types.DATE:
                    param.setDatoDate(cst.getDate(param.getNombre()));
                    break;
            }
        }
        return rs;
    }

    public void CerrarConexion() throws Exception {
        conn.close();
    }

    public int getEstadoConexion() {
        return estadoConexion;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
