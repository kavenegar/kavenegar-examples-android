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
import com.kavenegar.sdk.utils.PairValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    KavenegarApi api ;
    String[] rec= new String[]{"MobileNumbers"};
    String messatge="test";
    final String[] msg = {"ارسال ناموفق"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MultiDex.install(this);
        api = new KavenegarApi("YourApiKey");

        final EditText etApiKey,etSender,etReceptor,etmessage;
        Button btnSend=(Button) findViewById(R.id.btnSend);
        etApiKey=(EditText) findViewById(R.id.editText);
        etSender=(EditText) findViewById(R.id.editText2);
        etReceptor=(EditText) findViewById(R.id.editText3);
        etmessage=(EditText) findViewById(R.id.editText4);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MakeCallTTS();
                VerifyLookUp();

            }
        });



    }

    public void VerifyLookUp(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<PairValue> keys=new ArrayList<PairValue>();
                    keys.add(new PairValue("token10", "t10"));
                    keys.add(new PairValue("token20", "t20"));
                    SendResult Result=api.verifyLookup("PhoneNumber","0",null,"3","all",keys);


//                    if(Result!=null){
//                        msg[0] ="ارسال موفق";
//                    }
//                    else{
//                        msg[0] ="ارسال ناموفق";
//                    }

                    //Toast.makeText(getApplication(),msg[0],Toast.LENGTH_LONG).show();

//
//                    Log.d("kv",msg[0]);
                } catch (HttpException ex) { // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
                    System.out.print("HttpException  : " + ex.getMessage());
                } catch (ApiException ex) { // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
                    System.out.print("ApiException : " + ex.getMessage());
                }
            }
        }).start();
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
