package bai.kang.yun.zxd.di.module;

import com.jess.arms.di.scope.ActivityScope;

import bai.kang.yun.zxd.mvp.contract.UserContract;
import bai.kang.yun.zxd.mvp.model.UserModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jess on 9/4/16 11:10
 * Contact with jess.yan.effort@gmail.com
 */
@Module
public class UserModule {
    private UserContract.View view;

    /**
     * 构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public UserModule(UserContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UserContract.View provideUserView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    UserContract.Model provideUserModel(UserModel model){
        return model;
    }
}
