package com.cafe24.lms.dto;

import com.cafe24.lms.domain.Reservation;

import java.text.SimpleDateFormat;

public class ReservationDto {
    private Long userNo;
    private Long itemNo;
    private String borrowDate;
    private String returnDate;
    private Long orderNo;

    public ReservationDto(Reservation reservation){
        this.userNo = reservation.getUser().getNo();
        this.itemNo = reservation.getItem().getNo();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        this.borrowDate = format.format(reservation.getBorrowDate());
        this.returnDate = format.format(reservation.getReturnDate());
        this.orderNo = reservation.getOrderNo();
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public Long getItemNo() {
        return itemNo;
    }

    public void setItemNo(Long itemNo) {
        this.itemNo = itemNo;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "userNo=" + userNo +
                ", itemNo=" + itemNo +
                ", borrowDate='" + borrowDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", orderNo=" + orderNo +
                '}';
    }
}
