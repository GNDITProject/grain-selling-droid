package com.prayasj.gndit.custom.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.prayasj.gndit.R;

public class EditText extends LinearLayout {

    private View rootView;
    private int inputType;
    private String hint;

    public EditText(Context context) {
        super(context);
        initViews(context);
    }

    public EditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        initViews(context);
    }

    public Editable getText() {
        android.widget.EditText editText = rootView.findViewById(R.id.edit_text);
        return editText.getText();
    }

    private void initViews(Context context) {
        rootView = LayoutInflater.from(context).inflate(R.layout.custom_edittext, this, false);
        rootView.findViewById(R.id.close_button).setOnClickListener(clearText());
        android.widget.EditText editText = rootView.findViewById(R.id.edit_text);
        editText.setInputType(inputType);
        editText.setHint(hint);
        addView(rootView);
    }

    private void initAttrs(AttributeSet attributeSet) {
        TypedArray a = getContext().obtainStyledAttributes(attributeSet, R.styleable.EditText, 0, 0);
        inputType = a.getInt(R.styleable.EditText_editInputType, 1);
        hint = a.getString(R.styleable.EditText_hint);
        a.recycle();
    }

    private OnClickListener clearText() {
        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                android.widget.EditText editText = rootView.findViewById(R.id.edit_text);
                editText.setText("");
            }
        };
    }
}
