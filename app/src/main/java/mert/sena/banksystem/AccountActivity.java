package mert.sena.banksystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        this.init();

    }

    public void init(){
        TextView name = (TextView)findViewById(R.id.welcomeText);
        TextView dept = (TextView)findViewById(R.id.deptText);
        TextView amount = (TextView)findViewById(R.id.amountText);
        TextView limit = (TextView)findViewById(R.id.limitText);

        Button withdrawButton = (Button)findViewById(R.id.withdrawButton);
        Button payDeptButton = (Button)findViewById(R.id.payDeptButton);
        Button transferButton = (Button)findViewById(R.id.transferButton);

        Account dummy = app.accounts.getObj(app.currentid);

        name.setText(dummy.getName());
        dept.setText("Total Dept : " + String.valueOf(dummy.getDept()));
        amount.setText("Total Money : " + String.valueOf(dummy.getAmount()));
        limit.setText("Total Limit : " + String.valueOf(dummy.getLimit()));

        withdrawButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent1 = new Intent(AccountActivity.this, WithdrawMoneyActivity.class);
                        startActivity(intent1);
                    }
                }
        );

        payDeptButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                    }
                }
        );

        transferButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        app.currentid=0;
        Intent intent1 = new Intent(AccountActivity.this,MainActivity.class);
        startActivity(intent1);
    }
}
