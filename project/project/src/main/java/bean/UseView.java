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
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;//鐢ㄦ埛id
	private String platform;//鐧诲綍鐨勫钩鍙颁俊鎭�
    private String nickname;
    private String gender;//鎬у埆
    private String province;
    private String city;
    private String useIcon;
    private int auth;//鎿嶄綔骞冲彴鐨勬潈闄�1浠ｈ〃鏈�ぇ
    private long time;//绗竴娆″垱寤鸿处鍙风殑鏃堕棿
    
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