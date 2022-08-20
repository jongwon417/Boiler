package com.test.serverconnection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    TextView tv_on_off; // ON OFF 상태 텍스트뷰
    TextView tv_temp; // 온도 텍스트뷰
    Button btn_goSwitchPage; // 스위치 페이지 이동 버튼

    static List<Member> data;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_on_off = findViewById(R.id.tv_on_off);
        tv_temp = findViewById(R.id.tv_temp);
        btn_goSwitchPage = findViewById(R.id.btn_goSwitchPage);

        btn_goSwitchPage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                /* 보일러 첫 화면 페이지 */
                String page = "http://10.10.141.61:8080/boilerstat";

                try {
                    URL url = new URL(page);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    StringBuilder stringBuilder = new StringBuilder();

                    if(conn != null){

                        conn.setConnectTimeout(10000);
                        conn.setRequestMethod("GET");
                        conn.setUseCaches(false);
                        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){

                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                            while(true){
                                String line = bufferedReader.readLine();
                                if(line == null) break;
                                stringBuilder.append(line + "\n");
                            }
                            bufferedReader.close();
                        }
                        conn.disconnect();
                    }

                    Gson gson = new Gson();

                    Type type = new TypeToken<List<Member>>() {}.getType();
                    data = gson.fromJson(String.valueOf(stringBuilder),type);

                    String on_off = data.get(0).getOnOff();
                    int temp = data.get(0).getTemp();

                    switchOnOff(on_off);
                    showTemp(temp+"도");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
        thread.start();
    }

    /* 전원 텍스트뷰 출력 */
    public void switchOnOff(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv_on_off.append(data);
            }
        });
    }

    /* 온도 텍스트뷰 출력 */
    public void showTemp(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv_temp.append(data);
            }
        });
    }
}