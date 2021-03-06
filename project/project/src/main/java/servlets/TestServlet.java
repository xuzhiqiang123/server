package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.TestBean;

public class TestServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("init");
	}
	 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		PrintWriter pw = response.getWriter();
		String send = JSON.toJSONString(new TestBean("调用了get方法", 1,10));
		pw.write(send);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		response.setCharacterEncoding("Utf-8");
		PrintWriter pw = response.getWriter();
		String send = JSON.toJSONString(new TestBean("调用了POST方法", 2,10));
		pw.write(send);
		showParams(request);
	}
	
	private void showParams(HttpServletRequest request) {  
		
		System.out.println("Path:"+request.getServletPath());
		System.out.println("Port:"+request.getServerPort());
		System.out.println("msg:"+request.getParameter("msg"));
		
		
        Map<String,String> map = new HashMap<String, String>();  
        Enumeration<?> paramNames = request.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = (String) paramNames.nextElement();  
  
            String[] paramValues = request.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0) {  
                    map.put(paramName, paramValue);  
                }  
            }  
        }  
  
        Set<Map.Entry<String, String>> set = map.entrySet();  
        System.out.println("------------------------------");  
        for (Map.Entry entry : set) {  
            System.out.println(entry.getKey() + ":" + entry.getValue());  
        }  
        System.out.println("------------------------------");  
    }

}
