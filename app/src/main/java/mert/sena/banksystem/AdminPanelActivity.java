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

public class AdminPanelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        init();

        final Button removeAccount = (Button)findViewById(R.id.removeAccount);
        Button payDept = (Button)findViewById(R.id.payDept);

        removeAccount.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                deleteAccount();
            }
        });

        payDept.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                double sum=0;
                for (Account a: app.accounts.list){
                    sum+=a.getDept();
                    a.payDept(a.getDept());
                    init();
                }
                TextView statusLabel = (TextView)findViewById(R.id.statusLabel);
                statusLabel.setText("Status : "+ sum + " dept gone!"); //maybe better explanation
            }
        });


    }

    public void init(){
        TextView totalDept = (TextView)findViewById(R.id.totalDept);
        TextView totalMoney = (TextView)findViewById(R.id.totalMoney);
        TextView userCount  = (TextView)findViewById(R.id.userCount);

        totalDept.setText("Total Dept : " + String.valueOf(app.accounts.totalDept()));
        totalMoney.setText("Total Money : " + String.valueOf(app.accounts.totalMoney()));
        userCount.setText("Total Users : " + String.valueOf(app.accounts.list.size()));
    }

    private void deleteAccount(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter username!");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (app.accounts.findAccount(input.getText().toString()) != -1) {
                    app.accounts.removeAccount(app.accounts.findAccount(input.getText().toString()));
                    TextView statusLabel = (TextView) findViewById(R.id.statusLabel);
                    statusLabel.setText("Status : User deleted!");
                    init();
                } else {
                    TextView statusLabel = (TextView) findViewById(R.id.statusLabel);
                    statusLabel.setText("Status : User not found!");
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


    @Override
    public void onResume(){
        super.onResume();
        init();
    }
}
