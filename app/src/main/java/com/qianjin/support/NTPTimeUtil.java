package com.qianjin.support;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

public class NTPTimeUtil {

    private static int index = 0;

    private static List<String> servers = Arrays.asList("120.25.108.11", "118.24.195.65", "120.25.115.20", "202.112.29.82", "118.193.151.223");

    public static long getNtpSecond() {
        long t = getNtpSecond(servers.get(index));
        if (t != 0) {
            return t;
        }
        for (int i = 0; i < servers.size(); i++) {
            if (i == index) {
                continue;
            }
            t = getNtpSecond(servers.get(i));
            if (t != 0) {
                index = i;
                return t;
            }
        }
        return 0;
    }

    public static long getNtpSecond(String host) {
        try {
            NTPUDPClient timeClient = new NTPUDPClient();
            String timeServerUrl = host;
            InetAddress timeServerAddress = InetAddress.getByName(timeServerUrl);
            TimeInfo timeInfo = timeClient.getTime(timeServerAddress);
            TimeStamp timeStamp = timeInfo.getMessage().getTransmitTimeStamp();
            return timeStamp.getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(getNtpSecond());
        System.out.println("over");
    }
}
