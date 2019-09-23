package umutg.example.com.rc_araba_kontrol;


import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class Control extends AppCompatActivity {



    Button btnup,btndown,btnright,btnleft;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;




    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);


        Intent newint = getIntent();
        address = newint.getStringExtra(MainActivity.EXTRA_ADDRESS);

        btnup = (Button)findViewById(R.id.btnup);
        btndown = (Button)findViewById(R.id.btndown);
        btnright = (Button)findViewById(R.id.btnright);
        btnleft = (Button)findViewById(R.id.btnleft);



        new BTbaglan().execute();



        btnup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        up();
                        return true;
                    case MotionEvent.ACTION_UP:
                        stop();
                        return true;
                }
                return false;
            }
        });

        btnleft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        left();
                        return true;
                    case MotionEvent.ACTION_UP:
                        stop();
                        return true;
                }
                return false;
            }
        });


        btnright.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        right();
                        return true;
                    case MotionEvent.ACTION_UP:
                        stop();
                        return true;
                }
                return false;
            }
        });


        btndown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        down();
                        return true;
                    case MotionEvent.ACTION_UP:
                        stop();
                        return true;
                }
                return false;
            }
        });






    }

    @Override
    public void onStart(){
        super.onStart();
        final Speedometer speedometer = (Speedometer) findViewById(R.id.Speedometer);
        Button increaseSpeed = (Button) findViewById(R.id.IncreaseSpeed);
        Button decreaseSpeed = (Button) findViewById(R.id.DecreaseSpeed);
        increaseSpeed.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                speedometer.onSpeedChanged(speedometer.getCurrentSpeed()+8);
            }

        });
        decreaseSpeed.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                speedometer.onSpeedChanged(speedometer.getCurrentSpeed()-8);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Disconnect();
    }

    //Baglantı kurma ve Socket vasıtasyıla veriyi gonderme
    private class BTbaglan extends AsyncTask<Void,Void,Void> {
        private boolean ConnectSuccess = true;
        @Override
        protected void onPreExecute(){
            progress = ProgressDialog.show(Control.this,"Baglanıyor...","Lütfen Bekleyin");
        }





        @Override
        protected Void doInBackground(Void...devices){
            try {
                if(btSocket == null || !isBtConnected){
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice cihaz = myBluetooth.getRemoteDevice(address);
                    btSocket = cihaz.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();

                }
            } catch (IOException e){
                ConnectSuccess = false;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            if(!ConnectSuccess){
                msg("Baglantı Hatası, Lütfen Tekrar Deneyin");
                finish();
            } else {
                msg("Baglantı Basarılı");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }

    //Baglantıyı Sonlandırma
    private void Disconnect(){
        if(btSocket!=null){
            try {
                btSocket.close();
            } catch (IOException e){
                msg("Error");
            }
        }
        finish();
    }

    //Hata mesajı
    private void msg(String s){
        Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
    }


    //Butona basılınca yapılacak hareket kodları
    private void up(){
        if(btSocket!=null){
            try {
                btSocket.getOutputStream().write("F".toString().getBytes());
            } catch (IOException e){
                msg("Error");
            }
        }
    }

    private void left(){
        if(btSocket!=null){
            try {
                btSocket.getOutputStream().write("L".toString().getBytes());
            } catch (IOException e){
                msg("Error");
            }
        }
    }

    private void right(){
        if(btSocket!=null){
            try {
                btSocket.getOutputStream().write("R".toString().getBytes());
            } catch (IOException e){
                msg("Error");
            }
        }
    }

    private void down(){
        if(btSocket!=null){
            try {
                btSocket.getOutputStream().write("B".toString().getBytes());
            } catch (IOException e){
                msg("Error");
            }
        }
    }

    private void stop(){
        if(btSocket!=null){
            try {
                btSocket.getOutputStream().write("S".toString().getBytes());
            } catch (IOException e){
                msg("Error");
            }
        }
    }





























}
