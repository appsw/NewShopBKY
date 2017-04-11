package bai.kang.yun.zxd.di.component;

import com.jess.arms.di.scope.ActivityScope;

import bai.kang.yun.zxd.di.module.MainModule;
import bai.kang.yun.zxd.mvp.ui.activity.MainActivity;
import common.AppComponent;
import dagger.Component;

/**
 * Created by jess on 9/4/16 11:17
 * Contact with jess.yan.effort@gmail.com
 */
@ActivityScope
@Component(modules = MainModule.class,dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
