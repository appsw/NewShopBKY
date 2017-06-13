package bai.kang.yun.zxd.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import bai.kang.yun.zxd.app.utils.ShoppingCartBiz;
import bai.kang.yun.zxd.mvp.contract.MakeOrderContract;
import bai.kang.yun.zxd.mvp.model.api.cache.CacheManager;
import bai.kang.yun.zxd.mvp.model.api.service.ServiceManager;
import bai.kang.yun.zxd.mvp.model.entity.CarShop;
import bai.kang.yun.zxd.mvp.model.entity.ReturenExpress;
import bai.kang.yun.zxd.mvp.model.entity.ReturnMakeOrder;
import rx.Observable;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by Administrator on 2017/6/7 0007.
 */

@ActivityScope
public class MakeOrderModel extends BaseModel<ServiceManager, CacheManager> implements MakeOrderContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MakeOrderModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
        super(serviceManager, cacheManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<List<CarShop>> SelectGoodsList() {
        List<CarShop> carShops= ShoppingCartBiz.getAllSelectedGoods();
        return Observable.just(carShops);
    }

    @Override
    public Observable<ReturenExpress> GetShopExpress(int id, String salt,int storeId,
                                                     int weight,int deliver_id) {
        Observable<ReturenExpress> observable=mServiceManager.getExpressListService().getExpress(id,salt,storeId,weight,deliver_id);
        return observable;
    }

    @Override
    public Observable<ReturnMakeOrder> MakeOrder(int id, String salt, String orderInfo) {
        Observable<ReturnMakeOrder> observable=mServiceManager.getMakeOrderService().makeorder(id,salt,orderInfo);
        return observable;
    }
}
