package ca.splantio.ioucalculator;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blackcat.currencyedittext.CurrencyEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context ctxt;
    LinearLayout llPeople;
    Button btnNewPerson;

    ConstraintLayout btnSubmit;
    ArrayList<Person> people = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctxt = this;
        llPeople = (LinearLayout) findViewById(R.id.ll_people);

        btnNewPerson = (Button) findViewById(R.id.btnNewPerson);
        btnNewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View newPerson = LayoutInflater.from(ctxt).inflate(R.layout.layout_person_input, llPeople, false);
                llPeople.addView(newPerson);
            }
        });

        btnSubmit = (ConstraintLayout) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                people.clear();
                for (int i = 0; i < llPeople.getChildCount(); i++) {
                    View personLayout = llPeople.getChildAt(i);
                    EditText etName = (EditText) personLayout.findViewById(R.id.etName);
                    CurrencyEditText etPay = (CurrencyEditText) personLayout.findViewById(R.id.etCurrency);
                    String name = etName.getText().toString().trim();
                    float pay = etPay.getRawValue()/100f;

                    if ((name.length() != 0)) {
                        Person person = new Person(name);
                        person.addPayment(pay);
                        people.add(person);
                    }
                }

                people = OweCalculator.CalculateDebts(people);

                Intent i = new Intent(ctxt, ResultActivity.class);
                i.putExtra("PEOPLE", people);
                startActivity(i);
            }
        });



    }

}
