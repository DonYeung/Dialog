package com.don.widget;

import android.animation.AnimatorInflater;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.don.common.DialogManager;
import com.don.dialog.R;

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

        private String imgurl;

        private ImageView iv_ad, iv_close;
        private FrameLayout flContentContainer;
        private ImageView imageView;
        private LinearLayout anim_container;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
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

//            layout.setAnimation(setAnimation());
            anim_container= (LinearLayout) layout.findViewById(R.id.anim_container);

            iv_ad= (ImageView) layout.findViewById(R.id.iv_ad);
//            flContentContainer = (FrameLayout) layout.findViewById(R.id.fl_content_container);
//                 imageView = new ImageView(context);
//                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                flContentContainer.addView(imageView);

                Log.i("glide", "imgurl:" + imgurl);

                Glide.with(context)
                        .load(imgurl)
                        .crossFade()
                        .placeholder(R.mipmap.ic_adc)
                        .error(R.mipmap.ic_launcher_round)
                        .into(iv_ad);
            iv_ad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"广告点击"+message,Toast.LENGTH_SHORT).show();
                }
            });


            iv_close = (ImageView) layout.findViewById(R.id.iv_close);

            iv_close.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                        dialog.dismiss();

                }
            });
            dialog.setContentView(layout);
            return dialog;
        }
        protected Animation setAnimation() {
            Animation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                    0.5f);// 从0.5倍放大到1倍
            anim.setDuration(1000);
            return anim;
        }
    }


}