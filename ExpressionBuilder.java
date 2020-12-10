package taboola.israelrozen.solution_1;

import taboola.israelrozen.solution_1.resolvers.*;
import taboola.israelrozen.solution_1.enums.Operator;
import java.util.HashMap;
import java.util.Scanner;

public class ExpressionBuilder {
    //Context var
    private char var;
    private ExpressionValidator _expValidator=null;

    public ExpressionBuilder(ExpressionValidator expValidator) {
        _expValidator=expValidator;
    }

    public char getVariable() {
        return var;
    }

    Resolverable build(String expression, HashMap<Character, Integer> map) {
        Scanner scanner = new Scanner(expression);  //using default delimiter (white space)

        var = scanner.next().charAt(0);
        if (!map.containsKey(var)) map.put(var, 0);
        return parseExp(scanner, map);
    }

    private Resolverable parseExp(Scanner scanner, HashMap<Character, Integer> map) {
        Resolverable left = getResolver(scanner, map);
        return parse(left, scanner, map);
    }

    private Resolverable parse(Resolverable left, Scanner scanner,  HashMap<Character, Integer> map) {
        if (scanner.hasNext()) {
            switch (scanner.next()) {
                case "+":
                    return new Expression(left, Operator.ADD, parseExp(scanner, map));
                case "-":
                    return new Expression(left, Operator.SUB, parseExp(scanner, map));
                case "*":
                    Resolverable innerExpr = new Expression(left, Operator.MUL, getResolver(scanner, map));
                    if (scanner.hasNext()) {
                        return parse(innerExpr, scanner, map);
                    }
                    return innerExpr;
            }
        }
        return left;
    }

    protected Resolverable getResolver(Scanner scanner,  HashMap<Character, Integer> map) {
        String token=scanner.next();

        if (_expValidator.isNumber(token))
             return new SimpleExpression(Integer.valueOf(token));

        if (_expValidator.isAssignment(token)) 
            return getResolver(scanner,map);
        
        if (_expValidator.isPlusEquelAssign(token)) {
            Resolverable left = new SimpleVariable(var, false, map);
            return new Expression(left, Operator.ADD, parseExp(scanner, map));
        }

        if (_expValidator.isVariable(token)) 
            return new SimpleVariable(token.charAt(0), false, map);

        if (_expValidator.isPostIncrement(token)) 
            return new SimpleVariable(token.charAt(0), true, map);

        if (_expValidator.isPreIncrement(token))
            return new Expression(new SimpleExpression(1), Operator.ADD, new SimpleVariable(token.charAt(2), true, map));

        return null;
    }
}