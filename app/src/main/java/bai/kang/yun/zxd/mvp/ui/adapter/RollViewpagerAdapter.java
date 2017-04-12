package bai.kang.yun.zxd.mvp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class RollViewpagerAdapter extends StaticPagerAdapter {
    List<Map<String,String>> list;
    public RollViewpagerAdapter(List<Map<String,String>> list){
        this.list=list;

    }
    @Override
    public View getView(ViewGroup container, int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
