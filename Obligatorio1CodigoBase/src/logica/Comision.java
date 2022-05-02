/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class Comision {

    private String nombre;
    private Date fechaCreacion;
    private float porcentaje;
    private Producto producto;
    private Proveedor proveedor;
    private ArrayList<Factura> facturas = new ArrayList<>();
    private static boolean mostrarInfoCompleta = false;

    public Comision(Proveedor proveedor, Producto producto, String nombre, float porcentaje) {
        this.nombre = nombre;
        this.fechaCreacion = new Date();
        this.proveedor = proveedor;
        this.porcentaje = porcentaje;
        this.producto = producto;

        if (this.validar()) {
            proveedor.agregarComision(this);
            producto.agregarComision(this);
        }
    }
    
    public static boolean getMostrarInfoCompleta(){
        return mostrarInfoCompleta;
    }
    
    public static void setMostrarInfoCompleta(boolean b){
        mostrarInfoCompleta = b;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public Producto getProducto() {
        return producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void asignarFecha() {
        this.setFechaCreacion(new Date());
    }

    public void agregarFactura(Factura f) {
        if (!facturas.contains(f)) {
            this.facturas.add(f);
        }
    }

    public int getTotalUnidadesVendidas() {
        int totalUnidades = 0;
        Factura.setProductoConsultar(producto);
        for (Factura f : facturas) {
            totalUnidades += f.getTotalUnidadesProducto();
        }
        return totalUnidades;
    }

    public float getTotalPagar() {
        float porcentajeValor = porcentaje/100;
        return (porcentajeValor * producto.getPrecio()) * getTotalUnidadesVendidas();
    }

    public boolean validar() {
        return !nombre.isEmpty() && !nombre.isBlank() && producto != null && proveedor != null && porcentaje > 1 && porcentaje < 100;
    }

    @Override
    public String toString() {
        if (!mostrarInfoCompleta) {
            return nombre + "| porcentaje: " + porcentaje + "| producto: " + producto.getNombre();
        } else {
            return nombre + "|" + fechaCreacion + "| producto:" + producto.getNombre() + "| precio: $" + producto.getPrecio()+ "| total unidades vendidas: " + getTotalUnidadesVendidas() + "| porcentaje: %" + porcentaje + "| total a pagar comision: $" + getTotalPagar();
        }
    }

}
