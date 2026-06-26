package jp.co.aforce.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Orders implements Serializable {
	private int orderId;
	private String memberId;
	private Timestamp orderDate;
	private Timestamp orderDateTs;
	private int status;

	public int getOrderId() {
		return orderId;
	}

	public String getMemberId() {
		return memberId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public int getStatus() {
		return status;
	}

	public Timestamp getOrderDateTs() {
		return orderDateTs;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setOrderDateTs(Timestamp orderDateTs) {
		this.orderDateTs = orderDateTs;
	}
}
