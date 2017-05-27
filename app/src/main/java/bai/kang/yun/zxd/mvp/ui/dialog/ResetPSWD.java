package bai.kang.yun.zxd.mvp.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import bai.kang.yun.zxd.R;

/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class ResetPSWD extends Dialog {
    private Context context;
    private ClickListenerInterface clickListenerInterface;
    private EditText pswd;
    private EditText newpswd;
    private EditText renewpswd;
    public ResetPSWD(@NonNull Context context) {
        super(context);
        this.context = context;
    }
    public interface ClickListenerInterface {
        public void doLeft();

        public void doRight(String ed1,String ed2,String ed3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_resetpswd);
        inite();
    }
    public void inite() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_resetpswd, null);
        setContentView(view);

        pswd = (EditText) view.findViewById(R.id.pswd);
        newpswd = (EditText) view.findViewById(R.id.new_pswd);
        renewpswd = (EditText) view.findViewById(R.id.renew_pswd);
        TextView tvLeft = (TextView) view.findViewById(R.id.tvBtnLeft);
        TextView tvRight = (TextView) view.findViewById(R.id.tvBtnRight);




        tvLeft.setOnClickListener(new clickListener());
        tvRight.setOnClickListener(new clickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics();

        lp.width = (int) (d.widthPixels * 0.8);
        dialogWindow.setAttributes(lp);
    }
    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {


        public void onClick(View v) {

            int id = v.getId();
            switch (id) {
                case R.id.tvBtnLeft:
                    clickListenerInterface.doLeft();
                    break;
                case R.id.tvBtnRight:
                    clickListenerInterface.doRight(pswd.getText().toString().trim(),
                            newpswd.getText().toString().trim(),
                            renewpswd.getText().toString().trim());
                    break;

                default:
                    break;
            }
        }
    }
}
