package bg.bookstore.bookstoreApplication.Controllers;

import bg.bookstore.bookstoreApplication.Entities.Product;
import bg.bookstore.bookstoreApplication.Payload.Response.GetProductsResponse;
import bg.bookstore.bookstoreApplication.Repositories.OrderRepository;
import bg.bookstore.bookstoreApplication.Repositories.ProductRepository;
import bg.bookstore.bookstoreApplication.Repositories.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/store")
public class StoreController {
    private final ProductRepository productRepo;
    private final ReviewRepository reviewRepo;
    private final OrderRepository orderRepo;

    public StoreController(ProductRepository productRepo, ReviewRepository reviewRepo, OrderRepository orderRepo) {
        this.productRepo = productRepo;
        this.reviewRepo = reviewRepo;
        this.orderRepo = orderRepo;
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        List<Product> products = productRepo.findAll();

        List<GetProductsResponse> productsResponse = new ArrayList<>();

        for (Product product : products)
            productsResponse.add(new GetProductsResponse(product));

        List<List<GetProductsResponse>> productRows = productRows(productsResponse, 2);
        return ResponseEntity.ok(productRows);
    }

    private List<List<GetProductsResponse>> productRows(List<GetProductsResponse> products, Integer perRow) {
        List<List<GetProductsResponse>> productRows = new ArrayList<>();

        Integer rowCount = products.size() / perRow + 1;

        List<GetProductsResponse> row;
        for (int i = 1; i <= rowCount; i++) {
            int endIdx = i * perRow;

            if(endIdx > products.size() - 1)
                endIdx = products.size();
            row = products.subList((i - 1) * perRow, endIdx);

            productRows.add(row);
        }

        return productRows;
    }
}
