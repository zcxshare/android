package com.demo.www.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

/**
 * author:  zhouchaoxiang
 * date:    2018/7/2
 * explain:
 */
public class PickingAdapter extends RecyclerView.Adapter<PickingAdapter.PickingViewHolder> {

    private final List<PickingBean> mBeans;
    private Context mContext;

    public PickingAdapter(List<PickingBean> datas) {
        mBeans = datas;
    }

    @NonNull
    @Override
    public PickingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_picking, parent, false);
        return new PickingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PickingViewHolder holder, final int position) {
        final PickingBean bean = mBeans.get(position);
        holder.mTvLocation.setText(bean.location);
        holder.mTvSku.setText(bean.sku);
        holder.mTvQuantity.setText(Html.fromHtml(mContext.getResources().getString(R.string.picking_quantity, bean.needQuantity, bean.currentQuantity)));
//        holder.mTvCurrentQuantity.setText(bean.currentQuantity);
        holder.mTvName.setText(bean.name);
        if (bean.isPicked && "0".equals(bean.currentQuantity)) {
            holder.itemView.setBackgroundResource(R.color.stockout_background);
        } else if (bean.isPicked) {
            holder.mBtPicked.setBackgroundResource(R.color.bt_picked_background);
            holder.mBtPicked.setText("取消已拣");
            holder.itemView.setBackgroundResource(R.color.picked_background);
        } else {
            holder.mBtPicked.setBackgroundResource(R.color.bt_unpicked_background);
            holder.mBtPicked.setText("标记已拣");
            holder.itemView.setBackgroundResource(android.R.color.transparent);
        }
        holder.mBtPicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.isPicked = !bean.isPicked;
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeans == null ? 0 : mBeans.size();
    }

    class PickingViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvLocation;
        private TextView mTvSku;
        private TextView mTvQuantity;
        //        private TextView mTvCurrentQuantity;
        private TextView mTvName;
        private TextView mBtPicked;

        public PickingViewHolder(View view) {
            super(view);
            mTvLocation = view.findViewById(R.id.tv_location);
            mTvSku = view.findViewById(R.id.tv_sku);
            mTvQuantity = view.findViewById(R.id.tv_quantity);
//            mTvCurrentQuantity = view.findViewById(R.id.tv_current_quantity);
            mTvName = view.findViewById(R.id.tv_name);
            mBtPicked = view.findViewById(R.id.bt_picked);
        }
    }
}
