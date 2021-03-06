package bai.kang.yun.zxd.mvp.contract;

import com.jess.arms.mvp.BaseView;
import com.jess.arms.mvp.IModel;

/**
 * Created by jess on 9/4/16 10:47
 * Contact with jess.yan.effort@gmail.com
 */
public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView {
        void initTab();

    }
    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel{

    }
}
