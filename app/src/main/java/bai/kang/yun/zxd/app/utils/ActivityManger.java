package bai.kang.yun.zxd.app.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class ActivityManger {
    public static List<Activity> activities=new ArrayList<>();
    public static Activity getAvtivity(){
        return activities.get(activities.size()-1);
    }
    public static void addAvtivity(Activity activity){
        activities.add(activity);
    }
    public static void removeAvtivity(Activity activity){
        activities.remove(activity);
    }
}
