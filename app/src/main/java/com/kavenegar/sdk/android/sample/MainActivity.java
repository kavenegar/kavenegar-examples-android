package com.kavenegar.sdk.android.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.meysam.mytest.R;
import com.kavenegar.sdk.KavenegarApi;
import com.kavenegar.sdk.excepctions.ApiException;
import com.kavenegar.sdk.excepctions.HttpException;
import com.kavenegar.sdk.models.SendResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etApiKey,etSender,etReceptor,etmessage;

        Button btnSend=(Button) findViewById(R.id.btnSend);
        etApiKey=(EditText) findViewById(R.id.editText);
        etSender=(EditText) findViewById(R.id.editText2);
        etReceptor=(EditText) findViewById(R.id.editText3);
        etmessage=(EditText) findViewById(R.id.editText4);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            KavenegarApi api = new KavenegarApi(etApiKey.getText().toString());
                            SendResult Result = api.send(etSender.getText().toString(), etReceptor.getText().toString(), etmessage.getText().toString());
                        } catch (HttpException ex) { // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
                            System.out.print("HttpException  : " + ex.getMessage());
                        } catch (ApiException ex) { // در صورتی که خروجی وب سرویس 200 نباشد این خطارخ می دهد.
                            System.out.print("ApiException : " + ex.getMessage());
                        }
                    }
                }).start();
            }
        });



    }
}
