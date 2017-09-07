package ganhuo.ly.com.ganhuo.mvp.home.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import ganhuo.ly.com.ganhuo.R;
import ganhuo.ly.com.ganhuo.mvp.base.BaseActivity;
import ganhuo.ly.com.ganhuo.util.SaveImageUtils;
import ganhuo.ly.com.ganhuo.util.ShareUtils;

/**
 * Created by liuyu1 on 2017/8/15.
 */

public class ImageActivity extends BaseActivity{
    private Toolbar toolbar;
    private SimpleDraweeView simpleDraweeView;
    private String url, desc;

    public static final String EXTRA_IMAGE_URL = "url";
    public static final String EXTRA_IMAGE_DESC = "desc";
    public static Intent newIntent(Context context, String url, String desc) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra(ImageActivity.EXTRA_IMAGE_URL, url);
        intent.putExtra(ImageActivity.EXTRA_IMAGE_DESC, desc);
        context.startActivity(intent);
        return intent;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_image);

        url = getIntent().getStringExtra(ImageActivity.EXTRA_IMAGE_URL);
        desc = getIntent().getStringExtra(ImageActivity.EXTRA_IMAGE_DESC);
    }

    @Override
    protected void findViewById() {
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.draweeview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void setListener() {
        GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
        hierarchy.setProgressBarImage(new ProgressBarDrawable());
        Uri uri = Uri.parse(url);
        simpleDraweeView.setImageURI(uri);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle(desc+"");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_image, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_share:
                ShareUtils.shareImage(this, SaveImageUtils.saveImage(url, desc, this));
                break;
            case R.id.action_save:
                SaveImageUtils.saveImage(url, desc, this);
                Toast.makeText(this, "已经保存图片啦", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected Context getActivityContext() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
