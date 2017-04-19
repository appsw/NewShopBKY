package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jess.arms.utils.UiUtils;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.app.utils.MyGridView;
import bai.kang.yun.zxd.mvp.model.entity.SPCategory;


/**
 * Created by Administrator on 2016/10/12.
 */

public class FristListAdapter extends BaseAdapter {

    List<SPCategory> list;
    Context context;
    private LayoutInflater mInflater;


    public FristListAdapter(Context context, List<SPCategory> list){
        this.context=context;
        this.list=list;
        this.mInflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return list.size();
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
    public View getView(final int position, View convertView, final ViewGroup parent) {

            final ViewHolder viewHolder;
            if(convertView==null){
                convertView = mInflater.inflate(R.layout.item_textview,null);
                viewHolder=new ViewHolder();
                viewHolder.name=(TextView) convertView.findViewById(R.id.find_textview);
                viewHolder.gridView= (MyGridView) convertView.findViewById(R.id.find_category_grid);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
            }
                SPCategory spCategoryr=list.get(position);
                List<SPCategory> list=spCategoryr.getSubCategory();
                viewHolder.name.setText(spCategoryr.getName());
                GoodsCategoryGridAdapter goodsCategoryGridAdapter=new GoodsCategoryGridAdapter(list, UiUtils.getContext());
                viewHolder.gridView.setAdapter(goodsCategoryGridAdapter);
                viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent =new Intent(context, CategoryList.class);
//                        intent.putExtra("keywords",list.get(position).get("id")+"");
//                        ((Activity) parent.getContext()).startActivityForResult(intent,3);

                    }
                });
        return convertView;
    }
    private class ViewHolder{
        TextView name;
        MyGridView gridView;
    }

}
