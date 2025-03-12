import java.util.List;
import java.util.ArrayList;

/**
 * This is a simple version of a basic calculator (bc) that evaluates
 * simple expressions using stacks and supports variables.
 *
 * @author Aarav Prakash
 * @since February 26, 2025
 */


public class SimpleCalc 
{
    private ExprUtils utils;
    private ArrayStack<Double> valueStack;
    private ArrayStack<String> operatorStack;
    private List<Identifier> variables;

    public SimpleCalc() 
    {
        utils = new ExprUtils();
        valueStack = new ArrayStack<>();
        operatorStack = new ArrayStack<>();
        variables = new ArrayList<>();
        variables.add(new Identifier("e", Math.E)); 
        variables.add(new Identifier("pi", Math.PI)); 
    }

    /** the main method */
    public static void main(String[] args) 
    {
        SimpleCalc sc = new SimpleCalc();
        sc.run();
    }

    /** Runs the program */
    public void run() 
    {
        System.out.println("\nWelcome to SimpleCalc!!!\n");
        runCalc();
        System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
    }

    /**
     * Prompt the user for expressions, run the expression evaluator,
     * and display the answer.
     */
    public void runCalc() 
    {
        boolean quit = false;
        while (! quit) 
        {
            String input = Prompt.getString("");
            if (input.equals("q"))
                quit = true;
            else if (input.equals("h"))
                printHelp();
            else if (input.equals("l"))
                listVariables();
            else 
            {
                List<String> tokens = utils.tokenizeExpression(input);
                if(tokens.size() > 2 && tokens.get(1).equals("=") && isValidIdentifier(tokens.get(0))) 
                    assignVariable(tokens);
                else 
                {
                    double result = evaluateExpression(tokens);
                    System.out.println(result);
                }
            }
        }
    }

    /** Print help */
    public void printHelp() 
    {
        System.out.println("Help:");
        System.out.println("  h - this message\n  q - quit\n  l - list variables\n");
        System.out.println("Expressions can contain:");
        System.out.println("  integers or decimal numbers");
        System.out.println("  arithmetic operators +, -, *, /, %, ^");
        System.out.println("  parentheses '(' and ')'");
        System.out.println("  variables (letters only, case-sensitive)\n");
    }

    /**
     * Evaluate expression and return the value with PEMDAS
     *
     * @param tokens a List of String tokens from the input
     * @return a double value of the evaluated expression
     */
    public double evaluateExpression(List<String> tokens) 
    {
        for (String token : tokens) 
        {
            if (isNumeric(token))
                valueStack.push(Double.parseDouble(token));
            else if (isValidIdentifier(token))
                valueStack.push(getVariableValue(token));
            else if (token.equals("("))
                operatorStack.push(token);
            else if (token.equals(")")) 
            {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("("))
                    processOperator();

                operatorStack.pop();
            } 
            else 
            {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(token))
                    processOperator();
                
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty())
            processOperator();

        if (valueStack.isEmpty())
            return 0.0;
        else
            return valueStack.pop();
    }

    /**
     * Actually does the calculation with the operator
     */
    private void processOperator() 
    {
        if (valueStack.isEmpty()) 
            return;
        
        double b = valueStack.pop();
        if (valueStack.isEmpty()) 
            return;
        double a = valueStack.pop();
        String op = operatorStack.pop();

        if (op.equals("+")) valueStack.push(a + b);
        else if (op.equals("-")) valueStack.push(a - b);
        else if (op.equals("*")) valueStack.push(a * b);
        else if (op.equals("/")) valueStack.push(a / b);
        else if (op.equals("%")) valueStack.push(a % b);
        else if (op.equals("^")) valueStack.push(Math.pow(a, b));
    }

    /**
     * Checks the precedence of the operator, with a higher number
     * representing a greater precendence
     * 
     * @param op the operator when checking precendence
     * @return  a number that corresponds with the operator's precendence
     */
    private int precedence(String op) 
    {
        if (op.equals("^") ) return 3;
        if (op.equals("*") || op.equals("/") || op.equals("%")) return 2;
        if (op.equals("+") || op.equals("-")) return 1;
        return 0;
    }

    /**
     * Checks if the token is a number
     * 
     * @param token a token from the input
     * @return  if the token is a number
     */
    private boolean isNumeric(String token) 
    {
        if (token.isEmpty() || (token.length() == 1 && token.equals("-")))
            return false;
        
        int start = 0;
        if (token.charAt(0) == '-')
            start = 1;

        boolean hasDecimal = false;
        for (int i = start; i < token.length(); i++) 
        {
            char c = token.charAt(i);
            if (c == '.') 
            {
                if (hasDecimal) return false;
                hasDecimal = true;
            } 
            else if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    /**
     * Checks if the identifier is a valid identifier
     * 
     * @param token the list of tokens from the input
     * @return  if the identifier is valid
     */
    private boolean isValidIdentifier(String token) 
    {
        for (int i = 0; i < token.length(); i++) 
        {
            char character = token.charAt(i);
            if ((character < 'a' || character > 'z') && (character < 'A' || character > 'Z'))
                return false;
        }
        return true;
    }

    /**
     * Assigns the correct value for the variable
     * 
     * @param name the name of the variable
     * @return the value of the variable
     */
    private double getVariableValue(String name) 
    {
        if (name.equals("pi"))
            return Math.PI;
        else if (name.equals("e"))
            return Math.E;
    
        for (Identifier id : variables) 
        {
            if (id.getName().equals(name))
                return id.getValue();
        }
        return 0.0;
    }
    
    /**
     * Assigns variables with the corresponding value
     * 
     * @param tokens the list of all tokens
     */
    private void assignVariable(List<String> tokens) 
    {
        String name = tokens.get(0);
        
        if (name.equals("pi") || name.equals("e")) 
        {
            System.out.println("Cannot assign value to " + name);
            return;
        }
    
        List<String> expressionTokens = new ArrayList<>();
        for (int i = 2; i < tokens.size(); i++)
            expressionTokens.add(tokens.get(i));
        
        double value = evaluateExpression(expressionTokens);
    
        for (Identifier id : variables) 
        {
            if (id.getName().equals(name)) 
            {
                id.setValue(value);
                System.out.println(name + " = " + value);
                return;
            }
        }
    
        variables.add(new Identifier(name, value));
        System.out.println(name + " = " + value);
    }
    
    /**
     * Lists the variables with formatting for repeated decimals
     */
    private void listVariables() 
    {
        System.out.println("Identifiers:");
        for (Identifier id : variables) 
            System.out.printf("  %-10s = %s\n", id.getName(), id.getValue());
    }
}
