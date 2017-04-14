package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UseView;
import util.HibernateUtil;
import util.ServletUtil;

public class Login extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("Login---init");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InputStream in = request.getInputStream();
		byte[] b = new byte[1024];
		int count = 0;
		StringBuilder builder = new StringBuilder();
		while((count = in.read(b)) > -1){
			builder.append(new String(b,0,count,"utf-8"));
		}
		System.out.println(builder.toString());
		
		/*System.out.println("Login----doPost");
		UseView useView = (UseView) ServletUtil.parseInputStream(request, UseView.class);
		useView.setTime(System.currentTimeMillis());
		System.out.println("Login---"+useView.toString());
		Map<String, Object> param = new HashMap<>();
		param.put("useView", useView);
		ServletUtil.writeToClient(response, param);
		if(HibernateUtil.getOneMessage(HibernateUtil.getSession(), UseView.class, useView.getId()) == null){
			HibernateUtil.addOneMessage(HibernateUtil.getSession(), useView);
			System.out.println("save to database a client");
		}else{
			System.out.println("already have a client");
		}*/
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login----doGet");
		
		System.out.println("uri="+request.getRequestURI());
		System.out.println("urL="+request.getRequestURL());
		System.out.println("Query="+request.getQueryString());
		System.out.println("Method="+request.getMethod());
		System.out.println(request.getParameter("id"));
		System.out.println(request.getAttribute("nickname"));
	}

}
