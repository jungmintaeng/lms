package com.cafe24.lms.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(ReservationId.class)
public class Reservation {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_no")
    private Item item;

    @Temporal(TemporalType.DATE)
    @Column(name = "borrow_date")
    private Date borrowDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "order_no")
    private Long orderNo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
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
        return "Reservation{" +
                "user=" + user +
                ", item=" + item +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", orderNo=" + orderNo +
                '}';
    }
}
