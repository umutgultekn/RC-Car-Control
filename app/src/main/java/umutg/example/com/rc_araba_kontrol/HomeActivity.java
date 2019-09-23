package umutg.example.com.rc_araba_kontrol;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);



        //bekleme işlemi için yeni bir thread oluşturuyoruz.
        Thread splashThread = new Thread(){
            public void run(){
                try{
                    sleep(5000);//microsaniye cinsinden bekleme süresini verdik
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    //bekleme işlemi tamamlandıktan sonra ana dizine geçiş saglıyoruz.
                    Intent intent = new Intent(HomeActivity.this,HomeScreen.class);
                    startActivity(intent);
                }
            }
        };
        //bekleme işlemini üslenen thread methodu çagırıldı.
        splashThread.start();
    }
}
