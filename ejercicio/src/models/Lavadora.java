package models;

public class Lavadora extends Electrodomestico{
    private final int CARGA=5;
    protected int carga;

    public Lavadora(int carga) {
        this.carga = CARGA;
    }

    public Lavadora(int precio, int peso) {
        this.precioBase=precio;
        this.peso=peso;
    }

    public Lavadora(String color, int precioBase, char consumoEnergetico, int precio, int peso, int carga) {
        this.color = color;
        this.precioBase = precioBase;
        this.consumoEnergetico = consumoEnergetico;
        this.precio = precio;
        this.peso = peso;
        this.carga = carga;
    }

    public int getCarga() {
        return carga;
    }

    @Override
    public int precioFinal(){
        int precioExtra=0;

        if (carga>30){
            precioExtra=50;
        }
        return super.precioBase + precioExtra;
    }
}
