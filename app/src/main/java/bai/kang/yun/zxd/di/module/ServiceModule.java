package bai.kang.yun.zxd.di.module;


import javax.inject.Singleton;

import bai.kang.yun.zxd.mvp.model.api.service.AddressDeleteService;
import bai.kang.yun.zxd.mvp.model.api.service.BannerService;
import bai.kang.yun.zxd.mvp.model.api.service.CancelOrderService;
import bai.kang.yun.zxd.mvp.model.api.service.CarListService;
import bai.kang.yun.zxd.mvp.model.api.service.CommonService;
import bai.kang.yun.zxd.mvp.model.api.service.DelectOrderService;
import bai.kang.yun.zxd.mvp.model.api.service.GetADService;
import bai.kang.yun.zxd.mvp.model.api.service.GetAddressService;
import bai.kang.yun.zxd.mvp.model.api.service.GetAlipayUrlService;
import bai.kang.yun.zxd.mvp.model.api.service.GetCategoryGoodsService;
import bai.kang.yun.zxd.mvp.model.api.service.GetCategoryService;
import bai.kang.yun.zxd.mvp.model.api.service.GetDefaultAddService;
import bai.kang.yun.zxd.mvp.model.api.service.GetExpressListService;
import bai.kang.yun.zxd.mvp.model.api.service.GetGoodsDetailService;
import bai.kang.yun.zxd.mvp.model.api.service.GetOrderService;
import bai.kang.yun.zxd.mvp.model.api.service.GetPhoneYzm;
import bai.kang.yun.zxd.mvp.model.api.service.GetRegionService;
import bai.kang.yun.zxd.mvp.model.api.service.GetSearchService;
import bai.kang.yun.zxd.mvp.model.api.service.GetShopCategoryGoodsService;
import bai.kang.yun.zxd.mvp.model.api.service.GetShopCategoryService;
import bai.kang.yun.zxd.mvp.model.api.service.GetShopDetailService;
import bai.kang.yun.zxd.mvp.model.api.service.GetShopGoodsService;
import bai.kang.yun.zxd.mvp.model.api.service.GetShopListService;
import bai.kang.yun.zxd.mvp.model.api.service.GetTokenService;
import bai.kang.yun.zxd.mvp.model.api.service.GoodsGridService;
import bai.kang.yun.zxd.mvp.model.api.service.GoodsListService;
import bai.kang.yun.zxd.mvp.model.api.service.LoginService;
import bai.kang.yun.zxd.mvp.model.api.service.MakeOrderService;
import bai.kang.yun.zxd.mvp.model.api.service.RegisterService;
import bai.kang.yun.zxd.mvp.model.api.service.ResetPswdService;
import bai.kang.yun.zxd.mvp.model.api.service.SetAddressService;
import bai.kang.yun.zxd.mvp.model.api.service.SetDefaultAddService;
import bai.kang.yun.zxd.mvp.model.api.service.SetImgChuFangService;
import bai.kang.yun.zxd.mvp.model.api.service.SetTextChuFangService;
import bai.kang.yun.zxd.mvp.model.api.service.SetUserHeadPicService;
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
    @Singleton
    @Provides
    GetADService provideGetADService(Retrofit retrofit) {
        return retrofit.create(GetADService.class);
    }
    @Singleton
    @Provides
    GetCategoryService provideGetCategoryService(Retrofit retrofit) {
        return retrofit.create(GetCategoryService.class);
    }
    @Singleton
    @Provides
    GetCategoryGoodsService provideGetCategoryGoodsService(Retrofit retrofit) {
        return retrofit.create(GetCategoryGoodsService.class);
    }
    @Singleton
    @Provides
    GetPhoneYzm provideGetPhoneYzm(Retrofit retrofit) {
        return retrofit.create(GetPhoneYzm.class);
    }
    @Singleton
    @Provides
    RegisterService provideRegisterService(Retrofit retrofit) {
        return retrofit.create(RegisterService.class);
    }
    @Singleton
    @Provides
    LoginService provideLoginService(Retrofit retrofit) {
        return retrofit.create(LoginService.class);
    }
    @Singleton
    @Provides
    GetShopListService provideGetShopListService(Retrofit retrofit) {
        return retrofit.create(GetShopListService.class);
    }
    @Singleton
    @Provides
    GetGoodsDetailService provideGetGoodsDetailService(Retrofit retrofit) {
        return retrofit.create(GetGoodsDetailService.class);
    }
    @Singleton
    @Provides
    GetAddressService provideGetAddressService(Retrofit retrofit) {
        return retrofit.create(GetAddressService.class);
    }
    @Singleton
    @Provides
    SetAddressService provideSetAddressService(Retrofit retrofit) {
        return retrofit.create(SetAddressService.class);
    }
    @Singleton
    @Provides
    GetRegionService provideGetRegionService(Retrofit retrofit) {
        return retrofit.create(GetRegionService.class);
    }
    @Singleton
    @Provides
    AddressDeleteService provideAddressDeleteService(Retrofit retrofit) {
        return retrofit.create(AddressDeleteService.class);
    }
    @Singleton
    @Provides
    SetDefaultAddService provideSetDefaultAddService(Retrofit retrofit) {
        return retrofit.create(SetDefaultAddService.class);
    }
    @Singleton
    @Provides
    ResetPswdService provideResetPswdService(Retrofit retrofit) {
        return retrofit.create(ResetPswdService.class);
    }
    @Singleton
    @Provides
    GetShopDetailService provideGetShopDetailService(Retrofit retrofit) {
        return retrofit.create(GetShopDetailService.class);
    }
    @Singleton
    @Provides
    GetShopGoodsService provideGetShopGoodsService(Retrofit retrofit) {
        return retrofit.create(GetShopGoodsService.class);
    }
    @Singleton
    @Provides
    GetShopCategoryService provideGetShopCategoryService(Retrofit retrofit) {
        return retrofit.create(GetShopCategoryService.class);
    }
    @Singleton
    @Provides
    GetShopCategoryGoodsService provideGetShopCategoryGoodsService(Retrofit retrofit) {
        return retrofit.create(GetShopCategoryGoodsService.class);
    }
    @Singleton
    @Provides
    SetUserHeadPicService provideSetUserHeadPicService(Retrofit retrofit) {
        return retrofit.create(SetUserHeadPicService.class);
    }
    @Singleton
    @Provides
    GetOrderService provideGetOrderService(Retrofit retrofit) {
        return retrofit.create(GetOrderService.class);
    }
    @Singleton
    @Provides
    GetExpressListService provideGetExpressListService(Retrofit retrofit) {
        return retrofit.create(GetExpressListService.class);
    }
    @Singleton
    @Provides
    MakeOrderService provideMakeOrderService(Retrofit retrofit) {
        return retrofit.create(MakeOrderService.class);
    }
    @Singleton
    @Provides
    GetAlipayUrlService provideGetAlipayUrlService(Retrofit retrofit) {
        return retrofit.create(GetAlipayUrlService.class);
    }
    @Singleton
    @Provides
    SetImgChuFangService provideSetImgChuFangService(Retrofit retrofit) {
        return retrofit.create(SetImgChuFangService.class);
    }
    @Singleton
    @Provides
    SetTextChuFangService provideSetTextChuFangService(Retrofit retrofit) {
        return retrofit.create(SetTextChuFangService.class);
    }
    @Singleton
    @Provides
    DelectOrderService provideDelectOrderService(Retrofit retrofit) {
        return retrofit.create(DelectOrderService.class);
    }
    @Singleton
    @Provides
    CancelOrderService provideCancelOrderService(Retrofit retrofit) {
        return retrofit.create(CancelOrderService.class);
    }
    @Singleton
    @Provides
    GetDefaultAddService provideGetDefaultAddService(Retrofit retrofit) {
        return retrofit.create(GetDefaultAddService.class);
    }
    @Singleton
    @Provides
    GetSearchService provideGetSearchService(Retrofit retrofit) {
        return retrofit.create(GetSearchService.class);
    }


}
