package ir.netglobe.example.androidStudio;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.arna.Ad;
import com.arna.Advertise;
import com.arna.ReciverService;

/**
 * Created by enakhi on 4/12/2017.
 */

public class MyAdReciverService extends ReciverService {

    @Override
    public void onAdRecived(Ad ad) {
        Toast.makeText(getApplicationContext(),"from service(ad):"+ad.getPayload(),Toast.LENGTH_LONG).show();
        startActivity(ad.getIntent());
        Advertise.AdClicked(ad.getToken(),getApplicationContext());
        Advertise.AdShown(ad.getToken(),getApplicationContext());
    }

    @Override
    public void onPayloadRecived(String payload,String token) {
        Toast.makeText(getApplicationContext(),"from service:"+payload,Toast.LENGTH_LONG).show();
        Advertise.AdClicked(token,getApplicationContext());
        Advertise.AdShown(token,getApplicationContext());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
