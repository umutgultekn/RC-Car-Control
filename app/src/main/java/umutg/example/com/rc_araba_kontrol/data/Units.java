package umutg.example.com.rc_araba_kontrol.data;

import org.json.JSONObject;

/**
 * Created by Fatih on 23.03.2017.
 */

public class Units implements JSONPopulator{

    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void poupulate(JSONObject data) {

        temperature = data.optString("temperature");

    }
}


