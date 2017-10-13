package ganhuo.ly.com.ganhuo.mvp.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.mvp.entity.HuaResults;
import ganhuo.ly.com.ganhuo.mvp.entity.Results;
import ganhuo.ly.com.ganhuo.mvp.home.activity.ImageActivity;


/**
 * Created by liuyu1 on 2017/8/14.
 */

public class GirlyAdapter extends RecyclerView.Adapter<GirlyAdapter.GirlyViewHolder> {

    private Context context;
    private List<Results> girly_list = new ArrayList<>();
    private List<HuaResults.PinsBean> huaResults = new ArrayList<>();
    protected final String mUrlSmallFormat;//小图地址
    protected final String mUrlGeneralFormat;//普通地址
    protected final String mUrlBigFormat;//大图地址
    private int type;
    private String url_img;
    private String des;

    public List<Results> getResults() {
        return girly_list;
    }

    public List<HuaResults.PinsBean> getHuaResults() {
        return huaResults;
    }

    public GirlyAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
        this.mUrlSmallFormat = context.getResources().getString(R.string.url_image_small);
        this.mUrlGeneralFormat = context.getResources().getString(R.string.url_image_general);
        this.mUrlBigFormat = context.getResources().getString(R.string.url_image_big);
    }

    @Override
    public GirlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GirlyViewHolder holder = new GirlyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_girly, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final GirlyViewHolder holder, final int position) {

        if (type == 1) {
            url_img = girly_list.get(position).getUrl();
            des = girly_list.get(position).getDesc();
        } else {
            String key = huaResults.get(position).getFile().getKey();
            url_img = String.format(mUrlGeneralFormat, key);
            des = huaResults.get(position).getRaw_text();
        }

        Glide.with(context)
                .load(url_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    url_img = girly_list.get(position).getUrl();
                    des = girly_list.get(position).getDesc();
                    ImageActivity.newIntent(context, url_img, des);
                } else {
                    String key = huaResults.get(position).getFile().getKey();
                    String url_git_img = String.format(mUrlBigFormat, key);
                    Log.d("url_git_img",url_git_img);
                    ImageActivity.newIntent(context, url_git_img, des);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        if (type == 1) {
            return girly_list.size();
        }
        return huaResults.size();

    }


    class GirlyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public GirlyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_girly);
        }
    }

}
