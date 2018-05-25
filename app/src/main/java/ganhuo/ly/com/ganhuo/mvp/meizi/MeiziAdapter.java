package ganhuo.ly.com.ganhuo.mvp.meizi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.util.ListUtils;

public class MeiziAdapter extends RecyclerView.Adapter<MeiziAdapter.MeiziViewHolder> {

    private Context context;
    private List<MeiziResult.ResultsBean> pic_list = new ArrayList<>();

    public List<MeiziResult.ResultsBean> getResults() {
        return pic_list;
    }

    public MeiziAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MeiziViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MeiziViewHolder holder = new MeiziViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_text, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(MeiziViewHolder holder, final int position) {
        final MeiziResult.ResultsBean resultsBean = pic_list.get(position);
        holder.tv_title.setText(resultsBean.getTitle());
        holder.tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MeiziListActivity.class);
                intent.putExtra("mid", resultsBean.getMid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (ListUtils.isEmpty(pic_list)) {
            return 0;
        }
        return pic_list.size();
    }

    class MeiziViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tv_title;

        public MeiziViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
