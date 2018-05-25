package ganhuo.ly.com.ganhuo.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import ganhuo.ly.com.ganhuo.MyApplication;

/**
 * 文件工具类
 */
public class FileUtil {
    /**
     * @return 创建缓存目录
     */
    public static File getcacheDirectory() {
        File file = new File(MyApplication.getInstance().getApplicationContext().getExternalCacheDir(), "MyCache");
        if (!file.exists()) {
            boolean b = file.mkdirs();
            Log.e("file", "文件不存在  创建文件    " + b);
        } else {
            Log.e("file", "文件存在");
        }
        return file;
    }

    public static byte[] readInputStream(InputStream in) throws Exception {
        int len = 0;
        byte buf[] = new byte[1024];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        while ((len = in.read(buf)) != -1) {
            out.write(buf, 0, len);  //把数据写入内存
        }
        out.close();  //关闭内存输出流
        return out.toByteArray(); //把内存输出流转换成byte数组
    }

    public static Bitmap decodeSampledBitmapFromStream(byte[] b, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(b, 0, b.length, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        Log.v("decode", "返回bitmap");
        return BitmapFactory.decodeByteArray(b, 0, b.length, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        Log.v("calculate", " " + inSampleSize);
        return inSampleSize;
    }

    /**
     * @param bm       图片的bitmap
     * @param fileName 文件名
     * @throws IOException
     */
    public static void saveJPGFile(Bitmap bm, String fileName) {

        String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String path = sdcardPath + "/meizitu/";
        File dirFile = new File(path);
        // 文件夹不存在则创建文件夹
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File myCaptureFile = new File(path + fileName + ".jpg");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(myCaptureFile));
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            if (bm.isRecycled() == false) {
                bm.recycle();
            }
            Log.d("Downloadimage", "保存图片成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
