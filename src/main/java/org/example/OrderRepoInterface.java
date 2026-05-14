package org.example;

import java.util.ArrayList;

public interface OrderRepoInterface {
    Boolean addOrder(Order order);

    boolean addAllOrders(ArrayList<Order> orderListNew);

    boolean removeOrderByOrderID(String orderId);//todo testing

    boolean removeOrder(Order order);

    boolean removeAllOrdersByList(ArrayList<Order> orderList);

    boolean removeAllOrders();

    Order getOrderByOrderID(String orderId);

    Order getOrder(Order order);

    ArrayList<Order> getAvailableOrders(ArrayList<Order> orderList);
}
