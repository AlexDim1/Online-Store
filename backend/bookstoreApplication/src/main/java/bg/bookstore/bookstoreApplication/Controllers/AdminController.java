package bg.bookstore.bookstoreApplication.Controllers;

import bg.bookstore.bookstoreApplication.Entities.Order;
import bg.bookstore.bookstoreApplication.Entities.Product;
import bg.bookstore.bookstoreApplication.Payload.Request.AddProductRequest;
import bg.bookstore.bookstoreApplication.Payload.Response.AdminOrderResponse;
import bg.bookstore.bookstoreApplication.Payload.Response.AdminProductResponse;
import bg.bookstore.bookstoreApplication.Payload.Response.ProductsAndOrdersResponse;
import bg.bookstore.bookstoreApplication.Repositories.OrderRepository;
import bg.bookstore.bookstoreApplication.Repositories.ProductRepository;
import bg.bookstore.bookstoreApplication.Repositories.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/data")
    public ProductsAndOrdersResponse getData() {
        List<Product> products = productRepo.findAll();
        List<AdminProductResponse> productsResponse = new ArrayList<>();
        for (Product product: products)
            productsResponse.add(new AdminProductResponse(product));

        List<Order> orders = orderRepo.findAll();
        List<AdminOrderResponse> ordersResponse = new ArrayList<>();
        for (Order order: orders)
            ordersResponse.add(new AdminOrderResponse(order));

        return new ProductsAndOrdersResponse(productsResponse, ordersResponse);
    }

    @PostMapping("/products/add")
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest request) {
        Product product = productRepo.findProductByName(request.getName());

        if (product != null)
            return new ResponseEntity<>("Продуктът вече е добавен.", HttpStatus.BAD_REQUEST);

        product = new Product(request.getName(),
                request.getAuthor(),
                request.getDescription(),
                request.getShortDescription(),
                request.getPageCount(),
                request.getPrice());

        productRepo.save(product);
        return ResponseEntity.ok("Продуктът " + product.getName() + " с id: " + product.getId() + " e добавен успешно.");
    }
}
