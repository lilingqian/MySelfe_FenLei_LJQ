package com.example.lenovo.zhanli_ljq.inter;

/**
 * Created by lenovo on 2018/5/1.
 */

public interface IView<T> {

    void  onSuccess(T t);

    void  onFailed(Throwable t);

}
