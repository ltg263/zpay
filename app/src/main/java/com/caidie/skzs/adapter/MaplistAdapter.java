package com.caidie.skzs.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caidie.skzs.R;
import com.caidie.skzs.bean.GeneralIncomeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxy
 * @date 2018/11/26
 * Describe:
 */
public class MaplistAdapter extends RecyclerView.Adapter<MaplistAdapter.RewardViewHolder> {

    ArrayList<GeneralIncomeBean> lists = new ArrayList<>();
    Context mContext;

    public MaplistAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_map, parent, false);
        return new RewardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardViewHolder holder, final int position) {
        final GeneralIncomeBean list = lists.get(position);
        if(position%2==0){
            holder.ll_bj.setBackgroundColor(Color.parseColor("#eeeeee"));
        }else{
            holder.ll_bj.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        holder.tv1.setText(list.getTime());
        holder.tv2.setText(list.getSdd());
        holder.tv3.setText(list.getFkdd());
        holder.tv4.setText(list.getZsr());

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<GeneralIncomeBean> list) {
        this.lists = (ArrayList<GeneralIncomeBean>) list;
        Log.w("list",lists.toString());
        notifyDataSetChanged();
    }

    class RewardViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_bj;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        public RewardViewHolder(View itemView) {
            super(itemView);
            ll_bj= itemView.findViewById(R.id.ll_bj);
            tv1= itemView.findViewById(R.id.tv_1);
            tv2= itemView.findViewById(R.id.tv_2);
            tv3= itemView.findViewById(R.id.tv_3);
            tv4= itemView.findViewById(R.id.tv_4);
        }
    }
    // 利用接口 -> 给RecyclerView设置点击事件
    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        public void onItemClick(int postion) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;
    }
}
