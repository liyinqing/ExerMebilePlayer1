package atguigu.com.exermebileplayer.paper;

import android.view.View;
import android.widget.TextView;

import atguigu.com.exermebileplayer.fragment.BaseFragment;

/**
 * 作者：李银庆 on 2017/5/19 21:28
 */
public class NetlVideoPaper extends BaseFragment {
    @Override
    public View initView() {

        TextView textView = new TextView(context);
        textView.setText("网络视频");
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
