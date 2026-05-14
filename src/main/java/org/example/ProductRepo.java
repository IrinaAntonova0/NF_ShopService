package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ProductRepo {

    private List<Product> products = new ArrayList<>();

    public ProductRepo() {
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductRepo that = (ProductRepo) o;
        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(products);
    }

    @Override
    public String toString() {
        return "org.example.ProductRepo{" +
                "productList=" + products +
                '}';
    }

    boolean addProduct(Product product) {
        if (product == null || products.contains(product)) {
            return false;
        } else {
            return products.add(product);
        }
    }

    boolean addAllProducts(ArrayList<Product> productListNew) {
        productListNew.forEach(addIfMissing);
        return true;
    }
    Consumer<Product> addIfMissing = item -> {
        if (!products.contains(item)) {
            products.add(item);
        }
    };

    boolean removeProductByProdID(Integer prodId) {
        for (Product p : products) {
            if (p.prodID().equals(prodId)) {
                return removeProduct(p);
            }
        }
        return false;
    }//todo testing

    boolean removeProduct(Product pro) {
        if (pro == null || (!products.contains(pro))) {
            return false;
        } else {
            return products.remove(pro);
        }
    }

    boolean removeAllProductsByList(ArrayList<Product> productList) {
        productList.forEach(removeIfContained);
        return true;
    }
    Consumer<Product> removeIfContained = item -> {
        if(products.contains(item)){
            products.remove(item);
        }
    };

    boolean removeAllProducts() {
        if (products.size() > 0) {
            products.clear();
            return true;
        }
        return false;
    }

    Product getProductByProdID(Integer prodId) {
        for (Product p : products) {
            if (p.prodID().equals(prodId)) {
                return p;
            }
        }
        return null;
    }

    Product getProduct(Product product) {
        if (product == null || (!products.contains(product)))
            return null;
        int index = products.indexOf(product);
        return products.get(index);
    }

    ArrayList<Product> getAvailableProducts(ArrayList<Product> productList) {
        ArrayList<Product> availableProducts = (ArrayList<Product>) productList.clone();
        availableProducts.retainAll(products);
        return availableProducts;
    }
}
