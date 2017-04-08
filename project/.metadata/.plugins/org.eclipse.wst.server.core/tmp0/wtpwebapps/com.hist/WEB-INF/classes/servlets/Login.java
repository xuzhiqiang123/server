package servlets;

import java.io.IOException;
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
		System.out.println("Login----doPost");
		UseView useView = (UseView) ServletUtil.parseInputStream(request, UseView.class);
		useView.setTime(System.currentTimeMillis());
		System.out.println("Login---"+useView.toString());
		Map<String, Object> param = new HashMap<>();
		param.put("useView", useView);
		ServletUtil.writeToClient(response, param);
		HibernateUtil.addOneMessage(HibernateUtil.getSession(), useView);
		/*if(HibernateUtil.getOneMessage(HibernateUtil.getSession(), UseView.class, useView.getId()) == null){
			HibernateUtil.addOneMessage(HibernateUtil.getSession(), useView);
			System.out.println("save to database a client");
		}else{
			System.out.println("already have a client");
		}*/
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login----doGet");
	}

}