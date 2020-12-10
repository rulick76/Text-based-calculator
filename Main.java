package taboola.israelrozen.solution_1;

import taboola.israelrozen.solution_1.exceptions.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator();
        ExpressionValidator exprValidator=new ExpressionValidator();
        System.out.print("Text based calculator:\n" +
                " [+] Supported operations : addition, subtraction, multiplication, pre-increment, and post-increment.\n" +
                " [+] Keep inserting matematical expressions with the supported operations only .\n" +
                " [+] Seperate operations and variables with a white-space  .\n\n");
 
        while (true) {
            BufferedReader buffer= new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please enter expression: ");
            String exp = buffer.readLine();

            if (exp.isEmpty()) break;// print results

            try {
                // Validation layer 
                // handle the validations and raise invalid operations and expressions exceptions
                if(exprValidator.ValidExpression(exp)){
                    // calculation layer 
                    // handle the calculations and will raise undefined variabled exceptions
                    calculator.calculate(exp);
                }
            } catch (InvalidExpressionException e) {
                System.out.println("Invalid expression.");
            } catch (UndefinedVariableException e) {
                System.out.println("Variable is not defined.");
            } 
            catch (UnsupportedOperationException e) {
                System.out.println("Operation is not supported.");
            } 
        }
        ;
        System.out.print(calculator.getResult());
    }
}
