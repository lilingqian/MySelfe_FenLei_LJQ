package com.example.lenovo.zhanli_ljq.model;

import com.example.lenovo.zhanli_ljq.adapter.RightListAdapter;
import com.example.lenovo.zhanli_ljq.entity.MessageBean;
import com.example.lenovo.zhanli_ljq.entity.RightCategory;
import com.example.lenovo.zhanli_ljq.inter.IPresenter;
import com.example.lenovo.zhanli_ljq.utils.HttpUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by lenovo on 2018/5/1.
 */

public class RightModel {

    private  IPresenter presenter;

     public RightModel(IPresenter presenter){
         this.presenter=presenter;

     }

     public  void getData(String  cid){

         HttpUtils.getInstance().getService()
                 .getSecondCategory(cid)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new DisposableSubscriber<MessageBean<List<RightCategory>>>() {
                     @Override
                     public void onNext(MessageBean<List<RightCategory>> listMessageBean) {

                         presenter.onReceived(listMessageBean);
                     }

                     @Override
                     public void onError(Throwable t) {

                         presenter.onError(t);
                     }

                     @Override
                     public void onComplete() {

                     }
                 });

     }
}
