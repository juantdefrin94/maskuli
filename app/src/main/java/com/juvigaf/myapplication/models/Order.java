package com.juvigaf.myapplication.models;

public class Order {

    private Integer money;
    private String orderDate;
    private int teamId;
    private String userId;
    private int status;

    public Order(Integer money, String orderDate, int teamId, String userId) {
        this.money = money;
        this.orderDate = orderDate;
        this.teamId = teamId;
        this.userId = userId;
        this.status = 0;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
