package mert.sena.banksystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        while(app.accounts.getObj(app.currentid).hasmoreStack()){
            displayMessage(app.accounts.getObj(app.currentid).getfromStack());
        }

        displayMessage("Welcome " + app.accounts.getObj(app.currentid).getName());
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
                        withdrawMoney();
                    }
                }
        );

        payDeptButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        payDept();
                    }
                }
        );

        transferButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent1 = new Intent(AccountActivity.this, TransferActivity.class);
                        startActivity(intent1);
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

    public void displayMessage(String s){
        Context context = getApplicationContext();
        CharSequence text = s;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onResume(){
        super.onResume();
        init();
    }

    private void payDept(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Amount");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean flag = app.accounts.payDept(app.currentid, Integer.parseInt(input.getText().toString()));
                TextView statusLabel = (TextView)findViewById(R.id.statusLabel);
                if (flag) {
                    statusLabel.setText("Status : "+ Integer.parseInt(input.getText().toString()) + " dept paid.");
                    init();
                } else {
                    statusLabel.setText("Status : Not enough money!");
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
    }

    private void withdrawMoney(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Amount");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean flag = app.accounts.getObj(app.currentid).removeMoney(Double.parseDouble
                        (input.getText().toString()));
                TextView statusLabel = (TextView)findViewById(R.id.statusLabel);
                if (flag) {
                    statusLabel.setText("Status : "+ Integer.parseInt(input.getText().toString()) + " money withdrawn.");
                    init();
                } else {
                    statusLabel.setText("Status : Not enough money!");
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
    }
}
