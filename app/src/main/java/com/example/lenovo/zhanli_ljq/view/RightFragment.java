package com.example.lenovo.zhanli_ljq.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.zhanli_ljq.R;
import com.example.lenovo.zhanli_ljq.adapter.LeftListAdapter;
import com.example.lenovo.zhanli_ljq.adapter.RightListAdapter;
import com.example.lenovo.zhanli_ljq.entity.LeftCategory;
import com.example.lenovo.zhanli_ljq.entity.MessageBean;
import com.example.lenovo.zhanli_ljq.entity.RightCategory;
import com.example.lenovo.zhanli_ljq.inter.IView;
import com.example.lenovo.zhanli_ljq.presenter.LeftPresenter;
import com.example.lenovo.zhanli_ljq.presenter.RightPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/5/1.
 */

public class RightFragment extends Fragment implements IView<MessageBean<List<RightCategory>>>{

    @BindView(R.id.rv_right)
    RecyclerView rvRight;
    Unbinder unbinder;
    private Context mActivity;

    private RightPresenter presenter;
    //初始化适配器
    private RightListAdapter adapter;

    private List<RightCategory> list;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(mActivity, R.layout.fragment_right, null);

        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    //做网络请求
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list=new ArrayList<>();
        adapter=new RightListAdapter(mActivity,list);
        rvRight.setLayoutManager(new LinearLayoutManager(mActivity));
        rvRight.setAdapter(adapter);

        rvRight.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL));
        presenter=new RightPresenter();
        presenter.attachView(this);
        presenter.getData("1");

    }

    @Subscribe(threadMode = ThreadMode.MAIN)

    public  void receiveData(Long cid){
        presenter.getData(String.valueOf(cid));
    }
    //进行反注册
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }
    //成功方法
    @Override
    public void onSuccess(MessageBean<List<RightCategory>> listMessageBean) {

        if (listMessageBean !=null){
            List<RightCategory> data=listMessageBean.getData();
            if (data!=null){
                list.clear();
                list.addAll(data);
                adapter.notifyDataSetChanged();
            }
        }
    }

    //失败的方法
    @Override
    public void onFailed(Throwable t) {


        Toast.makeText(mActivity,"数据没有获取到"+t.getMessage(),Toast.LENGTH_SHORT).show();
    }
}
