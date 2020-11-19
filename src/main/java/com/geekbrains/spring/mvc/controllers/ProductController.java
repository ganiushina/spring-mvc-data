package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.model.Student;
import com.geekbrains.spring.mvc.repositories.specifications.ProductSpecifications;
import com.geekbrains.spring.mvc.repositories.specifications.StudentSpecifications;
import com.geekbrains.spring.mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// root: http://localhost:8189/app/products

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String showAllProducts(Model model,
                                  @RequestParam(name = "p", defaultValue = "1") Integer pageNumber,
                                  @RequestParam(name = "min_coast", required = false) Integer minCoast,
                                  @RequestParam(name = "max_coast", required = false) Integer maxCoast) {
        Specification<Product> spec = Specification.where(null);
        if (minCoast != null) {
            spec = spec.and(ProductSpecifications.coastGEThan(minCoast));
        }
        if (maxCoast != null) {
            spec = spec.and(ProductSpecifications.coastLEThan(maxCoast));
        }

        List<Product> products = productService.findAll(spec, pageNumber).getContent();
        model.addAttribute("products", products);
        return "all_products";
    }

    @GetMapping("/allProduct")
    public String showAllProductsOrderBy(Model model,
                                         @RequestParam(name = "p", defaultValue = "1") Integer pageNumber,
                                         @RequestParam(name = "sortBy", defaultValue = "asc") String sortBy) {
        Specification<Product> spec = Specification.where(null);
        if (sortBy.equals("asc")) {
     //       spec = spec.and(ProductSpecifications.coastOrderByAsc(sortBy));
        }


        List<Product> products = productService.findAll(spec, pageNumber).getContent();
        model.addAttribute("products", products);
        return "all_products";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_product_form";
    }

    // public String saveNewStudent(@RequestParam Long id, @RequestParam String name, @RequestParam int score) {
    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute Product newProduct) {
        productService.saveOrUpdateProduct(newProduct);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product modifiedProduct) {
        productService.saveOrUpdateProduct(modifiedProduct);
        return "redirect:/products/";
    }

    @GetMapping("/json/{id}")
    @ResponseBody
    public Product showJsonProduct(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/info_by_title")
    @ResponseBody
    public Product infoByTitle(@RequestParam String title) {
        return productService.findByTitle(title);
    }

    @GetMapping("/find_by_min_coast")
    @ResponseBody
    public List<Product> findProductsByMinCoast(@RequestParam int coast) {
        return productService.findByMinCoast(coast);
    }
    @GetMapping("/order_by")
    @ResponseBody
    public List<Product> findByOrderByCoastAsc(@RequestParam String sortType) {
        return productService.findByOrderByCoast(sortType);
        //return "redirect:/products/";
    }

    @GetMapping("/order_by_coast")
    @ResponseBody
    public List<Product> findByCoast(@RequestParam int coast) {
        return productService.findByCoast(coast);
        //return "redirect:/products/";
    }








}
