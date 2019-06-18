package fudan.se.lab4.service.impl;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

public class AddressService {
    public static String getWebIpAdd() {
        BufferedReader br = null;
        try {
            URL url = new URL("http://2019.ip138.com/ic.asp");
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            StringBuffer sb = new StringBuffer("");
            String webContent = "";
            while ((s = br.readLine()) != null) {
                sb.append(s + "\r\n");
            }
            //br.close();
            webContent = sb.toString();
            int start = webContent.indexOf("[")+1;
            int end = webContent.indexOf("]");
            webContent = webContent.substring(start,end);
            return webContent;
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }finally {
            try {
                if(br != null){
                    br.close();
                }
            }catch (Exception e){
                //e.printStackTrace();
            }

        }
    }

    public static String getGeoAddress(String ip) throws IOException, GeoIp2Exception {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/GeoLite2-Country.mmdb";
        String filePath1 = System.getProperty("user.dir") + "/src/main/resources/GeoLite2-City.mmdb";
        File database = new File(filePath);
        File database1 = new File(filePath1);
        DatabaseReader reader = new DatabaseReader.Builder(database).build();
        DatabaseReader reader1 = new DatabaseReader.Builder(database1).build();
        InetAddress ipAddress = InetAddress.getByName(ip);
//        System.out.println(getV4IP());
        CountryResponse response = reader.country(ipAddress);

        Country country = response.getCountry();
        System.out.println(country.getIsoCode());
        System.out.println(country.getName());
        System.out.println(country.getNames().get("zh-CN"));

        CityResponse response1 = reader1.city(ipAddress);
        City city = response1.getCity();
        System.out.println(city.getName());

        return country.getName();
    }

}
