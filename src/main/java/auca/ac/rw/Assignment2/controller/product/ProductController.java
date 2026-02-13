package auca.ac.rw.Assignment2.controller.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.Assignment2.model.product.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product(1L, "Laptop", "15 inch laptop", 900000.2, "Electronics", 5, "Dell"));
        products.add(new Product(2L, "Phone", "Smartphone", 60000.6, "Electronics", 10, "Samsung"));
        products.add(new Product(3L, "Headphones", "Wireless headphones", 8000.3, "Electronics", 0, "Sony"));
        products.add(new Product(4L, "T-Shirt", "Cotton t-shirt", 20000.2, "Clothing", 30, "H&M"));
        products.add(new Product(5L, "Shoes", "Running shoes", 7099.5, "Clothing", 15, "Nike"));
        products.add(new Product(6L, "Watch", "Digital watch", 5440.0, "Accessories", 8, "Casio"));
        products.add(new Product(7L, "Backpack", "School backpack", 4000.2, "Accessories", 12, "Adidas"));
        products.add(new Product(8L, "Book", "Programming book", 353333.49, "Books", 20, "Reilly"));
        products.add(new Product(9L, "Tablet", "10 inch tablet", 30330.3, "Electronics", 7, "Apple"));
        products.add(new Product(10L, "Camera", "Digital camera", 45990.80, "Electronics", 4, "Canon"));
    }

    // GET /api/products 
    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit) {

        if (page == null || limit == null) {
            return products;
        }

        int fromIndex = page * limit;
        int toIndex = fromIndex + limit;

        if (fromIndex >= products.size()) {
            return new ArrayList<>();
        }

        if (toIndex > products.size()) {
            toIndex = products.size();
        }

        return products.subList(fromIndex, toIndex);
    }

    // GET /api/products/{productId}
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    // GET /api/products/category/{category}
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                result.add(product);
            }
        }

        return result;
    }

    // GET /api/products/brand/{brand}
    @GetMapping("/brand/{brand}")
    public List<Product> getByBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product.getBrand().equalsIgnoreCase(brand)) {
                result.add(product);
            }
        }

        return result;
    }

    // GET /api/products/search?keyword=
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        List<Product> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (Product product : products) {
            if (product.getName().toLowerCase().contains(lowerKeyword)
                    || product.getDescription().toLowerCase().contains(lowerKeyword)) {
                result.add(product);
            }
        }

        return result;
    }

    // GET /api/products/price-range?min=&max=
    @GetMapping("/price-range")
    public List<Product> getByPriceRange(@RequestParam Double min,
                                         @RequestParam Double max) {
        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                result.add(product);
            }
        }

        return result;
    }

    // GET /api/products/in-stock
    @GetMapping("/in-stock")
    public List<Product> getInStock() {
        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (product.getStockQuantity() > 0) {
                result.add(product);
            }
        }

        return result;
    }

    // POST /api/products
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }

    // PUT /api/products/{productId}
    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId,
                                 @RequestBody Product updatedProduct) {

        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setName(updatedProduct.getName());
                product.setDescription(updatedProduct.getDescription());
                product.setPrice(updatedProduct.getPrice());
                product.setCategory(updatedProduct.getCategory());
                product.setStockQuantity(updatedProduct.getStockQuantity());
                product.setBrand(updatedProduct.getBrand());
                return product;
            }
        }

        return null;
    }

    // PATCH /api/products/{productId}/stock?quantity=...
    @PatchMapping("/{productId}/stock")
    public Product updateStock(@PathVariable Long productId,
                               @RequestParam int quantity) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setStockQuantity(quantity);
                return product;
            }
        }

        return null;
    }

    // DELETE /api/products/{productId}
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        products.removeIf(product -> product.getProductId().equals(productId));
        return "Product deleted";
    }
}

