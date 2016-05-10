package com.github.alinz.reactnativewebviewbridge;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

class JavascriptBridge {
  private WebView webView;

  public JavascriptBridge(WebView webView) {
    this.webView = webView;
  }

  @JavascriptInterface
  public void send(String message) {
    WritableMap params = Arguments.createMap();
    params.putInt("viewId", webView.getId());
    params.putString("message", message);
    ReactContext context = (ReactContext) webView.getContext();
    context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit("webViewBridgeMessage", params);
  }
}
