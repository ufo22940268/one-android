package me.biubiubiu.one;

import android.accounts.AccountManager;
import android.content.Context;

import me.biubiubiu.one.authenticator.BootstrapAuthenticatorActivity;
import me.biubiubiu.one.authenticator.LogoutService;
import me.biubiubiu.one.core.CheckIn;
import me.biubiubiu.one.core.TimerService;
import me.biubiubiu.one.ui.BootstrapTimerActivity;
import me.biubiubiu.one.ui.CarouselActivity;
import me.biubiubiu.one.ui.CheckInsListFragment;
import me.biubiubiu.one.ui.ItemListFragment;
import me.biubiubiu.one.ui.NewsActivity;
import me.biubiubiu.one.ui.NewsListFragment;
import me.biubiubiu.one.ui.UserActivity;
import me.biubiubiu.one.ui.UserListFragment;
import me.biubiubiu.one.ui.*;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for setting up provides statements.
 * Register all of your entry points below.
 */
@Module
(
        complete = false,

        injects = {
                BootstrapApplication.class,
                BootstrapAuthenticatorActivity.class,
                BootstrapTimerActivity.class,
                CheckInsListFragment.class,
                CarouselActivity.class,
                NewsActivity.class,
                NewsListFragment.class,
                UserActivity.class,
                UserListFragment.class,
                TimerService.class,
                PostRideActivity.class
        }

)
public class BootstrapModule  {

    @Singleton
    @Provides
    Bus provideOttoBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    LogoutService provideLogoutService(final Context context, final AccountManager accountManager) {
        return new LogoutService(context, accountManager);
    }

}
