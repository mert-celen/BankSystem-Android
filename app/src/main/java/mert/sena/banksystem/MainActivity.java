package mert.sena.banksystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String adminPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        app.loadApp();
        app.accounts.addAccount("user", "Mert ÇELEN", "Bilkent Universitesi", 10000, "mert", "mert");

        Button loginButton = (Button)findViewById(R.id.loginButton);
        Button registerButton = (Button)findViewById(R.id.registerButton);
        Button adminButton = (Button)findViewById(R.id.adminButton);


        loginButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent1 = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent1);
                    }
                }
        );


        registerButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent1 = new Intent(MainActivity.this,RegisterPanelActivity.class);
                        startActivity(intent1);
                    }
                }
        );


        adminButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                            checkAdmin();
                            if(adminPassword.compareTo("aaaa")==0){
                                Intent intent1 = new Intent(MainActivity.this,AdminPanelActivity.class);
                                startActivity(intent1);
                                adminPassword = "";
                            }
                    }
                }
        );
    }

    private void checkAdmin(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Admin Password!");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adminPassword = input.getText().toString();
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

    public void onBackPressed() {
        //do nothing
    }
}
