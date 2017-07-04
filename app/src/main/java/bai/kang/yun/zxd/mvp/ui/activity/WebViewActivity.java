package bai.kang.yun.zxd.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
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

        WebSettings webSettings =   webView .getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // TODO Auto-generated method stub
//                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
//                view.loadUrl(url);
//                return true;
//            }
//        });
    }

}
