package com.android.book.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.book.R;
import com.android.book.data.db.entity.PayType;

import java.util.List;

public class PayTypeSpinnerAdapter extends BaseAdapter {
    private Context mContext;
    private List<PayType> mData;

    public PayTypeSpinnerAdapter(Context context) {
        this.mContext = context;
    }

    public List<PayType> getmData() {
        return mData;
    }

    public void setmData(List<PayType> mData) {
        this.mData = mData;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public PayType getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_spinner, parent, false);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PayType type = mData.get(position);
        viewHolder.tv_name.setText(type.getPayValue());
        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
    }
}
