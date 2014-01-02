package ru.zzzzzzerg.linden;

#if android
import openfl.utils.JNI;

class StartApp
{
  public static function start(developerId : String, appId : String, showOnPause : Bool)
  {
    initJNI();

    _start(developerId, appId, showOnPause);
  }

  public static function showInterstitial()
  {
    initJNI();

    _showInterstitial();
  }

  public static function showInterstitialBackButton()
  {
    initJNI();

    _showInterstitialBackButton();
  }

  public static function showSplashScreen()
  {
    initJNI();

    _showSplashScreen();
  }

  public static function showBanner(gravity : Int)
  {
    initJNI();

    _showBanner(gravity);
  }

  public static function hideBanner()
  {
    initJNI();

    _hideBanner();
  }


  private static function initJNI()
  {
    var getMethod = JNI.createStaticMethod;
    if(_start == null)
    {
      _start = getMethod("ru/zzzzzzerg/linden/StartApp", "start", "(Ljava/lang/String;Ljava/lang/String;Z)V");
    }

    if(_showInterstitial == null)
    {
      _showInterstitial = getMethod("ru/zzzzzzerg/linden/StartApp", "showInterstitial", "()V");
    }

    if(_showInterstitialBackButton == null)
    {
      _showInterstitialBackButton = getMethod("ru/zzzzzzerg/linden/StartApp", "showInterstitialBackButton", "()V");
    }

    if(_showSplashScreen == null)
    {
      _showSplashScreen = getMethod("ru/zzzzzzerg/linden/StartApp", "showSplashScreen", "()V");
    }

    if(_showBanner == null)
    {
      _showBanner = getMethod("ru/zzzzzzerg/linden/StartApp", "showBanner", "(I)V");
    }

    if(_hideBanner == null)
    {
      _hideBanner = getMethod("ru/zzzzzzerg/linden/StartApp", "hideBanner", "()V");
    }

  }

  private static var _start : Dynamic = null;
  private static var _showInterstitial : Dynamic = null;
  private static var _showInterstitialBackButton : Dynamic = null;
  private static var _showSplashScreen : Dynamic = null;
  private static var _showBanner : Dynamic = null;
  private static var _hideBanner : Dynamic = null;
}

// see http://developer.android.com/reference/android/view/Gravity.html
class Gravity
{
  public static var AXIS_CLIP : Int = 8;
  public static var AXIS_PULL_AFTER : Int = 4;
  public static var AXIS_PULL_BEFORE : Int = 2;
  public static var AXIS_SPECIFIED : Int = 1;
  public static var AXIS_X_SHIFT : Int = 0;
  public static var AXIS_Y_SHIFT : Int = 4;
  public static var BOTTOM : Int = 80;
  public static var CENTER : Int = 17;
  public static var CENTER_HORIZONTAL : Int = 1;
  public static var CENTER_VERTICAL : Int = 16;
  public static var CLIP_HORIZONTAL : Int = 8;
  public static var CLIP_VERTICAL : Int = 128;
  public static var DISPLAY_CLIP_HORIZONTAL : Int = 16777216;
  public static var DISPLAY_CLIP_VERTICAL : Int = 268435456;
  public static var END : Int = 8388613;
  public static var FILL : Int = 119;
  public static var FILL_HORIZONTAL : Int = 7;
  public static var FILL_VERTICAL : Int = 112;
  public static var HORIZONTAL_GRAVITY_MASK : Int = 7;
  public static var LEFT : Int = 3;
  public static var NO_GRAVITY : Int = 0;
  public static var RELATIVE_HORIZONTAL_GRAVITY_MASK : Int = 8388615;
  public static var RELATIVE_LAYOUT_DIRECTION : Int = 8388608;
  public static var RIGHT : Int = 5;
  public static var START : Int = 8388611;
  public static var TOP : Int = 48;
  public static var VERTICAL_GRAVITY_MASK : Int = 112;
}

#end
