package com.don.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.don.common.DialogManager;
import com.don.common.DialogQueue;
import com.don.widget.MyOwnDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_show) {
                    List<String> imgurls=new ArrayList<String>();
                    imgurls.add("http://www.shiyan360.cn/Public/Uploads/Chuangke/20170619/59473876b4e81.jpg");
                    imgurls.add("http://cms-bucket.nosdn.127.net/catchpic/2/24/24f22569f438346c04668cfbccae1d8d.jpg");
                    imgurls.add("http://dingyue.nosdn.127.net/ws57UjkB1emt4Sub1kAo9ybdrYFmVdHVHX0t=I3KA2=S71500278874410.jpg");

//                    for (int i=0;i<imgurls.size();i++){
                        MyOwnDialog myOwnDialog =DialogManager.showDialog(MainActivity.this,"",imgurls.get(0));
                        myOwnDialog.setPriority(DialogQueue.DIALOG_PRIORITY_MIN);
                        myOwnDialog.display();
//                    }
//                    MyOwnDialog myOwnDialog2 =DialogManager.showDialog(MainActivity.this,"",imgurls.get(1));
//                    myOwnDialog2.setPriority(DialogQueue.DIALOG_PRIORITY_MIN);
//                    myOwnDialog2.display();
//
//                    MyOwnDialog myOwnDialog3 =DialogManager.showDialog(MainActivity.this,"",imgurls.get(2));
//                    myOwnDialog3.setPriority(DialogQueue.DIALOG_PRIORITY_MAX);
//                    myOwnDialog3.display();

                }
            }
        });
    }


}