package com.example.phonecaller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;


public class CallWindowActivity extends AppCompatActivity {

    String phoneNumber;
    ImageButton endCall, muteButton, keyboardButton, speakButton;
    TelephonyManager telephonyManager;

    TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_call_window);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);




            endCall = findViewById(R.id.endCallButton);
            muteButton = findViewById(R.id.Mute);
            keyboardButton = findViewById(R.id.keyBoardButton);
            speakButton = findViewById(R.id.speakerButton);
            number = findViewById(R.id.numberPhone);


            Intent intent = getIntent();
            phoneNumber = intent.getStringExtra("tel");
            number.setText(phoneNumber);


            endCall.setOnClickListener(f -> finish());


            return insets;

        });
    }
   }