package logica;

public class DatosPrueba {

    public static void cargar() {

        Fachada logica = Fachada.getInstancia();

        Proveedor pA = new Proveedor("Proveedor A");
        Proveedor pB = new Proveedor("Proveedor B");
        Proveedor pC = new Proveedor("Proveedor C");
        Proveedor pD = new Proveedor("Proveedor D");
        Proveedor pE = new Proveedor("Proveedor E");
        Proveedor pF = new Proveedor("Proveedor F");
        Proveedor pG = new Proveedor("Proveedor G");

        logica.agregarProveedor(pA);
        logica.agregarProveedor(pB);
        logica.agregarProveedor(pC);
        logica.agregarProveedor(pD);
        logica.agregarProveedor(pE);
        logica.agregarProveedor(pF);
        logica.agregarProveedor(pG);

        Producto caramelo = new Producto("Caramelo", 2, 3000, pA);
        Producto camisa = new Producto("Camisa", 1300, 1000, pB);
        Producto computadora = new Producto("Computadora", 20000, 40, pC);
        Producto cuaderno = new Producto("Cuaderno", 250, 5000, pD);
        Producto lentesSol = new Producto("Lentes de sol", 1000, 500, pE);

        logica.agregarProducto(caramelo);
        logica.agregarProducto(camisa);
        logica.agregarProducto(computadora);
        logica.agregarProducto(cuaderno);
        logica.agregarProducto(lentesSol);

        Comision c1 = new Comision(pA, caramelo, "caramelos1", 2f);
        Comision c1V2 = new Comision(pA, caramelo, "caramelos2", 3f);
        Comision c2 = new Comision(pB, camisa, "camisas1", 8f);
        Comision c3 = new Comision(pC, computadora, "computadoras1", 15f);
        Comision c4 = new Comision(pD, cuaderno, "cuadernos1", 10f);
        Comision c4V2 = new Comision(pD, cuaderno, "cuadernos2", 5f);
        Comision c4V3 = new Comision(pD, cuaderno, "cuadernos3", 3f);

        logica.agregarComision(c1);
        logica.agregarComision(c1V2);
        logica.agregarComision(c2);
        logica.agregarComision(c3);
        logica.agregarComision(c4);
        logica.agregarComision(c4V2);
        logica.agregarComision(c4V3);

        Cliente juan = new Cliente("12345678", "Juan");
        Cliente ana = new Cliente("13456789", "Ana");
        Cliente mario = new Cliente("21234567", "Mario");

        logica.agregarCliente(juan);
        logica.agregarCliente(ana);
        logica.agregarCliente(mario);

        Factura f1 = new Factura(juan);
        f1.agregar(30, caramelo);
        f1.agregar(2, camisa);
        f1.agregar(1, computadora);

        logica.agregarFactura(f1);

        Factura f2 = new Factura(ana);
        f2.agregar(400, caramelo);
        f2.agregar(20, camisa);
        f2.agregar(10, computadora);

        logica.agregarFactura(f2);

        Factura f3 = new Factura(mario);

        f3.agregar(1, camisa);
        f3.agregar(1, computadora);

        logica.agregarFactura(f3);
    }
}
