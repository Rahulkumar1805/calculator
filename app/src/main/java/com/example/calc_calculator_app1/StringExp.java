package com.example.calc_calculator_app1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class StringExp {

    public static double evaluate(String expression) {
        // Convert infix notation to postfix notation
        List<String> postfix = infixToPostfix(expression);
        // Evaluate postfix notation using a stack
        Stack<Double> stack = new Stack<>();
        for (String token : postfix) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = evaluateOperator(token, operand1, operand2);
                stack.push(result);
            }
        }
        return stack.pop();
    }

    private static List<String> infixToPostfix(String expression) {
        List<String> postfix = new ArrayList<>();
        Stack<String> operatorStack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/%", true);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) {
                continue;
            }
            if (isNumber(token)) {
                postfix.add(token);
            } else if ("(".equals(token)) {
                operatorStack.push(token);
            } else if (")".equals(token)) {
                while (!operatorStack.empty() && !"(".equals(operatorStack.peek())) {
                    postfix.add(operatorStack.pop());
                }
                if (!operatorStack.empty() && "(".equals(operatorStack.peek())) {
                    operatorStack.pop();
                }
            } else {
                while (!operatorStack.empty() && precedence(token) <= precedence(operatorStack.peek())) {
                    postfix.add(operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }
        while (!operatorStack.empty()) {
            postfix.add(operatorStack.pop());
        }
        return postfix;
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "%":
                return 2;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    private static double evaluateOperator(String operator, double operand1, double operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            case "%":
                return ((operand1 / 100.0) * operand2);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

}