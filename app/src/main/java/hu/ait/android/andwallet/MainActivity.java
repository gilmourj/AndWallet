package hu.ait.android.andwallet;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String CURRENT_BALANCE = "Balance: ";
    public static final String TOTAL_EXPENSES = "Expenses: ";
    public static final String TOTAL_INCOMES = "Incomes: ";


    @BindView(R.id.etIncOrExp)
    EditText etIncOrExp;
    @BindView(R.id.etMoney)
    EditText etMoney;
    @BindView(R.id.btToggle)
    ToggleButton btToggle;
    @BindView(R.id.layoutContent)
    LinearLayout layoutContent;
    FloatingActionButton fab;

    @BindView(R.id.summaryButton)
    Button summaryButton;

    TextView balanceStatement;
    int balance = 0;
    int expenses = 0;
    int incomes = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        balanceStatement = (TextView) findViewById(R.id.balanceStatement);
        balance = 0;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#bbbbbb")));

        ButterKnife.bind(this);
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }


    @OnClick(R.id.btSave)
    public void addPressed(Button Save) {
        final View budgetRow = getLayoutInflater().inflate(
                R.layout.money_row, null, false);


        if (etIncOrExp.getText().toString().length() == 0)
            etIncOrExp.setError(getString(R.string.title_error));
        else if (!isNumeric(etMoney.getText().toString())) {
            etMoney.setError(getString(R.string.valid_amount_error));
        } else {

            TextView tvType = budgetRow.findViewById(R.id.tvType);
            tvType.setText(etIncOrExp.getText());

            TextView tvMoney = budgetRow.findViewById(R.id.tvMoney);
            tvMoney.setText(etMoney.getText());


            ImageView ivMoneyType = budgetRow.findViewById(R.id.ivMoneyType);
            if (btToggle.isChecked()) {
                ivMoneyType.setImageResource(R.drawable.donation_512);
                int exp = Integer.parseInt(etMoney.getText().toString());
                balance = balance - exp;
                expenses = expenses + exp;
            } else {
                ivMoneyType.setImageResource(R.drawable.money_bag1600);
                int inc = Integer.parseInt(etMoney.getText().toString());
                balance = balance + inc;
                incomes = incomes + inc;
            }
            String balanceSt = getString(R.string.balanceString) + balance;
            balanceStatement.setText(balanceSt);


            layoutContent.addView(budgetRow);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    layoutContent.removeAllViewsInLayout();
                    balance = 0;
                    incomes = 0;
                    expenses = 0;
                    balanceStatement.setText("0");

                }
            });

        }
    }


    @OnClick(R.id.summaryButton)
    public void showSummary() {

        Intent intentResult = new Intent();
        intentResult.setClass(MainActivity.this, SummaryActivity.class);

        int currentBalance = balance;
        int totalExpenses = expenses;
        int totalIncome = incomes;

        intentResult.putExtra(CURRENT_BALANCE, currentBalance);
        intentResult.putExtra(TOTAL_EXPENSES, totalExpenses);
        intentResult.putExtra(TOTAL_INCOMES, totalIncome);


        startActivity(intentResult);

    }


}
