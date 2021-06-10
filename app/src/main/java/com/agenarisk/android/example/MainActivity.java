package com.agenarisk.android.example;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.agenarisk.android.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mandatory AgenaRisk Android initialization
        try {
            AgenaRiskHelper.init("D572AC-78FFC3-451984-D4B4D4-2F5D3A-419E78");
        }
        catch (Exception ex){
            System.out.println("AgenaRisk initialization failed");
            ex.printStackTrace();
        }

        final Activity activity = this;

        ((Button) findViewById(R.id.button_example1)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Example1(activity).run();
            }
        });

        ((Button) findViewById(R.id.button_example2)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Example2(activity).run();
            }
        });

        ((Button) findViewById(R.id.button_exit)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                quit(null);
            }
        });
    }

    public void quit(View view) {
        this.finishAffinity();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask();
        } else {
            finish();
        }
        System.exit(0);
    }

}