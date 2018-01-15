package com.baidu.duer.dcs.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.duer.dcs.R;


/**
 * Toast统一管理类
 * Created by kenway on 17/3/9 18:02
 * Email : xiaokai090704@126.com
 */

public class ToastUtils {
    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;





    public static void showForumToast(Context context, String msg, int resDrawable) {

        //Inflater意思是充气
        //LayoutInflater这个类用来实例化XML文件到其相应的视图对象的布局
        LayoutInflater inflater =LayoutInflater.from(context);
        //通过制定XML文件及布局ID来填充一个视图对象
        View layout = inflater.inflate(R.layout.widget_toast,null);

        ImageView image = (ImageView) layout.findViewById(R.id.widget_toast_iv);
        //设置布局中图片视图中图片
        image.setImageResource(resDrawable);

        TextView title = (TextView) layout.findViewById(R.id.widget_toast_tv);
        //设置标题
        title.setText(msg);



        Toast toast= new Toast(context);
        toast.setGravity(Gravity.CENTER , 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
