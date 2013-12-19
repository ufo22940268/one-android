package me.biubiubiu.one.ui;

import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.*;

import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;

import me.biubiubiu.one.BootstrapServiceProvider;
import me.biubiubiu.one.R;
import me.biubiubiu.one.core.BootstrapService;
import me.biubiubiu.one.util.SafeAsyncTask;

import com.viewpagerindicator.TitlePageIndicator;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.Views;
import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.MenuDrawer.OnDrawerStateChangeListener;


/**
 * Activity to view the carousel and view pager indicator with fragments.
 */
public class CarouselActivity extends BootstrapFragmentActivity implements OnDrawerStateChangeListener {

    @InjectView(R.id.grid) GridView menuLayout;
    @Inject BootstrapServiceProvider serviceProvider;
    static public final String[] TITLES = {
        "我要搭车",
        "看谁能带带我",
        "看看谁要搭车",
        "发布拼车服务",
        "我的信息",
    };

    static public final int[] IMAGE_RES = {
        R.drawable.icon01_03,
        R.drawable.icon02_03,
        R.drawable.icon03_03,
        R.drawable.icon04_03,
        R.drawable.icon05_07,
        R.drawable.icon06_07,
    };

    static public final int[] IMAGE_BACKGROUND = {
        R.color.menu_blue,
        R.color.menu_orange,
        R.color.menu_pink,
        R.color.menu_yellow,
        R.color.menu_green,
    };

    private MenuDrawer menuDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        super.onCreate(savedInstanceState);

        // Set up navigation drawer
        menuDrawer = MenuDrawer.attach(this);
        menuDrawer.setMenuView(R.layout.navigation_drawer);
        menuDrawer.setContentView(R.layout.carousel_view);
        menuDrawer.setSlideDrawable(R.drawable.ic_drawer);
        menuDrawer.setDrawerIndicatorEnabled(true);

        Views.inject(this);
        BlockAdapter adapter = new BlockAdapter(this, TITLES, IMAGE_RES, IMAGE_BACKGROUND);
        menuLayout.setAdapter(adapter);

        getSupportActionBar().setTitle("我要搭车");
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setNavListeners() {

        menuDrawer.findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuDrawer.toggleMenu();
                }
            });

        menuDrawer.findViewById(R.id.timer).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuDrawer.toggleMenu();
                    navigateToTimer();
                }
            });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case android.R.id.home:
            menuDrawer.toggleMenu();
            return true;
        case R.id.timer:
            navigateToTimer();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void navigateToTimer() {
        final Intent i = new Intent(this, BootstrapTimerActivity.class);
        startActivity(i);
    }

    @Override
    public void onDrawerSlide(float arg0, int arg1) {

    }

    @Override
    public void onDrawerStateChange(int arg0, int arg1) {
        getSupportActionBar().setTitle("closed");
    }
}
