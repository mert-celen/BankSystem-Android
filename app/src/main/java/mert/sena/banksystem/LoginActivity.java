package mert.sena.banksystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        TextView username = (TextView)findViewById(R.id.usernameBox);
        username.requestFocus();
        Button loginButton = (Button)findViewById(R.id.loginButton);

        loginButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        TextView username = (TextView)findViewById(R.id.usernameBox);
                        TextView password = (TextView)findViewById(R.id.passwordBox);

                        int index = app.accounts.findAccount(username.getText().toString());
                        if(index==-1){
                            TextView t = (TextView)findViewById(R.id.statusLabel);
                            t.setText("Account not found!");
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
                                t.setText("Wrong Password!");
                            }
                        }

                    }
                }
        );
    }
}
