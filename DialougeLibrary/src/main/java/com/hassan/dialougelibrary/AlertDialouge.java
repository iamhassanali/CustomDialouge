package com.hassan.dialougelibrary;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Hassan Ali on 25/8/2020.
 */

public class AlertDialouge {

    Context context;
    TextView title_tv, subtitle_tv, positive_tv, negative_tv;
    Dialog dialog;
    View separator;
    private DialougeListener positiveListener;
    private DialougeListener negativeListener;

    public AlertDialouge(Context context) {
        this.context = context;
        setDialog();
    }

    private void setDialog() {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialouge_layout);
        title_tv = dialog.findViewById(R.id.title);
        subtitle_tv = dialog.findViewById(R.id.subtitle);
        positive_tv = dialog.findViewById(R.id.dialogButtonOK);
        negative_tv = dialog.findViewById(R.id.dialogButtonNO);
        separator = dialog.findViewById(R.id.separator);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initEvents();
    }

    public AlertDialouge setTitle(String title) {
        title_tv.setText(title);
        return this;
    }

    public AlertDialouge setSubTitle(String title) {
        subtitle_tv.setText(title);
        return this;
    }

    public AlertDialouge show() {
        dialog.show();
        return this;
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public AlertDialouge cancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public AlertDialouge setPositiveListener(String label, DialougeListener listener) {
        positive_tv.setText(label);
        this.positiveListener = listener;
        positive_tv.setVisibility(View.VISIBLE);
        this.dismiss();
        return this;
    }

    public AlertDialouge setNegativeListener(String label, DialougeListener listener) {
        negative_tv.setText(label);
        this.negativeListener = listener;
        negative_tv.setVisibility(View.VISIBLE);
        separator.setVisibility(View.VISIBLE);
        this.dismiss();
        return this;
    }

    private void initEvents() {
        positive_tv.setOnClickListener(view -> {
            if (positiveListener != null) {
                positiveListener.onClick(AlertDialouge.this);
            }
        });
        negative_tv.setOnClickListener(view -> {
            if (negativeListener != null) {
                negativeListener.onClick(AlertDialouge.this);
            }
        });
    }
}