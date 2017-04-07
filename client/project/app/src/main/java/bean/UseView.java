package bean;

import response.BaseResponse;

/**
 * Created by YYBJ on 2017/4/7.
 */

public class UseView extends BaseResponse{
    public String id;//用户id
    public String platform;//登录的平台信息
    public String nickname;
    public String gender;//性别
    public String province;
    public String city;
    public String useIcon;
    public int auth;//操作平台的权限 1代表最大

    @Override
    public String toString() {
        return "UseView{" +
                "id='" + id + '\'' +
                ", platform='" + platform + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", useIcon='" + useIcon + '\'' +
                ", auth=" + auth +
                '}';
    }
}
