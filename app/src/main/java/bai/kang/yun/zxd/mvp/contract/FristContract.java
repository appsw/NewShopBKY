package bai.kang.yun.zxd.mvp.contract;

import android.widget.SimpleAdapter;

import com.jess.arms.mvp.BaseView;
import com.jess.arms.mvp.IModel;

import java.util.Map;

import bai.kang.yun.zxd.mvp.ui.adapter.RollViewpagerAdapter;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class FristContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    public interface View extends BaseView {
        void setAdapter(RollViewpagerAdapter adapter,SimpleAdapter simpleadapter);
    }
    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    public interface Model extends IModel {
        Observable<Map<String, String>> getBannerUrl();
    }
}
