package me.biubiubiu.one.ui;

import java.util.Calendar;
import java.util.Map;

import me.biubiubiu.one.R;
import me.biubiubiu.one.ui.view.ValueSpinner;
import me.biubiubiu.one.util.HttpHandler;
import me.biubiubiu.one.util.ViewUtils;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import butterknife.InjectView;
import butterknife.Views;

import com.loopj.android.http.RequestParams;

/**
 * Activity to view the carousel and view pager indicator with fragments.
 */
public class PostRideActivity extends BaseActivity  {

    static public final int REQUEST_GET_LOCATION = 1;


    @InjectView(R.id.form) TableLayout mForm;
    @InjectView(R.id.price) TextView mPriceView;
    @InjectView(R.id.people) ValueSpinner mPeopleView;
    @InjectView(R.id.car_type) ValueSpinner mCarTypeView;
    @InjectView(R.id.wait_time) ValueSpinner mWaitTimeView;
    @InjectView(R.id.pick_date) Button mPickDate;
    @InjectView(R.id.pick_time) Button mPickTime;

    // date and time
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    static final int TIME_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID = 1;

    static public final String[][] spinner_map_people = {
        {"1人", "1"},
        {"2人", "2"},
        {"3人", "3"},
    };
    static public final String[][] spinner_map_car_type = {
        {"自驾车", "0"},
        {"出租车", "1"},
    };
    static public final String[][] spinner_map_wait_time = {
        {"1小时", "1"},
        {"2小时", "2"},
        {"3小时", "3"},
    };

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.post_ride);
        getSupportActionBar().setTitle("看谁能带我");
        Views.inject(this);

        mPeopleView.setup(spinner_map_people);
        mCarTypeView.setup(spinner_map_car_type);
        mWaitTimeView.setup(spinner_map_wait_time);

        initDate();
    }

    public void initDate() {
        mPickDate.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    showDialog(DATE_DIALOG_ID);
                }
            });

        mPickTime.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    showDialog(TIME_DIALOG_ID);
                }
            });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        updateDisplay();

    }

    private Calendar getCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, mYear);
        cal.set(Calendar.MONTH, mMonth);
        cal.set(Calendar.DAY_OF_MONTH, mDay);
        cal.set(Calendar.HOUR_OF_DAY, mHour);
        cal.set(Calendar.MINUTE, mMinute);
        return cal;
    }

    private void updateDisplay() {
        String dateStr =  new StringBuilder()
            .append(mYear).append("年")
            .append(mMonth + 1).append("月")
            .append(mDay).append("日")
            .toString();
        mPickDate.setText(dateStr);

        String timeStr =  new StringBuilder()
            .append(pad(mHour))
            .append(":")
            .append(pad(mMinute)).toString();
        mPickTime.setText(timeStr);
    }

    public void clickLoc(View view) {
        pickLocation(view.getId());
    }

    private void pickLocation(int id) {
        Intent intent = new Intent(this, GetPositionActivity.class);
        intent.putExtra("view_id", id);
        startActivityForResult(intent, REQUEST_GET_LOCATION);
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public void submit(View view) {
        Map<String, String> map = ViewUtils.collectForm(mForm);
        if (map.get("pick_time") != null && map.get("pick_date") != null) {
            // map.put("start_off_time", getCalendar().toString());
            map.put("start_off_time", "1232-01-02");
        }

        //Mock value
        map.put("start_lat", "120.12");
        map.put("start_lng", "80.12");
        map.put("dest_lat", "120.12");
        map.put("dest_lng", "80.12");

        RequestParams params = ViewUtils.toRequestParams(map);
        HttpHandler handler = new HttpHandler(this);
        handler.post("rides", params);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case TIME_DIALOG_ID:
            return new TimePickerDialog(this,
                                        mTimeSetListener, mHour, mMinute, false);
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this,
                                        mDateSetListener,
                                        mYear, mMonth, mDay);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener =
        new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
                updateDisplay();
            }
        };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
        new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;
                updateDisplay();
            }
        };

    @Override
    protected void onActivityResult(int req, int arg1, Intent arg2) {
        if (req == REQUEST_GET_LOCATION) {
            int id = arg2.getIntExtra("view_id", 0);
            String title = arg2.getStringExtra("title");
            float lat = arg2.getFloatExtra("lat", 0);
            float lng = arg2.getFloatExtra("lng", 0);
            ((TextView)findViewById(id)).setText(title);
        }
    }
}
