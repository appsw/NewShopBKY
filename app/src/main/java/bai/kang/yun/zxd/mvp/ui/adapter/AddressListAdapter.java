package bai.kang.yun.zxd.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import bai.kang.yun.zxd.R;
import bai.kang.yun.zxd.mvp.model.entity.ReturnAddress;
import bai.kang.yun.zxd.mvp.ui.Listener.AddEditListener;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class AddressListAdapter extends BaseAdapter {
    AddEditListener addEditListener;
    Context context;
    List<ReturnAddress.ItemsEntity> addresses;
    private LayoutInflater mInflater;
    public AddressListAdapter(Context context, List<ReturnAddress.ItemsEntity> list){
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
            viewHolder.btn_delect= ((Button) convertView.findViewById(R.id.address_delete_btn));
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
        ReturnAddress.ItemsEntity address=addresses.get(position);
        viewHolder.name.setText(address.getReal_name());
        viewHolder.phone_number.setText(address.getPhone());
        viewHolder.address.setText(address.getAddress());
        viewHolder.btn_edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditListener.edit(position);
            }
        });
        viewHolder.btn_delect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditListener.delect(position);
            }
        });
        if(address.isdefault())
            viewHolder.btn_chose.setBackgroundResource(R.drawable.icon_checked);
        else
            viewHolder.btn_chose.setBackgroundResource(R.drawable.icon_checkno);
        viewHolder.btn_chose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditListener.chose(position);
            }
        });
        return convertView;
    }
    public void setAddEditListener(AddEditListener addEditListener){
        this.addEditListener=addEditListener;
    }
    public List<ReturnAddress.ItemsEntity> getAddresses(){
        return addresses;
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
