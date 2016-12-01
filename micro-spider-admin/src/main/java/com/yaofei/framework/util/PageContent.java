package com.yaofei.framework.util;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageContent<T> implements Serializable {

	private static final long serialVersionUID = 867755909294344406L;

	private List<T> content = new ArrayList<>();

	private long total;

	public PageContent(){}

	public PageContent(List<T> content, long total){

		Assert.notNull(content, "Content must not be null!");

		this.content.addAll(content);

		this.total = total;

	}

	public List<T> getContent(){
        return content;
	}

	/**
	 * Returns the total amount of elements.
	 *
	 * @return the total amount of elements
	 */
	public long getTotal(){
		return total;
	}

	public long getTotalElements() {
		return total;
	}


	public void setContent(List<T> content) {
		this.content.clear();
		this.content.addAll(content);
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
