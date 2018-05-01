package com.example.lenovo.zhanli_ljq.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.zhanli_ljq.R;
import com.example.lenovo.zhanli_ljq.entity.LeftCategory;
import com.example.lenovo.zhanli_ljq.inter.OnRecyclerViewItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2018/5/1.
 */

public class LeftListAdapter extends RecyclerView.Adapter< LeftListAdapter.ViewHolder> {

    private Context context;
    private List<LeftCategory> list;

    private OnRecyclerViewItemClickListener listener;


    //给接口实现一个初始化的方法；

    public  void setListener(OnRecyclerViewItemClickListener listener){
        this.listener=listener;
    }


    //加上构造方法
    public LeftListAdapter(Context context, List<LeftCategory> list) {
        this.context = context;
        this.list = list;
    }

    //找viewhodel将viewhodelnew出来 将布局传给viewhodel
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=View.inflate(context, R.layout.item_left_list,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        //设置展示的图片
        Glide.with(context).load(list.get(position).getIcon()).into(holder.imgLogo);

        //设置下面的显示的汉字
        holder.txtTitle.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return  list.size();
        }
        return 0;
    }

    class  ViewHolder extends  RecyclerView.ViewHolder{

        @BindView(R.id.img_left_item)
        ImageView imgLogo;
        @BindView(R.id.txt_left_item)
        TextView txtTitle;

        public ViewHolder (View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);



        }
    }
}
