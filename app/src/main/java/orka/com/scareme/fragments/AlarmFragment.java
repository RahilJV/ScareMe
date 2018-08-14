package orka.com.scareme.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import orka.com.scareme.models.NotificationHelper;
import orka.com.scareme.R;


public class AlarmFragment extends Fragment {

    private static final String TAG = AlarmFragment.class.getSimpleName();
    @BindView(R.id.alarmTimePicker)
    TimePicker alarmTimePicker;
    @BindView(R.id.alarmToggle)
    ToggleButton alarmToggle;
    private FloatingActionButton fab;


    public AlarmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this, view);
        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                    Log.d(TAG, "Alarm On");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        Log.d(TAG, "Hour: " + alarmTimePicker.getCurrentHour());
                        Log.d(TAG, "Minute: " + alarmTimePicker.getCurrentMinute());
//                        Log.d(TAG, "onCheckedChanged: " + alarmTimePicker.getBaseline());

                        NotificationHelper.scheduleRepeatingRTCNotification(getActivity().getApplicationContext(),
                                alarmTimePicker.getHour(), alarmTimePicker.getMinute());
                        Log.d(TAG, "greater than 6.0");
                    } else {
                        Log.d(TAG, "Houri: " + alarmTimePicker.getCurrentHour());
                        Log.d(TAG, "Minutei: " + alarmTimePicker.getCurrentMinute());
                        NotificationHelper.scheduleRepeatingRTCNotification(getActivity().getApplicationContext(),
                                alarmTimePicker.getCurrentHour(), alarmTimePicker.getCurrentMinute());
                        Log.d(TAG, "lesser than 6.0");
                    }
                } else {
                    NotificationHelper.cancelAlarmRTC();
                    Log.d(TAG, "Alarm Off");
                }
            }
        });
        return view;
    }

}
