package com.teenyda.constant;

public enum PaymentTypeEnum {

    Balance(3, "余额"),
    Alipay(2, "支付宝"),
    WeChatPay(1, "微信支付");

    int paymentType;

    String desc;

    PaymentTypeEnum(int paymentType, String desc) {
        this.paymentType = paymentType;
        this.desc = desc;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
