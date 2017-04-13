package bai.kang.yun.zxd.di.component;

import com.jess.arms.di.scope.ActivityScope;

import bai.kang.yun.zxd.di.module.FristModule;
import bai.kang.yun.zxd.mvp.ui.fragment.FristFragment;
import common.AppComponent;
import dagger.Component;

/**
 * Created by jess on 9/4/16 11:17
 * Contact with jess.yan.effort@gmail.com
 */
@ActivityScope
@Component(modules = FristModule.class,dependencies = AppComponent.class)
public interface FristComponent {
//    void inject(FristContract.View context);
      void inject(FristFragment fragment);
}
