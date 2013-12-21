package me.biubiubiu.one;
import com.novoda.imageloader.core.ImageManager;
import com.novoda.imageloader.core.LoaderSettings.SettingsBuilder;
import com.novoda.imageloader.core.cache.LruBitmapCache;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.FROYO;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;

import com.github.kevinsawicki.http.HttpRequest;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;


/**
 * one application
 */
public class BootstrapApplication extends Application {

    private static BootstrapApplication instance;
    private static ImageManager mImageManager;
    public BMapManager mBMapManager = null;

    public static final String strKey = "RIfYQ1lQSuh5iz3uYKu6bWlb";


    /**
     * Create main application
     */
    public BootstrapApplication() {

        // Disable http.keepAlive on Froyo and below
        if (SDK_INT <= FROYO)
            HttpRequest.keepAlive(false);
    }

    /**
     * Create main application
     *
     * @param context
     */
    public BootstrapApplication(final Context context) {
        this();
        attachBaseContext(context);

    }

    public static ImageManager getImageLoader() {
        return mImageManager;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // Perform injection
        Injector.init(getRootModule(), this);
        mImageManager = new ImageManager(this, new SettingsBuilder()
                                         .withCacheManager(new LruBitmapCache(this))
                                         .build(this));
        initEngineManager(this);
    }

    public void initEngineManager(Context context) {
        if (mBMapManager == null) {
            mBMapManager = new BMapManager(context);
        }

        if (!mBMapManager.init(strKey,new MyGeneralListener())) {
            Toast.makeText(BootstrapApplication.getInstance().getApplicationContext(),
                           "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
        }
    }
     
    
    public static class MyGeneralListener implements MKGeneralListener {

        @Override
        public void onGetNetworkState(int iError) {
            if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
                Toast.makeText(BootstrapApplication.getInstance().getApplicationContext(), "您的网络出错啦！",
                               Toast.LENGTH_LONG).show();
            }
            else if (iError == MKEvent.ERROR_NETWORK_DATA) {
                Toast.makeText(BootstrapApplication.getInstance().getApplicationContext(), "输入正确的检索条件！",
                               Toast.LENGTH_LONG).show();
            }
            // ...
        }

        @Override
        public void onGetPermissionState(int iError) {
            //非零值表示key验证未通过
            if (iError != 0) {
                //授权Key错误：
                Toast.makeText(BootstrapApplication.getInstance().getApplicationContext(),
                               "请在 BootstrapApplication.java文件输入正确的授权Key,并检查您的网络连接是否正常！error: "+iError, Toast.LENGTH_LONG).show();
                // BootstrapApplication.getInstance().m_bKeyRight = false;
            }
            else{
                // BootstrapApplication.getInstance().m_bKeyRight = true;
                Toast.makeText(BootstrapApplication.getInstance().getApplicationContext(),
                               "key认证成功", Toast.LENGTH_LONG).show();
            }
        }
    }
    private Object getRootModule() {
        return new RootModule();
    }


    /**
     * Create main application
     *
     * @param instrumentation
     */
    public BootstrapApplication(final Instrumentation instrumentation) {
        this();
        attachBaseContext(instrumentation.getTargetContext());
    }

    public static BootstrapApplication getInstance() {
        return instance;
    }
}
