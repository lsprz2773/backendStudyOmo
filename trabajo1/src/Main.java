import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int opc = 0;

        do {
            System.out.println("T I E N D A    A M I G A");
            System.out.println("Menú de opciones");
            System.out.println("1.- Agregar productos al inventario");
            System.out.println("2.- Iniciar una venta");
            System.out.println("3.- Salir");
            System.out.print("Ingresa una opcion: ");
            opc = keyboard.nextInt();

            if (opc == 1) {
                System.out.println("Ingresa un producto: ");
                String producto = keyboard.next();
            }
            else if (opc == 2) {
                System.out.println("Ingresa un producto: ");
                String producto = keyboard.next();
            }

        }while (opc != 3);
    }
}