package com.yaofei.framework.mvc;

/**
 * Created by gonghongrui on 16/10/25.
 */
public class PageSearchRequest<T>  {

	public Integer page;
	public Integer limit;
	T searchCondition;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public T getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(T searchCondition) {
		this.searchCondition = searchCondition;
	}
}
