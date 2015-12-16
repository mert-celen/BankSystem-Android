package mert.sena.banksystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WithdrawMoneyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_money);
        Account dummy = app.accounts.getObj(app.currentid);
        TextView currentMoney = (TextView)findViewById(R.id.currentMoney);
        TextView welcomeText = (TextView)findViewById(R.id.welcomeText);
        welcomeText.setText(dummy.getName());
        currentMoney.setText("Current amount : " + Double.toString(dummy.getAmount()));

        Button withdrawButton = (Button)findViewById(R.id.withdrawButton);


        withdrawButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        boolean flag;
                        TextView moneyToDraw = (TextView)findViewById(R.id.moneyToDraw);
                        TextView statusLabel = (TextView)findViewById(R.id.statusLabel);

                        flag = app.accounts.getObj(app.currentid).removeMoney(Double.parseDouble
                                (String.valueOf(moneyToDraw.getText())));
                        if(flag){
                            statusLabel.setText("Status: Withdraw completed!");
                            //currentMoney.setText("Current amount : " + Double.toString(dummy.getAmount()));
                            WithdrawMoneyActivity.this.onBackPressed();

                        }

                        else
                            statusLabel.setText("Status: Not enough money");
                    }
                }
        );

    }

}
