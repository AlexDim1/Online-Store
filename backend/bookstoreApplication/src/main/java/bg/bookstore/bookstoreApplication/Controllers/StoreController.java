package bg.bookstore.bookstoreApplication.Controllers;

import bg.bookstore.bookstoreApplication.Entities.Product;
import bg.bookstore.bookstoreApplication.Payload.Response.ProductResponse;
import bg.bookstore.bookstoreApplication.Payload.Response.StoreResponse;
import bg.bookstore.bookstoreApplication.Repositories.OrderRepository;
import bg.bookstore.bookstoreApplication.Repositories.ProductRepository;
import bg.bookstore.bookstoreApplication.Repositories.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Product product = productRepo.findProductById(id);

        if(product == null)
            return ResponseEntity.ok("Няма такъв продукт!");

        return ResponseEntity.ok(new ProductResponse(product));
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        List<Product> products = productRepo.findAll();

        List<StoreResponse> productsResponse = new ArrayList<>();

        for (Product product : products)
            productsResponse.add(new StoreResponse(product));

        List<List<StoreResponse>> productRows = productRows(productsResponse, 2);
        return ResponseEntity.ok(productRows);
    }

    private List<List<StoreResponse>> productRows(List<StoreResponse> products, Integer perRow) {
        List<List<StoreResponse>> productRows = new ArrayList<>();

        Integer rowCount = products.size() / perRow + 1;

        List<StoreResponse> row;
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
