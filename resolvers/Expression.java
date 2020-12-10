package taboola.israelrozen.solution_1.resolvers;
import taboola.israelrozen.solution_1.enums.Operator;

import java.util.HashMap;

public class Expression implements Resolverable {
    private final Resolverable left;
    private final Operator op;
    private final Resolverable right;

    public Expression(Resolverable left, Operator op, Resolverable right) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public int resolve(HashMap<Character, Integer> postIncrements) {
        int leftValue = left.resolve(postIncrements);
        int rightValue = right.resolve(postIncrements);

        switch (op) {
            case ADD:
                return leftValue + rightValue;
            case SUB:
                return leftValue - rightValue;
            case MUL:
                return leftValue * rightValue;
        }
        return 0;
    }
}
