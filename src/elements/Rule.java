package elements;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marek
 */
public class Rule {

    private Query ruleName;
    private List<RulePart> ruleParts;

    public Rule(RulePart... args) throws Exception {
        //first and second params must be queries, and next ones will be operation and query alternating
        if (args[0].getQuery() == null) {
            throw new Exception("You need to name your rule");
        }
        if (args[1].getQuery() == null) {
            throw new Exception("Second argument must be a fact or a query");
        }
        if (args.length % 2 != 0) {
            throw new Exception("You need more arguments");
        }
        for (int i = 2; i < args.length; i++) {
            if (i % 2 == 0) {
                if (args[i].getOperation() == null || args[i].getOperation().isEmpty()) {
                    throw new Exception("You need to alternate operation and query");
                }
            } else if (args[i].getQuery() == null) {
                throw new Exception("You need to alternate operation and query");
            }
        }
        //checks are done so I just write them into the variables for use in outside queries
        ruleName = args[0].getQuery();
        ruleParts = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            ruleParts.add(args[i]);
        }
    }

    public Query getRuleName() {
        return ruleName;
    }

    public List<RulePart> getRuleParts() {
        return ruleParts;
    }

}
