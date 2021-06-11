package com.agenarisk.android.example;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agenarisk.android.R;

import org.w3c.dom.Text;

import uk.co.agena.minerva.util.Environment;
import uk.co.agena.minerva.util.product.ProductError;

public class MainActivity extends AppCompatActivity {

    private static boolean licenseError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity activity = this;

        // Mandatory AgenaRisk Android initialization
        try {
            AgenaRiskHelper.init("D572AC-78FFC3-451984-D4B4D4-2F5D3A-419E78");
            Environment.getProductInstance();
        }
        catch (Exception ex){
            System.out.println("AgenaRisk initialization failed");
            ex.printStackTrace();
        }
        catch (Error error){
            TextView scrollText = (TextView) findViewById(R.id.text_scroll_content);
            scrollText.setText(error.getMessage());
            scrollText.setTextColor(Color.RED);
            licenseError = true;
        }

        Button button1 = (Button) findViewById(R.id.button_example1);
        button1.setEnabled(!licenseError);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Example1(activity).run();
            }
        });

        Button button2 = (Button) findViewById(R.id.button_example2);
        button2.setEnabled(!licenseError);
        button2.setOnClickListener(new View.OnClickListener() {
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