package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPaymentMethod extends AppCompatActivity implements View.OnClickListener {

    private Button completeSignUp;

    private String[] creditCardInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_method);

        completeSignUp = (Button)findViewById(R.id.completeSignUp);
        completeSignUp.setOnClickListener(this);

        creditCardInfo = new String[]{((EditText)findViewById(R.id.nameOnCard)).toString(),
                                      ((EditText) findViewById(R.id.cardNumber)).toString(),
                                      ((EditText)findViewById(R.id.expirationDate)).toString(),
                                      ((EditText) findViewById(R.id.CVV)).toString()};
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.completeSignUp:
                String[] textFields = getIntent().getExtras().getStringArray("textFields");

                String firstName = textFields[0].split(" ")[0];
                String lastName = textFields[0].split(" ")[1];

                CreditCard cc = new CreditCard(
                        creditCardInfo[0],
                        Long.parseLong(creditCardInfo[1]),
                        creditCardInfo[2],
                        Integer.parseInt(creditCardInfo[3]));
                Client client = new Client(
                        UserRole.CLIENT,
                        firstName,
                        lastName,
                        textFields[1],
                        textFields[2],
                        textFields[3],
                        cc
                );
                break;
        }
    }
}