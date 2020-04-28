package com.caidie.skzs.payutils;

import com.caidie.skzs.payutils.Const;
import com.caidie.skzs.payutils.Order;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderMatcher {

    private String weixin = "com.tencent.mm";

    private String weixin_t = "微信收款";

    private String zhifubao = "com.eg.android.AlipayGphone";

    private String zhifubao_t = "支付宝";

    public Order match(String pkg, String title, String text) {
        if (pkg.contains(weixin)) {
            return weixin(title, text);
        } else if (pkg.contains(zhifubao)) {
            return zhifubao(title, text);
        }
        return null;
    }

    private Order weixin(String title, String text) {
        Order order = null;
        if (title.contains(weixin_t)) {
            String price = findPrice(text);
            if (price != null) {
                order = new Order();
                order.setType(Const.weixin);
                order.setPrice(price);
            }
        }
        return order;
    }

    private Order zhifubao(String title, String text) {
        Order order = null;
        if (title.contains(zhifubao_t)) {
            String price = findPrice(text);
            if (price != null) {
                order = new Order();
                order.setType(Const.alipay);
                order.setPrice(price);
            }
        }
        return order;
    }

    private String findPrice(String str) {
        String ps = "([\\d\\.]+)元";
        Pattern p = Pattern.compile(ps);
        Matcher m = p.matcher(str);
        if (m.find()) {
            String uname = m.group(1);
            return uname;
        }
        return null;
    }

}
