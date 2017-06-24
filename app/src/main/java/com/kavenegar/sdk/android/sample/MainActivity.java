package com.kavenegar.sdk.android.sample;

import android.os.Bundle;
//import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meysam.mytest.R;
import com.kavenegar.sdk.KavenegarApi;
import com.kavenegar.sdk.excepctions.ApiException;
import com.kavenegar.sdk.excepctions.HttpException;
import com.kavenegar.sdk.models.SendResult;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    KavenegarApi api ;
    String[] rec= new String[]{"09353364377"};
    String messatge="test";
    final String[] msg = {"ارسال ناموفق"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MultiDex.install(this);
        api = new KavenegarApi("796E5161645A7949536A4D7A565641414D636A4A4E513D3D");

        final EditText etApiKey,etSender,etReceptor,etmessage;

        Button btnSend=(Button) findViewById(R.id.btnSend);
        etApiKey=(EditText) findViewById(R.id.editText);
        etSender=(EditText) findViewById(R.id.editText2);
        etReceptor=(EditText) findViewById(R.id.editText3);
        etmessage=(EditText) findViewById(R.id.editText4);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeCallTTS();


            }
        });



    }



    public void MakeCallTTS(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    //SendResult Result = api.send("10004346","09353364377","test");

                    List<SendResult> r1= api.CallMakeTTS(Arrays.asList(rec),messatge);
                    if(r1!=null){
                        msg[0] ="ارسال موفق";
                    }
                    else{
                        msg[0] ="ارسال ناموفق";
                    }

                    Log.d("kv",msg[0]);
                } catch (HttpException ex) { // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
                    System.out.print("HttpException  : " + ex.getMessage());
                } catch (ApiException ex) { // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
                    System.out.print("ApiException : " + ex.getMessage());
                }
            }
        }).start();
    }


}
