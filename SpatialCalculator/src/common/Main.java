package common;

public class Main {

    public static void main(String[] args) {

        Compartment c = new Compartment();
        c.name = "compartment 1";
        c.group = "E";
        c.sprk = false;
        c.actOpns = 4;
        c.h = 3;
        c.w = 10;
        c.LD = 3;
    
        c.hazard();
        c.area(); 
        c.ratio();     
        c.ratioCode();
        c.getAO();
        c.getUO();

        System.out.println(c.area());
        System.out.println(c.ratio());
        System.out.println(c.ratioCode());
        

    }
}