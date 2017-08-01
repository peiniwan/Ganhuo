//package ganhuo.ly.com.ganhuo.MVP;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Handler;
//import android.view.View;
//
//import ganhuo.ly.com.ganhuo.MVP.Base.BaseActivity;
//import ganhuo.ly.com.ganhuo.R;
//
//
///*
//* 欢迎页面
//* 用途进入白屏或者黑屏
//* 加载必须配置数据
//* */
//public class WelcomeActivity extends BaseActivity {
//
//    @Override
//    protected void loadViewLayout() {
//        setContentView(R.layout.activity_welcome);
//    }
//
//    @Override
//    protected void findViewById() {
//
//    }
//
//    @Override
//    protected void setListener() {
//
//    }
//
//    @Override
//    protected void processLogic() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(WelcomeActivity.this,HomeTabActivity.class));
//                finish();
//            }
//        }, 2000);
//    }
//
//    @Override
//    protected Context getActivityContext() {
//        return this;
//    }
//
//    @Override
//    public void onClick(View view) {
//
//    }
//}
