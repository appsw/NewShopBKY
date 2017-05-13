package bai.kang.yun.zxd.di.module;


import javax.inject.Singleton;

import bai.kang.yun.zxd.mvp.model.api.service.BannerService;
import bai.kang.yun.zxd.mvp.model.api.service.CarListService;
import bai.kang.yun.zxd.mvp.model.api.service.CommonService;
import bai.kang.yun.zxd.mvp.model.api.service.GetTokenService;
import bai.kang.yun.zxd.mvp.model.api.service.GoodsGridService;
import bai.kang.yun.zxd.mvp.model.api.service.GoodsListService;
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

    @Singleton
    @Provides
    BannerService provideBannersService(Retrofit retrofit) {
        return retrofit.create(BannerService.class);
    }
    @Singleton
    @Provides
    GoodsListService provideGoodsListService(Retrofit retrofit) {
        return retrofit.create(GoodsListService.class);
    }
    @Singleton
    @Provides
    GoodsGridService provideGoodsGridService(Retrofit retrofit) {
        return retrofit.create(GoodsGridService.class);
    }
    @Singleton
    @Provides
    CarListService provideCarListService(Retrofit retrofit) {
        return retrofit.create(CarListService.class);
    }
    @Singleton
    @Provides
    GetTokenService provideGetTokenService(Retrofit retrofit) {
        return retrofit.create(GetTokenService.class);
    }
}
