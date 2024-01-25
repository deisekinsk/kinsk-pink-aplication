package com.kinsk.pink.service;

import com.kinsk.pink.model.Product;
import com.kinsk.pink.model.Subscription;
import com.kinsk.pink.model.User;
import com.kinsk.pink.repository.ProductRespository;
import com.kinsk.pink.repository.SubscriptionRespository;
import com.kinsk.pink.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRespository subsRepository;
   @Autowired
   private UserRespository userRespository;
   @Autowired
   private ProductRespository productRespository;

//    public SubscriptionService(SubscriptionRespository subsRepository) {
//        this.subsRepository = subsRepository;
//    }

    public List<Subscription> findAll(){
        return subsRepository.findAll();
    }

    public Subscription findSubscriptionById (@PathVariable Long id) {
        if (id!=null){
            Optional<Subscription> subscriptionOpt = subsRepository.findById(id);
            return subscriptionOpt.get();
        }else throw new NotFoundException("Subscription not found");

    }

    public Subscription save(Subscription subscription) {
        // set a date
        if (subscription.getStartDate() == null) {
            subscription.setStartDate(new Date());
        }
        subscription.setEndDate(null);

        User user = subscription.getUser();
        if (user != null && user.getId() != null) {
            Long userId = user.getId();
            Optional<User> existingUserOpt = userRespository.findById(userId);
            //subscription -> subscription.setUser(user)
            //subscription é uma instância de Subscription, e você está chamando o método setUser dessa instância.
            existingUserOpt.ifPresent(subscription::setUser);
        }

        Product product = subscription.getProduct();
        if (product != null && product.getId() != null) {
            Long productId = product.getId();
            Optional<Product> existingProductOpt = productRespository.findById(productId);
            //subscription -> subscription.setUser(user)
            //subscription é uma instância de Subscription, e você está chamando o método setUser dessa instância.
            existingProductOpt.ifPresent(subscription::setProduct);
        }

        return subsRepository.save(subscription);
    }

    public Subscription update(Subscription subscription) throws NotFoundException {

        Subscription s = findSubscriptionById(subscription.getId());
        if(s != null) {
            return subsRepository.save(conversion(subscription, s));
        } else {
            throw new NotFoundException("Subscription not found");
        }
    }

    public Subscription conversion (Subscription subscription, Subscription s){
        // Atualiza a data de início, usuário e produto com os valores da nova assinatura
        s.setStartDate(subscription.getStartDate());
        s.setUser(subscription.getUser());
        s.setProduct(subscription.getProduct());

        if (subscription.getEndDate() != null) {
            s.setEndDate(subscription.getEndDate());
        } else {
            // Se a nova assinatura não tiver uma data de término, mantém endDate como nulo
            s.setEndDate(null);
        }

        // Se a nova assinatura não tiver um status, mantém o status original
        if (subscription.getStatus() != null) {
            s.setStatus(subscription.getStatus());
        }

        return s;
    }

    public void deleteById (@PathVariable Long id) throws NotFoundException{
        Optional<Subscription> subscriptionOpt = subsRepository.findById(id);
        if(subscriptionOpt.isPresent()){
            subsRepository.deleteById(id);
        } else throw new NotFoundException("Subscription not found");
    }
}
