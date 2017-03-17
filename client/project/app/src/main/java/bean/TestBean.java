package bean;

import response.BaseResponse;

/**
 * Created by YYBJ on 2017/3/15.
 */

public class TestBean extends BaseResponse {
    public String msg;
    public int statu;
    public int test;

    @Override
    public String toString() {
        return "TestBean{" +
                "msg='" + msg + '\'' +
                ", statu=" + statu +
                ", test=" + test +
                '}';
    }
}
