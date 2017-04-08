package bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="new_envirs")
public class Environment {
	
	private float temp;//�¶�
	private float damp;//ʪ��
	private float carbon;//������̼Ũ��
	private int light;//����ǿ��
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long time;//�����ʱ��
	
	public Environment() {}
	
	public Environment(float temp, float damp, float carbon, int light,long time) {
		super();
		this.temp = temp;
		this.damp = damp;
		this.carbon = carbon;
		this.light = light;
		this.time = time;

	}

	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public float getDamp() {
		return damp;
	}

	public void setDamp(float damp) {
		this.damp = damp;
	}

	public float getCarbon() {
		return carbon;
	}

	public void setCarbon(float carbon) {
		this.carbon = carbon;
	}

	public int getLight() {
		return light;
	}

	public void setLight(int light) {
		this.light = light;
	}
}
