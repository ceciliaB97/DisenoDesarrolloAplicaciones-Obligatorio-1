package IuConsola;

import java.util.ArrayList;
import logica.Cliente;
import logica.Fachada;
import logica.Factura;
import logica.LineaFactura;
import logica.Producto;
import logica.Proveedor;
import utilidades.Consola;

public class IuConsola {
    
    Fachada logica = Fachada.getInstancia();
    
    /**
     * Ejecuta la consola
     */
    public void mostrarConsola(){
        boolean salir = false;
        do{
           
           int opcion = imprimirMenu();
           salir = procesarOpcion(opcion);
            
        }
        while(!salir);
    }
    
    /**
     * Imprime el menú y sus opciones a pantalla
     */
    private int imprimirMenu(){
        System.out.println("MENU");
        System.out.println("====");
        
        ArrayList<String> opciones = new ArrayList();
        opciones.add("Alta de Cliente");
        opciones.add("Alta de Producto");
        opciones.add("Alta de Factura");
        opciones.add("Consulta clientes");
        opciones.add("Salir del menú");
        return Consola.menu(opciones);
    }
    
    /**
     * Captura la opción seleccionada por el usuario y ejecuta la acción correspondiente
     */
    private boolean procesarOpcion(int opcion) {
        boolean salir = false;
        int numero;
      
        switch (opcion){
            case 0:
                this.nuevoCliente();
                break;
            case 1:
                nuevoProducto();
                break;
                
            case 2:
                nuevoFactura();
                break;
                
            case 3:
                consultarClientes();
                break;
                
            case 4:
                salir = true;
                break;
            
        }
        return salir;
    }

    private void nuevoCliente() {
        
        System.out.println("ALTA DE CLIENTE");
        System.out.println("===============");
       
        Cliente unCliente = new Cliente();
        unCliente.setCedula(Consola.leer("Cedula:"));
        unCliente.setNombre(Consola.leer("Nombre:"));
        if(logica.agregarCliente(unCliente)){
           mostrarClientes();
        }else 
             System.out.println("EL CLIENTE NO FUE INGRESADO");
        
    }

    private void mostrarClientes() {
         System.out.println("=================");
         System.out.println("CLIENTES ACTUALES");
         System.out.println("=================");
         ArrayList<Cliente> clientes = logica.getClientes();
         for(Cliente c:clientes){
             System.out.println(c.getCedula() + " - " + c.getNombre());
         }
    }

    private void nuevoProducto() {
        
        System.out.println("ALTA DE PRODUCTO");
        System.out.println("===============");
        
        Producto prod = new Producto();
        
        boolean ok = false;
        do{
            ok = prod.setNombre(Consola.leer("Nombre:"));
        }while(!ok);
        
        while( !prod.setUnidades(Consola.leerInt("Unidades:")) ); 
         
        
        ArrayList<Proveedor> provs = logica.getProveedores();
        
        int p = Consola.menu(provs);
        
        Proveedor select = provs.get(p);
        
        prod.setProveedor(select);
        
        while( !prod.setPrecio(Consola.leerInt("Precio:")) ); 
        
        if(logica.agregarProducto(prod)){
            System.out.println("PRODUCTO AGREGADO");
        }else {
            System.out.println("ERROR AL AGREGAR EL PRODUCTO");
        }
    }

    public void nuevoFactura() {
        System.out.println("ALTA DE FACTURA");
        System.out.println("===============");
        Cliente c;
        //do {
            c = logica.buscarClientePorCedula(Consola.leer("Cedula:"));
            if (c == null) {
                System.out.println("No existe el cliente (vuelva al menu principal)");
                return;
            }
        //}
        //while (c==null);
        
        Factura nuevaFactura = new Factura(c);
        boolean ok, salir = false;
        while (!salir) {
            System.out.println("Agregando item a la factura-->");
            ok = nuevaFactura.agregarPorCodigoProducto(
            Consola.leerInt("Cantidad:"), Consola.leerInt("Cod. Producto:"));

            if (ok) {
                mostrarFactura(nuevaFactura);
            } else {
                System.out.println("Error al agregar el producto.");
            }
            ArrayList<String> opciones = new ArrayList<>();
            opciones.add("Finalizar");
            opciones.add("Continuar");
            opciones.add("Descartar");
            int opcion = Consola.menu(opciones);
            switch (opcion) {
                case 0:
                    logica.agregarFactura(nuevaFactura);
                    mostrarFactura(nuevaFactura);
                    salir = true;
                    break;

                case 1:
                    break;

                case 2:
                    salir = true;
                    break;
            }
        }
        System.out.println("Vuelva al menú principal");
        
    }

    private void mostrarFactura(Factura factura) {
        int x = 1;
        String mensaje = null;
        for (LineaFactura linea : factura.getLineas()) {
            mensaje = x + " - " + linea.getProducto().getCodigo() + " - " + 
                    linea.getProducto().getNombre() + " - " + 
                    linea.getCantidad() + " - " + 
                    " $ " + linea.total();
            System.out.println(mensaje);
            x++;
        }
        System.out.println("***************");
        System.out.println("Total $ : " + factura.total());
    }

    private void consultarClientes() {
        System.out.println("=================");
        System.out.println("CONSULTAR CLIENTES");
        System.out.println("=================");
        if (logica.getProductos().isEmpty()) {
             System.out.println("No existen productos ingresados.");
             return;
        }
        String mensaje;
        Producto menor = logica.getProductoMenorPrecio();
        mensaje = "(" + menor.getCodigo() + ") " + menor.getNombre() + " - " +
                   " $ " + menor.getCodigo() + " - " + menor.getUnidades();
        System.out.println(mensaje);
        ArrayList<Cliente> clientes = logica.clientesCompraronProductoMenorPrecio();
        
        if (clientes.isEmpty()) {
            System.out.println("No existen clientes que compraron el producto de menor precio.");
            return;
        }
         
        for(Cliente c:clientes){
           mensaje = c.getCedula() + " - " +
                   c.getNombre() + " - " +
                   logica.clienteFechaUltimaFacturaPorProducto(c, menor);
           System.out.println(mensaje);
        }
    }
}
    


    

