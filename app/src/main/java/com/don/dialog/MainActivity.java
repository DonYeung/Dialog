package com.don.dialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.birbit.android.jobqueue.JobManager;
import com.don.common.DialogManager;
import com.don.common.DialogQueue;
import com.don.widget.MyOwnDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyOwnDialog dialog1,dialog2,dialog3;
    private int DialogId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_show) {
                     dialog1 = DialogManager.showDialog(MainActivity.this,"dialog1","http://www.shiyan360.cn/Public/Uploads/Chuangke/20170619/59473876b4e81.jpg");
                    dialog1.setPriority(1);

                    dialog1.display();


                     dialog2 = DialogManager.showDialog(MainActivity.this,"dialog2","http://cms-bucket.nosdn.127.net/catchpic/2/24/24f22569f438346c04668cfbccae1d8d.jpg");
                    dialog2.setPriority(2);
                    onPrepareDialog(1,dialog1);
                    dialog2.display();

                     dialog3 = DialogManager.showDialog(MainActivity.this,"dialog3","http://dingyue.nosdn.127.net/ws57UjkB1emt4Sub1kAo9ybdrYFmVdHVHX0t=I3KA2=S71500278874410.jpg");
                    dialog3.setPriority(3);
                    dialog3.display();



                }
            }
        });
    }
    public void onPrepareDialog(int id, MyOwnDialog dialog) {
        switch(id) {
            case 1 :
                removeDialog(1);
                break;
        }
    }

}