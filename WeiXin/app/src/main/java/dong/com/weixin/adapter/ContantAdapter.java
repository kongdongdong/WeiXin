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
public class ContantAdapter extends BaseAdapter {
    Context mContext;
    List<Contant> contants;
    public ContantAdapter(Context mContext, List<Contant> contants) {
        this.mContext = mContext;
        this.contants = contants;
    }

    @Override
    public int getCount() {
        if(contants == null){
            return 0;
        }
        return contants.size();
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if(view == null){
            view = View.inflate(mContext, R.layout.item_list_contant,null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) view.findViewById(R.id.icon);
            viewHolder.indicator = (TextView) view.findViewById(R.id.indicator);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        if(i==0){
            viewHolder.indicator.setVisibility(View.VISIBLE);
            viewHolder.indicator.setText(contants.get(i).getFirstLitter());
        }else{
            if(!contants.get(i).getFirstLitter().equals(contants.get(i-1).getFirstLitter())){
                viewHolder.indicator.setVisibility(View.VISIBLE);
                viewHolder.indicator.setText(contants.get(i).getFirstLitter());
            }else{
                viewHolder.indicator.setVisibility(View.GONE);
            }
        }



        viewHolder.icon.setImageResource(contants.get(i).getIcon());

        viewHolder.name.setText(contants.get(i).getName());
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
