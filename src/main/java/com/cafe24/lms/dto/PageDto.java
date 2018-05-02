package com.cafe24.lms.dto;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageDto<T> {
    private Boolean canGoPrev;
    private Boolean canGoNext;
    private Long curPage;
    private List<Long> pageNumbers;
    private Long totalPages;
    private Long totalItems;
    private List<T> content;
    private Long startNo;
    private Long endNo;

    public PageDto() {
    }

    public PageDto(Page<T> page, Integer currentPage){
        if(page == null)
            return;
        curPage = Long.valueOf(currentPage);
        startNo = Long.valueOf(currentPage / 5 * 5 + 1);
        endNo = startNo + 5;

        pageNumbers = new ArrayList<>();
        for(Long i = startNo; i < endNo; i++){
            pageNumbers.add(i);
        }
        totalPages = Long.valueOf(page.getTotalPages());
        totalItems = page.getTotalElements();
        canGoPrev = startNo > 5;
        canGoNext = endNo - 1 < totalPages;
        this.content = page.getContent();

//        System.out.println("total : " + totalItems + "\ntotal pages : " + totalPages + "\nprev : " + canGoPrev + "\nnext : " + canGoNext);
    }

    public Boolean getCanGoPrev() {
        return canGoPrev;
    }

    public void setCanGoPrev(Boolean canGoPrev) {
        this.canGoPrev = canGoPrev;
    }

    public Boolean getCanGoNext() {
        return canGoNext;
    }

    public void setCanGoNext(Boolean canGoNext) {
        this.canGoNext = canGoNext;
    }

    public Long getCurPage() {
        return curPage;
    }

    public void setCurPage(Long curPage) {
        this.curPage = curPage;
    }

    public List<Long> getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(List<Long> pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Long getStartNo() {
        return startNo;
    }

    public void setStartNo(Long startNo) {
        this.startNo = startNo;
    }

    public Long getEndNo() {
        return endNo;
    }

    public void setEndNo(Long endNo) {
        this.endNo = endNo;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }
}
