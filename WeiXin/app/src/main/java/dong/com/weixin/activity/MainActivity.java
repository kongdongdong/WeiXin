package dong.com.weixin.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dong.com.weixin.R;
import dong.com.weixin.adapter.ViewPgaerAdapter;
import dong.com.weixin.fragment.ContantFragment;
import dong.com.weixin.fragment.FindFragment;
import dong.com.weixin.fragment.MineFragment;
import dong.com.weixin.fragment.XinFragment;
import dong.com.weixin.ui.ActionItem;
import dong.com.weixin.ui.BladeView;
import dong.com.weixin.ui.ChangeColorIconWithText;
import dong.com.weixin.ui.PopMenu;
import dong.com.weixin.ui.TitlePopup;
import roboguice.inject.InjectView;

public class MainActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    @InjectView(R.id.add)
    private ImageView add;

    @InjectView(R.id.viewpager)
    private ViewPager viewpager;

    @Inject
    private Context mContext;

    private TitlePopup titlePopup;
    private PopMenu popMenu;
    private BladeView bladeView;
    private List<Fragment> listFragment;
    private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        initPopUp();
        initPopUpTwo();

    }



    private void initView() {
        listFragment = new ArrayList<Fragment>();
        listFragment.add(new XinFragment());
        listFragment.add(new ContantFragment());
        listFragment.add(new FindFragment());
        listFragment.add(new MineFragment());

        viewpager.setAdapter(new ViewPgaerAdapter(getSupportFragmentManager(), listFragment));

        ChangeColorIconWithText one = (ChangeColorIconWithText) findViewById(R.id.id_indicator_one);
        mTabIndicators.add(one);
        ChangeColorIconWithText two = (ChangeColorIconWithText) findViewById(R.id.id_indicator_two);
        mTabIndicators.add(two);
        ChangeColorIconWithText three = (ChangeColorIconWithText) findViewById(R.id.id_indicator_three);
        mTabIndicators.add(three);
        ChangeColorIconWithText four = (ChangeColorIconWithText) findViewById(R.id.id_indicator_four);
        mTabIndicators.add(four);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);


        one.setIconAlpha(1.0f);
    }

    private void initListener() {
        viewpager.setOnPageChangeListener(this);
        add.setOnClickListener(this);
    }

    private void initPopUp() {
        // 实例化标题栏弹窗
        titlePopup = new TitlePopup(this, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        titlePopup.setItemOnClickListener(onitemClick);
        // 给标题栏弹窗添加子类
        titlePopup.addAction(new ActionItem(this, R.string.menu_groupchat,
                R.mipmap.icon_menu_group));
        titlePopup.addAction(new ActionItem(this, R.string.menu_addfriend,
                R.mipmap.icon_menu_addfriend));
        titlePopup.addAction(new ActionItem(this, R.string.menu_qrcode,
                R.mipmap.icon_menu_sao));
        titlePopup.addAction(new ActionItem(this, R.string.menu_money,
                R.mipmap.abv));
    }
    private void initPopUpTwo() {

        popMenu = new PopMenu(this);
        List<PopMenu.MenuItemModel> items = new ArrayList<PopMenu.MenuItemModel>();
        items.add(new PopMenu.MenuItemModel(R.mipmap.icon_menu_group,"群聊"));
        items.add(new PopMenu.MenuItemModel(R.mipmap.icon_menu_addfriend,"添加好友"));
        items.add(new PopMenu.MenuItemModel( R.mipmap.icon_menu_sao, "扫一扫"));
        items.add(new PopMenu.MenuItemModel(R.mipmap.abv, "收钱"));
        popMenu.addItems(items);
        popMenu.setOnDismissListener(new PopMenu.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        popMenu.setOnItemClickListener(new PopMenu.OnItemClickListener() {
            @Override
            public void onItemClick(int index) {

            }
        });

    }
    private TitlePopup.OnItemOnClickListener onitemClick = new TitlePopup.OnItemOnClickListener() {

        public void onItemClick(ActionItem item, int position) {

        }
    };
    /**
     * 重置其他的TabIndicator的颜色
     */
    private void resetOtherTabs(){
        for (int i = 0; i < mTabIndicators.size(); i++)
        {
            mTabIndicators.get(i).setIconAlpha(0);
        }


    }
    @Override
    public void onClick(View view) {


        switch (view.getId())
        {
            case R.id.id_indicator_one:
                resetOtherTabs();
                mTabIndicators.get(0).setIconAlpha(1.0f);
                viewpager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:
                resetOtherTabs();
                mTabIndicators.get(1).setIconAlpha(1.0f);
                viewpager.setCurrentItem(1, false);
                break;
            case R.id.id_indicator_three:
                resetOtherTabs();
                mTabIndicators.get(2).setIconAlpha(1.0f);
                viewpager.setCurrentItem(2, false);
                break;
            case R.id.id_indicator_four:
                resetOtherTabs();
                mTabIndicators.get(3).setIconAlpha(1.0f);
                viewpager.setCurrentItem(3, false);
                break;
            case R.id.add:
               // titlePopup.show(view);
                popMenu.showAsDropDown(view);
                break;
        }
    }

    private int position;
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //滑动渐变切换图片
        if (positionOffset > 0)
        {
            ChangeColorIconWithText left = mTabIndicators.get(position);
            ChangeColorIconWithText right = mTabIndicators.get(position + 1);
            left.setPosition(position);
            left.setIconAlpha(1 - positionOffset);
            right.setPosition(position + 1);
            right.setIconAlpha(positionOffset);
        }

        System.out.println("positionOffset="+positionOffset);


    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
        //点击切换图片
        ChangeColorIconWithText left = mTabIndicators.get(position);
        left.setPosition(position);



    }

    @Override
    public void onPageScrollStateChanged(int state) {
        bladeView = (BladeView) findViewById(R.id.bladeView);
        if(this.position == 1 && state == 1){

            bladeView.dismissPopup();
            bladeView.invalidate();
            bladeView.choose = -1;
            bladeView.showBkg = false;
            bladeView.setVisibility(View.INVISIBLE);
        }
        if(this.position == 1 && state == 0){
            bladeView.setVisibility(View.VISIBLE);
        }
        System.out.println("state="+state);
    }
}
