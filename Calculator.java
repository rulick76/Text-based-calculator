package taboola.israelrozen.solution_1;
import java.util.ArrayList;
import java.util.HashMap;

public class Calculator {

    private HashMap<Character, Integer> variables = new HashMap<>();

    public int calculate(String expr) {
        ExpressionBuilder builder = new ExpressionBuilder(new ExpressionValidator());
        HashMap<Character, Integer> postIncrements = new HashMap<>();
        HashMap<Character, Integer> calcVariables = new HashMap<>(variables);

        int result = builder.build(expr, calcVariables).resolve(postIncrements);
        
        calcVariables.put(builder.getVariable(), result);

        for (char key : postIncrements.keySet()) {
            int addBy = postIncrements.get(key);
            calcVariables.put(key, calcVariables.get(key) + addBy);
        }

        variables = calcVariables;
        return result;
    }

    public String getResult() {
        StringBuilder sb =new StringBuilder();

        sb.append("(");
        ArrayList<Character> keys = new ArrayList<>(variables.keySet());
        for (int i = 0; i < keys.size(); i++) {
            sb.append(keys.get(i) + "=" + variables.get(keys.get(i)));
            if (i < keys.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");

        return sb.toString();
    }
}