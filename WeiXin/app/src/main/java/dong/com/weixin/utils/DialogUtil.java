package dong.com.weixin.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import dong.com.weixin.R;
import dong.com.weixin.ui.Global;

/**
 * Created by dong on 2015/11/2.
 */
public class DialogUtil {

    private ListView listview;
    private Dialog dialog;
    private String[] strs;
    private Context context;
    private View view;
    public DialogUtil(String[] strs,Context context){
        this.strs = strs;
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.dialog_list,null);
        listview  = (ListView) view.findViewById(R.id.listview);
        init();
    }

    private void init() {
        Adapter adapter = new Adapter(context);
        listview.setAdapter(adapter);
        dialog = new Dialog(context, R.style.mydialog);// 创建自定义样式

        dialog.setContentView(view);
    }

    /**
     * 显示对话框
     */
    public void ShowDialog(){
        setVibrator();
        int width = DesplayUtil.getDesplayWidth(context);//得到当前显示设备的宽度，单位是像素
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();//得到这个dialog界面的参数对象
        params.width =width - (width/6);//设置dialog的界面宽度
        params.height =  AbsListView.LayoutParams.WRAP_CONTENT;//设置dialog高度为包裹内容
        params.gravity = Gravity.CENTER;//设置dialog的重心
        //dialog.getWindow().setLayout(width-(width/6),  LayoutParams.WRAP_CONTENT);//用这个方法设置dialog大小也可以，但是这个方法不能设置重心之类的参数，推荐用Attributes设置
        dialog.getWindow().setAttributes(params);//最后把这个参数对象设置进去，即与dialog绑定
        dialog.show();
    }

    /**
     * listView的item点击监听
     * @param listener
     */
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
       if(listview != null){
           listview.setOnItemClickListener(listener);
       }

    }

    /**
     * 隐藏对话框
     */
    public void dialogDimiss(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    /**
     * 设置震动100毫秒
     */
    public void setVibrator(){
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);
    }

    private class Adapter extends BaseAdapter{
        Context mContext;
        public Adapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            if(strs == null){
                return 0;
            }
            return strs.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null){
                view = LayoutInflater.from(mContext).inflate(R.layout.dialog_list_item,null);
            }
            TextView textview = (TextView) view.findViewById(R.id.textview);
            textview.setText(strs[i]);
            return view;
        }
    }

}
