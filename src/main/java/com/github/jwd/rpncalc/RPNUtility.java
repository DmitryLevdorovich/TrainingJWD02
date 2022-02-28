package com.github.jwd.rpncalc;

final class RPNUtility {

    private RPNUtility() {}

    public static int getPriority(char token) {
        if (token == '*' || token == '/') {
            return 4;
        } else if (token == '+' || token == '-') {
            return 3;
        } else if (token == '(') {
            return 2;
        } else if (token == ')') {
            return 1;
        } else {
            return 0;
        }
    }

    public static boolean isArithmeticOperator(char a) {
        return a == '+' || a == '-' ||
                a == '*' || a == '/';
    }
}
