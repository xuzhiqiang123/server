package bean;

public class TestBean {
	
	private String msg;
	private int statu;
	
	private int test;
	
	public TestBean(String msg, int status,int test) {
		this.msg = msg;
		this.test = test;
		this.statu = status;
	}
	
	public int getTest() {
		return test;
	}
	public void setTest(int test) {
		this.test = test;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
}
