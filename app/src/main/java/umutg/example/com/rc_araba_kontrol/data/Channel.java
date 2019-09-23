package umutg.example.com.rc_araba_kontrol.data;

import org.json.JSONObject;

/**
 * Created by Fatih on 23.03.2017.
 */

public class Channel implements JSONPopulator {
    private  Units units;
    private  Item item;

    public Units getUnits() {
        return units;
    }


    public Item getItem() {
        return item;
    }



    @Override
    public void poupulate(JSONObject data) {

        units = new Units();
        units.poupulate(data.optJSONObject("units"));

        item =new Item();
        item.poupulate(data.optJSONObject("item"));

    }
}
