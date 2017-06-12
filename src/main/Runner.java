package main;

import brain.SmartTransistor;
import elements.Fact;
import elements.Query;
import elements.Rule;
import elements.RulePart;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Marek
 */
public class Runner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            List<Fact> facts = new ArrayList<Fact>() {
                private static final long serialVersionUID = 1L;
                {
                    add(new Fact("father", "a", "b", "c"));
                    add(new Fact("father", "a", "b", "d"));
                    add(new Fact("father", "a", "b"));
                    add(new Fact("father", "a", "a"));
                    add(new Fact("father", "z", "a"));
                    add(new Fact("father", "b", "c"));
                    add(new Fact("father", "b", "a"));
                }
            };

            List<Query> queries = new ArrayList<Query>() {
                private static final long serialVersionUID = 1L;
                {
                    add(new Query("father", "b"));
                    add(new Query("father", "a", "XX"));
                    add(new Query("father", "Example", "a"));
                    add(new Query("father", "XX", "XX", "XX", "XX"));
                    add(new Query("father", "XX", "XX", "XX"));
                }
            };

            Rule r1 = new Rule(new RulePart(null, new Query("grandfather", "A", "B")),
                    new RulePart(null, new Query("father", "A", "C")),
                    new RulePart("and", null),
                    new RulePart(null, new Query("father", "C", "B")));

            //the actual Prolog implementation
            SmartTransistor computer = new SmartTransistor();

            facts.stream().forEach((fact) -> {
                computer.addFact(fact);
            });

            for (Query query : queries) {
                String result = computer.query(query);
                System.out.println(result);
            }

        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        } finally {
//            input.nextLine();
        }
    }

}
