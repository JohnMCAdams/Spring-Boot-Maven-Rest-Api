package mediasoft.dev.productapi.rest;

import mediasoft.dev.productapi.entities.Product;
import mediasoft.dev.productapi.repositories.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductRest {

    @Autowired
    private ProductDAO pdao;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = pdao.findAll();
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "id") Long id) {
        Optional<Product> opt = pdao.findById(id);
        if (!opt.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(opt.get());
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProd = pdao.save(product);
        return ResponseEntity.ok(newProd);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable(name = "id") Long id) {
        pdao.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return null;
    }


    //@GetMapping
    /*public String hello() {
        return "<h1>Hello World!</h1>";
    }*/
}
