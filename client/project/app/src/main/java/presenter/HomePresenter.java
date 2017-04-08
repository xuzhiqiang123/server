package presenter;

import android.util.Log;

import net.ApiClient;
import net.Presenter;

import java.util.HashMap;

import bean.TestBean;
import response.BaseResponse;
import util.UIUtils;

/**
 * Created by YYBJ on 2017/3/15.
 */

public class HomePresenter extends Presenter{

    public void testGet(){
        ApiClient.get("/project1/servlets/TestServlet", TestBean.class,this);
    }

    public void testPost(){
        HashMap<String,Object> params = new HashMap<>();
        params.put("msg","123456789");
        params.put("status",2);
        ApiClient.post("/project/servlets/TestServlet",params, TestBean.class,this);
    }

    @Override
    public boolean onSuccess(String tag, BaseResponse response) {
        if (super.onSuccess(tag, response)){
            if (tag.equals("/project/servlets/TestServlet") && response instanceof TestBean){
                TestBean bean = (TestBean) response;
                Log.e("onsuccess",response.toString());
                UIUtils.showToast(bean.msg+","+bean.statu);
            }
        }
        return true;
    }
}
