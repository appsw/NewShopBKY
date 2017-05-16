package bai.kang.yun.zxd.mvp.model;

import android.os.Handler;

import com.google.gson.JsonObject;
import com.jess.arms.mvp.BaseModel;

import bai.kang.yun.zxd.mvp.model.api.cache.CacheManager;
import bai.kang.yun.zxd.mvp.model.api.service.ServiceManager;
import bai.kang.yun.zxd.mvp.model.entity.Token;
import io.rx_cache.DynamicKey;
import io.rx_cache.Reply;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by Administrator on 2017/5/12 0012.
 */


public class TokenModel extends BaseModel<ServiceManager, CacheManager> {

    Handler handler;
    public TokenModel(ServiceManager serviceManager, CacheManager cacheManager,Handler handler) {
        super(serviceManager, cacheManager);
        this.handler=handler;
    }
    public void getToken(String scope) {
        JsonObject jsonObject=new JsonObject();
//        jsonObject.addProperty("pid","10");
        jsonObject.addProperty("client_id","app_baikangyun");
        jsonObject.addProperty("redirect_uri","");
        jsonObject.addProperty("scope",scope);
        jsonObject.addProperty("response_type","token");
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
//        Observable<Token> tokenObservable=mServiceManager
//                .getGetTokenService().getToken("app_baikangyun","",scope,"token");
        Observable<Token> tokenObservable=mServiceManager
                .getGetTokenService().getToken("","","","");
//        使用rxcache缓存
         mCacheManager.getCommonCache()
                .getToken(tokenObservable
                        , new DynamicKey(scope)
                )
                .flatMap(new Func1<Reply<Token>, Observable<Token>>() {
                    @Override
                    public Observable<Token> call(Reply<Token> listReply) {
                        return Observable.just(listReply.getData());
                    }
                })
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                 .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Token>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Token token) {
//                        Message message=new Message();
//                        message.obj=token.getAccess_token();
//                        handler.sendMessage(message);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
