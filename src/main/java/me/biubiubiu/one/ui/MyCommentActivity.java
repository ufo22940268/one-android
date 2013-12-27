package me.biubiubiu.one.ui;

import android.util.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.app.*;
import android.os.*;
import android.text.*;
import android.database.*;
import android.net.*;
import android.opengl.*;
import android.graphics.*;
import android.view.animation.*;
import android.text.TextUtils;
import android.support.v4.app.*;
import android.support.v4.app.Fragment;
import me.biubiubiu.one.util.HttpHandler.ResponseHandler;
import me.biubiubiu.one.util.Parser;
import com.kanak.emptylayout.EmptyLayout;

import java.util.*;
import butterknife.InjectView;
import butterknife.Views;

import me.biubiubiu.one.R;

import android.os.Bundle;

public class MyCommentActivity extends BaseActivity {

    @InjectView(R.id.list) ListView mListView;
    private CommentAdapter mAdapter;
    private EmptyLayout mEmptyLayout;
    
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.my_comment);
        Views.inject(this);
        mAdapter = new CommentAdapter();
        mListView.setAdapter(mAdapter);
        mEmptyLayout = new EmptyLayout(this, mListView);
        loadData();
        setTitle("我的评价");
    }

    private void loadData() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", getUserId());
        mEmptyLayout.showLoading();
        mHttpHandler.get("comments", map, new ResponseHandler() {
                @Override
                public void onSuccess(String result) {
                    if (isFinished()) {
                        return;
                    }
                    List<Map<String, String>> list = Parser.items(result);
                    if (list != null && list.size() > 0) {
                        mAdapter.loadData(list);
                    }
                }
            });
    }

    public class CommentAdapter extends MapAdapter {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (convertView == null) {
                view = LayoutInflater.from(MyCommentActivity.this).inflate(R.layout.list_item_comment, parent, false);
            }

            Map<String, String> map = getItemData(position);
            ((TextView)view.findViewById(R.id.username)).setText("用户名:" + map.get("commentor_name"));
            ((TextView)view.findViewById(R.id.time)).setText(map.get("time"));
            ((TextView)view.findViewById(R.id.content)).setText(map.get("comment"));
            return view;
        }

    }

}
