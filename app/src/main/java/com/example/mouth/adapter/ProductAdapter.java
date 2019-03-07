package com.example.mouth.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.mouth.R;
import com.example.mouth.bean.Bean;

import java.util.List;

class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>
{
    Context context;
    List<Bean.DataBean.DatasBean> datas;
    public ProductAdapter(Context context, List<Bean.DataBean.DatasBean> datas)
    {
        this.context =context ;
        this.datas =datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.pro, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i)
    {
        viewHolder.pro_name.setText(datas.get(i).getType_name());
        viewHolder.pro_price.setText(datas.get(i).getPrice());
        viewHolder.pro_box.setChecked(datas.get(i).isChecked());
        viewHolder.pro_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                datas.get(i).setChecked(isChecked);
                if(listener !=null)
                {
                    listener.BackListener();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox pro_box;
        private final TextView pro_name;
        private final TextView pro_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pro_box = itemView.findViewById(R.id.pro_box);
            pro_name = itemView.findViewById(R.id.pro_name);
            pro_price = itemView.findViewById(R.id.pro_price);
        }
    }
    interface CallBackListener{
        void BackListener();
    }
    CallBackListener listener ;
    public void setClick(CallBackListener listener )
    {
        this.listener = listener;
    }
    public void AllNotAll(boolean isChecked)
    {
        for (Bean.DataBean.DatasBean datasBean : datas)
        {
            datasBean.setChecked(isChecked);
        }
           notifyDataSetChanged();
    }
}
