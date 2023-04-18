package com.codestates.project.dto.request;

public class BookSearchRequest {

    private BookSearchType searchType;
    private String searchValue;

    public BookSearchType getSearchType() {
        return searchType;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchType(BookSearchType searchType) {
        this.searchType = searchType;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
