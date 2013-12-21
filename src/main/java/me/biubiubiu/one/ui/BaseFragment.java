package me.biubiubiu.one.ui;

import me.biubiubiu.one.BootstrapApplication;
import me.biubiubiu.one.R;
import me.biubiubiu.one.util.HttpHandler;

import android.os.Bundle;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragment;
import com.novoda.imageloader.core.ImageManager;
import com.novoda.imageloader.core.model.ImageTagFactory;

public class BaseFragment extends SherlockFragment {

    protected HttpHandler mHttpHandler;
    protected ImageTagFactory imageTagFactory;
    protected ImageManager imageManager;

    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        mHttpHandler = new HttpHandler(getActivity());
        imageManager = BootstrapApplication.getImageLoader();
        imageTagFactory = ImageTagFactory.newInstance(getActivity(), R.drawable.transparent);
        imageTagFactory.setAnimation(R.anim.fade_in);
        imageTagFactory.setSaveThumbnail(true);

    }

    public boolean isFinished() {
        return getActivity() == null;
    }

    public void loadPhoto(ImageView view, String url) {
        view.setTag(imageTagFactory.build(url, getActivity()));
        imageManager.getLoader().load(view);
    }

    protected void setActionBarTitle(String title) {
        ((BaseActivity)getActivity()).getSupportActionBar().setTitle(title);
    }

}
