package com.prayasj.gndit.activity;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.prayasj.gndit.R;
import com.prayasj.gndit.fragment.DatePickerFragment;
import com.prayasj.gndit.model.UserProfileInfo;
import com.prayasj.gndit.network.ServiceBuilder;
import com.prayasj.gndit.network.response.ErrorResponse;
import com.prayasj.gndit.network.service.UserProfileService;
import com.prayasj.gndit.presenter.CreateProfilePresenter;
import com.prayasj.gndit.validator.UserProfileInfoValidator;
import com.prayasj.gndit.views.CreateUserProfileView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.prayasj.gndit.fragment.DatePickerFragment.DATE;

public class CreateProfileActivity extends AppCompatActivity implements OnDateSetListener, CreateUserProfileView {

  private long selectedDob;
  private ProgressDialog progressDialog;
  private CreateProfilePresenter createProfilePresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    selectedDob = new Date().getTime();
    setContentView(R.layout.create_profile_activity);
    createProfilePresenter = new CreateProfilePresenter(ServiceBuilder.build(UserProfileService.class), this, new UserProfileInfoValidator());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.menu_make_crop_request, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_save) {
      createProfilePresenter.saveUserProfile();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void showDatePicker(View view) {
    DatePickerFragment datePickerFragment = new DatePickerFragment();
    Bundle args = new Bundle();
    args.putLong(DATE, selectedDob);
    datePickerFragment.setArguments(args);
    datePickerFragment.show(getSupportFragmentManager(), "DATE_PICKER");
  }

  @Override
  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month);
    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    Date date = calendar.getTime();
    selectedDob = date.getTime();
    EditText dobText = findViewById(R.id.dob_text);
    dobText.setText(new SimpleDateFormat("MMM dd, yyyy").format(date));
  }

  @Override
  public void showProgressDialog() {
    progressDialog = new ProgressDialog(this);
    progressDialog.setTitle(R.string.wait_a_moment);
    progressDialog.setMessage(getString(R.string.saving_profile_information));
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.setCancelable(false);
    progressDialog.show();
  }

  @Override
  public UserProfileInfo getUserProfileInfo() {
    return new UserProfileInfo(
      getStringFromView(R.id.profile_name),
      getGender(),
      getStringFromView(R.id.address),
      getStringFromView(R.id.contact),
      selectedDob);
  }

  private String getGender() {
    String selectedItem = (String) ((Spinner) findViewById(R.id.gender)).getSelectedItem();
    Map<String, String> genderKeys = new HashMap<>();
    genderKeys.put(getString(R.string.male), "M");
    genderKeys.put(getString(R.string.female), "F");
    return genderKeys.containsKey(selectedItem) ? genderKeys.get(selectedItem) : selectedItem;
  }

  @NonNull
  private String getStringFromView(@IdRes int viewId) {
    return ((com.prayasj.gndit.custom.views.EditText) findViewById(viewId)).getText().toString();
  }

  @Override
  public void onProfileSaveSuccessful() {
    progressDialog.dismiss();
    Intent dashboardIntent = new Intent(this, DashboardActivity.class);
    startActivity(dashboardIntent);
    finish();
  }

  @Override
  public void onProfileSaveFailure(ErrorResponse response) {
    progressDialog.dismiss();
    showSnackBar(response.getMessage());

  }

  @Override
  public void onTechnicalError() {
    progressDialog.dismiss();
    showSnackBar(getString(R.string.technical_difficulty));
  }

  @Override
  public void showErrorMessage(String message) {
    progressDialog.dismiss();
    showSnackBar(message);
  }

  private void showSnackBar(String message) {
    final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
    snackbar.setAction("OK", new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        snackbar.dismiss();
      }
    }).show();
  }
}
