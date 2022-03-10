package inheritance.constructor;

public class Raven extends Bird{
    public Raven() {
        super("grey");

        System.out.println("Constructing Raven");
    }
}
