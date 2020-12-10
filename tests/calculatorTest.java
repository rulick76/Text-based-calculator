package taboola.israelrozen.solution_1.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import taboola.israelrozen.solution_1.ExpressionValidator;
import taboola.israelrozen.solution_1.Calculator;

import org.junit.Test;

public class calculatorTest {

    @Test
    public void testValidExpression() {
        ExpressionValidator validator=new ExpressionValidator();
        assertEquals(true, validator.ValidExpression("i = 0"));
        assertEquals(true, validator.ValidExpression("j = ++i"));
        assertEquals(true, validator.ValidExpression("x = i++ + 5"));
        assertEquals(true, validator.ValidExpression("y = 5 + 3 * 10"));
        assertEquals(true, validator.ValidExpression("i += y"));
    }
    
    @Test
    public void testGetResult() {
        Calculator calculator=new Calculator();
        assertEquals(0, calculator.calculate("i = 0"));
        assertEquals(1, calculator.calculate("j = ++i"));
        assertEquals(6, calculator.calculate("x = i++ + 5"));
        assertEquals(35, calculator.calculate("y = 5 + 3 * 10"));
        assertEquals(37, calculator.calculate("i += y"));
        assertEquals("(x=6,i=37,y=35,j=1)", calculator.getResult());

    }

    @Test
    public void testSimpleAssignment() {
        Calculator calculator=new Calculator();
        assertEquals(5, calculator.calculate("i = 5"));
        assertEquals(2, calculator.calculate("j = 2"));
        assertEquals(6, calculator.calculate("i = i + j"));
    }

    @Test
    public void testPreIncremental() {
        Calculator calculator=new Calculator();
        assertEquals(5, calculator.calculate("i = 5"));
        assertEquals(6, calculator.calculate("j ++i"));
    }

    @Test
    public void testPostIncremental() {
        Calculator calculator=new Calculator();
        assertEquals(5, calculator.calculate("i = 5"));
        assertEquals(5, calculator.calculate("j = i++"));
    }

    @Test
    public void testPlusEqual() {
        Calculator calculator=new Calculator();
        assertEquals(3, calculator.calculate("i = 3"));
        assertEquals(3, calculator.calculate("j += i"));
    }

    @Test
    public void testMultiplyOperator() {
        Calculator calculator=new Calculator();
        assertEquals(3, calculator.calculate("i = 3"));
        assertEquals(15, calculator.calculate("j = i * 5"));
    }

    @Test
    public void testDeepExpression() {
        Calculator calculator=new Calculator();
        assertEquals(3, calculator.calculate("i = 3"));
        assertEquals(4, calculator.calculate("j = 4"));
        assertEquals(19, calculator.calculate("k = i + j + i * j"));
    }
}
