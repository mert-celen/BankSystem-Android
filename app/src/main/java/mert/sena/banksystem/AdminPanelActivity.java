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
    private int currentAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        Button removeAccount = (Button)findViewById(R.id.removeAccount);
        Button payDepts = (Button)findViewById(R.id.payDept);

        removeAccount.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                getAccount();
                app.accounts.removeAccount(currentAccount);
            }
        });

        payDepts.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                double sum=0;
                for (Account a:app.accounts.list){
                    sum+=a.getDept();
                    a.payDept(a.getDept());
                }
                TextView statusLabel = (TextView)findViewById(R.id.statusLabel);
                statusLabel.setText("Status : "+ sum + " amount of dept gone!"); //maybe better explanation
            }
        });


    }

    private void getAccount(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Admin Password!");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentAccount = app.accounts.findAccount(input.getText().toString());
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
