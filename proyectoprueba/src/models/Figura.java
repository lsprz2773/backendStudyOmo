package models;

public class Figura {
    private float area;
    protected String mns = "";

    public void calcularArea() {
        System.out.println(mns);
    }
     public void setMns(String mns){
        this.mns = mns;
     }

     public Figura () {}

     public Figura(String mns){
         System.out.println(mns);
     }
}
