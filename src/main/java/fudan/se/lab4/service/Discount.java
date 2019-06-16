package fudan.se.lab4.service;

import fudan.se.lab4.dto.Order;
import scala.Tuple2;

public interface Discount {
    Tuple2<Double, String> calculate(Order order);
}
