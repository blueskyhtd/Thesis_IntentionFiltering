package com.viettel.hisone.intentionfilter.model;

import java.io.Serializable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author hadtt17
 * @since Sep 7, 2016
 * @modified Sep 7, 2016
 * @by hadtt17
 */

@Root(name = "thread")
public class ThreadForum implements Serializable {

	private static final long serialVersionUID = 1L;

	@Element(name = "post", required = false)
	private Post post;

	public ThreadForum() {
	}

	public ThreadForum(Post post) {
		super();
		this.post = post;
	}
}
