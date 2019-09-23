package umutg.example.com.rc_araba_kontrol.data;

import org.json.JSONObject;

/**
 * Created by Fatih on 23.03.2017.
 */

public class Item implements JSONPopulator {

    private Condition condition;

        public Condition getCondition() {
            return condition;

    }

    @Override
    public void poupulate(JSONObject data) {

        condition = new Condition();
        condition.poupulate(data.optJSONObject("condition"));

    }
}
