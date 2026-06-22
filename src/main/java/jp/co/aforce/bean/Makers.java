package jp.co.aforce.bean;

import java.io.Serializable;

public class Makers implements Serializable {
	private String makerId;
	private String makerName;

	public String getMakerId() {
		return makerId;
	}

	public String getMakerName() {
		return makerName;
	}

	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}

	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}
}
