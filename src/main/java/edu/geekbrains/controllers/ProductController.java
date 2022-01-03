package edu.geekbrains.controllers;


import edu.geekbrains.entities.Product;
import edu.geekbrains.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> startPage(
            @RequestParam(name = "minFilter", defaultValue = "0") BigDecimal minPrice,
            @RequestParam(name = "maxFilter", required = false) BigDecimal maxPrice
    ) {
        if (maxPrice == null) {
            maxPrice = BigDecimal.valueOf(Integer.MAX_VALUE);
        }

        return productService.findByPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/products/get/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PostMapping("/products/addProduct")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    /**
     * ToDo
     * 3. * К запросу всех товаров добавьте возможность фильтрации по минимальной и максимальной цене (
     * в трех вариантах: товары дороже min цены, товары дешевле max цены, или товары, цена которых находится
     * в пределах min-max)
     */
//    @GetMapping("/products/filter")
//    public List<Product> findAllByPriceBetween(@RequestParam(defaultValue = "0") BigDecimal min, @RequestParam(defaultValue = "2000000000") BigDecimal max) {
//        return productService.findByPriceBetween(min, max);
//    }

    @GetMapping("/products/change_quantity")
    public void changeQuantity(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changeQuantity(productId, delta);
    }
}
