package com.agenarisk.android.example;

import android.app.Activity;

import com.agenarisk.api.exception.ModelException;
import com.agenarisk.api.model.DataSet;
import com.agenarisk.api.model.Model;
import com.agenarisk.api.model.Network;
import com.agenarisk.api.model.Node;

import java.io.File;
import java.io.IOException;

import uk.co.agena.minerva.util.Logger;

/**
 * Example 2
 * Using AgenaRisk Java API,
 * copy a CMPX model (exported from AgenaRisk Desktop as JSON) from file packed with the application to a temp file,
 * load the model from the file,
 * set some observations (including a node variable),
 * calculate,
 * print data points and summary statistics JSON
 */
public class Example2 extends Example {

    public Example2(Activity parent){
        super(parent);
    }

    public void runTask() {
        appendLines(
                "Example 2",
                "Using AgenaRisk Java API, copy a CMPX model (exported from AgenaRisk Desktop as JSON) from file packed with the application to a temp file, load the model from the file, set some observations (including a node variable), calculate, print data points and summary statistics JSON",
                "");

        refreshText();

        showSpinner();

        try {
            File file = AgenaRiskHelper.copyTemp("/Car Costs.json", ".json");
            Model model = Model.loadModel(file.getAbsolutePath());

            Network network = model.getNetworkList().get(0);

            Node nodeCarType = network.getNode("car_type");
            Node nodeReliability = network.getNode("Reliability");
            Node nodeMilesPerYear = network.getNode("Miles_per_year");

            DataSet dataSet = model.getDataSetList().get(0);

            dataSet.setObservation(nodeCarType, "Large");
            dataSet.setObservation(nodeReliability, "Medium");
            dataSet.setVariableObservation(nodeMilesPerYear, "miles_per_year_const", 4000);

            long start = System.currentTimeMillis();
            model.calculate();
            long end = System.currentTimeMillis();

            long duration = end - start;
            double durationS = ((double)duration/1000);
            appendLines(
                    "Calculation duration: " + durationS + " s",
                    "");

            Node nodeTotalCost = network.getNode("total_cost");

            String results = dataSet.getCalculationResult(nodeTotalCost).toJson().toString(2);
            appendLines(results,"");
        }
        catch (IOException | ModelException ex){
            appendLines("Failed to copy and load model file");
            appendLines(ex.getClass() + " " +ex.getMessage());
            appendLines(ex.getStackTrace());
        }
        catch (Exception ex){
            appendLines("Failure");
            appendLines(ex.getClass() + " " +ex.getMessage());
            appendLines(ex.getStackTrace());
        }

        refreshText();

        hideSpinner();
    }

}
