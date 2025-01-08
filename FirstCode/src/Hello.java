import SecondClass.SecondClass;

public class Hello {
    public static void main(String[] args) {
        SecondClass.toPrint();

        int x = 100, y = 100;

        if (x == 100 && y == 100) {
            System.out.println("AND");
        }
        if (x == 100 || y == 100) {
            System.out.println("OR");

        }
    }
}
