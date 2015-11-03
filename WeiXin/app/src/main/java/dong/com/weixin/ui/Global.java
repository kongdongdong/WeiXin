package dong.com.weixin.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;



/**
 * Created with IntelliJ IDEA.
 * User: sfce
 * Date: 13-10-22
 * Time: 下午1:00
 */
public class Global {
    public static final boolean DEBUG = true;

    public static class Notification {
        @SuppressLint("NewApi")
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public static void updateNotifications(Context context,
                String title, String content, Class<? extends Activity> activityClass,
                    int iconRes, int count, boolean led, int id,Map<String,Object> mapData) {
            if (Build.VERSION.SDK_INT >= 11) {
                //Android 3.0 = 11
                //声明通知（消息）管理器
                NotificationManager mNotificationManager;
                Intent mIntent;
                PendingIntent mPendingIntent;
                //声明Notification对象
                android.app.Notification mNotification;

                //初始化NotificationManager对象
                mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mIntent = new Intent(context, activityClass);
                mIntent.putExtra("sectionNumber",(Integer)mapData.get("sectionNumber"));
                mIntent.putExtra("lanmuTitle",(String)mapData.get("lanmuTitle"));
                mIntent.putExtra("articleTitle",(String)mapData.get("articleTitle"));
                mIntent.putExtra("id", (Integer)mapData.get("id"));
                mIntent.putExtra("date", (String)mapData.get("date"));
                mIntent.putExtra("startType","article");
                mIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                /** 设置 */

                //主要是设置点击通知时显示内容的类
                mPendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                //构造Notification对象
                if (Build.VERSION.SDK_INT >= 16) {
                    mNotification = new android.app.Notification.Builder(context)
                            .setTicker(content)
                            .setContentTitle(title)
                            .setContentText(content)
                            .setSmallIcon(iconRes)
                            .setContentIntent(mPendingIntent)
                            .setNumber(count)
                            .setAutoCancel(true)
                            .build();
                } else {
                    mNotification = new android.app.Notification.Builder(context)
                            .setTicker(content)
                            .setContentTitle(title)
                            .setContentText(content)
                            .setSmallIcon(iconRes)
                            .setContentIntent(mPendingIntent)
                            .setNumber(count)
                            .setAutoCancel(true)
                            .getNotification();
                }
                if (led) {
                    mNotification.defaults = android.app.Notification.DEFAULT_LIGHTS;
                    mNotification.flags |= android.app.Notification.FLAG_SHOW_LIGHTS;
                }
                mNotification.flags |= android.app.Notification.FLAG_AUTO_CANCEL;
                mNotificationManager.notify(id, mNotification);
            } else {
                //声明通知（消息）管理器
                NotificationManager mNotificationManager;
                Intent mIntent;
                PendingIntent mPendingIntent;
                //声明Notification对象
                android.app.Notification mNotification;

                //初始化NotificationManager对象
                mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mIntent = new Intent(context, activityClass);
                mIntent.putExtra("sectionNumber",(Integer)mapData.get("sectionNumber"));
                mIntent.putExtra("lanmuTitle",(String)mapData.get("lanmuTitle"));
                mIntent.putExtra("articleTitle",(String)mapData.get("articleTitle"));
                mIntent.putExtra("id", (Integer)mapData.get("id"));
                mIntent.putExtra("date", (String)mapData.get("date"));
                mIntent.putExtra("startType","article");
                mIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                /** 设置 */

                //主要是设置点击通知时显示内容的类,requestCode传入id以区分
                mPendingIntent = PendingIntent.getActivity(context, id, mIntent, PendingIntent.FLAG_UPDATE_CURRENT); //如果转移内容则用m_Intent();
                //构造Notification对象
                mNotification = new android.app.Notification();
                //设置通知在状态栏显示的图标
                mNotification.icon = iconRes;
                //当我们点击通知时显示的内容
                mNotification.tickerText = content;
                //通知时发出默认的LED
                if (led) {
                    mNotification.defaults = android.app.Notification.DEFAULT_LIGHTS;
                    mNotification.flags |= android.app.Notification.FLAG_SHOW_LIGHTS;
                }
                mNotification.flags |= android.app.Notification.FLAG_AUTO_CANCEL;

                mNotification.number = count;
                //设置通知显示的参数
                mNotification.setLatestEventInfo(context, title, content, mPendingIntent);
                //可以理解为执行这个通知
                mNotificationManager.notify(id, mNotification);
            }
        }

    }




        

    public static class System {
        public static void hideIME(Activity context) {
            final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            View view = context.getCurrentFocus();
            if (view != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);// 隐藏软键盘
            }
        }

        public static void hideIME(Activity context, View focus) {
            final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (focus != null) {
                imm.hideSoftInputFromWindow(focus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);// 隐藏软键盘
            }
        }

        public static void showIME(Activity context) {
            final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            View view = context.getCurrentFocus();
            if (view != null) {
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT); // 显示软键盘
            }
        }

        public static void showIME(Activity context, View focus) {
            final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (focus != null) {
                imm.showSoftInput(focus, InputMethodManager.SHOW_IMPLICIT); // 显示软键盘
            }
        }
    }

    public static class Form {
        public final static String REGEX_TELEPHONE = "^(1(([35][0-9])|(47)|[8][01236789]))\\d{8}$";
        public final static String REGEX_NUMBER = "^[1-9]\\d*$";
        public static final String IDCARD = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";

        public static boolean isTelephone(CharSequence resource) {
            return resource.toString().matches(REGEX_TELEPHONE);
        }

        public static boolean isNumber(CharSequence resource) {
            return resource.toString().matches(REGEX_NUMBER);
        }

        public static boolean isIDCard(CharSequence resource) {
            return resource.toString().matches(IDCARD);
        }

        public static Spanned toLinkStyle(CharSequence resource) {
            return Html.fromHtml("<u><i>" + resource + "</i></u>");
        }
    }

    public static class Device {
        public static void installApk(Context context, File apk) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(apk), "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

        public static String getImei(final Context ctx) {
            TelephonyManager telManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
            if (DEBUG)
                return "359513040775545";
            //		return "358059041252309";
            String imei = telManager.getDeviceId();
            if (imei == null) {
                return "";
            }
            return imei;
        }

        public static String getImsi(final Context ctx) {
            TelephonyManager telManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
            String imsi = telManager.getSubscriberId();
            if (imsi == null) {
                return "";
            }
            return imsi;
        }


        /**
         * @return 获取状态栏高度
         */
        public static int getStatusBarHeight(final Context ctx) {
            Class<?> clazz = null;
            Object obj = null;
            Field field = null;
            int value = 0, statusBarHeight = 0;
            try {
                clazz = Class.forName("com.android.internal.R$dimen");
                obj = clazz.newInstance();
                field = clazz.getField("status_bar_height");
                value = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = ctx.getResources().getDimensionPixelSize(value);
                return statusBarHeight;
            } catch (Exception e1) {
                e1.printStackTrace();
                return 0;
            }
        }

        /**
         * @return 得到屏幕密度
         */
        public static float getDensity(final Context context) {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
            float density = dm.density;
            return density;
        }

        /**
         * @return 获取屏幕宽度
         */
        @SuppressLint("NewApi")
		public static int getScreenWidth(final Context context) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            if (getAndroidSDKVersion() >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                Point point = new Point();
                display.getSize(point);
                return point.x;
            }
            return display.getWidth();
        }

        /**
         * @return 获取屏幕高度
         */
        @SuppressLint("NewApi")
		public static int getScreenHeight(final Context context) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            if (getAndroidSDKVersion() >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                Point point = new Point();
                display.getSize(point);
                return point.y;
            }
            int height = display.getHeight();
            return height;
        }

        /**
         * 获取设备型号
         *
         * @return
         */
        public static String getDeviceModel() {
            String model = Build.MODEL;
            return model;
        }

        /**
         * 检查SDCard是否是可写的
         *
         * @return
         */
        public static boolean isSdcardWriteAble() {
            String state = Environment.getExternalStorageState();
            boolean externalStorageWriteable = false;
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                // We can read and write the media
                externalStorageWriteable = true;
            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                // We can only read the media
                externalStorageWriteable = true;
            } else {
                // Something else is wrong. It may be one of many other states, but all we need
                // to know is we can neither read nor write
                externalStorageWriteable = false;
            }
            return externalStorageWriteable;
        }

        /**
         * 获取当前运行的SDK版本
         *
         * @return SDK版本
         */
        public static int getAndroidSDKVersion() {
            int version = 0;
            try {
                version = Integer.valueOf(Build.VERSION.SDK_INT);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return version;
        }

        /**
         * 获取软件自身的信息
         *
         * @return
         */
        public static PackageInfo getSelfVersion(final Context context) {
            try {
                PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(),
                        PackageManager.GET_ACTIVITIES);
                return info;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static boolean isInstalled(Context context, String packageName) {
            PackageManager pm = context.getPackageManager();
            for (PackageInfo packageInfo : pm.getInstalledPackages(0)) {
                if (packageName.equals(packageInfo.packageName)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class IOUtil {
        /**
         * 从输入流中读取所有的字节
         *
         * @param inStream
         * @return
         * @throws IOException
         */
        public static byte[] readStream(InputStream inStream) throws IOException {
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int len = -1;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            return outSteam.toByteArray();
        }

        public static void string2File(String string, String path) throws IOException {
            File file = new File(path);
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(string.getBytes());
            outStream.flush();
            outStream.close();
        }

        public static void write2File(byte[] bytes, String path) throws IOException {
            File file = new File(path);
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(bytes);
            outStream.flush();
            outStream.close();
        }

        public interface FileWriterWatcher {
            void notify(int progress);

            void onFinish(File apk);

            boolean isAlive();

            void setAlive(boolean alive);
        }


        public static void write2File(byte[] datas, File file, FileWriterWatcher watcher) throws IOException {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            ByteArrayInputStream inStream = new ByteArrayInputStream(datas);
            FileOutputStream outStream = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int len;
            int sum = 0;
            while (watcher.isAlive() && (len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
                sum += len;
                watcher.notify(sum);
            }
            inStream.close();
            outStream.flush();
            outStream.close();
            watcher.onFinish(file);
        }

        public static void write2File(byte[] bytes, File file) throws IOException {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            FileOutputStream outStream = new FileOutputStream(file);

            outStream.write(bytes);
            outStream.flush();
            outStream.close();
        }

        public static String fileInAssets2String(Context context, String fileName) {
            try {
                return new String(readStream(context.getAssets().open(fileName)));
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        public static Bitmap getBitmapFromAsset(Context context, String strName) {
            String picPathString = "images/" + strName;

            AssetManager assetManager = context.getAssets();

            InputStream is = null;
            try {
                is = assetManager.open(picPathString);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        }
    }
}
