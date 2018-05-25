package ganhuo.ly.com.ganhuo.mvp.meizi;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.util.FileUtil;

public class MeiziListAdapter extends RecyclerView.Adapter<MeiziListAdapter.MeiziViewHolder> {

    private Context context;
    private List<byte[]> realPicList = new ArrayList<>();


    public List<byte[]> getRealResults() {
        return realPicList;
    }

    public MeiziListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MeiziViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MeiziViewHolder holder = new MeiziViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_girly, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(MeiziViewHolder holder, final int position) {
        if (realPicList != null) {
            final byte[] b = realPicList.get(position);
            Glide.with(context).load(b).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        requestPermission();
                        Bitmap bitmap = FileUtil.decodeSampledBitmapFromStream(b, 0, 0);
                        FileUtil.saveJPGFile(bitmap, "nihao");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    ImageActivity.newIntent(getActivity(), url, des);
                }
            });
        }


    }


    /**
     * 6.0以上需要动态申请敏感权限
     */
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (context.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                    return;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return realPicList.size();
    }

    class MeiziViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;

        public MeiziViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.iv_girly);
        }
    }
}
