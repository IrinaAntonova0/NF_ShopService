package org.example;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class OrderListRepo implements OrderRepoInterface {
    private ArrayList<Order> orders = new ArrayList<>();

    public OrderListRepo(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public OrderListRepo() {
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderListRepo that = (OrderListRepo) o;
        return Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orders);
    }

    @Override
    public String toString() {
        return "org.example.OrderListRepo{" +
                "orders=" + orders +
                '}';
    }

    @Override
    public Boolean addOrder(Order order){
        if (order == null || orders.contains(order)) {
            return false;
        } else {
            return orders.add(order);
        }
    }

    @Override
    public boolean addAllOrders(ArrayList<Order> orderListNew) {
        orderListNew.forEach(addIfNew);
        return true;
    }
    Consumer<Order> addIfNew = item -> {
        if (!orders.contains(item)) {
            orders.add(item);
        }
    };

    @Override
    public boolean removeOrderByOrderID(String orderId) {
        for (Order or : orders) {
            if (or.orderID().equals(orderId)) {
                return removeOrder(or);
            }
        }
        return false;
    }//todo testing

    @Override
    public boolean removeOrder(Order order) {
        if (order == null || (!orders.contains(order))) {
            return false;
        } else {
            return orders.remove(order);
        }
    }

    @Override
    public boolean removeAllOrdersByList(ArrayList<Order> orderList) {
        orderList.forEach(removeIfContained);
        return true;
    }
    Consumer<Order> removeIfContained = item -> {
        if(orders.contains(item)){
            orders.remove(item);
        }
    };

    @Override
    public boolean removeAllOrders() {
        if (orders.size() > 0) {
            orders.clear();
            return true;
        }
        return false;
    }

    @Override
    public Order getOrderByOrderID(String orderId) {
        for (Order or : orders) {
            if (or.orderID().equals(orderId)) {
                return or;
            }
        }
        return null;
    }

    @Override
    public Order getOrder(Order order) {
        if (order == null || (!orders.contains(order)))
            return null;
        int index = orders.indexOf(order);
        return orders.get(index);
    }

    @Override
    public ArrayList<Order> getAvailableOrders(ArrayList<Order> orderList) {
        ArrayList<Order> availableOrders = (ArrayList<Order>) orderList.clone();
        availableOrders.retainAll(orders);
        return availableOrders;
    }

}
