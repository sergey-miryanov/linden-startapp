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
  }

  private static var _start : Dynamic = null;
  private static var _showInterstitial : Dynamic = null;
  private static var _showInterstitialBackButton : Dynamic = null;
  private static var _showSplashScreen : Dynamic = null;
}

#end
