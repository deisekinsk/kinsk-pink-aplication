package com.kinsk.pink.service;

import com.kinsk.pink.model.PricingCategory;
import com.kinsk.pink.repository.PricingCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class PrincingCategoryService {

    @Autowired
    private PricingCategoryRepository pricingCategoryRepository;


    public PrincingCategoryService(PricingCategoryRepository pricingCategoryRepository) {
        this.pricingCategoryRepository = pricingCategoryRepository;
    }

    public List<PricingCategory> findAll(){
        return pricingCategoryRepository.findAll();
    }

    public PricingCategory findPricingById (@PathVariable Long id)
            throws ChangeSetPersister.NotFoundException {

        if (id!=null){
            Optional<PricingCategory> pricingCategory =
                    pricingCategoryRepository.findById(id);
            return pricingCategory.get();
        }else throw new ChangeSetPersister.NotFoundException();

    }

    public PricingCategory save(PricingCategory pricingCategory){
        return pricingCategoryRepository.save(pricingCategory);
    }


    public void deleteById(@PathVariable Long id) throws NotFoundException {
        Optional<PricingCategory> pricingCategory = pricingCategoryRepository.findById(id);
        if(pricingCategory.isPresent()){
            pricingCategoryRepository.deleteById(id);
        }else throw new NotFoundException("Pricing Categoru not found");
    }




}
