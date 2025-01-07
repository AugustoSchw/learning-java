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


    }
}
