package com.example.mouth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.mouth.adapter.ShopAdapter;
import com.example.mouth.api.Api;
import com.example.mouth.bean.Bean;
import com.example.mouth.presenter.ImplShouPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rec)
    RecyclerView rec;
    @BindView(R.id.all)
    CheckBox all;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.num)
    TextView num;
    private List<Bean.DataBean> data;
    private ShopAdapter shopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        /**
         * 初始化布局
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rec.setLayoutManager(linearLayoutManager);
        /**
         * 初始化p层
         */
        ImplShouPresenter implShouPresenter = new ImplShouPresenter(this);
        implShouPresenter.getPresenter(Api.SHOU);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoneyAndCount(all.isChecked());
                shopAdapter.notifyDataSetChanged();


            }
        });
    }
    /**
     * 定义一个成功的方法
     */
    public void getData(Object object)
    {
        if(object instanceof Bean)
        {
            Bean bean = (Bean) object;
            data = bean.getData();
            shopAdapter = new ShopAdapter(MainActivity.this, data);
            rec.setAdapter(shopAdapter);


        }
    }
    public void MoneyAndCount(boolean bool)
    {
        double totalMoney =0;
        int totalNum = 0;
        //循环遍历商家。判断商家的状态
        for(int a = 0; a< data.size(); a++)
        {
            Bean.DataBean dataBean = data.get(a);
            dataBean.setChecked(bool);
            List<Bean.DataBean.DatasBean> datas = dataBean.getDatas();
            //循环遍历商品
            for(int i =0 ; i<datas .size(); i++)
            {
                datas.get(i).setChecked(bool);

            }
        }
        if(bool)
        {
            num.setText("去结算"+totalNum);
            price.setText("总价钱"+totalMoney);
        }else{
            num.setText("去结算  0");
            price.setText("总价钱 0.00");
        }
    }

}
