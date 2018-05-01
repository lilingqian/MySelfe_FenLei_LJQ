package com.example.lenovo.zhanli_ljq.inter;

/**
 * Created by lenovo on 2018/5/1.
 */

public interface IPresenter<T> {

    void onReceived(T t);
    void onError(Throwable t);
}
