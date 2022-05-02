package logica;

import java.util.ArrayList;
import java.util.Date;

public class ControlFacturas {

    private ArrayList<Factura> facturas = new ArrayList();
    private int proximoNumero;
   
    protected ControlFacturas(){
    
    }
    
    public ArrayList<Factura> getFacturas() {
        return facturas;
    }
    
    public void agregar(Factura unaFactura){
        unaFactura.setNumero(generarProximoNumero());
        unaFactura.asignarFecha();
        unaFactura.bajarStock();
        facturas.add(unaFactura);
    }
    
    public boolean clienteComproProducto(Cliente c, Producto p){
        boolean ret = false;
        for(Factura f: facturas){
            if (f.getCliente().equals(c)&&f.tieneProducto(p)){
                ret = true;
            }
        }
      
        return ret;
    }
    
    public Date clienteFechaUltimaFacturaPorProducto(Cliente c, Producto p){
        Date ret = null;
  
        for(Factura f: facturas){
            if (f.getCliente().equals(c) && f.tieneProducto(p)) {
                ret = f.getFecha();
            }
        }
        return ret;
    }
    
    private int generarProximoNumero(){
        proximoNumero++;
        return proximoNumero;
    }  
}
