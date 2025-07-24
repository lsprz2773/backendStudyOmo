package models;

public class ItemCarritoCompra {
    static private int idItemCompra=0; //static es para bloquear al atributo solo a la clase, no a objetos.
    private int idProducto;
    private int cantidad;

    public ItemCarritoCompra(int idProducto, int cantidad) {
        idItemCompra++;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }
}
