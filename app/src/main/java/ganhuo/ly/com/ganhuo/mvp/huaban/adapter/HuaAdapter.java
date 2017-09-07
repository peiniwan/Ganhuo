package ganhuo.ly.com.ganhuo.mvp.huaban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.mvp.entity.HuaResults;

/**
 * Created by liuyu1 on 2017/8/22.
 */

public class HuaAdapter extends RecyclerView.Adapter<HuaAdapter.CardViewHolder> {

    private HuaResults huaResults;
    private Context context;



    public void setHuaResults(HuaResults huaResults) {
        this.huaResults = huaResults;
        notifyDataSetChanged();
    }


    public HuaAdapter(Context context, HuaResults huaResults) {
        this.context = context;
        this.huaResults = huaResults;
        setHasStableIds(true);

    }

    @Override
    public HuaAdapter.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();

        final View itemView = LayoutInflater
                .from(context)
                .inflate(R.layout.zhihu_list_item, parent, false);

        return new CardViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(HuaAdapter.CardViewHolder holder, int position) {
//        ZhiHuResults.StoriesBean storiesBean = HuaResults.getStories().get(position);
//        Log.d("onFailure", storiesBean.getTitle() + "---" + storiesBean.getImages());

//        Glide.with(context)
//                .load(storiesBean.getImages().get(0))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.newsImage);
//        holder.questionTitle.setText(storiesBean.getTitle());
    }

    @Override
    public int getItemCount() {
        if (huaResults != null) {
            return 0;
        } else {
            return 0;
        }
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView newsImage;
        public TextView questionTitle;
        public TextView dailyTitle;
        public ImageView overflow;


        public CardViewHolder(View v ) {
            super(v);
            newsImage = (ImageView) v.findViewById(R.id.thumbnail_image);
            questionTitle = (TextView) v.findViewById(R.id.question_title);
            dailyTitle = (TextView) v.findViewById(R.id.daily_title);
            overflow = (ImageView) v.findViewById(R.id.card_share_overflow);

            v.setOnClickListener(this);
            overflow.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }
    }

}
