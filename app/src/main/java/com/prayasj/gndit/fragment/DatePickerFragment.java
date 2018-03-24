package com.prayasj.gndit.fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.prayasj.gndit.activity.CreateProfileActivity;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment {

  public static final String DATE = "DatePickerFragment.DATE";

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    final Calendar c = Calendar.getInstance();
    c.setTime(new Date(getArguments().getLong(DATE)));
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
    return new DatePickerDialog(getActivity(), (CreateProfileActivity) getActivity(), year, month, day);
  }
}
