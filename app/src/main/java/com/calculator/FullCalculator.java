package com.calculator;
import java.util.Stack;

public class FullCalculator {

    private final Stack<Character> operatorStack;
    private final Stack<Double> valueStack;
    private boolean error;

    public FullCalculator() {
        operatorStack = new Stack<>();
        valueStack = new Stack<>();
        error = false;
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == 'x' || ch == '÷';
    }

    private int getPrecedence(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        }
        if (ch == 'x' || ch == '÷') {
            return 2;
        }
        return 0;
    }

    private void processOperator(char t) {
        double a, b;
        if (valueStack.empty()) {
            error = true;
            return;
        } else {
            b = valueStack.peek();
            valueStack.pop();
        }
        if (valueStack.empty()) {
            error = true;
            return;
        } else {
            a = valueStack.peek();
            valueStack.pop();
        }
        double r = 0;
        if (t == '+') {
            r = a + b;
        } else if (t == '-') {
            r = a - b;
        } else if (t == 'x') {
            r = a * b;
        } else if(t == '÷') {
            r = a / b;
        } else {
            error = true;
        }
        valueStack.push(r);
    }

    public String processInput(String input) {
        // The tokens that make up the input
        String[] tokens = input.split(" ");

        // Main loop - process all input tokens
        for (String nextToken : tokens) {
            if (!nextToken.isEmpty()) {
                char ch = nextToken.charAt(0);
                if (ch >= '0' && ch <= '9') {
                    double value = Double.parseDouble(nextToken);
                    valueStack.push(value);
                } else if (isOperator(ch)) {
                    if (!operatorStack.empty() && getPrecedence(ch) <= getPrecedence(operatorStack.peek())) {
                        while (!operatorStack.empty() && getPrecedence(ch) <= getPrecedence(operatorStack.peek())) {
                            char toProcess = operatorStack.peek();
                            operatorStack.pop();
                            processOperator(toProcess);
                        }
                    }
                    operatorStack.push(ch);
                } else if (ch == '(') {
                    operatorStack.push(ch);
                } else if (ch == ')') {
                    while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
                        char toProcess = operatorStack.peek();
                        operatorStack.pop();
                        processOperator(toProcess);
                    }
                    if (!operatorStack.empty() && operatorStack.peek() == '(') {
                        operatorStack.pop();
                    } else {
                        error = true;
                    }
                }
            }

        }
        // Empty out the operator stack at the end of the input
        while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
            char toProcess = operatorStack.peek();
            operatorStack.pop();
            processOperator(toProcess);
        }
        // Print the result if no error has been seen.
        if (!error) {
            double result = valueStack.peek();
            valueStack.pop();
            if (!operatorStack.empty() || !valueStack.empty()) {
                return "error";
            } else {
                return "" + result;
            }
        } else {
            return "error";
        }
    }

}