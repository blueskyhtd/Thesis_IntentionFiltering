package com.viettel.hisone.intentionfilter.model;

import java.io.Serializable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author hadtt17
 * @since Sep 7, 2016
 * @modified Sep 7, 2016
 * @by hadtt17
 */

@Root(name = "post")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	@Attribute(name = "id", required = false)
	private int id;

	@Attribute(name = "class", required = false)
	private int cls;

	@Element(name = "title", required = false)
	private String title;

	@Element(name = "username", required = false)
	private String username;

	@Element(name = "datetime", required = false)
	private String datetime;

	@Element(name = "content", required = false)
	private String content;

	public Post() {
	}

	public Post(int cls, String content) {
		this.cls = cls;
		this.content = content;
	}

	public Post(int id, int cls, String title, String username, String datetime, String content) {
		super();
		this.id = id;
		this.cls = cls;
		this.title = title;
		this.username = username;
		this.datetime = datetime;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCls() {
		return cls;
	}

	public void setCls(int cls) {
		this.cls = cls;
	}

}
