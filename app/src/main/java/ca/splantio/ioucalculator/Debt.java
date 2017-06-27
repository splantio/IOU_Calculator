package ca.splantio.ioucalculator;

import java.io.Serializable;

/**
 * Created by spencer on 2017-06-26.
 */

public class Debt implements Serializable {

    private float amount;
    private Person personOwed;

    public Debt(float amount, Person owed) {
        this.amount = amount;
        this.personOwed = owed;
    }

    public float getAmount() {
        return amount;
    }

    public Person getPersonOwed() {
        return personOwed;
    }

    public void setAmount(float f) {
        this.amount = f;
    }
}
