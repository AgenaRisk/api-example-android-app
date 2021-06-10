package com.agenarisk.android.example;

import android.app.Activity;
import android.widget.TextView;

import com.agenarisk.api.model.Model;
import com.agenarisk.api.model.Network;
import com.agenarisk.api.model.Node;
import com.agenarisk.android.R;

/**
 * Example 1
 * Using AgenaRisk Java API, create new model with a simulation node and calculate, then print data points and summary statistics JSON
 */
public class Example1 extends Example {

    public Example1(Activity parent){
        super(parent);
    }

    public void runTask() {
        appendLines(
                "Example 1",
                "Using AgenaRisk Java API, create new model with a simulation node and calculate, then print data points and summary statistics JSON",
                "");

        refreshText();

        showSpinner();

        try {
            Model model = Model.createModel();
            Network net = model.createNetwork("net");
            Node node = net.createNode("node", Node.Type.ContinuousInterval);
            node.convertToSimulated();
            node.setTableFunction("Normal(0,100000)");

            long start = System.currentTimeMillis();
            model.calculate();
            long end = System.currentTimeMillis();
            long duration = end - start;
            double durationS = ((double)duration/1000);
            appendLines(
                    "Calculation duration: " + durationS + " s",
                    "");

            String results = model.getDataSetList().get(0).getCalculationResult(node).toJson().toString(2);
            appendLines(results,"");
        }
        catch(Exception ex){
            appendLines(ex.getClass() + " " +ex.getMessage());
            appendLines(ex.getStackTrace());
        }

        refreshText();

        hideSpinner();
    }
}
