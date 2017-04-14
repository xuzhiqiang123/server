package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.usertype.UserVersionType;

import com.alibaba.fastjson.JSON;

import bean.UseView;

public class ServletUtil {
	
	public static void printParams(HttpServletRequest request){
		Map<String, String[]> params = request.getParameterMap();  
        StringBuilder queryString = new StringBuilder();  
        for (String key : params.keySet()) {  
            String[] values = params.get(key);  
            for (int i = 0; i < values.length; i++) {  
                String value = values[i];  
                queryString.append(key + "=" + value + ",");  
            }  
        }  
        String s = queryString.substring(0, queryString.length()-1);
        System.out.println(s);
	}
	
	public static UseView test(HttpServletRequest request) throws UnsupportedEncodingException{
		UseView useView = new UseView();
		useView.setId(new String(request.getParameter("id").getBytes("iso-8859-1"), "utf-8"));
		useView.setAuth(Integer.valueOf(request.getParameter("auth")));
		useView.setCity(new String(request.getParameter("city").getBytes("iso-8859-1"), "utf-8"));
		useView.setGender(new String(request.getParameter("gender").getBytes("iso-8859-1"), "utf-8"));
		useView.setPlatform(new String(request.getParameter("platform").getBytes("iso-8859-1"), "utf-8"));
		useView.setProvince(new String(request.getParameter("province").getBytes("iso-8859-1"), "utf-8"));
		useView.setTime(Long.valueOf(request.getParameter("time")));
		useView.setUseIcon(new String(request.getParameter("useIcon").getBytes("iso-8859-1"), "utf-8"));
		return useView;
	} 
	
	public static void printAttribute(HttpServletRequest request){
		Enumeration<String> params = request.getAttributeNames();  
        StringBuilder queryString = new StringBuilder();  
        while(params.hasMoreElements()){
        	String value = String.valueOf(request.getAttribute(params.nextElement()));
        	queryString.append(params.nextElement() + "=" + value + ",");  
        }
        // 鍘绘帀鏈�悗涓�釜绌烘牸  
//        String s = queryString.substring(0, queryString.length()-1);
        System.out.println(queryString.toString());
	}
	
	@SuppressWarnings("rawtypes")
	public static Object parseInputStream(HttpServletRequest request,Class obj){
		try {
			InputStream in = request.getInputStream();
			byte[] b = new byte[1024];
			int count = 0;
			StringBuilder builder = new StringBuilder();
			while((count = in.read(b)) > -1){
				builder.append(new String(b,0,count,"utf-8"));
			}
			@SuppressWarnings("unchecked")
			Object object = JSON.parseObject(builder.toString(),obj);
			return object;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void writeToClient(HttpServletResponse response, Map<String, Object> param) throws IOException {
		response.setHeader("Content-type", "textml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		param.put("status", 1);
		PrintWriter writer = response.getWriter();
		String data = JSON.toJSONString(param);
		System.out.println(data);
		writer.print(data);
		writer.flush();
		writer.close();
	}

}
