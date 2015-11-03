package dong.com.weixin.utils;

import android.content.Context;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by dong on 2015/10/10.
 */
public class DesplayUtil {



    /**
     * 用于获取状态栏的高度。
     * @return 返回状态栏高度的像素值。
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight=0;
        if (statusBarHeight == 0) {
            try {
                Class c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = context.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }

    public static int getDesplayHight(Context context){
        // 获得屏幕的宽度和高度
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

       return  wm.getDefaultDisplay().getHeight();
    }
    public static int getDesplayWidth(Context context){
        // 获得屏幕的宽度和高度
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        return  wm.getDefaultDisplay().getWidth();
    }
}
