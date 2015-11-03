package dong.com.weixin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import dong.com.weixin.R;

public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText et_phone,et_pwd;
    private Button bt_login;
    private ImageView im_delete,im_delete_pwd;
    private TextView tv_title;
    private String phone,pwd;
    int length;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bt_login = (Button) findViewById(R.id.bt_login);
        im_delete = (ImageView) findViewById(R.id.im_delete);
        im_delete_pwd = (ImageView) findViewById(R.id.im_delete_pwd);
        tv_title = (TextView) findViewById(R.id.tv_title);

        tv_title.setText(R.string.login_type_phone);
        phone = et_phone.getText().toString();
        pwd = et_pwd.getText().toString();
        if(phone ==null || TextUtils.isEmpty(phone)){
            im_delete.setVisibility(View.INVISIBLE);
            bt_login.setBackgroundResource(R.drawable.btn_green_selector_tre);
            bt_login.setClickable(false);
        }else{
            im_delete.setVisibility(View.VISIBLE);
            bt_login.setBackgroundResource(R.drawable.btn_green_selector);
            bt_login.setClickable(true);
        }
        if(pwd == null || TextUtils.isEmpty(pwd)){
            im_delete_pwd.setVisibility(View.INVISIBLE);
        }else{
            im_delete_pwd.setVisibility(View.VISIBLE);
        }

        im_delete.setOnClickListener(this);
        im_delete_pwd.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        et_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence != null && !TextUtils.isEmpty(charSequence)){
                    im_delete_pwd.setVisibility(View.VISIBLE);
                }else{
                    im_delete_pwd.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                phone = et_phone.getText().toString();
                if(phone ==null || TextUtils.isEmpty(phone)){
                    im_delete.setVisibility(View.INVISIBLE);
                    bt_login.setBackgroundResource(R.drawable.btn_green_selector_tre);
                    bt_login.setClickable(false);
                }else{
                    bt_login.setClickable(true);
                    im_delete.setVisibility(View.VISIBLE);
                    bt_login.setBackgroundResource(R.drawable.btn_green_selector);
                }


                if(charSequence != null && !TextUtils.isEmpty(charSequence) && charSequence.length()>length){
                    if(charSequence.length()==3 || charSequence.length() == 8){
                        phone += " ";
                        et_phone.setText(phone);
                        et_phone.setSelection(charSequence.length()+1);
                    }
                }
                length = charSequence.length();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        SpannableString ss = new SpannableString("你的手机号码");
        AbsoluteSizeSpan size = new AbsoluteSizeSpan(15,true);
        ss.setSpan(size,0,ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        et_phone.setHint(ss);
        SpannableString ssTwo = new SpannableString("填写密码");
        AbsoluteSizeSpan sizeTwo = new AbsoluteSizeSpan(15,true);
        ssTwo.setSpan(sizeTwo, 0, ssTwo.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        et_pwd.setHint(ssTwo);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.im_delete:
                et_phone.setText("");
                break;
            case R.id.im_delete_pwd:
                et_pwd.setText("");
                break;
        }
    }
}
