package com.agenarisk.android.example;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.agenarisk.android.R;

public abstract class Example {
    private final Activity parent;
    private final TextView textView;
    private final ProgressBar spinner;
    private final StringBuilder sb = new StringBuilder();

    public Example(Activity parent){
        this.parent = parent;
        textView = (TextView) this.parent.findViewById(R.id.text_scroll_content);
        spinner = this.parent.findViewById(R.id.progressBar_cyclic);
    }

    public final void run(){
        Thread thread = new Thread() {
            public void run() {
                runTask();
            }
        };
        thread.start();
    }
    protected abstract void runTask();


    protected void appendLines(Object... lines){
        for(Object line: lines){
            sb.append(line).append("\n");
        }
    }

    protected void showSpinner(){
        parent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                spinner.setVisibility(View.VISIBLE);
            }
        });
    }

    protected void hideSpinner(){
        parent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                spinner.setVisibility(View.GONE);
            }
        });
    }

    protected void refreshText() {
        parent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(sb.toString());
            }
        });
    }
}
