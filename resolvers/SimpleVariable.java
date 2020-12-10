package taboola.israelrozen.solution_1.resolvers;

import java.util.HashMap;
import taboola.israelrozen.solution_1.exceptions.UndefinedVariableException;

public class SimpleVariable implements Resolverable {
    private final char var;
    private final HashMap<Character, Integer> map;

    private boolean increment = false;

    public SimpleVariable(char var, boolean increment, HashMap<Character, Integer> map) {
        this.var = var;
        this.map = map;
        this.increment = increment;
    }

    @Override
    public int resolve(HashMap<Character, Integer> postIncrements) {

        if (increment) {
            if (!postIncrements.containsKey(var)) 
                postIncrements.put(var, 0);
            postIncrements.put(var, postIncrements.get(var) + 1);
        }
        if (!map.containsKey(var)) 
            throw new UndefinedVariableException();

        return map.get(var);
    }
}