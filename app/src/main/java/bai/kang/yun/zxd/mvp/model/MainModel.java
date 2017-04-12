package bai.kang.yun.zxd.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.MainContract;
import bai.kang.yun.zxd.mvp.model.api.cache.CacheManager;
import bai.kang.yun.zxd.mvp.model.api.service.ServiceManager;

/**
 * Created by jess on 9/4/16 10:56
 * Contact with jess.yan.effort@gmail.com
 */
@ActivityScope
public class MainModel extends BaseModel<ServiceManager, CacheManager> implements MainContract.Model {

    @Inject
    public MainModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }

}
