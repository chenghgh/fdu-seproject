package fudan.se.lab4.service.impl;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeService {

    public static String getWebsiteDatetime(String webUrl){
        try {
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            Date date = new Date(ld);// 转换为标准时间对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
            return sdf.format(date);
        } catch (MalformedURLException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return null;
    }
    public static String getNTPDate() {
        Date date = null;
        TimeInfo info = null;
        String[] hosts = new String[]{
                "ntp02.oal.ul.pt", "ntp04.oal.ul.pt",
                "ntp.xs4all.nl"};

        NTPUDPClient client = new NTPUDPClient();
        client.setDefaultTimeout(5000);
        TimeStamp timeStamp = null;
        for (String host : hosts) {

                try {
                InetAddress hostAddr = InetAddress.getByName(host);
                info = client.getTime(hostAddr);
                date = new Date(info.getMessage().getTransmitTimeStamp().getTime());
                break;
            }
            catch (IOException e) {
                //e.printStackTrace();
            }
        }
        client.close();
        if(info != null){
            timeStamp = info.getMessage().getTransmitTimeStamp();
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        if(timeStamp != null){
            return dateFormat.format(timeStamp.getTime());
        }else return "";

    }

    public static boolean isDouble11(){
        String date = TimeService.getNTPDate();
        String[] arr = date.split(" ");
        String month = arr[0].split("-")[1];
        String day = arr[0].split("-")[2];
        if(month.equals("11") && day.equals("11"))
            return true;
        return false;
    }

    public static boolean is15th(){
        String date = TimeService.getNTPDate();
        String[] arr = date.split(" ");
        //String month = arr[0].split("-")[1];
        String day = arr[0].split("-")[2];
        if(day.equals("15"))
            return true;
        return false;
    }

    public static String getDay(){
        String date = TimeService.getNTPDate();
        String[] arr = date.split(" ");
        String day = arr[0].split("-")[2];
        return day;
    }
}
