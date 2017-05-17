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
                                  GetPhoneYzm getPhoneYzm,RegisterService registerService){
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
    /**
     * 这里可以释放一些资源(注意这里是单例，即不需要在activity的生命周期调用)
     */
    @Override
    public void onDestroy() {

    }
}
