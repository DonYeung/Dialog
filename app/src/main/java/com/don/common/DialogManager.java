package com.don.common;

/**
 * Created by drcom on 2017/7/19.
 */

import android.content.Context;

import com.don.widget.MyOwnDialog;

/**
 * 全局Dialog管理
 * Created by Don on 2017/7/19.
 */

public class DialogManager {

    /**
     * 全局Dialog队列
     */
    private static DialogQueue dialogQueue;

    /**
     * 当前显示的Dialog
     */
    public static MyOwnDialog current;

    /**
     * 获取全局Dialog队列
     * @return
     */
    public static DialogQueue getDialogQueue() {
        if(dialogQueue == null)
            dialogQueue = new DialogQueue();
        return dialogQueue;
    }

    /**
     * 显示一个Dialog
     * <p>如果当前Dialog({@link DialogManager#current})不为空，则该Dialog按优先级入队；否则直接显示。</p>
     * @param context
     * @param content 内容
     * @return 返回将要显示的Dialog实例
     */
    public static MyOwnDialog showDialog(Context context,String content,String imgurl){
        MyOwnDialog.Builder builder = new MyOwnDialog.Builder(context);
        builder.setImageUrl(imgurl);
        builder.setMessage(content);
        return builder.create();
    }
}