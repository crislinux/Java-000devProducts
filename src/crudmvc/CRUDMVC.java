
package crudmvc;

import Modelo.Producto;
import Modelo.ConsultasProducto;
import Vista.frmProducto;
import Controlador.CtrlProducto;

public class CRUDMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Producto mod = new Producto();
       ConsultasProducto modC = new ConsultasProducto();
       frmProducto frm = new frmProducto();
       
       CtrlProducto ctrl = new CtrlProducto(mod,modC,frm);
       ctrl.iniciar();
       frm.setVisible(true);
    }
    
}
