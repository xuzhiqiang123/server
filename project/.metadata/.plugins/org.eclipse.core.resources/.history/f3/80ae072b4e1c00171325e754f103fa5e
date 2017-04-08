package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

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
        // 去掉最后一个空格  
        String s = queryString.substring(0, queryString.length()-1);
        System.out.println(s);
	}
	
	public static void printAttribute(HttpServletRequest request){
		Enumeration<String> params = request.getAttributeNames();  
        StringBuilder queryString = new StringBuilder();  
        while(params.hasMoreElements()){
        	String value = String.valueOf(request.getAttribute(params.nextElement()));
        	queryString.append(params.nextElement() + "=" + value + ",");  
        }
        // 去掉最后一个空格  
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
		param.put("status", 1);
		PrintWriter writer = response.getWriter();
		String data = JSON.toJSONString(param);
		writer.print(data);
		writer.flush();
	}

}
