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
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class RecentFrag extends Fragment {

    LinearLayout linearLayout;

    TextView text;

    Button button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        linearLayout = view.findViewById(R.id.rec_con);

        loadPhoneNumbersFromSharedPreferences();
        text = view.findViewById(R.id.textForRec);
        button = view.findViewById(R.id.clear);

        button.setOnClickListener(v -> {
            clearPhoneNumbers();
            removeCallButtons();
        });


    }


    private void removeCallButtons() {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View view = linearLayout.getChildAt(i);
            if (view instanceof Button) {
                linearLayout.removeView(view);
                i--;
            }
        }
    }

    private void loadPhoneNumbersFromSharedPreferences() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("RecentCalls", Context.MODE_PRIVATE);
        String numbers = sharedPreferences.getString("numbers", "");

        if (numbers != null && !numbers.isEmpty()) {
            String[] numberArray = numbers.split(",");
            for (String number : numberArray) {
                addCallButton(number);

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
            savePhoneNumberToSharedPreferences(number);

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + number));
            startActivity(callIntent);

        });

        linearLayout.addView(button);

    }

    public void savePhoneNumberToSharedPreferences(String number) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("RecentCalls", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        String currentNumbers = sharedPreferences.getString("numbers", null);

        String updatedNumbers = number + "," + currentNumbers;


        editor.putString("numbers", updatedNumbers);
        editor.apply();


    }

    private void clearPhoneNumbers() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("RecentCalls", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("numbers");
        editor.apply();
    }

    private void restartFrag(){
        FragmentTransaction fg = getActivity().getSupportFragmentManager().beginTransaction();
        fg.detach(this);
        fg.attach(this);
        fg.commit();



    }
}