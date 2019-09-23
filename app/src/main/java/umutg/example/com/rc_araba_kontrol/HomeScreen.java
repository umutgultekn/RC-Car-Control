package umutg.example.com.rc_araba_kontrol;

import android.content.Intent;
import android.provider.Browser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    Button btnBluetooth,btnBrowser,btnWeather,btnMaps,btnRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        btnBluetooth = (Button) findViewById(R.id.btnBluetooth);
        btnBrowser = (Button) findViewById(R.id.btnBrowser);
        btnWeather = (Button) findViewById(R.id.btnWeather);
        btnMaps = (Button) findViewById(R.id.btnMaps);
        btnRadio =(Button)findViewById(R.id.btnRadio);


        btnBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeScreen.this,MainActivity.class);

                startActivity(i);
            }
        });

        btnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeScreen.this,browser.class);

                startActivity(i);
            }
        });

        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeScreen.this,weather.class);

                startActivity(i);
            }
        });
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeScreen.this,MapActivity.class);

                startActivity(i);
            }
        });

        btnRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeScreen.this,radio.class);

                startActivity(i);
            }
        });







    }
}
