package com.example.lenovo.zhanli_ljq.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.zhanli_ljq.R;
import com.example.lenovo.zhanli_ljq.adapter.LeftListAdapter;
import com.example.lenovo.zhanli_ljq.entity.LeftCategory;
import com.example.lenovo.zhanli_ljq.entity.MessageBean;
import com.example.lenovo.zhanli_ljq.inter.IView;
import com.example.lenovo.zhanli_ljq.inter.OnRecyclerViewItemClickListener;
import com.example.lenovo.zhanli_ljq.presenter.LeftPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/5/1.
 */

public class LeftFragment extends Fragment implements IView<MessageBean<List<LeftCategory>>> {

    @BindView(R.id.rv_left)
    RecyclerView rvLeft;
    Unbinder unbinder;
    private Context mActivity;

    //初始化适配器
    private LeftListAdapter adapter;

    private List<LeftCategory> list;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(mActivity, R.layout.fragment_left, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    //做网络请求
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list=new ArrayList<>();
        adapter=new LeftListAdapter(mActivity,list);

        adapter.setListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Long cid=list.get(position).getCid();
                EventBus.getDefault().post(cid);
            }
        });
        rvLeft.setLayoutManager(new LinearLayoutManager(mActivity));
        rvLeft.setAdapter(adapter);

        LeftPresenter presenter=new LeftPresenter();
        presenter.attachView(this);
        presenter.getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //成功方法
    @Override
    public void onSuccess(MessageBean<List<LeftCategory>> listMessageBean) {

        if (listMessageBean !=null){
            List<LeftCategory> data=listMessageBean.getData();
            if (data!=null){
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
