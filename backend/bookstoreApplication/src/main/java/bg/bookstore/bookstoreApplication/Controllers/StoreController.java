package bg.bookstore.bookstoreApplication.Controllers;

import bg.bookstore.bookstoreApplication.Repositories.OrderRepository;
import bg.bookstore.bookstoreApplication.Repositories.ProductRepository;
import bg.bookstore.bookstoreApplication.Repositories.ReviewRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
