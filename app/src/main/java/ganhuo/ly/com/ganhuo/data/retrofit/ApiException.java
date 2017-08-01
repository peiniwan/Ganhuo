package ganhuo.ly.com.ganhuo.data.retrofit;


/**
 * 异常类
 * 按照自己公司的接口需求来  不可直接套用
 * 使用需谨慎
 */
public class ApiException extends RuntimeException {


    public ApiException(String strType) {
        getApiExceptionMessage(strType);
    }

    /**
     * 对服务器接口传过来的错误信息进行统一处理
     * 免除在Activity的过多的错误判断
     */
    private static String getApiExceptionMessage(String strType) {
        String message = "";
        switch (strType) {
            case "ERROR":
                message = "ERROR:网页解析失败";
                break;
            default:
                message = "ERROR:网络连接异常";

        }
        return message;
    }
}

