package bai.kang.yun.zxd.di.module;


import javax.inject.Singleton;

import bai.kang.yun.zxd.mvp.model.api.service.CommonService;
import bai.kang.yun.zxd.mvp.model.api.service.UserService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by zhiyicx on 2016/3/30.
 */
@Module
public class ServiceModule {

    @Singleton
    @Provides
    CommonService provideCommonService(Retrofit retrofit) {
        return retrofit.create(CommonService.class);
    }

    @Singleton
    @Provides
    UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

}
