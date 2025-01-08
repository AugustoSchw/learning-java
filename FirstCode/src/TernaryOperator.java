public class TernaryOperator {
    public static void main(String[] args) {
        // Ternary Operator:
            // operand1 ? operand2 : operand3
            // What is does:
            // IF operand1 is true, it will return operand2
            // if it's false, it will return operand3

        int result;
        boolean isReal = false;
        result = isReal ? 3 : 2;
        // if isReal is true, it will return 3. Else, it returns 2

        System.out.println(result);

        boolean isTwo = result == 2 ? true : false; // if result equal 2, then the boolean "isTwo" is true. Else, false.

        System.out.println(isTwo);
    }
}
