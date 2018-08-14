package orka.com.scareme.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import orka.com.scareme.R;
import orka.com.scareme.fragments.AlarmFragment;
import orka.com.scareme.fragments.SettingsFragments;
import orka.com.scareme.fragments.StoryFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    private FrameLayout mFrameLayout;

    private AlarmFragment mAlarmFragment;
    private SettingsFragments mSettingsFragments;
    private StoryFragment mStoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.bottomMenu);
        mFrameLayout = findViewById(R.id.frameMain);

        mAlarmFragment = new AlarmFragment();
        mSettingsFragments = new SettingsFragments();
        mStoryFragment = new StoryFragment();

        setFragment(mAlarmFragment);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_scareAlarm:
                        mBottomNavigationView.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(mAlarmFragment);
                        return true;

                    case R.id.nav_settings:
                        mBottomNavigationView.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(mSettingsFragments);
                        return true;
                    case  R.id.nav_shortStories:
                        mBottomNavigationView.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(mStoryFragment);
                        return true;

                        default:
                            return false;


                }
            }
        });
    }

    private void setFragment(android.support.v4.app.Fragment fragment) {

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frameMain, fragment );
        fragmentTransaction.commit();

    }
}
