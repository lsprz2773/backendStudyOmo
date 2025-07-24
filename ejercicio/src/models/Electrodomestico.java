package models;

public class Electrodomestico {
    private final String COLOR="Blanco";
    private final int PRECIOBASE=100;
    private char CONSUMO_ENERGETICO='F';
    private final int PESO=5;

    protected int precioBase;
    protected String color;
    protected char consumoEnergetico;
    protected int precio;
    protected int peso;

    public Electrodomestico() {
        this.precioBase=PRECIOBASE;
        this.color=COLOR;
        this.consumoEnergetico=CONSUMO_ENERGETICO;
        this.peso=PESO;
    }

    public Electrodomestico(int precio, int peso) {
        this.precioBase=precio;
        this.peso=peso;
        this.color=COLOR;
        this.consumoEnergetico=CONSUMO_ENERGETICO;
    }

    public Electrodomestico(String color, int precioBase, char consumoEnergetico, int precio, int peso) {
        this.precioBase=precioBase;
        this.color=comprobarColor(color);
        this.consumoEnergetico = comprobarConsumoEnergetico(consumoEnergetico);
        this.precio=precioFinal();
        this.peso=peso;
    }

    public int getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(int precioBase) {
        this.precioBase = precioBase;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public char getConsumoEnergetico() {
        return consumoEnergetico;
    }

    public void setConsumoEnergetico(char consumoEnergetico) {
        this.consumoEnergetico = consumoEnergetico;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    private char comprobarConsumoEnergetico(char letra){
        char[] letras = {'A','B','C','D','E','F'};
        boolean flag=false;
        for(int i=0;i<letras.length && !flag;i++){
            if(letras[i]==letra){
                flag=true;
            }
        }
        return (flag) ? letra : CONSUMO_ENERGETICO;
    }

    private String comprobarColor(String color){
        String[] COLORES = {"blanco", "negro", "rojo", "azul", "gris"};
        boolean flag=false;
        for(int i=0;i<COLORES.length && !flag;i++){
            if(COLORES[i].contains(color.toLowerCase())){
                flag=true;
            }
        }
        return (flag) ? color : COLOR;
    }


    public int precioFinal(){
        int precioFinal=0;
        int pesoFinal=0;

        switch (consumoEnergetico){
            case 'A' -> precioFinal=100;
            case 'B' -> precioFinal=80;
            case 'C' -> precioFinal=60;
            case 'D' -> precioFinal=50;
            case 'E' -> precioFinal=30;
            case 'F' -> precioFinal=10;
        }

        if (peso<0 && peso>19){
            pesoFinal=10;
        } else if (peso<20 && peso>49) {
            pesoFinal=50;
        } else if (peso<50 && peso>79) {
            pesoFinal=80;
        } else{
            pesoFinal=100;
        }

        return precioFinal + pesoFinal;

    }
}

