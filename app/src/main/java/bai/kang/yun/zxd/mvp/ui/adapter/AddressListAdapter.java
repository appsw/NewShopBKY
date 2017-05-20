package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.Address;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class AddressListAdapter extends BaseAdapter {

    Context context;
    List<Address> addresses;
    private LayoutInflater mInflater;
    public AddressListAdapter(Context context, List<Address> list){
        this.context=context;
        this.addresses=list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return addresses.size();
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
            convertView = mInflater.inflate(R.layout.item_address,null);
            viewHolder=new ViewHolder();
            viewHolder.name=(TextView) convertView.findViewById(R.id.address_consignee_txtv);
            viewHolder.phone_number=(TextView) convertView.findViewById(R.id.address_mobile_txtv);
            viewHolder.address=(TextView) convertView.findViewById(R.id.address_detail_txtv);
            viewHolder.btn_chose= ((Button) convertView.findViewById(R.id.address_setdefault_btn));
            viewHolder.btn_edit= ((Button) convertView.findViewById(R.id.address_edit_btn));
            viewHolder.btn_delect= ((Button) convertView.findViewById(R.id.address_delete_txtv));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
//        Address address=addresses.get(position);
//        viewHolder.name.setText(address.getName());
//        viewHolder.phone_number.setText(address.getNumber_phone());
//        viewHolder.address.setText(address.getAdd_deils());
//        if(address.is())
//            viewHolder.btn_chose.setBackgroundResource(R.drawable.icon_checked);
//        else
//            viewHolder.btn_chose.setBackgroundResource(R.drawable.icon_checkno);

        return convertView;
    }
    private class ViewHolder{
        TextView name;
        TextView phone_number;
        TextView address;
        Button btn_chose;
        Button btn_edit;
        Button btn_delect;
    }
}
