package bai.kang.yun.zxd.mvp.model.api.service;

import com.jess.arms.http.BaseServiceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jess on 8/5/16 13:01
 * contact with jess.yan.effort@gmail.com
 */
@Singleton
public class ServiceManager implements BaseServiceManager {
    private CommonService mCommonService;
    private UserService mUserService;
    private BannerService mBannerService;
    private GoodsListService mGoodsListService;
    private GoodsGridService mGoodsGridService;
    private CarListService mCarListService;
    private GetTokenService mGetTokenService;
    private GetADService mGetADService;
    private GetCategoryService mGetCategoryService;
    private GetCategoryGoodsService mGetCategoryGoodsService;
    private GetPhoneYzm mGetPhoneYzm;
    private RegisterService mRegisterService;
    private LoginService mLoginService;
    private GetShopListService mGetShopListService;
    private GetGoodsDetailService mGetGoodsDetailService;
    private GetAddressService mGetAddressService;
    private SetAddressService mSetAddressService;
    private GetRegionService mGetRegionService;
    private AddressDeleteService mAddressDeleteService;
    private SetDefaultAddService mSetDefaultAddService;
    private ResetPswdService mResetPswdService;
    private GetShopDetailService mGetShopDetailService;
    private GetShopGoodsService mGetShopGoodsService;
    private GetShopCategoryService mGetShopCategoryService;
    private GetShopCategoryGoodsService mGetShopCategoryGoodsService;
    private SetUserHeadPicService mSetUserHeadPicService;
    private GetOrderService mGetOrderService;
    private GetExpressListService mGetExpressListService;
    private MakeOrderService mMakeOrderService;
    private GetAlipayUrlService mGetAlipayUrlService;
    private SetImgChuFangService mSetImgChuFangService;
    private SetTextChuFangService mSetTextChuFangService;
    private DelectOrderService mDelectOrderService;
    private CancelOrderService mCancelOrderService;
    private GetDefaultAddService mGetDefaultAddService;
    private GetSearchService mGetSearchService;
    private OauthLogService mOauthLogService;
    /**
     * 如果需要添加service只需在构造方法中添加对应的service,在提供get方法返回出去,只要在ServiceModule提供了该service
     * Dagger2会自行注入
     * @param commonService
     */
    @Inject public ServiceManager(CommonService commonService,UserService userService,
                                  BannerService bannerService,GoodsListService goodsListService,
                                  GoodsGridService goodsGridService,CarListService carListService,
                                  GetTokenService getTokenService,GetADService getADService,
                                  GetCategoryService getCategoryService,GetCategoryGoodsService getCategoryGoodsService,
                                  GetPhoneYzm getPhoneYzm,RegisterService registerService,
                                  LoginService loginService,GetShopListService getShopListService,
                                  GetGoodsDetailService getGoodsDetailService,GetAddressService getAddressService,
                                  SetAddressService setAddressService,GetRegionService getRegionService,
                                  AddressDeleteService addressDeleteService,SetDefaultAddService setDefaultAddService,
                                  ResetPswdService resetPswdService,GetShopDetailService getShopDetailService,
                                  GetShopGoodsService getShopGoodsService,GetShopCategoryService getShopCategoryService,
                                  GetShopCategoryGoodsService getShopCategoryGoodsService,SetUserHeadPicService setUserHeadPicService,
                                  GetOrderService getOrderService,GetExpressListService getExpressListService,
                                  MakeOrderService makeOrderService,GetAlipayUrlService getAlipayUrlService,
                                  SetImgChuFangService setImgChuFangService,SetTextChuFangService setTextChuFangService,
                                  DelectOrderService delectOrderService,CancelOrderService cancelOrderService,
                                  GetDefaultAddService getDefaultAddService,GetSearchService getSearchService,
                                  OauthLogService oauthLogService){
        this.mCommonService = commonService;
        this.mUserService = userService;
        this.mBannerService=bannerService;
        this.mGoodsListService=goodsListService;
        this.mGoodsGridService=goodsGridService;
        this.mCarListService=carListService;
        this.mGetTokenService=getTokenService;
        this.mGetADService=getADService;
        this.mGetCategoryService=getCategoryService;
        this.mGetCategoryGoodsService=getCategoryGoodsService;
        this.mGetPhoneYzm=getPhoneYzm;
        this.mRegisterService=registerService;
        this.mLoginService=loginService;
        this.mGetShopListService=getShopListService;
        this.mGetGoodsDetailService=getGoodsDetailService;
        this.mGetAddressService=getAddressService;
        this.mSetAddressService=setAddressService;
        this.mGetRegionService=getRegionService;
        this.mAddressDeleteService=addressDeleteService;
        this.mSetDefaultAddService=setDefaultAddService;
        this.mResetPswdService=resetPswdService;
        this.mGetShopDetailService=getShopDetailService;
        this.mGetShopGoodsService=getShopGoodsService;
        this.mGetShopCategoryService=getShopCategoryService;
        this.mGetShopCategoryGoodsService=getShopCategoryGoodsService;
        this.mSetUserHeadPicService=setUserHeadPicService;
        this.mGetOrderService=getOrderService;
        this.mGetExpressListService=getExpressListService;
        this.mMakeOrderService=makeOrderService;
        this.mGetAlipayUrlService=getAlipayUrlService;
        this.mSetImgChuFangService=setImgChuFangService;
        this.mSetTextChuFangService=setTextChuFangService;
        this.mDelectOrderService=delectOrderService;
        this.mCancelOrderService=cancelOrderService;
        this.mGetDefaultAddService=getDefaultAddService;
        this.mGetSearchService=getSearchService;
        this.mOauthLogService=oauthLogService;
    }

    public CommonService getCommonService() {
        return mCommonService;
    }

    public UserService getUserService() {
        return mUserService;
    }
    public BannerService getBannerService(){
        return mBannerService;
    }
    public GoodsListService getGoodsListService(){return mGoodsListService;}
    public GoodsGridService getGoodsGridService(){return mGoodsGridService;}
    public CarListService getCarListService(){return mCarListService;}
    public GetTokenService getGetTokenService(){return mGetTokenService;}
    public GetADService getGetADService(){return mGetADService;}
    public GetCategoryService getGetCategoryService(){return mGetCategoryService;}
    public GetCategoryGoodsService getCategoryGoodsService(){return mGetCategoryGoodsService;}
    public GetPhoneYzm getGetPhoneYzm(){return mGetPhoneYzm;}
    public RegisterService getRegisterService(){return mRegisterService;}
    public LoginService getLoginService(){return mLoginService;}
    public GetShopListService getShopListService(){return mGetShopListService;}
    public GetGoodsDetailService getGetGoodsDetailService(){return mGetGoodsDetailService;}
    public GetAddressService getGetAddressService(){return mGetAddressService;}
    public SetAddressService getSetAddressService(){return mSetAddressService;}
    public GetRegionService getGetRegionService(){return mGetRegionService;}
    public AddressDeleteService getAddressDeleteService(){return mAddressDeleteService;}
    public SetDefaultAddService getSetDefaultAddService(){return mSetDefaultAddService;}
    public ResetPswdService getResetPswdService(){return mResetPswdService;}
    public GetShopDetailService getGetShopDetailService(){return mGetShopDetailService;}
    public GetShopGoodsService getGetShopGoodsService(){return mGetShopGoodsService;}
    public GetShopCategoryService getGetShopCategoryService(){return mGetShopCategoryService;}
    public GetShopCategoryGoodsService getGetShopCategoryGoodsService(){return mGetShopCategoryGoodsService;}
    public SetUserHeadPicService getSetUserHeadPicService(){return mSetUserHeadPicService;}
    public GetOrderService getGetOrderService(){return  mGetOrderService;}
    public GetExpressListService getExpressListService(){return mGetExpressListService;}
    public MakeOrderService getMakeOrderService(){return mMakeOrderService;}
    public GetAlipayUrlService getGetAlipayUrlService(){return mGetAlipayUrlService;}
    public SetImgChuFangService getSetImgChuFangService(){return mSetImgChuFangService;};
    public SetTextChuFangService getSetTextChuFangService(){return mSetTextChuFangService;};
    public DelectOrderService getDelectOrderService(){return mDelectOrderService;}
    public CancelOrderService getCancelOrderService(){return mCancelOrderService;}
    public GetDefaultAddService getGetDefaultAddService(){return mGetDefaultAddService;}
    public GetSearchService getGetSearchService(){return mGetSearchService;}
    public OauthLogService getOauthLogService(){return mOauthLogService;}

    /**
     * 这里可以释放一些资源(注意这里是单例，即不需要在activity的生命周期调用)
     */
    @Override
    public void onDestroy() {

    }
}
