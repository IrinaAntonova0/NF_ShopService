package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShopService {

    private ProductRepo produktRepo = new ProductRepo();
    private OrderRepoInterface orderRepoInterface = new OrderListRepo();

    public ShopService() {
    }

    public ShopService(ProductRepo produktRepo, OrderRepoInterface orderRepoInterface) {
        this.produktRepo = produktRepo;
        this.orderRepoInterface = orderRepoInterface;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShopService that = (ShopService) o;
        return Objects.equals(produktRepo, that.produktRepo) && Objects.equals(orderRepoInterface, that.orderRepoInterface);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produktRepo, orderRepoInterface);
    }

    @Override
    public String toString() {
        return "ShopService{" +
                "produktRepo=" + produktRepo +
                ", orderRepoInterface=" + orderRepoInterface +
                '}';
    }

    //Schritt 1: Implementiere eine Methode zum Aufgeben einer neuen Bestellung.
    //Die Artikel werden später unter Angabe der Produkt Id bestellt.
    public Order orderReceipt(String customerName, ArrayList<Product> orderedProductList, List<Integer> orderedProductAmounts ){
        boolean allProductsAvailable = isAllProductsAvailable(orderedProductList);
        if(!allProductsAvailable){
            System.out.println(Messages.PRODUCTS_NOT_AVAILABLE);
            return null;
        }
        return createNewOrder(customerName, orderedProductList, orderedProductAmounts);
    }

    private Order createNewOrder(String customerName, ArrayList<Product> availableProductList, List<Integer> orderedProductAmounts) {
        Order newOrder = Order.createNewOrder(customerName, availableProductList, orderedProductAmounts);
        if( newOrder != null ) {
            orderRepoInterface.addOrder(newOrder);
            System.out.println(Messages.ORDER_ACCEPTED);
        }
        return newOrder;
    }

    private boolean isAllProductsAvailable(ArrayList<Product> orderedProductList) {
        ArrayList<Product> availableProducts = produktRepo.getAvailableProducts(orderedProductList);
        return availableProducts.size()== orderedProductList.size();
    }
}
