package atguigu.com.exermebileplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;

import atguigu.com.exermebileplayer.fragment.BaseFragment;
import atguigu.com.exermebileplayer.paper.LocalAudioPaper;
import atguigu.com.exermebileplayer.paper.LocalVideoPaper;
import atguigu.com.exermebileplayer.paper.NetAudioPaper;
import atguigu.com.exermebileplayer.paper.NetlVideoPaper;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private FrameLayout fl_content;
    private RadioGroup rg_main;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment tempFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl_content = (FrameLayout)findViewById(R.id.fl_content);
        rg_main = (RadioGroup)findViewById(R.id.rg_main);
        dataAdd();
        rg_main.setOnCheckedChangeListener(this);
        rg_main.check(R.id.rb_local_video);
    }

    private void dataAdd() {
        fragments = new ArrayList<>();
        fragments.add(new LocalVideoPaper());
        fragments.add(new LocalAudioPaper());
        fragments.add(new NetAudioPaper());
        fragments.add(new NetlVideoPaper());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_local_video:
                position = 0 ;
                break;
            case R.id.rb_local_audio:
                position = 1 ;
                break;
            case R.id.rb_net_audio:
                position = 2 ;
                break;
            case R.id.rb_net_video:
                position = 3 ;
                break;
        }
        BaseFragment fragment = fragments.get(position);
        addFragment(fragment);
    }

    private void addFragment(BaseFragment fragment) {
        if(tempFragment != fragment) {
            //开启事物
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if(!fragment.isAdded()){
                if(tempFragment!=null){
                    ft.hide(tempFragment);
                }
                ft.add(R.id.fl_content,fragment);
            }else{
                if(tempFragment!=null){
                    ft.hide(tempFragment);
                }
                ft.show(fragment);
            }
            ft.commit();
            tempFragment = fragment;
        }

    }
}
