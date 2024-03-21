package org.example.model;

import java.util.Date;

public class RevenuePerDay {
    private Integer id;
    private Date cDate;
    private Long revenueCash;
    private Long revenueNonCash;

    public RevenuePerDay(Date cDate, Long revenueCash, Long revenueNonCash) {
        this.cDate = cDate;
        this.revenueCash = revenueCash;
        this.revenueNonCash = revenueNonCash;
    }

    public RevenuePerDay(Integer id, Date cDate, Long revenueCash, Long revenueNonCash) {
        this.id = id;
        this.cDate = cDate;
        this.revenueCash = revenueCash;
        this.revenueNonCash = revenueNonCash;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public Long getRevenueCash() {
        return revenueCash;
    }

    public void setRevenueCash(Long revenueCash) {
        this.revenueCash = revenueCash;
    }

    public Long getRevenueNonCash() {
        return revenueNonCash;
    }

    public void setRevenueNonCash(Long revenueNonCash) {
        this.revenueNonCash = revenueNonCash;
    }
}
