package bai.kang.yun.zxd.di.module;

import com.jess.arms.di.scope.ActivityScope;

import bai.kang.yun.zxd.mvp.contract.FristContract;
import bai.kang.yun.zxd.mvp.model.FristModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
@Module
public class FristModule {

    private FristContract.View view;

    /**
     * 构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public FristModule(FristContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    FristContract.View provideFristView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    FristContract.Model provideFristModel(FristModel model){
        return model;
    }
}
