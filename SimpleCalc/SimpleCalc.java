import java.util.List;

/**
 * This is a simple version of a basic calculator (bc) that evaluates
 * simple expressions using stacks.
 *
 * @author Aarav Prakash
 * @since February 26, 2025
 */
public class SimpleCalc {
    private ExprUtils utils; // expression utilities
    private ArrayStack<Double> valueStack; // value stack
    private ArrayStack<String> operatorStack; // operator stack

    // constructor
    public SimpleCalc() {
        utils = new ExprUtils();
        valueStack = new ArrayStack<>();
        operatorStack = new ArrayStack<>();
    }

    public static void main(String[] args) {
        SimpleCalc sc = new SimpleCalc();
        sc.run();
    }

    public void run() {
        System.out.println("\nWelcome to SimpleCalc!!!");
        runCalc();
        System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
    }

    /**
     * Prompt the user for expressions, run the expression evaluator,
     * and display the answer.
     */
    public void runCalc() {
        boolean quit = false;
        while (!quit) {
            String input = Prompt.getString("");
            if (input.equals("q")) {
                quit = true;
            } else if (input.equals("h")) {
                printHelp();
            } else {
                List<String> tokens = utils.tokenizeExpression(input);
                double result = evaluateExpression(tokens);
                System.out.println(result);
            }
        }
    }

    /** Print help */
    public void printHelp() {
        System.out.println("Help:");
        System.out.println("  h - this message\n  q - quit\n");
        System.out.println("Expressions can contain:");
        System.out.println("  integers or decimal numbers");
        System.out.println("  arithmetic operators +, -, *, /, %, ^");
        System.out.println("  parentheses '(' and ')'\n");
    }

    /**
     * Evaluate expression and return the value, following PEMDAS.
     *
     * @param tokens a List of String tokens making up an arithmetic expression
     * @return a double value of the evaluated expression
     */
    public double evaluateExpression(List<String> tokens) {
        for (String token : tokens) {
            if (isNumeric(token)) { // If it's a number
                valueStack.push(Double.parseDouble(token));
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    processOperator();
                }
                operatorStack.pop(); // Remove '('
            } else { // It's an operator
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(token)) {
                    processOperator();
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            processOperator();
        }

        return valueStack.isEmpty() ? 0.0 : valueStack.pop();
    }

    private void processOperator() {
        if (valueStack.isEmpty()) return;
        double b = valueStack.pop();
        double a = valueStack.pop();
        String op = operatorStack.pop();

        if (op.equals("+")) valueStack.push(a + b);
        else if (op.equals("-")) valueStack.push(a - b);
        else if (op.equals("*")) valueStack.push(a * b);
        else if (op.equals("/")) valueStack.push(a / b);
        else if (op.equals("%")) valueStack.push(a % b);
        else if (op.equals("^")) valueStack.push(Math.pow(a, b));
    }

    private int precedence(String op) {
        if (op.equals("^") ) return 3;
        if (op.equals("*") || op.equals("/") || op.equals("%")) return 2;
        if (op.equals("+") || op.equals("-")) return 1;
        return 0;
    }

    private boolean isNumeric(String token) {
    if (token.isEmpty() || (token.length() == 1 && token.equals("-"))) {
        return false;
    }
    int start = (token.charAt(0) == '-') ? 1 : 0;
    boolean hasDecimal = false;
    for (int i = start; i < token.length(); i++) {
        char c = token.charAt(i);
        if (c == '.') {
            if (hasDecimal) return false;
            hasDecimal = true;
        } else if (c < '0' || c > '9') {
            return false;
        }
    }
    
    return true;
    }
}
