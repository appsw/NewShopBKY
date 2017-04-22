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

    /**
     * 如果需要添加service只需在构造方法中添加对应的service,在提供get方法返回出去,只要在ServiceModule提供了该service
     * Dagger2会自行注入
     * @param commonService
     */
    @Inject public ServiceManager(CommonService commonService,UserService userService,
                                  BannerService bannerService,GoodsListService goodsListService,
                                  GoodsGridService goodsGridService,CarListService carListService){
        this.mCommonService = commonService;
        this.mUserService = userService;
        this.mBannerService=bannerService;
        this.mGoodsListService=goodsListService;
        this.mGoodsGridService=goodsGridService;
        this.mCarListService=carListService;
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
    /**
     * 这里可以释放一些资源(注意这里是单例，即不需要在activity的生命周期调用)
     */
    @Override
    public void onDestroy() {

    }
}
