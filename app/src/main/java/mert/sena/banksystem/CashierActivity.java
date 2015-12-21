package mert.sena.banksystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CashierActivity extends AppCompatActivity {
private boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier);
        TextView username = (TextView)findViewById(R.id.usernameText);
        username.setFocusable(true);
        System.out.println("deneme");
        Button askpinButton = (Button)findViewById(R.id.askpinButton);
        askpinButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        TextView username = (TextView)findViewById(R.id.usernameText);
                        TextView amount = (TextView)findViewById(R.id.amountText);
                        TextView statusLabel = (TextView)findViewById(R.id.statusLabel);
                        if(app.accounts.isUserAvailable(username.getText().toString())){
                            statusLabel.setText("Status : User not found!");
                        }else if(Integer.parseInt(amount.getText().toString())<=0 || amount.getText().length()==0){
                            statusLabel.setText("Status : Enter an amount");
                        }
                        else{
                            askforPin(username.getText().toString(), Double.parseDouble(amount.getText().toString()));
                        }
                    }
                }
        );
    }

    public boolean askforPin(String s1,double a1){
        final String s = s1;
        final double amount = a1;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your Pin Code for "+ amount + " amount of payment.");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            TextView statusLabel = (TextView) findViewById(R.id.statusLabel);

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (app.accounts.getObj(app.accounts.findAccount(s)).getPinCode()
                        == Integer.parseInt(input.getText().toString())) {
                    app.accounts.addDept(amount,s);
                    statusLabel.setText("Status : Dept added!");
                } else {
                    statusLabel.setText("Status : Canceled!");
                    flag = false;
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
        return flag;
    }
}
