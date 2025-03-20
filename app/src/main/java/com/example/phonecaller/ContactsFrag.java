package com.example.phonecaller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class ContactsFrag extends Fragment {
    LinearLayout linearLayout;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contacts, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.AddNewContact);
        linearLayout = view.findViewById(R.id.contactsLinear);

        loadPhoneNumbersFromSharedPreferences();
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(),addNewContact.class);
            startActivity(intent);

        });


    }

    private void loadPhoneNumbersFromSharedPreferences() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("Contact", Context.MODE_PRIVATE);
        String nickName = sharedPreferences.getString("Contacts", "");

        if (nickName != null && !nickName.isEmpty()) {
            String[] numberArray = nickName.split(",");
            for (String nick : numberArray) {                                                         
                addCallButton(nick);

            }
        }
    }

    private void addCallButton(String number) {
        Button button = new Button(requireContext());
        button.setText(number);
        button.setBackgroundColor(Color.TRANSPARENT);
        button.setTextColor(Color.WHITE);
        button.setTextSize(40);
        button.setGravity(0);

        button.setOnClickListener(v -> {


            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + number));
            startActivity(callIntent);

        });

        linearLayout.addView(button);

    }

    public void getNickname(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("Contact", Context.MODE_PRIVATE);
        String nickName = sharedPreferences.getString("Contacts", "");
    }
}