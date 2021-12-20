package com.example.model;

import java.io.Serializable;

public class OrderStatus implements Serializable {
    private String orderId,orderStatus,orderDateTime,orderTotal;

    public OrderStatus( String orderId, String orderStatus, String orderDateTime, String orderTotal) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderDateTime = orderDateTime;
        this.orderTotal = orderTotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }
}
