package atguigu.com.exermebileplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import atguigu.com.exermebileplayer.R;
import atguigu.com.exermebileplayer.Utils.Utils;
import atguigu.com.exermebileplayer.domain.MebileData;

/**
 * 作者：李银庆 on 2017/5/19 22:17
 */
public class LocalVideoAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<MebileData> datas;
private Utils utils;
    public LocalVideoAdapter(Context context, ArrayList<MebileData> mebileData) {
        this.context = context;
        this.datas = mebileData;
        utils = new Utils();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public MebileData getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_local_video, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_duration = (TextView) convertView.findViewById(R.id.tv_duration);
            viewHolder.tv_size = (TextView) convertView.findViewById(R.id.tv_size);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MebileData mebileData = datas.get(position);
        viewHolder.tv_name.setText(mebileData.getName());
        viewHolder.tv_size.setText(android.text.format.Formatter.formatFileSize(context, mebileData.getSize()));
        viewHolder.tv_duration.setText(utils.stringForTime((int) mebileData.getSize()));

        return convertView;
    }

    static class ViewHolder {
        TextView tv_name;
        TextView tv_duration;
        TextView tv_size;
    }
}
