package com.github.jwd.rpncalc;

public final class RPNCalc {

    /*public static void main(String[] args) throws RuntimeException {

        String expression = "2+2+3*5-((5-4)*5)";

        String exprNoSpaces = expression.replaceAll("\\s+", "");
        try {
            Validator.validateExpression(exprNoSpaces);
            String rpnString = Parser.parseExpression(exprNoSpaces);
            Double result = Calculator.calculateParsedExpression(rpnString);
            System.out.println(result);

        } catch (RuntimeException e) {
            throw e;
        } finally {

        }
    }*/

    private RPNCalc() {}

    public static double calculate(String expression) throws RuntimeException {

        if(expression.isEmpty()) {
            throw new RuntimeException("String is empty");
        }

        double result = 0.0;

        String exprNoSpaces = expression.replaceAll("\\s+", "");
        try {
            Validator.validateExpression(exprNoSpaces);
            String rpnString = Parser.parseExpression(exprNoSpaces);
            result = Calculator.calculateParsedExpression(rpnString);
        } catch (RuntimeException e) {
            e.printStackTrace(System.out);
        } finally {
            //System.out.println("Unknown error");
        }

        return result;
    }
}
