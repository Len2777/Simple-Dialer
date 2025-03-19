package com.example.phonecaller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CallFrag extends Fragment {
    String number;
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, plus, star, hashtag;
    ImageButton call, backspace;
    TextView text;

    private final ActivityResultLauncher<String> requestCallPermission =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    phoneCall();
                } else {
                    Log.e("CallFrag", "Permission denied for phone calls");
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_call, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        b0 = view.findViewById(R.id.zero);
        b1 = view.findViewById(R.id.button1);
        b2 = view.findViewById(R.id.button2);
        b3 = view.findViewById(R.id.button3);
        b4 = view.findViewById(R.id.button4);
        b5 = view.findViewById(R.id.button5);
        b6 = view.findViewById(R.id.button6);
        b7 = view.findViewById(R.id.button7);
        b8 = view.findViewById(R.id.button8);
        b9 = view.findViewById(R.id.button9);
        plus = view.findViewById(R.id.plus);
        star = view.findViewById(R.id.star);
        hashtag = view.findViewById(R.id.hashtag);
        call = view.findViewById(R.id.Call);
        backspace = view.findViewById(R.id.BackSpace);
        text = view.findViewById(R.id.textPhone);

        View.OnClickListener numberClickListener = v -> {
            Button button = (Button) v;
            text.append(button.getText().toString());
        };

        b0.setOnClickListener(numberClickListener);
        b1.setOnClickListener(numberClickListener);
        b2.setOnClickListener(numberClickListener);
        b3.setOnClickListener(numberClickListener);
        b4.setOnClickListener(numberClickListener);
        b5.setOnClickListener(numberClickListener);
        b6.setOnClickListener(numberClickListener);
        b7.setOnClickListener(numberClickListener);
        b8.setOnClickListener(numberClickListener);
        b9.setOnClickListener(numberClickListener);
        star.setOnClickListener(numberClickListener);
        hashtag.setOnClickListener(numberClickListener);
        plus.setOnClickListener(numberClickListener);

        call.setOnClickListener(v -> {
            checkCallPermission();

        });

        backspace.setOnClickListener(v -> {
            String currentText = text.getText().toString();
            if (!currentText.isEmpty()) {
                text.setText(currentText.substring(0, currentText.length() - 1));
            }
        });


    }

    public void checkCallPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            phoneCall();
        } else {
            requestCallPermission.launch(Manifest.permission.CALL_PHONE);
        }
    }

    private void phoneCall() {
        number = text.getText().toString();
        if (number.isEmpty()) {
            return;
        }

        savePhoneNumberToSharedPreferences(number);

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        startActivity(callIntent);



    }




    public void savePhoneNumberToSharedPreferences(String number) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("RecentCalls", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        String currentNumbers = sharedPreferences.getString("numbers", "");

        String updatedNumbers = number + "," + currentNumbers;


        editor.putString("numbers", updatedNumbers);
        editor.apply();
    }



}


