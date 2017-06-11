package main;

import brain.SmartTransistor;
import elements.Fact;
import elements.Query;
import elements.Rule;
import elements.RulePart;
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
            Fact f1 = new Fact("father", "a", "b", "c");
            Fact f2 = new Fact("father", "a", "b");
            Fact f3 = new Fact("father", "a", "a");
            Fact f4 = new Fact("father", "z", "a");
            Fact f5 = new Fact("father", "b", "c");
            Fact f6 = new Fact("father", "b", "a");

            Query q1 = new Query("father", "b");
            Query q2 = new Query("father", "a", "XX");
            Query q3 = new Query("father", "Example", "a");
            Query q4 = new Query("father", "XX", "XX", "XX", "XX");

            Rule r1 = new Rule(new RulePart(null, new Query("grandfather", "A", "B")),
                    new RulePart(null, new Query("father", "A", "C")),
                    new RulePart("and", null),
                    new RulePart(null, new Query("father", "C", "B")));

            //the actual Prolog implementation
            SmartTransistor computer = new SmartTransistor();

            computer.addFact(f1);
            computer.addFact(f2);
            computer.addFact(f3);
            computer.addFact(f4);
            computer.addFact(f5);
            computer.addFact(f6);

            String result = computer.query(q1);
            System.out.println(result);
            result = computer.query(q2);
            System.out.println(result);
            result = computer.query(q3);
            System.out.println(result);
            result = computer.query(q4);
            System.out.println(result);
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        } finally {
//            input.nextLine();
        }
    }

}
