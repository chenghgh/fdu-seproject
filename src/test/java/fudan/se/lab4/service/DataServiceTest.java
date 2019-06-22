package fudan.se.lab4.service;

//import fudan.se.lab4.constant.InfoConstant;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.entity.User;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class DataServiceTest {


//
    @Test
    public void getDrinkBasicPrice() {
        assertEquals(20,(int)DataService.getDrinkBasicPrice(101));
        assertEquals(22,(int)DataService.getDrinkBasicPrice(102));
        assertEquals(16,(int)DataService.getDrinkBasicPrice(202));
        assertEquals(18,(int)DataService.getDrinkBasicPrice(201));


    }

    @Test
    public void getSizeExtraPrice() {
        assertEquals(6,(int)DataService.getSizeExtraPrice(101,3));
        assertEquals(6,(int)DataService.getSizeExtraPrice(102,3));
        assertEquals(4,(int)DataService.getSizeExtraPrice(101,2));
        assertEquals(4,(int)DataService.getSizeExtraPrice(102,2));
        assertEquals(2,(int)DataService.getSizeExtraPrice(101,1));
        assertEquals(2,(int)DataService.getSizeExtraPrice(102,1));

        assertEquals(5,(int)DataService.getSizeExtraPrice(202,3));
        assertEquals(5,(int)DataService.getSizeExtraPrice(201,3));
        assertEquals(4,(int)DataService.getSizeExtraPrice(202,2));
        assertEquals(4,(int)DataService.getSizeExtraPrice(201,2));
        assertEquals(2,(int)DataService.getSizeExtraPrice(202,1));
        assertEquals(2,(int)DataService.getSizeExtraPrice(201,1));



    }
//
    @Test
    public void getIngredientPrice() {
        assertEquals(1.2,DataService.getIngredientPrice(1),0.1);
        assertEquals(1.2,DataService.getIngredientPrice(2),0.1);
        assertEquals(1,(int)DataService.getIngredientPrice(3));
        assertEquals(1,(int)DataService.getIngredientPrice(4));

    }
//
    @Test
    public void creatUser() {
        User user = new User("chen","12345678");
        DataService.creatUser(user);
        assertTrue(DataService.checkInfo(user));
    }
//
    @Test
    public void ifUserExists() {
        assertTrue(DataService.ifUserExists(new User("Se19_3carry1","12345678")));
        assertFalse(DataService.ifUserExists(new User("Xu","1234567")));
        assertFalse(DataService.ifUserExists(new User("","")));
    }

    @Test
    public void checkInfo() {
        assertTrue(DataService.checkInfo(new User("Se19_3carry1","12345678")));
        assertFalse(DataService.checkInfo(new User("Cui","123456")));
        assertFalse(DataService.checkInfo(new User("","")));
    }

    @Test
    public void getTableFields() {
        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("101");
        arrayList1.add("102");
        arrayList1.add("201");
        arrayList1.add("202");
        arrayList1.add("301");
        arrayList1.add("302");
        Map<String, ArrayList<String>> map = DataService.getTableFields("DrinkPriceTable");
        assertEquals(arrayList1,map.get("Id"));;
    }

    @Test
    public void getDrinkBasicName(){
        assertEquals("Cappuccino",DataService.getDrinkBasicName(102));
    }
}