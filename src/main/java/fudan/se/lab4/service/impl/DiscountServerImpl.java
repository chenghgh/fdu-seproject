package fudan.se.lab4.service.impl;

import fudan.se.lab4.dto.OrderItemInfo;
import fudan.se.lab4.dto.OrderItemInfos;
import fudan.se.lab4.dto.PaymentInfo;
import fudan.se.lab4.service.DiscoutService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static fudan.se.lab4.Lab4Application.InfoLanguage;

@Service
public class DiscountServerImpl implements DiscoutService {
    static int numOfFree = 0;  //the number of free bills
    static String today = TimeService.getDay();





    @Override
    public double getDiscount(OrderItemInfos orderItemInfos, PaymentInfo info){
        List<List<String>> allMsgs = new ArrayList<>();
        double discount[] = new double[5];

        allMsgs.add(new ArrayList<>());
        allMsgs.add(new ArrayList<>());
        allMsgs.add(new ArrayList<>());
        allMsgs.add(new ArrayList<>());
        allMsgs.add(new ArrayList<>());

        discount[0] = getDiscount1(orderItemInfos, allMsgs.get(0));
        discount[1] = getDiscount2(orderItemInfos, allMsgs.get(1));
        discount[2] = getDiscount3(orderItemInfos, allMsgs.get(2));
        discount[3] = getDiscount4(orderItemInfos, allMsgs.get(3));
        discount[4] = getDiscount5(orderItemInfos, allMsgs.get(4));

        double maxDiscount = 0.0;
        int num = 0;
        for(int i = 0 ; i < 5 ;i++ ) {
            if(discount[i] > maxDiscount){
                maxDiscount = discount[i];
                num = i;

            }
        }
        info.setDiscount(maxDiscount);
        info.setMsgs(allMsgs.get(num));
        return info.getDiscount();
    }

    private double getDiscount1(OrderItemInfos infos, List<String> msgs1){
        List<OrderItemInfo> orderItemInfos = infos.getInfos();
        double discount1 = 0.0;
        int sizeOfEspresso = 0;
        int sizeOfTea = 0;
        int sizeOfBlackTea = 0;
        int sizeOfCappuccino = 0;

        for(int i = 0; i<orderItemInfos.size();i++) {

            if(orderItemInfos.get(i).getID()==101 && orderItemInfos.get(i).getSize()==3){
                sizeOfEspresso += 1;
            }
            else if(orderItemInfos.get(i).getID()==202) {
                sizeOfTea += 1;
            }
            else if (orderItemInfos.get(i).getID()==201) {
                sizeOfTea += 1;
                sizeOfBlackTea += 1;
            }
            else if(orderItemInfos.get(i).getID()==102) {
                sizeOfCappuccino += 1;
            }
        }

        //Discount of Espresso
        int countOfEspresso = sizeOfEspresso/2;
        discount1 += countOfEspresso*40*0.2;
        if(sizeOfEspresso>=2)
            msgs1.add(MessageFormat.format(InfoLanguage.getString("DISCOUNT_OF_ESPRESSO"),countOfEspresso));

        //Discount of Tea
        int countOfTea = sizeOfTea/4;
        if(countOfTea>sizeOfBlackTea)
            discount1 += sizeOfBlackTea*18.0 + (countOfTea-sizeOfBlackTea)*16.0;
        else
            discount1 += countOfTea*18.0;
        if(sizeOfTea>=4)
            msgs1.add(MessageFormat.format(InfoLanguage.getString("DISCOUNT_OF_TEA"),sizeOfTea,countOfTea));
        //Discount of Cappuccino
        int countOfCappuccino = sizeOfCappuccino/2;
        discount1 += countOfCappuccino*22*0.5;
        if(sizeOfCappuccino>=2)
            msgs1.add(MessageFormat.format(InfoLanguage.getString("DISCOUNT_OF_CAPPUCCINO"),countOfCappuccino, sizeOfCappuccino));
        return discount1;
    }

    private double getDiscount2(OrderItemInfos infos, List<String> msgs2){

        double discount2 = 0.0;
        double totalCount = infos.getPrice();
        int totalIntCount = (int)totalCount;
        totalIntCount/=100;
        discount2=totalIntCount*30.0;
        if(totalCount >=100){
            msgs2.add(MessageFormat.format(InfoLanguage.getString("FULL_DISCOUNT"),totalCount,totalIntCount*30));
        }
        return discount2;
    }

    private double getDiscount3(OrderItemInfos infos, List<String> msgs3){
        double discount3 = 0.0;
        double totalCount = infos.getPrice();
        if(false){
            discount3 = totalCount*0.5;
            msgs3.add(MessageFormat.format(InfoLanguage.getString("DISCOUNT_OF_DOUBLEELEVEN"),discount3));
        }
        return discount3;
    }

    private double getDiscount4(OrderItemInfos infos, List<String> msgs4){
        if(!today.equals(TimeService.getDay())){
            today = TimeService.getDay();
            numOfFree = 0;

        }


        double discount4 = 0.0;
        double totalCount = infos.getPrice();
        if(false){


            if(false ){
                discount4 = totalCount;
                msgs4.add(MessageFormat.format(InfoLanguage.getString("DISCOUNT_OF_MIDMONTH"),totalCount));
                numOfFree ++;
            }
        }
        return discount4;
    }

    private double getDiscount5(OrderItemInfos infos, List<String> msgs5){
        List<OrderItemInfo> orderItemInfos = infos.getInfos();
        double discount5 = 0.0;
        double totalCount = infos.getPrice();
        int numOfCoffee = 0;
        int numOfTea = 0;
        for(int i = 0; i<orderItemInfos.size();i++) {
            if(orderItemInfos.get(i).getID()==101||orderItemInfos.get(i).getID()==102)
                numOfCoffee ++;
            if(orderItemInfos.get(i).getID()==201||orderItemInfos.get(i).getID()==202)
                numOfTea ++;
        }

        if(numOfCoffee >= 1 && numOfTea >= 1){
            discount5 = 0.15 * totalCount;
            msgs5.add(MessageFormat.format(InfoLanguage.getString("DISCOUNT_OF_NOTFUSSY"),discount5));
        }
        return discount5;
    }


}
