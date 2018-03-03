package com.prayasj.gndit.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.prayasj.gndit.R;
import com.prayasj.gndit.model.CropRequest;

import java.util.ArrayList;
import java.util.List;

public class CropRequestAdapter extends ArrayAdapter<CropRequest>{
  public CropRequestAdapter(@NonNull Context context, @NonNull List<CropRequest> cropRequest) {
    super(context, 0, cropRequest);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View listItemView = convertView;
    if (listItemView == null) {
      listItemView = LayoutInflater.from(getContext()).inflate(
        R.layout.list_item_crop_requests, parent, false);
    }

    CropRequest currentRequest = getItem(position);

    TextView cropNameTextview = listItemView.findViewById(R.id.crop_name);
    cropNameTextview.setText(currentRequest.getCrop().getName());

    TextView quantityTextview = listItemView.findViewById(R.id.quantity);
    quantityTextview.setText(currentRequest.getQuantity().toString());

    TextView priceTextview = listItemView.findViewById(R.id.price);
    priceTextview.setText(currentRequest.getPrice().toString());

    return listItemView;
  }
}
