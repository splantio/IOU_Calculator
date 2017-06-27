package ca.splantio.ioucalculator;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by spencer on 2017-06-26.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    LayoutInflater inflater;
    private Context ctxt;
    private ArrayList<Person> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView llResult;

        public ViewHolder(CardView ll) {
            super(ll);
            llResult = ll;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context, ArrayList<Person> people) {
        ctxt = context;
        mDataset = people;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        inflater = (LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CardView layout = (CardView) inflater.inflate(R.layout.layout_debtee, parent, false);

        ViewHolder vh = new ViewHolder(layout);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        TextView tvTitle = (TextView) holder.llResult.findViewById(R.id.tv_ower_name);
        LinearLayout llDebts = (LinearLayout) holder.llResult.findViewById(R.id.ll_debts);

        Person p = mDataset.get(position);
        tvTitle.setText(p.getName() + " owes:");

        Boolean owesAtLeastOnePerson = false;
        for (Debt debt : p.getDebts()) {
            if (debt.getAmount() != 0) {
                owesAtLeastOnePerson = true;

                inflater = (LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View debtView = inflater.inflate(R.layout.listitem_debt, null);
                llDebts.addView(debtView);

                TextView personOwed = (TextView) debtView.findViewById(R.id.tv_owed_to);
                personOwed.setText(debt.getPersonOwed().getName());
                TextView amountOwed = (TextView) debtView.findViewById(R.id.tv_amount_owed);
                amountOwed.setText(formatFloat(debt.getAmount()));

            }
        }

        if (!owesAtLeastOnePerson) {
            tvTitle.setText(p.getName() + " owes nobody");
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public String formatFloat(float pay) {
        DecimalFormat dFormat = new DecimalFormat("####,###,###.00");
        return "$" + dFormat.format(pay);
    }
}
