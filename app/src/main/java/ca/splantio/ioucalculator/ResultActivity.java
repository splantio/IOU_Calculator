package ca.splantio.ioucalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

        RecyclerView recView = (RecyclerView) findViewById(R.id.recv_results);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(mLayoutManager);

        // specify an adapter
        MyAdapter mAdapter = new MyAdapter(this, people);
        recView.setAdapter(mAdapter);

    }


}
