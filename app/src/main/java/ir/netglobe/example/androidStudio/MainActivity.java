package ir.netglobe.example.androidStudio;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arna.Advertise;
import com.arna.manager.views.baners.AdView;

import java.util.Locale;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        com.arna.Advertise.start(getApplicationContext());
        if(Locale.getDefault().getLanguage().toLowerCase().equals("fa")) {
            ////place badger at top-left position when locale is farsi
            com.arna.Advertise.attachBadgerToActivity(this, com.arna.Advertise.BADGER_POSITION_TOP_LEFT);
        }
        else {
            ////place badger at top-right position when locale is none farsi
            com.arna.Advertise.attachBadgerToActivity(this, com.arna.Advertise.BADGER_POSITION_TOP_RIGHT);
        }
        com.arna.Advertise.attachBannerToActivity(this,com.arna.Advertise.BANNER_MODE_UNDER_VIEWS);
        com.arna.Advertise.checkSettings(this,true,true);

        findViewById(R.id.btn_show_interstitial_ad).setOnClickListener(this);
        findViewById(R.id.btn_get_Information).setOnClickListener(this);
        findViewById(R.id.btn_add_banner).setOnClickListener(this);
        findViewById(R.id.btn_remove_banner).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get_Information:
                ((TextView)findViewById(R.id.txt_information)).setText(com.arna.Advertise.getStatus(this));
                break;
            case R.id.btn_add_banner:
                com.arna.manager.views.baners.AdView adView=new AdView(this);
                ((LinearLayout)findViewById(R.id.red_layout)).addView(adView);
                Toast t = Toast.makeText(this, R.string.addBannerToast, Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL,0,0);
                t.show();
                break;
            case R.id.btn_remove_banner:
                if(((LinearLayout)findViewById(R.id.red_layout)).getChildCount()>0)
                ((LinearLayout)findViewById(R.id.red_layout)).removeViewAt(((LinearLayout)findViewById(R.id.red_layout)).getChildCount()-1);
                break;
            case R.id.btn_show_interstitial_ad:
                com.arna.Advertise.ShowInterstitialAd(this,new com.arna.advertiseInterfaces.AdStateListener(){

                    @Override
                    public void isShowing(int i) {
                        Toast t = Toast.makeText(MainActivity.this,"AdStateListiner isShowing",Toast.LENGTH_LONG);
                        t.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL,0,0);
                        t.show();
                    }
                    @Override
                    public void notAvailable(int i) {
                        Toast t =  Toast.makeText(MainActivity.this,"AdStateListiner notAvailable",Toast.LENGTH_LONG);
                        t.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL,0,0);
                        t.show();
                    }
                    @Override
                    public void wasDismissed(int i) {
                        Toast t = Toast.makeText(MainActivity.this,"AdStateListiner wasDismissed",Toast.LENGTH_LONG);
                        t.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL,0,0);
                        t.show();
                    }
                });
                break;
        }
    }
}
