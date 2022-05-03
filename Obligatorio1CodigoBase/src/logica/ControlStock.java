package logica;

import java.util.ArrayList;

public class ControlStock {

    private int proxIdProd = 1;

    private ArrayList<Producto> productos = new ArrayList();
    private ArrayList<Proveedor> proveedores = new ArrayList();
    private ArrayList<Comision> comisiones = new ArrayList();

    protected ControlStock() {
    }

    public boolean buscarComisionesConMismoNombre(String nombre) {
        boolean retorno = false;
        for (Comision c : comisiones) {
            if (!c.getNombre().equals(nombre)) {
                retorno = false;
            }
        }
        return retorno;
    }

    public void agregarFacturaEnComisiones(Factura f) {
        for (Comision c : comisiones) {
            for (Producto p : f.getProductos()) {
                if (p.equals(c.getProducto()) && f.getFecha().after(c.getFechaCreacion())) {
                    c.agregarFactura(f);
                }
            }
        }
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }

    public ArrayList<Comision> getComisiones() {
        return comisiones;
    }

    public Producto getProductoMenorPrecio() {
        Producto menor = productos.get(0);
        Producto p;
        for (int x = 1; x < productos.size(); x++) {
            p = productos.get(x);
            if (p.getPrecio() < menor.getPrecio()) {
                menor = p;
            }
        }

        return menor;
    }

    public void agregarComision(Comision c) {
        if (!comisiones.contains(c) && c.validar()) {
            comisiones.add(c);
            Proveedor prov = c.getProveedor();
            Producto prod = c.getProducto();
            prov.agregarComision(c);
            prod.agregarComision(c);
        }

    }

    public void agregar(Proveedor unProveedor) {
        if (!proveedores.contains(unProveedor)) {
            proveedores.add(unProveedor);
        }
    }

    public boolean agregar(Producto unProducto) {
        if (!unProducto.validar()) {
            return false;
        }
        unProducto.setCodigo(proxIdProd);
        proxIdProd++;
        productos.add(unProducto);
        unProducto.getProveedor().agregar(unProducto);
        return true;
    }

    public Producto buscarProductoPorCodigo(int codigoProducto) {
        for (Producto p : productos) {
            if (p.getCodigo() == codigoProducto) {
                return p;
            }
        }
        return null;
    }
}
