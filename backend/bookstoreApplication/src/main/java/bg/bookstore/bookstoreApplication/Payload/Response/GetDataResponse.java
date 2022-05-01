package bg.bookstore.bookstoreApplication.Payload.Response;

import java.util.List;

public class GetDataResponse {
    private List<AdminProductResponse> products;
    private List<AdminOrderResponse> orders;

    public GetDataResponse(List<AdminProductResponse> products, List<AdminOrderResponse> orders) {
        this.products = products;
        this.orders = orders;
    }

    public List<AdminProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<AdminProductResponse> products) {
        this.products = products;
    }

    public List<AdminOrderResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<AdminOrderResponse> orders) {
        this.orders = orders;
    }
}
