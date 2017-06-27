package ca.splantio.ioucalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    ArrayList<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        try {
            Intent intent = getIntent();
            people = (ArrayList<Person>) intent.getSerializableExtra("PEOPLE");
        } catch (Exception e) {
            people = new ArrayList<>();
        }

        LinearLayout llResults = (LinearLayout) findViewById(R.id.ll_results);

        for (Person p : people) {

            View personDebtLayout = LayoutInflater.from(this).inflate(R.layout.layout_debtee, llResults, false);
            llResults.addView(personDebtLayout);

            TextView debter = (TextView) personDebtLayout.findViewById(R.id.tv_ower_name);
            debter.setText(p.getName() + " owes:");

            LinearLayout debts = (LinearLayout) personDebtLayout.findViewById(R.id.ll_debts);
            Boolean owesAtLeastOnePerson = false;
            for (Debt debt : p.getDebts()) {
                if (debt.getAmount() != 0) {
                    owesAtLeastOnePerson = true;
                    View debtView = LayoutInflater.from(this).inflate(R.layout.listitem_debt, debts, false);
                    llResults.addView(debtView);

                    TextView personOwed = (TextView) debtView.findViewById(R.id.tv_owed_to);
                    personOwed.setText(debt.getPersonOwed().getName());
                    TextView amountOwed = (TextView) debtView.findViewById(R.id.tv_amount_owed);
                    amountOwed.setText(formatFloat(debt.getAmount()));
                }
            }

            if (!owesAtLeastOnePerson) {
                debter.setText(p.getName() + " owes nobody");
            }


        }
    }

    public String formatFloat(float pay) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return "$"+formatter.format(pay);
    }

}
