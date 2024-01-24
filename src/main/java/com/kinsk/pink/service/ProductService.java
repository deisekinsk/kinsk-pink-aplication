package com.kinsk.pink.service;

import com.kinsk.pink.model.Product;
import com.kinsk.pink.repository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRespository productRepository;

    public ProductService(ProductRespository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> findAll(){
        return productRepository.findAll();

    }
    public Product findProductById (@PathVariable Long id)
            throws NotFoundException {

        if (id!=null){
            Optional<Product> product = productRepository.findById(id);
            return product.get();
        }else throw new NotFoundException();

    }

    public Product save (Product product){
        return productRepository.save(product);
    }


    public Product update (Product product) throws NotFoundException {
        Product p = findProductById(product.getId());
        return productRepository.save(conversion(product));
    }

    public Product conversion (Product product){
        Product p = new Product();
        p.setId(product.getId());
        p.setName(product.getName());
        p.setPriceRoot(product.getPriceRoot());
        return p;
    }

    public void deleteById (@PathVariable Long id) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
        } else throw new NotFoundException();
    }

}
