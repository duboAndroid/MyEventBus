package com.harvic.tryeventbus2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.harvic.other.FirstEvent;
import com.harvic.other.SecondEvent;
import com.harvic.other.ThirdEvent;

import de.greenrobot.event.EventBus;

public class MainActivity extends Activity {

    Button btn;
    TextView tv;
    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        btn = (Button) findViewById(R.id.btn_try);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onEventMainThread(FirstEvent event) {
        Toast.makeText(this, "onEventMainThread " + event.getMsg(), Toast.LENGTH_LONG).show();
    }

    //SecondEvent接收函数一
    public void onEventMainThread(SecondEvent event) {
        Toast.makeText(this, "onEventMainThread " + event.getMsg(), Toast.LENGTH_LONG).show();
    }

    //SecondEvent接收函数二
    public void onEventBackgroundThread(SecondEvent event) {
        Toast.makeText(this, "onEventBackgroundThread " + event.getMsg(), Toast.LENGTH_LONG).show();
    }

    //SecondEvent接收函数三
    public void onEventAsync(SecondEvent event) {
        Toast.makeText(this, "onEventAsync " + event.getMsg(), Toast.LENGTH_LONG).show();
    }

    public void onEvent(ThirdEvent event) { //主线程
        Toast.makeText(this, "OnEvent " + event.getMsg(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
