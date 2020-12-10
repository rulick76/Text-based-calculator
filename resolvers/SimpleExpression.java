package taboola.israelrozen.solution_1.resolvers;

import java.util.HashMap;

public class SimpleExpression implements Resolverable {
    private final int value;

    public SimpleExpression(int value) {
        this.value = value;
    }

    @Override
    public int resolve(HashMap<Character, Integer> postIncrements) {
        return value;
    }
}