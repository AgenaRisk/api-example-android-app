package com.agenarisk.android.example;

import android.app.Activity;
import android.widget.TextView;

import com.agenarisk.android.R;

public abstract class Example {
    private final Activity parent;
    private final TextView textView;
    private final StringBuilder sb = new StringBuilder();

    public Example(Activity parent){
        this.parent = parent;
        textView = (TextView) this.parent.findViewById(R.id.text_scroll_content);
    }

    protected void appendLines(Object... lines){
        for(Object line: lines){
            sb.append(line).append("\n");
        }
    }

    protected void refreshText(){
        textView.setText(sb.toString());
    }
}
