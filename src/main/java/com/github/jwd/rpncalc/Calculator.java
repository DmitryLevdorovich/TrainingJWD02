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

                if(rpnExpression.charAt(i) == '+') {
                    stack.push(b + a);
                } else if(rpnExpression.charAt(i) == '-') {
                    stack.push(b - a);
                } else if(rpnExpression.charAt(i) == '*') {
                    stack.push(b * a);
                } else if(rpnExpression.charAt(i) == '/') {
                    stack.push(b / a);
                }


            }
        }

        return stack.pop();
    }
}
