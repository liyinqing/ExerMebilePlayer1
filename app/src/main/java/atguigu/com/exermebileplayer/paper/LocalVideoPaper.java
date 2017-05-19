package atguigu.com.exermebileplayer.paper;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import atguigu.com.exermebileplayer.R;
import atguigu.com.exermebileplayer.activity.SystemVideoPlayerActivity;
import atguigu.com.exermebileplayer.adapter.LocalVideoAdapter;
import atguigu.com.exermebileplayer.domain.MebileData;
import atguigu.com.exermebileplayer.fragment.BaseFragment;

/**
 * 作者：李银庆 on 2017/5/19 21:28
 */
public class LocalVideoPaper extends BaseFragment implements AdapterView.OnItemClickListener {
    private ListView lv;
    private TextView tv_data;
    private ArrayList<MebileData> mebileData;
    private LocalVideoAdapter adapter;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_video_paper,null);
        lv = (ListView) view.findViewById(R.id.lv);
        tv_data = (TextView) view.findViewById(R.id.tv_data);
        lv.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getData();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mebileData != null && mebileData.size()>0){
                tv_data.setVisibility(View.GONE);
                adapter = new LocalVideoAdapter(context,mebileData);
                lv.setAdapter(adapter);

            }else {
                tv_data.setVisibility(View.VISIBLE);
            }

        }
    };
    public void getData() {
        new Thread(){
            public void run(){
                mebileData = new ArrayList<MebileData>();
                ContentResolver resolver = context.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        ;
                String[] datas ={
                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DURATION,
                        MediaStore.Video.Media.SIZE,
                        MediaStore.Video.Media.DATA
                };
                Cursor cursor = resolver.query(uri, datas, null, null, null);
                if(cursor != null){
                    while(cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                        long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                        long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.SIZE));
                        String data = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                        mebileData.add(new MebileData(name,duration,size,data));
                        handler.sendEmptyMessage(0);
                    }
                }

            }
        }.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MebileData item = adapter.getItem(position);
        String data = item.getData();
        Intent intent = new Intent(context,SystemVideoPlayerActivity.class);
        intent.setDataAndType(Uri.parse(data),"video/*");
        startActivity(intent);
    }
}
