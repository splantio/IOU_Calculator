package ca.splantio.ioucalculator;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by spencer on 2017-06-26.
 */

public class Person implements Serializable {

    private String name;
    private float paymentTotal = 0f;
    private ArrayList<Debt> debts;

    public Person(String name) {
        this.name = name;
        this.debts = new ArrayList<>();
    }

    public void addPayment(float pay) {
        this.paymentTotal += pay;
    }

    public String getName() {
        return name;
    }

    public float getPaymentTotal() {
        return paymentTotal;
    }

    public void addDebt(Debt debt) {
        debts.add(debt);
    }

    public ArrayList<Debt> getDebts() {
        return debts;
    }

}
