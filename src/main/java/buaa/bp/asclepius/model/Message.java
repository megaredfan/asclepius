package buaa.bp.asclepius.model;

import java.sql.Timestamp;

public class Message {

	private long id;
	private String content;
	private String author;
	private Timestamp createTime;
	private long pid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", author=" + author
				+ ", createTime=" + createTime + ", pid=" + pid + "]";
	}
	
	
}
