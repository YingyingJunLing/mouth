package com.example.mouth.model;

public interface IShouModel
{
    public void getIShouModel(String url,NetCallBack netCallBack);
    interface NetCallBack{
        void loadSuccess(Object obj);
        void loadFail();
    }
}
