package com.example.phonecaller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class addNewContact extends AppCompatActivity {
    Button back, confirm;
    EditText setNumber, setName;
    String number;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            back = findViewById(R.id.buttonToReturnFromAddContact);
            confirm = findViewById(R.id.confirmContact);
            setName  = findViewById(R.id.nameForContact);
            setNumber = findViewById(R.id.NumberFroContacts);


            confirm.setOnClickListener(view -> saveContact());

            back.setOnClickListener(view -> {
                finish();
            });



            return insets;


        });


    }
    private void saveContact() {
        SharedPreferences sharedPreferences = getSharedPreferences("Contact", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String name = setName.getText().toString().trim();
        String number = setNumber.getText().toString().trim();

        if (!name.isEmpty() && !number.isEmpty()) {

            String currentContacts = sharedPreferences.getString("Contacts", "");
            String updatedContacts = name + ":" + number + ";" + currentContacts;
            editor.putString("Contacts", updatedContacts);
            editor.apply();
        }

        finish();
    }
}