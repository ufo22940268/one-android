package me.biubiubiu.one.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.biubiubiu.one.Injector;
import me.biubiubiu.one.R;
import me.biubiubiu.one.util.HttpHandler.ResponseHandler;
import me.biubiubiu.one.util.Parser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kanak.emptylayout.EmptyLayout;
//import com.markupartist.android.widget.PullToRefreshListView;

public class PageFragment extends BaseFragment {

    public ListView mListView;
    protected PageAdapter mAdapter;
    private EmptyLayout mEmptyLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup parent = (ViewGroup) inflater.inflate(R.layout.page_list,
                                                        container, false);
        Injector.inject(this);
        mListView = (ListView)parent.findViewById(R.id.list);
        mAdapter = new PageAdapter(getActivity());
        mEmptyLayout = new EmptyLayout(getActivity(), mListView);
        mListView.setAdapter(mAdapter);
        loadData();
        return parent;
    }

    private void loadData() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("lat", "12.2");
        map.put("lng", "2.2");
        mEmptyLayout.showLoading();
        mHttpHandler.get("rides", map, new ResponseHandler() {
                @Override
                public void onSuccess(String result) {
                    if (isFinished()) {
                        return;
                    }
                    List<Map<String, String>> list = Parser.items(result);
                    if (list != null && list.size() > 0) {
                        // Map<String, String> item = list.get(0);
                        // Log.d("debug", "--------------------" + item );
                        mAdapter.loadData(list);
                    }
                }
            });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
