package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopServiceMain {


    static void main(String[] args) {
        System.out.println("org.example.ShopServiceMain.main");

        ShopService myShop = new ShopService(createProduct1to6Repo(), new OrderListRepo());

        Order order = myShop.orderReceipt("firstCustomer", createProductList5to6(), List.of(5,6));

        System.out.println("Order " + order);
        System.out.println("myShop.proRepo = " + myShop);

/*        // wait for some input
        Scanner sc = new Scanner(System.in);
        sc.hasNextLine();*/
    }

    private static ArrayList<Product> createProductList5to6() {
        Product pro5 = new Product(5,"pro5", new BigDecimal("5.0"));
        Product pro6 = new Product(6,"pro6", new BigDecimal("6.0"));
        //Product pro7 = new Product(7,"pro7", new BigDecimal("7.0"));
        return new ArrayList<>(Arrays.asList(pro5, pro6));
    }

    private static ProductRepo createProduct1to6Repo() {
        Product pro = new Product(1,"pro1", new BigDecimal("1.0"));
        Product pro2 = new Product(2,"pro2", new BigDecimal("2.0"));
        Product pro3 = new Product(3,"pro3", new BigDecimal("3.0"));
        Product pro4 = new Product(4,"pro4",new BigDecimal("4.0"));
        Product pro5 = new Product(5,"pro5", new BigDecimal("5.0"));
        Product pro6 = new Product(6,"pro6", new BigDecimal("6.0"));
        ProductRepo proRepo = new ProductRepo();
        proRepo.addProduct(pro);
        proRepo.addProduct(pro2);
        proRepo.addProduct(pro3);
        proRepo.addProduct(pro4);
        proRepo.addProduct(pro5);
        proRepo.addProduct(pro6);
        return proRepo;
    }

/*    List<Integer> zahlen = Arrays.asList(1, 2, 3, 4, 5);
    IntSummaryStatistics stats = zahlen.stream()
            .mapToInt(Integer::intValue)
            .summaryStatistics();
System.out.println(stats);
// Ausgabe: IntSummaryStatistics{count=5, sum=15, min=1, average=3.000000, max=5}*/

}
