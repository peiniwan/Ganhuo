package ganhuo.ly.com.ganhuo.mvp.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.mvp.entity.ZhiHuResults;

/**
 * Created by liuyu1 on 2017/8/22.
 */

public class ZhiFragmentAdapter extends RecyclerView.Adapter<ZhiFragmentAdapter.CardViewHolder> {

    private ZhiHuResults zhiHuResults;
    private Context context;
    private OnItemClickListener mOnItemClickListener = null;
    private OnShareClickListener mShareClickListener = null;


    public void setZhiHuResults(ZhiHuResults zhiHuResults) {
        this.zhiHuResults = zhiHuResults;
        notifyDataSetChanged();
    }


    public ZhiFragmentAdapter(Context context, ZhiHuResults zhiHuResults) {
        this.context = context;
        this.zhiHuResults = zhiHuResults;
        setHasStableIds(true);

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnShareClickListener {
        void onShareClick(View view, int position);
    }

    public void setOnShareClickListener(OnShareClickListener listener) {
        this.mShareClickListener = listener;
    }


    @Override
    public ZhiFragmentAdapter.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final View itemView = LayoutInflater
                .from(context)
                .inflate(R.layout.zhihu_list_item, parent, false);

        return new CardViewHolder(itemView, new CardViewHolder.ClickResponseListener() {
            @Override
            public void onWholeClick(int position) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(itemView, position);
                }
            }

            @Override
            public void onOverflowClick(View v, final int position) {
                PopupMenu popup = new PopupMenu(context, v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.contextual_news_list, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_share_url:
                                if (mShareClickListener != null) {
                                    mShareClickListener.onShareClick(itemView, position);
                                }
                                break;
                        }
                        return true;
                    }
                });
                popup.show();

            }
        });
    }


    @Override
    public void onBindViewHolder(ZhiFragmentAdapter.CardViewHolder holder, int position) {
        ZhiHuResults.StoriesBean storiesBean = zhiHuResults.getStories().get(position);
//        Log.d("ZhihuFragment", storiesBean.getTitle() + "---" + storiesBean.getImages());
        Glide.with(context)
                .load(storiesBean.getImages().get(0))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.newsImage);
        holder.questionTitle.setText(storiesBean.getTitle());
    }

    @Override
    public int getItemCount() {
        if (zhiHuResults != null) {
            return zhiHuResults.getStories().size();
        } else {
            return 0;
        }
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView newsImage;
        public TextView questionTitle;
        public TextView dailyTitle;
        public ImageView overflow;

        private ClickResponseListener mClickResponseListener;

        public CardViewHolder(View v, ClickResponseListener clickResponseListener) {
            super(v);

            this.mClickResponseListener = clickResponseListener;

            newsImage = (ImageView) v.findViewById(R.id.thumbnail_image);
            questionTitle = (TextView) v.findViewById(R.id.question_title);
            dailyTitle = (TextView) v.findViewById(R.id.daily_title);
            overflow = (ImageView) v.findViewById(R.id.card_share_overflow);

            v.setOnClickListener(this);
            overflow.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == overflow) {
                mClickResponseListener.onOverflowClick(v, getAdapterPosition());
            } else {
                mClickResponseListener.onWholeClick(getAdapterPosition());
            }
        }

        public interface ClickResponseListener {
            void onWholeClick(int position);

            void onOverflowClick(View v, int position);
        }
    }

}
