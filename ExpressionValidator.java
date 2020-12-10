package taboola.israelrozen.solution_1;

import java.util.Scanner;
import java.util.regex.Pattern;
import taboola.israelrozen.solution_1.exceptions.InvalidExpressionException;

public class ExpressionValidator {

    public boolean ValidExpression(String expr) {
        Scanner scanner = new Scanner(expr);
        boolean nextExpectedToken = false;
        
        // first token has to be a variable
        if (!scanner.hasNext() || !Pattern.matches("[a-z]", scanner.next())) 
        throw new InvalidExpressionException();
        // second token has to be = / += assignment
        if (scanner.hasNext()){
            var nextToken=scanner.next();
            if(!isAssignment(nextToken) && !isPlusEquelAssign(nextToken)) 
                throw new InvalidExpressionException();
        }else{
            throw new InvalidExpressionException();
        }
            
        while (scanner.hasNext()) {
            String token = scanner.next();
            if (nextExpectedToken) {  // next token has to be an Operation
                if (!isOperation(token)) {
                    throw new UnsupportedOperationException();
                }
                nextExpectedToken = false;
            } else {  
                //next token has to be a value
                if (!isValue(token)) {
                    throw new InvalidExpressionException();
                }
                nextExpectedToken = true;
            }
        }
        scanner.close();
        return nextExpectedToken;
    }

    public boolean isAssignment(String s) {
        return Pattern.matches("=", s);
    }

    public boolean isOperation(String s) {
        return (Pattern.matches("\\+", s))
                || Pattern.matches("-", s)
                || Pattern.matches("\\*", s);
    }

    public boolean isValue(String s) {
        return isNumber(s) || isVariable(s) || isPreIncrement(s) || isPostIncrement(s);
    }

    public boolean isNumber(String s) {
        return Pattern.matches("[0-9]+", s);
    }

    public boolean isPlusEquelAssign(String s){
        return Pattern.matches("\\+=", s);
    }

    public boolean isEquelAssign(String s){
        return Pattern.matches("\\=", s);
    }

    public boolean isVariable(String s) {
        return Pattern.matches("[a-z]", s);
    }

    public boolean isPreIncrement(String s) {
        return Pattern.matches("\\+\\+[a-z]", s);
    }

    public boolean isPostIncrement(String s) {
        return Pattern.matches("[a-z]\\+\\+", s);
    }
}
