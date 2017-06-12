package elements;

/**
 *
 * @author Marek
 */
public class RulePart {

    private final Query query;
    private final String operation;

    public RulePart(String operation, Query query) throws Exception {
        this.operation = operation;
        this.query = query;
        if ((query == null) && !operation.toLowerCase().equals("and") && !operation.toLowerCase().equals("or")) {
            throw new Exception("Missing part of the rule");
        }
    }

    public Query getQuery() {
        return query;
    }

    public String getOperation() {
        return operation;
    }

}
