package dong.com.weixin.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import dong.com.weixin.R;
import dong.com.weixin.adapter.ConversitionAdapter;
import dong.com.weixin.utils.DialogUtil;
import dong.com.weixin.utils.LogUtils;

public class XinFragment extends Fragment {

    private ListView conversition_listview;
    private ConversitionAdapter adapter;
    private Context mContext;
    private DialogUtil dialog;
    private String[] strs = {"标为未读","取消关注","删除该聊天"};
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_xin, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        initView();
    }

    private void initView() {
        conversition_listview = (ListView) getActivity().findViewById(R.id.conversition_listview);
        adapter = new ConversitionAdapter(mContext);
        conversition_listview.setAdapter(adapter);
        dialog = new DialogUtil(strs,mContext);
        setListener();
    }

    private void setListener() {


        conversition_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialog.ShowDialog();
                return false;
            }
        });

        dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtils.i(i+"");
                dialog.dialogDimiss();
            }
        });
    }


}
