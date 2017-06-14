package bai.kang.yun.zxd.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import bai.kang.yun.zxd.R;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public class WebViewActivity extends Activity {
    private WebView webView;
    private String url=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView= (WebView) findViewById(R.id.webview);
        url= getIntent().getStringExtra("url");
        if(url!=null)
            webView.loadUrl(url);

    }

}
