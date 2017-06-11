package brain;

import elements.Fact;
import elements.Query;
import elements.Rule;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Marek
 */
public class SmartTransistor {

    private final List<Fact> facts;

    public SmartTransistor() {
        facts = new ArrayList<>();
    }

    public void addFact(Fact newFact) {
        facts.add(newFact);
    }

    public String query(Query query) {
        //if I am not missing arguments, I just get the whole list of result that are possible and return the first matching item
        if (!query.isIsMissingArgs()) {
            return String.valueOf(isInFacts(query));
        }
        //I must return a string that maps the missing args to their value in the first item returned by the method
        List<Fact> resultList = findMissingArgs(query);
        String resultString = "";
        //if there's no result, return false
        if (resultList.isEmpty()) {
            return "false";
        }
        //if i have multiple results, the only one I return is the first one
        Fact firstResult = resultList.get(0);
        for (int i = 0; i < query.getMissingArgs().length; i++) {
            if (query.getMissingArgs()[i] == 1) {
                resultString += query.getChildrenList().get(i) + ": " + firstResult.getChildrenList().get(i) + ' ';
            }
        }
        return resultString;
    }

    private boolean isInFacts(Query query) {
        //select only facts that have the same functors and same number of arguments
        List<Fact> resultingFacts = facts.stream().filter(f -> f.getFunctor().equals(query.getFunctor()) && f.getChildrenList().size() == query.getChildrenList().size()).collect(Collectors.toList());
        //iterate through the resulting facts - if they are found according to the children 
        //list and missing arguments add the results to the return list
        for (Fact fact : resultingFacts) {
            boolean factExists = true;
            //find out if for below can be written as:
            //var matchingFact = !fact.ChildrenList.Where((t, i) => !t.Equals(query.ChildrenList[i])).Any();

            //iterate through the children of the fact and compare to the children list from args
            for (int i = 0; i < fact.getChildrenList().size(); i++) {
                //if the children are the same then move to next child
                if (fact.getChildrenList().get(i).equals(query.getChildrenList().get(i))) {
                    continue;
                }
                //if the children are not the same I can exit the loop and move to the next fact
                factExists = false;
                break;
            }
            if (factExists) {
                return true;
            }
        }
        return false;
    }

    private List<Fact> findMissingArgs(Query query) {
        List<Fact> resultList = new ArrayList<>();
        //select only facts that have the same functors and same number of arguments

        List<Fact> resultingFacts = facts.stream().filter(f -> f.getFunctor().equals(query.getFunctor()) && f.getChildrenList().size() == query.getChildrenList().size()).collect(Collectors.toList());

        //loop through each fact to find out if it fits the query
        for (Fact fact : resultingFacts) {
            boolean addToResult = true;
            //loop through the missing arguments - same length as args in the query - and if the arguments match add to result list
            for (int i = 0; i < query.getMissingArgs().length; i++) {
                String queryArg = query.getChildrenList().get(i);
                String factArg = fact.getChildrenList().get(i);
                //if the argument is missing(value 1) then go to next argument
                if (query.getMissingArgs()[i] == 1) {
                    continue;
                }
                //if the argument is not missing but is the same as the query argument go to next argument
                if (queryArg.equals(factArg)) {
                    continue;
                }
                //if the argument is not missing and is not the same as the query we move to next fact
                addToResult = false;
                break;
            }
            if (addToResult) {
                resultList.add(fact);
            }
        }
        return resultList;
    }

    public void addRule(Rule rule) {
        //I am sure that my rule will look like this RULE_FUNCTOR(ARGS),FUNCTOR(ARGS),AND/OR,FUNCTOR(ARGS),AND/OR,...
        //I will use the rule to generate new facts, PROLOG works in similar fashion, because rules are not possible to be added at runtime

    }
}
