package mert.sena.banksystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Firebase firebase;
    private String adminPassword = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        init();
    }

    public void init() {

        //init
        if (app.flag) {
            app.loadApp();
            app.flag = false;
        }

        app.accounts.addAccount("Mert ÇELEN", "Bilkent Universitesi", 10000, "mert", "mert",1111);
        app.accounts.addAccount("Sena Altun", "Bilkent Universitesi", 10000, "sena", "sena",1111);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button registerButton = (Button) findViewById(R.id.registerButton);
        TextView adminButton = (TextView) findViewById(R.id.currentButton);
        final TextView updatesText = (TextView)findViewById(R.id.updatesText);
        Log.i("mertFilter","deneme");
        firebase = new Firebase("https://sweltering-torch-6571.firebaseio.com/Testing");
        updatesText.setText(firebase.getKey().toString());

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String newStr = (String)dataSnapshot.getValue();
                updatesText.setText(newStr);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        loginButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
//                        Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
//                        startActivity(intent1);
                        Firebase database = new Firebase("https://sweltering-torch-6571.firebaseio.com/");
                        database.createUser("deneme", "deneme", new Firebase.ValueResultHandler<Map<String, Object>>() {
                            @Override
                            public void onSuccess(Map<String, Object> result) {
                                Log.i("mertFilter", "user added");
                                System.out.println("user added");
                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                Log.i("mertFilter", "error");
                                System.out.println("hata");
                            }
                        });
                    }
                }
        );


        registerButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent1 = new Intent(MainActivity.this, RegisterPanelActivity.class);
                        startActivity(intent1);
                    }
                }
        );


        adminButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        checkAdmin();
                    }
                }
        );
    }

    private void checkAdmin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Admin Password!");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adminPassword = input.getText().toString();
                if (adminPassword.compareTo("aaaa") == 0) {

                    Intent intent1 = new Intent(MainActivity.this, AdminPanelActivity.class);
                    startActivity(intent1);
                    adminPassword = "";
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

    public void onBackPressed() {
        //do nothing
    }

}
