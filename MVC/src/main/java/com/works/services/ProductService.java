package com.works.services;

import com.works.props.Article;
import com.works.props.ProductData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    final RestTemplate restTemplate;

    public List<Article> allProduct() {
        List<Article> ls = new ArrayList<>();
        try {
            String url = "https://newsapi.org/v2/top-headlines?country=tr&category=business&apiKey=38a9e086f10b445faabb4461c4aa71f8";
            ProductData stData = restTemplate.getForObject(url, ProductData.class);
            ls = stData.getArticles();
        }catch (Exception ex) {
            System.err.println("Product Error : " + ex);
        }
        return ls;
    }

}
