package com.test.serverconnection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class SubActivity extends AppCompatActivity {

    
    Button btn_on; // 전원 ON 버튼
    Button btn_off; // 전원 OFF 버튼
    Button btn_home; // 홈화면 이동


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // 레이아웃 변수와 연결
        btn_on = findViewById(R.id.btn_on);
        btn_off = findViewById(R.id.btn_off);
        btn_home = findViewById(R.id.btn_home);



        // 버튼 홈
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        
        // 버튼 OFF
        btn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "보일러 끔", Toast.LENGTH_SHORT).show();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String page = "http://10.10.141.61:8080/updateon_off/OFF";

                        try {
                            URL url = new URL(page);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            StringBuilder stringBuilder = new StringBuilder();

                            if(conn != null){
                                conn.setConnectTimeout(10000);
                                conn.setRequestMethod("GET");
                                conn.setUseCaches(false);

                                conn.getResponseCode();
                                conn.disconnect();
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } //End of run()

                });thread.start();
            }
        });


        // 버튼 ON
        btn_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "보일러 켬", Toast.LENGTH_SHORT).show();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String page = "http://10.10.141.61:8080/updateon_off/ON";
                        try {
                            URL url = new URL(page);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            StringBuilder stringBuilder = new StringBuilder();

                            if(conn != null){
                                conn.setConnectTimeout(10000);
                                conn.setRequestMethod("GET");
                                conn.setUseCaches(false);
                                conn.getResponseCode();
                                conn.disconnect();
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });thread.start();
            }
        });
    }
}