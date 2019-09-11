package com.android.book.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.book.R;
import com.android.book.data.db.entity.Bill;
import com.android.book.utilitles.Util;

import java.util.List;

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.BillViewHolder> {
    private List<Bill> billList;


    private LayoutInflater mLayoutInflater;

    public BillListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_bill, parent, false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        Bill bill = billList.get(position);
        holder.tvPayType.setText(String.valueOf(bill.getPayType()));
        holder.tvDesc.setText(bill.getDesc());
        holder.tvAmount.setText(Util.formatAmount(bill.getAmount()));
    }

    @Override
    public int getItemCount() {
        return billList == null ? 0 : billList.size();
    }

    static class BillViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPayType, tvDesc, tvAmount;

        public BillViewHolder(View itemView) {
            super(itemView);
            tvPayType = itemView.findViewById(R.id.tv_paytype);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvAmount = itemView.findViewById(R.id.tv_amount);
        }
    }
}
