package org.example;

import java.util.*;
import java.util.function.Consumer;

public class OrderMapRepo implements OrderRepoInterface {

    private Map<String, Order> ordersMap = new HashMap<>();

    public OrderMapRepo(Map<String, Order> ordersMap) {
        this.ordersMap = ordersMap;
    }

    public OrderMapRepo() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderMapRepo that = (OrderMapRepo) o;
        return Objects.equals(ordersMap, that.ordersMap);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ordersMap);
    }

    @Override
    public String toString() {
        return "org.example.OrderMapRepo{" +
                "ordersMap=" + ordersMap +
                '}';
    }

    @Override
    public Boolean addOrder(Order order) {
        if (order == null || ordersMap.containsValue(order)) {
            return false;
        } else {
            return ordersMap.put(order.orderID(),order)==null ? true : false;
        }
    }

    @Override
    public boolean addAllOrders(ArrayList<Order> orderListNew) {
        orderListNew.forEach(addIfNew);
        return true;
    }
    Consumer<Order> addIfNew = item -> {
        if (!ordersMap.containsValue(item)) {
            ordersMap.put(item.orderID(),item);
        }
    };

    @Override
    public boolean removeOrderByOrderID(String orderId) {
        if( ordersMap.containsKey(orderId)){
            return ordersMap.remove(orderId)!=null ? true : false;
        }
        else{
            return false;
        }
    }//todo testing

    @Override
    public boolean removeOrder(Order order) {
        if (order == null || (!ordersMap.containsValue(order))) {
            return false;
        } else {
            return ordersMap.remove (order.orderID())!=null ? true : false;
        }
    }

    @Override
    public boolean removeAllOrdersByList(ArrayList<Order> orderList) {
        orderList.forEach(removeIfContained);
        return true;
    }
    Consumer<Order> removeIfContained = item -> {
        if(ordersMap.containsValue(item)){
            ordersMap.remove(item.orderID(), item);
        }
    };

    @Override
    public boolean removeAllOrders() {
        if(ordersMap.size()>0){
            ordersMap.clear();
            return true;
        }
        return false;
    }

    @Override
    public Order getOrderByOrderID(String orderId) {
        return ordersMap.getOrDefault(orderId, null);
    }

    @Override
    public Order getOrder(Order order) {
        if ( order!=null && ordersMap.containsValue(order)) {
            return ordersMap.get(order.orderID());
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Order> getAvailableOrders(ArrayList<Order> orderList) {
        ArrayList<Order> availableOrders = (ArrayList<Order>) orderList.clone();
        availableOrders.retainAll((Collection<Order>) ordersMap);
        return availableOrders;
    }
}
