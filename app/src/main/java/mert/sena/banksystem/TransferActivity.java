package mert.sena.banksystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        Button sendMoney = (Button)findViewById(R.id.sendMoney);

        if(app.accounts.getObj(app.currentid).getAmount()<0){
            TextView statusLabel = (TextView)findViewById(R.id.statusLabel);
            statusLabel.setText("Status : You are broke!");
            sendMoney.setEnabled(false);
        }

        sendMoney.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        TextView dummy = (TextView)findViewById(R.id.receiverBox);
                        String receiverId = dummy.getText().toString();
                        TextView amount = (TextView)findViewById(R.id.amountBox);
                        TextView message = (TextView)findViewById(R.id.messageBox);

                        boolean flag;
                        if(app.accounts.findAccount(receiverId)!=-1){
                            flag = app.accounts.transfer(app.accounts.findAccount(receiverId)
                            , app.currentid,Integer.parseInt(amount.getText().toString()),
                                    "Incoming EFT from " + app.accounts.getObj(app.currentid).getName() + " " +
                                            message.getText().toString());
                            if(flag){
                                TextView statusLabel = (TextView)findViewById(R.id.statusLabel);
                                statusLabel.setText("Status = Transfer completed!");
                            }else{
                                TextView statusLabel = (TextView)findViewById(R.id.statusLabel);
                                statusLabel.setText("Status : Not enough money!");
                            }
                        }else {
                            TextView statusLabel = (TextView)findViewById(R.id.statusLabel);
                            statusLabel.setText("Status : Receiver not found!");
                        }
                    }
                }
        );
    }
}
