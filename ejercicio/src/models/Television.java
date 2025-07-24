package models;

public class Television extends Electrodomestico{
    private final int RESOLUCION=20;
    private final boolean SINTONIZADOR=false;

    protected int resolucion;
    protected boolean sintonizador;

    public Television(int resolucion, boolean sintonizador){
        this.resolucion=RESOLUCION;
        this.sintonizador=SINTONIZADOR;
    }

    public Television (String color, int precioBase, char consumoEnergetico, int precio, int peso, int resolucion, boolean sintonizador){
        this.color = color;
        this.precioBase = precioBase;
        this.consumoEnergetico = consumoEnergetico;
        this.precio = precio;
        this.peso = peso;
        this.resolucion=resolucion;
        this.sintonizador=sintonizador;
    }

    public int getResolucion() {
        return resolucion;
    }

    public boolean getSintonizador() {
        return sintonizador;
    }

    @Override
    public int precioFinal(){
        int newPrecioBase = 0;
        int hasSintonizador=0;

        if (resolucion>40){
            newPrecioBase =(int)(super.precioBase * 0.3);
        }
        if (sintonizador==true){
            hasSintonizador=50;
        }

        return super.precioBase+newPrecioBase+hasSintonizador;
    }


}
