package orka.com.scareme;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
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


/**
 * A simple {@link Fragment} subclass.
 */

public class AlarmFragment extends Fragment {


    private static final String TAG = AlarmFragment.class.getSimpleName();
    @BindView(R.id.alarmTimePicker)
    TimePicker alarmTimePicker;
    @BindView (R.id.alarmToggle)
    ToggleButton alarmToggle;
    public AlarmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,view);

        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked)
                {
                    Log.d(TAG,"Alarm On");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        NotificationHelper.scheduleRepeatingElapsedNotification(getActivity().getApplicationContext());
                        Log.d(TAG,"greater than 6.0");
                    }
                    else
                    {
                        NotificationHelper.scheduleRepeatingRTCNotification(getActivity().getApplicationContext(),
                                alarmTimePicker.getCurrentHour(), alarmTimePicker.getCurrentMinute());
                        Log.d(TAG,"lesser than 6.0");
                    }
                }
                else
                {
                    NotificationHelper.cancelAlarmRTC();
                    Log.d(TAG, "Alarm Off");
                }

            }
        });
        return view;
    }

}
