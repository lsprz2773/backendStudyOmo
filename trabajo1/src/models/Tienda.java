package models;

public class Tienda {
    private int idTienda;
    private String nombre;
    private Inventario inventario;
    private BitacoraVenta bitacoraVenta;

    public Tienda() {
        inventario = new Inventario();
        bitacoraVenta = new BitacoraVenta();
    }
}
