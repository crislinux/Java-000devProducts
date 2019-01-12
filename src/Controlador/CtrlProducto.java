/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladors;

import Modelo.ConsultasProducto;
import Modelo.Producto;
import Vista.frmProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author Cris
 */
public class CtrlProducto implements ActionListener {
    
    private Producto mod;
    private ConsultasProducto modC;
    private frmProducto frm;
    
    public CtrlProducto (Producto mod,ConsultasProducto modC, frmProducto frm)
    {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        
        this.frm.buttonSave.addActionListener(this);
        this.frm.buttonEdit.addActionListener(this);
        this.frm.buttonDelete.addActionListener(this);
        this.frm.buttonClean.addActionListener(this);
        this.frm.buttonSearch.addActionListener(this);
    }
    
    public void iniciar()
    {
        frm.setTitle("Manejo de Productos");
        frm.setLocationRelativeTo(null);
        frm.idProd.setVisible(false);
        mostrardate();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == frm.buttonSave)
        {
            mod.setCodigo(frm.codeProd.getText());
            mod.setNombre(frm.nameProd.getText());
            mod.setPrecio(Double.parseDouble(frm.valueProd.getText()));
            mod.setCantidad(Integer.parseInt(frm.quantityProd.getText()));
            
            if(modC.registrar(mod))
            {
                JOptionPane.showMessageDialog(null,"Producto Registrado.");
                limpiar();
                 mostrardate();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Error al intentar Registrar.");
                limpiar();
            }
        }
        
         if(e.getSource() == frm.buttonEdit)
        {
            mod.setId(Integer.parseInt(frm.idProd.getText()));
            mod.setCodigo(frm.codeProd.getText());
            mod.setNombre(frm.nameProd.getText());
            mod.setPrecio(Double.parseDouble(frm.valueProd.getText()));
            mod.setCantidad(Integer.parseInt(frm.quantityProd.getText()));
            
            if(modC.modificar(mod))
            {
                JOptionPane.showMessageDialog(null,"Producto Modificado");
                limpiar();
                 mostrardate();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Error al intentar Modificar.");
                limpiar();
            }
        }
         
         if(e.getSource() == frm.buttonDelete)
        {
            mod.setId(Integer.parseInt(frm.idProd.getText()));
            
            if(modC.eliminar(mod))
            {
                JOptionPane.showMessageDialog(null,"Producto Eliminado");
                limpiar();
                 mostrardate();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Error al intentar Eliminar.");
                limpiar();
            }
        }
         
         if(e.getSource() == frm.buttonSearch)
        {
            mod.setCodigo(frm.codeProd.getText());

            if(modC.buscar(mod))
            {
                frm.codeProd.setText(mod.getCodigo());
                frm.idProd.setText(String.valueOf(mod.getId()));
                frm.nameProd.setText(mod.getNombre());
                frm.quantityProd.setText(String.valueOf(mod.getCantidad()));
                frm.valueProd.setText(String.valueOf(mod.getPrecio()));
                
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Registro no encontrado.");
                limpiar();
            }
        }
         
        if(e.getSource() == frm.buttonClean)
        {
         limpiar();
        }
    }
    
    public void limpiar()
    {
        frm.codeProd.setText(null);
        frm.idProd.setText(null);
        frm.nameProd.setText(null);
        frm.quantityProd.setText(null);
        frm.valueProd.setText(null);
    }
     public void mostrardate()
    {
        frm.tableDate.setModel(modC.mostrar());
    }
    
}
