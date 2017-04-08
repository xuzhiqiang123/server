package bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="use_view")
public class UseView {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;//用户id
	private String platform;//登录的平台信息
    private String nickname;
    private String gender;//性别
    private String province;
    private String city;
    private String useIcon;
    private int auth;//操作平台的权限 1代表最大
    private long time;//第一次创建账号的时间
    
    public UseView() {
		// TODO Auto-generated constructor stub
	}
    
    
    
	public UseView(String id, String platform, String nickname, String gender, String province, String city,
			String useIcon, int auth, long time) {
		this.id = id;
		this.platform = platform;
		this.nickname = nickname;
		this.gender = gender;
		this.province = province;
		this.city = city;
		this.useIcon = useIcon;
		this.auth = auth;
		this.time = time;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUseIcon() {
		return useIcon;
	}
	public void setUseIcon(String useIcon) {
		this.useIcon = useIcon;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "UseView [id=" + id + ", platform=" + platform + ", nickname=" + nickname + ", gender=" + gender
				+ ", province=" + province + ", city=" + city + ", useIcon=" + useIcon + ", auth=" + auth + ", time="
				+ time + "]";
	}
}
