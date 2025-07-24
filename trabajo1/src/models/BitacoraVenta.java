package models;

import java.util.ArrayList;

public class BitacoraVenta {
    private int idBitacora;
    private ArrayList<CarritoCompra> carritoCompras;

    public BitacoraVenta() {
        this.carritoCompras = new ArrayList<>();
    }

    public boolean addCarritoCompra(CarritoCompra c){
        return carritoCompras.add(c);
    }
}
