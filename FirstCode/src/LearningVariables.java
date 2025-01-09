public class LearningVariables {
    public static void main(String[] args) {
        int firstNumber = 2;  // 32 bits
        firstNumber = 3;
        System.out.println(firstNumber);

        String name = "Augusto S";
        System.out.println(name);
        char x = 'A';   // 16 bits (not 8). It's bigger to represent UTF-16
        for (int i = 0; i < 26; i++) {
            System.out.print(x + " ");
            x = (char) (x + 1);
        }
        x = (char) (x - 1);
        System.out.println(x);

        System.out.println("Byte range: "+ Byte.MIN_VALUE + " to " + Byte.MAX_VALUE);

        long longNumber = 100L; // L to say it's not an int and treat it as a long
        float fx = 23.2F;   // F to say it's not a double and treat it as a float
        // float is 32 bits
        // double is 64 bits


        short shortV = 1, shortV2 = 2;





        // Casting
            // convert a number of one type to another
            // using the line 14 as an example. Java returns the result of x - 1 as an integer
            // to convert it to a char, we use the (char) (x - 1)

        boolean isValid = true, hasName = false;

        System.out.println(isValid);
        System.out.println(hasName);

        char firstC = 'A', secondC = 'B';

        System.out.println(firstC + secondC);   // treats it like a number

        // to treat it as a string, you can put an empty string at the start of the print, so it knows to treat all as a string
        // the same goes for types like int
        System.out.println("" + firstC + secondC);  // OUT: AB
        double result = Math.pow(2, 5);
        System.out.println(result);



    }
}
