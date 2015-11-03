package dong.com.weixin.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import dong.com.weixin.R;

/**
 * Created by dong on 2015/11/2.
 */
public class DialogUtil {

    private ListView listview;
    private String[] strs;
    public DialogUtil(String[] strs){
        this.strs = strs;
    }

    public void ShowDialog(Context context){
        AlertDialog dialog = null;
        AlertDialog.Builder builder=null;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_list,null);
        listview  = (ListView) view.findViewById(R.id.listview);
        Adapter adapter = new Adapter(context);
        listview.setAdapter(adapter);

        builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog = builder.create();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        // 设置透明度为0.3
        //lp.alpha = 0.0f;
        window.setAttributes(lp);

        dialog.show();

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
