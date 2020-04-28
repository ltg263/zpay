package com.caidie.skzs.payutils;

import com.caidie.skzs.payutils.NTPTimeUtil;

/**
 * 订单
 */
public class Order {

    private String id;

    private String price;

    private String oldprice;

    private String discount;

    private Long paytime;

    private Long createtime;

    private String type;

    private String source;

    private String data;

    private Integer done;

    public Order(){
        paytime = NTPTimeUtil.getNtpSecond();
        done = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldprice() {
        return oldprice;
    }

    public void setOldprice(String oldprice) {
        this.oldprice = oldprice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Long getPaytime() {
        return paytime;
    }

    public void setPaytime(Long paytime) {
        this.paytime = paytime;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }
}
