package com.don.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.don.common.DialogManager;
import com.don.dialog.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by drcom on 2017/7/19.
 */

public class MyOwnDialog extends Dialog {
    /**
     * Dialog优先级
     */
    private int priority;
    private MyOwnDialog dialogPro;

    public MyOwnDialog(Context context) {
        super(context);
        dialogPro = this;

    }

    public MyOwnDialog(Context context, int theme) {
        super(context, theme);
        dialogPro = this;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public interface OnCustomDialogListener {
        public void back(String name);
    }

    public MyOwnDialog getDialogPro() {
        return dialogPro;
    }

    public void setDialogPro(MyOwnDialog dialogPro) {
        this.dialogPro = dialogPro;
    }

    public void display() {
        display(this);
    }

    /**
     * 按照队列中的顺序出队显示
     *
     * @param dialog 待显示的对话框
     *               如果为null，表示继续显示Queue中其他的dialog
     */
    private void display(MyOwnDialog dialog) {
        if (dialog != null) {
            DialogManager.getDialogQueue().offer(dialog);
        }

        if (DialogManager.current == null) {
            DialogManager.current = DialogManager.getDialogQueue().poll();

            if (DialogManager.current != null) {
                DialogManager.current.getDialogPro().show();
                DialogManager.current.getDialogPro().setOnDismissListener(new AlertDialog.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        DialogManager.current = null;
                        // dialog关闭后，继续show(null)
                        display(null);
                    }
                });
            }
        }
    }

    public static class Builder {

        private Context context;
        private String message;

        private View contentView, v;
        private String imgurl;

        private ImageView iv_ad, iv_close;
        private FrameLayout flContentContainer;
        private ImageView imageView;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        //设置位置（传listview的item过来）
        public Builder setImageUrl(String imgurl) {
            this.imgurl = imgurl;

            return this;
        }

        public MyOwnDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //主题可以换
            final MyOwnDialog dialog = new MyOwnDialog(context,
                    R.style.LocatonDialogStyle);
            View layout = inflater.inflate(R.layout.alertdialog_mine, null);

            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));



            flContentContainer = (FrameLayout) layout.findViewById(R.id.fl_content_container);


                 imageView = new ImageView(context);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                flContentContainer.addView(imageView);

                Log.i("glide", "imgurl:" + imgurl);

                Glide.with(context)
                        .load(imgurl)
                        .crossFade()
                        .placeholder(R.mipmap.ic_adc)
                        .error(R.mipmap.ic_launcher_round)
                        .into(imageView);


            iv_close = (ImageView) layout.findViewById(R.id.iv_close);

            iv_close.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                        dialog.dismiss();

                }
            });

            dialog.setTitle(message);
            dialog.setContentView(layout);
            return dialog;
        }
    }


}