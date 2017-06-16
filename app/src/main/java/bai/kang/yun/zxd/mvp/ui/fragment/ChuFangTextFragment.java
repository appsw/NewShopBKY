package bai.kang.yun.zxd.mvp.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.utils.UiUtils;

import org.json.JSONException;
import org.json.JSONObject;

import bai.kang.yun.zxd.R;
import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class ChuFangTextFragment extends BaseFragment {
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_age)
    EditText et_age;
    @BindView(R.id.et_sex)
    EditText et_sex;
    @BindView(R.id.et_yiyuan)
    EditText et_yiyuan;
    @BindView(R.id.et_kebie)
    EditText et_kebie;
    @BindView(R.id.et_yisheng)
    EditText et_yisheng;
    @BindView(R.id.et_date)
    EditText et_date;
    public static ChuFangTextFragment newInstance() {
        ChuFangTextFragment fragment = new ChuFangTextFragment();
        return fragment;
    }
    @Override
    protected void ComponentInject() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_chufangtext, container, false);
    }
    public JSONObject getData(){
        JSONObject jsonObject=new JSONObject();
        String name=et_name.getText().toString().trim();
        String age=et_age.getText().toString().trim();
        String sex=et_sex.getText().toString().trim();
        String yiyuan=et_yiyuan.getText().toString().trim();
        String kebie=et_kebie.getText().toString().trim();
        String yisheng=et_yisheng.getText().toString().trim();
        String date=et_date.getText().toString().trim();
        if(name.equals("")){
            UiUtils.makeText("请输入姓名！");
            return null;
        }else if(age.equals("")){
            UiUtils.makeText("请输入年龄！");
            return null;
        }else if(sex.equals("")){
            UiUtils.makeText("请输入性别！");
            return null;
        }else if(yiyuan.equals("")){
            UiUtils.makeText("请输入医院！");
            return null;
        }else if(kebie.equals("")){
            UiUtils.makeText("请输入科别！");
            return null;
        }else if(yisheng.equals("")){
            UiUtils.makeText("请输入医生！");
            return null;
        }else if(date.equals("")){
            UiUtils.makeText("请输入日期！");
            return null;
        }else {
            try {
                jsonObject.put("姓名",name).put("性别",sex).put("年龄",age)
                        .put("医院",yiyuan).put("医生",yisheng).put("科别",kebie)
                        .put("开具时间",date);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return jsonObject;
    }

    @Override
    protected void initData() {

    }
}
