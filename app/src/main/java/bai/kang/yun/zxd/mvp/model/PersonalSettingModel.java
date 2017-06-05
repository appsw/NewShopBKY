package bai.kang.yun.zxd.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BaseModel;

import java.io.File;

import javax.inject.Inject;

import bai.kang.yun.zxd.mvp.contract.PersonalSettingContract;
import bai.kang.yun.zxd.mvp.model.api.cache.CacheManager;
import bai.kang.yun.zxd.mvp.model.api.service.ServiceManager;
import bai.kang.yun.zxd.mvp.model.entity.ReturnDeleteAdd;
import bai.kang.yun.zxd.mvp.model.entity.ReturnSetAdd;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
 * Created by Administrator on 2017/5/27 0027.
 */

@ActivityScope
public class PersonalSettingModel extends BaseModel<ServiceManager, CacheManager> implements PersonalSettingContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public PersonalSettingModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
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
    public Observable<ReturnDeleteAdd> ReSetPswd(int uid, String salt, String pswd) {
        Observable<ReturnDeleteAdd> detail = mServiceManager.getResetPswdService().set(uid,salt,pswd);

        return detail;
    }

    @Override
    public Observable<ReturnSetAdd> uploadFile(int uid, String salt, File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part MultipartFile =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);
        Observable<ReturnSetAdd> detail = mServiceManager.getSetUserHeadPicService().uploadFile(uid,salt,MultipartFile);

        return detail;
    }
}
