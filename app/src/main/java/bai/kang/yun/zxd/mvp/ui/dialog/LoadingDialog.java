package bai.kang.yun.zxd.mvp.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import bai.kang.yun.zxd.R;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class LoadingDialog {
    Dialog mLoadingDialog;
    AVLoadingIndicatorView avi;
    private Context context;
    private String msg;
    public LoadingDialog(Context context, String msg) {
        this.context=context;
        this.msg=msg;
        // 首先得到整个View
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_loading, null);
        // 获取整个布局
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);
        // 页面中的LoadingView
        avi = (AVLoadingIndicatorView) view.findViewById(R.id.avi);
        // 页面中显示文本
        TextView loadingText = (TextView) view.findViewById(R.id.loading_text);
        // 显示文本
        loadingText.setText(msg);
        // 创建自定义样式的Dialog
        mLoadingDialog = new Dialog(context, R.style.loading_dialog);
        // 设置返回键无效
//        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
    }
    public void show(){
        if(mLoadingDialog!=null){
            avi.show();
            mLoadingDialog.show();

        }

    }

    public void close(){
        if (mLoadingDialog!=null) {
            avi.hide();
            mLoadingDialog.dismiss();
        }
    }

}
