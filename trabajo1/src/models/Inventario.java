package models;

import java.util.ArrayList;

public class Inventario {
    private int idInventario;
    private ArrayList<Producto> productos = new ArrayList<>();

    public Boolean addProducto(Producto producto){
        return productos.add(producto);
    }

    public int showCantidadProductos(){
        return productos.size();
    }

    public boolean removeProducto(Producto producto){
        return productos.remove(producto);
    }

    public String showListaProductos(){
        String data = "Productos:[ \n";
        for (Producto producto : productos) {
            data = data + "{id: " + producto.getIdProducto() + ", nombre: " + producto.getNombre() + ", precio: " + producto.getPrecio() + ", descripcion: " + producto.getIdProducto() + ", stock:" + producto.getStock() + "}\n";
        }
        data=data+"]";
        return data;
    }

    public Producto searchProducto(String nombre){
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {}
        }
        return productos.get(0);
    }
}
