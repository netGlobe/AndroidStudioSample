package ir.netglobe.example.androidStudio;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.arna.Ad;

import com.arna.NetGlobe;
import com.arna.ReciverService;


public class MyAdReciverService extends ReciverService {

    @Override
    public void onAdRecived(Ad ad) {
        Toast.makeText(getApplicationContext(),"from service(ad):"+ad.getPayload(),Toast.LENGTH_LONG).show();
        startActivity(ad.getIntent());
        NetGlobe.AdClicked(ad.getToken(),getApplicationContext());
        NetGlobe.AdShown(ad.getToken(),getApplicationContext());
    }

    @Override
    public void onPayloadRecived(String payload,String token) {
        Toast.makeText(getApplicationContext(),"from service:"+payload,Toast.LENGTH_LONG).show();
        NetGlobe.AdClicked(token,getApplicationContext());
        NetGlobe.AdShown(token,getApplicationContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
