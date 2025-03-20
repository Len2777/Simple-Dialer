package com.example.phonecaller;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.karumi.dexter.Dexter;

public class MainActivity extends AppCompatActivity {
    int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            if(a  < 1) {
                RecentFrag recentFrag = new RecentFrag();
                setFrag(recentFrag);
                a+=1;

            }
            return insets;





        });
    }

    public void recCalled(View v) {
        RecentFrag recentFrag = new RecentFrag();
        setFrag(recentFrag);
    }

    public void contacts(View v) {
        ContactsFrag contactsFrag = new ContactsFrag();
        setFrag(contactsFrag);

    }

    public void call(View v) {
        CallFrag callFrag = new CallFrag();
        setFrag(callFrag);

    }

    private void setFrag(Fragment frag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.placeChange, frag);
        ft.addToBackStack(null);
        ft.commit();

    }


}