package com.example.lenovo.zhanli_ljq.model;

import com.example.lenovo.zhanli_ljq.entity.LeftCategory;
import com.example.lenovo.zhanli_ljq.entity.MessageBean;
import com.example.lenovo.zhanli_ljq.inter.IPresenter;
import com.example.lenovo.zhanli_ljq.utils.HttpUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by lenovo on 2018/5/1.
 */

public class LeftModel {

    private IPresenter presenter;

    public LeftModel (IPresenter presenter){
        this.presenter=presenter;
    }

    //开始网络请求
    public void getData(){

        HttpUtils.getInstance().getService()
                .getFirstCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<MessageBean<List<LeftCategory>>>() {
                    //返回成功的信息
                    @Override
                    public void onNext(MessageBean<List<LeftCategory>> listMessageBean) {
                        presenter.onReceived(listMessageBean);
                    }

                    //返回失败的信息
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
