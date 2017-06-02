package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.app.utils.Transfer;
import bai.kang.yun.zxd.mvp.model.entity.ReturnShopCategory;
import bai.kang.yun.zxd.mvp.ui.activity.GoodsListActivity;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class ShopCategoryAdapter extends BaseAdapter {
    private List<ReturnShopCategory.DataEntity> CategoryList;
    Context context;
    private LayoutInflater mInflater;
    public ShopCategoryAdapter(Context context,List<ReturnShopCategory.DataEntity> CategoryList){
        this.context=context;
        this.CategoryList=CategoryList;
        this.mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return CategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.item_shopcategory,null);
            viewHolder=new ViewHolder();
            viewHolder.name=(TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
        viewHolder.name.setText(CategoryList.get(position).getName());
//        Observable.just(CategoryList.get(position).getName())
//                .subscribe(RxTextView.text(viewHolder.name));
        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transfer.chosegoods_for_open_shoplist_type=Transfer.SHOP_CATEGORY;
                Transfer.choseshopcategory_open_goods_id=CategoryList.get(position).getId();
                Intent intent=new Intent(context, GoodsListActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    private class ViewHolder{
        TextView name;

    }
}
