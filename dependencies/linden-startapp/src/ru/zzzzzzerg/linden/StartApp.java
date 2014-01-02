package ru.zzzzzzerg.linden;

import android.content.Context;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Map;
import java.util.HashMap;

import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.splash.SplashConfig;
import com.searchboxsdk.android.StartAppSearch;
import com.startapp.android.publish.banner.Banner;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.view.ViewGroup;
import android.view.Gravity;

import org.haxe.extension.Extension;

public class StartApp extends Extension
{
  private static boolean _showOnPause;
  private static StartAppAd _startAppInstance;
  private static Bundle _saveInstanceState;
  private static Banner _banner;
  private static FrameLayout _frame;
  private static LayoutParams _frameParams;

  private static String tag = "LindenStartApp";

  public StartApp()
  {
    Log.d(tag, "Construct LindenStartApp");
    _showOnPause = false;
    _startAppInstance = null;
    _saveInstanceState = null;
  }

  /**
   * Called when an activity you launched exits, giving you the requestCode
   * you started it with, the resultCode it returned, and any additional data
   * from it.
   */
  public boolean onActivityResult (int requestCode, int resultCode, Intent data)
  {
    return true;
  }


  /**
   * Called when the activity is starting.
   */
  public void onCreate(Bundle savedInstanceState)
  {
    _saveInstanceState = savedInstanceState;
    _frame = new FrameLayout(mainActivity);
    _frameParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    _frameParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

    _banner = new Banner(mainActivity);
    _banner.setLayoutParams(_frameParams);

    _frame.addView(_banner);
  }


  /**
   * Perform any final cleanup before an activity is destroyed.
   */
  public void onDestroy()
  {
  }


  /**
   * Called as part of the activity lifecycle when an activity is going into
   * the background, but has not (yet) been killed.
   */
  public void onPause()
  {
    if(_startAppInstance != null && _showOnPause)
    {
      _startAppInstance.onPause();
    }
  }


  /**
   * Called after {@link #onStop} when the current activity is being
   * re-displayed to the user (the user has navigated back to it).
   */
  public void onRestart()
  {
  }


  /**
   * Called after {@link #onRestart}, or {@link #onPause}, for your activity
   * to start interacting with the user.
   */
  public void onResume()
  {
    if(_startAppInstance != null)
    {
      _startAppInstance.onResume();
    }
  }


  /**
   * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when
   * the activity had been stopped, but is now again being displayed to the
   * user.
   */
  public void onStart()
  {
  }


  /**
   * Called when the activity is no longer visible to the user, because
   * another activity has been resumed and is covering this one.
   */
  public void onStop()
  {
  }

  public static void start(String developerId, String appId, boolean showOnPause)
  {
    if(_startAppInstance != null)
    {
      Log.e(tag, "Already started");
      return ;
    }

    Log.d(tag, "Starting");

    StartAppAd.init(mainActivity, developerId, appId);
    StartAppSearch.init(mainActivity, developerId, appId);

    _startAppInstance = new StartAppAd(mainActivity);
    _showOnPause = showOnPause;

    Log.d(tag, "Started");
  }

  public static void showInterstitial()
  {
    if(_startAppInstance == null)
    {
      Log.e(tag, "Not started");
      return ;
    }

    callbackHandler.post(new Runnable()
        {
          public void run()
          {
            Log.d(tag, "Showing Interstitial");
            _startAppInstance.showAd();
            _startAppInstance.loadAd();
            Log.d(tag, "Showed Interstitial");
          }
        });

  }

  public static void showInterstitialBackButton()
  {
    if(_startAppInstance == null)
    {
      Log.e(tag, "Not started");
      return ;
    }

    callbackHandler.post(new Runnable()
        {
          public void run()
          {
            Log.d(tag, "Showing Interstitial for Back button");
            _startAppInstance.onBackPressed();
            Log.d(tag, "Showed Interstitial for Back button");
          }
        });
  }

  public static void showSplashScreen()
  {
    callbackHandler.post(new Runnable()
        {
          public void run()
          {
            Log.d(tag, "Showing splash");
            StartAppAd.showSplash(mainActivity, _saveInstanceState,
                new SplashConfig()
                  .setTheme(SplashConfig.Theme.OCEAN)
                  .setAppName(tag)
                  );
            Log.d(tag, "Showed splash");
          }
        });
  }

  public static boolean showBanner(final int gravity)
  {
    if(_frame.getParent() == null)
    {
      callbackHandler.post(new Runnable()
          {
            public void run()
            {
              Log.d(tag, "Showing banner");
              _frameParams.gravity = gravity;
              ViewGroup g = (ViewGroup)mainView.getParent();
              g.addView(_frame);
              Log.d(tag, "Shown banner at " + g);
            }
          });
      return true;
    }
    else
    {
      Log.e(tag, "Banner alreay shown");
      return false;
    }
  }

  public static boolean hideBanner()
  {
    if(_frame.getParent() != null)
    {
      callbackHandler.post(new Runnable()
          {
            public void run()
            {
              Log.d(tag, "Removing banner");
              ViewGroup g = (ViewGroup)mainView.getParent();
              g.removeView(_frame);
              Log.d(tag, "Removed banner");
            }
          });
      return true;
    }
    else
    {
      Log.e(tag, "Banner not shown yet");
      return false;
    }
  }

}
