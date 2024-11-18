package com.heemin.ws.model.dto;

public class SearchCondition {
	private long categoryId;	//영상 검색에 활용할 카테고리 아이디
	private String searchValue;	//검색 값
	
	public SearchCondition() {
		
	}

	public SearchCondition(long categoryId, String searchValue) {
		super();
		this.categoryId = categoryId;
		this.searchValue = searchValue;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public String toString() {
		return "SearchCondition [categoryId=" + categoryId + ", searchValue=" + searchValue + "]";
	}
	
	
}
