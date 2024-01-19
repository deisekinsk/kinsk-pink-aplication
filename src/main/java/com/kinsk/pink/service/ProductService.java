package com.kinsk.pink.service;

import com.kinsk.pink.model.Product;
import com.kinsk.pink.repository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRespository productRespository;

    public ProductService(ProductRespository productRespository) {
        this.productRespository = productRespository;
    }

    public List<Product> findAll(){
        return productRespository.findAll();
    }

    @GetMapping(value = "/{ID}")
    public Product findProductById (@PathVariable Long id)
            throws NotFoundException {

        if (id!=null){
            Optional<Product> product = productRespository.findById(id);
            return product.get();
        }else throw new NotFoundException();

    }





//    public save (){}
//    public update (){}
//    public update (){}





}
