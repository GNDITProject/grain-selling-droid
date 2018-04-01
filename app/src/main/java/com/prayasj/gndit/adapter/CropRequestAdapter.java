package com.prayasj.gndit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.prayasj.gndit.R;
import com.prayasj.gndit.activity.DashboardActivity;
import com.prayasj.gndit.model.CropRequest;

import java.util.List;

public class CropRequestAdapter extends ArrayAdapter<CropRequest> implements PopupMenu.OnMenuItemClickListener {
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
    cropNameTextview.setText(currentRequest.getCropName());

    TextView quantityTextview = listItemView.findViewById(R.id.quantity);
    quantityTextview.setText(currentRequest.getQuantity().toString());

    TextView priceTextview = listItemView.findViewById(R.id.price);
    priceTextview.setText(currentRequest.getPrice().toString());

    TextView priceUnitTextview = listItemView.findViewById(R.id.price_unit);
    priceUnitTextview.setText(getContext().getText(R.string.Rs));

    TextView timeAndDateTextview = listItemView.findViewById(R.id.created_at);
    timeAndDateTextview.setText(currentRequest.getCreatedAt());

    TextView quantityUnitTextview = listItemView.findViewById(R.id.quantity_unit);
    quantityUnitTextview.setText(getContext().getText(R.string.Mt));

    View menuForCardView = listItemView.findViewById(R.id.menu_for_card_view);
    menuForCardView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showMenu(v);
      }
    });

    return listItemView;
  }

  private void showMenu(View v) {
    PopupMenu popup = new PopupMenu(getContext(), v);
    MenuInflater menuInflater = popup.getMenuInflater();
    menuInflater.inflate(R.menu.menu_for_card_view, popup.getMenu());
    popup.setOnMenuItemClickListener (this);
    popup.show();
  }

  @Override
  public boolean onMenuItemClick(MenuItem item) {
    switch (item.getItemId()){
      case R.id.delete:
        Toast.makeText(getContext(),"deleted",Toast.LENGTH_SHORT).show();
    }
    return true;
  }
}
