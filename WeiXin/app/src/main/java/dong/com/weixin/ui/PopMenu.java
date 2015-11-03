package dong.com.weixin.ui;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import dong.com.weixin.R;


/**
 * 修改于：2013-2-28 17:03:35
 * 	修正 ListView item 点击响应失败！
 * 
 * @author dong
 *
 */
public class PopMenu implements OnItemClickListener {
	public interface OnItemClickListener {
		public void onItemClick(int index);
    }
    public interface OnDismissListener {
        public void onDismiss();
    }
	private List<MenuItemModel> itemList;
	private Context context;
	private PopupWindow popupWindow;
	private ListView listView;
	private OnItemClickListener listener;
	private LayoutInflater inflater;
    private OnDismissListener dismissListener;

	
	public PopMenu(Context context) {
		this.context = context;

		itemList = new ArrayList<MenuItemModel>();

		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.popmenu, null);

		listView = (ListView) view.findViewById(R.id.listView);
		listView.setAdapter(new PopAdapter());
		listView.setOnItemClickListener(this);

		popupWindow = new PopupWindow(view, 
				context.getResources().getDimensionPixelSize(R.dimen.popmenu_width),  //这里宽度需要自己指定，使用 WRAP_CONTENT 会很大
				LayoutParams.WRAP_CONTENT);
		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (listener != null) {
			listener.onItemClick(position);
		}
		dismiss();
	}

	// 设置菜单项点击监听器
	public void setOnItemClickListener(OnItemClickListener listener) {
		 this.listener = listener;
	}

	// 批量添加菜单项
	public void addItems(List<MenuItemModel> items) {
		for(MenuItemModel itemModel:items){
			itemList.add(itemModel);
		}
	}

	// 单个添加菜单项
	public void addItem(MenuItemModel itemModel) {
		itemList.add(itemModel);
	}

	// 下拉式 弹出 pop菜单 parent 右下角
	public void showAsDropDown(View parent) {
		/*popupWindow.showAsDropDown(parent, 10,
		// 保证尺寸是根据屏幕像素密度来的
				context.getResources().getDimensionPixelSize(R.dimen.popmenu_yoff));*/
		popupWindow.showAsDropDown(parent);
		// 使其聚集
		popupWindow.setFocusable(false);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(true);
		// 刷新状态
		popupWindow.update();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (dismissListener != null) {
                    dismissListener.onDismiss();
                }
            }
        });
	}

    public void setOnDismissListener(OnDismissListener listener){
        this.dismissListener = listener;
    }


	// 隐藏菜单
	public void dismiss() {
		popupWindow.dismiss();
	}

    // 显示状态
    public boolean isShowing() {
        return popupWindow.isShowing();
    }

	// 适配器
	private final class PopAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return itemList.size();
		}

		@Override
		public Object getItem(int position) {
			return itemList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.pomenu_item, null);
				holder.icon = (ImageView)convertView.findViewById(R.id.item_pomenu_img);
				holder.textView = (TextView) convertView.findViewById(R.id.item_pomenu_txt);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.icon.setImageResource(itemList.get(position).getIconResID());
			holder.textView.setText(itemList.get(position).getItemStr());

			return convertView;
		}

		private final class ViewHolder {
			ImageView icon;
			TextView textView;
		}
	}
	
	public static class MenuItemModel{
		private int iconResID;
		private String itemStr;
		
		public MenuItemModel(int iconResID,String itemStr){
			this.iconResID = iconResID;
			this.itemStr = itemStr;
		}
		public int getIconResID() {
			return iconResID;
		}
		public void setIconResID(int iconResID) {
			this.iconResID = iconResID;
		}
		public String getItemStr() {
			return itemStr;
		}
		public void setItemStr(String itemStr) {
			this.itemStr = itemStr;
		}
	}
}
