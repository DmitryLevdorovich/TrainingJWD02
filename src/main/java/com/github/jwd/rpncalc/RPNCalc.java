package com.github.jwd.rpncalc;

import com.github.jwd.rpncalc.exception.RPNCalcException;

public class RPNCalc {

    public static void main(String[] args) throws RPNCalcException {

        String expression = "2+2+2*2+(5-4)";

        String exprNoSpaces = expression.replaceAll("\\s+", "");
        try {
            Validator.validateExpression(exprNoSpaces);
            String rpnString = Parser.parseExpression(exprNoSpaces);
            Double result = Calculator.calculateParsedExpression(rpnString);
            System.out.println(result);

        } catch (RPNCalcException e) {
            throw e;
        } finally {

        }
    }

    public double calculate(String expression) throws RPNCalcException {

        if(!expression.isEmpty()) {
            throw new RPNCalcException("String is empty");
        }

        String exprNoSpaces = expression.replaceAll("\\s+", "");
        try {
            Validator.validateExpression(exprNoSpaces);
            String rpnString = Parser.parseExpression(exprNoSpaces);
            Double result = Calculator.calculateParsedExpression(rpnString);

        } catch (RPNCalcException e) {
            throw e;
        } finally {

        }

        return 0;
    }
}
