package elements;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marek
 */
public class Fact {

    private String functor;
    private List<String> childrenList;

    public Fact(String... args) throws Exception {
        //first argument is the functor
        if (args.length < 2) {
            throw new Exception("You need to have a name and at least 1 argument");
        }
        functor = args[0];
        childrenList = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            childrenList.add(args[i]);
        }
    }

    public String getFunctor() {
        return functor;
    }

    public List<String> getChildrenList() {
        return childrenList;
    }

}
