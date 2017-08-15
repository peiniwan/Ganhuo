package ganhuo.ly.com.ganhuo.mvp.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.mvp.entity.Results;
import ganhuo.ly.com.ganhuo.mvp.home.activity.ImageActivity;

/**
 * Created by liuyu1 on 2017/8/14.
 */

public class GirlyAdapter extends RecyclerView.Adapter<GirlyAdapter.GirlyViewHolder> {

    private Context context;
    private List<Results> girly_list = new ArrayList<>();

    public List<Results> getResults() {
        return girly_list;
    }

    public GirlyAdapter(Context context, List<Results> girly_list) {
        this.context = context;
        if (girly_list != null) {
            this.girly_list = girly_list;
        }
    }

    @Override
    public GirlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GirlyViewHolder holder = new GirlyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_girly, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final GirlyViewHolder holder, final int position) {
        Glide.with(context)
                .load(girly_list.get(position).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageActivity.newIntent(context,
                        girly_list.get(position).getUrl(),
                        girly_list.get(position).getDesc());
            }
        });
    }

    @Override
    public int getItemCount() {
        return girly_list.size();
    }


    class GirlyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public GirlyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_girly);
        }
    }

}
