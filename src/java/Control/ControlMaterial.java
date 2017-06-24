/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Conexion.Conector;
import Entidades.Clase;
import Entidades.Material;
import Entidades.SubClase;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Omar
 */
public class ControlMaterial {
    
    private String Sololetras(String text){
        String cadenaValida="abcdefghijklmnñopqrstuvwxyzABCDEFGHIJLMNÑOPQRSTUVWXYZ1234567890";    
        char[] valores= cadenaValida.toCharArray();
        char[] texto=text.toCharArray();
        String stringRpta="";
        for(int y=0;y<texto.length;y++){
            for(int p=0;p<valores.length;p++){
                if(String.valueOf(texto[y]).contains(String.valueOf(valores[p]))){
                    stringRpta=stringRpta+String.valueOf(texto[y]);
                    p=valores.length+1;
                }
            }
        }
        return stringRpta;
    }
    

    public List<Material> getMaterial(String cond,String porCod,String idSede) {
        List<Material> materiales = new ArrayList<>();
        String[]TOKS=cond.split(" ");
        String condicion=" ";
       if(TOKS.length>0){
           condicion="(";
            for (String TOKS1 : TOKS) {                
                if(!Sololetras(TOKS1).trim().isEmpty()){
                    condicion = condicion+" A.DES_DET LIKE '%" + Sololetras(TOKS1) + "%' OR";
                }
            }
           //condicion=condicion.substring(0, condicion.length()-2);
           condicion=condicion+"  A.COD_MAT LIKE '%"+cond+"%'";
           condicion=condicion+" ) AND ";
       }else{
            condicion="  A.COD_MAT='abcdef' ";
       }
       if (porCod.contains("0")){
           
                condicion=condicion+"  B.COD_SBC=0 AND B.EST_CLA='ACT' " +
                        " AND SUBSTR(A.COD_MAT,1,2)=B.COD_CLA " +
                        " AND D.COD_MAT=A.COD_MAT" +
                        "  AND C.EST_CLA='ACT' " +
                        "  AND SUBSTR(A.COD_MAT,1,2)=C.COD_CLA" +
                        " AND SUBSTR(A.COD_MAT,3,2)=C.COD_SBC  " +
                        " and  d.cod_ceo is not null and rownum<=20 and D.FEC_ULT_SAL is not null and d.cod_ceo="+idSede +
                        "  group by A.COD_MAT,A.DES_DET ,A.UND_MED,B.COD_CLA,B.DES_SBC,C.COD_SBC ,C.DES_SBC,NVL(PRC_PMD_NSO_GRA,PRC_UNT_NSO),D.FEC_ULT_SAL "
                        + " order by a.COD_MAT";
       }else  if (porCod.contains("1")){
            condicion="  A.COD_MAT='"+cond+"' and B.COD_SBC=0 AND B.EST_CLA='ACT' " +
                        " AND SUBSTR(A.COD_MAT,1,2)=B.COD_CLA " +
                        " AND D.COD_MAT=A.COD_MAT" +
                        "  AND C.EST_CLA='ACT' " +
                        "  AND SUBSTR(A.COD_MAT,1,2)=C.COD_CLA" +
                        " AND SUBSTR(A.COD_MAT,3,2)=C.COD_SBC  " +
                        " and  d.cod_ceo is not null and rownum<=20 and D.FEC_ULT_SAL is not null and d.cod_ceo="+idSede +
                        "  group by A.COD_MAT,A.DES_DET ,A.UND_MED,B.COD_CLA,B.DES_SBC,C.COD_SBC ,C.DES_SBC,NVL(PRC_PMD_NSO_GRA,PRC_UNT_NSO),D.FEC_ULT_SAL "
                    + "order by  D.FEC_ULT_SAL"; 
       }
        condicion=condicion.toUpperCase();
        try{
            Conector con = new Conector();
            Statement st;
            String query=" Select distinct A.COD_MAT,A.DES_DET ,A.UND_MED,B.COD_CLA,B.DES_SBC,C.COD_SBC CC,C.DES_SBC DD,NVL(PRC_PMD_NSO_GRA,PRC_UNT_NSO) PRECIO , D.FEC_ULT_SAL" +
                        " FROM PD_CAT_MAT A ,PD_CLA_SBC  B  ,PD_CLA_SBC  C,pd_mat_ceo D " +
                        " WHERE "+condicion;
            System.out.println(query);
            try (ResultSet rs = con.ejecutarQuery(query)) {
                while (rs.next()) {
                    Material mat = new Material();
                    mat.setCodigoMaterial(rs.getString("cod_mat"));
                    mat.setDescMaterial(rs.getString("des_det"));
                    mat.setPrecioMaterial(Double.parseDouble(rs.getString("PRECIO")));
                    mat.setUnidad(rs.getString("UND_MED"));
                    Clase clas= new Clase(Integer.parseInt(rs.getString("COD_CLA")),rs.getString("DES_SBC"));
                    SubClase subc = new SubClase(Integer.parseInt(rs.getString("CC")),rs.getString("DD"),clas);
                    mat.setSubclase(subc);
                    materiales.add(mat);
                }   st = rs.getStatement();
            }
            st.close();
            con.CerrarConexion();

        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
        }
       // materiales.add(new Material("COD-00123", "material de prueba1", 0));
        //materiales.add(new Material("COD-00124", "material de prueba2", 0));

        return materiales;
    }

    public List<Material> getServicio(String cond, String porCod) {
    
         List<Material> materiales = new ArrayList<>();
        String[]TOKS=cond.split(" ");
        String condicion=" ";
       if(TOKS.length>0){
           condicion="(";
            for (String TOKS1 : TOKS) {                
                if(!Sololetras(TOKS1).trim().isEmpty()){
                    condicion = condicion+" A.DES_ABR LIKE '%" + Sololetras(TOKS1) + "%' OR";
                }
            }
           //condicion=condicion.substring(0, condicion.length()-2);
           condicion=condicion+"  A.COD_PRD='"+cond+"'";
           condicion=condicion+" ) AND ";
       }else{
            condicion="  A.COD_PRD='abcdef' ";
       }
       if (porCod.contains("0")){
           
                condicion=condicion+"  substr(A.cod_PRD,1,2)='SR' and A.est_ATl='ACT' and A.tip_prd='SER' AND " +
                                    " B.COD_PRD=A.COD_PRD " +
                                    " GROUP BY A.COD_PRD,A.DES_ABR " +
                                    " ORDER BY A.DES_ABR ";
                
       }else  if (porCod.contains("1")){
            condicion="  A.COD_PRD='"+cond+"' and substr(A.cod_PRD,1,2)='SR' and A.est_ATl='ACT' and A.tip_prd='SER' AND " +
                                    " B.COD_PRD=A.COD_PRD " +
                                    " GROUP BY A.COD_PRD,A.DES_ABR " +
                                    " ORDER BY A.DES_ABR "; 
       }
        condicion=condicion.toUpperCase();
        try{
            Conector con = new Conector();
            Statement st;
            String query=   " select A.COD_PRD,A.DES_ABR,MAX(NVL(B.PRC_REP_NSO,1)) PRECIO " +
                            " from pd_prd_dat_gen A,pd_prd_ceo B " +
                            " where "+condicion;
            System.out.println(query);
            try (ResultSet rs = con.ejecutarQuery(query)) {
                while (rs.next()) {
                    Material mat = new Material();
                    mat.setCodigoMaterial(rs.getString("COD_PRD"));
                    mat.setDescMaterial(rs.getString("DES_ABR"));
                    mat.setPrecioMaterial(Double.parseDouble(rs.getString("PRECIO")));
                    mat.setUnidad("UNID");
                    Clase clas= new Clase(0,"SERVICIOS");
                    SubClase subc = new SubClase(0,"SERVICIOS",clas);
                    mat.setSubclase(subc);
                    materiales.add(mat);
                }   st = rs.getStatement();
            }
            st.close();
            con.CerrarConexion();

        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
        }
        // materiales.add(new Material("COD-00123", "material de prueba1", 0));
        //materiales.add(new Material("COD-00124", "material de prueba2", 0));

        return materiales;
        
    }
}
