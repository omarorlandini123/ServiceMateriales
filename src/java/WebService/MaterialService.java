/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;
import Control.ControlMaterial;
import Entidades.Material;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author Omar
 */
@Path("/Material")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MaterialService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Material> getMaterial(@QueryParam("cond") String cond,@QueryParam("porCod") String porCod,@QueryParam("idSede") String idSede){
        Control.ControlMaterial ctrl= new ControlMaterial();
        return  ctrl.getMaterial(cond,porCod,idSede);
    }
}
