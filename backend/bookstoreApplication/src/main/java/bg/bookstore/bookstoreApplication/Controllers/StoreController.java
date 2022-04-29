package bg.bookstore.bookstoreApplication.Controllers;

import bg.bookstore.bookstoreApplication.Entities.Product;
import bg.bookstore.bookstoreApplication.Repositories.OrderRepository;
import bg.bookstore.bookstoreApplication.Repositories.ProductRepository;
import bg.bookstore.bookstoreApplication.Repositories.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return ResponseEntity.ok(products);
    }
}
