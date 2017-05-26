package bai.kang.yun.zxd.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.AddressListContract;
import bai.kang.yun.zxd.mvp.model.api.cache.CacheManager;
import bai.kang.yun.zxd.mvp.model.api.service.ServiceManager;
import bai.kang.yun.zxd.mvp.model.entity.ReturnAddress;
import bai.kang.yun.zxd.mvp.model.entity.ReturnDeleteAdd;
import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import rx.Observable;
import rx.functions.Func1;

import static android.R.attr.id;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by Administrator on 2017/5/19 0019.
 */

@ActivityScope
public class AddressListModel extends BaseModel<ServiceManager, CacheManager> implements AddressListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public AddressListModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
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
    public Observable<ReturnAddress> getAddress(int uid, String salt, int page,boolean clean) {
        Observable<ReturnAddress> detail = mServiceManager.getGetAddressService().getAddress(uid,salt,page);

        return mCacheManager.getCommonCache()
                .getAddress(detail,new DynamicKey(id),new EvictDynamicKey(clean))
                .flatMap(new Func1<Reply<ReturnAddress>, Observable<ReturnAddress>>() {
                    @Override
                    public Observable<ReturnAddress> call(Reply<ReturnAddress> mapReply) {
                        return Observable.just(mapReply.getData());
                    }
                });
    }

    @Override
    public Observable<ReturnDeleteAdd> DeleteAdd(int uid, String salt, int id) {
        Observable<ReturnDeleteAdd> detail = mServiceManager.getAddressDeleteService().delete(uid,salt,id);

        return detail;
    }

    @Override
    public Observable<ReturnDeleteAdd> SetDefault(int uid, String salt, int id) {
        Observable<ReturnDeleteAdd> detail = mServiceManager.getSetDefaultAddService().set(uid,salt,id);

        return detail;
    }
}
