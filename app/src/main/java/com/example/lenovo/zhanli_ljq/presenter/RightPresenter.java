package com.example.lenovo.zhanli_ljq.presenter;

import com.example.lenovo.zhanli_ljq.entity.MessageBean;
import com.example.lenovo.zhanli_ljq.entity.RightCategory;
import com.example.lenovo.zhanli_ljq.inter.IPresenter;
import com.example.lenovo.zhanli_ljq.inter.IView;
import com.example.lenovo.zhanli_ljq.model.RightModel;

import java.util.List;

/**
 * Created by lenovo on 2018/5/1.
 */

public class RightPresenter implements IPresenter<MessageBean<List<RightCategory>>> {


    private IView iv;

    public  void attachView(IView iv){
        this.iv=iv;

    }

    public  void detachView(){
        if (iv==null){
            iv=null;

        }
    }

    //获取数据
    public  void getData(String cid){

        RightModel model=new RightModel(this);
        model.getData(cid);

    }


    @Override
    public void onReceived(MessageBean<List<RightCategory>> listMessageBean) {

        iv.onSuccess(listMessageBean);
    }

    @Override
    public void onError(Throwable t) {

    }
}
