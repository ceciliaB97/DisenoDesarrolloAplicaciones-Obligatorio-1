package logica;

import java.util.ArrayList;

public class Proveedor {

    private String nombre;
    private ArrayList<Producto> productos = new ArrayList();
    private ArrayList<Comision> comisiones = new ArrayList();

    public Proveedor(String nombre) {
        this.nombre = nombre;
    }

    public void agregarComision(Comision c) {
        if (!comisiones.contains(c)) {
            comisiones.add(c);
        }
    }

    public ArrayList<Comision> getComisiones() {
        return comisiones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void agregar(Producto p) {
        if (!productos.contains(p)) {
            productos.add(p);
        }
    }

    @Override
    public String toString() {
        return nombre;
    }

}
