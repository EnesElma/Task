package com.enes.product.service;

import com.enes.product.entity.Product;
import com.enes.product.exception.CustomException;
import com.enes.product.repo.IProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Value("${user.service.url}")
    private String user_service_url;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final IProductRepository repository;

    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public Product createProduct(Product product){
        if (verifyUsername(product.getUsername())){
            throw new CustomException("Username not found");
        }

        Optional<Product> savedProduct = repository.findByName(product.getName());
        if (savedProduct.isPresent()){
            throw new CustomException("Product already exist");
        }
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Product request){

        if (verifyUsername(request.getUsername())){
            throw new CustomException("Username not found");
        }

        Optional<Product> product = repository.findById(request.getId());
        if (product.isEmpty()){
            throw new CustomException("Product not found");
        }
        return repository.save(request);
    }

    @Override
    public void deleteProduct(Long id, String username){
        if (verifyUsername(username)){
            throw new CustomException("Username not found");
        }

        repository.deleteByIdAndUsername(id,username);
    }

    @Override
    public List<Product> listAllProduct(){
        return repository.findAll();
    }

    public boolean verifyUsername(String username){

        User result = webClientBuilder.build()
                .get().uri(user_service_url+"/"+username)
                .retrieve()
                .bodyToMono(User.class).share().block();

        return result.getUsername() == null;
    }
}

@Data
class User{
    private Long id;
    private String name;
    private String username;
}

