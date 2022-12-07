package com.works.controllers;

import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    final ProductService productService;

    @GetMapping("/product")
    public String dashboard(Model model) {
        model.addAttribute("news", productService.allProduct() );
        return "product";
    }

}
