package com.github.jwd.rpncalc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

final class Validator {

    private Validator() {}

    public static void validateExpression(String expression) throws RuntimeException {

        if(!containsOnlyAllowedSymbols(expression)) {
            throw new RuntimeException("Validation failed. Not allowed symbols found.");
        }

        if(!areBracketsCorrect(expression)) {
            throw new RuntimeException("Validation failed. Brackets are not balanced.");
        }

        if(!areOperatorsCorrect(expression)) {
            throw new RuntimeException("Validation failed. Operators are incorrect.");
        }
    }

    private static boolean containsOnlyAllowedSymbols(String expression) {

        return Pattern.matches("^[\\d\\(][\\d\\(\\)\\-\\+\\/\\*]+[\\d\\)]$", expression);
    }

    private static boolean areBracketsCorrect(String expression) {
        Deque<Character> stack = new ArrayDeque<>();

        // Traversing the Expression
        for (int i = 0; i < expression.length(); i++)
        {
            char x = expression.charAt(i);

            if (x == '(') {
                // Push the element in the stack
                stack.push(x);
                continue;
            }

            if(x == ')') {
                stack.pop();
            }
        }

        // Check Empty Stack
        return (stack.isEmpty());
    }

    private static boolean areOperatorsCorrect(String expression) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < expression.length() - 1; i++) {

            if (expression.charAt(i) == '+' || expression.charAt(i) == '-' ||
                    expression.charAt(i) == '*' || expression.charAt(i) == '/') {

                stringBuilder.append(expression.charAt(i - 1));
                stringBuilder.append(expression.charAt(i));
                stringBuilder.append(expression.charAt(i + 1));

                if(!Pattern.matches("([\\d\\)][\\-\\+\\/\\*][\\d\\(])", stringBuilder.toString())) {
                    return false;
                }

                stringBuilder = new StringBuilder();
            }
        }
        return true;
    }

}
