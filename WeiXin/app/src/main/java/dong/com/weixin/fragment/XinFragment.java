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

public class XinFragment extends Fragment {

    private ListView conversition_listview;
    private ConversitionAdapter adapter;
    private Context mContext;

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

        setListener();
    }

    private void setListener() {
        conversition_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] strs = {"我们好","你们好","他们好"};

                DialogUtil dialog = new DialogUtil(strs);
                dialog.ShowDialog(mContext);
                return false;
            }
        });
    }


}
