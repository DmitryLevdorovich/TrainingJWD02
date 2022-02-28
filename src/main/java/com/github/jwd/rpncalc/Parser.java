package com.github.jwd.rpncalc;

import java.util.ArrayDeque;
import java.util.Deque;

class Parser {

    public static String parseExpression(String expression) {

        StringBuilder result = new StringBuilder();
        int opPriority;
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            opPriority = RPNUtility.getPriority(expression.charAt(i));
            switch (opPriority) {
                case 0:
                    result.append(expression.charAt(i));
                    break;
                case 1:
                    result.append(" ");
                    while(!stack.isEmpty() && RPNUtility.getPriority(stack.peek()) != 2) {
                        result.append(stack.pop());
                    }
                    stack.pop();
                    break;
                case 2:
                    stack.push(expression.charAt(i));
                    break;
                case 3:
                case 4:
                    result.append(" ");
                    while(!stack.isEmpty()) {
                        if(RPNUtility.getPriority(stack.peek()) >= opPriority) {
                            result.append(stack.pop());
                        } else {
                            break;
                        }
                    }
                    stack.push(expression.charAt(i));
                    break;
                default:
                    break;
            }
        }
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }
}
