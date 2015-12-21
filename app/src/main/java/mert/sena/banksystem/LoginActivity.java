package mert.sena.banksystem;

import android.app.AlertDialog;
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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        TextView username = (TextView)findViewById(R.id.usernameBox);
        username.requestFocus();
        Button loginButton = (Button)findViewById(R.id.loginButton);
        final Button forgotPw = (Button)findViewById(R.id.forgotPw);

        loginButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        TextView username = (TextView)findViewById(R.id.usernameBox);
                        TextView password = (TextView)findViewById(R.id.passwordBox);

                        int index = app.accounts.findAccount(username.getText().toString());
                        if(username.getText().length()==0){
                            TextView t = (TextView)findViewById(R.id.statusLabel);
                            t.setText("Status : Enter something!");
                        }else if(index==-1){
                            TextView t = (TextView)findViewById(R.id.statusLabel);
                            t.setText("Status : Account not found!");
                        }else{
                            if(app.accounts.checkAccount(username.getText().toString(), password.getText().toString()))
                            {
                                TextView t = (TextView)findViewById(R.id.statusLabel);
                                t.setText("Login...");
                                Intent intent1 = new Intent(LoginActivity.this,AccountActivity.class);
                                startActivity(intent1);
                            }
                            else{
                                TextView t = (TextView)findViewById(R.id.statusLabel);
                                t.setText("Status : Wrong Password!");
                            }
                        }

                    }
                }
        );

        forgotPw.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        TextView username = (TextView)findViewById(R.id.usernameBox);
                        TextView t = (TextView)findViewById(R.id.statusLabel);
                        if(username.getText().toString().length() == 0){
                            t.setText("Status : Enter your username");
                        }else {
                            if(app.accounts.isUserAvailable(username.getText().toString())){
                                t.setText("Status : User not found!");
                            }else {
                                recoverPw();
                            }
                        }
                    }
                }
        );

    }

    private void recoverPw(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your Pin");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            TextView statusLabel = (TextView)findViewById(R.id.statusLabel);
            TextView username = (TextView)findViewById(R.id.usernameBox);
            public void onClick(DialogInterface dialog, int which) {
                boolean flag = app.accounts.checkPin(username.getText().toString(),
                        Integer.parseInt(input.getText().toString()));
                if (flag) {
                    newpw(username.getText().toString());
                } else {
                    statusLabel.setText("Status : Wrong Pin!");
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

    private void newpw(String u){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new password");
        final EditText input = new EditText(this);
        final String username = u;
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView statusLabel = (TextView) findViewById(R.id.statusLabel);
                if (input.getText().toString().length() >= 6) {
                    app.accounts.setPassword(username, input.getText().toString());
                    statusLabel.setText("Status : Password Changed.");
                }else{
                    statusLabel.setText("Status : Must be >=6 character");
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
