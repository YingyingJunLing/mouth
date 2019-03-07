package com.example.mouth.presenter;

import com.example.mouth.MainActivity;
import com.example.mouth.model.IShouModel;
import com.example.mouth.model.ImplShouModel;

public class ImplShouPresenter implements IShouPresenter
{
    MainActivity mainActivity;
    private final ImplShouModel implShouModel;

    public ImplShouPresenter(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity ;
        implShouModel = new ImplShouModel();
    }

    @Override
    public void getPresenter(String url)
    {
        implShouModel.getIShouModel(url, new IShouModel.NetCallBack() {
            @Override
            public void loadSuccess(Object obj) {
                mainActivity.getData(obj);

            }

            @Override
            public void loadFail() {

            }
        });

    }
}
