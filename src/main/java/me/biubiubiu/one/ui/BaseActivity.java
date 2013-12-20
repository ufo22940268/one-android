package me.biubiubiu.one.ui;

import me.biubiubiu.one.BootstrapApplication;
import me.biubiubiu.one.R;
import me.biubiubiu.one.util.HttpHandler;

import android.os.Bundle;
import android.widget.ImageView;

import com.actionbarsherlock.view.MenuItem;
import com.novoda.imageloader.core.ImageManager;
import com.novoda.imageloader.core.model.ImageTagFactory;

public class BaseActivity extends BootstrapFragmentActivity {

    protected HttpHandler mHttpHandler;
    protected ImageTagFactory imageTagFactory;
    protected ImageManager imageManager;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        mHttpHandler = new HttpHandler(this);
        imageManager = BootstrapApplication.getImageLoader();
        imageTagFactory = ImageTagFactory.newInstance(this, R.drawable.transparent);
        imageTagFactory.setAnimation(R.anim.fade_in);
        imageTagFactory.setSaveThumbnail(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isFinished() {
        return this == null;
    }

    public void loadPhoto(ImageView view, String url) {
        view.setTag(imageTagFactory.build(url, this));
        imageManager.getLoader().load(view);
    }
}
