package hu.ait.android.andwallet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView totalIncome = (TextView) findViewById(R.id.tvIncome);
        TextView totalExpenses = (TextView) findViewById(R.id.tvExpense);
        TextView totalBalance = (TextView) findViewById(R.id.tvBalance);


        if ((getIntent().hasExtra(MainActivity.TOTAL_INCOMES) ||
                getIntent().hasExtra(MainActivity.TOTAL_EXPENSES)) &&
                getIntent().hasExtra(MainActivity.CURRENT_BALANCE)) {
            int incomes = getIntent().getIntExtra(MainActivity.TOTAL_INCOMES, 0);
            int expenses = getIntent().getIntExtra(MainActivity.TOTAL_EXPENSES, 0);
            int balance = getIntent().getIntExtra(MainActivity.CURRENT_BALANCE, 0);

            String inc = "$" + Integer.toString(incomes);
            String exp = "$" + Integer.toString(expenses);
            String bal = "$" + Integer.toString(balance);

            totalIncome.setText(inc);
            totalExpenses.setText(exp);
            totalBalance.setText(bal);
        } else {
            totalIncome.setText("0");
            totalExpenses.setText("0");
            totalBalance.setText("0");
        }
    }


}
