package elements;

import java.util.List;
import utils.Utils;

/**
 *
 * @author Marek
 */
public class Query extends Fact {

    /**
     *
     * @param args
     * @throws Exception
     */
    public Query(String... args) throws Exception {
        super(args);
        isMissingArgs = false;
        findMissingArguments();
    }

    private int[] missingArgs;
    private boolean isMissingArgs;

    private void findMissingArguments() {
        List<String> childrenList = getChildrenList();
        missingArgs = new int[childrenList.size()];
        for (int i = 0; i < childrenList.size(); i++) {
            if (Utils.isUppercase(childrenList.get(i))) {
                missingArgs[i] = 1;
                isMissingArgs = true;
            } else {
                missingArgs[i] = 0;
            }
        }
    }

    public int[] getMissingArgs() {
        return missingArgs;
    }

    public boolean isIsMissingArgs() {
        return isMissingArgs;
    }

}
