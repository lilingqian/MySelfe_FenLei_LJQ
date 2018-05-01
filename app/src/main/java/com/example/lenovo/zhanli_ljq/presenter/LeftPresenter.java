package com.example.lenovo.zhanli_ljq.presenter;

import com.example.lenovo.zhanli_ljq.entity.LeftCategory;
import com.example.lenovo.zhanli_ljq.entity.MessageBean;
import com.example.lenovo.zhanli_ljq.inter.IPresenter;
import com.example.lenovo.zhanli_ljq.inter.IView;
import com.example.lenovo.zhanli_ljq.model.LeftModel;

import java.util.List;

/**
 * Created by lenovo on 2018/5/1.
 */

public class LeftPresenter implements IPresenter<MessageBean<List<LeftCategory>>> {

    private IView iv;

    public void attachView(IView iv){
        this.iv=iv;

    }
    public  void  detachView(){

        if (iv!=null){
            iv=null;

        }
    }

    //网络请求获取数据从model传过来的数据
    public  void getData() {
        LeftModel model=new LeftModel( this);
        model.getData();

    }



    @Override
    public void onReceived(MessageBean<List<LeftCategory>> listMessageBean) {

        iv.onSuccess(listMessageBean);
    }

    @Override
    public void onError(Throwable t) {
        iv.onFailed(t);
    }
}
