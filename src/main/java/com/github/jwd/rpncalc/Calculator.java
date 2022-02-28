package com.github.jwd.rpncalc;

import java.util.ArrayDeque;
import java.util.Deque;

class Calculator {

    public static double calculateParsedExpression(String rpnExpression) {
        StringBuilder operand = new StringBuilder();
        Deque<Double> stack = new ArrayDeque<>();

        for (int i = 0; i < rpnExpression.length(); i++) {
            if(rpnExpression.charAt(i) == ' ') {
                continue;
            }

            if(RPNUtility.getPriority(rpnExpression.charAt(i)) == 0) {
                while (rpnExpression.charAt(i) != ' ' &&
                        RPNUtility.getPriority(rpnExpression.charAt(i)) == 0) {
                    operand.append(rpnExpression.charAt(i++));
                    if (i == rpnExpression.length()) {
                        break;
                    }
                }
                stack.push(Double.parseDouble(operand.toString()));
                operand = new StringBuilder();
            }

            if(RPNUtility.isArithmeticOperator(rpnExpression.charAt(i))) {
                double a = stack.pop();
                double b = stack.pop();
                stack.push(performArithmeticOperation(a, b, rpnExpression.charAt(i)));
            }
        }

        return stack.pop();
    }

    private static double performArithmeticOperation(double a, double b, char op) {

        switch (op) {
            case '+':
                return b + a;
            case '-':
                return b - a;
            case '*':
                return b * a;
            case '/':
                if(a != 0) {
                    return b / a;
                } else {
                    throw new IllegalArgumentException("Division by 0." + b + " / " + a);
                }
            default:
                throw new IllegalArgumentException("Unexpected operator " + op);
        }

    }
}
