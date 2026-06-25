package jp.co.aforce.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Notices implements Serializable {
	private int id;
	private String title;
	private String content;
	private String noticeType;
	private int display;
	private LocalDateTime start;
	private String startStr;
	private LocalDateTime end;
	private String endStr;
	private LocalDateTime create;
	private String createStr;
	private LocalDateTime update;
	private String updateStr;

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public int getDisplay() {
		return display;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public String getStartStr() {
		return startStr;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public String getEndStr() {
		return endStr;
	}

	public LocalDateTime getCreate() {
		return create;
	}

	public String getCreateStr() {
		return createStr;
	}

	public LocalDateTime getUpdate() {
		return update;
	}

	public String getUpdateStr() {
		return updateStr;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public void setStartStr(String startStr) {
		this.startStr = startStr;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public void setEndStr(String endStr) {
		this.endStr = endStr;
	}

	public void setCreate(LocalDateTime create) {
		this.create = create;
	}

	public void setCreateStr(String createStr) {
		this.createStr = createStr;
	}

	public void setUpdate(LocalDateTime update) {
		this.update = update;
	}

	public void setUpdateStr(String updateStr) {
		this.updateStr = updateStr;
	}

}
