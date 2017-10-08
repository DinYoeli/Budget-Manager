package com.dmd.budgetmanager;

import android.webkit.WebView;
import android.webkit.WebViewClient;
/**
 * Created by Daniel on 13/09/2017.
 */

public class WebViewController  extends WebViewClient{
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
