package fudan.se.lab4.dto;

import fudan.se.lab4.Util.InitUtil;
import org.slf4j.Logger;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import static fudan.se.lab4.Util.InitUtil.InfoLanguage;

public class PaymentInfo implements Serializable {
    private static final long serialVersionUID = -5743364759168621824L;
    private double price;
    private double discount;
    private double discountPrice;
    private List<String> msgs;
    private  Logger logger = InitUtil.sysInfoLogger;
    public PaymentInfo(double price, double discount, double discountPrice, List<String> msgs) {
        this.price = price;
        this.discount = discount;
        this.discountPrice = discountPrice;
        this.msgs = msgs;
    }

    public PaymentInfo() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public List<String> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<String> msgs) {
        this.msgs = msgs;
    }

    public void showInfo(){
        logger.info(MessageFormat.format(InfoLanguage.getString("PAYMENT_INFO"), this.price, this.discount, this.discountPrice));
        System.out.println(MessageFormat.format(InfoLanguage.getString("PAYMENT_INFO"), this.price, this.discount, this.discountPrice));
    }
}
