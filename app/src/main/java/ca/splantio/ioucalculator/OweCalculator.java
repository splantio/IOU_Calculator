package ca.splantio.ioucalculator;


import java.util.ArrayList;

/**
 * Created by spencer on 2017-06-26.
 */

public class OweCalculator {


    public static ArrayList<Person> CalculateDebts(ArrayList<Person> people) {

        // Give everyone their debts
        for (Person p : people) {
            // divide payment by size of person list. Everybody owes 'p' this amount
            float whatIsOwed = p.getPaymentTotal() / people.size();

            for (Person p2 : people) {
                if (!p.equals(p2)) {
                    Debt debt = new Debt(whatIsOwed, p);
                    p2.addDebt(debt);
                }
            }
        }

        // Remove redundancies
        for (Person p : people) {

            for (Debt debt : p.getDebts()) {

                float amountOwed = debt.getAmount();
                float amountPayed = p.getPaymentTotal() / people.size();
                float newDebt = amountOwed - amountPayed;
                if (newDebt < 0) {
                    newDebt = 0;
                }
                debt.setAmount(newDebt);

            }
        }


        return people;

    }


}
