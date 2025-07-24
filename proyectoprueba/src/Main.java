import models.Circulo;
import models.Figura;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Figura figura = new Figura("Figura");
        figura.calcularArea();

        Circulo circulo = new Circulo();
        circulo.calcularArea();
        circulo.quienSoy();

        Figura fig = new Circulo(); //Polimorfismo, si se puede
        Circulo cir = new Figura(); //Polimorfismo no se puede
    }
}