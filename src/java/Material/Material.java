/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Material;

import Control.ControlMaterial;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author consultor3
 */
@WebService(serviceName = "Material")
public class Material {

    /**
     * This is a sample web service operation
     * @param cond
     * @param porCod
     * @return 
     */
    @WebMethod(operationName = "getMaterial")
    public List<Entidades.Material> getMaterial(@WebParam(name = "cond") String cond,@WebParam(name = "porCod") String porCod,@WebParam(name = "idSede") String idSede) {
       Control.ControlMaterial ctrl= new ControlMaterial();
        return  ctrl.getMaterial(cond,porCod,idSede);
    }
    @WebMethod(operationName = "getServicio")
    public List<Entidades.Material> getServicio(@WebParam(name = "cond") String cond,@WebParam(name = "porCod") String porCod) {
       Control.ControlMaterial ctrl= new ControlMaterial();
        return  ctrl.getServicio(cond,porCod);
    }
   
}
