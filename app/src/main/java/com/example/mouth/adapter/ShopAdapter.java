package com.example.mouth.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.mouth.MainActivity;
import com.example.mouth.R;
import com.example.mouth.bean.Bean;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>
{
    Context context;
    List<Bean.DataBean> data;

    public ShopAdapter(Context context, List<Bean.DataBean> data)
    {
        this.data = data;
        this.context =context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.sj, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i)
    {
           viewHolder.sj_name.setText(data.get(i).getTitle());
           viewHolder.sj_box.setChecked(data.get(i).isChecked());


        final List<Bean.DataBean.DatasBean> datas = data.get(i).getDatas();
        Log.e("datas",datas.size()+"");
        //初始化子布局
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewHolder.pro_rec.setLayoutManager(linearLayoutManager);
        //创建适配器
        final ProductAdapter productAdapter = new ProductAdapter(context, datas);
        viewHolder.pro_rec.setAdapter(productAdapter);
        viewHolder.sj_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.get(i).setChecked(viewHolder.sj_box.isChecked());
                productAdapter.AllNotAll(viewHolder.sj_box.isChecked());
            }
        });
        productAdapter.setClick(new ProductAdapter.CallBackListener() {
            @Override
            public void BackListener() {
                if(listener !=null)
                {
                    listener.BackListener();
                }
               boolean isAllChecked =true;
                for (Bean.DataBean.DatasBean datasBean : datas)
                {
                    if(!datasBean.isChecked())
                    {
                        isAllChecked =false;
                        break;
                    }
                }
                viewHolder.sj_box.setChecked(isAllChecked);
                data.get(i).setChecked(isAllChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        private final CheckBox sj_box;
        private final TextView sj_name;
        private final RecyclerView pro_rec;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            sj_box = itemView.findViewById(R.id.sj_box);
            sj_name = itemView.findViewById(R.id.sj_name);
            pro_rec = itemView.findViewById(R.id.pro_rec);
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
}
