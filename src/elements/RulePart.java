package elements;

/**
 *
 * @author Marek
 */
public class RulePart {

    private Query query;
    private String operation;

    public RulePart(String operation, Query query) throws Exception {
        this.operation = operation;
        this.query = query;
        if ((query == null) && !operation.toLowerCase().equals("and") && !operation.toLowerCase().equals("or")) {
            throw new Exception("You need to specify AND or OR");
        }
    }

    public Query getQuery() {
        return query;
    }

    public String getOperation() {
        return operation;
    }

}
