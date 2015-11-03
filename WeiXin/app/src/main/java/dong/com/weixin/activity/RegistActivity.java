package dong.com.weixin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import dong.com.weixin.R;
import roboguice.inject.InjectView;

public class RegistActivity extends Activity {

    @InjectView(R.id.tv_title) private TextView tv_title;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
    }

    private void initView() {


        tv_title.setText(R.string.regist_type_phone);
    }


}
