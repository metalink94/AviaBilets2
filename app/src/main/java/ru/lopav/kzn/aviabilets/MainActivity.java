package ru.lopav.kzn.aviabilets;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.aviasales.core.AviasalesSDK;
import ru.aviasales.core.identification.SdkConfig;
import ru.aviasales.template.ui.fragment.AviasalesFragment;

public class MainActivity extends AppCompatActivity {

    // replace with your travel payout credentials
    private final static String TRAVEL_PAYOUTS_MARKER = "198747";
    private final static String TRAVEL_PAYOUTS_TOKEN = "186485adcfc6e436f9bcd25ef226fff9";
    private final static String SDK_HOST = "www.travel-api.pw";
    private AviasalesFragment aviasalesFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AviasalesSDK.getInstance().init(this, new SdkConfig(TRAVEL_PAYOUTS_MARKER, TRAVEL_PAYOUTS_TOKEN, SDK_HOST));
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initFragment();
    }

    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        aviasalesFragment = (AviasalesFragment) fm.findFragmentByTag(AviasalesFragment.TAG);

        if (aviasalesFragment == null) {
            aviasalesFragment = (AviasalesFragment) AviasalesFragment.newInstance();
        }

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place, aviasalesFragment, AviasalesFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (!aviasalesFragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
