package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.Person;

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
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		Person person = new Person(24, "json", true);
		String msg = JSON.toJSONString(person);
		writer.println("<h1>doGet</h1>");
		System.out.println(msg);
		writer.println(msg);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		PrintWriter writer = response.getWriter();
		Person person = new Person(24, "json", true);
		String msg = JSON.toJSONString(person);
		writer.println(msg);
	}

}