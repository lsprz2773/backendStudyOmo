package models;

public class Circulo extends Figura {
    private double radio;

    public Circulo() {
        super.mns="Circulo";
    }

    public void quienSoy(){
        super.mns = "Quien soy";
        super.calcularArea();
        //super.setMns("Hola desde circulo");
    }

}
