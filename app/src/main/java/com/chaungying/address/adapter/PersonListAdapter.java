package com.chaungying.address.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chaungying.address.bean.PersonListBean;
import com.chaungying.wuye3.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王晓赛 or 2016/8/31
 */
public class PersonListAdapter extends BaseAdapter {

    private Context mContext;
    List<PersonListBean.DataBean> list = new ArrayList<PersonListBean.DataBean>();

    public PersonListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(List<PersonListBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler holer = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_person_list_item, null);
            holer = new ViewHoler(convertView);
            convertView.setTag(holer);
        } else {
            holer = (ViewHoler) convertView.getTag();
        }
        if (list.get(position).getPortrait() != null && list.get(position).getPortrait().length() > 0) {
            Picasso.with(mContext).load(list.get(position).getPortrait()).error(R.drawable.qian_dao_tong_ji_head).into(holer.person_list_iv);
        } else {
            Picasso.with(mContext).load(R.drawable.qian_dao_tong_ji_head).into(holer.person_list_iv);
        }
        holer.person_list_name.setText(list.get(position).getUserName());
        holer.person_list_position.setText(list.get(position).getPosition());
        return convertView;
    }

    class ViewHoler {
        CircularImageView person_list_iv;
        TextView person_list_name;
        TextView person_list_position;


        ViewHoler(View view) {
            person_list_iv = (CircularImageView) view.findViewById(R.id.person_list_iv);
            person_list_name = (TextView) view.findViewById(R.id.person_list_name);
            person_list_position = (TextView) view.findViewById(R.id.person_list_position);
        }
    }


}
