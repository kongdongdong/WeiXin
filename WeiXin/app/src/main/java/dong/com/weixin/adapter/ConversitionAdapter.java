package dong.com.weixin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dong.com.weixin.R;
import dong.com.weixin.bean.Contant;

/**
 * Created by dong on 2015/10/8.
 */
public class ConversitionAdapter extends BaseAdapter {
    Context mContext;

    public ConversitionAdapter(Context mContext) {
        this.mContext = mContext;

    }

    @Override
    public int getCount() {

        return 20;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if(view == null){
            view = View.inflate(mContext, R.layout.item_list_xin,null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) view.findViewById(R.id.icon);
            viewHolder.indicator = (TextView) view.findViewById(R.id.indicator);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder{
        TextView indicator;
        TextView name;
        ImageView icon;
    }

}
