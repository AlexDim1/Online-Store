package bg.bookstore.bookstoreApplication.Controllers;

import bg.bookstore.bookstoreApplication.Entities.Product;
import bg.bookstore.bookstoreApplication.Payload.Request.AddProductRequest;
import bg.bookstore.bookstoreApplication.Repositories.OrderRepository;
import bg.bookstore.bookstoreApplication.Repositories.ProductRepository;
import bg.bookstore.bookstoreApplication.Repositories.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ProductRepository productRepo;
    private final ReviewRepository reviewRepo;
    private final OrderRepository orderRepo;

    public AdminController(ProductRepository productRepo, ReviewRepository reviewRepo, OrderRepository orderRepo) {
        this.productRepo = productRepo;
        this.reviewRepo = reviewRepo;
        this.orderRepo = orderRepo;
    }

    @PostMapping("/products/add")
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest request) {
        Product product = productRepo.findProductByName(request.getName());

        if (product != null)
            return new ResponseEntity<>("Продуктът вече е добавен.", HttpStatus.BAD_REQUEST);

        product = new Product(request.getName(),
                request.getAuthor(),
                request.getDescription(),
                request.getPageCount(),
                request.getPrice());

        productRepo.save(product);
        return ResponseEntity.ok("Продуктът " + product.getName() + " с id: " + product.getId() + " e добавен успешно.");
    }
}
