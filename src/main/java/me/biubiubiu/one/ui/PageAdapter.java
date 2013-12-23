package me.biubiubiu.one.ui;

import java.util.List;
import java.util.Map;

import java.util.Stack;

import me.biubiubiu.one.R;
import me.biubiubiu.one.ui.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PageAdapter extends BaseAdapter {

    private Context mContext;
    private List<Map<String, String>> mData;

    public PageAdapter(Context context) {
        mContext = context;
    }

    public void loadData(List<Map<String, String>> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public Map<String, String> getItemData(int position) {
        return mData.get(position);
    }


    public int getCount() {
        if (mData == null) {
            return 0;
        } else {
            return mData.size();
        }
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_page, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.title = (TextView)view.findViewById(R.id.title);
            holder.dest = (TextView)view.findViewById(R.id.dest);
            holder.start = (TextView)view.findViewById(R.id.start);
            holder.price = (TextView)view.findViewById(R.id.price);
            holder.distance = (TextView)view.findViewById(R.id.distance);
            holder.portrait = (ImageView)view.findViewById(R.id.portrait);
            view.setTag(holder);
        }

        Map<String, String> item = mData.get(position);
        ViewHolder holder = (ViewHolder)view.getTag();
        holder.title.setText(item.get("title"));
        holder.dest.setText("目的地:" + item.get("dest_addr"));
        holder.start.setText("出发地:" + item.get("start_addr"));
        holder.price.setText("搭车币:" + item.get("price"));
        holder.distance.setText("距离:" + item.get("distance"));
        BaseActivity act = (BaseActivity)mContext;
        act.loadPhoto(holder.portrait, item.get("image_url"));
        return view;
    }

    public class ViewHolder {
        public TextView title;
        public TextView dest;
        public TextView start;
        public TextView price;
        public TextView distance;
        public ImageView portrait;
    }
}
